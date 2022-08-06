package antonio.j2ee.practica1Thinspo.video.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Representa a una fila de la tabla Video
 * @author  Antonio Sánchez Antón
 * @since  1.0
 */
public class Video {
    protected String videoid;
    protected float averageold;
    protected String categories;
    protected Date datecaptured;
    protected String description;
    protected long duration;
    protected long favoritecount;
    protected int numdislikes;
    protected int numlikes;
    protected long numratersold;
    protected Date published;
    protected double scoresnanormalized;
    protected double scoresnatotal;
    protected double scoresnavideo;
    protected Date timecaptured;
    protected Date timecommentssearched;
    protected Date timescorecalculated;
    protected String title;
    protected String uoloader_username_oid;
    protected long viewcount;

    protected Collection<Tag>tags;
    
    public Video(){
    	
    }
    
    /**
     * Constructor con parametros
     * @param videoid
     * @param averageold
     * @param categories
     * @param datecaptured
     * @param description
     * @param duration
     * @param favoritecount
     * @param numdislikes
     * @param numlikes
     * @param numratersold
     * @param published
     * @param scoresnanormalized
     * @param scoresnatotal
     * @param scoresnavideo
     * @param timecaptured
     * @param timecommentssearched
     * @param timescorecalculated
     * @param title
     * @param uoloader_username_oid
     * @param viewcount
     */
	public Video(String videoid, float averageold, String categories,
			Date datecaptured, String description, long duration,
			long favoritecount, int numdislikes, int numlikes,
			long numratersold, Date published, double scoresnanormalized,
			double scoresnatotal, double scoresnavideo, Date timecaptured,
			Date timecommentssearched, Date timescorecalculated, String title,
			String uoloader_username_oid, long viewcount) {
		this.videoid = videoid;
		this.averageold = averageold;
		this.categories = categories;
		this.datecaptured = datecaptured;
		this.description = description;
		this.duration = duration;
		this.favoritecount = favoritecount;
		this.numdislikes = numdislikes;
		this.numlikes = numlikes;
		this.numratersold = numratersold;
		this.published = published;
		this.scoresnanormalized = scoresnanormalized;
		this.scoresnatotal = scoresnatotal;
		this.scoresnavideo = scoresnavideo;
		this.timecaptured = timecaptured;
		this.timecommentssearched = timecommentssearched;
		this.timescorecalculated = timescorecalculated;
		this.title = title;
		this.uoloader_username_oid = uoloader_username_oid;
		this.viewcount = viewcount;
		tags=procesarTags();
	}

    
    /**
     * Forma a partir de la columna de BBDD categories la Collection de Tags
     * @return
     */
	private Collection<Tag> procesarTags() {
		if(tags==null)
			 tags=new ArrayList<Tag>();
		tags=VideoHelper.procesarCategoria(tags, this.categories);
		return tags;
	}

	public String getVideoid() {
		return videoid;
	}

	public void setVideoid(String videoid) {
		this.videoid = videoid;
	}

	public float getAverageold() {
		return averageold;
	}

	public void setAverageold(float averageold) {
		this.averageold = averageold;
	}

	public String getCategories() {
		return categories;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}

	public Date getDatecaptured() {
		return datecaptured;
	}

	public void setDatecaptured(Date datecaptured) {
		this.datecaptured = datecaptured;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public long getFavoritecount() {
		return favoritecount;
	}

	public void setFavoritecount(long favoritecount) {
		this.favoritecount = favoritecount;
	}

	public int getNumdislikes() {
		return numdislikes;
	}

	public void setNumdislikes(int numdislikes) {
		this.numdislikes = numdislikes;
	}

	public int getNumlikes() {
		return numlikes;
	}

	public void setNumlikes(int numlikes) {
		this.numlikes = numlikes;
	}

	public long getNumratersold() {
		return numratersold;
	}

	public void setNumratersold(long numratersold) {
		this.numratersold = numratersold;
	}

	public Date getPublished() {
		return published;
	}

	public void setPublished(Date published) {
		this.published = published;
	}

	public double getScoresnanormalized() {
		return scoresnanormalized;
	}

	public void setScoresnanormalized(double scoresnanormalized) {
		this.scoresnanormalized = scoresnanormalized;
	}

	public double getScoresnatotal() {
		return scoresnatotal;
	}

	public void setScoresnatotal(double scoresnatotal) {
		this.scoresnatotal = scoresnatotal;
	}

	public double getScoresnavideo() {
		return scoresnavideo;
	}

	public void setScoresnavideo(double scoresnavideo) {
		this.scoresnavideo = scoresnavideo;
	}

	public Date getTimecaptured() {
		return timecaptured;
	}

	public void setTimecaptured(Date timecaptured) {
		this.timecaptured = timecaptured;
	}

	public Date getTimecommentssearched() {
		return timecommentssearched;
	}

	public void setTimecommentssearched(Date timecommentssearched) {
		this.timecommentssearched = timecommentssearched;
	}

	public Date getTimescorecalculated() {
		return timescorecalculated;
	}

	public void setTimescorecalculated(Date timescorecalculated) {
		this.timescorecalculated = timescorecalculated;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUoloader_username_oid() {
		return uoloader_username_oid;
	}

	public void setUoloader_username_oid(String uoloader_username_oid) {
		this.uoloader_username_oid = uoloader_username_oid;
	}

	public long getViewcount() {
		return viewcount;
	}

	public void setViewcount(long viewcount) {
		this.viewcount = viewcount;
	}

	@Override
	public String toString() {
		return title;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((videoid == null) ? 0 : videoid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Video other = (Video) obj;
		if (videoid == null) {
			if (other.videoid != null)
				return false;
		} else if (!videoid.equals(other.videoid))
			return false;
		return true;
	}

	public Collection<Tag> getTags() {
		return tags;
	}

	
	

}
