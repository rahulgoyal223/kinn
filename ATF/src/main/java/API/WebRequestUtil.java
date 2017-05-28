package API;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.ClientResponse;
import org.testng.Reporter;

public class WebRequestUtil {	

	/**
     * @param
     * @return
     * @throws Exception
     */
	public String requestBuilderTypePostJson(String authToken, String str, String endPoint) throws Exception {
        System.out.println("EndPoint: " + endPoint);
        //TO
        Client client = ClientBuilder.newClient();
        client.getSslContext();

        WebTarget webTarget = client.target(endPoint);

        Invocation.Builder invocationBuilder = webTarget.request();
        invocationBuilder.accept(MediaType.APPLICATION_JSON);
        invocationBuilder.header("token", authToken);
        invocationBuilder.header("cookie", "EHRTOKEN=" + authToken);

        //Response response = invocationBuilder.put(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
        Response response = invocationBuilder.post(Entity.entity(str, MediaType.APPLICATION_JSON));
        if (response.getStatus() != 200) {
            throw new Exception("The web request did not succeed! POST ENDPOINT " + endPoint + " .Response status " + String.valueOf(response.getStatus()));
        }

        String s = response.readEntity(String.class);
        return s;
    }
	
	/**
     * @param
     * @return
     * @throws Exception
     */
    public String requestBuilderTypeGetJson(String authToken, String sEndPoint) throws Exception {
        Client client = ClientBuilder.newClient();
        client.getSslContext();
        System.out.println("EndPoint " + sEndPoint);
        WebTarget webTarget = client.target(sEndPoint);
        webTarget.property(ClientProperties.FOLLOW_REDIRECTS, true);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        invocationBuilder.header("token", authToken);
        Response response = invocationBuilder.get();
        System.out.println(String.valueOf(response.getStatus()));

        //validating success
        if (response.getStatus() != 200) {
            throw new Exception("Endpoint get request " + sEndPoint + " failed with status " + String.valueOf(response.getStatus()));
        }

        //from every response take out the // before serialization, adding more manipulation to the JSON returned
        String s = response.readEntity(String.class);
        return s;
    }
	
    /**
     * @param
     * @return
     * @throws Exception
     */
    public String requestBuilderTypeDeleteJson(String authToken, String endPoint) throws Exception {
        System.out.println("EndPoint: " + endPoint);
        //TO
        Client client = ClientBuilder.newClient();
        client.getSslContext();

        WebTarget webTarget = client.target(endPoint);

        Invocation.Builder invocationBuilder = webTarget.request();
        invocationBuilder.accept(MediaType.APPLICATION_JSON);
        invocationBuilder.header("token", authToken);
        invocationBuilder.header("cookie", "EHRTOKEN=" + authToken);

        //Response response = invocationBuilder.put(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
        Response response = invocationBuilder.delete();
        if (response.getStatus() != 200) {
            throw new Exception("The web request did not succeed! POST ENDPOINT " + endPoint + " .Response status " + String.valueOf(response.getStatus()));
        }

        String s = response.readEntity(String.class);
        return s;
    }  
    
    /**
     * @param
     * @return
     * @throws Exception
     */
    public String requestBuilderTypePutJson(String authToken, String str, String endPoint) throws Exception {
        System.out.println("EndPoint: " + endPoint);
        //TO
        Client client = ClientBuilder.newClient();
        client.getSslContext();

        WebTarget webTarget = client.target(endPoint);

        Invocation.Builder invocationBuilder = webTarget.request();
        invocationBuilder.accept(MediaType.APPLICATION_JSON);
        invocationBuilder.header("token", authToken);
        invocationBuilder.header("cookie", "EHRTOKEN=" + authToken);

        Response response = invocationBuilder.put(Entity.entity(str, MediaType.APPLICATION_FORM_URLENCODED));
        if (response.getStatus() != 200) {
            throw new Exception("The web request did not succeed! POST ENDPOINT " + endPoint + " .Response status " + String.valueOf(response.getStatus()));
        }

        String s = response.readEntity(String.class);
        return s;
    }  
}
