import java.util.UUID;

public class Teacher extends User
{
    protected String fullName;

    public Teacher(String fullName)
    {
        this.fullName = fullName;
    }

    public String getFullName()
    {
        return fullName;
    }
}