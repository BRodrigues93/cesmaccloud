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
            return users;
        });

        /**
         * @author: Ibirajara Barrel Junior
         * Método para retornar o usuário pelo id.
         * Percorre a lista para validar o id do usuário. 
         * Consequentemente, inserção do usuário o id deve ser o maior da lista + 1
         */
        
        get("/todos/:id", (req) -> {
            Integer id = null;
            usuario = null;
            String message = "";
            String result = "";
            int statusCode = 404;
            try {
                id = Integer.parseInt(req.param("id").value());
                for (User user : users) {
                    if (user.getId() == id) {
                        statusCode = 200;
                        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
                        this.usuario = user;
                        result = ow.writeValueAsString(this.usuario);
                    } else {
                        message = "Nome não encontrado!";
                    }
                }
            } catch (NumberFormatException e) {
                return Results.with("O id deve ser um número").status(400).type("text/plain");
            }
            
            if(this.usuario != null){
               return Results.with(result).status(statusCode).type("text/plain");

            }else{
                    return Results.with("Usuário não encontrado!").status(400).type("text/plain");
                    
                    
                }
        });

        get("/todos/searchbyname/:name", req -> {
            String name = req.param("name").value();

            String message = "";
            int statusCode = 404;

            if (users.size() > 0) {
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
                message = "A lista esta vazia!";
            }

            return Results.with(message).status(statusCode).type("text/plain");
        });

        post("/todos", req -> {
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = req.body().value();
            User user = mapper.readValue(jsonInString, User.class);

            String message = "Inserido com sucesso!";
            int statusCode = 400;

                if (user.getName().equals("")) {
                    message = "Nome não pode ser vazio.";
                } else if (user.getIdade() == 0) {
                    message = "Idade não pode ser vazio.";
                } else {
                    statusCode = 200;
                    user.setId(this.users.size()+1);
                    this.users.add(user);
                }

                return Results.with(message).status(statusCode).type("text/plain");
            }

        )
        .consumes(MediaType.json)
        .name("Insert an User");

        delete("/todos/delete/:id", req -> {
            int id = req.param("id").value();

            String message = "";
            int statusCode = 408;
	    boolean sit = true;

            if (id.equals("")) {
        		message = "O ID não pode ser vazio.";
		    } else
		    	if (users.size() > 0) {
		            for (User user : users) {
		                if (user.getId().equals(id)) { 
		                    sit = false;
		                    statusCode = 200;

		                    user.remove(this.user);

		                    message = "Usuario foi deletado com sucesso!";
				    
		                }
		            }
		            if (sit){
		            	message = "Usuário não existe!";
		            }
		        } else {
		            statusCode = 400;
		            message = "Nenhum usuário cadastrado!";
		        }

        	return Results.with(message).status(statusCode).type("text/plain");
		});
    }

    public static void main(final String[] args) throws Exception {
        run(App::new, args);
    }
    
     put("/todos/updatename/:name", req -> {
            String name = req.param("name").value();
            String message = "";
            int statusCode = 404;

            if (users.size() > 0) {
                for (User user : users) {
                    if (user.getName().contains(name)) {
                        statusCode = 200;
                        message = "Nome : " + user.getName() ;
                        if (users.getId().getName() == userUpdate.getName()){
                        	users.put(user.getName(), user);
                    } else {
                        message = "Não encontrado!";
                    }
                }
             }
            } else {
                statusCode = 400;
                message = "A lista esta vazia!";
            }

            return Results.with(message).status(statusCode).type("text/plain");
        });
    

}
