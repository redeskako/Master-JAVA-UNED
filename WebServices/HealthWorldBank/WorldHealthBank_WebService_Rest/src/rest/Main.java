package rest;

public class Main {

	public static void main( final String args[] ) throws Exception {
		new Server();
		System.out.println( "Server ready..." );
		 
		System.in.read();
		System.out.println( "Server exiting" );
		System.exit( 0 );
		}
}
