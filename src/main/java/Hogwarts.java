import java.util.ArrayList;

public class Hogwarts
{
    protected static ArrayList <Teacher> allTeachers = new ArrayList <> ();
    protected static ArrayList <Student> allStudents = new ArrayList <> ();
    protected static ArrayList <Course>  allCourses  = new ArrayList <> ();

    public static void viewHogwartsData ()
    {
        setAttributes ();
        System.out.println ("Teachers List:");
        viewAllTeachers ();
        System.out.println ("Students List:");
        viewAllStudents ();
        System.out.println ("Courses List: ");
        viewAllCourses ();
    }

    public static void setAttributes ()
    {
        allTeachers = Teacher.allTeachers;
        allStudents = Student.allStudents;
        allCourses  = Course.allCourses;
    }

    public static void viewAllTeachers ()
    {
        for (Teacher teacher : allTeachers)
        {
            System.out.println (teacher.fullName);
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
