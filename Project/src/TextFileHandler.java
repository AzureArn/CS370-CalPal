import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;

// class responsible for creating/adjusting text files
public class TextFileHandler {
    private static final String textFolder = "DataFiles";

    // finds the folder or file with the given name
    // returns the File object containing the folder or a File object with an empty string
    public static File getFile(String fileName){
        // get path to file
        String filePath = "";
        try {
            Stream<Path> paths = Files.walk(Paths.get(System.getProperty("user.dir")));
            paths = paths.filter(str ->  str.toString().endsWith(fileName));
            Optional<Path> p = paths.findAny();
            if (p.isPresent()) {
                filePath = p.get().toString();
            }
            paths.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        return new File(filePath);
    }

    // creates text file if it doesn't exist,
    // return true if file created, false if not
    // name argument is the name of the file to create
    public static boolean createTextFile(String fileName){
        File folder = getFile(textFolder);
        if(folder.getName().isEmpty()){ // if the folder could not be found
            System.out.println("Couldn't find the parent folder");
            System.exit(2);
        }
        // new file placed in the chosen folder
        File file = new File(folder, fileName + ".txt");
        boolean fileCreated = false;
        try{
            fileCreated = file.createNewFile();
        } catch (Exception e){
            e.printStackTrace();
            System.exit(3);
        }

        return fileCreated;
    }

    // append the given text to the given file
    public static void appendTextFile(String fileName, String text){
        try{
            BufferedWriter bWriter = new BufferedWriter(new FileWriter(new File(getFile(textFolder), fileName + ".txt"),true));
            bWriter.write(text);
            bWriter.close();
        } catch (IOException ioe){
            ioe.printStackTrace();
            System.exit(5);
        }
    }

    // replaces the last line of a text file
    public static void changeLastLine(String fileName, String text){
        // create new file to write into and later rename it to match the old file's name
        File newFile = new File(getFile(textFolder), "new.txt");
        File oldFile = new File(getFile(textFolder), fileName + ".txt");
        try{
            // writer into the new file
            BufferedWriter bWriter = new BufferedWriter(new FileWriter(newFile,false));

            // reader for the old file
            Scanner reader = new Scanner(new FileReader(oldFile));

            // while there are lines in the file, read the line and write into new file
            while(reader.hasNextLine()){
                String line = reader.nextLine() + "\n";
                if(!reader.hasNextLine()){ // if this is the last line, insert the text
                    line = text;
                }
                bWriter.write(line);
            }

            // rename the new file to match the old one, delete the old one
            reader.close();
            bWriter.close();
            oldFile.delete(); // old file must be deleted before renaming
            newFile.renameTo(oldFile);

        } catch(Exception fnfe){
            fnfe.printStackTrace();
            System.exit(4);
        }
    }

    public static String getFileContents(String fileName){
        // the string to return
        StringBuilder contents = new StringBuilder();

        // reader for the file
        try{
            Scanner reader = new Scanner(new FileReader(getFile(fileName + ".txt")));
            // while there are lines in the file, read the line and append it to the string
            while(reader.hasNextLine()){
                contents.append(reader.nextLine()).append("\n");
            }
            reader.close();
        } catch(Exception e){
            e.printStackTrace();
            System.exit(6);
        }

        return contents.toString();
    }

}
