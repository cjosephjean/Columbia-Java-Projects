import java.util.*;

public class Drunkard{
    // declare instance variables
    private int x;
    private int y;
    private int startX;
    private int startY;
    private ArrayList<String> directions;
    
    // stores initial location in variables
    public Drunkard(int avenue, int street){
        x = startX = avenue;
        y = startY = street;
        setDirections();
    }
    
    // stores direction options in array
    public void setDirections(){
        directions = new ArrayList<String>();
        directions.add("Up");
        directions.add("Down");
        directions.add("Left");
        directions.add("Right");
    }
    
    // shuffles/randomises directions
    public void step(){
        Collections.shuffle(directions);
        String direction = directions.get(0);
        if (direction == "Up")
            y += 1;
        if (direction == "Down")
            y -= 1;
        if (direction == "Left")
            x -= 1;
        if (direction == "Right")
            x += 1;
    }

    // moving ahead some number of steps
    public void fastForward(int steps){
        for (int i = 0; i < steps; i++)
            step();
    }
    
    // tells current location
    public String getLocation(){
         return "(" + x + " avenue, " + y + " street)";
    }
    
   
   // tells distance from the start 
    public int howFar(){
        int xDistance = Math.abs(x - startX);
        int yDistance = Math.abs(y - startY);
        return xDistance + yDistance;
    }
}