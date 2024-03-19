import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.currentTimeMillis;

public class Student extends Account
{
    static Scanner scanner = new Scanner (System.in);

    protected static String skipLine = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";

    protected static ArrayList <String>  allUserNames  = new ArrayList <> ();
    protected static ArrayList <String>  allOwlmails   = new ArrayList <> ();
    protected static ArrayList <String>  allPasswords  = new ArrayList <> ();
    protected static ArrayList <UUID>    allAccountIDs = new ArrayList <> ();
    protected static ArrayList <String>  allFullNames  = new ArrayList <> ();
    protected static ArrayList <Student> allStudents   = new ArrayList <> ();

    protected boolean tookSortingQuiz;
    protected String  studentHouse;

    private final byte[] salt;

    protected ArrayList <Course> coursesList = new ArrayList <> ();

    /*
    CONSTRUCTOR FUNCTIONS
    */

    public Student (String userName, String owlmail, String password, String fullName)
    {
        this.userName = userName;
        this.owlmail  = owlmail;

        byte[] salt = generateSalt ();
        this.salt = salt;
        password  = hashPassword (password, salt);

        this.password = password;
        this.fullName = fullName;

        Student.allUserNames.add (userName);
        Student.allOwlmails.add (owlmail);
        Student.allPasswords.add (password);
        Student.allFullNames.add (fullName);

        this.accountID = UUID.randomUUID ();
        Student.allAccountIDs.add (accountID);
    }

    public static void addStudent (Student student)
    {
        Student.allStudents.add (student);
    }

    /*
    ACCOUNT FUNCTIONS
    */

    public void accountSetting (Student student)
    {
        System.out.println (skipLine);

        System.out.println ("Hogwarts School Datacenter.");
        System.out.println ("Student account setting");

        System.out.println ("1. Change Username");
        System.out.println ("2. Change Password");
        System.out.println ("3. Change Owlmail");
        System.out.print ("Enter your choice: ");

        int menuInput = scanner.nextInt ();
        scanner.nextLine ();
        if (menuInput == 0)
        {
            return;
        }

        switch (menuInput)
        {
            case 1:
                student.changeUsername (student);
                break;
            case 2:
                student.changePassword (student);
                break;
            case 3:
                student.changeOwlmail (student);
                break;
            default:
                return;
        }
    }

    public void changeUsername (Student student)
    {
        System.out.println (skipLine);

        System.out.println ("Hogwarts School Datacenter.");
        System.out.println ("Student change username");

        System.out.print ("Current password: ");
        String password = scanner.nextLine ();
        if (password.equals ("esc"))
        {
            return;
        }
        password = hashPassword (password, student.salt);

        int countOfIncorrectAttempts = 0;

        while (! password.equals (student.password))
        {
            countOfIncorrectAttempts++;
            if (countOfIncorrectAttempts < 5)
            {
                System.out.println ("False incantation! Attempt once more, or invoke 'Forgot Password' to unravel the enchantment.");
            }
            else
            {
                System.out.println ("By Merlin's beard, you have forgotten the password. Someone must've used Obliviate on you. Go on, try again.");
            }
            System.out.print ("Current Password: ");

            password = scanner.nextLine ();
            if (password.equals ("esc"))
            {
                return;
            }
            password = hashPassword (password, student.salt);
        }

        System.out.print ("New username: ");
        String userName = scanner.nextLine ();
        if (userName.equals ("esc"))
        {
            return;
        }

        while (validateUserName (userName) != 1)
        {
            if (validateUserName (userName) == 2)
            {
                System.out.println ("This name is already enscribed in the tome. Conjure forth a new one to claim as your own.");
                System.out.print ("New Username: ");

                userName = scanner.nextLine ();
                if (userName.equals ("esc"))
                {
                    return;
                }
            }
            else if (validateUserName (userName) == 0)
            {
                System.out.println ("Your chosen moniker must be imbued with at least six enchanted characters. Bestow upon it a more potent charm and try anew.");
                System.out.print ("New username: ");

                userName = scanner.nextLine ();
                if (userName.equals ("esc"))
                {
                    return;
                }
            }
        }

        for (int i = 0; i < allPasswords.size (); i++)
        {
            if (password.equals (allPasswords.get (i)))
            {
                allUserNames.set (i, userName);
            }
        }
        student.userName = userName;

        System.out.println ("Username changed successfully");
        Main.sleep (1000);
    }

    public void changePassword (Student student)
    {
        System.out.println (skipLine);

        System.out.println ("Hogwarts School Datacenter.");
        System.out.println ("Student change password");

        System.out.print ("Current password: ");
        String password = scanner.nextLine ();
        if (password.equals ("esc"))
        {
            return;
        }
        password = hashPassword (password, student.salt);

        int countOfIncorrectAttempts = 0;

        while (! password.equals (student.password))
        {
            countOfIncorrectAttempts++;
            if (countOfIncorrectAttempts < 5)
            {
                System.out.println ("False incantation! Attempt once more, or invoke 'Forgot Password' to unravel the enchantment.");
            }
            else
            {
                System.out.println ("By Merlin's beard, you have forgotten the password. Someone must've used Obliviate on you. Go on, try again.");
            }
            System.out.print ("Current Password: ");

            password = scanner.nextLine ();
            if (password.equals ("esc"))
            {
                return;
            }
            password = hashPassword (password, student.salt);
        }

        System.out.print ("New Password: ");
        String firstPassword = scanner.nextLine ();
        if (firstPassword.equals ("esc"))
        {
            return;
        }

        System.out.print ("Confirm Password: ");
        String secondPassword = scanner.nextLine ();
        if (secondPassword.equals ("esc"))
        {
            return;
        }

        while (Student.validatePassword (firstPassword, secondPassword) != 1)
        {
            if (Student.validatePassword (firstPassword, secondPassword) == 2)
            {
                System.out.println ("The incantations do not align! Speak your password once more to synchronize the enchantments.");

                System.out.print ("New Password: ");
                firstPassword = scanner.nextLine ();
                if (firstPassword.equals ("esc"))
                {
                    return;
                }

                System.out.print ("Confirm Password: ");
                secondPassword = scanner.nextLine ();
                if (secondPassword.equals ("esc"))
                {
                    return;
                }
            }
            else if (Student.validatePassword (firstPassword, secondPassword) == 0)
            {
                System.out.println ("Magical defenses demand greater complexity! Forge a new password adorned with an uppercase incantation, a numerical talisman, and a special character charm.");

                System.out.print ("Password: ");
                firstPassword = scanner.nextLine ();
                if (firstPassword.equals ("esc"))
                {
                    return;
                }

                System.out.print ("Confirm Password: ");
                secondPassword = scanner.nextLine ();
                if (secondPassword.equals ("esc"))
                {
                    return;
                }
            }
        }

        for (int i = 0; i < allPasswords.size (); i++)
        {
            if (password.equals (allPasswords.get (i)))
            {
                allPasswords.set (i, hashPassword (firstPassword, student.salt));
            }
        }
        student.password = hashPassword (firstPassword, student.salt);

        System.out.println ("Password changed successfully");
        Main.sleep (1000);
    }

    public void changeOwlmail (Student student)
    {
        System.out.println (skipLine);

        System.out.println ("Hogwarts School Datacenter.");
        System.out.println ("Student change password");

        System.out.print ("Current password: ");
        String password = scanner.nextLine ();
        if (password.equals ("esc"))
        {
            return;
        }
        password = hashPassword (password, student.salt);

        int countOfIncorrectAttempts = 0;

        while (! password.equals (student.password))
        {
            countOfIncorrectAttempts++;
            if (countOfIncorrectAttempts < 5)
            {
                System.out.println ("False incantation! Attempt once more, or invoke 'Forgot Password' to unravel the enchantment.");
            }
            else
            {
                System.out.println ("By Merlin's beard, you have forgotten the password. Someone must've used Obliviate on you. Go on, try again.");
            }
            System.out.print ("Current Password: ");

            password = scanner.nextLine ();
            if (password.equals ("esc"))
            {
                return;
            }
            password = hashPassword (password, student.salt);
        }

        System.out.print ("New owlmail: ");
        String owlmail = scanner.nextLine ();
        if (owlmail.equals ("esc"))
        {
            return;
        }

        while (Student.validateOwlmail (owlmail) != 1)
        {
            if (Student.validateOwlmail (owlmail) == 2)
            {
                System.out.println ("Unfortunately, this owl is already taken. Present a new owl, or sign in if you possess an existing account.");
                System.out.print ("New owlmail: ");

                owlmail = scanner.nextLine ();
                if (owlmail.equals ("esc"))
                {
                    return;
                }
            }
            else if (Student.validateOwlmail (owlmail) == 0)
            {
                System.out.println ("Unfortunately, this owl can't be accepted! Provide another owl to continue.");
                System.out.print ("New owlmail: ");

                owlmail = scanner.nextLine ();
                if (owlmail.equals ("esc"))
                {
                    return;
                }
            }
        }

        for (int i = 0; i < allPasswords.size (); i++)
        {
            if (password.equals (allPasswords.get (i)))
            {
                allOwlmails.set (i, owlmail);
            }
        }
        student.owlmail = owlmail;

        System.out.println ("Owlmail changed successfully");
        Main.sleep (1000);
    }

    public static Student findStudentByOwlmail (String owlmail)
    {
        for (Student student : allStudents)
        {
            if (student.owlmail.equalsIgnoreCase (owlmail))
            {
                return student;
            }
        }
        return null;
    }

    public static Student findStudentByName (String fullName)
    {
        for (Student s : allStudents)
        {
            if (s.fullName.equalsIgnoreCase (fullName))
            {
                return s;
            }
        }
        return null;
    }

    /*
    STUDENT FUNCTIONS
    */

    public void takeCourse (Student student)
    {
        System.out.println (skipLine);

        System.out.println ("Hogwarts School Datacenter.");
        System.out.println ("Take Course Menu");

        System.out.println ();
        Course.viewAvailableCourses ();

        System.out.print ("Name the course you seek to delve into: ");

        String courseName = scanner.nextLine ();
        if (courseName.equals ("esc"))
        {
            return;
        }

        while (Course.findCourse (courseName) == null)
        {
            System.out.println ("The scrolls of Hogwarts hold no record of such a course. \n" +
                    "Tarry a moment, wizard, and ensure the incantation of your chosen subject is spoken true.");
            System.out.print ("Name the course you seek to delve into: ");

            courseName = scanner.nextLine ();
            if (courseName.equals ("esc"))
            {
                return;
            }
        }

        Course course = Course.findCourse (courseName);
        assert course != null;
        course.addStudent (student);
        student.coursesList.add (course);

        System.out.println ("Course enrolled successfully. May your studies be as fruitful as a field of Mandrakes in springtime.");
        Main.sleep (1000);
    }

    public void viewAllCourses ()
    {
        System.out.println (skipLine);
        System.out.println ("Courses List:");
        for (Course course : coursesList)
        {
            System.out.println (course.title + " - Professor " + course.professor.fullName);
        }
        System.out.println ("Press Enter to Continue ");
        scanner.nextLine ();
    }

    public void viewAllProfessors ()
    {
        Course[] coursesListArray = new Course[(coursesList.size ())];
        coursesList.toArray (coursesListArray);

        System.out.println ("Professors List:");
        for (Course course : coursesListArray)
        {
            System.out.println ("Professor " + course.professor.fullName);
        }
        System.out.println ("Press Enter to Continue ");
        scanner.nextLine ();
    }

    public void takeSortingQuiz ()
    {
        if (tookSortingQuiz)
        {
            System.out.println ("Once sorted, forever bound. Alas, you cannot partake in the Sorting Quiz twice, young wizard.");
            Main.sleep (5000);
        }
        else
        {
            System.out.println (skipLine);

            int ravenclawPoints = 0;
            int gryffindorPoints = 0;
            int slytherinPoints = 0;
            int hufflepuffPoints = 0;

            String[] questions = {"Which of the following would you most hate people to call you?",
                    "After you have died, what would you most like people to do when they hear your name?",
                    "Given the choice, would you rather invent a potion that would guarantee you:",
                    "How would you like to be known to history?",
                    "You enter an enchanted garden. What would you be most curious to examine first?",
                    "What kind of instrument most pleases your ear?",
                    "Four boxes are placed before you. Which would you try and open?",
                    "Four goblets are placed before you. Which would you choose to drink?",
                    "Once every century, the Flutterby bush produces flowers that adapt their scent to attract the unwary. \n" +
                            "If it lured you, it would smell of:",
                    "A troll has gone berserk in the Headmaster's study at Hogwarts. It is about to smash, crush and tear several irreplaceable items and treasures, \n" +
                            "including a cure for dragon pox, which the Headmaster has nearly perfected; \n" +
                            "student records going back 1000 years and a mysterious handwritten book full of strange runes, believed to have belonged to Merlin. \n" +
                            "In which order would you rescue these objects from the troll's club, if you could?",
                    "Which would you rather be?",
                    "Which of the following do you find most difficult to deal with?",
                    "What are you most looking forward to learning at Hogwarts?",
                    "If you could have any power, which would you choose?",
                    "Which of the following would you most like to study?",
                    "One of your house mates has cheated in a Hogwarts exam by using a Self-Spelling Quill. \n" +
                            "Now he has come top of the class in Charms, beating you into second place. Professor Flitwick is suspicious of what happened. \n" +
                            "He draws you to one side after his lesson and asks you whether or not your classmate used a forbidden quill. What do you do?",
                    "You and two friends need to cross a bridge guarded by a river troll who insists on fighting one of you before he will let all of you pass. Do you:",
                    "Which road tempts you most?",
                    "Which nightmare would frighten you most?",
                    "Late at night, walking alone down the street, you hear a peculiar cry that you believe to have a magical source. Do you:",
                    "A Muggle confronts you and says that they are sure you are a witch or wizard. Do you:"
            };

            String[][] answers = {
                    {"2 Ordinary", "0 Ignorant", "1 Cowardly", "3 Selfish"},
                    {"3 Miss you, but smile", "1 Ask for more stories about your adventures", "0 Think with admiration of your achievements",
                            "2 I don't care what people think of me after I'm dead; it's what they think when I'm alive that counts"},
                    {"3 Love?", "1 Glory?", "0 Wisdom?", "2 Power?"},
                    {"0 The Wise", "3 The Good", "2 The Great", "1 The Bold"},
                    {"0 The silver leafed tree bearing golden apples", "3 The fat red toadstools that appear to be talking to each other",
                            "2 The bubbling pool, in the depths of which something luminous is swirling?", "1 The statue of an old wizard with a strangely twinkling eye"},
                    {"2 The violin", "3 The trumpet", "0 The piano", "1 The drum"},
                    {"3 The small tortoiseshell box, embellished with gold, inside which some small creature seems to be squeaking.",
                            "2 The gleaming jet black box with a silver lock and key, marked with a mysterious rune that you know to be the mark of Merlin.",
                            "0 The ornate golden casket, standing on clawed feet, whose inscription warns that both secret knowledge and unbearable temptation lie within.",
                            "1 The small pewter box, unassuming and plain, with a scratched message upon it that reads \"I open only for the worthy.\""},
                    {"0 The foaming, frothing, silvery liquid that sparkles as though containing ground diamonds.",
                            "3 The smooth, thick, richly purple drink that gives off a delicious smell of chocolate and plums.",
                            "1 The golden liquid so bright that it hurts the eye, and which makes sunspots dance all around the room.",
                            "2 The mysterious black liquid that gleams like ink, and gives off fumes that make you see strange visions."},
                    {"1 A crackling log fire", "2 The sea", "0 Fresh parchment", "3 Home"},
                    {"0 Book/Records/Cure", "3 Records/Book/Cure", "2 Records/Cure/Book", "1 Cure/Records/Book"},
                    {"0 Imitated?", "2 Praised?", "3 Liked?", "1 Trusted?"},
                    {"0 Hunger", "3 Cold", "1 Loneliness", "2 Boredom"},
                    {"1 Apparition and Disapparition", "0 Transfiguration", "3 All about magical creatures, and how to befriend/care for them?", "2 Every area of magic I can"},
                    {"2 The power to read minds", "1 The power of invisibility", "3 The power of superhuman strength", "0 The power to speak to animals"},
                    {"1 Centaurs & Werewolves", "2 Merpeople", "0 Goblins & Ghosts", "3 Trolls"},
                    {"3 Lie and say you don't know, but hope that somebody else tells Professor Flitwick the truth.",
                            "1 Tell Professor Flitwick that he ought to ask your classmate and resolve to tell your classmate that if he doesn't tell the truth, you will.",
                            "0 Tell Professor Flitwick the truth. If your classmate is prepared to win by cheating, he deserves to be found out. \n" +
                                    "Also, as you are both in the same house, any points he loses will be regained by you, for coming first in his place.",
                            "2 You would not wait to be asked to tell Professor Flitwick the truth. \n" +
                                    "If you knew that somebody was using a forbidden quill, you would tell the professor before the exam started."},
                    {"0 Attempt to confuse the troll into letting all three of you pass without fighting?",
                            "3 Suggest drawing lots to decide which of you will fight?",
                            "2 Suggest that all three of you should fight without telling the troll?",
                            "1 Volunteer to fight?"},
                    {"3 The wide, sunny, grassy lane", "2 The narrow, dark, lantern-lit alley",
                            "1 The twisting, leaf-strewn path through woods", "0 The cobbled street lined with ancient buildings"},
                    {"0 Standing on top of something very high and realizing suddenly that there are no hand- or footholds, nor any barrier to stop you falling",
                            "1 An eye at the keyhole of the dark, windowless room in which you are locked",
                            "3 Waking up to find that neither your friends nor your family have any idea who you are.",
                            "2 Being forced to speak in such a silly voice that hardly anyone can understand you, and everyone laughs at you"},
                    {"3 Proceed with caution, keeping one hand on your concealed wand and an eye out for any disturbance?",
                            "1 Draw your wand and try to discover the source of the noise?",
                            "2 Draw your wand and stand your ground?",
                            "0 Withdraw into the shadows to await developments, while mentally reviewing the most appropriate defensive and offensive spells, should trouble occur?"},
                    {"0 Ask what makes them think so?", "2 Agree, and ask whether they'd like a free sample of a jinx?",
                            "1 Agree, and walk away, leaving them to wonder whether you are bluffing?", "3 Tell them that you are worried about their mental health, and offer to call a doctor."}
            };

            Random random = new Random (currentTimeMillis ());
            int welcomeRandom = random.nextInt () % 5;

            switch (welcomeRandom)
            {
                case 0 -> Main.slowPrint ("Ah, another curious soul seeking guidance. Let me delve into the depths of your mind and heart to find where you truly belong.", 50);
                case 1 -> Main.slowPrint ("What do we have here, a new arrival, eager to embark on your journey through Hogwarts. Let me see what makes you tick.", 50);
                case 2 -> Main.slowPrint ("Welcome, welcome! Another eager mind awaits its fate. Let me peer into the depths of your soul.", 50);
                case 3 -> Main.slowPrint ("Ah, another soul to be sorted, how intriguing! Let me delve into your essence and see where you belong.", 50);
                case 4 -> Main.slowPrint ("Ah, another seeker of destiny has arrived! Let me peer into the depths of your being and uncover your true nature.", 50);
                default -> Main.slowPrint ("Welcome, welcome! I see an eager young wizard in search of their fate. Let me see through the endless futures to choose your destiny.", 50);
            }

            Main.sleep (2000);

            Scanner scanner = new Scanner (System.in);
            for (int i = 0; i < questions.length; i++)
            {
                System.out.println (skipLine + "Question " + (i + 1) + ":\n\n" + questions[i]);
                for (int j = 0; j < answers[i].length; j++)
                {
                    String[] parts = answers[i][j].split (" ", 2);
                    System.out.println ((j + 1) + "- " + parts[1]);
                }

                System.out.print ("Enter your choice, young wizard: ");
                int answerIndex = scanner.nextInt () - 1;

                if (answerIndex >= 0 && answerIndex < answers[i].length)
                {
                    switch (Integer.parseInt (answers[i][answerIndex].substring (0, 1)))
                    {
                        case 0:
                            ravenclawPoints++;
                            break;
                        case 1:
                            gryffindorPoints++;
                            break;
                        case 2:
                            slytherinPoints++;
                            break;
                        case 3:
                            hufflepuffPoints++;
                            break;
                    }
                }
                else
                {
                    System.out.println ("Invalid answer. Skipping to the next question.");
                }
            }

            System.out.println (skipLine);
            tookSortingQuiz = true;
            int maxPoints = Math.max (ravenclawPoints, Math.max (gryffindorPoints, Math.max (slytherinPoints, hufflepuffPoints)));

            if (maxPoints == ravenclawPoints)
            {
                System.out.println ("Congratulations! You belong to Ravenclaw!");
                studentHouse = "Ravenclaw";
            }
            else if (maxPoints == gryffindorPoints)
            {
                System.out.println ("Congratulations! You belong to Gryffindor!");
                studentHouse = "Gryffindor";
            }
            else if (maxPoints == slytherinPoints)
            {
                System.out.println ("Congratulations! You belong to Slytherin!");
                studentHouse = "Slytherin";
            }
            else
            {
                System.out.println ("Congratulations! You belong to Hufflepuff!");
                studentHouse = "Hufflepuff";
            }
            Main.sleep (3000);
        }
    }

    /*
    AUTHORITY FUNCTIONS
    */

    public static int validateUserName (String userName)
    {
        if (userName.length () < 6)
        {
            return 0;
        }

        if (Student.allUserNames.isEmpty ())
        {
            return 1;
        }

        String[] allUserNamesArray = new String[Student.allUserNames.size ()];
        Student.allUserNames.toArray (allUserNamesArray);

        for (String s : allUserNamesArray)
        {
            if (userName.equalsIgnoreCase (s))
            {
                return 2;
            }
        }
        return 1;
    }

    public static int validateOwlmail (String owlmail)
    {
        String regex = "^[a-zA-Z0-9_+.&*-]+(?:\\.[a-zA-Z0-9_+.&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern pattern = Pattern.compile (regex);
        Matcher matcher = pattern.matcher (owlmail);

        if (! matcher.find ())
        {
            return 0;
        }

        if (Student.allOwlmails.isEmpty ())
        {
            return 1;
        }
        else
        {
            String[] allOwlmailsArray = new String[Student.allOwlmails.size ()];
            Student.allOwlmails.toArray (allOwlmailsArray);

            for (String s : allOwlmailsArray)
            {
                if (owlmail.equalsIgnoreCase (s))
                {
                    return 2;
                }
            }
        }
        return 1;
    }

    public static int validatePassword (String firstPassword, String secondPassword)
    {
        if (! firstPassword.equals (secondPassword))
        {
            return 2;
        }

        String regex = "^(.{0,7}|[^0-9]*|[^A-Z]*|[^a-z]*|[a-zA-Z0-9]*)$";

        Pattern pattern = Pattern.compile (regex);
        Matcher matcher = pattern.matcher (firstPassword);

        if (matcher.find ())
        {
            return 0;
        }
        return 1;
    }

    public static int findUserNumber (String owlmail)
    {
        int userNumber = 0;

        String[] allOwlmailsArray = new String[Student.allOwlmails.size ()];
        Student.allOwlmails.toArray (allOwlmailsArray);

        for (int i = 0; i < Student.allOwlmails.size (); i++)
        {
            if (owlmail.equals (allOwlmailsArray[i]))
            {
                userNumber = i;
            }
        }

        return userNumber;
    }

    public boolean checkPassword (String password, int userNumber)
    {
        String[] allPasswordsArray = new String[Student.allPasswords.size ()];
        Student.allPasswords.toArray (allPasswordsArray);

        password = hashPassword (password, salt);

        assert password != null;
        return password.equals (allPasswordsArray[userNumber]);
    }

    /*
    GET-INFO FUNCTIONS
    */

    public String getFullName ()
    {
        return fullName;
    }
}