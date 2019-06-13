package confluence;

import java.util.Scanner;
import java.io.IOException;
import java.net.HttpURLConnection;

import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class CreateConfluencePage {
	
	public boolean createPageConfluence(String pageTitle, String confluenceSpace) throws AuthenticationException, ClientProtocolException, IOException
	{
	CloseableHttpClient client = HttpClients.createDefault();
    HttpPost httpPost = new HttpPost("https://amanhemant01.atlassian.net/wiki/rest/api/content");
    String json;
    UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("ashokhemant123@gmail.com", "Mabdr8jbiLvPsguCa9v449F7");
    json = "{\"type\":\"page\", \"title\":\""+pageTitle+"\", \"space\":{\"key\":\""+confluenceSpace+"\"}}";
    StringEntity entity = new StringEntity(json);
    httpPost.addHeader(new BasicScheme().authenticate(credentials, httpPost, null));
    httpPost.setEntity(entity);
    httpPost.setHeader("Accept", "application/json");
    httpPost.setHeader("Content-type", "application/json");
    CloseableHttpResponse response = client.execute(httpPost);
    int responseCode = response.getStatusLine().getStatusCode();
    System.out.println(responseCode);
    if(responseCode == HttpURLConnection.HTTP_OK) {
        client.close();
        return true;
    }
    else 
    {
        client.close();
        return false;
    }
	}
	
	public static void main(String[] args) throws AuthenticationException, ClientProtocolException, IOException 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Input Page Name:");
		String Str = sc.nextLine();
		System.out.println(new CreateConfluencePage().createPageConfluence(Str, "HEMANT"));
		sc.close();
	}
}
