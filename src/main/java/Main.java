import javax.swing.plaf.PanelUI;
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
        student = new Student ("TheDanielTp", "prof.danial4@gmail.com", "Tdtp3148_P", "Danial Taghipour");
        Student.addStudent (student);
        userAccess = "Student";
        Teacher Snape = new Teacher ("Snape", "prof.snape@gmail.com", "Snape_0U0", "Severus Snape");
        Teacher.addTeacher (Snape);
        Course course = new Course ("Defense against Dark Arts", Snape);
        Course course2 = new Course ("Potions");
        Course.allCourses.add (course);
        Course.allCourses.add (course2);
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
        System.out.println ("1. Sign up");
        System.out.println ("2. Sign in");
        System.out.print ("Which option shall you choose: ");

        int menuInput = scanner.nextInt ();
        scanner.nextLine ();

        if (menuInput == 1)
        {
            signUpMenu ();
        }
        else if (menuInput == 2)
        {
            signInMenu ();
        }
        else
        {
            System.out.print ("Incorrect wand movement! Make another selection.");
            sleep (2000);
            runMenu ();
        }
    }

    /*
    SIGN-UP FUNCTIONS
    */

    public static void signUpMenu ()
    {
        System.out.println (skipLine);

        System.out.println ("Hogwarts School Datacenter.");
        System.out.println ("Sign up Menu");
        System.out.println ("1. Sign up as Student");
        System.out.println ("2. Sign up as Teacher");
        System.out.println ("3. Sign up as Assistant");
        System.out.print ("Which option shall you choose: ");

        int menuInput = scanner.nextInt ();
        scanner.nextLine ();

        if (menuInput == 1)
        {
            signUpStudent ();
        }
        else if (menuInput == 2)
        {
            signUpTeacher ();
        }
        else if (menuInput == 3)
        {
            System.out.println ("Assistant");
        }
        else
        {
            System.out.print ("Incorrect wand movement! Make another selection.");
            sleep (2000);
            signUpMenu ();
        }
    }

    public static void signUpStudent ()
    {
        System.out.println (skipLine);

        System.out.println ("Hogwarts School Datacenter.");
        System.out.println ("Student Sign up");

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
        System.out.println ("Teacher Sign up");

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

    /*
    SIGN-IN FUNCTIONS
    */

    public static void signInMenu ()
    {
        System.out.println (skipLine);

        System.out.println ("Hogwarts School Datacenter.");
        System.out.println ("Sign in Menu");
        System.out.println ("1. Sign in as Student");
        System.out.println ("2. Sign in as Teacher");
        System.out.println ("3. Sign in as Assistant");
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

                break;
            default:
                System.out.print ("Incorrect wand movement! Make another selection.");
                sleep (2000);
                signInMenu ();
                break;
        }
    }

    public static void signInStudent ()
    {
        System.out.println (skipLine);

        System.out.println ("Hogwarts School Datacenter.");
        System.out.println ("Student Sign in");

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
        System.out.println ("Teacher Sign in");

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
        teacher = Teacher.findTeacher (email);

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

        teacher    = Teacher.findTeacher (email);
        student    = null;
        assistant  = null;
        userAccess = "Teacher";
        teacherMenu ();
    }

    public static void signInAssistant ()
    {
        System.out.println (skipLine);

        System.out.println ("Hogwarts School Datacenter.");
        System.out.println ("Assistant Sign in");

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

        assistant    = Assistant.findAssistant (email);
        student    = null;
        teacher  = null;
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
        System.out.println ("6. Log out");
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
        System.out.println ("6. Log out");
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
                teacher.selectCourse();
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

    }
}