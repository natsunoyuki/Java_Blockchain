import java.util.*; 
import java.security.NoSuchAlgorithmException;

/*
uses ArrayList and the .add method to implement the BlockChain 
BlockChain provides a platform to chain several Block objects together.

BlockChain contain the following objects:
    1. ArrayList to hold the blocks in sequential order
    2. Blockchain label as unique identification.
*/

public class BlockChain{

    //blockchain is stored as an ArrayList
    private ArrayList<Block> blockchain;
    //also label the blockchain:
    private String label;
    
    //Constructor
    public BlockChain(String x){
        label = x;
        blockchain = new ArrayList<Block>();
    }//end BlockChain(String x)

    public void addBlock(Block b){
        //append a new block to the end of the block chain
        blockchain.add(b);
    }//end addBlock(Block b)

    public Block getBlock(int i){
        return blockchain.get(i);
    }//end getBlock(int i)

    public Block getLastBlock(){
        int i = BlockChainLength() - 1;
        return blockchain.get(i);
    }//end getLastBlock()

    public int BlockChainLength(){
        return blockchain.size();
    }//end BLockChainLength()
    
    public boolean isEmpty(){
        return BlockChainLength() == 0;
    }//end isEmpty()
    
    public String toString(){
        return String.format("Blockchain Label : %s; blockchain Length : %d",label, BlockChainLength());
    }//end toString()
    
    public void printAllDetails(){
		//print information of all blocks in existence in sequence
        int N = BlockChainLength();
        for(int i = 0; i < N; i++){
            System.out.println(getBlock(i));
        }//end for
    }//end printAllDetails()
    
    public void printLastBlock(){
		//print minute details of last block in the chain
	    System.out.println(getLastBlock());
        System.out.printf("Current blockchain length : %d\n",BlockChainLength());
        System.out.println("Accounts : " + getLastBlock().getBCData().toString());
        System.out.println();
	}//end printLastBlock()
	
	public void verifyBlockChain(){
		Block B;
		String X;
		Iterator<Block> i = blockchain.iterator();
		while(i.hasNext()){
			B = i.next();
			System.out.println("Current hash for block " + B.getIndex() + ":");
			System.out.println(B.getCurrentHash());
			X = SHA.SHA256(B.getNonce()+B.getDate()+B.getBCData().toString()+B.getPreviousHash());
			//System.out.println(B.getNonce());
			//System.out.println(B.getDate());
			//System.out.println(B.getBCData().toString());
			//System.out.println(B.getPreviousHash());
			System.out.println("Calculated hash for block " + B.getIndex() + ":");
			System.out.println(X);
			if(X.equals(B.getCurrentHash())){
				System.out.printf("*************Block %d verified!*************\n", B.getIndex());
			}else{
			    System.out.printf("BLOCK %d ERROR!!!\n",B.getIndex());
			}
		}//end while()
	} //end veryfyBlockChain()
	
}//end BlockChain
