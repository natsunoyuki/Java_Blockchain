import java.security.NoSuchAlgorithmException; 
import java.util.*;

public class Test{
    //ALWAYS REMEMBER TO SEED THE RNG!
    public static Random random = new Random(System.currentTimeMillis());
    
    public static void main(String args[]){ 
        BlockChain blockchain = new BlockChain("Test");
	    
	int n; //number of leading zeros in nonce
    	if(args.length == 0){
    	    System.out.println("Number of leading zeros not set... using default of 4...");
            n = 4; //set default to 4 leading zeros
    	} else {
    	    n = Integer.parseInt(args[0]);
    	}
	
        int M = 100000;
        blockchain.addBlock(createGenesisBlock(n),M);
        System.out.println(blockchain.getLastBlock());
        System.out.println(blockchain);
		
        blockchain.addBlock(createNextBlock(n,M,blockchain.getLastBlock(),"Yumi",20));
        System.out.println(blockchain.getLastBlock());
        System.out.println(blockchain);		
		
    }  
    
    //function to create the Genesis block of the block chain.
    //This must be outside the Block class
    public static Block createGenesisBlock(int N, int M) {
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
			if(i%M==0){
			    //as we do not want too long a nonce, reset the length
			    //to 1 after a certain number of trials
                            System.out.printf("\n%d iterations complete...", i);
                            init_nonce = RandomString(init_nonce_length);
                            block.updateNonce(init_nonce);
                            block.updateDate(new Date());
                            block.updateHash();
                            i=1;
                        }		
		    }
		}
        System.out.printf("%d iterations to create genesis block.\n",i);
		return block;
    }
    
    public static Block createNextBlock(int N, int M, Block prevBlock, String init_data_name, int init_data_amount) {
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
			if(i%M==0){
			    //as we do not want too long a nonce, reset the length
			    //to 1 after a certain number of trials
                            System.out.printf("\n%d iterations complete...", i);
                            init_nonce = RandomString(init_nonce_length);
                            block.updateNonce(init_nonce);
                            block.updateDate(new Date());
                            block.updateHash();
                            i=1;
                        }
		    }
		}
            System.out.printf("%d iterations to create new block.\n",i);
            return block;		
	}
    
    //creates a repeating random alpha-numeric string of length N
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


