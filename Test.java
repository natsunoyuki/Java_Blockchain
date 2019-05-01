import java.security.NoSuchAlgorithmException; 
import java.util.*;

public class Test{
	//ALWAYS REMEMBER TO SEED THE RNG!
    public static Random random = new Random(System.currentTimeMillis());
    
    public static void main(String args[]) throws NoSuchAlgorithmException{ 
		Block block = createGenesisBlock(2);
		System.out.println(block);
		
		
    }  
    
    //function to create the Genesis block of the block chain.
    //This must be outside the Block class
    public static Block createGenesisBlock(int N) throws NoSuchAlgorithmException{
		int i=0;
		String zerostring = SHA.ZeroString(N);
		String init_nonce = RandomString(1);
		String init_hash = SHA.SHA256(init_nonce);
		BCData data = new BCData("Natsume",100);
		Block block = new Block(init_nonce,new Date(),data,init_hash);
        
		while(true){
			if(block.getCurrentHash().substring(0,N).equals(zerostring)){
			    break;
			}else{
		        init_nonce += RandomString(1);
		        block.updateNonce(init_nonce);
		        block.updateDate(new Date());
		        block.updateHash();
		        i++;
			}
		}
        System.out.printf("%d iterations to create genesis block.\n",i);
		return block;
    }
    
    public static Block createNextBlock(Block previous_block){
	}
    
    public static String RandomString(int N){
		String a = "abcdefghijklmnopqrstuvwxyz0123456789";
        int alen = 36;
        String string = "";
        int b;
        for(int i=0;i<N;i++){
			b = random.nextInt(alen);
		    string += a.charAt(b);
		}
        return string;
    }    

}


