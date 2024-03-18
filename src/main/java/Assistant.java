import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Assistant extends User
{
    Scanner scanner = new Scanner (System.in);
    protected static String skipLine = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";

    public static ArrayList <Assistant>      allAssistants = new ArrayList <> ();
    public static ArrayList <TeacherRequest> allRequests   = new ArrayList <> ();

    private final byte[] salt;

    protected Assistant (String username, String email, String password, String fullName)
    {
        this.userName = username;
        this.email = email;

        byte[] salt = generateSalt ();
        this.salt = salt;
        password  = hashPassword (password, salt);

        this.password = password;
        this.fullName = fullName;

        Assistant.allUserNames.add (userName);
        Assistant.allEmails.add (email);
        Assistant.allPasswords.add (password);
        Assistant.allFullNames.add (fullName);

        this.accountID = UUID.randomUUID ();
        Assistant.allAccountIDs.add (accountID);
    }

    public static Assistant findAssistant (String email)
    {
        for (Assistant assistant : allAssistants)
        {
            if (assistant.email.equals (email))
            {
                return assistant;
            }
        }
        return null;
    }


    /*
    AUTHORITY FUNCTIONS
    */

    public static int validateUserName (String userName)
    {
        if (userName.length () < 6)
        {
            return 0;
        }

        if (Assistant.allUserNames.isEmpty ())
        {
            return 1;
        }

        String[] allUserNamesArray = new String[Assistant.allUserNames.size ()];
        Assistant.allUserNames.toArray (allUserNamesArray);

        for (String s : allUserNamesArray)
        {
            if (userName.equals (s))
            {
                return 2;
            }
        }
        return 1;
    }

    public static int validateEmail (String email)
    {
        String regex = "^[a-zA-Z0-9_+.&*-]+(?:\\.[a-zA-Z0-9_+.&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern pattern = Pattern.compile (regex);
        Matcher matcher = pattern.matcher (email);

        if (! matcher.find ())
        {
            return 0;
        }

        if (Assistant.allEmails.isEmpty ())
        {
            return 1;
        }
        else
        {
            String[] allEmailsArray = new String[Assistant.allEmails.size ()];
            Assistant.allEmails.toArray (allEmailsArray);

            for (String s : allEmailsArray)
            {
                if (email.equals (s))
                {
                    return 2;
                }
            }
        }
        return 1;
    }

    public static int validatePassword (String firstPassword, String secondPassword)
    {
        if (! firstPassword.equals (secondPassword))
        {
            return 2;
        }

        String regex = "^(.{0,7}|[^0-9]*|[^A-Z]*|[^a-z]*|[a-zA-Z0-9]*)$";

        Pattern pattern = Pattern.compile (regex);
        Matcher matcher = pattern.matcher (firstPassword);

        if (matcher.find ())
        {
            return 0;
        }
        return 1;
    }

    public static int findUserNumber (String email)
    {
        int userNumber = 0;

        String[] allEmailsArray = new String[Assistant.allEmails.size ()];
        Assistant.allEmails.toArray (allEmailsArray);

        for (int i = 0; i < Assistant.allEmails.size (); i++)
        {
            if (email.equals (allEmailsArray[i]))
            {
                userNumber = i;
            }
        }

        return userNumber;
    }

    public boolean checkPassword (String password, int userNumber)
    {
        String[] allPasswordsArray = new String[Assistant.allPasswords.size ()];
        Assistant.allPasswords.toArray (allPasswordsArray);

        password = hashPassword (password, salt);

        assert password != null;
        return password.equals (allPasswordsArray[userNumber]);
    }
}
