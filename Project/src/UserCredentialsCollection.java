import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;

// collection of user credentials gained from the text file for easier checking
public class UserCredentialsCollection {
    // store user credentials here so the text file only has to be traversed once
    private ArrayList<UserCredentials> users;

    // only called by the other functions, creates file if it doesn't exist,
    // return true if file created, false if not
    private static boolean createUserFile(){
        return TextFileHandler.createTextFile("users");
    }

    // constructor
    public UserCredentialsCollection(){
        users = new ArrayList<UserCredentials>();
        if(!UserCredentialsCollection.createUserFile()){ // if a file wasn't created, check the file

            // put all lines of the file into an array
            String[] lines = TextFileHandler.getFileContents("users").split("\\R");

            // while there are lines in the file, read the line and add the user to the array
            for(String line : lines) {
                String name = line.substring(0, line.indexOf("::"));
                String password = line.substring(line.indexOf("::") + 2);
                users.add(new UserCredentials(name, password));
            }
        }
    }

    // checks if user with the given name is in the list (ignores case)
    public boolean userNameExists(String userName){
        for(UserCredentials u: users){
            if(u.getName().equalsIgnoreCase(userName)){
                return true;
            }
        }
        return false;
    }

    // return true if it was valid, false otherwise
    public boolean addUser(String name, String password){
        name = name.strip();
        password = password.strip();
        // check if there is both a name and password, and they don't contain colons
        if(name.isEmpty() || password.isEmpty() || name.contains(":") || password.contains(":")) {
            return false;
        }

        // return false if the name already exists
        if(userNameExists(name)){
            return false;
        }

        // append the line to the text file
        String line = name + "::" + password + "\n";
        TextFileHandler.appendTextFile("users", line);

        // place this new user into the array
        users.add(new UserCredentials(name, password));
        return true;
    }

    // return true if the user exists, false otherwise
    public boolean loginSuccess(String name, String password){
        name = name.strip();
        password = password.strip();
        // search through each user for a match, return false if one isn't found
        for(UserCredentials u : users){
            if(u.getName().equalsIgnoreCase(name) && u.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    public void printUsers(){
        for(UserCredentials u : users){
            System.out.println(u.getName());
        }
    }
}
