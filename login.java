package Project;
import java.io.*;
import java.util.*;

public class login {
    Scanner sc = new Scanner(System.in);
    String uname;
    String response;
    int incorrectAttempts = 0;
    private Main mainInstance;

    public void details(Main mainInstance) throws InterruptedException {

        this.mainInstance = mainInstance;
        System.out.println("--------------WELCOME TO AIRLINE RESERVATION SYSTEM--------------");
        System.out.println("---------------------------LOGIN SYSTEM--------------------------");
        System.out.println("ENTER YOUR USER NAME:");
        uname = sc.next();
        System.out.println("Hey " + uname + ", are you a new user? Yes or No");
        response = sc.next();
        if (response.equalsIgnoreCase("Yes")) {
            register(mainInstance);
        } else {
            signin(mainInstance);
        }
    }

    public void register(Main mainInstance) throws InterruptedException {
        System.out.println("---------------------------REGISTER SYSTEM--------------------------");
        System.out.println("");
        System.out.println("Enter your new password:");
        String newPassword = sc.next();
        System.out.println("");
        System.out.println("Registering " + uname + " with password...");
        System.out.println("Congratulations!!! You have successfully created an account");

        // Store the username and password in the login.txt file
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("login.txt", true));
            writer.write(uname + "," + newPassword);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        signin(mainInstance);
    }

    public boolean signin(Main mainClass) throws InterruptedException {
        while (true) {
            System.out.println("ENTER YOUR PASSWORD:");
            String enteredPassword = sc.next();
            if (validatePassword(enteredPassword)) {
                System.out.println("WELCOME " + uname + " YOUR LOGIN IS SUCCESSFUL !!!!");
                return true;

            } else {
                incorrectAttempts++;
                System.out.println("YOU HAVE ENTERED A WRONG PASSWORD. ATTEMPT " + incorrectAttempts + " OUT OF 3");
                if (incorrectAttempts >= 3) {
                    System.out.println("You have exceeded the maximum number of attempts. Changing password...");
                    changePassword();
                    signin(mainClass);
                } else {
                    System.out.println("Options: 1 - Retry, 2 - Forget pin");
                    int option = sc.nextInt();
                    if (option == 2) {
                        changePassword();
                    }
                }
            }
        }
    }



    public void changePassword() {
        System.out.println("Enter a new password:");
        String newPassword = sc.next();
        updatePasswordFile(newPassword);
        System.out.println("Password changed successfully!");
    }

    public boolean validatePassword(String enteredPassword) {
        try {
            File inputFile = new File("login.txt");
            Scanner fileScanner = new Scanner(inputFile);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 2 && parts[0].equals(uname) && parts[1].equals(enteredPassword)) {
                    return true;
                }
            }
            fileScanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updatePasswordFile(String newPassword) {
        try {
            ArrayList<String> lines = new ArrayList<>();
            File inputFile = new File("login.txt");
            Scanner fileScanner = new Scanner(inputFile);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (line.startsWith(uname + ",")) {
                    line = uname + "," + newPassword;
                }
                lines.add(line);
            }
            fileScanner.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter("login.txt"));
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Main main = new Main();
        main.userLoggedIn();
    }
}
