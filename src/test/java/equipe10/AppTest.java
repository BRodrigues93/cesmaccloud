package equipe10;

import org.junit.Ignore;
import org.junit.Test;
import junit.framework.TestCase;
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
  
  @Test
  public void get() throws Exception{
      String json = "";
      server.get("/todos/:id").body(json, "applicarion/json").expect(400);
  }


  @Test
  public void delete() throws Exception {
    String json = "{\n" +
        "    \"id\": \"1\"\n" +
    "}";

    server.delete("/todos/delete")
        .body(json, "application/json")
        .expect(200);
  }

}
