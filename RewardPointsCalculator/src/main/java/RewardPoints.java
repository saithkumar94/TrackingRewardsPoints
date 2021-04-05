import java.util.HashMap;
import java.util.Map;

public class RewardPoints extends NewCustomer {
	
	public void printRewards(HashMap<String, Integer> customerList)  {
		
		for( Map.Entry<String, Integer> entry : customerList.entrySet() ){
		    System.out.println( entry.getKey() + ": " + entry.getValue() + " points");
		
	}
	}

}
