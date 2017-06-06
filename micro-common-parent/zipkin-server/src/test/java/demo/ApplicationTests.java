package demo;

import com.zipkin.boot.ZipkinApplication;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ZipkinApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port=0")
public class ApplicationTests {

    @Value("${local.server.port}")
    private int port = 0;

//     public static void main(String[] args) throws ClientProtocolException, IOException {
//         HttpClientBuilder builder = HttpClientBuilder.create();
// //		HttpPost post = new HttpPost("http://localhost:8088/refresh");
//         HttpPost post = new HttpPost("http://localhost:7077/bus/refresh");
//
//         CloseableHttpResponse response = builder.build().execute(post);
//         System.out.println(EntityUtils.toString(response.getEntity()));
//     }
//
//     @Test
//     public void configurationAvailable() {
//         @SuppressWarnings("rawtypes")
//         ResponseEntity<Map> entity = new TestRestTemplate().getForEntity(
//                 "http://localhost:" + port + "/app/cloud", Map.class);
//         assertEquals(HttpStatus.OK, entity.getStatusCode());
//     }
//
//     @Test
//     public void envPostAvailable() {
//         MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
//         @SuppressWarnings("rawtypes")
//         ResponseEntity<Map> entity = new TestRestTemplate().postForEntity(
//                 "http://localhost:" + port + "/admin/env", form, Map.class);
//         assertEquals(HttpStatus.OK, entity.getStatusCode());
//     }
}
