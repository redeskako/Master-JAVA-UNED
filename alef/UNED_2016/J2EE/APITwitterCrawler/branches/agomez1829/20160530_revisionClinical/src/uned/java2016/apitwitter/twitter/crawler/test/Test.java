package uned.java2016.apitwitter.twitter.crawler.test;

import uned.java2016.apitwitter.twitter.crawler.net.restful.TwitterCredentials;
import uned.java2016.apitwitter.twitter.crawler.net.restful.TwitterRestAPIClient;
import uned.java2016.apitwitter.twitter.crawler.net.restful.TwitterSearchRequest;

public class Test {

	public static void main(String[] args) {
		
		
		// creamos credenciales
		TwitterCredentials c=new TwitterCredentials(
				"SZUPw862Fly3HqGRh8aootkWs", // apiKey
				"ifbFch7TL04vTDJIsZ78TJUdKX5O1PeYKBQwJVtUf8H5jkeALy", //apiSecret
				"222586918-93mKQ4kbhhYfrRLHbjhb5U3X0hpWRt2PjNgKnQSl", //tokenKey
				"WKhHhcA6dk8IzGfifDD70ev7PS2V3GFDSKzlD9VkgUC2g" //tokenSecret
		);
		 // creamos consulta rápida
		 TwitterSearchRequest searchRequest=new TwitterSearchRequest();
		 searchRequest.setQuery("#Diabetes");
		 searchRequest.setCount(2);
		 // creamos un cliente
		 TwitterRestAPIClient client=new TwitterRestAPIClient();
		 // y llamamos
		 try{
		 client.configureCredentials(c);
		 System.out.println(client.callApi(searchRequest));		 
		 }catch(Exception e){
			 System.out.println(e.getMessage());
		 }		 		 
	}

}
