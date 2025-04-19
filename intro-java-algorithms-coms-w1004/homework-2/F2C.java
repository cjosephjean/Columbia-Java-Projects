/** F2C.java - add your solutiuon to program 1 of homework 1 here.
 * 
 */
import java.util.Scanner;

public class F2C {
    
    public static final void main(String[] args) {
        Scanner myVar = new Scanner(System.in);
        
        //System prompt
        System.out.println("Enter temperature in Farenheit as an integer:");

        // Numerical input
        int temperature = myVar.nextInt();
        int celcius = ((temperature-32) * 5/9);
            
        // Calculation and output input by user
        System.out.println(celcius + " ºC");
        
    }
    
    
}