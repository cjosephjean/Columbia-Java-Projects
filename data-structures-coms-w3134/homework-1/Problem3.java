public class Problem3{
public static void main(String[] args){
 
    BigO myBigO = new BigO();

    long startTime = System.nanoTime();
    myBigO.cubic(100);
    long endTime = System.nanoTime();

    System.out.println(endTime - startTime);

    startTime = System.nanoTime();
    myBigO.exp(100);
    endTime = System.nanoTime();

    System.out.println(endTime - startTime);

    startTime = System.nanoTime();
    myBigO.constant(100);
    endTime = System.nanoTime();

    System.out.println(endTime - startTime);


    startTime = System.nanoTime();
    myBigO.cubic(1000);
    endTime = System.nanoTime();

    System.out.println(endTime - startTime);

    startTime = System.nanoTime();
    myBigO.exp(1000);
    endTime = System.nanoTime();

    System.out.println(endTime - startTime);

    startTime = System.nanoTime();
    myBigO.constant(1000);
    endTime = System.nanoTime();

    System.out.println(endTime - startTime);

    startTime = System.nanoTime();
    myBigO.cubic(10000);
    endTime = System.nanoTime();

    System.out.println(endTime - startTime);

    startTime = System.nanoTime();
    myBigO.exp(10000);
    endTime = System.nanoTime();

    System.out.println(endTime - startTime);

    startTime = System.nanoTime();
    myBigO.constant(10000);
    endTime = System.nanoTime();

    System.out.println(endTime - startTime);
    }
}