import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
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

        User user = unmarshall(response, User.class);

        Assert.assertEquals( user.getLogin(), "simon-revit");
    }

    @Test
    public void returnCorrectId() throws IOException {

        HttpGet get = new HttpGet(BASE_URL + "/users/simon-revit");

        response = client.execute(get);

        User user = unmarshall(response, User.class);

        Assert.assertEquals( user.getId(), 62121680);
    }


    private User unmarshall(CloseableHttpResponse response, Class<User> clazz) throws IOException {

        String jsonBody = EntityUtils.toString(response.getEntity());

        ObjectMapper objectMapper = new ObjectMapper();
        //.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET,false)
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        return objectMapper.readValue(jsonBody, clazz);
    }
}
