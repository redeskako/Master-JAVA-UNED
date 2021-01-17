package uned.java2016.apitwitter.dao;

import java.util.ArrayList;

public interface NeighborsDAO {
	
	public void insertNeighbors(String hashtag, String[] neighbors);
	
	public ArrayList<String> selectNeighbors(String hashtag);
	
	public boolean exists(String hashtag, String neighbor);

	void insertNeighbor(String hashtag, String neighbor);

}
