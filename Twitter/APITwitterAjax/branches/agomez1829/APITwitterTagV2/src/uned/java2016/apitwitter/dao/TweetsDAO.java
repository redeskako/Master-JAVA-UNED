package uned.java2016.apitwitter.dao;

import java.util.*;

public interface TweetsDAO {

	public void insertTweet(Tweet tweet);
	
	public void insertTweets(ArrayList<Tweet> tweets);
	
	public Tweet selectTweet(long tweetid);
	
	public ArrayList<Tweet> selectTweets(String hashtag);
	
	public ArrayList<Tweet> selectTweets(String hashtag, int minimo, int maximo);
	
	public int contadorTweets(String hashtag);
	
	public void deleteTweets(String hashtag);
	
	public boolean exists(long tweetid);
	
	public ArrayList<Tweet> leerTweetsconHashtagDeterminado(String hashtag,int primero,int cuantos);

	public long selectMaxId();

	public void insertMaxId(long maxid);
	
	
}
