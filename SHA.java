import java.security.MessageDigest; 
import java.math.BigInteger; 
import java.security.NoSuchAlgorithmException; 
import java.util.Random;

public class SHA{
    public static String SHA256(String string){ 
        int N = 16; //output as hexadecimal value
	int M = 64; //proper hash length of 64
	String H = null;; //for returning
        try { 
            MessageDigest hasher = MessageDigest.getInstance("SHA-256"); 
            //the input string has to be encoded into bytes first!
            byte[] hashedbytes = hasher.digest(string.getBytes()); 
            BigInteger toInt = new BigInteger(1, hashedbytes); 
            H = toInt.toString(N);
            if(H.length() < M){
		H=ZeroString(M-H.length())+H; 
	    }
        } 
        catch(NoSuchAlgorithmException except){ 
            System.out.println(except); 
        } 
	return H;
    }   
    
    public static String ZeroString(int N){
	if(N<0){
            return "";
	}
	String string = "";
	for(int i=0;i<N;i++){
            string += "0";
        }
	return string;
    }
}
