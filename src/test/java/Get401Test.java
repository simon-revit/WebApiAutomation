import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.*;

public class Get401Test extends BaseClass {

    public static final int OK_STATUS_CODE = 200;
    public static final int NOTFOUND_STATUS_CODE = 404;
    public static final int NON_AUTHORISED_STATUS_CODE = 401;
    CloseableHttpClient client;
    CloseableHttpResponse response;
    //HttpClient lcients;

    @BeforeMethod
    public void setup() {
        client = HttpClientBuilder.create().build();
    }

    @AfterMethod
    public void closeResources() throws IOException {
        client.close();
        response.close();
    }

    @Test
    public void authorisationUrlReturns401() throws IOException {
        HttpGet get = new HttpGet(BASE_URL + "/authorizations");
        response = client.execute(get);

        int actualStatus = response.getStatusLine().getStatusCode();

        assertEquals( actualStatus, NON_AUTHORISED_STATUS_CODE);
    }

    @Test
    public void followersUrlReturns401() throws IOException {
        HttpGet get = new HttpGet(BASE_URL + "/user/followers");
        response = client.execute(get);

        int actualStatus = response.getStatusLine().getStatusCode();

        assertEquals( actualStatus, NON_AUTHORISED_STATUS_CODE);
    }

    @Test
    public void followingUrlReturns401() throws IOException {
        HttpGet get = new HttpGet(BASE_URL + "/user/following/");
        response = client.execute(get);

        int actualStatus = response.getStatusLine().getStatusCode();

        assertEquals( actualStatus, NON_AUTHORISED_STATUS_CODE);
    }

    @Test
    public void notificationsUrlReturns401() throws IOException {
        HttpGet get = new HttpGet(BASE_URL + "/notifications");
        response = client.execute(get);

        int actualStatus = response.getStatusLine().getStatusCode();

        assertEquals( actualStatus, NON_AUTHORISED_STATUS_CODE);
    }

}
