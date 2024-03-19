public class AssistantRequest
{
    protected String userName;
    protected String email;
    protected String password;
    protected String fullName;

    public AssistantRequest (String userName, String email, String password, String fullName)
    {
        this.userName = userName;
        this.email    = email;
        this.password = password;
        this.fullName = fullName;
    }

    public void addRequest (AssistantRequest assistantRequest)
    {
        Assistant.allAssistantRequests.add (assistantRequest);
    }

    public static AssistantRequest findRequest (String fullName)
    {
        for (AssistantRequest assistantRequest : Assistant.allAssistantRequests)
        {
            if (assistantRequest.fullName.equals (fullName))
            {
                return assistantRequest;
            }
        }
        return null;
    }
}