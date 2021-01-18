package BBDD;

public class BBDDVideoClubException extends RuntimeException {
	
		public BBDDVideoClubException(){
			super("\n\tBBDDVideoClubException");
		}
		
		public BBDDVideoClubException(String err){
			super("\n\tBBDDVideoClubException"+err);
		}

	}

