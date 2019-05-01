import java.util.*;

public class BCData{
    
    private Dictionary data; 
    
    public BCData(String a, int b){
	    data=new Hashtable();
	    data.put(a,b);
	}
	
	public Dictionary getData(){
		return data;
	}

}
