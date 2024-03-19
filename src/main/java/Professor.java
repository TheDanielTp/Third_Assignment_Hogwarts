import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Professor extends Account
{
    static Scanner scanner = new Scanner (System.in);

    protected static String skipLine = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";

    protected static ArrayList <String>    allUserNames  = new ArrayList <> ();
    protected static ArrayList <String>    allOwlmails   = new ArrayList <> ();
    protected static ArrayList <String>    allPasswords  = new ArrayList <> ();
    protected static ArrayList <UUID>      allAccountIDs = new ArrayList <> ();
    protected static ArrayList <String>    allFullNames  = new ArrayList <> ();
    protected static ArrayList <Professor> allProfessors = new ArrayList <> ();

    protected     int    score;
    private final byte[] salt;

    protected ArrayList <Course> coursesList  = new ArrayList <> ();
    protected ArrayList <String> commentsList = new ArrayList <> ();

    /*
    CONSTRUCTOR FUNCTIONS
    */

    protected Professor (String userName, String owlmail, String password, String fullName)
    {
        this.userName = userName;
        this.owlmail  = owlmail;

        byte[] salt = generateSalt ();
        this.salt = salt;
        password  = hashPassword (password, salt);

        this.password = password;
        this.fullName = fullName;

        score = 50;

        Professor.allUserNames.add (userName);
        Professor.allOwlmails.add (owlmail);
        Professor.allPasswords.add (password);
        Professor.allFullNames.add (fullName);

        this.accountID = UUID.randomUUID ();
        Professor.allAccountIDs.add (accountID);
    }

    public static void addProfessor (Professor professor)
    {
        allProfessors.add (professor);
    }

    /*
    ACCOUNT FUNCTIONS
    */

    public void accountSetting (Professor professor)
    {
        System.out.println (skipLine); //clears the terminal screen

        System.out.println ("Hogwarts School Datacenter.");
        System.out.println ("Professor account setting");

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
                professor.changeUsername (professor);
                break;
            case 2:
                professor.changePassword (professor);
                break;
            case 3:
                professor.changeOwlmail (professor);
                break;
            default:
                return;
        }
        accountSetting (professor);
    }

    public void changeUsername (Professor professor)
    {
        System.out.println (skipLine); //clears the terminal screen

        System.out.println ("Hogwarts School Datacenter.");
        System.out.println ("Professor change username");

        System.out.print ("Current password: ");
        String password = scanner.nextLine ();
        if (password.equals ("esc"))
        {
            return;
        }
        password = hashPassword (password, professor.salt);

        int countOfIncorrectAttempts = 0;

        while (! password.equals (professor.password))
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
            password = hashPassword (password, professor.salt);
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
        professor.userName = userName;

        System.out.println ("Username changed successfully");
        Main.sleep (1000);
    }

    public void changePassword (Professor professor)
    {
        System.out.println (skipLine); //clears the terminal screen

        System.out.println ("Hogwarts School Datacenter.");
        System.out.println ("Student change password");

        System.out.print ("Current password: ");
        String password = scanner.nextLine ();
        if (password.equals ("esc"))
        {
            return;
        }
        password = hashPassword (password, professor.salt);

        int countOfIncorrectAttempts = 0;

        while (! password.equals (professor.password))
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
            password = hashPassword (password, professor.salt);
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
                allPasswords.set (i, hashPassword (firstPassword, professor.salt));
            }
        }
        professor.password = hashPassword (firstPassword, professor.salt);

        System.out.println ("Password changed successfully");
        Main.sleep (1000);
    }

    public void changeOwlmail (Professor professor)
    {
        System.out.println (skipLine); //clears the terminal screen

        System.out.println ("Hogwarts School Datacenter.");
        System.out.println ("Student change password");

        System.out.print ("Current password: ");
        String password = scanner.nextLine ();
        if (password.equals ("esc"))
        {
            return;
        }
        password = hashPassword (password, professor.salt);

        int countOfIncorrectAttempts = 0;

        while (! password.equals (professor.password))
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
            password = hashPassword (password, professor.salt);
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
        professor.owlmail = owlmail;

        System.out.println ("Owlmail changed successfully");
        Main.sleep (1000);
    }

    public static Professor findProfessorByOwlmail (String owlmail)
    {
        for (Professor professor : allProfessors)
        {
            if (professor.owlmail.equalsIgnoreCase (owlmail))
            {
                return professor;
            }
        }
        return null;
    }

    public static Professor findProfessorByName (String fullName)
    {
        for (Professor professor : allProfessors)
        {
            if (professor.fullName.equalsIgnoreCase (fullName))
            {
                return professor;
            }
        }
        return null;
    }

    /*
    PROFESSOR FUNCTIONS
    */

    public void takeCourse (Professor professor)
    {
        System.out.println (skipLine); //clears the terminal screen

        System.out.println ("Hogwarts School Datacenter.");
        System.out.println ("Take Course Menu");

        System.out.println ();
        Course.viewFreeCourses ();

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
        course.assignProfessor (professor);
        professor.coursesList.add (course);

        System.out.println ("Course enrolled successfully. May your teachings be as fruitful as a field of Mandrakes in springtime.");
        Main.sleep (1000);
    }

    public void viewAllCourses ()
    {
        System.out.println (skipLine); //clears the terminal screen
        System.out.println ("Courses List:");
        for (Course course : coursesList)
        {
            System.out.println (course.title);
        }
    }

    public void viewComments ()
    {
        System.out.println ("Comments List: ");
        for (String string : commentsList)
        {
            System.out.println (string);
        }
        System.out.println ("Press Enter to Continue");
        scanner.nextLine ();
    }

    public void selectCourse ()
    {
        System.out.println (skipLine); //clears the terminal screen

        viewAllCourses ();
        System.out.print ("Select Course: ");
        String courseName = scanner.nextLine ();
        if (courseName.equals ("esc"))
        {
            return;
        }

        while (Course.findCourse (courseName) == null)
        {
            System.out.println ("The scrolls of Hogwarts hold no record of such a course. \n" +
                    "Tarry a moment, wizard, and ensure the incantation of your chosen subject is spoken true.");
            System.out.print ("Select Course: ");

            courseName = scanner.nextLine ();
            if (courseName.equals ("esc"))
            {
                return;
            }
        }
        Course course = Course.findCourse (courseName);
        assert course != null;
        scoreStudents (course);
    }

    public void selectCourseView ()
    {
        System.out.println (skipLine); //clears the terminal screen

        viewAllCourses ();
        System.out.print ("Select Course: ");
        String courseName = scanner.nextLine ();
        if (courseName.equals ("esc"))
        {
            return;
        }

        while (Course.findCourse (courseName) == null)
        {
            System.out.println ("The scrolls of Hogwarts hold no record of such a course. \n" +
                    "Tarry a moment, wizard, and ensure the incantation of your chosen subject is spoken true.");
            System.out.print ("Select Course: ");

            courseName = scanner.nextLine ();
            if (courseName.equals ("esc"))
            {
                return;
            }
        }
        Course course = Course.findCourse (courseName);
        assert course != null;

        System.out.println (skipLine); //clears the terminal screen
        course.viewAllStudents ();

        System.out.println ("Press Enter to Continue ");
        scanner.nextLine ();
    }

    public void scoreStudents (Course course)
    {
        System.out.println (skipLine); //clears the terminal screen
        course.viewAllStudents ();
        System.out.print ("Select Student: ");

        String fullName = scanner.nextLine ();
        if (fullName.equals ("esc"))
        {
            return;
        }

        while (Student.findStudentByName (fullName) == null)
        {
            System.out.println ("The scrolls of Hogwarts hold no record of such a student. \n" +
                    "Tarry a moment, wizard, and ensure the incantation of your chosen subject is spoken true.");
            System.out.print ("Select Course: ");

            fullName = scanner.nextLine ();
            if (fullName.equals ("esc"))
            {
                return;
            }
        }
        Student student = Student.findStudentByName (fullName);

        System.out.println (skipLine); //clears the terminal screen
        if (course.findScore (fullName) != 0)
        {
            assert student != null;
            System.out.print (student.fullName + "'s Score is " + course.findScore (fullName) + ". Enter the new score: ");
        }
        else
        {
            assert student != null;
            System.out.print ("Enter " + student.fullName + "'s Score: ");
        }

        double score = scanner.nextDouble ();
        course.setScore (fullName, score);

        System.out.println (skipLine); //clears the terminal screen
        System.out.println ("1) Score another student");
        System.out.println ("2) Change course");
        System.out.println ("3) Return to menu");
        System.out.print ("Enter your choice: ");

        int input = scanner.nextInt ();
        scanner.nextLine ();
        switch (input)
        {
            case 1:
                scoreStudents (course);
            case 2:
                selectCourse ();
            case 3:
                Main.professorMenu ();
            default:
                scoreStudents (course);
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

        if (Professor.allUserNames.isEmpty ())
        {
            return 1;
        }

        String[] allUserNamesArray = new String[Professor.allUserNames.size ()];
        Professor.allUserNames.toArray (allUserNamesArray);

        for (String string : allUserNamesArray)
        {
            if (userName.equalsIgnoreCase (string))
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

        if (Professor.allOwlmails.isEmpty ())
        {
            return 1;
        }
        else
        {
            String[] allOwlmailsArray = new String[Professor.allOwlmails.size ()];
            Professor.allOwlmails.toArray (allOwlmailsArray);

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

        String[] allOwlmailsArray = new String[Professor.allOwlmails.size ()];
        Professor.allOwlmails.toArray (allOwlmailsArray);

        for (int i = 0; i < Professor.allOwlmails.size (); i++)
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
        String[] allPasswordsArray = new String[Professor.allPasswords.size ()];
        Professor.allPasswords.toArray (allPasswordsArray);

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

    public int getScore ()
    {
        return score;
    }
}