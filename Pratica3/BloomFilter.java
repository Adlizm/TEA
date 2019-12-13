package pratica3;

import java.util.Random;
import java.util.ArrayList;

public class BloomFilter {
    static final int BIG_PRIME = 2653681;
    static final int SEED = 453;
    
    private int KHashs;
    private int TableSize;
    private boolean[] hashTable;
    private int maxTails[];
    private int G;
    
    public BloomFilter(int K, int M) {
        this.KHashs = K;
        this.TableSize = M;
        
        this.hashTable = new boolean[M];
        this.maxTails = new int[K];
        
        this.G = (int) Math.sqrt(K);
        
        for(int i = 0; i < M; i++)
            this.hashTable[i] = false;
        for(int i = 0; i < K; i++)
            this.maxTails[i] = 0;
    }

    boolean contains(String termo) {
        Random r = new Random(BloomFilter.SEED);
        for(int i = 0; i < this.KHashs; i++){
            int w = r.nextInt();
            int b = r.nextInt();
            
            int index = (w*termo.hashCode() + b) % BloomFilter.BIG_PRIME % this.TableSize;
            index = Math.abs(index);
            if(!this.hashTable[index])
            	return false;
        }
        return true;
    }

    void put(String termo) {
        Random r = new Random(BloomFilter.SEED);
        for(int i = 0; i < this.KHashs; i++){
            int w = r.nextInt();
            int b = r.nextInt();
            
            int index = (w*termo.hashCode() + b) % BloomFilter.BIG_PRIME % this.TableSize;
            index = Math.abs(index);
            this.hashTable[index] = true;
            
            int tail = 0;
            while(index % 2 == 0){
                tail++;
                index /= 2; 
            }
            if(this.maxTails[i] < tail)
               this.maxTails[i] = tail;
        }
    }

    int contadorFlajoletMartin() {
        int nGroups = 0;
        double sumMed = 0;
        for(int i = 0; i + this.G < this.KHashs; i += this.G){
            ArrayList<Integer> tails = new ArrayList<>();
            for(int j = i; j < i + this.G; j++){
                tails.add(this.maxTails[j]);
            }
            tails.sort((Integer x, Integer y) -> x - y);
            if(this.G % 2 == 0)
            	sumMed += ( tails.get((this.G - 1) / 2) + tails.get(this.G/2) ) / 2;
            else
            	sumMed += tails.get(this.G/2);
            nGroups++;
        }
        if(nGroups > 0)
            sumMed = (double) sumMed/nGroups;
        return (int) Math.pow(2, sumMed);
    }
}
