import java.util.ArrayList;
import java.util.UUID;

public class Course
{
    protected String  title;
    protected UUID    courseID;
    protected Teacher teacher;

    protected ArrayList <Student> studentsList   = new ArrayList <> ();
    protected ArrayList <StudentScore> studentsScoresList = new ArrayList <> ();

    protected static ArrayList <String>  allTitles   = new ArrayList <> ();
    protected static ArrayList <Teacher> allTeachers = new ArrayList <> ();
    protected static ArrayList <Course>  allCourses  = new ArrayList <> ();

    /*
    CONSTRUCTOR FUNCTIONS
    */

    protected Course (String title)
    {
        this.title = title;
        allTitles.add (title);

        this.teacher = null;

        this.courseID = UUID.randomUUID ();
    }

    public Course (String title, Teacher teacher)
    {
        this.title = title;
        allTitles.add (title);

        this.teacher = teacher;
        allTeachers.add (teacher);

        this.courseID = UUID.randomUUID ();
    }

    public void assignTeacher (Teacher teacher)
    {
        this.teacher = teacher;
        allTeachers.add (teacher);
    }

    public static void forceTeacher (Course course, Teacher teacher)
    {
        teacher.coursesList.add (course);
        allTeachers.add (teacher);
    }

    /*
    COURSE FUNCTIONS
    */

    public void addStudent (Student student)
    {
        studentsList.add (student);
        StudentScore studentScore = new StudentScore ();
        studentScore.fullName = student.fullName;
        studentScore.score = 0;
        studentsScoresList.add (studentScore);
    }

    public void setScore (String fullName, double score)
    {
        for (StudentScore studentScore : studentsScoresList)
        {
            if (studentScore.fullName.equals (fullName)){
                studentScore.score = score;
            }
        }
    }

    public double findScore (String fullName)
    {
        for (StudentScore studentScore : studentsScoresList)
        {
            if (studentScore.fullName.equals (fullName)){
                return studentScore.score;
            }
        }
        return 0;
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

    public void viewAllStudents ()
    {
        Student[] allStudentsArray = new Student[studentsList.size ()];
        studentsList.toArray (allStudentsArray);

        for (Student student : allStudentsArray)
        {
            System.out.println (student.fullName);
        }
    }

    public static void viewFreeCourses ()
    {
        Course[] allCoursesArray = new Course[allCourses.size ()];
        allCourses.toArray (allCoursesArray);

        for (Course course : allCoursesArray)
        {
            if (course.teacher == null)
            {
                System.out.println (course.title);
            }
        }
    }

    public static void viewAvailableCourses ()
    {
        Course[] allCoursesArray = new Course[allCourses.size ()];
        allCourses.toArray (allCoursesArray);

        for (Course course : allCoursesArray)
        {
            if (course.teacher != null)
            {
                System.out.println (course.title + " - Professor " + course.teacher.getFullName ());
            }
        }
    }
}
