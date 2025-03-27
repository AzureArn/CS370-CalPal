import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;

public class UserLoginManager {

    private static UserCredentialsCollection users;

    // constructor
    public UserLoginManager(){
        users = new UserCredentialsCollection();
    }

    // try to add user given the name and password
    public void addUser(String name, String password){
        name = name.strip();
        password = password.strip();
        boolean addedUser = users.addUser(name, password);
        if(addedUser){
            System.out.println("User " + name + " added");
        }
        else{
            System.out.println("User could not be added, make sure the name is unique and doesn't include ':'");
        }
    }

    // attempt to login
    public boolean login(String name, String password){
        name = name.strip();
        password = password.strip();
        if(users.loginSuccess(name, password)){
            System.out.println("Logged in successfully");
            return true;
        }
        else{
            System.out.println("Could not log in, user name or password invalid");
            return false;
        }
    }

    // print all users
    public void printUsers(){
        users.printUsers();
    }
}