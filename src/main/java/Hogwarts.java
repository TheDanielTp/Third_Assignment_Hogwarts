import java.util.ArrayList;

public class Hogwarts
{
    protected static ArrayList <Professor> allProfessors = new ArrayList <> ();
    protected static ArrayList <Student> allStudents = new ArrayList <> ();
    protected static ArrayList <Course>  allCourses  = new ArrayList <> ();

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
        allStudents = Student.allStudents;
        allCourses  = Course.allCourses;
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
