import java.security.MessageDigest; 
import java.math.BigInteger; 
import java.security.NoSuchAlgorithmException; 
import java.util.Random;

public class SHA{
    public static String SHA256(String string){ 
		//Handles the Exception thrown by SHA256Except
        String H = null; //for returning
        try { 
            H = SHA256Except(string, 16, 64);
        } 
        catch(NoSuchAlgorithmException except){ 
            System.out.println(except); 
        } //end try catch
        return H;
    }//end SHA256
    
    public static String SHA256Except(String string, int N, int M) throws NoSuchAlgorithmException{
		//This function throws an Exception which MUST BE CAUGHT!!!
        String H = null; //for returning
        MessageDigest hasher = MessageDigest.getInstance("SHA-256"); 
        //the input string has to be encoded into bytes first!
        byte[] hashedbytes = hasher.digest(string.getBytes()); 
        BigInteger toInt = new BigInteger(1, hashedbytes); 
        H = toInt.toString(N);
        if(H.length() < M){
            H = ZeroString(M - H.length()) + H; 
        } //end if
        return H;	    
    }//end SHA256Except
    
    public static String ZeroString(int N){
        if(N < 0){
            return "";
        }//end if
        String string = "";
        for(int i = 0; i < N;i++){
            string += "0";
        }//end for
        return string;
    }//end ZeroString(int N)
}//end SHA
