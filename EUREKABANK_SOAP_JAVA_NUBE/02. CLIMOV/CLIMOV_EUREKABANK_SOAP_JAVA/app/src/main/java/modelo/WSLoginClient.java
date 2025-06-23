package modelo;

import android.os.AsyncTask;
import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class WSLoginClient {

    private static final String NAMESPACE = "http://ws.monster.edu.ec/";

    private static final String URL = "http://192.168.100.36:8080/WSEureka";
    private static final String METHOD_NAME = "login";
    private static final String SOAP_ACTION = NAMESPACE + METHOD_NAME;

    public interface LoginCallback {
        void onLoginSuccess(String resultado);
        void onLoginError(String error);
    }

    public static void login(final String usuario, final String clave, final LoginCallback callback) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
                    request.addProperty("usuario", usuario);
                    request.addProperty("clave", clave);

                    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                    envelope.dotNet = false;
                    envelope.setOutputSoapObject(request);

                    HttpTransportSE transport = new HttpTransportSE(URL);
                    transport.call(SOAP_ACTION, envelope);

                    if (envelope.bodyIn instanceof SoapObject) {
                        SoapObject response = (SoapObject) envelope.bodyIn;
                        return response.getProperty(0).toString();
                    } else if (envelope.bodyIn instanceof org.ksoap2.SoapFault) {
                        org.ksoap2.SoapFault fault = (org.ksoap2.SoapFault) envelope.bodyIn;
                        Log.e("WSLoginClient", "SOAP Fault: " + fault.faultstring);
                        return "ERROR: " + fault.faultstring;
                    } else {
                        return "Respuesta desconocida del servidor.";
                    }

                } catch (Exception e) {
                    Log.e("WSLoginClient", "Error en login: " + e.getMessage(), e);
                    return null;
                }
            }


            @Override
            protected void onPostExecute(String resultado) {
                if (resultado != null) {
                    callback.onLoginSuccess(resultado);
                } else {
                    callback.onLoginError("No se pudo iniciar sesión. Verifique la conexión.");
                }
            }
        }.execute();
    }
}
