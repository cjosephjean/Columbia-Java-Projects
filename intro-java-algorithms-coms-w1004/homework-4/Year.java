/*
*
* Christopher Jean 
* 7/24/2020
*
* This class represents a calendar year.
*
* It contains a method that determines if
* the year is a leap year.
*
*/


public class Year{
    
    // declare instance variables
    private int CalYr;  


    // constructor
    public Year(int y){
        CalYr = y;
    }
    
    public boolean isLeapYear(){
        // if (year is not divisible by 4) then (it is a common year)
        if (CalYr % 4 != 0) {
           return false;
        } 
        // else if (year is not divisible by 100) then (it is a leap year)
        else if (CalYr % 100 != 0) {
           return true;
        }
        // else if (year is not divisible by 400) then (it is a common year)
        else if (CalYr % 400 != 0) {
           return false;
        }    
        // else (it is a leap year)

        else {
           return true;
        }        
    }
}    