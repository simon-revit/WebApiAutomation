import org.apache.http.client.methods.HttpGet;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.*;

public class Get404Test extends BaseClass {

    @DataProvider Object[][] nonExistentEndPoints() {
        return new Object[][] {
                {"/thisFolderShouldNotExist"},
                {"/blah"}

        };

    }


    @Test (dataProvider = "nonExistentEndPoints")
    public void nonExistingUrlReturns404(String ends) throws IOException {
        HttpGet get = new HttpGet(BASE_URL + ends);
        response = client.execute(get);

        int actualStatus = response.getStatusLine().getStatusCode();

        assertEquals( actualStatus, NOT_FOUND_STATUS_CODE);
    }
}
