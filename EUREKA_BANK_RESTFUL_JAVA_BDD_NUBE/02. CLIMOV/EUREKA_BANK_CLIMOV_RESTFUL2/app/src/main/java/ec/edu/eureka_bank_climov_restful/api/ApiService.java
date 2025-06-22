package ec.edu.eureka_bank_climov_restful.api;

import java.util.List;
import ec.edu.eureka_bank_climov_restful.model.Cuenta;
import ec.edu.eureka_bank_climov_restful.model.LoginRequest;
import ec.edu.eureka_bank_climov_restful.model.Movimiento;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface ApiService {

    @POST("login")
    Call<Boolean> login(@Body LoginRequest request);

    @GET("coreBancario/cuenta/{id}")
    Call<Cuenta> obtenerCuenta(@Path("id") String cuentaId);

    @GET("coreBancario/movimientos/{cuenta}")
    Call<List<Movimiento>> obtenerMovimientos(@Path("cuenta") String cuenta);

    @FormUrlEncoded
    @POST("coreBancario/deposito")
    Call<ResponseBody> registrarDeposito(
            @Field("cuenta") String cuenta,
            @Field("importe") double importe
    );

    @FormUrlEncoded
    @POST("coreBancario/retiro")
    Call<ResponseBody> registrarRetiro(
            @Field("cuenta") String cuenta,
            @Field("importe") double importe
    );

    @FormUrlEncoded
    @POST("coreBancario/transferencia")
    Call<ResponseBody> registrarTransferencia(
            @Field("cuentaOrigen") String cuentaOrigen,
            @Field("cuentaDestino") String cuentaDestino,
            @Field("importe") double importe
    );
}
