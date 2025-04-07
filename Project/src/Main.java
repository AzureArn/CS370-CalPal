import javax.swing.*;

// Driver class
public class Main {
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run(){
                View view = new View();
                UIController controller = new UIController(view);
            }
        });
    }

// OLD NON-GUI CODE, DELETE WHEN GUI IS FUNCTIONAL
//    // function for the user screen, the one accessed after logging in
//    public static void userView(String userName, Scanner keyboard){
//        UserDataManager udm = new UserDataManager(userName);
//        boolean exit = false;
//        String inputText = "";
//        int menuOption = 0;
//        while(!exit) {
//            System.out.print("1) View Today's Entry\n" +
//                             "2) Adjust Today's Entry\n" +
//                             "3) View Earlier Entry\n" +
//                             "4) Exit\n" +
//                             "Input a number: ");
//            try {
//                menuOption = keyboard.nextInt();
//                keyboard.nextLine(); // clear input
//            } catch (InputMismatchException ime) {
//                System.out.println("Invalid input\n");
//                keyboard.nextLine(); // prevent infinite loop
//                continue; // go to next iteration
//            }
//
//            if(menuOption == 1){
//                udm.viewEntry(LocalDate.now());
//            }
//            else if(menuOption == 2){
//                int consumed;
//                int burned;
//                System.out.print("How many calories were consumed?: ");
//                consumed = keyboard.nextInt();
//                System.out.print("How many calories were burned?: ");
//                burned = keyboard.nextInt();
//                keyboard.nextLine(); // clear input
//                udm.saveCalorieData(consumed, burned);
//            }
//            else if(menuOption == 3){
//                String date;
//                System.out.print("Enter a date (yyyy-mm-dd): ");
//                date = keyboard.nextLine();
//                try {
//                    udm.viewEntry(LocalDate.parse(date));
//                }
//                catch(Exception e){
//                    System.out.println("Date wasn't in valid format");
//                }
//            }
//            else if(menuOption == 4){
//                exit = true;
//            }
//            else{
//                System.out.println("Invalid option number\n");
//            }
//        }
//    }
//
//    // current main function doesn't use swing UI,
//    // this is for demonstration and will be replaced
//    // Starts at log in screen
//    public static void main(String[] args) {
//        Scanner keyboard = new Scanner(System.in);
//        UserLoginManager ulm = new UserLoginManager();
//
//        boolean exit = false;
//        String inputText = "";
//        int menuOption = 0;
//        while(!exit){
//            System.out.print("1) Log In\n" +
//                             "2) Create New User\n" +
//                             "3) View Users\n" +
//                             "4) Exit\n" +
//                             "Input a number: ");
//            try{
//                menuOption = keyboard.nextInt();
//                keyboard.nextLine(); // clear input
//            }catch (InputMismatchException ime){
//                System.out.println("Invalid input\n");
//                keyboard.nextLine(); // prevent infinite loop
//                continue; // go to next iteration
//            }
//
//            if(menuOption == 1){
//                String name;
//                String password;
//
//                System.out.print("User name: ");
//                name = keyboard.nextLine();
//                System.out.print("Password: ");
//                password = keyboard.nextLine();
//
//                // if login successful, move to user's page
//                if(ulm.login(name, password)){
//                    Main.userView(name, keyboard);
//                    exit = true;
//                }
//            }
//            else if(menuOption == 2){
//                String name;
//                String password;
//
//                System.out.print("Enter a name (no : allowed): ");
//                name = keyboard.nextLine();
//                System.out.print("Enter a password: ");
//                password = keyboard.nextLine();
//
//                ulm.addUser(name, password);
//            }
//            else if(menuOption == 3){
//                ulm.printUsers();
//                System.out.println();
//            }
//            else if(menuOption == 4){
//                exit = true;
//            }
//            else{
//                System.out.println("Invalid option number\n");
//            }
//        }
//
//
//    }
}
