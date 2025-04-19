import java.util.Arrays;

public class GenericMethods{
    public static <AnyType extends Comparable<AnyType>> int linearSearch(AnyType[] arr, AnyType x) {
    int targetValue = -1;

    for (int i = 0; i < arr.length; i++){
        if (arr[i].compareTo(x) == 0){
            return i;
        }
    }

    return targetValue;
    }
  
    public static <AnyType extends Comparable<AnyType>> int binarySearch(AnyType[] a, AnyType x) {
    return binarySearch(a, x, 0, a.length - 1);
    }

    private static <AnyType extends Comparable<AnyType>> int binarySearch(AnyType[] a, AnyType x, int lowValue, int highValue) {

    
        if (lowValue > highValue) {
            return -1;
        }
        
        int middleValue = (lowValue + highValue) / 2;

        if (a[middleValue].compareTo(x) == 0) {
            return middleValue;
        } 
        else if (a[middleValue].compareTo(x) < 0) {
            return binarySearch(a, x, middleValue + 1, highValue);
        } 
        else {
            return binarySearch(a, x, lowValue, middleValue - 1);
        }
    }

//Tester
  
    public static void main(String[] args) {
    int count = 100;

    Rectangle[] rectangles = new Rectangle[count];

    for (int i = 0; i < count; i++) {
      rectangles[i] = new Rectangle(i, i + 100);
    }

    Arrays.sort(rectangles);

    //This should print 0
    System.out.println(binarySearch(rectangles, new Rectangle(0,100)));

    //This should print -1
    System.out.println(binarySearch(rectangles, new Rectangle(1, 100)));
      
    //This should print 0  
    System.out.println(linearSearch(rectangles, new Rectangle(0, 100)));  

    //This should print -1
    System.out.println(linearSearch(rectangles, new Rectangle(1, 100))); 
  }
}