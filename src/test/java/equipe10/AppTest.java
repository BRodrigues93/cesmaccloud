package equipe10;

import org.junit.Ignore;
import org.junit.Test;

/**
 * @author jooby generator
 */
public class AppTest extends BaseTest {

  @Test
  @Ignore
  public void index() throws Exception {
    server.get("/")
        .expect(200)
        .header("Content-Type", "text/html;charset=UTF-8");
  }

  @Test
  public void insert() throws Exception {
    String json = "{\n" +
        "    \"name\": \"Heiner\",\n" +
        "    \"idade\": \"20\"\n" +
    "}";

    server.post("/todos")
        .body(json, "application/json")
        .expect(200);
  }

}
