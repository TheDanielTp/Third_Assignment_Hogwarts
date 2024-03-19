public class ProfessorRequest
{
    protected String userName;
    protected String owlmail;
    protected String password;
    protected String fullName;

    public ProfessorRequest (String userName, String owlmail, String password, String fullName)
    {
        this.userName = userName;
        this.owlmail    = owlmail;
        this.password = password;
        this.fullName = fullName;
    }

    public void addRequest (ProfessorRequest professorRequest)
    {
        Assistant.allProfessorRequests.add (professorRequest);
    }

    public static ProfessorRequest findRequest (String fullName)
    {
        for (ProfessorRequest professorRequest : Assistant.allProfessorRequests)
        {
            if (professorRequest.fullName.equals (fullName))
            {
                return professorRequest;
            }
        }
        return null;
    }
}