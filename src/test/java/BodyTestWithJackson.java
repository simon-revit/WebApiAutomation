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


    @Test
    public void returnCorrectLogin() throws IOException {

        HttpGet get = new HttpGet(BASE_URL + "/users/simon-revit");

        response = client.execute(get);

        unmarshall(response, User.class);

        User user = unmarshall(response, User.class);

        Assert.assertEquals( user.getLogin(), "simon-revit");
    }

    private User unmarshall(CloseableHttpResponse response, Class<User> clazz) throws IOException {

        String jsonBody = EntityUtils.toString(response.getEntity());

        // Test output
        System.out.println(jsonBody);
        // ignores the fails (like failing when assigning variables that haven't been declared)
        ObjectMapper objectMapper = new ObjectMapper();
        //.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET,false)
        //objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)

        return objectMapper.readValue(jsonBody, clazz);
    }
}

//  .disable(JsonGenerator.Feature.AUTO_CLOSE_TARGET)
//  .disable(JsonGenerator.Feature.AUTO_CLOSE_TARGET)
//  jsonBody.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET,false);