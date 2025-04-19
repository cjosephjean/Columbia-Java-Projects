public class Rectangle implements RectangleInterface, Comparable<Rectangle> {
    private int length;
    private int width;
    public Rectangle (int l, int w){
      length = l;
       width = w;
    }
    public double getLength(){
      return length;
    }
    public double getWidth(){
      return width;
    }
    @Override
    public int compareTo(Rectangle other) {
        int perimeter = 2 * length + 2 * width;
        int perimeter2 = 2 * other.length + 2 * other.width;
        return perimeter - perimeter2;
    }
}
