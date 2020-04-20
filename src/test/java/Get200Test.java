import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.http.HttpClient;

import static org.testng.Assert.*;

public class Get200Test extends BaseClass {

    @DataProvider
    private Object[][] endpoints() {
            return new Object[][] {
                    //{"/issues"},
                    {"/emojis"},
                    {"/rate_limit"},
                    {"/search/repositories?q=java"}
        };
    }

    @Test (dataProvider = "endpoints")
    public void baseUrlReturns200(String endpoint) throws IOException {
        HttpGet get = new HttpGet(BASE_URL + endpoint);
        response = client.execute(get);

        //Create breakpoint here to collect response variables
        int actualStatus = response.getStatusLine().getStatusCode();

        assertEquals( actualStatus, OK_STATUS_CODE);


    }


}
