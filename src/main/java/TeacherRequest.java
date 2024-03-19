public class TeacherRequest
{
    protected String userName;
    protected String email;
    protected String password;
    protected String fullName;

    public TeacherRequest (String userName, String email, String password, String fullName)
    {
        this.userName = userName;
        this.email    = email;
        this.password = password;
        this.fullName = fullName;
    }

    public void addRequest (TeacherRequest teacherRequest)
    {
        Assistant.allTeacherRequests.add (teacherRequest);
    }

    public static TeacherRequest findRequest (String fullName)
    {
        for (TeacherRequest teacherRequest : Assistant.allTeacherRequests)
        {
            if (teacherRequest.fullName.equals (fullName))
            {
                return teacherRequest;
            }
        }
        return null;
    }
}