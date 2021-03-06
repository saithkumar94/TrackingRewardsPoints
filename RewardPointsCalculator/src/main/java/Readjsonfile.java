import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;


public class Readjsonfile {

	 public static HashMap<String, Integer> customerList = new HashMap<String, Integer>();
	 public static HashMap<String, Integer> customerOrderList = new HashMap<String, Integer>();

	 public static void main(String[] args) {
	 
	 JSONParser jsonP = new JSONParser();
	  
	 try(FileReader reader = new FileReader("src/main/resources/events.json")){  
		 //Read JSON File
		 Object obj = jsonP.parse(reader);
		 JSONObject eventsjsonobj = (JSONObject)obj;
		 // pass the input into a JSONArray
		 JSONArray eventList = (JSONArray) eventsjsonobj.get("events");
		 //System.out.println(eventList);
   
 
		 for(int i=0;i<eventList.size();i++) {
			 JSONObject eventObj = (JSONObject)eventList.get(i);
	   
			 String action = (String) eventObj.get("action");
			 String name = (String) eventObj.get("name");
			 String date_time = (String) eventObj.get("timestamp");
			 Double amount = 0.0;
			 String time = date_time.substring(11,13);
			 String customer = null;
	   
			 // insert new customer into map
			 if(action.contains("new_customer")) {
				 newCustomer(name, 0);
			 }
			 
			 //insert or add points for the new order to respective customer
			 else if(action.contains("new_order")) {
				 customer = (String) eventObj.get("customer");
				 if(eventObj.get("amount") != null) {
					 amount = (Double) eventObj.get("amount");				 
				 }
				 // call to calculatepoints method
				 calculatepoints(customer, time, amount);
			 }
			 	   
		 }
		 //call to print final points tally
		 printRewards();
   
	 }
	 catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
	 }
	 
	 
	 
		public static void newCustomer(String name, Integer points) {
			customerList.put(name, points);
			customerOrderList.put(name, 0);
			
		}

		
		public static void printRewards()  {
			
			for( Map.Entry<String, Integer> entry : customerList.entrySet() ){
				int points = entry.getValue();
				int total_orders = customerOrderList.get(entry.getKey());
			    if(points != 0) {
					int ppo = points/total_orders;
			    	System.out.println( entry.getKey() + ": " + entry.getValue() + " points with " + ppo + " per order");
			    }
			    else
			    	System.out.println(entry.getKey() + ": No orders.");
		}
		}
		
		
		public static void calculatepoints(String name, String time, Double amount) {

			int points = 0;
			int currPoints = 0;
			int total_orders = 0;
			if(customerList.get(name) != null)
				currPoints = customerList.get(name);
			
			if(customerOrderList.get(name)!=null)
				total_orders = customerOrderList.get(name);
			
			int hours = Integer.parseInt(time);
			
		
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
				total_orders++;
				customerOrderList.put(name, total_orders);
			}	
		}
		
}
