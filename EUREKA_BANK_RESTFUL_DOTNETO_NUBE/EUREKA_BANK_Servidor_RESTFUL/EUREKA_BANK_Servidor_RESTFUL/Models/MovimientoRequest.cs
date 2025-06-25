namespace EUREKA_BANK_Servidor_RESTFUL.Models
{
    public class MovimientoRequest
    {
        public string CuentaOrigen { get; set; }
        public decimal Importe { get; set; }
        public string TipoMovimiento { get; set; }
        public string? CuentaDestino { get; set; } // Solo si es transferencia
    }
}
