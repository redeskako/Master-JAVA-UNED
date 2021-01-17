package antonio.j2ee.practica1Thinspo.videosearch.modelo;

import java.util.Date;
/**
 * Representa a una fila de la tabla VideoSearch
 * @author  Antonio Sánchez Antón
 * @since  1.0
 */
public class VideoSearch {
    protected long videosearchid;
    protected String order;
    protected int position;
    protected String query;
    protected Date timeseached;
    protected String video_videoid_oid;

    
    public VideoSearch(){
    	
    }

     /**
      * Constructor 
      * @param videosearchid
      * @param order
      * @param position
      * @param query
      * @param timeseached
      * @param video_videoid_oid
      */
	public VideoSearch(long videosearchid, String order, int position,
			String query, Date timeseached, String video_videoid_oid) {
		super();
		this.videosearchid = videosearchid;
		this.order = order;
		this.position = position;
		this.query = query;
		this.timeseached = timeseached;
		this.video_videoid_oid = video_videoid_oid;
	}


	public long getVideosearchid() {
		return videosearchid;
	}


	public void setVideosearchid(long videosearchid) {
		this.videosearchid = videosearchid;
	}


	public String getOrder() {
		return order;
	}


	public void setOrder(String order) {
		this.order = order;
	}


	public int getPosition() {
		return position;
	}


	public void setPosition(int position) {
		this.position = position;
	}


	public String getQuery() {
		return query;
	}


	public void setQuery(String query) {
		this.query = query;
	}


	public Date getTimeseached() {
		return timeseached;
	}


	public void setTimeseached(Date timeseached) {
		this.timeseached = timeseached;
	}


	public String getVideo_videoid_oid() {
		return video_videoid_oid;
	}


	public void setVideo_videoid_oid(String video_videoid_oid) {
		this.video_videoid_oid = video_videoid_oid;
	}
    
    

}
