package videoClub;

public class VideoClubException extends RuntimeException{
	public VideoClubException(){
		super("\n\tVideoClbuException");
	}
	
	public VideoClubException(String err){
		super("\n\tVideoClubException"+err);
	}

}
