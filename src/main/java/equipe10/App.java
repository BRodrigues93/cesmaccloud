package equipe10;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import org.jooby.Jooby;
import org.jooby.MediaType;
import org.jooby.Results;
import org.jooby.json.Jackson;

/**
 * @author jooby generator
 */
public class App extends Jooby {

    private ArrayList<User> users = new ArrayList<>();

    {
        use(new Jackson());

        get("/todos", req -> {
            return "[{name: 'asd'}, {name: 'qwe'}]";
        });

        get("/todos/:id", req -> {
            return "id";
        });

        get("/todos/searchbyname/:name", req -> {
            String name = req.param("name").value();
            String message = "here";

            int statusCode = 404;

            if (users.size() > 0) {
                if (!name.isEmpty()) {
                    for (User user : users) {
                        if (user.getName().contains(name)) {
                            statusCode = 200;
                            message = "Nome do usuário encontrado: " + user.getName()
                                    + ", idade:" + user.getIdade();
                        } else {
                            message = "Nome não encontrado!";
                        }
                    }
                } else {
                    statusCode = 400;
                    message = "Parametro de busca, vazio!";
                }
            } else {
                statusCode = 204;
                message = "A lista esta vazia!";
            }

            return Results.with(message, statusCode).type("text/plain");
        });

        post("/todos", (req, rsp) -> {
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = req.body().value();
            User user = mapper.readValue(jsonInString, User.class);

                if (user.getName().equals("")) {
                    rsp.send("Nome não pode ser vazio");
                } else if (user.getIdade() == 0) {
                    rsp.send("Idade não pode ser vazio");
                } else {
                    this.users.add(user);
                }

                rsp.send("Adicionado com sucesso");
            }
        )
        .consumes(MediaType.json)
        .name("Insert an User");

        delete("/todos/:id", req -> {
            return "todoId";
        });
    }

    public static void main(final String[] args) throws Exception {
        run(App::new, args);
    }

}
