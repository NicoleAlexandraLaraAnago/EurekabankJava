namespace EUREKA_BANK_CLIWEB_RESTFUL.Models
{
    public class MovimientoRequest
    {
        public string CuentaOrigen { get; set; }
        public decimal Importe { get; set; }
        public string TipoMovimiento { get; set; }
        public string? CuentaDestino { get; set; }
    }

}
