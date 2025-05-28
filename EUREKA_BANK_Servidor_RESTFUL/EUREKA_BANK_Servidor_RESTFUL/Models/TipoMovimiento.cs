namespace EUREKA_BANK_Servidor_RESTFUL.Models
{
    using System.ComponentModel.DataAnnotations;

    public class TipoMovimiento
    {
        [Key]
        public string TipoCodigo { get; set; }  // Ej: '003'

        public string Descripcion { get; set; } // Ej: 'Depósito'

        public string Accion { get; set; }      // INGRESO o SALIDA

        public string Estado { get; set; } = "ACTIVO";
    }

}
