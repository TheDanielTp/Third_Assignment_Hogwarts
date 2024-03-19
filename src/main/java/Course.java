import java.util.ArrayList;
import java.util.UUID;

public class Course
{
    protected String  title;
    protected UUID    courseID;
    protected Professor professor;

    protected ArrayList <Student>      studentsList       = new ArrayList <> ();
    protected ArrayList <StudentScore> studentsScoresList = new ArrayList <> ();

    protected static ArrayList <String>  allTitles   = new ArrayList <> ();
    protected static ArrayList <Professor> allProfessors = new ArrayList <> ();
    protected static ArrayList <Course>  allCourses  = new ArrayList <> ();

    /*
    CONSTRUCTOR FUNCTIONS
    */

    protected Course (String title)
    {
        this.title = title;
        allTitles.add (title);

        this.professor = null;

        this.courseID = UUID.randomUUID ();
    }

    public Course (String title, Professor professor)
    {
        this.title = title;
        allTitles.add (title);

        this.professor = professor;
        allProfessors.add (professor);

        this.courseID = UUID.randomUUID ();
    }

    public void assignProfessor (Professor professor)
    {
        this.professor = professor;
        allProfessors.add (professor);
    }

    public static void forceProfessor (Course course, Professor professor)
    {
        professor.coursesList.add (course);
        allProfessors.add (professor);
    }

    /*
    COURSE FUNCTIONS
    */

    public void addStudent (Student student)
    {
        studentsList.add (student);
        StudentScore studentScore = new StudentScore ();
        studentScore.fullName = student.fullName;
        studentScore.score    = 0;
        studentsScoresList.add (studentScore);
    }

    public void setScore (String fullName, double score)
    {
        for (StudentScore studentScore : studentsScoresList)
        {
            if (studentScore.fullName.equals (fullName))
            {
                studentScore.score = score;
            }
        }
    }

    public double findScore (String fullName)
    {
        for (StudentScore studentScore : studentsScoresList)
        {
            if (studentScore.fullName.equals (fullName))
            {
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
            if (course.professor == null)
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
            if (course.professor != null)
            {
                System.out.println (course.title + " - Professor " + course.professor.getFullName ());
            }
        }
    }
}
