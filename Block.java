import java.util.*; 
import java.security.NoSuchAlgorithmException; //exception

/*
This java code was based on python code developed by:
https://medium.com/crypto-currently/lets-build-the-tiniest-blockchain-e70965a248b
*/

public class Block{
    private String nonce; //nonce string
    private Date date;    //Date object used as timestamp
    private BCData data;  //BCData object used to hold block chain data
    private String hash0; //previous hash
    private String hash1; //current hash

    public Block(String init_nonce, Date init_date, BCData init_data, String init_hash) throws NoSuchAlgorithmException{
		nonce = init_nonce;
		date = init_date;
		data = init_data;
		hash0 = init_hash;      
		hash1 = hashBlock();  
    }

    public void updateNonce(String new_nonce){
		nonce = new_nonce;
	}
	
	public void updateDate(Date new_date){
		date = new_date;
	}

    public void updateHash() throws NoSuchAlgorithmException{
	    hash1 = hashBlock();
	}

    public String hashBlock() throws NoSuchAlgorithmException{
		//hash the block using the nonce, date-time stamp, data and previous hash
		String string = nonce+date.toString()+data.toString()+hash0;
        String hashedString = SHA.SHA256(string);
        return hashedString;
    }

    public String toString(){
        return String.format("Date-time stamp : %s\nBlock Hash : %s",date.toString(), hash1);
    }
    
    public String getNonce(){
	    return nonce;
	}
    
    public String getCurrentHash(){
	    return hash1;
	}
}
