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
    
    private Dictionary data; 
    
    public BCData(){
	    data = new Hashtable();
	}
    
    public BCData(String a, int b){
	    data = new Hashtable();
	    data.put(a,b);
	}
	
	public BCData(String[] a, int[] b){
		data = new Hashtable();
		int i = 0;
		int N = b.length;
		for(i=0; i<N; i++){
		    data.put(a[i],b[i]);
		}
	}
	
	public BCData(Dictionary X){
		data=X;
	}
	
	public Dictionary getData(){
		return data;
	}
	
	public void updateBCData(String[] a, int[] b){
		int i = 0;
		int N = b.length;
		for(i=0; i<N; i++){
		    data.put(a[i],b[i]);
		}
	}
	
	public String toString(){
	    return data.toString();
	}

}
