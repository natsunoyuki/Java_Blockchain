import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/*
Object to hold the data in Block. Block can take any object as data
and hash it as long as it can be converted to a string.

For this project we use a Hashtable() to store the user-coin data as 
key-item pairs.

Objects contained in BCData class:
    1. Map<String, Integer> Hashtable object

Possible way to input data into Map:
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
		//this is very dangerous as BCData then references the object X.
		//Object X should be cloned or copied!
        data = X;
    }//end BCData(Map X)

    public BCData(BCData other){
		this.data = new Hashtable<String,Integer>();
        Iterator<Entry<String,Integer>> i = other.data.entrySet().iterator();
        while (i.hasNext()) {
            Map.Entry<String,Integer> pair = (Map.Entry<String,Integer>) i.next();
            this.data.put(pair.getKey(),pair.getValue());
        }//end while
	}//end BCData(BCData other)

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
