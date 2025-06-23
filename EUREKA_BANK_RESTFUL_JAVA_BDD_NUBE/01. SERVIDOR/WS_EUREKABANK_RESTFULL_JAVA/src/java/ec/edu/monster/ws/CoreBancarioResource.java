package ec.edu.monster.ws;

import ec.edu.monster.db.AccesoDB;
import ec.edu.monster.modelo.Movimiento;
import ec.edu.monster.servicio.EurekaService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("coreBancario")
public class CoreBancarioResource {

    @Context
    private UriInfo context;

    public CoreBancarioResource() {
    }

    // ✅ 1. CONSULTAR MOVIMIENTOS
    @GET
    @Path("/movimientos/{cuenta}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMovimientos(@PathParam("cuenta") String cuenta) {
        try {
            EurekaService service = new EurekaService();
            List<Movimiento> lista = service.leerMovimientos(cuenta);

            if (lista.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"error\":\"No se encontraron movimientos para la cuenta: " + cuenta + "\"}")
                        .build();
            }

            String movimientosJSON = service.convertirMovimientosAJSON(lista);
            return Response.ok(movimientosJSON).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\":\"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    // ✅ 2. DEPOSITO
    @POST
    @Path("/deposito")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarDeposito(@FormParam("cuenta") String cuenta,
                                      @FormParam("importe") double importe) {
        String codEmp = "0001";
        try {
            EurekaService service = new EurekaService();
            service.registrarDeposito(cuenta, importe, codEmp);
            return Response.ok("{\"estado\":1, \"mensaje\":\"Depósito registrado\"}").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"estado\":0, \"error\":\"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    // ✅ 3. RETIRO
    @POST
    @Path("/retiro")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarRetiro(@FormParam("cuenta") String cuenta,
                                    @FormParam("importe") double importe) {
        String codEmp = "0001";
        try {
            EurekaService service = new EurekaService();
            service.registrarRetiro(cuenta, importe, codEmp);
            return Response.ok("{\"estado\":1, \"mensaje\":\"Retiro registrado\"}").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"estado\":0, \"error\":\"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    // ✅ 4. TRANSFERENCIA
    @POST
    @Path("/transferencia")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarTransferencia(@FormParam("cuentaOrigen") String cuentaOrigen,
                                           @FormParam("cuentaDestino") String cuentaDestino,
                                           @FormParam("importe") double importe) {
        String codEmp = "0001";
        try {
            EurekaService service = new EurekaService();
            service.registrarTransferencia(cuentaOrigen, cuentaDestino, importe, codEmp);
            return Response.ok("{\"estado\":1, \"mensaje\":\"Transferencia registrada\"}").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"estado\":0, \"error\":\"" + e.getMessage() + "\"}")
                    .build();
        }
    }
    
    @GET
    @Path("/cuenta/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerCuenta(@PathParam("id") String id) {
        try (Connection cn = AccesoDB.getConnection()) {
            String sql = "SELECT chr_cuencodigo, dec_cuensaldo FROM cuenta WHERE chr_cuencodigo = ?";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, id);
            ResultSet rs = pstm.executeQuery();

            if (!rs.next()) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"error\":\"Cuenta no encontrada\"}")
                        .build();
            }

            String cuenta = rs.getString("chr_cuencodigo");
            double saldo = rs.getDouble("dec_cuensaldo");

            String json = String.format(java.util.Locale.US, "{\"cuentaId\":\"%s\", \"saldo\":%.2f}", cuenta, saldo);
            return Response.ok(json).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\":\"" + e.getMessage() + "\"}")
                    .build();
        }
    }
}
