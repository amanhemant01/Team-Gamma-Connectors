package jfrog;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Scanner;

import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


public class CreateJFrogRepo {
	public boolean createJfrogrepository(String pageTitle) throws AuthenticationException, ClientProtocolException, IOException
	{
	CloseableHttpClient client = HttpClients.createDefault();
    HttpPut httpPut = new HttpPut("https://tiwari.jfrog.io/tiwari/api/repositories/"+pageTitle);
    String json;
    UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("admin", "AKCp5dK4mFbAe35QosdmHr7jLWm4EzF34dn8nr5pcr6tYPyQ6tMmCbcJ7ydkeTKH7w9fq9et8");
    json = "{\"rclass\":\"local\"}";
    StringEntity entity = new StringEntity(json);
    httpPut.addHeader(new BasicScheme().authenticate(credentials, httpPut, null));
    httpPut.setEntity(entity);
    httpPut.setHeader("Content-type", "application/json");
    CloseableHttpResponse response = client.execute(httpPut);
    int responseCode = response.getStatusLine().getStatusCode();
    System.out.println(responseCode);
    if(responseCode == HttpURLConnection.HTTP_OK) {
        client.close();
        return true;
    }
    else {
        client.close();
        return false;
    }
	}
	public static void main(String[] args) throws AuthenticationException, ClientProtocolException, IOException 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Input Project Name:");
		String Str = sc.nextLine();
		System.out.println(new CreateJFrogRepo().createJfrogrepository(Str));
		sc.close();
	}
	

}
