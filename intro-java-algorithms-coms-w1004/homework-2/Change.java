/** Change.java - add your solutiuon to program 3 of homework 1 here.
 * 
 */
import java.util.Scanner;

public class Change {
    
    public static final void main(String[] args) {
        
         Scanner myVar = new Scanner(System.in);
        
        //System prompt amount due e.g. 119
        System.out.println("Enter amount due in pennies:");

        //Numerical input stored in due
        int due = myVar.nextInt();
        
        //System prompt amount paid e.g. 300
        System.out.println("Enter amount received from customer in pennies:");

        //Numerical input stored in paid
        int paid = myVar.nextInt();
        
        //Calculation of change e.g. 181
        int change = paid-due;
        
        //to get the number of dollars e.g. 1 dollar
        int dollars = change/100; 

        //to get the change after dollars e.g. 81 cents
        int quarterandunder = change % 100;

        //to get the number of quarters e.g. 3 quarters
        int quarters = quarterandunder/25;

        //to get the change after quarters e.g. 6 cents
        int dimeandunder = quarterandunder % 25;

        //to get the number of dimes e.g. 0 dimes
        int dimes = dimeandunder/10;

        //to get the change after dimes e.g. 6 cents
        int nickelandunder = dimeandunder % 10; 

        //to get the number of nickels e.g. 1 nickel
        int nickels = nickelandunder/5;

        //to get the change after nickels a.k.a. the number of pennies e.g. 1 cent
        int pennies = nickelandunder % 5;
        
        //Output by system
        System.out.println(dollars + " dollar(s)");        
        System.out.println(quarters + " quarter(s)");
        System.out.println(dimes + " dime(s)");
        System.out.println(nickels + " nickel(s)");
        System.out.println(pennies + " penny/pennies/pence is the change due.");
        
        
    }
    
    
}