import java.util.*;

public class Main
{
    static Scanner scanner = new Scanner (System.in);

    static String skipLine   = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
    static String userAccess = null;

    static Student   student;
    static Professor professor;
    static Assistant assistant;

    /*
    IMPLEMENTED FUNCTIONS
    */

    public static void main (String[] args)
    {
        generateUsers (); //generates pre-designed users
        runMenu (); //opens main menu
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
        System.out.println ();
        System.out.println ("1. Sign Up");
        System.out.println ("2. Sign In");
        System.out.print ("Enter your choice: ");

        int menuInput = scanner.nextInt ();
        scanner.nextLine ();

        switch (menuInput)
        {
            case 1:
                signUpMenu (); //opens up sign up menu
            case 2:
                signInMenu (); //opens up sign in menu
            case 0:
                System.exit (0); //exits the program
            default:
            {
                System.out.print ("Invalid input. Please try again.");
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
        System.out.println ("2. Sign Up as Professor");
        System.out.println ("3. Sign Up as Assistant");
        System.out.print ("Enter your choice: ");

        int menuInput = scanner.nextInt ();
        scanner.nextLine ();

        switch (menuInput)
        {
            case 1:
                signUpStudent ();
                break;
            case 2:
                signUpProfessor ();
                break;
            case 3:
                signUpAssistant ();
                break;
            case 0:
                return;
            default:
                System.out.print ("Invalid input. Please try again.");
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
        System.out.println ();
        System.out.print ("Owlmail: ");
        String owlmail = scanner.nextLine ();
        if (owlmail.equals ("esc"))
        {
            runMenu ();
        }

        while (Student.validateOwlmail (owlmail) != 1)
        {
            if (Student.validateOwlmail (owlmail) == 2)
            {
                System.out.println ("Unfortunately, this owl is already taken. Present a new owl, or sign in if you possess an existing account.");
                System.out.print ("Owlmail: ");

                owlmail = scanner.nextLine ();
                if (owlmail.equals ("esc"))
                {
                    runMenu ();
                }
            }
            else if (Student.validateOwlmail (owlmail) == 0)
            {
                System.out.println ("Unfortunately, this owl can't be accepted! Provide another owl to continue.");
                System.out.print ("Owlmail: ");

                owlmail = scanner.nextLine ();
                if (owlmail.equals ("esc"))
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
                System.out.println ("Magical defenses demand greater complexity! \n" +
                        "Forge a new password adorned with an uppercase incantation, a numerical talisman, and a special character charm.");

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
        student = new Student (username, owlmail, password, fullName);
        Student.allStudents.add (student);

        System.out.println ("Success! You have successfully navigated the enchanted threshold. Await as the gates admit you to your realm.");
        sleep (1000);
        runMenu ();
    }

    public static void signUpProfessor ()
    {
        System.out.println (skipLine);

        System.out.println ("Hogwarts School Datacenter.");
        System.out.println ("Professor Sign Up");
        System.out.println ();
        System.out.print ("Owlmail: ");
        String owlmail = scanner.nextLine ();
        if (owlmail.equals ("esc"))
        {
            runMenu ();
        }

        while (Professor.validateOwlmail (owlmail) != 1)
        {
            if (Professor.validateOwlmail (owlmail) == 2)
            {
                System.out.println ("Unfortunately, this owl is already taken. Present a new owl, or sign in if you possess an existing account.");
                System.out.print ("Owlmail: ");

                owlmail = scanner.nextLine ();
                if (owlmail.equals ("esc"))
                {
                    runMenu ();
                }
            }
            else if (Professor.validateOwlmail (owlmail) == 0)
            {
                System.out.println ("Unfortunately, this owl can't be accepted! Provide another owl to continue.");
                System.out.print ("Owlmail: ");

                owlmail = scanner.nextLine ();
                if (owlmail.equals ("esc"))
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

        while (Professor.validateUserName (username) != 1)
        {
            if (Professor.validateUserName (username) == 2)
            {
                System.out.println ("This name is already enscribed in the tome. Conjure forth a new one to claim as your own.");
                System.out.print ("Username: ");

                username = scanner.nextLine ();
                if (username.equals ("esc"))
                {
                    runMenu ();
                }
            }
            else if (Professor.validateUserName (username) == 0)
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

        while (Professor.validatePassword (firstPassword, secondPassword) != 1)
        {
            if (Professor.validatePassword (firstPassword, secondPassword) == 2)
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
            else if (Professor.validatePassword (firstPassword, secondPassword) == 0)
            {
                System.out.println ("Magical defenses demand greater complexity! \n" +
                        "Forge a new password adorned with an uppercase incantation, a numerical talisman, and a special character charm.");

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
        ProfessorRequest professorRequest = new ProfessorRequest (username, owlmail, password, fullName);
        professorRequest.addRequest (professorRequest);

        System.out.println ("Success! You're request has been made. Please wait as our Assistants review your request");
        sleep (1000);
        runMenu ();
    }

    public static void signUpAssistant ()
    {
        System.out.println (skipLine);

        System.out.println ("Hogwarts School Datacenter.");
        System.out.println ("Assistant Sign Up");
        System.out.println ();
        System.out.print ("Owlmail: ");
        String owlmail = scanner.nextLine ();
        if (owlmail.equals ("esc"))
        {
            runMenu ();
        }

        while (Assistant.validateOwlmail (owlmail) != 1)
        {
            if (Assistant.validateOwlmail (owlmail) == 2)
            {
                System.out.println ("Unfortunately, this owl is already taken. Present a new owl, or sign in if you possess an existing account.");
                System.out.print ("Owlmail: ");

                owlmail = scanner.nextLine ();
                if (owlmail.equals ("esc"))
                {
                    runMenu ();
                }
            }
            else if (Assistant.validateOwlmail (owlmail) == 0)
            {
                System.out.println ("Unfortunately, this owl can't be accepted! Provide another owl to continue.");
                System.out.print ("Owlmail: ");

                owlmail = scanner.nextLine ();
                if (owlmail.equals ("esc"))
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
                System.out.println ("Magical defenses demand greater complexity! \n" +
                        "Forge a new password adorned with an uppercase incantation, a numerical talisman, and a special character charm.");

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
        AssistantRequest assistantRequest = new AssistantRequest (username, owlmail, password, fullName);
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
        System.out.println ();
        System.out.println ("1. Sign In as Student");
        System.out.println ("2. Sign In as Professor");
        System.out.println ("3. Sign In as Assistant");
        System.out.print ("Enter your choice: ");

        int menuInput = scanner.nextInt ();
        scanner.nextLine ();

        switch (menuInput)
        {
            case 1:
                signInStudent ();
                break;
            case 2:
                signInProfessor ();
                break;
            case 3:
                signInAssistant ();
                break;
            case 0:
                return;
            default:
                System.out.print ("Invalid input. Please try again.");
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
        System.out.println ();
        System.out.print ("Owlmail: ");
        String owlmail = scanner.nextLine ();
        if (owlmail.equals ("esc"))
        {
            runMenu ();
        }

        while (Student.validateOwlmail (owlmail) != 2)
        {
            if (Student.validateOwlmail (owlmail) == 1)
            {
                System.out.println ("The owl's journey finds no roost at this address. Provide a new own or sign up if you've yet to claim an account.");
                System.out.print ("Owlmail: ");

                owlmail = scanner.nextLine ();
                if (owlmail.equals ("esc"))
                {
                    runMenu ();
                }
            }
            else if (Student.validateOwlmail (owlmail) == 0)
            {
                System.out.println ("Unfortunately, this owl can't be accepted! Provide another owl to continue.");
                System.out.print ("Owlmail: ");

                owlmail = scanner.nextLine ();
                if (owlmail.equals ("esc"))
                {
                    runMenu ();
                }
            }
        }

        int userNumber = Student.findUserNumber (owlmail);
        student = Student.findStudentByOwlmail (owlmail);

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

        student    = Student.findStudentByOwlmail (owlmail);
        professor  = null;
        assistant  = null;
        userAccess = "Student";
        studentMenu ();
    }

    public static void signInProfessor ()
    {
        System.out.println (skipLine);

        System.out.println ("Hogwarts School Datacenter.");
        System.out.println ("Professor Sign In");
        System.out.println ();
        System.out.print ("Owlmail: ");
        String owlmail = scanner.nextLine ();
        if (owlmail.equals ("esc"))
        {
            runMenu ();
        }

        while (Professor.validateOwlmail (owlmail) != 2)
        {
            if (Professor.validateOwlmail (owlmail) == 1)
            {
                System.out.println ("The owl's journey finds no roost at this address. Provide a new own or sign up if you've yet to claim an account.");
                System.out.print ("Owlmail: ");

                owlmail = scanner.nextLine ();
                if (owlmail.equals ("esc"))
                {
                    runMenu ();
                }
            }
            else if (Professor.validateOwlmail (owlmail) == 0)
            {
                System.out.println ("Unfortunately, this owl can't be accepted! Provide another owl to continue.");
                System.out.print ("Owlmail: ");

                owlmail = scanner.nextLine ();
                if (owlmail.equals ("esc"))
                {
                    runMenu ();
                }
            }
        }

        int userNumber = Professor.findUserNumber (owlmail);
        professor = Professor.findProfessorByOwlmail (owlmail);

        System.out.print ("Password: ");
        String password = scanner.nextLine ();
        if (password.equals ("esc"))
        {
            professor = null;
            runMenu ();
        }

        int countOfIncorrectAttempts = 0;

        while (! professor.checkPassword (password, userNumber))
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
                professor = null;
                runMenu ();
            }
        }

        System.out.println ("Success! You have successfully navigated the enchanted threshold. Await as the magical gates admit you to your realm.");
        sleep (1000);

        professor  = Professor.findProfessorByOwlmail (owlmail);
        student    = null;
        assistant  = null;
        userAccess = "Professor";
        professorMenu ();
    }

    public static void signInAssistant ()
    {
        System.out.println (skipLine);

        System.out.println ("Hogwarts School Datacenter.");
        System.out.println ("Assistant Sign In");
        System.out.println ();
        System.out.print ("Owlmail: ");
        String owlmail = scanner.nextLine ();
        if (owlmail.equals ("esc"))
        {
            runMenu ();
        }

        while (Assistant.validateOwlmail (owlmail) != 2)
        {
            if (Assistant.validateOwlmail (owlmail) == 1)
            {
                System.out.println ("The owl's journey finds no roost at this address. Provide a new own or sign up if you've yet to claim an account.");
                System.out.print ("Owlmail: ");

                owlmail = scanner.nextLine ();
                if (owlmail.equals ("esc"))
                {
                    runMenu ();
                }
            }
            else if (Assistant.validateOwlmail (owlmail) == 0)
            {
                System.out.println ("Unfortunately, this owl can't be accepted! Provide another owl to continue.");
                System.out.print ("Owlmail: ");

                owlmail = scanner.nextLine ();
                if (owlmail.equals ("esc"))
                {
                    runMenu ();
                }
            }
        }

        int userNumber = Assistant.findUserNumber (owlmail);
        assistant = Assistant.findAssistant (owlmail);

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

        assistant  = Assistant.findAssistant (owlmail);
        student    = null;
        professor  = null;
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
        System.out.println ("4. View all professors");
        System.out.println ("5. Rate Teachers");
        System.out.println ("6. Take sorting quiz");
        System.out.println ("7. Log Out");
        System.out.print ("Enter your choice: ");

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
                student.viewAllProfessors ();
                break;
            case 5:
                Hogwarts.rateTeacher (student);
                break;
            case 6:
                student.takeSortingQuiz ();
                break;
            case 7:
                userAccess = null;
                student = null;
                runMenu ();
                break;
            default:
                System.out.print ("Invalid input. Please try again.");
                sleep (2000);
                break;
        }
        studentMenu ();
    }

    public static void professorMenu ()
    {
        if (professor == null)
        {
            runMenu ();
        }
        System.out.println (skipLine);

        System.out.println ("Hogwarts School Datacenter.");
        System.out.println ("Professor Menu");
        System.out.println ("Welcome Professor " + professor.getFullName ());
        System.out.println ("Professor score: " + professor.getScore () + " / 100");
        System.out.println ();
        System.out.println ("1. Professor account setting");
        System.out.println ("2. Take course");
        System.out.println ("3. Score students");
        System.out.println ("4. View Courses List");
        System.out.println ("5. View Course's List of Students");
        System.out.println ("6. View Comments");
        System.out.println ("7. Log Out");
        System.out.print ("Enter your choice: ");

        int menuInput = scanner.nextInt ();
        scanner.nextLine ();

        switch (menuInput)
        {
            case 1:
                professor.accountSetting (professor);
                break;
            case 2:
                professor.takeCourse (professor);
                break;
            case 3:
                professor.selectCourse ();
                break;
            case 4:
                professor.viewAllCourses ();
                System.out.println ("Press Enter to Continue ");
                scanner.nextLine ();
                break;
            case 5:
                professor.selectCourseView ();
                break;
            case 6:
                professor.viewComments ();
            case 7:
                userAccess = null;
                professor = null;
                runMenu ();
                break;
            default:
                System.out.print ("Invalid input. Please try again.");
                sleep (2000);
                break;
        }
        professorMenu ();
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
        System.out.println ("2. Review Professor Requests");
        System.out.println ("3. Review Assistant Requests");
        System.out.println ("4. Create a Course");
        System.out.println ("5. View Courses List");
        System.out.println ("6. View Professors List");
        System.out.println ("7. Remove a Professor / Student");
        System.out.println ("8. Log Out");
        System.out.print ("Enter your choice: ");

        int menuInput = scanner.nextInt ();
        scanner.nextLine ();

        switch (menuInput)
        {
            case 1:
                assistant.accountSetting (assistant);
                break;
            case 2:
                Assistant.viewProfessorRequests ();
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
                System.out.println ("Press Enter to Continue ");
                scanner.nextLine ();
                break;
            case 6:
                System.out.println (skipLine);
                System.out.println ("Professors List: ");
                Assistant.viewAllProfessors ();
                System.out.println ("Press Enter to Continue ");
                scanner.nextLine ();
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
                System.out.print ("Invalid input. Please try again.");
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
        generateAssistants ();
        generateStudents ();
        generateProfessors ();
        generateCourse ();
    }

    public static void generateAssistants ()
    {
        Assistant albus = new Assistant (
                "Dumbledore1881", "albus.dumbledore@hogwarts.edu", "Ph0enixR!se21", "Albus Dumbledore");
        Assistant.addAssistant (albus);

        Assistant salazar = new Assistant (
                "SerpentSovereign", "salazar.slytherin@hogwarts.edu", "Pureblood_Serpent1042", "Salazar Slytherin");
        Assistant.addAssistant (salazar);

        Assistant godric = new Assistant (
                "LionHeartGodric", "godric.gryffindor@hogwarts.edu", "Courageous_Lion1167", "Godric Gryffindor");
        Assistant.addAssistant (godric);

        Assistant helga = new Assistant (
                "KindnessKeeper", "helga.hufflepuff@hogwarts.edu", "Hufflepuff_Hospitality980", "Helga Hufflepuff");
        Assistant.addAssistant (helga);

        Assistant rowena = new Assistant (
                "RavenMind", "rowena.ravenclaw@hogwarts.edu", "Wise_Crow1020", "Rowena Ravenclaw");
    }

    public static void generateStudents ()
    {
        Student danial = new Student (
                "TheDanielTp", "prof.danial4@hogwarts.edu", "D_patronus48", "Danial Taghipour");
        Student.addStudent (danial);

        Student rana = new Student (
                "RanaRokni", "doctor.rana@hogwarts.edu", "Doctor_Rokni99", "Rana Rokni");
        Student.addStudent (rana);

        Student mahan = new Student (
                "BladeOfMiquella", "mahan.madani@hogwarts.edu", "EldenL0rd!", "Mahan Madani");
        Student.addStudent (mahan);
    }

    public static void generateProfessors ()
    {
        Professor mcGonogall = new Professor (
                "TransfigurationMaestro", "Minerva.McGonagall@hogwarts.edu", "Gryffindor_L3ad3r", "Minerva McGonagall");
        Professor.addProfessor (mcGonogall);

        Professor flitwick = new Professor (
                "CharmMasterFlitwick", "Filius.Flitwick@hogwarts.edu", "Wingardium_L3vi0sa", "Filius Flitwick");
        Professor.addProfessor (flitwick);

        Professor snape = new Professor (
                "HalfBloodPrince", "Severus.Snape@hogwarts.edu", "Sectum_sempra78", "Severus Snape");
        Professor.addProfessor (snape);

        Professor binns = new Professor (
                "HistoryGhost", "Cuthbert.Binns@hogwarts.edu", "Eterna1Hist0ry!", "Cuthbert Binns");
        Professor.addProfessor (binns);

        Professor quirrell = new Professor (
                "TurbanedMaster", "Quirinus.Quirrell@hogwarts.edu", "DarkLord_Defeater69", "Quirinus Quirrell");
        Professor.addProfessor (quirrell);
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