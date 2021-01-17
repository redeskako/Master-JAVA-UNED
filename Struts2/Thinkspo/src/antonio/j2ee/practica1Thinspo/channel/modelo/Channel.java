package antonio.j2ee.practica1Thinspo.channel.modelo;

import java.util.Date;

/**
 * Representa a una fila de la tabla Channel
 * @author  Antonio Sánchez Antón
 * @since  1.0
 */
public class Channel {
    protected String username;
    protected Long channelviews;
    protected Date datefavoritevideosearched;
    protected Date datejoined;
    protected Date datefriendssearch;
    protected Date datesubscribedtosearched;
    protected String location;
    protected Double scoresna;
    protected Double scoresnanormalized;
    protected Long subscriberscount;
    protected Date timecaptured;
    protected Date timefavcaptured;
    protected Date timescorecalculated;
    protected Date timesubcaptured;
    protected Long uploadviews;

    
    /**
     * Constructor vacio.Especificacion JavaBean
     */
    public Channel(){
    	
    }
 
    /**
     * Constructor con todos los valores de una fila de la tabla Channel
     * @param username
     * @param channelviews
     * @param datefavoritevideosearched
     * @param datejoined
     * @param datefriendssearch
     * @param datesubscribedtosearched
     * @param location
     * @param scoresna
     * @param scoresnanormalized
     * @param subscriberscount
     * @param timecaptured
     * @param timefavcaptured
     * @param timescorecalculated
     * @param timesubcaptured
     * @param uploadviews
     */
    public Channel(String username, Long channelviews,
			Date datefavoritevideosearched, Date datejoined,
			Date datefriendssearch, Date datesubscribedtosearched,
			String location, Double scoresna, Double scoresnanormalized,
			Long subscriberscount, Date timecaptured, Date timefavcaptured,
			Date timescorecalculated, Date timesubcaptured, Long uploadviews) {
		this.username = username;
		this.channelviews = channelviews;
		this.datefavoritevideosearched = datefavoritevideosearched;
		this.datejoined = datejoined;
		this.datefriendssearch = datefriendssearch;
		this.datesubscribedtosearched = datesubscribedtosearched;
		this.location = location;
		this.scoresna = scoresna;
		this.scoresnanormalized = scoresnanormalized;
		this.subscriberscount = subscriberscount;
		this.timecaptured = timecaptured;
		this.timefavcaptured = timefavcaptured;
		this.timescorecalculated = timescorecalculated;
		this.timesubcaptured = timesubcaptured;
		this.uploadviews = uploadviews;
	}

    //getters y setters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getChannelviews() {
		return channelviews;
	}

	public void setChannelviews(Long channelviews) {
		this.channelviews = channelviews;
	}

	public Date getDatefavoritevideosearched() {
		return datefavoritevideosearched;
	}

	public void setDatefavoritevideosearched(Date datefavoritevideosearched) {
		this.datefavoritevideosearched = datefavoritevideosearched;
	}

	public Date getDatejoined() {
		return datejoined;
	}

	public void setDatejoined(Date datejoined) {
		this.datejoined = datejoined;
	}

	public Date getDatefriendssearch() {
		return datefriendssearch;
	}

	public void setDatefriendssearch(Date datefriendssearch) {
		this.datefriendssearch = datefriendssearch;
	}

	public Date getDatesubscribedtosearched() {
		return datesubscribedtosearched;
	}

	public void setDatesubscribedtosearched(Date datesubscribedtosearched) {
		this.datesubscribedtosearched = datesubscribedtosearched;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Double getScoresna() {
		return scoresna;
	}

	public void setScoresna(Double scoresna) {
		this.scoresna = scoresna;
	}

	public Double getScoresnanormalized() {
		return scoresnanormalized;
	}

	public void setScoresnanormalized(Double scoresnanormalized) {
		this.scoresnanormalized = scoresnanormalized;
	}

	public Long getSubscriberscount() {
		return subscriberscount;
	}

	public void setSubscriberscount(Long subscriberscount) {
		this.subscriberscount = subscriberscount;
	}

	public Date getTimecaptured() {
		return timecaptured;
	}

	public void setTimecaptured(Date timecaptured) {
		this.timecaptured = timecaptured;
	}

	public Date getTimefavcaptured() {
		return timefavcaptured;
	}

	public void setTimefavcaptured(Date timefavcaptured) {
		this.timefavcaptured = timefavcaptured;
	}

	public Date getTimescorecalculated() {
		return timescorecalculated;
	}

	public void setTimescorecalculated(Date timescorecalculated) {
		this.timescorecalculated = timescorecalculated;
	}

	public Date getTimesubcaptured() {
		return timesubcaptured;
	}

	public void setTimesubcaptured(Date timesubcaptured) {
		this.timesubcaptured = timesubcaptured;
	}

	public Long getUploadviews() {
		return uploadviews;
	}

	public void setUploadviews(Long uploadviews) {
		this.uploadviews = uploadviews;
	}

}
