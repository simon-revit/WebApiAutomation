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

    @DataProvider
    private Object[][] endpoints() {
        return new Object[][] {
                {"/authorizations"},
                {"/user/followers"},
                //{"/user/following/"},
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
