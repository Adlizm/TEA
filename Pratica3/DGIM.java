package pratica3;

import java.util.ArrayList;

public class DGIM {
    
    
    class Bucket{
        int length = 0;
        int time;
        public Bucket(int time){
            this.time = time;
        }
    }
    private int timestamp = 0;
    private int maxSize;
    private ArrayList<Bucket> buckets;
    private int maxBuckets;
    
    public DGIM(int maxWindowSize) {
        this.maxSize = maxWindowSize;
        this.maxBuckets = (int) (2 * Math.log(maxSize) / Math.log(2)) + 1;
        this.buckets = new ArrayList<>();
    }

    void put(int key) {
        if(key == 1){
           buckets.add(new Bucket(timestamp));
           for(int i = buckets.size() - 1; i >= 2; i-- ){
                Bucket top = buckets.get(i);
                Bucket mid = buckets.get(i - 1);
                Bucket bot = buckets.get(i - 2);
                if(top.length == mid.length && mid.length == bot.length){
                    bot.time = mid.time;
                    bot.length++;
                    buckets.set(i - 1, top);
                    buckets.remove(i);
                }
           }
        }
        timestamp++;
        if(buckets.size() > this.maxBuckets){
            buckets.remove(0);
        }
    }

    int maxWindowSize() {
        return this.maxSize;
    }

    int contagemJanela(int t) {
        int i,qnt = 0;
        Bucket b = null;
        for(i = buckets.size() - 1; i >= 0; i--){
            b= buckets.get(i);
            if(b.time >= t){
                qnt += (int) Math.pow(2,b.length);
                b = null;
            }else{
                break;
            }
        }
        qnt +=  b != null ? (t - b.time): 0;
        return qnt;
    }
}
