import java.util.ArrayList;
import java.util.UUID;

public class Course
{
    protected String  title;
    protected UUID    courseID;
    protected Teacher teacher;

    protected ArrayList <Student> studentsList = new ArrayList <> ();

    protected static ArrayList <String> allTitles   = new ArrayList <> ();
    protected static ArrayList <Teacher> allTeachers = new ArrayList <> ();
    protected static ArrayList <Course> allCourses  = new ArrayList <> ();

    public Course (String title, Teacher teacher)
    {
        this.title   = title;
        allTitles.add (title);

        this.teacher = teacher;
        allTeachers.add (teacher);

        this.courseID = UUID.randomUUID ();

        Student defaultStudent = new Student ("Default", "Default@Default.Default", "Default_0");
        studentsList.add (defaultStudent);
    }

    public void addStudent (Student student)
    {
        studentsList.add (student);
    }

    public static Course findCourse (String courseName)
    {
        Course[] allCoursesArray = new Course[allCourses.size ()];
        allCourses.toArray (allCoursesArray);

        for (Course s : allCoursesArray)
        {
            if (courseName.equals (s.title))
            {
                return s;
            }
        }
        return null;
    }

    public static void viewAllCourses()
    {
        Course[] allCoursesArray = new Course[allCourses.size ()];
        allCourses.toArray (allCoursesArray);

        for (Course course : allCoursesArray)
        {
            System.out.println (course.title + " - Professor " + course.teacher.fullName);
        }
    }
}
