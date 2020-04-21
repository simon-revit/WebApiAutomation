import org.apache.http.Header;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ResponseHeaders extends BaseClass {

    @Test
    public void contentTypeIsJson() throws IOException {
        HttpGet get = new HttpGet(BASE_URL);
        response = client.execute(get);

        Header contentType = response.getEntity().getContentType();

        assertEquals( contentType.getName(), "content-type");
        assertEquals( contentType.getValue(), "application/json; charset=utf-8");

        ContentType conciseContentType = ContentType.getOrDefault(response.getEntity());

        assertEquals( conciseContentType.getMimeType(), "application/json");
    }

    @Test
    public void serverIsGithub() throws IOException {
        HttpGet get = new HttpGet(BASE_URL);
        response = client.execute(get);

        String headerValue = ResponseUtils.getHeader(response, "Server");

        assertEquals(headerValue,"GitHub.com");
    }

    @Test
    public void serverIsGithubJava8Way() throws IOException {
        HttpGet get = new HttpGet(BASE_URL);
        response = client.execute(get);

        String headerValue = ResponseUtils.getHeaderJava8Way(response, "Server");

        assertEquals(headerValue,"GitHub.com");
    }

    @Test
    public void xRateLimitIs60() throws IOException {
        HttpGet get = new HttpGet(BASE_URL);
        response = client.execute(get);

        String limitValue = ResponseUtils.getHeaderJava8Way(response, "X-Ratelimit-Limit");
        int limitValueAsInt = Integer.parseInt(limitValue);

        assertEquals(limitValueAsInt,60);
    }

    @Test
    public void eTagIsPresent() throws IOException {
        HttpGet get = new HttpGet(BASE_URL);
        response = client.execute(get);

        Boolean tagIsPresent = ResponseUtils.headerIsPresent(response, "ETag");

        assertTrue(tagIsPresent);
    }



}
