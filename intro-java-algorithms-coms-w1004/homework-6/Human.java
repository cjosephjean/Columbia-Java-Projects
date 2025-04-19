/***************
 * A template for a Human Nim player
 **************/ 
import java.util.Scanner;

public class Human{
   
    public int choice;
    public Scanner input;
    
    public Human(){
        input = new Scanner(System.in);
        choice = -1;
    }
    
    public void move(){
        System.out.println("How many marbles would you like to take?");
        choice = input.nextInt();
    }
    
    
    public int getChoice(){
        return choice;
    }
}