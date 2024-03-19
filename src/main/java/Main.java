import java.util.*;

public class Main
{
    static Scanner scanner = new Scanner (System.in);

    static String skipLine   = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
    static String userAccess = null;

    static Student   student;
    static Teacher   teacher;
    static Assistant assistant;

    /*
    IMPLEMENTED FUNCTIONS
    */

    public static void main (String[] args)
    {
        generateUsers ();
        runMenu ();
    }

    public static void slowPrint (String text, int time)
    {
        char[] chars = text.toCharArray ();
        for (char aChar : chars)
        {
            System.out.print (aChar);
            sleep (time);
        }
    }

    public static void sleep (int time)
    {
        try
        {
            Thread.sleep (time);
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException (e);
        }
    }

    public static void runMenu ()
    {
        System.out.println (skipLine);

        System.out.println ("Hogwarts School Datacenter.");
        System.out.println ("Main Menu");
        System.out.println ("1. Sign Up");
        System.out.println ("2. Sign in");
        System.out.print ("Which option shall you choose: ");

        int menuInput = scanner.nextInt ();
        scanner.nextLine ();

        switch (menuInput)
        {
            case 1:
                signUpMenu ();
            case 2:
                signInMenu ();
            case 0:
                return;
            default:
            {
                System.out.print ("Incorrect wand movement! Make another selection.");
                sleep (2000);
                runMenu ();
            }
        }
        runMenu ();
    }

    /*
    SIGN-UP FUNCTIONS
    */

    public static void signUpMenu ()
    {
        System.out.println (skipLine);

        System.out.println ("Hogwarts School Datacenter.");
        System.out.println ("Sign Up Menu");
        System.out.println ("1. Sign Up as Student");
        System.out.println ("2. Sign Up as Teacher");
        System.out.println ("3. Sign Up as Assistant");
        System.out.print ("Which option shall you choose: ");

        int menuInput = scanner.nextInt ();
        scanner.nextLine ();

        switch (menuInput)
        {
            case 1:
                signUpStudent ();
                break;
            case 2:
                signUpTeacher ();
                break;
            case 3:
                signUpAssistant ();
                break;
            case 0:
                return;
            default:
                System.out.print ("Incorrect wand movement! Make another selection.");
                sleep (2000);
                signUpMenu ();
                break;
        }
        signUpMenu ();
    }

    public static void signUpStudent ()
    {
        System.out.println (skipLine);

        System.out.println ("Hogwarts School Datacenter.");
        System.out.println ("Student Sign Up");

        System.out.print ("Email: ");
        String email = scanner.nextLine ();
        if (email.equals ("esc"))
        {
            runMenu ();
        }

        while (Student.validateEmail (email) != 1)
        {
            if (Student.validateEmail (email) == 2)
            {
                System.out.println ("Unfortunately, this owl is already taken. Present a new owl, or sign in if you possess an existing account.");
                System.out.print ("Email: ");

                email = scanner.nextLine ();
                if (email.equals ("esc"))
                {
                    runMenu ();
                }
            }
            else if (Student.validateEmail (email) == 0)
            {
                System.out.println ("Unfortunately, this owl can't be accepted! Provide another owl to continue.");
                System.out.print ("Email: ");

                email = scanner.nextLine ();
                if (email.equals ("esc"))
                {
                    runMenu ();
                }
            }
        }

        System.out.print ("Username: ");
        String username = scanner.nextLine ();
        if (username.equals ("esc"))
        {
            runMenu ();
        }

        while (Student.validateUserName (username) != 1)
        {
            if (Student.validateUserName (username) == 2)
            {
                System.out.println ("This name is already enscribed in the tome. Conjure forth a new one to claim as your own.");
                System.out.print ("Username: ");

                username = scanner.nextLine ();
                if (username.equals ("esc"))
                {
                    runMenu ();
                }
            }
            else if (Student.validateUserName (username) == 0)
            {
                System.out.println ("Your chosen moniker must be imbued with at least six enchanted characters. Bestow upon it a more potent charm and try anew.");
                System.out.print ("Username: ");

                username = scanner.nextLine ();
                if (username.equals ("esc"))
                {
                    runMenu ();
                }
            }
        }

        System.out.print ("Password: ");
        String firstPassword = scanner.nextLine ();
        if (firstPassword.equals ("esc"))
        {
            runMenu ();
        }

        System.out.print ("Confirm Password: ");
        String secondPassword = scanner.nextLine ();
        if (secondPassword.equals ("esc"))
        {
            runMenu ();
        }

        while (Student.validatePassword (firstPassword, secondPassword) != 1)
        {
            if (Student.validatePassword (firstPassword, secondPassword) == 2)
            {
                System.out.println ("The incantations do not align! Speak your password once more to synchronize the enchantments.");

                System.out.print ("Password: ");
                firstPassword = scanner.nextLine ();
                if (firstPassword.equals ("esc"))
                {
                    runMenu ();
                }

                System.out.print ("Confirm Password: ");
                secondPassword = scanner.nextLine ();
                if (secondPassword.equals ("esc"))
                {
                    runMenu ();
                }
            }
            else if (Student.validatePassword (firstPassword, secondPassword) == 0)
            {
                System.out.println ("Magical defenses demand greater complexity! Forge a new password adorned with an uppercase incantation, a numerical talisman, and a special character charm.");

                System.out.print ("Password: ");
                firstPassword = scanner.nextLine ();
                if (firstPassword.equals ("esc"))
                {
                    runMenu ();
                }

                System.out.print ("Confirm Password: ");
                secondPassword = scanner.nextLine ();
                if (secondPassword.equals ("esc"))
                {
                    runMenu ();
                }
            }
        }

        System.out.print ("Full Name: ");
        String fullName = scanner.nextLine ();
        if (fullName.equals ("esc"))
        {
            runMenu ();
        }

        String password = firstPassword;
        student = new Student (username, email, password, fullName);
        Student.allStudents.add (student);

        System.out.println ("Success! You have successfully navigated the enchanted threshold. Await as the gates admit you to your realm.");
        sleep (1000);
        runMenu ();
    }

    public static void signUpTeacher ()
    {
        System.out.println (skipLine);

        System.out.println ("Hogwarts School Datacenter.");
        System.out.println ("Teacher Sign Up");

        System.out.print ("Email: ");
        String email = scanner.nextLine ();
        if (email.equals ("esc"))
        {
            runMenu ();
        }

        while (Teacher.validateEmail (email) != 1)
        {
            if (Teacher.validateEmail (email) == 2)
            {
                System.out.println ("Unfortunately, this owl is already taken. Present a new owl, or sign in if you possess an existing account.");
                System.out.print ("Email: ");

                email = scanner.nextLine ();
                if (email.equals ("esc"))
                {
                    runMenu ();
                }
            }
            else if (Teacher.validateEmail (email) == 0)
            {
                System.out.println ("Unfortunately, this owl can't be accepted! Provide another owl to continue.");
                System.out.print ("Email: ");

                email = scanner.nextLine ();
                if (email.equals ("esc"))
                {
                    runMenu ();
                }
            }
        }

        System.out.print ("Username: ");
        String username = scanner.nextLine ();
        if (username.equals ("esc"))
        {
            runMenu ();
        }

        while (Teacher.validateUserName (username) != 1)
        {
            if (Teacher.validateUserName (username) == 2)
            {
                System.out.println ("This name is already enscribed in the tome. Conjure forth a new one to claim as your own.");
                System.out.print ("Username: ");

                username = scanner.nextLine ();
                if (username.equals ("esc"))
                {
                    runMenu ();
                }
            }
            else if (Teacher.validateUserName (username) == 0)
            {
                System.out.println ("Your chosen moniker must be imbued with at least six enchanted characters. Bestow upon it a more potent charm and try anew.");
                System.out.print ("Username: ");

                username = scanner.nextLine ();
                if (username.equals ("esc"))
                {
                    runMenu ();
                }
            }
        }

        System.out.print ("Password: ");
        String firstPassword = scanner.nextLine ();
        if (firstPassword.equals ("esc"))
        {
            runMenu ();
        }

        System.out.print ("Confirm Password: ");
        String secondPassword = scanner.nextLine ();
        if (secondPassword.equals ("esc"))
        {
            runMenu ();
        }

        while (Teacher.validatePassword (firstPassword, secondPassword) != 1)
        {
            if (Teacher.validatePassword (firstPassword, secondPassword) == 2)
            {
                System.out.println ("The incantations do not align! Speak your password once more to synchronize the enchantments.");

                System.out.print ("Password: ");
                firstPassword = scanner.nextLine ();
                if (firstPassword.equals ("esc"))
                {
                    runMenu ();
                }

                System.out.print ("Confirm Password: ");
                secondPassword = scanner.nextLine ();
                if (secondPassword.equals ("esc"))
                {
                    runMenu ();
                }
            }
            else if (Teacher.validatePassword (firstPassword, secondPassword) == 0)
            {
                System.out.println ("Magical defenses demand greater complexity! Forge a new password adorned with an uppercase incantation, a numerical talisman, and a special character charm.");

                System.out.print ("Password: ");
                firstPassword = scanner.nextLine ();
                if (firstPassword.equals ("esc"))
                {
                    runMenu ();
                }

                System.out.print ("Confirm Password: ");
                secondPassword = scanner.nextLine ();
                if (secondPassword.equals ("esc"))
                {
                    runMenu ();
                }
            }
        }

        System.out.print ("Full Name: ");
        String fullName = scanner.nextLine ();
        if (fullName.equals ("esc"))
        {
            runMenu ();
        }

        String password = firstPassword;
        TeacherRequest teacherRequest = new TeacherRequest (username, email, password, fullName);
        teacherRequest.addRequest (teacherRequest);

        System.out.println ("Success! You're request has been made. Please wait as our Assistants review your request");
        sleep (1000);
        runMenu ();
    }

    public static void signUpAssistant ()
    {
        System.out.println (skipLine);

        System.out.println ("Hogwarts School Datacenter.");
        System.out.println ("Assistant Sign Up");

        System.out.print ("Email: ");
        String email = scanner.nextLine ();
        if (email.equals ("esc"))
        {
            runMenu ();
        }

        while (Assistant.validateEmail (email) != 1)
        {
            if (Assistant.validateEmail (email) == 2)
            {
                System.out.println ("Unfortunately, this owl is already taken. Present a new owl, or sign in if you possess an existing account.");
                System.out.print ("Email: ");

                email = scanner.nextLine ();
                if (email.equals ("esc"))
                {
                    runMenu ();
                }
            }
            else if (Assistant.validateEmail (email) == 0)
            {
                System.out.println ("Unfortunately, this owl can't be accepted! Provide another owl to continue.");
                System.out.print ("Email: ");

                email = scanner.nextLine ();
                if (email.equals ("esc"))
                {
                    runMenu ();
                }
            }
        }

        System.out.print ("Username: ");
        String username = scanner.nextLine ();
        if (username.equals ("esc"))
        {
            runMenu ();
        }

        while (Assistant.validateUserName (username) != 1)
        {
            if (Assistant.validateUserName (username) == 2)
            {
                System.out.println ("This name is already enscribed in the tome. Conjure forth a new one to claim as your own.");
                System.out.print ("Username: ");

                username = scanner.nextLine ();
                if (username.equals ("esc"))
                {
                    runMenu ();
                }
            }
            else if (Assistant.validateUserName (username) == 0)
            {
                System.out.println ("Your chosen moniker must be imbued with at least six enchanted characters. Bestow upon it a more potent charm and try anew.");
                System.out.print ("Username: ");

                username = scanner.nextLine ();
                if (username.equals ("esc"))
                {
                    runMenu ();
                }
            }
        }

        System.out.print ("Password: ");
        String firstPassword = scanner.nextLine ();
        if (firstPassword.equals ("esc"))
        {
            runMenu ();
        }

        System.out.print ("Confirm Password: ");
        String secondPassword = scanner.nextLine ();
        if (secondPassword.equals ("esc"))
        {
            runMenu ();
        }

        while (Assistant.validatePassword (firstPassword, secondPassword) != 1)
        {
            if (Assistant.validatePassword (firstPassword, secondPassword) == 2)
            {
                System.out.println ("The incantations do not align! Speak your password once more to synchronize the enchantments.");

                System.out.print ("Password: ");
                firstPassword = scanner.nextLine ();
                if (firstPassword.equals ("esc"))
                {
                    runMenu ();
                }

                System.out.print ("Confirm Password: ");
                secondPassword = scanner.nextLine ();
                if (secondPassword.equals ("esc"))
                {
                    runMenu ();
                }
            }
            else if (Assistant.validatePassword (firstPassword, secondPassword) == 0)
            {
                System.out.println ("Magical defenses demand greater complexity! Forge a new password adorned with an uppercase incantation, a numerical talisman, and a special character charm.");

                System.out.print ("Password: ");
                firstPassword = scanner.nextLine ();
                if (firstPassword.equals ("esc"))
                {
                    runMenu ();
                }

                System.out.print ("Confirm Password: ");
                secondPassword = scanner.nextLine ();
                if (secondPassword.equals ("esc"))
                {
                    runMenu ();
                }
            }
        }

        System.out.print ("Full Name: ");
        String fullName = scanner.nextLine ();
        if (fullName.equals ("esc"))
        {
            runMenu ();
        }

        String password = firstPassword;
        AssistantRequest assistantRequest = new AssistantRequest (username, email, password, fullName);
        assistantRequest.addRequest (assistantRequest);

        System.out.println ("Success! You're request has been made. Please wait as our Assistants review your request");
        sleep (1000);
        runMenu ();
    }

    /*
    SIGN-IN FUNCTIONS
    */

    public static void signInMenu ()
    {
        System.out.println (skipLine);

        System.out.println ("Hogwarts School Datacenter.");
        System.out.println ("Sign In Menu");
        System.out.println ("1. Sign In as Student");
        System.out.println ("2. Sign In as Teacher");
        System.out.println ("3. Sign In as Assistant");
        System.out.print ("Which option shall you choose: ");

        int menuInput = scanner.nextInt ();
        scanner.nextLine ();

        switch (menuInput)
        {
            case 1:
                signInStudent ();
                break;
            case 2:
                signInTeacher ();
                break;
            case 3:
                signInAssistant ();
                break;
            case 0:
                return;
            default:
                System.out.print ("Incorrect wand movement! Make another selection.");
                sleep (2000);
                signInMenu ();
                break;
        }
        signInMenu ();
    }

    public static void signInStudent ()
    {
        System.out.println (skipLine);

        System.out.println ("Hogwarts School Datacenter.");
        System.out.println ("Student Sign In");

        System.out.print ("Email: ");
        String email = scanner.nextLine ();
        if (email.equals ("esc"))
        {
            runMenu ();
        }

        while (Student.validateEmail (email) != 2)
        {
            if (Student.validateEmail (email) == 1)
            {
                System.out.println ("The owl's journey finds no roost at this address. Provide a new own or sign up if you've yet to claim an account.");
                System.out.print ("Email: ");

                email = scanner.nextLine ();
                if (email.equals ("esc"))
                {
                    runMenu ();
                }
            }
            else if (Student.validateEmail (email) == 0)
            {
                System.out.println ("Unfortunately, this owl can't be accepted! Provide another owl to continue.");
                System.out.print ("Email: ");

                email = scanner.nextLine ();
                if (email.equals ("esc"))
                {
                    runMenu ();
                }
            }
        }

        int userNumber = Student.findUserNumber (email);
        student = Student.findStudentByEmail (email);

        System.out.print ("Password: ");
        String password = scanner.nextLine ();
        if (password.equals ("esc"))
        {
            student = null;
            runMenu ();
        }

        int countOfIncorrectAttempts = 0;

        while (! student.checkPassword (password, userNumber))
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
            System.out.print ("Password: ");

            password = scanner.nextLine ();
            if (password.equals ("esc"))
            {
                student = null;
                runMenu ();
            }
        }

        System.out.println ("Success! You have successfully navigated the enchanted threshold. Await as the magical gates admit you to your realm.");
        sleep (1000);

        student    = Student.findStudentByEmail (email);
        teacher    = null;
        assistant  = null;
        userAccess = "Student";
        studentMenu ();
    }

    public static void signInTeacher ()
    {
        System.out.println (skipLine);

        System.out.println ("Hogwarts School Datacenter.");
        System.out.println ("Teacher Sign In");

        System.out.print ("Email: ");
        String email = scanner.nextLine ();
        if (email.equals ("esc"))
        {
            runMenu ();
        }

        while (Teacher.validateEmail (email) != 2)
        {
            if (Teacher.validateEmail (email) == 1)
            {
                System.out.println ("The owl's journey finds no roost at this address. Provide a new own or sign up if you've yet to claim an account.");
                System.out.print ("Email: ");

                email = scanner.nextLine ();
                if (email.equals ("esc"))
                {
                    runMenu ();
                }
            }
            else if (Teacher.validateEmail (email) == 0)
            {
                System.out.println ("Unfortunately, this owl can't be accepted! Provide another owl to continue.");
                System.out.print ("Email: ");

                email = scanner.nextLine ();
                if (email.equals ("esc"))
                {
                    runMenu ();
                }
            }
        }

        int userNumber = Teacher.findUserNumber (email);
        teacher = Teacher.findTeacherByEmail (email);

        System.out.print ("Password: ");
        String password = scanner.nextLine ();
        if (password.equals ("esc"))
        {
            teacher = null;
            runMenu ();
        }

        int countOfIncorrectAttempts = 0;

        while (! teacher.checkPassword (password, userNumber))
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
            System.out.print ("Password: ");

            password = scanner.nextLine ();
            if (password.equals ("esc"))
            {
                teacher = null;
                runMenu ();
            }
        }

        System.out.println ("Success! You have successfully navigated the enchanted threshold. Await as the magical gates admit you to your realm.");
        sleep (1000);

        teacher    = Teacher.findTeacherByEmail (email);
        student    = null;
        assistant  = null;
        userAccess = "Teacher";
        teacherMenu ();
    }

    public static void signInAssistant ()
    {
        System.out.println (skipLine);

        System.out.println ("Hogwarts School Datacenter.");
        System.out.println ("Assistant Sign In");

        System.out.print ("Email: ");
        String email = scanner.nextLine ();
        if (email.equals ("esc"))
        {
            runMenu ();
        }

        while (Assistant.validateEmail (email) != 2)
        {
            if (Assistant.validateEmail (email) == 1)
            {
                System.out.println ("The owl's journey finds no roost at this address. Provide a new own or sign up if you've yet to claim an account.");
                System.out.print ("Email: ");

                email = scanner.nextLine ();
                if (email.equals ("esc"))
                {
                    runMenu ();
                }
            }
            else if (Assistant.validateEmail (email) == 0)
            {
                System.out.println ("Unfortunately, this owl can't be accepted! Provide another owl to continue.");
                System.out.print ("Email: ");

                email = scanner.nextLine ();
                if (email.equals ("esc"))
                {
                    runMenu ();
                }
            }
        }

        int userNumber = Assistant.findUserNumber (email);
        assistant = Assistant.findAssistant (email);

        System.out.print ("Password: ");
        String password = scanner.nextLine ();
        if (password.equals ("esc"))
        {
            assistant = null;
            runMenu ();
        }

        int countOfIncorrectAttempts = 0;

        while (! assistant.checkPassword (password, userNumber))
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
            System.out.print ("Password: ");

            password = scanner.nextLine ();
            if (password.equals ("esc"))
            {
                assistant = null;
                runMenu ();
            }
        }

        System.out.println ("Success! You have successfully navigated the enchanted threshold. Await as the magical gates admit you to your realm.");
        sleep (1000);

        assistant  = Assistant.findAssistant (email);
        student    = null;
        teacher    = null;
        userAccess = "Assistant";
        assistantMenu ();
    }

    /*
    USER-MENU FUNCTIONS
    */

    public static void studentMenu ()
    {
        if (student == null)
        {
            runMenu ();
        }
        System.out.println (skipLine);

        System.out.println ("Hogwarts School Datacenter.");
        System.out.println ("Student Menu");
        System.out.println ("Welcome Mx. " + student.getFullName ());
        System.out.println ();
        System.out.println ("1. Student account setting");
        System.out.println ("2. Take course");
        System.out.println ("3. View all taken courses");
        System.out.println ("4. View all teachers");
        System.out.println ("5. Take sorting quiz");
        System.out.println ("6. Log Out");
        System.out.print ("Which option shall you choose: ");

        int menuInput = scanner.nextInt ();
        scanner.nextLine ();

        switch (menuInput)
        {
            case 1:
                student.accountSetting (student);
                break;
            case 2:
                student.takeCourse (student);
                break;
            case 3:
                student.viewAllCourses ();
                break;
            case 4:
                System.out.println (skipLine);
                student.viewAllTeachers ();
                break;
            case 5:
                student.takeSortingQuiz ();
                break;
            case 6:
                userAccess = null;
                student = null;
                runMenu ();
                break;
            default:
                System.out.print ("Incorrect wand movement! Make another selection.");
                sleep (2000);
                break;
        }
        studentMenu ();
    }

    public static void teacherMenu ()
    {
        if (teacher == null)
        {
            runMenu ();
        }
        System.out.println (skipLine);

        System.out.println ("Hogwarts School Datacenter.");
        System.out.println ("Teacher Menu");
        System.out.println ("Welcome Professor " + teacher.getFullName ());
        System.out.println ("Teacher score: " + teacher.getScore () + " / 100");
        System.out.println ();
        System.out.println ("1. Teacher account setting");
        System.out.println ("2. Take course");
        System.out.println ("3. Score students");
        System.out.println ("4. View Courses List");
        System.out.println ("5. View Course's List of Students");
        System.out.println ("6. Log Out");
        System.out.print ("Which option shall you choose: ");

        int menuInput = scanner.nextInt ();
        scanner.nextLine ();

        switch (menuInput)
        {
            case 1:
                teacher.accountSetting (teacher);
                break;
            case 2:
                teacher.takeCourse (teacher);
                break;
            case 3:
                teacher.selectCourse ();
                break;
            case 4:
                teacher.viewAllCourses ();
                System.out.println ("Press Enter to Continue ");
                scanner.nextLine ();
                break;
            case 5:
                teacher.selectCourseView ();
                break;
            case 6:
                userAccess = null;
                teacher = null;
                runMenu ();
                break;
            default:
                System.out.print ("Incorrect wand movement! Make another selection.");
                sleep (2000);
                break;
        }
        teacherMenu ();
    }

    public static void assistantMenu ()
    {
        if (assistant == null)
        {
            runMenu ();
        }
        System.out.println (skipLine);

        System.out.println ("Hogwarts School Datacenter.");
        System.out.println ("Assistant Menu");
        System.out.println ("Welcome Professor " + assistant.getFullName ());
        System.out.println ();
        System.out.println ("1. Assistant Account Setting");
        System.out.println ("2. Review Teacher Requests");
        System.out.println ("4. Create a Course");
        System.out.println ("5. View Courses List");
        System.out.println ("6. View Teachers List");
        System.out.println ("7. Remove a Teacher / Student");
        System.out.println ("8. Log Out");
        System.out.print ("Which option shall you choose: ");

        int menuInput = scanner.nextInt ();
        scanner.nextLine ();

        switch (menuInput)
        {
            case 1:
                assistant.accountSetting (assistant);
                break;
            case 2:
                Assistant.viewTeacherRequests ();
                break;
            case 3:
                Assistant.viewAssistantRequests ();
                break;
            case 4:
                Assistant.createCourse ();
                break;
            case 5:
                System.out.println (skipLine);
                System.out.println ("Courses List: ");
                Assistant.viewAllCourses ();
                break;
            case 6:
                System.out.println (skipLine);
                System.out.println ("Teachers List: ");
                Assistant.viewAllTeachers ();
                break;
            case 7:
                Assistant.removeUser ();
                break;
            case 8:
                userAccess = null;
                assistant = null;
                runMenu ();
                break;
            default:
                System.out.print ("Incorrect wand movement! Make another selection.");
                sleep (2000);
                break;
        }
        assistantMenu ();
    }

    /*
    START-UP FUNCTIONS
    */

    public static void generateUsers ()
    {
        Student danial = new Student ("TheDanielTp", "prof.danial4@hogwarts.edu", "D_patronus48", "Danial Taghipour");
        Student.addStudent (student);

        Assistant Dumbledore = new Assistant ("Dumbledore1881", "Albus.Dumbledore@hogwarts.edu", "Ph0enixR!se21", "Albus Dumbledore");
        Assistant.addAssistant (Dumbledore);

        generateTeachers ();
        generateCourse ();
    }

    public static void generateTeachers ()
    {
        Teacher mcGonogall = new Teacher (
                "TransfigurationMaestro", "Minerva.McGonagall@hogwarts.edu", "Gryffindor_L3ad3r", "Minerva McGonagall");
        Teacher.addTeacher (mcGonogall);

        Teacher flitwick = new Teacher (
                "CharmMasterFlitwick", "Filius.Flitwick@hogwarts.edu", "Wingardium_L3vi0sa", "Filius Flitwick");
        Teacher.addTeacher (flitwick);

        Teacher snape = new Teacher (
                "HalfBloodPrince", "Severus.Snape@hogwarts.edu", "Sectum_sempra78", "Severus Snape");
        Teacher.addTeacher (snape);

        Teacher binns = new Teacher (
                "HistoryGhost", "Cuthbert.Binns@hogwarts.edu", "Eterna1Hist0ry!", "Cuthbert Binns");
        Teacher.addTeacher (binns);

        Teacher quirrell = new Teacher (
                "TurbanedMaster", "Quirinus.Quirrell@hogwarts.edu", "DarkLord_Defeater69", "Quirinus Quirrell");
        Teacher.addTeacher (quirrell);
    }

    public static void generateCourse ()
    {
        Course transfiguration = new Course ("Transfiguration");
        Course.allCourses.add (transfiguration);

        Course charms = new Course ("Charms");
        Course.allCourses.add (charms);

        Course potions = new Course ("Potions");
        Course.allCourses.add (potions);

        Course historyOfMagic = new Course ("History of Magic");
        Course.allCourses.add (historyOfMagic);

        Course defenseAgainstDarkArts = new Course ("Defence Against the Dark Arts");
        Course.allCourses.add (defenseAgainstDarkArts);

        Course astronomyAndHerbology = new Course ("Astronomy and Herbology");
        Course.allCourses.add (astronomyAndHerbology);

        Course flyingLessons = new Course ("Flying Lessons");
        Course.allCourses.add (flyingLessons);
    }
}