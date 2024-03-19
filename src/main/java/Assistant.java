import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Assistant extends Account
{
    static Scanner scanner = new Scanner (System.in);

    protected static String skipLine = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";

    protected static ArrayList <Assistant>        allAssistants        = new ArrayList <> ();
    protected static ArrayList <AssistantRequest> allAssistantRequests = new ArrayList <> ();
    protected static ArrayList <TeacherRequest>   allTeacherRequests   = new ArrayList <> ();

    private final byte[] salt;

    /*
    CONSTRUCTOR FUNCTIONS
    */

    protected Assistant (String username, String email, String password, String fullName)
    {
        this.userName = username;
        this.email    = email;

        byte[] salt = generateSalt ();
        this.salt = salt;
        password  = hashPassword (password, salt);

        this.password = password;
        this.fullName = fullName;

        Assistant.allUserNames.add (userName);
        Assistant.allEmails.add (email);
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

    public static Assistant findAssistant (String email)
    {
        for (Assistant assistant : allAssistants)
        {
            if (assistant.email.equals (email))
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
        System.out.println ("3. Change Email");
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
                assistant.changeEmail (assistant);
                break;
            default:
                return;
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

    public void changeEmail (Assistant assistant)
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

        System.out.print ("New email: ");
        String email = scanner.nextLine ();
        if (email.equals ("esc"))
        {
            return;
        }

        while (Student.validateEmail (email) != 1)
        {
            if (Student.validateEmail (email) == 2)
            {
                System.out.println ("Unfortunately, this owl is already taken. Present a new owl, or sign in if you possess an existing account.");
                System.out.print ("New email: ");

                email = scanner.nextLine ();
                if (email.equals ("esc"))
                {
                    return;
                }
            }
            else if (Student.validateEmail (email) == 0)
            {
                System.out.println ("Unfortunately, this owl can't be accepted! Provide another owl to continue.");
                System.out.print ("New email: ");

                email = scanner.nextLine ();
                if (email.equals ("esc"))
                {
                    return;
                }
            }
        }

        for (int i = 0; i < allPasswords.size (); i++)
        {
            if (password.equals (allPasswords.get (i)))
            {
                allEmails.set (i, email);
            }
        }
        assistant.email = email;

        System.out.println ("Email changed successfully");
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
        Assistant assistant = new Assistant (assistantRequest.userName, assistantRequest.email, assistantRequest.password, assistantRequest.fullName);
        Assistant.allAssistants.add (assistant);

        System.out.println ("Assistant added successfully");
        Main.sleep (1000);
    }

    public static void viewTeacherRequests ()
    {
        System.out.println (skipLine);

        System.out.println ("Teacher Requests List: ");
        for (TeacherRequest teacherRequest : allTeacherRequests)
        {
            System.out.println (teacherRequest.fullName);
        }
        System.out.print ("Select Request: ");

        String requestName = scanner.nextLine ();
        if (requestName.equals ("esc"))
        {
            return;
        }

        while (TeacherRequest.findRequest (requestName) == null)
        {
            System.out.println ("Request not found. Please try again.");
            System.out.print ("Select Request: ");

            requestName = scanner.nextLine ();
            if (requestName.equals ("esc"))
            {
                return;
            }
        }

        TeacherRequest teacherRequest = TeacherRequest.findRequest (requestName);

        assert teacherRequest != null;
        Teacher teacher = new Teacher (teacherRequest.userName, teacherRequest.email, teacherRequest.password, teacherRequest.fullName);
        Teacher.allTeachers.add (teacher);

        System.out.println ("Teacher added successfully");
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

        System.out.println ("Course created successfully. Do you wish to assign a teacher to the course?");
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

        System.out.println ("Teachers List: ");
        Assistant.viewAllTeachers ();
        System.out.print ("Enter the name of the teacher: ");

        String fullName = scanner.nextLine ();
        if (fullName.equals ("esc"))
        {
            return;
        }

        while (Teacher.findTeacherByName (fullName) == null)
        {
            System.out.println ("Teacher not found. Please try again.");
            System.out.print ("Enter the name of the teacher: ");

            fullName = scanner.nextLine ();
            if (fullName.equals ("esc"))
            {
                return;
            }
        }

        Teacher teacher = Teacher.findTeacherByName (fullName);
        Course course = new Course (title, teacher);
        Course.allCourses.add (course);

        assert teacher != null;
        Course.forceTeacher (course, teacher);

        System.out.println ("Teacher assigned successfully. Returning to menu.");
        Main.sleep (1000);
    }

    public static void viewAllCourses ()
    {
        for (Course course : Course.allCourses)
        {
            System.out.println (course.title + " - Professor " + course.teacher.fullName);
        }
    }

    public static void viewAllTeachers ()
    {
        for (Teacher teacher : Teacher.allTeachers)
        {
            System.out.println (teacher.fullName);
        }
    }

    public static void viewAllStudents ()
    {
        for (Student student : Student.allStudents)
        {
            System.out.println (student.fullName);
        }
    }

    public static void removeUser ()
    {
        System.out.println (skipLine);

        System.out.println ("Hogwarts School Datacenter.");
        System.out.println ("Remove User Menu");
        System.out.println ();
        System.out.println ("1. Student");
        System.out.println ("2. Teacher");
        System.out.print ("Enter your choice: ");

        int input = scanner.nextInt ();
        scanner.nextLine ();

        switch (input)
        {
            case 1:
                removeStudent ();
            case 2:
                removeTeacher ();
            case 0:
                return;
            default:
                System.out.println ("Invalid input. Please try again.");
                removeUser ();
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
            System.out.println ("Student not found. Please try again.");
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
            course.studentsList.remove (student);
        }

        System.out.println ("Student removed successfully. Returning to menu.");
        Main.sleep (1000);
    }

    public static void removeTeacher ()
    {
        System.out.println (skipLine);

        System.out.println ("Teachers List: ");
        viewAllTeachers ();
        System.out.print ("Enter the name of the teacher: ");

        String fullName = scanner.nextLine ();
        if (fullName.equals ("esc"))
        {
            return;
        }

        while (Teacher.findTeacherByName (fullName) == null)
        {
            System.out.println ("Teacher not found. Please try again.");
            System.out.print ("Enter the name of the teacher: ");

            fullName = scanner.nextLine ();
            if (fullName.equals ("esc"))
            {
                return;
            }
        }

        Teacher teacher = Teacher.findTeacherByName (fullName);
        Teacher.allTeachers.remove (teacher);

        for (Course course : Course.allCourses)
        {
            if (course.teacher.equals (teacher))
            {
                course.teacher = null;
            }
        }

        System.out.println ("Teacher removed successfully. Returning to menu.");
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
            if (userName.equals (s))
            {
                return 2;
            }
        }
        return 1;
    }

    public static int validateEmail (String email)
    {
        String regex = "^[a-zA-Z0-9_+.&*-]+(?:\\.[a-zA-Z0-9_+.&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern pattern = Pattern.compile (regex);
        Matcher matcher = pattern.matcher (email);

        if (! matcher.find ())
        {
            return 0;
        }

        if (Assistant.allEmails.isEmpty ())
        {
            return 1;
        }
        else
        {
            String[] allEmailsArray = new String[Assistant.allEmails.size ()];
            Assistant.allEmails.toArray (allEmailsArray);

            for (String s : allEmailsArray)
            {
                if (email.equals (s))
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

    public static int findUserNumber (String email)
    {
        int userNumber = 0;

        String[] allEmailsArray = new String[Assistant.allEmails.size ()];
        Assistant.allEmails.toArray (allEmailsArray);

        for (int i = 0; i < Assistant.allEmails.size (); i++)
        {
            if (email.equals (allEmailsArray[i]))
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
