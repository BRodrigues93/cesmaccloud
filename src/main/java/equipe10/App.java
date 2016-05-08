 package equipe10;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
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
        * O usuario informa o id do usuario a ser buscado.
        * Percorre a lista para validar o id retornando o usuario ou uma mensagem caso
        * nao encontre.
        * Consequentemente, inserção do usuário o id deve ser o maior da lista + 1
        * V. 2.0
        */
       get("/todos/:id", (req) -> {
            Integer id = null;
            User usuario = null;
            String message = "";
            String result = "";
            int statusCode = 404;
            System.out.println("Agora no programa..................");

            try {
                id = Integer.parseInt(req.param("id").value());
                for (User user : users) {
                    if (user.getId() == id) {
                        statusCode = 200;
                        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
                        usuario = user;
                        result = ow.writeValueAsString(usuario);
                    } else {
                        message = "Nome não encontrado!";
                    }
                }
            } catch (NumberFormatException e) {
                return Results.with("O id deve ser um número").status(400).type("text/plain");
            }
            
            if(usuario != null){
               return Results.with(result).status(statusCode).type("text/plain");

            }else{
                    return Results.with("Usuário não encontrado!").status(400).type("text/plain");
                    
                    
                }
        });
	/**
        * @author: Khwesten Heiner 
        * Método para procurar nome do usuario e mostrar em tela seu nome e idade.
        * O usuario informa o nome a ser buscado.
        * Percorre a lista para validar o nome retornando o Nome e idade ou uma mensagem caso
        * nao encontre.
        */
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

        
        
        
        /**
        * @author: Wylianne da Silva Costa
        * Método para deletar o usuário pelo id.
        * O usuario informa o id do usuario a ser buscado.
        * Percorre a lista para validar o id retornando o usuario ou uma mensagem caso nao encontre.
        * V. 2.0
        */
        delete("/todos/delete/:id", (req) -> {
		Integer id = Integer.parseInt(req.param("id").value());
		String message = "";
		int statusCode = 408;
		boolean sit = true;
		if (id.equals("")) {
			message = "O ID não pode ser vazio.";
		}else{
			if (users.size() > 0) {
				for (User user : users) {
					if (user.getId() == id) { 
					    sit: false;
					    statusCode = 200;
					    users.remove(user);
					    message = "Usuario foi deletado com sucesso!";
					}
				}
				if (sit){
					statusCode = 200;
					message = "Usuário não existe!";
				}
		        } else {
				statusCode = 400;
				message = "Nenhum usuário cadastrado!";
		        }
		}
        	return Results.with(message).status(statusCode).type("text/plain");
	});
        
    	 /**
         * @author: Bruno Rodrigues Barbosa
         * Método para alterar o nome do usuário pelo nome.
         * Percorre a lista para pesquisar o nome do usuário; 
         * Após encontrar, poderá fazer a alteração digitando um "novo nome";
         * para atualizar verifica o id do mesmo;
         * atualiza o nome e o insere no id do nome selecionado;
         */
     put("/todos/updatename/:name", req -> {
                   User userUpdate = (User) req;
                   String message = "Usuario nao encontrado";
                   int statusCode = 404;
                   if (users.size() > 0) {
                       for (User user : users) {
                           if (user.getId() == userUpdate.getId() ) {
                               statusCode = 200;
                               message = "Usuario " + user.getName() + " atualizado!" ;
                               users.set(users.indexOf(user), userUpdate);
                       }
                    }
                   } else {
                       statusCode = 400;
                       message = "A lista esta vazia!";
                   }

                   return Results.with(message).status(statusCode).type("text/plain");
               });         
        
    }
    
    public static void main(final String[] args) throws Exception {
        run(App::new, args);
    }
	/**
        * @author: Khwesten Heiner 
        * Adicionar nome e idade.
        */
    public void addUsertoList(){
        System.out.println("no app para popular...");
        User u = new User();
        u.setId(1);
        u.setName("Nome de teste");
        u.setIdade(29);
        this.users.add(u);
        System.out.println("populou...");
        
    }

}
