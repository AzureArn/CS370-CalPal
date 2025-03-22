// individual user's credentials
public class UserCredentials {

    private String name;
    private String password;

    public UserCredentials(String n, String p) {
        name = n;
        password = p;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
