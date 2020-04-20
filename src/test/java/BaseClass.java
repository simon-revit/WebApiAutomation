import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;

public class BaseClass {
    protected static final String BASE_URL = "https://api.github.com";
    protected static final int OK_STATUS_CODE = 200;
    protected static final int NOT_FOUND_STATUS_CODE = 404;
    protected static final int NON_AUTHORISED_STATUS_CODE = 401;

    protected CloseableHttpClient client;
    protected CloseableHttpResponse response;

    @BeforeMethod
    public void setup() {
        client = HttpClientBuilder.create().build();
    }

    @AfterMethod
    public void closeResources() throws IOException {
        client.close();
        response.close();
    }

}
