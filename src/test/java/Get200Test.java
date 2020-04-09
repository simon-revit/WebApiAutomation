import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.http.HttpClient;

import static org.testng.Assert.*;

public class Get200Test extends BaseClass {


    public static final int OK_STATUS_CODE = 200;
    public static final int NOTFOUND_STATUS_CODE = 404;
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
    public void baseUrlReturns200() throws IOException {
        HttpGet get = new HttpGet(BASE_URL);
        response = client.execute(get);

        int actualStatus = response.getStatusLine().getStatusCode();

        assertEquals( actualStatus, OK_STATUS_CODE);
    }

    @Test
    public void issuesUrlReturns200() throws IOException {
        HttpGet get = new HttpGet(BASE_URL + "/issues");
        response = client.execute(get);

        int actualStatus = response.getStatusLine().getStatusCode();

        assertEquals( actualStatus, NOTFOUND_STATUS_CODE);
    }

    @Test
    public void emojisUrlReturns200() throws IOException {
        HttpGet get = new HttpGet(BASE_URL + "/emojis");
        response = client.execute(get);

        int actualStatus = response.getStatusLine().getStatusCode();

        assertEquals( actualStatus, OK_STATUS_CODE);
    }


    @Test
    public void rateLimitUrlReturns200() throws IOException {
        HttpGet get = new HttpGet(BASE_URL + "/rate_limit");
        response = client.execute(get);

        int actualStatus = response.getStatusLine().getStatusCode();

        assertEquals( actualStatus, OK_STATUS_CODE);
    }

    @Test
    public void repoSearchUrlReturns200() throws IOException {
        HttpGet get = new HttpGet(BASE_URL + "/search/repositories?q=java");
        response = client.execute(get);

        int actualStatus = response.getStatusLine().getStatusCode();

        assertEquals( actualStatus, OK_STATUS_CODE);
    }


}
