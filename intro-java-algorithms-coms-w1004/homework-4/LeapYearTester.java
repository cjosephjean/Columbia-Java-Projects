/**
* This class is to designed to work with your Year Class
*
* Do NOT alter this class. Your code must work with this class.
*/

import java.util.Scanner;

public class LeapYearTester{

    public static void main(String[] args){

        Scanner input = new Scanner(System.in);
        System.out.println("Please enter a year");
        int year_input = input.nextInt();

        // now make a Year object

        Year theYear = new Year(year_input);

        // now check to see if it's a leap year

        if (theYear.isLeapYear())
            System.out.println("That's a leap year!");
        else
            System.out.println("That's not a leap year!");
    }
}
