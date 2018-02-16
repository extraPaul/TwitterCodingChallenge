import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import twitter4j.IDs;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("SqwFneMMI2gUe6H1Gdef5UjSf")
		  .setOAuthConsumerSecret("iXk1AuPSH6OIVpBdwVApVhb7h7hvhfK9x4VqdXaQ6thAfToU1S")
		  .setOAuthAccessToken("514984495-TQIqptuhg9vHd8KfrXs72L7FOUal0eeJHRCceFtv")
		  .setOAuthAccessTokenSecret("9Vn33AqUWT8Ka1IAUotYfW9Y5sKzQdRBh9S8BAViA45lM");
		TwitterFactory factory = new TwitterFactory(cb.build());
	    Twitter twitter = factory.getInstance();
	    
	    File file = new File("users.txt");
	    List<String> userNameList = new ArrayList<String>();
	    Scanner input;
		try {
			input = new Scanner(file);
			while (input.hasNextLine()) {
				String temp = input.nextLine();
		        userNameList.add(temp.replace(",", "").replace("\\", ""));
		    }
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	    
	    
	    try {
	    	List<User> users = new ArrayList<User>();
	    	for(int i = 0; i < 10; i++){
	    		users.add(twitter.showUser(userNameList.get(i)));
	    	}
	    	
	    	
	    	users.sort(new Comparator<User>(){
	    	    @Override
	    	    public int compare(User u1, User u2) {
	    	    	int num1 = u1.getFollowersCount();
	    	    	int num2 = u2.getFollowersCount();
	    	        if(num1 < num2)
	    	        	return 1;
	    	        else if(num1 == num2)
	    	        	return 0;
	    	        else
	    	        	return -1;
	    	    }
	    	});
	    	
	    	for(User user : users){
	    		System.out.println(user.getName() + " has " + user.getFollowersCount() + " user");
	    	}
	    	
	    	/*
	        twitterScreenName = twitter.getScreenName();

		    IDs followerIDs = twitter.getFollowersIDs(twitterScreenName, -1);
		    long[] ids = followerIDs.getIDs();
		    for (long id : ids) {
		       twitter4j.User user = twitter.showUser(id);
		       //here i am trying to fetch the followers of each id
		       String userScreenName = user.getScreenName();
		       System.out.println("Name: " + user.getScreenName());
		       System.out.println("Location:" + user.getLocation());
	
		       IDs followerIDsOfFollowers = twitter.getFollowersIDs(user.getScreenName(), -1);
		       long[]fofIDs = followerIDsOfFollowers.getIDs();
		       for(long subId : fofIDs) {
		           twitter4j.User user1 = twitter.showUser(subId);
		           System.out.println("Follower Master:" + userScreenName +" Follower of Follower Name: " + user1.getScreenName());
		           System.out.println("Location:" + user1.getLocation());
		       }
		    }
		    */
	    } catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
