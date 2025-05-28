package ec.edu.eureka_bank_climov_restful.api;

import java.util.List;

import ec.edu.eureka_bank_climov_restful.model.Cuenta;
import ec.edu.eureka_bank_climov_restful.model.LoginRequest;
import ec.edu.eureka_bank_climov_restful.model.Movimiento;
import ec.edu.eureka_bank_climov_restful.model.MovimientoRequest;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @POST("login")
    Call<Boolean> login(@Body LoginRequest request);

    @GET("cuentas/{id}")
    Call<Cuenta> obtenerCuenta(@Path("id") String cuentaId);

    @GET("movimientos/{cuenta}")
    Call<List<Movimiento>> obtenerMovimientos(@Path("cuenta") String cuenta);

    @POST("movimientos")
    Call<ResponseBody> realizarMovimiento(@Body MovimientoRequest request); // <-- actualizamos aquí
}
