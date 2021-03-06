import java.lang.Math;
import java.util.HashMap;

public class RewardPointsCalculator extends NewCustomer{
		
	public void calculatepoints(HashMap<String, Integer> customerList, String name, String dateTime, Double amount) {
		
		int points = 0;
		int currPoints = customerList.get(name);
		int hours = Integer.parseInt(dateTime);
		
		switch(hours) {
		
			case 10 :
				points = (int) (Math.ceil(amount) * 1);
				break;
			
			case 11:
				points = (int) (Math.ceil(amount) * 1 / 2);
				break;
			case 12:
				points = (int) (Math.ceil(amount) * 1 /3);
				break;
			case 13:
				points = (int) (Math.ceil(amount) * 1 /2);
				break;
			case 14:
				points = (int) (Math.ceil(amount) * 1);
				break;
			default:
				points = (int) (Math.ceil(amount) * 0.25);
		
		}	
		
		if(points >= 3 ) {
			customerList.put(name, points + currPoints);
		}	
	}
	
}
