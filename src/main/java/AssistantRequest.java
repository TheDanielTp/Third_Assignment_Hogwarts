public class AssistantRequest
{
    protected String userName;
    protected String owlmail;
    protected String password;
    protected String fullName;

    public AssistantRequest (String userName, String owlmail, String password, String fullName)
    {
        this.userName = userName;
        this.owlmail    = owlmail;
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