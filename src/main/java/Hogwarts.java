import java.util.ArrayList;
import java.util.Scanner;

public class Hogwarts
{
    static Scanner scanner = new Scanner (System.in);

    protected static String skipLine = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";

    protected static ArrayList <Professor> allProfessors  = new ArrayList <> ();
    protected static ArrayList <Student>   allStudents    = new ArrayList <> ();
    protected static ArrayList <Course>    allCourses     = new ArrayList <> ();
    protected static ArrayList <String>    courseRequests = new ArrayList <> ();

    public static void rateProfessor (Student student)
    {
        System.out.println (skipLine);

        System.out.println ("Hogwarts School Datacenter.");
        System.out.println ("Rating Menu");
        System.out.println ();
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

        System.out.print ("Enter your score: ");

        int score = scanner.nextInt ();
        scanner.nextLine ();

        while (score < 0 || score > 100)
        {
            System.out.println ("Invalid score. Please enter an amount between 0 and 100.");
            System.out.print ("Enter your score: ");

            score = scanner.nextInt ();
            scanner.nextLine ();
        }

        if (score > professor.getScore ())
        {
            professor.score = professor.score + score / 30;
        }
        else if (score < professor.getScore ())
        {
            professor.score = professor.score - score / 30;
        }

        System.out.println ("Professor rated successfully. Do you have any comments?");
        System.out.println ("1. Yes");
        System.out.println ("2. No");

        int input = scanner.nextInt ();
        scanner.nextLine ();

        if (input == 2)
        {
            return;
        }

        System.out.print ("Enter your comment: ");
        String comment = scanner.nextLine ();
        professor.commentsList.add (comment);

        System.out.println ("Comment added successfully. Returning to menu");
        Main.sleep (1000);
    }

    public static void viewStudentInfo ()
    {
        setAttributes ();
        System.out.println ("Hogwarts School Datacenter.");
        System.out.println ("Students Information");
        System.out.println ();
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
        assert student != null;

        System.out.println ("Full Name: " + student.fullName);
        System.out.println ("Username: " + student.userName);
        System.out.println ("Courses: ");
        student.viewAllCourses ();
        System.out.println ("Professors: ");
        student.viewAllProfessors ();
        System.out.println ("Owlmail: " + student.owlmail);

        System.out.println ("Press Enter to Continue.");
        scanner.nextLine ();
    }

    public static void requestCourse ()
    {
        System.out.println ("Hogwarts School Datacenter.");
        System.out.println ("Students Information");
        System.out.println ();
        System.out.println ("Course name: ");

        String title = scanner.nextLine ();
        courseRequests.add (title);
        System.out.println ("Request added successfully. Returning to menu.");
        Main.sleep (1000);
    }

    /*
    GLOBAL FUNCTIONS
    */

    public static void viewHogwartsData ()
    {
        setAttributes ();
        System.out.println ("Professors List:");
        viewAllProfessors ();
        System.out.println ("Students List:");
        viewAllStudents ();
        System.out.println ("Courses List: ");
        viewAllCourses ();
    }

    public static void setAttributes ()
    {
        allProfessors = Professor.allProfessors;
        allStudents   = Student.allStudents;
        allCourses    = Course.allCourses;
    }

    public static void viewAllProfessors ()
    {
        for (Professor professor : allProfessors)
        {
            System.out.println (professor.fullName);
        }
    }

    public static void viewAllStudents ()
    {
        for (Student student : allStudents)
        {
            System.out.println (student.fullName);
        }
    }

    public static void viewAllCourses ()
    {
        for (Course course : allCourses)
        {
            System.out.println (course.title);
        }
    }
}
