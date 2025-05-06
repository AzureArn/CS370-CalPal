public class UserLoginManager {

    private static UserCredentialsCollection users;

    // constructor
    public UserLoginManager(){
        users = new UserCredentialsCollection();
    }

    // try to add user given the name and password
    public boolean addUser(String name, String password){
        return users.addUser(name, password);
    }

    // attempt to login
    public boolean logIn(String name, String password){
        return users.userExists(name, password);
    }

    // print all users to console
    public void printUsers(){
        users.printUsers();
    }
}