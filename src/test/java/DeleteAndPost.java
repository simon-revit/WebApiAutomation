import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Base64;



public class DeleteAndPost extends BaseClass {

    @Test
    public void deleteIsSuccessful() throws IOException {

        // NB: crap-repo was created so that it can be deleted
        HttpDelete request = new HttpDelete(BASE_URL + "/repos/simon-revit/fucked-repo");

        request.setHeader(HttpHeaders.AUTHORIZATION, "token " + "f5da2e8fe6b94e27819e13113fa3e93dcd4c53d6");

        response = client.execute(request);
        int actualStatusCode = response.getStatusLine().getStatusCode();

        Assert.assertEquals(actualStatusCode, NO_CONTENT_STATUS_CODE);
    }

    @Test
    public void createRepoReturns201() throws IOException {
        // Create a HttpPost request with a valid Endpoint
        HttpPost request = new HttpPost(BASE_URL + "/user/repos");

        // Set the Basic Auth Header
        String auth = "simon-revit" + ":" + "8a4108955a004c5fffc9e5b4180b0c8d02647e77";

        // Turn the auth strings into a base64 encoded string
        String encodedString = Base64.getEncoder().encodeToString(auth.getBytes());
        System.out.println(encodedString);  // Testing the string

        // Set the Header with Basic Authorization
        request.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + encodedString);

        // Define the Json to Post
        String json = "{\"name\": \"fucked-repo\"}";
        // Set Entity (String, Content-Type) -> filling request with some content
        request.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON) );
        // Send request
        response = client.execute(request);
        //Get Status Code
        int actualStatusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(actualStatusCode, CREATED_STATUS_CODE);

    }
}
