package Project;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class BankTransferPaymentService implements PaymentService {
    @Override
    public void processPayment()
    {

        Scanner in = new Scanner(System.in);

        System.out.print("Enter bank name: ");
        String enteredBankName = in.nextLine();

        System.out.print("Enter USERNAME: ");
        String enteredName = in.nextLine();

        System.out.print("Enter TYPE (SAVINGS/CURRENT) //type s or c ");
        String enteredType = in.nextLine();

        System.out.print("Enter PIN: ");
        String enteredPIN = in.nextLine();

        boolean detailsCorrect = false;

        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\viswa\\Downloads\\Java Project\\banktransfer.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String bankName = parts[0];
                String Name = parts[1];
                String Type = parts[2];
                String Pin = parts[3];

                if (bankName.equals(enteredBankName) && Name.equals(enteredName) && Type.equals(enteredType) && Pin.equals(enteredPIN)) {
                    System.out.println("Details are correct" );
                    detailsCorrect = true;
                    break; // No need to continue searching once we found the correct details
                }
            }
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if (detailsCorrect) {
            while (true) {
                System.out.println("ENTER OTP: ");
                int otp = in.nextInt();
                if (otp == 0000) {
                    System.out.println("Bank transfer payment received....... ");
                    System.out.println("TICKET WILL BE SENT TO YOUR MAIL ID ");
                    System.out.println("THANKS FOR BEING A VALUABLE CUSTOMER :) ");
                    break;
                } else {
                    System.out.println("WRONG OTP !!!  PLEASE RECHECK AND ENTER THE CORRECT ONE...");
                }
            }
        } else {
            System.out.println("Bank transfer details are incorrect. Payment cannot be processed.");
        }
    }
}
