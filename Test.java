import java.security.NoSuchAlgorithmException; 
import java.util.*;

public class Test{
    //ALWAYS REMEMBER TO SEED THE RNG!
    public static Random random = new Random(System.currentTimeMillis());
    
    public static void main(String args[]){ 
        BlockChain blockchain = new BlockChain("Test");
	    int M = 8; //acceptable nonce length
	    int n; //number of leading zeros in hash
    	if(args.length == 0){
    	    System.out.println("Number of leading zeros not set... using default of 4...");
            n = 4; //set default to 4 leading zeros
    	} else {
    	    n = Integer.parseInt(args[0]);
    	}

        blockchain.addBlock(createGenesisBlock(n,M));
        System.out.println(blockchain.getLastBlock());
        System.out.println(blockchain);
	
        System.out.println();
	
        blockchain.addBlock(createNextBlock(n,M,blockchain.getLastBlock(),"Yumi",100));
        System.out.println(blockchain.getLastBlock());
        System.out.println(blockchain);

        System.out.println();
	
        blockchain.addBlock(createNextBlock(n,M,blockchain.getLastBlock(),"Naomi",100));
        System.out.println(blockchain.getLastBlock());
        System.out.println(blockchain);		
		
    }  
    
    //function to create the Genesis block of the block chain.
    //This must be outside the Block class
    public static Block createGenesisBlock(int N, int M) {
        //create the genesis block of a block chain. This function 
        //should only be used ONCE!
        System.out.print("Creating block number 1 now... ");
        int counter = 0;
        int init_nonce_length = 1;
        String init_data_name = "Natsume";
        int init_data_amount = 100;
        String zerostring = SHA.ZeroString(N);
        String init_nonce = RandomString(init_nonce_length);
        String init_hash = SHA.SHA256(init_nonce);
        BCData data = new BCData(init_data_name, init_data_amount);
        Block block = new Block(1,init_nonce,new Date(),data,init_hash);
        while(!block.getCurrentHash().substring(0,N).equals(zerostring)){
            counter++; //count how many iterations in total
            init_nonce += RandomString(1);            
            block = new Block(1,init_nonce,new Date(),data,init_hash);
		    if(init_nonce.length() > M){
                //as we do not want too long a nonce, reset the length
                //to 1 after a certain number of trials
                //System.out.printf("\n%d iterations complete...", i);
                init_nonce = RandomString(init_nonce_length);
                block = new Block(1,init_nonce,new Date(),data,init_hash);
            }
            //System.out.println(init_nonce.length());
		} 
        System.out.printf("%d iterations to create genesis block.\n",counter);
		return block;
    }
    
    public static Block createNextBlock(int N, int M, Block prevBlock, String init_data_name, int init_data_amount) {
		//using data from the previous block, create a new block
		int index = prevBlock.getIndex()+1;
		System.out.printf("Creating block number %d now... ", index);
		int i = 1; //loop counter resetter
        int counter = 0;
		int init_nonce_length = 1;
		String zerostring = SHA.ZeroString(N);
		String init_nonce = RandomString(init_nonce_length);
		String init_hash = prevBlock.getCurrentHash();
		BCData data = new BCData(init_data_name, init_data_amount);
		Block block = new Block(index,init_nonce,new Date(),data,init_hash);
		while(!block.getCurrentHash().substring(0,N).equals(zerostring)){
            counter++; //count how many iterations in total
            init_nonce += RandomString(1);            
            block = new Block(index,init_nonce,new Date(),data,init_hash);
		    if(init_nonce.length() > M){
                //as we do not want too long a nonce, reset the length
                //to 1 after a certain number of trials
                //System.out.printf("\n%d iterations complete...", i);
                init_nonce = RandomString(init_nonce_length);
                block = new Block(index,init_nonce,new Date(),data,init_hash);
            }
            //System.out.println(init_nonce.length());
		}
        System.out.printf("%d iterations to create new block.\n",counter);
        return block;		
	}
    
    //creates a repeating random alpha-numeric string of length N
    public static String RandomString(int N){
        //String a = "abcdefghijklmnopqrstuvwxyz0123456789";
        String a = "0123456789abcdef";
        int alen = a.length();
        String string = "";
        int b;
        for(int i=0;i<N;i++){
	        b = random.nextInt(alen);
	        string += a.charAt(b);
	    }
        return string;
    }    
}
