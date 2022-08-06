package com.restfulexample.rest.ws;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class HelloWorldClient {

	public static void main(String[] args) {
		  String nombre = "Diego";
		  String baseURI = "http://localhost:8080/RESTful_HelloWorld";
		  ClientConfig config = new DefaultClientConfig();
		  Client client = Client.create(config);
		  WebResource service = client.resource(baseURI);
		  // Get plain text
		  System.out.println(service.path("rest").path("hello/"+nombre).accept(MediaType.TEXT_PLAIN).get(String.class));
		  // Get XML
		  System.out.println(service.path("rest").path("hello/"+nombre).accept(MediaType.TEXT_XML).get(String.class));
		  // The HTML
		  System.out.println(service.path("rest").path("hello/"+nombre).accept(MediaType.TEXT_HTML).get(String.class));
		 }

}
