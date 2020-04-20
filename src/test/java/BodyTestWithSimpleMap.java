import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static entities.User.ID;
import static entities.User.LOGIN;

public class BodyTestWithSimpleMap extends BaseClass {

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

    //DataProvider is not necessary here, and put straight into tests.
    @DataProvider
    private Object [][] endpoints() {
        return new Object[][] {
                {"/users/simon-revit"}
        };
    }

    @Test (dataProvider = "endpoints")
    public void returnsCorrectLogin(String ends) throws IOException {
        HttpGet get = new HttpGet(BASE_URL + ends);
        response = client.execute(get);

        String jsonBody = EntityUtils.toString( response.getEntity() );

        JSONObject jsonObject = new JSONObject((jsonBody));

        String loginValue = (String) getValueFor(jsonObject, LOGIN);

        Assert.assertEquals(loginValue, "simon-revit");
    }

    @Test (dataProvider = "endpoints")
    public void returnsCorrectId(String ends) throws IOException {
        HttpGet get = new HttpGet(BASE_URL + ends);
        response = client.execute(get);

        String jsonBody = EntityUtils.toString( response.getEntity() );
        JSONObject jsonObject = new JSONObject((jsonBody));
        Integer loginValue = (Integer) getValueFor(jsonObject, ID);

        Assert.assertEquals(loginValue, Integer.valueOf(62121680));
    }

    //Temp Method for learning (Ideally, this would be in a try catch block.
    private Object getValueFor(JSONObject jsonObject, String key) {
        return jsonObject.get(key);
    }

}
