import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User implements AccountManagement
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
        System.out.println (skipLine);

        System.out.println ("Hogwarts School Datacenter.");
        System.out.println ("Teacher change username");

        System.out.print ("Current password: ");
        String password = scanner.nextLine ();
        if (password.equals ("esc"))
        {
            return;
        }
        password = hashPassword (password, user.salt);

        int countOfIncorrectAttempts = 0;

        while (! password.equals (teacher.password))
        {
            countOfIncorrectAttempts++;
            if (countOfIncorrectAttempts < 5)
            {
                System.out.println ("False incantation! Attempt once more, or invoke 'Forgot Password' to unravel the enchantment.");
            }
            else
            {
                System.out.println ("By Merlin's beard, you have forgotten the password. Someone must've used Obliviate on you. Go on, try again.");
            }
            System.out.print ("Current Password: ");

            password = scanner.nextLine ();
            if (password.equals ("esc"))
            {
                return;
            }
            password = hashPassword (password, teacher.salt);
        }

        System.out.print ("New username: ");
        String userName = scanner.nextLine ();
        if (userName.equals ("esc"))
        {
            return;
        }

        while (validateUserName (userName) != 1)
        {
            if (validateUserName (userName) == 2)
            {
                System.out.println ("This name is already enscribed in the tome. Conjure forth a new one to claim as your own.");
                System.out.print ("New Username: ");

                userName = scanner.nextLine ();
                if (userName.equals ("esc"))
                {
                    return;
                }
            }
            else if (validateUserName (userName) == 0)
            {
                System.out.println ("Your chosen moniker must be imbued with at least six enchanted characters. Bestow upon it a more potent charm and try anew.");
                System.out.print ("New username: ");

                userName = scanner.nextLine ();
                if (userName.equals ("esc"))
                {
                    return;
                }
            }
        }

        for (int i = 0; i < allPasswords.size (); i++)
        {
            if (password.equals (allPasswords.get (i)))
            {
                allUserNames.set (i, userName);
            }
        }
        teacher.userName = userName;

        System.out.println ("Username changed successfully");
        Main.sleep (1000);
    }

    public void changePassword (Teacher teacher)
    {
        System.out.println (skipLine);

        System.out.println ("Hogwarts School Datacenter.");
        System.out.println ("Student change password");

        System.out.print ("Current password: ");
        String password = scanner.nextLine ();
        if (password.equals ("esc"))
        {
            return;
        }
        password = hashPassword (password, teacher.salt);

        int countOfIncorrectAttempts = 0;

        while (! password.equals (teacher.password))
        {
            countOfIncorrectAttempts++;
            if (countOfIncorrectAttempts < 5)
            {
                System.out.println ("False incantation! Attempt once more, or invoke 'Forgot Password' to unravel the enchantment.");
            }
            else
            {
                System.out.println ("By Merlin's beard, you have forgotten the password. Someone must've used Obliviate on you. Go on, try again.");
            }
            System.out.print ("Current Password: ");

            password = scanner.nextLine ();
            if (password.equals ("esc"))
            {
                return;
            }
            password = hashPassword (password, teacher.salt);
        }

        System.out.print ("New Password: ");
        String firstPassword = scanner.nextLine ();
        if (firstPassword.equals ("esc"))
        {
            return;
        }

        System.out.print ("Confirm Password: ");
        String secondPassword = scanner.nextLine ();
        if (secondPassword.equals ("esc"))
        {
            return;
        }

        while (Student.validatePassword (firstPassword, secondPassword) != 1)
        {
            if (Student.validatePassword (firstPassword, secondPassword) == 2)
            {
                System.out.println ("The incantations do not align! Speak your password once more to synchronize the enchantments.");

                System.out.print ("New Password: ");
                firstPassword = scanner.nextLine ();
                if (firstPassword.equals ("esc"))
                {
                    return;
                }

                System.out.print ("Confirm Password: ");
                secondPassword = scanner.nextLine ();
                if (secondPassword.equals ("esc"))
                {
                    return;
                }
            }
            else if (Student.validatePassword (firstPassword, secondPassword) == 0)
            {
                System.out.println ("Magical defenses demand greater complexity! Forge a new password adorned with an uppercase incantation, a numerical talisman, and a special character charm.");

                System.out.print ("Password: ");
                firstPassword = scanner.nextLine ();
                if (firstPassword.equals ("esc"))
                {
                    return;
                }

                System.out.print ("Confirm Password: ");
                secondPassword = scanner.nextLine ();
                if (secondPassword.equals ("esc"))
                {
                    return;
                }
            }
        }

        for (int i = 0; i < allPasswords.size (); i++)
        {
            if (password.equals (allPasswords.get (i)))
            {
                allPasswords.set (i, hashPassword (firstPassword, teacher.salt));
            }
        }
        teacher.password = hashPassword (firstPassword, teacher.salt);

        System.out.println ("Password changed successfully");
        Main.sleep (1000);
    }

}
