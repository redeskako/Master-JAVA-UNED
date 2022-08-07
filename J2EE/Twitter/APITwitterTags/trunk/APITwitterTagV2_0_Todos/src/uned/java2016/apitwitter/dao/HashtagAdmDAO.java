package uned.java2016.apitwitter.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface HashtagAdmDAO {
	
	public void insertHashtagAdm(HashtagAdm unhashtag) throws SQLException;
	
	public void insertHashtagAdm(ArrayList<HashtagAdm> grupo) throws SQLException;
	
	public boolean exists(String hashtag) throws SQLException;
	
	public HashtagAdm selectHashtagAdm(String hashtagid);
	
	public ArrayList<HashtagAdm> selectAll()throws SQLException;
	
	public ArrayList<String> selectHashtagIds() throws SQLException;
	
	public String AlineaHashTagsAdm() throws SQLException;

	public String AlineaHashTagsAdm(String texto) throws SQLException;

	public ArrayList<String> selectHashtagIds(String texto) throws SQLException;

        public void deleteHahstagAdm(String hashtagid) throws SQLException;

}
