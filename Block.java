import java.util.*; 
import java.security.NoSuchAlgorithmException; //exception

/*
This java code was based on python code developed by:
https://medium.com/crypto-currently/lets-build-the-tiniest-blockchain-e70965a248b
*/

public class Block{
	
    private int index;    //block index
    private String nonce; //nonce string
    private Date date;    //Date object used as timestamp
    private BCData data;  //BCData object used to hold block chain data
    private String hash0; //previous hash
    private String hash1; //current hash

    //Constructor:
    public Block(int i, String init_nonce, Date init_date, BCData init_data, String init_hash){
        index = i;
        nonce = init_nonce;
        date = init_date;
        data = init_data;
        hash0 = init_hash;      
        hash1 = hashBlock();  
    }//end Block()
    
    public String hashBlock() {
        //hash the block using the nonce, date-time stamp, data and previous hash
        String string = nonce+date.toString()+data.toString()+hash0;
        String hashedString = SHA.SHA256(string);
        return hashedString;
    }//end hashBlock()

    public String toString(){
        return String.format("Date-time stamp : %s\nBlock Hash : %s",date.toString(), hash1);
    }//end toString()
    
    public String getNonce(){
        return nonce;
    }//end getNonce()
    
    public BCData getBCData(){
        return data;
    }//end getBCData()
    
    public String getCurrentHash(){
        return hash1;
    }//end getCurrentHash()

    public String getPreviousHash(){
        return hash0;
    }//end getPreviousHash()

    public int getIndex(){
        return index;
    }//end getIndex()

    public Date getDate(){
        return date;
    }//end getDate()
}//end Block
