package equipe10;

import java.util.ArrayList;
import org.jooby.Jooby;
import org.jooby.Results;

/**
 * @author jooby generator
 */
public class App extends Jooby {

    private ArrayList<User> users = new ArrayList<>();

    {
        get("/todos", req -> {
            return "[{name: 'asd'}, {name: 'qwe'}]";
        });
    }

    {
        get("/todos/:id", req -> {
            return "id";
        });
    }

    {
        get("/todos/searchbyname/:name", req -> {
            String name = req.param("name").value();
            String message = "here";

            int statusCode = 404;

            if (users.size() > 0) {
                if (!name.isEmpty()) {
                    for (User user : users) {
                        if (user.getName().contains(name)) {
                            statusCode = 200;
                            message = "Nome do usuário encontrado: " + user.getName();
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
    }

    {
        post("/todos", req -> {
            User user = req.body().to(User.class);

            if (user.getName().equals("")) {
                return "Nome não pode ser vazio";
            } else if (user.getIdade() != 0) {
                return "Idade não pode ser vazio";
            } else {
                users.add(user);
            }

            return "Adicionado com sucesso";

        });
    }

    {
        delete("/todos/:id", req -> {
            return "todoId";
        });
    }

    public static void main(final String[] args) throws Exception {
        run(App::new, args);
    }

}
