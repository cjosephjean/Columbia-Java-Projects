/** Minutes.java - add your solutiuon to program 2 of homework 1 here.
 * 
 */
import java.util.Scanner;

public class Minutes {
    
    public static final void main(String[] args) {
        Scanner myVar = new Scanner(System.in);
        
        //System prompt hours
        System.out.println("Enter number of hours:");

        // Numerical input
        float hours = myVar.nextFloat();
        
        //System prompt days
        System.out.println("Enter number of days:");

        // Numerical input
        float days = myVar.nextFloat();
        
        //System prompt weeks
        System.out.println("Enter number of weeks:");

        // Numerical input
        float weeks = myVar.nextFloat();
        
        //System prompt years
        System.out.println("Enter number of years:");

        // Numerical input
        float years = myVar.nextFloat();

        // Calculation and output input by user
        System.out.println((hours*60+days*1440+weeks*10080+years*525600) + " total minutes");
        
        
        
        
    }
    
    
}