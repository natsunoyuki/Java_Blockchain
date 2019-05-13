import java.security.NoSuchAlgorithmException; 
import java.util.*;

public class Test{
	//ALWAYS REMEMBER TO SEED THE RNG!
    public static Random random = new Random(System.currentTimeMillis());
    
    public static void main(String args[]){ 
		BlockChain blockchain = new BlockChain("Test");
		
		blockchain.addBlock(createGenesisBlock(2));
		System.out.println(blockchain.getLastBlock());
		System.out.println(blockchain);
		
		blockchain.addBlock(createNextBlock(2,blockchain.getLastBlock(),"Yumi",20));
		System.out.println(blockchain.getLastBlock());
		System.out.println(blockchain);		
		
    }  
    
    //function to create the Genesis block of the block chain.
    //This must be outside the Block class
    public static Block createGenesisBlock(int N) {
		//create the genesis block of a block chain. This function 
		//should only be used ONCE!
		System.out.print("Creating genesis block now... ");
		int i = 0; //loop counter
		int init_nonce_length = 1;
		String init_data_name = "Natsume";
		int init_data_amount = 100;
		String zerostring = SHA.ZeroString(N);
		String init_nonce = RandomString(init_nonce_length);
		String init_hash = SHA.SHA256(init_nonce);
		BCData data = new BCData(init_data_name, init_data_amount);
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
    
    public static Block createNextBlock(int N, Block prevBlock, String init_data_name, int init_data_amount) {
		//using data from the previous block, create a new block
		System.out.print("Creating new block now... ");
		int i = 0; //loop counter
		int init_nonce_length = 1;
		String zerostring = SHA.ZeroString(N);
		String init_nonce = RandomString(init_nonce_length);
		String init_hash = prevBlock.getCurrentHash();
		BCData data = new BCData(init_data_name, init_data_amount);
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
        System.out.printf("%d iterations to create new block.\n",i);
		return block;		
		
	}
    
    //creates a random alpha-numeric string of length N
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


