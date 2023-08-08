package Project;

import java.util.*;
import java.util.Random;
public class UPIPaymentService implements PaymentService {
    private int sotp=(int)(Math.random()*1000);
    @Override
    public void processPayment()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("ENTER THE BANK NAME:");
        String bankName =sc.nextLine();
        System.out.println("ENTER THE UPI ID:");
        String customerid =sc.nextLine();
        System.out.println("ENTER THE PIN:");
        int password =sc.nextInt();
        while(true){
            System.out.println("ENTER OTP Sent To Your Mobile: ");
            System.out.println("Your otp is "+sotp+" Do not share with anyone");
            int otp=sc.nextInt();
            if(otp==sotp){
                System.out.println("UPI payment received....... ");
                System.out.println("TICKET WILL BE SENT TO YOUR MAIL ID ");
                System.out.println("THANKS FOR BEING A VALUABLE CUSTOMER :) ");
                break;
            }
            else{
                System.out.println("WRONG OTP !!!  PLEASE RECHECK AND ENTER THE CORRECT ONE...");
            }
        }

    }
}