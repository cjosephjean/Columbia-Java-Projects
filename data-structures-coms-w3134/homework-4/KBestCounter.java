import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.List;

public class KBestCounter<T extends Comparable<? super T>> implements KBest<T> {
    
    private int returnK;
    PriorityQueue<T> kQueue = new PriorityQueue<>();
    
    public KBestCounter(int k) {
        returnK = k;
    }
    
    public void count(T x) {
        kQueue.add(x);
    }
    
    public List<T> kbest() {
        PriorityQueue<T> newKQueue = new PriorityQueue<>();
        List<T> kBestList = new ArrayList<>();
        int length = kQueue.size();
        int excess = length - returnK;
        if (excess < 0) {
            System.out.println("Fewer values exist than were requested; all will be returned.");
            excess = 0;
        }
        for (int i=1; i<=length; i++) {
            T val = kQueue.poll();
            newKQueue.add(val);
            if (i > excess) {
                kBestList.add(val);
            }
        }
        kQueue = newKQueue;
        return kBestList;
    }
    
}