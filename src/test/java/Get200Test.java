import org.apache.http.client.methods.HttpGet;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

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
