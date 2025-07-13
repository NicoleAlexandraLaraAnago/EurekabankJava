
package ec.edu.restfull.servicio;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ec.edu.restfull.servicio package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _LoginResponse_QNAME = new QName("http://servicio.restfull.edu.ec/", "loginResponse");
    private final static QName _ObtenerFactura_QNAME = new QName("http://servicio.restfull.edu.ec/", "obtenerFactura");
    private final static QName _HistorialFacturasPorUsuario_QNAME = new QName("http://servicio.restfull.edu.ec/", "historialFacturasPorUsuario");
    private final static QName _BuscarVuelos_QNAME = new QName("http://servicio.restfull.edu.ec/", "buscarVuelos");
    private final static QName _ComprarYFacturarResponse_QNAME = new QName("http://servicio.restfull.edu.ec/", "comprarYFacturarResponse");
    private final static QName _Login_QNAME = new QName("http://servicio.restfull.edu.ec/", "login");
    private final static QName _ObtenerFacturaResponse_QNAME = new QName("http://servicio.restfull.edu.ec/", "obtenerFacturaResponse");
    private final static QName _ObtenerAmortizacionPorFactura_QNAME = new QName("http://servicio.restfull.edu.ec/", "obtenerAmortizacionPorFactura");
    private final static QName _BuscarVueloPorIdResponse_QNAME = new QName("http://servicio.restfull.edu.ec/", "buscarVueloPorIdResponse");
    private final static QName _HistorialFacturasPorUsuarioResponse_QNAME = new QName("http://servicio.restfull.edu.ec/", "historialFacturasPorUsuarioResponse");
    private final static QName _ObtenerAmortizacionPorFacturaResponse_QNAME = new QName("http://servicio.restfull.edu.ec/", "obtenerAmortizacionPorFacturaResponse");
    private final static QName _ComprarYFacturar_QNAME = new QName("http://servicio.restfull.edu.ec/", "comprarYFacturar");
    private final static QName _VerHistorialBusquedasResponse_QNAME = new QName("http://servicio.restfull.edu.ec/", "verHistorialBusquedasResponse");
    private final static QName _BuscarVueloPorId_QNAME = new QName("http://servicio.restfull.edu.ec/", "buscarVueloPorId");
    private final static QName _VerHistorialBusquedas_QNAME = new QName("http://servicio.restfull.edu.ec/", "verHistorialBusquedas");
    private final static QName _BuscarVuelosResponse_QNAME = new QName("http://servicio.restfull.edu.ec/", "buscarVuelosResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ec.edu.restfull.servicio
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link LoginResponse }
     * 
     */
    public LoginResponse createLoginResponse() {
        return new LoginResponse();
    }

    /**
     * Create an instance of {@link ObtenerFactura }
     * 
     */
    public ObtenerFactura createObtenerFactura() {
        return new ObtenerFactura();
    }

    /**
     * Create an instance of {@link HistorialFacturasPorUsuario }
     * 
     */
    public HistorialFacturasPorUsuario createHistorialFacturasPorUsuario() {
        return new HistorialFacturasPorUsuario();
    }

    /**
     * Create an instance of {@link BuscarVuelos }
     * 
     */
    public BuscarVuelos createBuscarVuelos() {
        return new BuscarVuelos();
    }

    /**
     * Create an instance of {@link Login }
     * 
     */
    public Login createLogin() {
        return new Login();
    }

    /**
     * Create an instance of {@link ComprarYFacturarResponse }
     * 
     */
    public ComprarYFacturarResponse createComprarYFacturarResponse() {
        return new ComprarYFacturarResponse();
    }

    /**
     * Create an instance of {@link ObtenerFacturaResponse }
     * 
     */
    public ObtenerFacturaResponse createObtenerFacturaResponse() {
        return new ObtenerFacturaResponse();
    }

    /**
     * Create an instance of {@link ObtenerAmortizacionPorFactura }
     * 
     */
    public ObtenerAmortizacionPorFactura createObtenerAmortizacionPorFactura() {
        return new ObtenerAmortizacionPorFactura();
    }

    /**
     * Create an instance of {@link HistorialFacturasPorUsuarioResponse }
     * 
     */
    public HistorialFacturasPorUsuarioResponse createHistorialFacturasPorUsuarioResponse() {
        return new HistorialFacturasPorUsuarioResponse();
    }

    /**
     * Create an instance of {@link BuscarVueloPorIdResponse }
     * 
     */
    public BuscarVueloPorIdResponse createBuscarVueloPorIdResponse() {
        return new BuscarVueloPorIdResponse();
    }

    /**
     * Create an instance of {@link ObtenerAmortizacionPorFacturaResponse }
     * 
     */
    public ObtenerAmortizacionPorFacturaResponse createObtenerAmortizacionPorFacturaResponse() {
        return new ObtenerAmortizacionPorFacturaResponse();
    }

    /**
     * Create an instance of {@link ComprarYFacturar }
     * 
     */
    public ComprarYFacturar createComprarYFacturar() {
        return new ComprarYFacturar();
    }

    /**
     * Create an instance of {@link VerHistorialBusquedasResponse }
     * 
     */
    public VerHistorialBusquedasResponse createVerHistorialBusquedasResponse() {
        return new VerHistorialBusquedasResponse();
    }

    /**
     * Create an instance of {@link BuscarVueloPorId }
     * 
     */
    public BuscarVueloPorId createBuscarVueloPorId() {
        return new BuscarVueloPorId();
    }

    /**
     * Create an instance of {@link VerHistorialBusquedas }
     * 
     */
    public VerHistorialBusquedas createVerHistorialBusquedas() {
        return new VerHistorialBusquedas();
    }

    /**
     * Create an instance of {@link BuscarVuelosResponse }
     * 
     */
    public BuscarVuelosResponse createBuscarVuelosResponse() {
        return new BuscarVuelosResponse();
    }

    /**
     * Create an instance of {@link Compra }
     * 
     */
    public Compra createCompra() {
        return new Compra();
    }

    /**
     * Create an instance of {@link DetalleCompra }
     * 
     */
    public DetalleCompra createDetalleCompra() {
        return new DetalleCompra();
    }

    /**
     * Create an instance of {@link HistorialBusqueda }
     * 
     */
    public HistorialBusqueda createHistorialBusqueda() {
        return new HistorialBusqueda();
    }

    /**
     * Create an instance of {@link MetodoPago }
     * 
     */
    public MetodoPago createMetodoPago() {
        return new MetodoPago();
    }

    /**
     * Create an instance of {@link Factura }
     * 
     */
    public Factura createFactura() {
        return new Factura();
    }

    /**
     * Create an instance of {@link Usuario }
     * 
     */
    public Usuario createUsuario() {
        return new Usuario();
    }

    /**
     * Create an instance of {@link Amortizacion }
     * 
     */
    public Amortizacion createAmortizacion() {
        return new Amortizacion();
    }

    /**
     * Create an instance of {@link Vuelo }
     * 
     */
    public Vuelo createVuelo() {
        return new Vuelo();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servicio.restfull.edu.ec/", name = "loginResponse")
    public JAXBElement<LoginResponse> createLoginResponse(LoginResponse value) {
        return new JAXBElement<LoginResponse>(_LoginResponse_QNAME, LoginResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerFactura }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servicio.restfull.edu.ec/", name = "obtenerFactura")
    public JAXBElement<ObtenerFactura> createObtenerFactura(ObtenerFactura value) {
        return new JAXBElement<ObtenerFactura>(_ObtenerFactura_QNAME, ObtenerFactura.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HistorialFacturasPorUsuario }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servicio.restfull.edu.ec/", name = "historialFacturasPorUsuario")
    public JAXBElement<HistorialFacturasPorUsuario> createHistorialFacturasPorUsuario(HistorialFacturasPorUsuario value) {
        return new JAXBElement<HistorialFacturasPorUsuario>(_HistorialFacturasPorUsuario_QNAME, HistorialFacturasPorUsuario.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BuscarVuelos }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servicio.restfull.edu.ec/", name = "buscarVuelos")
    public JAXBElement<BuscarVuelos> createBuscarVuelos(BuscarVuelos value) {
        return new JAXBElement<BuscarVuelos>(_BuscarVuelos_QNAME, BuscarVuelos.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ComprarYFacturarResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servicio.restfull.edu.ec/", name = "comprarYFacturarResponse")
    public JAXBElement<ComprarYFacturarResponse> createComprarYFacturarResponse(ComprarYFacturarResponse value) {
        return new JAXBElement<ComprarYFacturarResponse>(_ComprarYFacturarResponse_QNAME, ComprarYFacturarResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Login }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servicio.restfull.edu.ec/", name = "login")
    public JAXBElement<Login> createLogin(Login value) {
        return new JAXBElement<Login>(_Login_QNAME, Login.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerFacturaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servicio.restfull.edu.ec/", name = "obtenerFacturaResponse")
    public JAXBElement<ObtenerFacturaResponse> createObtenerFacturaResponse(ObtenerFacturaResponse value) {
        return new JAXBElement<ObtenerFacturaResponse>(_ObtenerFacturaResponse_QNAME, ObtenerFacturaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerAmortizacionPorFactura }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servicio.restfull.edu.ec/", name = "obtenerAmortizacionPorFactura")
    public JAXBElement<ObtenerAmortizacionPorFactura> createObtenerAmortizacionPorFactura(ObtenerAmortizacionPorFactura value) {
        return new JAXBElement<ObtenerAmortizacionPorFactura>(_ObtenerAmortizacionPorFactura_QNAME, ObtenerAmortizacionPorFactura.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BuscarVueloPorIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servicio.restfull.edu.ec/", name = "buscarVueloPorIdResponse")
    public JAXBElement<BuscarVueloPorIdResponse> createBuscarVueloPorIdResponse(BuscarVueloPorIdResponse value) {
        return new JAXBElement<BuscarVueloPorIdResponse>(_BuscarVueloPorIdResponse_QNAME, BuscarVueloPorIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HistorialFacturasPorUsuarioResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servicio.restfull.edu.ec/", name = "historialFacturasPorUsuarioResponse")
    public JAXBElement<HistorialFacturasPorUsuarioResponse> createHistorialFacturasPorUsuarioResponse(HistorialFacturasPorUsuarioResponse value) {
        return new JAXBElement<HistorialFacturasPorUsuarioResponse>(_HistorialFacturasPorUsuarioResponse_QNAME, HistorialFacturasPorUsuarioResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerAmortizacionPorFacturaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servicio.restfull.edu.ec/", name = "obtenerAmortizacionPorFacturaResponse")
    public JAXBElement<ObtenerAmortizacionPorFacturaResponse> createObtenerAmortizacionPorFacturaResponse(ObtenerAmortizacionPorFacturaResponse value) {
        return new JAXBElement<ObtenerAmortizacionPorFacturaResponse>(_ObtenerAmortizacionPorFacturaResponse_QNAME, ObtenerAmortizacionPorFacturaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ComprarYFacturar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servicio.restfull.edu.ec/", name = "comprarYFacturar")
    public JAXBElement<ComprarYFacturar> createComprarYFacturar(ComprarYFacturar value) {
        return new JAXBElement<ComprarYFacturar>(_ComprarYFacturar_QNAME, ComprarYFacturar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VerHistorialBusquedasResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servicio.restfull.edu.ec/", name = "verHistorialBusquedasResponse")
    public JAXBElement<VerHistorialBusquedasResponse> createVerHistorialBusquedasResponse(VerHistorialBusquedasResponse value) {
        return new JAXBElement<VerHistorialBusquedasResponse>(_VerHistorialBusquedasResponse_QNAME, VerHistorialBusquedasResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BuscarVueloPorId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servicio.restfull.edu.ec/", name = "buscarVueloPorId")
    public JAXBElement<BuscarVueloPorId> createBuscarVueloPorId(BuscarVueloPorId value) {
        return new JAXBElement<BuscarVueloPorId>(_BuscarVueloPorId_QNAME, BuscarVueloPorId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VerHistorialBusquedas }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servicio.restfull.edu.ec/", name = "verHistorialBusquedas")
    public JAXBElement<VerHistorialBusquedas> createVerHistorialBusquedas(VerHistorialBusquedas value) {
        return new JAXBElement<VerHistorialBusquedas>(_VerHistorialBusquedas_QNAME, VerHistorialBusquedas.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BuscarVuelosResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servicio.restfull.edu.ec/", name = "buscarVuelosResponse")
    public JAXBElement<BuscarVuelosResponse> createBuscarVuelosResponse(BuscarVuelosResponse value) {
        return new JAXBElement<BuscarVuelosResponse>(_BuscarVuelosResponse_QNAME, BuscarVuelosResponse.class, null, value);
    }

}
