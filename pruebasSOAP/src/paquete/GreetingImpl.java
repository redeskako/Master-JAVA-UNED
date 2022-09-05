package paquete;
import javax.jws.WebService;

@WebService(endpointInterface = "paquete.Greeting")

public class GreetingImpl implements Greeting {
@Override
public String sayHello(String name) {

return "Hello, Welcom to jax-ws " + name;

}
}