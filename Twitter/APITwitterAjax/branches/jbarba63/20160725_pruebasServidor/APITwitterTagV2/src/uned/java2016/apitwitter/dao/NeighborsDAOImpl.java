package uned.java2016.apitwitter.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class NeighborsDAOImpl implements NeighborsDAO{
	
	private Connection conn;
    
    public NeighborsDAOImpl(Connection conn){
    	
    	this.conn = conn;
    }

	@Override
	public void insertNeighbors(String hashtag, String[] neighbors) {
		Statement stm = null;
		int rs = 0;
		try {
        	stm = this.conn.createStatement();
		        for (String string : neighbors) {
			    StringBuilder sql = new StringBuilder("INSERT INTO hashtag_neighbors (hashtag_id, neighbor) VALUES ( '"+hashtag+"','"+string+"' )");
			    System.out.println(sql);
			    rs = stm.executeUpdate(sql.toString());
			    System.out.println("INSERCION="+rs);
		        }
				stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void insertNeighbor(String hashtag, String neighbor) {
		Statement stm = null;
		int rs = 0;
		if (!(this.exists(hashtag, neighbor))){
			try {
        	stm = this.conn.createStatement();
			StringBuilder sql = new StringBuilder("INSERT INTO hashtag_neighbors (hashtag_id, neighbor) VALUES ( '"+hashtag+"','"+neighbor+"' )");
			System.out.println(sql.toString());
			rs = stm.executeUpdate(sql.toString());
			stm.close();
		    } catch (SQLException e) {
			    e.printStackTrace();
		    }
		}
	}

	@Override
	public ArrayList<String> selectNeighbors(String hashtag) {
		ArrayList<String> salida = new ArrayList();
		Statement stm = null;
		ResultSet rs = null;
		Integer cuenta=0;
        try {
        	stm = this.conn.createStatement();
        	rs = stm.executeQuery("select count(*) from hashtag_neighbors where hashtag_id='" + hashtag + "';");
			if (rs.next()){
				cuenta=(Integer)rs.getInt(1);
			}	
        	rs = stm.executeQuery("select neighbor from hashtag_neighbors where hashtag_id='" + hashtag + "';");
        	for (int i=0;i<cuenta;i++){
        		if(rs.next()){
        		    salida.add(rs.getString(1));
        		}
        	}
			rs.close();
			stm.close();
			return(salida);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(salida);
	}

	@Override
	public boolean exists(String hashtag, String neighbor) {
		Statement stm = null;
		ResultSet rs = null;
		try {
			stm = this.conn.createStatement();
        	rs = stm.executeQuery("select count(*) from hashtag_neighbors where hashtag_id='" + hashtag + "' and neighbor='"+neighbor+"';");
			if (rs.next()){
				if (rs.getInt(1) > 0){return(true);}
			}
			rs.close();
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
