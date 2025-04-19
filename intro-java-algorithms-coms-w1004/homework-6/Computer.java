/***************
 * A template for a computer Nim player
 **************/

import java.util.Random;

public class Computer{
    
    public int mode;
    public int choice;
    
    public Computer(int m){
        mode = m;
        choice = -1;
    }
    
    public void move(int marblesLeftover){
    
  
        // If the computer is stupid or only (2^n)-1 marbles remain
        if (mode == 1 || marblesLeftover  == 1 || marblesLeftover == 3 || marblesLeftover == 7 || marblesLeftover == 15 || marblesLeftover == 31 || marblesLeftover == 63) {
            Random r = new Random();
            choice = r.nextInt(marblesLeftover/2);
        } else {
            // If the computer is smart, chooses whatever number of marbles that leaves (2^n)-1 remaining
            if (marblesLeftover > 63) {
                choice = marblesLeftover - 63;
            } else if (marblesLeftover > 31) {
                choice = marblesLeftover - 31;
            } else if (marblesLeftover > 15) {
                choice = marblesLeftover - 15;
            } else if (marblesLeftover > 7) {
                choice = marblesLeftover - 7;
            } else if (marblesLeftover > 3) {
                choice = marblesLeftover - 3;
            } else if (marblesLeftover > 1) {
                choice = 1;
            }
        }
    }
    
    
    public int getChoice(){
        return choice;
    }
    
    
}