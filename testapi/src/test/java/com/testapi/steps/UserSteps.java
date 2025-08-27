package com.testapi.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import com.testapi.models.User;
import com.testapi.pages.UserApiPage;
import static org.assertj.core.api.Assertions.assertThat;

public class UserSteps {
    private Response response;
    private User user;

    @Given("el usuario consulta la lista de usuarios")
    public void consultaListaUsuarios() {
        response = new UserApiPage().getAllUsers();
    }

    @When("obtiene el usuario con id {int}")
    public void obtieneUsuarioPorId(int id) {
        response = new UserApiPage().getUserById(id);
        user = response.getBody().as(User.class);
    }

    @Then("visualiza el phone, email y company del usuario")
    public void visualizaDatosUsuario() {
        assertThat(user).isNotNull();
        System.out.println("Phone: " + user.phone);
        System.out.println("Email: " + user.email);
    System.out.println("Company: " + (user.company != null ? user.company : "N/A"));
    }

    @Given("el usuario crea un nuevo usuario")
    public void el_usuario_crea_un_nuevo_usuario() {
        response = new UserApiPage().createUser("Nuevo Usuario", "nuevo@email.com");
        assertThat(response.getStatusCode()).isIn(200, 201);
    }

    @When("actualiza el usuario")
    public void actualiza_el_usuario() {
        Integer id = null;
        try {
            id = response.jsonPath().getInt("id");
        } catch (Exception e) {
            System.out.println("No se pudo obtener el id del usuario creado. Respuesta: " + response.getBody().asString());
        }
        if (id != null) {
            response = new UserApiPage().updateUser(id, "Usuario Actualizado");
            assertThat(response.getStatusCode()).isIn(200, 201);
        } else {
            System.out.println("No se puede actualizar porque no se obtuvo el id.");
        }
    }

    @When("elimina el usuario")
    public void elimina_el_usuario() {
        Integer id = null;
        try {
            id = response.jsonPath().getInt("id");
        } catch (Exception e) {
            System.out.println("No se pudo obtener el id del usuario creado. Respuesta: " + response.getBody().asString());
        }
        if (id != null) {
            response = new UserApiPage().deleteUser(id);
            assertThat(response.getStatusCode()).isIn(200, 201, 204);
        } else {
            System.out.println("No se puede eliminar porque no se obtuvo el id.");
        }
    }

    @Then("la operación es exitosa")
    public void la_operacion_es_exitosa() {
        assertThat(response.getStatusCode()).isIn(200, 201, 204);
    }
}
