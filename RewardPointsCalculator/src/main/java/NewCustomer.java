import java.util.HashMap;

public class NewCustomer {
	
	//final HashMap<String, Integer> customerList = new HashMap<String, Integer>();
	
	public void newCustomer(HashMap<String, Integer> customerList, String name, Integer points) {
		System.out.println("New Customer method");
		customerList.put(name, points);
		
	}
	
	
	

}
