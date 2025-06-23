package modelo;

import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class WSEurekabankCliente {

    private static final String NAMESPACE = "http://ws.monster.edu.ec/";
    private static final String URL = "http://192.168.100.36:8080/WSEureka";
    private static final String METHOD_NAME_TRAER_MOVIMIENTOS = "traerMovimientos";
    private static final String METHOD_NAME_REG_DEPOSITO = "regDeposito";
    private static final String SOAP_ACTION_TRAER_MOVIMIENTOS = NAMESPACE + METHOD_NAME_TRAER_MOVIMIENTOS;
    private static final String SOAP_ACTION_REG_DEPOSITO = NAMESPACE + METHOD_NAME_REG_DEPOSITO;
    private static final String METHOD_NAME_OBTENER_SALDO = "obtenerSaldo";
    private static final String SOAP_ACTION_OBTENER_SALDO = NAMESPACE + METHOD_NAME_OBTENER_SALDO;


    public List<Movimiento> traerMovimientos(String numeroCuenta) {
        try {
            // Crear el request
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_TRAER_MOVIMIENTOS);

            // Añadir el número de cuenta al request
            PropertyInfo cuentaProperty = new PropertyInfo();
            cuentaProperty.setName("cuenta");
            cuentaProperty.setValue(numeroCuenta);
            cuentaProperty.setType(String.class);
            request.addProperty(cuentaProperty);

            // Configurar el envelope
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = false;
            envelope.setOutputSoapObject(request);

            // Configurar el transporte
            HttpTransportSE httpTransport = new HttpTransportSE(URL, 60000);
            httpTransport.debug = true;

            try {
                // Realizar la llamada
                httpTransport.call(SOAP_ACTION_TRAER_MOVIMIENTOS, envelope);

                // Imprimir el request y response para debugging
                System.out.println("REQUEST DUMP: " + httpTransport.requestDump);
                System.out.println("RESPONSE DUMP: " + httpTransport.responseDump);

                // Obtener la respuesta
                Object response = envelope.getResponse();

                // Crear una lista para almacenar los movimientos
                List<Movimiento> movimientos = new ArrayList<>();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                // Manejar diferentes tipos de respuesta
                if (response instanceof Vector) {
                    Vector<?> vector = (Vector<?>) response;
                    for (Object item : vector) {
                        if (item instanceof SoapObject) {
                            SoapObject movimientoSoap = (SoapObject) item;
                            try {
                                // Extraer las propiedades con manejo seguro de tipos
                                String cuenta = getSoapPropertySafely(movimientoSoap, "cuenta");
                                int nromov = Integer.parseInt(getSoapPropertySafely(movimientoSoap, "nromov"));
                                Date fecha = dateFormat.parse(getSoapPropertySafely(movimientoSoap, "fecha"));
                                String tipo = getSoapPropertySafely(movimientoSoap, "tipo");
                                String accion = getSoapPropertySafely(movimientoSoap, "accion");
                                double importe = Double.parseDouble(getSoapPropertySafely(movimientoSoap, "importe"));

                                // Crear un objeto Movimiento y agregarlo a la lista
                                Movimiento movimiento = new Movimiento(cuenta, nromov, fecha, tipo, accion, importe);
                                movimientos.add(movimiento);
                            } catch (Exception e) {
                                System.out.println("Error procesando movimiento individual: " + e.getMessage());
                                // Continuar con el siguiente movimiento
                                continue;
                            }
                        }
                    }
                } else if (response instanceof SoapObject) {
                    SoapObject soapResponse = (SoapObject) response;
                }

                return movimientos;

            } finally {
                httpTransport.reset();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error detallado: " + e.getMessage());
            return null;
        }
    }

    // Método auxiliar para obtener propiedades de forma segura
    private String getSoapPropertySafely(SoapObject soapObject, String propertyName) {
        try {
            Object property = soapObject.getProperty(propertyName);
            return property != null ? property.toString() : "";
        } catch (Exception e) {
            System.out.println("Error obteniendo propiedad " + propertyName + ": " + e.getMessage());
            return "";
        }
    }



    // Método para consumir regDeposito
    public String regDeposito(String cuenta, String codigo,  String monto) {
        try {
            // Crear el request
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_REG_DEPOSITO);

            // Añadir propiedades como String
            request.addProperty("cuenta", cuenta);
            request.addProperty("importe", monto);
            request.addProperty("tipoCodigo", codigo);

            // Configurar el envelope
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = false;
            envelope.setOutputSoapObject(request);

            // Configurar el transporte
            HttpTransportSE httpTransport = new HttpTransportSE(URL, 60000);
            httpTransport.debug = true;

            try {
                // Realizar la llamada
                httpTransport.call(SOAP_ACTION_REG_DEPOSITO, envelope);

                // Imprimir el request y response para debugging
                System.out.println("REQUEST DUMP: " + httpTransport.requestDump);
                System.out.println("RESPONSE DUMP: " + httpTransport.responseDump);

                // Obtener la respuesta
                Object response = envelope.getResponse();
                return response != null ? response.toString() : "Sin respuesta";
            } finally {
                httpTransport.reset();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error detallado: " + e.getMessage());
            return "Error al realizar la operación regDeposito: " + e.getMessage();
        }
    }

    public double verSaldo(String cuenta) {
        try {
            // Crear la solicitud SOAP
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_OBTENER_SALDO);

            // Añadir el parámetro 'cuenta' a la solicitud
            request.addProperty("cuenta", cuenta);

            // Configurar el envelope
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = false;
            envelope.setOutputSoapObject(request);

            // Configurar el transporte HTTP
            HttpTransportSE httpTransport = new HttpTransportSE(URL, 60000);
            httpTransport.debug = true;

            try {
                // Realizar la llamada al servicio web
                httpTransport.call(SOAP_ACTION_OBTENER_SALDO, envelope);

                // Imprimir la solicitud y respuesta para depuración
                System.out.println("REQUEST DUMP: " + httpTransport.requestDump);
                System.out.println("RESPONSE DUMP: " + httpTransport.responseDump);

                // Obtener la respuesta
                Object response = envelope.getResponse();

                // Convertir la respuesta a double y retornarla
                if (response != null) {
                    return Double.parseDouble(response.toString());
                } else {
                    throw new Exception("Respuesta nula del servicio web");
                }
            } finally {
                httpTransport.reset();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error detallado: " + e.getMessage());
            return -1; // Retornar un valor negativo en caso de error
        }
    }

}
