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

    // only called by other functions to find the folder or file
    // returns the File object containing the folder or a File object with an empty string
    private static File findFile(String fileName){
        // get path to Project directory
        String projectPath = "";
        try {
            Stream<Path> paths = Files.walk(Paths.get(System.getProperty("user.dir")));
            paths = paths.filter(str ->  str.toString().endsWith(fileName));
            Optional<Path> p = paths.findAny();
            if (p.isPresent()) {
                projectPath = p.get().toString();
            }
            paths.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        return new File(projectPath);
    }

    // only called by the other functions, creates file if it doesn't exist,
    // return true if file created, false if not
    private static boolean createUserFile(){
        File folder = UserCredentialsCollection.findFile("Project");
        if(folder.getName().isEmpty()){ // if the folder could not be found
            System.out.println("Couldn't find the folder");
            System.exit(2);
        }
        // new file placed in the chosen folder
        File file = new File(folder,"users.txt");
        boolean fileCreated = false;
        try{
            fileCreated = file.createNewFile();
        } catch (Exception e){
            e.printStackTrace();
            System.exit(3);
        }

        return fileCreated;
    }

    // constructor
    public UserCredentialsCollection(){
        users = new ArrayList<UserCredentials>();
        if(!UserCredentialsCollection.createUserFile()){ // if a file wasn't created, check the file
            try{
                // finds the file in the Project folder
                Scanner reader = new Scanner(new FileReader(new File(UserCredentialsCollection.findFile("Project"), "users.txt")));
                // while there are lines in the file, add the users to the array
                while(reader.hasNextLine()){
                    String line = reader.nextLine();
                    String name = line.substring(0, line.indexOf("::"));
                    String password = line.substring(line.indexOf("::") + 2);
                    users.add(new UserCredentials(name, password));
                }
                reader.close();

            } catch(Exception fnfe){
                fnfe.printStackTrace();
                System.exit(4);
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

        try{
            BufferedWriter bWriter = new BufferedWriter(new FileWriter(new File(UserCredentialsCollection.findFile("Project"), "users.txt"),true));
            // users in the file are stored in this format: name::password
            bWriter.write(name + "::" + password + "\n");
            bWriter.close();
        } catch (IOException ioe){
            ioe.printStackTrace();
            System.exit(5);
        }

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
