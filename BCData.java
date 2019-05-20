import java.util.*;

/*
String[] a = {"a","b","c","d"};
int[] b = {1,2,3,4};
BCData X = new BCData(a,b);
System.out.println(X);	   
String[] c = {"e","f","g","h"};
int[] d = {5,6,7,8};
X.updateBCData(c,d);
System.out.println(X);	
*/

public class BCData{
    
    //The BlockChainData is stored according to key-item pairs in a map
    private Map<String, Integer> data; 
    
    //Constructors:
    public BCData(){
        data = new Hashtable();
    }//end BCData()
    
    public BCData(String a, int b){
        data = new Hashtable();
        data.put(a,b);
    }//end BCData(String a, int b)

    public BCData(String[] a, int[] b){
        data = new Hashtable();
        int i = 0;
        if(a.length == b.length){
            for(i = 0; i < b.length; i++){
                data.put(a[i],b[i]);
            }//end for
        }//end if
    }//end BCData(String[] a, int[] b)

    public BCData(Map X){
        data = X;
    }//end BCData(Map X)

    public Map getData(){
        return data;
    }//end getData()

    public boolean hasAccount(String a){
        return data.containsKey(a);
    }//end hasAccount(String a)

    public int getCoinAmount(String a){
        return data.get(a);
    }//end getCoinAmount(String a)

    public void updateBCData(String[] a, int[] b){
        int i = 0;
        int N = b.length;
        for(i = 0; i < N; i++){
            data.put(a[i],b[i]);
        }//end for
    }//end updateBCData(String[] a, int [] b)

    public String toString(){
        return data.toString();
    }//end toString()
}//end BCData
