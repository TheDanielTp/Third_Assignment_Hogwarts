public interface AccountManagement
{
    public static int validatePassword (String firstPassword, String secondPassword)
    {
        return 0;
    }

    public void changeUsername (Account account);

    public void changePassword (Account account);
}
