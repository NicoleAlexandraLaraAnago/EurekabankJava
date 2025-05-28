using System.ComponentModel.DataAnnotations;

namespace Eurekabank_Servidor_SOAP.Models
{
    public class TipoMovimiento
    {
        [Key]
        public string TipoCodigo { get; set; }  // Ej: '003'

        public string Descripcion { get; set; } // Ej: 'Depósito'

        public string Accion { get; set; }      // INGRESO o SALIDA

        public string Estado { get; set; } = "ACTIVO";
    }
}
