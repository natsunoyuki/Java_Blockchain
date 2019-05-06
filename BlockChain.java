import java.util.*; 
import java.security.NoSuchAlgorithmException; //exception

//uses ArrayList and the .add method to implement the BlockChain 
//technology. We do not implement .remove as blocks cannot be removed
//from a blockchain after appending.

public class BlockChain{
    //instead of using an array: Block[] blockchain = new Block[N];
    //use an ArrayList instead which allows pop and append operations
    private ArrayList<Block> blockchain;
    private String label;
    
    public BlockChain(String x){
		label = x;
		blockchain = new ArrayList<Block>();
	}
	
	public void addBlock(Block b){
		//append a new block to the end of the block chain
		blockchain.add(b);
	}
	
	public Block getBlock(int i){
	    return blockchain.get(i);
	}
	
	public Block getLastBlock(){
	    int i = BlockChainLength() - 1;
	    return blockchain.get(i);
	}
	
	public int BlockChainLength(){
		return blockchain.size();
	}
    
    public boolean isEmpty(){
	    return blockchain.size() == 0;
	}
    
    public String toString(){
	    return String.format("Blockchain Label : %s, blockchain Length : %d",label, BlockChainLength());
	}
    
    public void printAllDetails(){
	    int N = BlockChainLength();
	    for(int i=0; i<N; i++){
		    System.out.println(getBlock(i));
		}
	}
    
}
