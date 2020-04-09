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

import static org.testng.Assert.*;

public class Get401Test extends BaseClass {

    public static final int OK_STATUS_CODE = 200;
    public static final int NOTFOUND_STATUS_CODE = 404;
    public static final int NON_AUTHORISED_STATUS_CODE = 401;
    CloseableHttpClient client;
    CloseableHttpResponse response;

    @BeforeMethod
    public void setup() {
        client = HttpClientBuilder.create().build();
    }

    @AfterMethod
    public void closeResources() throws IOException {
        client.close();
        response.close();
    }

    @DataProvider
    private Object[][] endpoints() {
        return new Object[][] {
                {"/authorizations"},
                {"/user/followers"},
                {"/user/following/"},
                {"/notifications"}
        };
    }

    @Test (dataProvider = "endpoints")
    public void authorisationUrlReturns401(String ends) throws IOException {
        HttpGet get = new HttpGet(BASE_URL + ends);
        response = client.execute(get);

        int actualStatus = response.getStatusLine().getStatusCode();

        assertEquals( actualStatus, NON_AUTHORISED_STATUS_CODE);
    }


}
