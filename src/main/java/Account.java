import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account implements AccountManagement
{
    Scanner scanner = new Scanner (System.in);

    static String skipLine   = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";

    protected String  userName;
    protected String email;
    protected String password;
    protected String fullName;
    protected UUID   accountID;

    protected static ArrayList <String> allUserNames  = new ArrayList <> ();
    protected static ArrayList <String> allEmails     = new ArrayList <> ();
    protected static ArrayList <String> allPasswords  = new ArrayList <> ();
    protected static ArrayList <UUID>   allAccountIDs = new ArrayList <> ();
    protected static ArrayList <String>  allFullNames = new ArrayList <> ();

    public int SignIn (String userName, String password)
    {
        String[] allUsers = new String[allUserNames.size ()];
        allUserNames.toArray (allUsers);
        String[] allWords = new String[allPasswords.size ()];
        allPasswords.toArray (allWords);

        for (int i = 0; i < allUsers.length; i++)
        {
            if (userName.equals (allUsers[i]))
            {
                if (password.equals (allWords[i]))
                {
                    return 1; //username was found and password is correct. operation succeeded
                }
                else
                {
                    return 2; //username was found but password is incorrect. operation failed
                }
            }
        }
        return 0; //username was not found. operation failed
    }

    public static byte[] generateSalt ()
    {
        byte[] salt = new byte[16];
        SecureRandom random = new SecureRandom ();
        random.nextBytes (salt);
        return salt;
    }

    public static String hashPassword (String password, byte[] salt)
    {
        try
        {
            MessageDigest messageDigest = MessageDigest.getInstance ("SHA-256");
            messageDigest.reset ();
            messageDigest.update (salt);
            byte[] hashedBytes = messageDigest.digest (password.getBytes ());

            StringBuilder stringBuilder = new StringBuilder ();
            for (byte b : hashedBytes)
            {
                stringBuilder.append (String.format ("%02x", b));
            }
            return stringBuilder.toString ();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace ();
            return null;
        }
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

    public void changeUsername (User user)
    {
        return;
    }

    public void changePassword (User user)
    {
        return;
    }
}
