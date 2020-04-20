import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.NotFound;
import entities.RateLimit;
import entities.User;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class BodyTestWithJackson extends BaseClass {




    @Test
    public void returnCorrectLogin() throws IOException {

        HttpGet get = new HttpGet(BASE_URL + "/users/simon-revit");
        response = client.execute(get);
        User user = ResponseUtils.unmarshall(response, User.class);
        Assert.assertEquals( user.getLogin(), "simon-revit");
    }

    @Test
    public void returnCorrectId() throws IOException {

        HttpGet get = new HttpGet(BASE_URL + "/users/simon-revit");
        response = client.execute(get);
        User user = ResponseUtils.unmarshallGeneric(response, User.class);
        Assert.assertEquals( user.getId(), 62121680);
    }

    @Test
    public void notFoundMessage() throws IOException {

        HttpGet get = new HttpGet(BASE_URL + "/nonexisting/endpoint");
        response = client.execute(get);
        NotFound notFoundMessage = ResponseUtils.unmarshallGeneric(response, NotFound.class);
        Assert.assertEquals( notFoundMessage.getMessage(), "Not Found");
    }

    @Test
    public void correctRateLimit() throws IOException {

        HttpGet get = new HttpGet(BASE_URL + "/rate_limit");
        response = client.execute(get);
        RateLimit rateLimits = ResponseUtils.unmarshallGeneric(response, RateLimit.class);
        Assert.assertEquals( rateLimits.getCoreLimit(), 60);
        Assert.assertEquals( rateLimits.getSearchLimit(), "10");
    }


}
