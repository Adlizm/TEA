/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pratica5;

/**
 *
 * @author graduacao
 */
public class Teste {
    public static void main(String[] args) {
        String[] words = {
            "banana","abcaxi","cafe","cha","loucura","chuveiro","solto","cafe","vacina","macio","balde","louco","fd"
        };
        BloomFilter palavras = new BloomFilter(10, 5000);
        int maxWindowSize = 10000;
        DGIM contador = new DGIM(maxWindowSize);
        
        for(String p:words){
            if(palavras.contains(p)){
                contador.put(0);
            }else{
                palavras.put(p);
                contador.put(1);
            }
        }
        System.out.println(palavras.contadorFlajoletMartin());
        System.out.println(contador.contagemJanela(5));
    }
}
