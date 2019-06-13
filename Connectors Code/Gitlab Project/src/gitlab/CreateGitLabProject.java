package gitlab;

import java.util.Scanner;
import java.io.IOException;
import java.net.HttpURLConnection;

import org.apache.http.auth.AuthenticationException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class CreateGitLabProject {
	public boolean createProjectGitlab(String pageTitle) throws AuthenticationException, ClientProtocolException, IOException
	{
	CloseableHttpClient client = HttpClients.createDefault();
    HttpPost httpPost = new HttpPost("https://gitlab.com/api/v4/projects?private_token=oedWzaVDE7hym2DTFyxZ");
    
    
    String urlParameters = "name="+pageTitle;
    StringEntity entity = new StringEntity(urlParameters);
   
    httpPost.setEntity(entity);
    
    httpPost.setHeader("Accept", "application/json");
    httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
    httpPost.setHeader("charset", "utf-8");
    httpPost.setHeader("PRIVATE-TOKEN", "oedWzaVDE7hym2DTFyxZ");
    CloseableHttpResponse response = client.execute(httpPost);
    
    int responseCode = response.getStatusLine().getStatusCode();
    System.out.println(responseCode);
    if(responseCode == HttpURLConnection.HTTP_CREATED) {
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
    System.out.println(new CreateGitLabProject().createProjectGitlab(Str));
    sc.close();
}

}
