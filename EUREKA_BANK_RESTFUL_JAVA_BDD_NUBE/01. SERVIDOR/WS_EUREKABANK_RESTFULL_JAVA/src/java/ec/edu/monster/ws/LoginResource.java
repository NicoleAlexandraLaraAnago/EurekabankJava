/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.ws;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("login")
public class LoginResource {

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@FormParam("usuario") String usuario, @FormParam("contrasena") String contrasena) {
        if ("MONSTER".equals(usuario) && "MONSTER9".equals(contrasena)) {
            return Response.ok("{\"estado\":1, \"mensaje\":\"Acceso concedido\"}").build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("{\"estado\":0, \"mensaje\":\"Credenciales incorrectas\"}")
                    .build();
        }
    }
}