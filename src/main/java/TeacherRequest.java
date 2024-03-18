public class TeacherRequest
{
    protected String userName;
    protected String email;
    protected String password;
    protected String fullName;

    public TeacherRequest (String userName, String email, String password, String fullName)
    {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
    }

    public void addRequest (TeacherRequest teacherRequest)
    {
        Assistant.allRequests.add (teacherRequest);
    }
}
