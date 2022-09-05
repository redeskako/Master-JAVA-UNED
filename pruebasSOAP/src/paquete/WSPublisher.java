package paquete;

import javax.xml.ws.Endpoint;

public class WSPublisher {

public static void main(String[] args) {

Endpoint.publish("http://localhost:8080/WS/Greeting",new GreetingImpl());

}

}