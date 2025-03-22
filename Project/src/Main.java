import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        UserLoginManager ulm = new UserLoginManager();

        boolean exit = false;
        String inputText = "";
        int menuOption = 0;
        while(!exit){
            System.out.print("1) Log In\n" +
                             "2) Create New User\n" +
                             "3) View Users\n" +
                             "4) Exit\n" +
                             "Input a number: ");
            try{
                menuOption = keyboard.nextInt();
                keyboard.nextLine(); // clear input
            }catch (InputMismatchException ime){
                System.out.println("Invalid input\n");
                keyboard.nextLine(); // prevent infinite loop
                continue; // go to next iteration
            }

            if(menuOption == 1){
                String name;
                String password;

                System.out.print("User name: ");
                name = keyboard.nextLine();
                System.out.print("Password: ");
                password = keyboard.nextLine();

                ulm.login(name, password);
            }
            else if(menuOption == 2){
                String name;
                String password;

                System.out.print("Enter a name (no : allowed): ");
                name = keyboard.nextLine();
                System.out.print("Enter a password: ");
                password = keyboard.nextLine();

                ulm.addUser(name, password);
            }
            else if(menuOption == 3){
                ulm.printUsers();
                System.out.println();
            }
            else if(menuOption == 4){
                exit = true;
            }
            else{
                System.out.println("Invalid input\n");
            }
        }


    }
}
