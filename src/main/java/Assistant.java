import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Assistant extends Account
{
    static Scanner scanner = new Scanner (System.in);

    protected static String skipLine = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";

    protected static ArrayList <String> allUserNames  = new ArrayList <> ();
    protected static ArrayList <String> allOwlmails   = new ArrayList <> ();
    protected static ArrayList <String> allPasswords  = new ArrayList <> ();
    protected static ArrayList <UUID>   allAccountIDs = new ArrayList <> ();
    protected static ArrayList <String> allFullNames  = new ArrayList <> ();

    protected static ArrayList <Assistant>        allAssistants        = new ArrayList <> ();
    protected static ArrayList <AssistantRequest> allAssistantRequests = new ArrayList <> ();
    protected static ArrayList <ProfessorRequest> allProfessorRequests = new ArrayList <> ();

    private final byte[] salt;

    /*
    CONSTRUCTOR FUNCTIONS
    */

    protected Assistant (String username, String owlmail, String password, String fullName)
    {
        this.userName = username;
        this.owlmail  = owlmail;

        byte[] salt = generateSalt ();
        this.salt = salt;
        password  = hashPassword (password, salt);

        this.password = password;
        this.fullName = fullName;

        Assistant.allUserNames.add (userName);
        Assistant.allOwlmails.add (owlmail);
        Assistant.allPasswords.add (password);
        Assistant.allFullNames.add (fullName);

        this.accountID = UUID.randomUUID ();
        Assistant.allAccountIDs.add (accountID);
    }

    public static void addAssistant (Assistant assistant)
    {
        allAssistants.add (assistant);
    }

    /*
    ACCOUNT FUNCTIONS
    */

    public static Assistant findAssistant (String owlmail)
    {
        for (Assistant assistant : allAssistants)
        {
            if (assistant.owlmail.equalsIgnoreCase (owlmail))
            {
                return assistant;
            }
        }
        return null;
    }

    public void accountSetting (Assistant assistant)
    {
        System.out.println (skipLine);

        System.out.println ("Hogwarts School Datacenter.");
        System.out.println ("Assistant account setting");

        System.out.println ("1. Change Username");
        System.out.println ("2. Change Password");
        System.out.println ("3. Change Owlmail");
        System.out.print ("Which option shall you choose: ");

        int menuInput = scanner.nextInt ();
        scanner.nextLine ();
        if (menuInput == 0)
        {
            return;
        }

        switch (menuInput)
        {
            case 1:
                assistant.changeUsername (assistant);
                break;
            case 2:
                assistant.changePassword (assistant);
                break;
            case 3:
                assistant.changeOwlmail (assistant);
                break;
            case 0:
                return;
            default:
                System.out.print ("Invalid input. Please try again.");
                Main.sleep (2000);
                accountSetting (assistant);
        }
    }

    public void changeUsername (Assistant assistant)
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
        password = hashPassword (password, assistant.salt);

        int countOfIncorrectAttempts = 0;

        while (! password.equals (assistant.password))
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
            password = hashPassword (password, assistant.salt);
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
        assistant.userName = userName;

        System.out.println ("Username changed successfully");
        Main.sleep (1000);
    }

    public void changePassword (Assistant assistant)
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
        password = hashPassword (password, assistant.salt);

        int countOfIncorrectAttempts = 0;

        while (! password.equals (assistant.password))
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
            password = hashPassword (password, assistant.salt);
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

        while (Assistant.validatePassword (firstPassword, secondPassword) != 1)
        {
            if (Assistant.validatePassword (firstPassword, secondPassword) == 2)
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
            else if (Assistant.validatePassword (firstPassword, secondPassword) == 0)
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
                allPasswords.set (i, hashPassword (firstPassword, assistant.salt));
            }
        }
        assistant.password = hashPassword (firstPassword, assistant.salt);

        System.out.println ("Password changed successfully");
        Main.sleep (1000);
    }

    public void changeOwlmail (Assistant assistant)
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
        password = hashPassword (password, assistant.salt);

        int countOfIncorrectAttempts = 0;

        while (! password.equals (assistant.password))
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
            password = hashPassword (password, assistant.salt);
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
        assistant.owlmail = owlmail;

        System.out.println ("Owlmail changed successfully");
        Main.sleep (1000);
    }

    /*
    ASSISTANT FUNCTIONS
    */

    public static void viewAssistantRequests ()
    {
        System.out.println (skipLine);

        System.out.println ("Assistant Requests List: ");
        for (AssistantRequest assistantRequest : allAssistantRequests)
        {
            System.out.println (assistantRequest.fullName);
        }
        System.out.print ("Select Request: ");

        String requestName = scanner.nextLine ();
        if (requestName.equals ("esc"))
        {
            return;
        }

        while (AssistantRequest.findRequest (requestName) == null)
        {
            System.out.println ("Request not found. Please try again.");
            System.out.print ("Select Request: ");

            requestName = scanner.nextLine ();
            if (requestName.equals ("esc"))
            {
                return;
            }
        }

        AssistantRequest assistantRequest = AssistantRequest.findRequest (requestName);

        assert assistantRequest != null;
        Assistant assistant = new Assistant (assistantRequest.userName, assistantRequest.owlmail, assistantRequest.password, assistantRequest.fullName);
        Assistant.allAssistants.add (assistant);

        System.out.println ("Assistant added successfully");
        Main.sleep (1000);
    }

    public static void viewProfessorRequests ()
    {
        System.out.println (skipLine);

        System.out.println ("Professor Requests List: ");
        for (ProfessorRequest professorRequest : allProfessorRequests)
        {
            System.out.println (professorRequest.fullName);
        }
        System.out.print ("Select Request: ");

        String requestName = scanner.nextLine ();
        if (requestName.equals ("esc"))
        {
            return;
        }

        while (ProfessorRequest.findRequest (requestName) == null)
        {
            System.out.println ("Request not found. Please try again.");
            System.out.print ("Select Request: ");

            requestName = scanner.nextLine ();
            if (requestName.equals ("esc"))
            {
                return;
            }
        }

        ProfessorRequest professorRequest = ProfessorRequest.findRequest (requestName);

        assert professorRequest != null;
        Professor professor = new Professor (professorRequest.userName, professorRequest.owlmail, professorRequest.password, professorRequest.fullName);
        Professor.allProfessors.add (professor);

        System.out.println ("Professor added successfully");
        Main.sleep (1000);
    }

    public static void createCourse ()
    {
        System.out.println ("Hogwarts School Datacenter.");
        System.out.println ("Course Creation");
        System.out.println ();
        System.out.print ("Enter the title of the course: ");

        String title = scanner.nextLine ();
        if (title.equals ("esc"))
        {
            return;
        }

        for (Course course : Course.allCourses)
        {
            while (course.title.equals (title))
            {
                System.out.println ("This course already exists. Modify it from the modification menu or create a new course.");
                System.out.print ("Enter the title of the course: ");

                title = scanner.nextLine ();
                if (title.equals ("esc"))
                {
                    return;
                }
            }
        }

        System.out.println ("Course created successfully. Do you wish to assign a professor to the course?");
        System.out.println ("1. Yes");
        System.out.println ("2. No");
        System.out.print ("Enter your choice: ");

        int input = scanner.nextInt ();
        scanner.nextLine ();

        if (input == 2)
        {
            Course course = new Course (title);
            Course.allCourses.add (course);
            return;
        }

        System.out.println ("Professors List: ");
        Assistant.viewAllProfessors ();
        System.out.print ("Enter the name of the professor: ");

        String fullName = scanner.nextLine ();
        if (fullName.equals ("esc"))
        {
            return;
        }

        while (Professor.findProfessorByName (fullName) == null)
        {
            System.out.println ("Professor not found. Please try again.");
            System.out.print ("Enter the name of the professor: ");

            fullName = scanner.nextLine ();
            if (fullName.equals ("esc"))
            {
                return;
            }
        }

        Professor professor = Professor.findProfessorByName (fullName);
        Course course = new Course (title, professor);
        Course.allCourses.add (course);

        assert professor != null;
        Course.forceProfessor (course, professor);

        System.out.println ("Professor assigned successfully. Returning to menu.");
        Main.sleep (1000);
    }

    public static void viewAllCourses ()
    {
        for (Course course : Course.allCourses)
        {
            if (course.professor != null)
            {
                System.out.println (course.title + " - Professor " + course.professor.fullName);
            }
            else
            {
                System.out.println (course.title + " - No assigned professor");
            }
        }
    }

    public static void viewAllProfessors ()
    {
        if (Professor.allProfessors.isEmpty ())
        {
            return;
        }
        for (Professor professor : Professor.allProfessors)
        {
            if (professor != null)
            {
                System.out.println (professor.fullName);
            }
        }
    }

    public static void viewAllStudents ()
    {
        if (Student.allStudents.isEmpty ())
        {
            return;
        }
        for (Student student : Student.allStudents)
        {
            if (student != null)
            {
                System.out.println (student.fullName);
            }
        }
    }

    public static void removeUser ()
    {
        System.out.println (skipLine);

        System.out.println ("Hogwarts School Datacenter.");
        System.out.println ("Remove User Menu");
        System.out.println ();
        System.out.println ("1. Student");
        System.out.println ("2. Professor");
        System.out.print ("Which option shall you choose: ");

        int input = scanner.nextInt ();
        scanner.nextLine ();

        switch (input)
        {
            case 1:
                removeStudent ();
                break;
            case 2:
                removeProfessor ();
                break;
            case 0:
                return;
            default:
                System.out.println ("Invalid input. Please try again.");
                removeUser ();
                break;
        }
    }

    public static void removeStudent ()
    {
        System.out.println (skipLine);

        System.out.println ("Students List: ");
        viewAllStudents ();
        System.out.print ("Enter the name of the student: ");

        String fullName = scanner.nextLine ();
        if (fullName.equals ("esc"))
        {
            return;
        }

        while (Student.findStudentByName (fullName) == null)
        {
            System.out.println ("There's no record of such student in archives. Please try again.");
            System.out.print ("Enter the name of the student: ");

            fullName = scanner.nextLine ();
            if (fullName.equals ("esc"))
            {
                return;
            }
        }

        Student student = Student.findStudentByName (fullName);
        Student.allStudents.remove (student);

        for (Course course : Course.allCourses)
        {
            if (course.studentsList != null)
            {
                course.studentsList.remove (student);
            }
        }

        System.out.println ("Student removed successfully. Returning to menu.");
        Main.sleep (1000);
    }

    public static void removeProfessor ()
    {
        System.out.println (skipLine);

        System.out.println ("Professors List: ");
        viewAllProfessors ();
        System.out.print ("Enter the name of the professor: ");

        String fullName = scanner.nextLine ();
        if (fullName.equals ("esc"))
        {
            return;
        }

        while (Professor.findProfessorByName (fullName) == null)
        {
            System.out.println ("There's no record of such professor in archives. Please try again.");
            System.out.print ("Enter the name of the professor: ");

            fullName = scanner.nextLine ();
            if (fullName.equals ("esc"))
            {
                return;
            }
        }

        Professor professor = Professor.findProfessorByName (fullName);
        Professor.allProfessors.remove (professor);

        for (Course course : Course.allCourses)
        {
            if (course.professor != null)
            {
                if (course.professor.equals (professor))
                {
                    course.professor = null;
                }
            }
        }

        System.out.println ("Professor removed successfully. Returning to menu.");
        Main.sleep (1000);
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

        if (Assistant.allUserNames.isEmpty ())
        {
            return 1;
        }

        String[] allUserNamesArray = new String[Assistant.allUserNames.size ()];
        Assistant.allUserNames.toArray (allUserNamesArray);

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

        if (Assistant.allOwlmails.isEmpty ())
        {
            return 1;
        }
        else
        {
            String[] allOwlmailsArray = new String[Assistant.allOwlmails.size ()];
            Assistant.allOwlmails.toArray (allOwlmailsArray);

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

        String[] allOwlmailsArray = new String[Assistant.allOwlmails.size ()];
        Assistant.allOwlmails.toArray (allOwlmailsArray);

        for (int i = 0; i < Assistant.allOwlmails.size (); i++)
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
        String[] allPasswordsArray = new String[Assistant.allPasswords.size ()];
        Assistant.allPasswords.toArray (allPasswordsArray);

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
