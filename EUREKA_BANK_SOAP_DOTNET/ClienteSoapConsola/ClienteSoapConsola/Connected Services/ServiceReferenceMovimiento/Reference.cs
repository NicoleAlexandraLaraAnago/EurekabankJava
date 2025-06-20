﻿//------------------------------------------------------------------------------
// <auto-generated>
//     Este código fue generado por una herramienta.
//
//     Los cambios en este archivo podrían causar un comportamiento incorrecto y se perderán si
//     se vuelve a generar el código.
// </auto-generated>
//------------------------------------------------------------------------------

namespace ServiceReferenceMovimiento
{
    using System.Runtime.Serialization;
    
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Tools.ServiceModel.Svcutil", "2.2.0-preview1.23462.5")]
    [System.Runtime.Serialization.DataContractAttribute(Name="Movimiento", Namespace="http://schemas.datacontract.org/2004/07/Eurekabank_Servidor_SOAP.Models")]
    public partial class Movimiento : object
    {
        
        private string CuentaDestinoField;
        
        private string CuentaIdField;
        
        private string EmpleadoIdField;
        
        private System.DateTime FechaField;
        
        private decimal ImporteField;
        
        private int NumeroMovimientoField;
        
        private string TipoField;
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public string CuentaDestino
        {
            get
            {
                return this.CuentaDestinoField;
            }
            set
            {
                this.CuentaDestinoField = value;
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public string CuentaId
        {
            get
            {
                return this.CuentaIdField;
            }
            set
            {
                this.CuentaIdField = value;
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public string EmpleadoId
        {
            get
            {
                return this.EmpleadoIdField;
            }
            set
            {
                this.EmpleadoIdField = value;
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public System.DateTime Fecha
        {
            get
            {
                return this.FechaField;
            }
            set
            {
                this.FechaField = value;
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public decimal Importe
        {
            get
            {
                return this.ImporteField;
            }
            set
            {
                this.ImporteField = value;
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public int NumeroMovimiento
        {
            get
            {
                return this.NumeroMovimientoField;
            }
            set
            {
                this.NumeroMovimientoField = value;
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public string Tipo
        {
            get
            {
                return this.TipoField;
            }
            set
            {
                this.TipoField = value;
            }
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Tools.ServiceModel.Svcutil", "2.2.0-preview1.23462.5")]
    [System.Runtime.Serialization.DataContractAttribute(Name="MovimientoRequest", Namespace="http://schemas.datacontract.org/2004/07/Eurekabank_Servidor_SOAP.Models")]
    public partial class MovimientoRequest : object
    {
        
        private string CuentaDestinoField;
        
        private string CuentaOrigenField;
        
        private decimal ImporteField;
        
        private string TipoMovimientoField;
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public string CuentaDestino
        {
            get
            {
                return this.CuentaDestinoField;
            }
            set
            {
                this.CuentaDestinoField = value;
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public string CuentaOrigen
        {
            get
            {
                return this.CuentaOrigenField;
            }
            set
            {
                this.CuentaOrigenField = value;
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public decimal Importe
        {
            get
            {
                return this.ImporteField;
            }
            set
            {
                this.ImporteField = value;
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public string TipoMovimiento
        {
            get
            {
                return this.TipoMovimientoField;
            }
            set
            {
                this.TipoMovimientoField = value;
            }
        }
    }
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Tools.ServiceModel.Svcutil", "2.2.0-preview1.23462.5")]
    [System.ServiceModel.ServiceContractAttribute(ConfigurationName="ServiceReferenceMovimiento.IMovimientoService")]
    public interface IMovimientoService
    {
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/IMovimientoService/GetMovimientos", ReplyAction="http://tempuri.org/IMovimientoService/GetMovimientosResponse")]
        System.Threading.Tasks.Task<ServiceReferenceMovimiento.Movimiento[]> GetMovimientosAsync(string cuenta);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/IMovimientoService/RegistrarMovimiento", ReplyAction="http://tempuri.org/IMovimientoService/RegistrarMovimientoResponse")]
        System.Threading.Tasks.Task<bool> RegistrarMovimientoAsync(ServiceReferenceMovimiento.MovimientoRequest req);
    }
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Tools.ServiceModel.Svcutil", "2.2.0-preview1.23462.5")]
    public interface IMovimientoServiceChannel : ServiceReferenceMovimiento.IMovimientoService, System.ServiceModel.IClientChannel
    {
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Tools.ServiceModel.Svcutil", "2.2.0-preview1.23462.5")]
    public partial class MovimientoServiceClient : System.ServiceModel.ClientBase<ServiceReferenceMovimiento.IMovimientoService>, ServiceReferenceMovimiento.IMovimientoService
    {
        
        /// <summary>
        /// Implemente este método parcial para configurar el punto de conexión de servicio.
        /// </summary>
        /// <param name="serviceEndpoint">El punto de conexión para configurar</param>
        /// <param name="clientCredentials">Credenciales de cliente</param>
        static partial void ConfigureEndpoint(System.ServiceModel.Description.ServiceEndpoint serviceEndpoint, System.ServiceModel.Description.ClientCredentials clientCredentials);
        
        public MovimientoServiceClient() : 
                base(MovimientoServiceClient.GetDefaultBinding(), MovimientoServiceClient.GetDefaultEndpointAddress())
        {
            this.Endpoint.Name = EndpointConfiguration.BasicHttpBinding_IMovimientoService.ToString();
            ConfigureEndpoint(this.Endpoint, this.ClientCredentials);
        }
        
        public MovimientoServiceClient(EndpointConfiguration endpointConfiguration) : 
                base(MovimientoServiceClient.GetBindingForEndpoint(endpointConfiguration), MovimientoServiceClient.GetEndpointAddress(endpointConfiguration))
        {
            this.Endpoint.Name = endpointConfiguration.ToString();
            ConfigureEndpoint(this.Endpoint, this.ClientCredentials);
        }
        
        public MovimientoServiceClient(EndpointConfiguration endpointConfiguration, string remoteAddress) : 
                base(MovimientoServiceClient.GetBindingForEndpoint(endpointConfiguration), new System.ServiceModel.EndpointAddress(remoteAddress))
        {
            this.Endpoint.Name = endpointConfiguration.ToString();
            ConfigureEndpoint(this.Endpoint, this.ClientCredentials);
        }
        
        public MovimientoServiceClient(EndpointConfiguration endpointConfiguration, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(MovimientoServiceClient.GetBindingForEndpoint(endpointConfiguration), remoteAddress)
        {
            this.Endpoint.Name = endpointConfiguration.ToString();
            ConfigureEndpoint(this.Endpoint, this.ClientCredentials);
        }
        
        public MovimientoServiceClient(System.ServiceModel.Channels.Binding binding, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(binding, remoteAddress)
        {
        }
        
        public System.Threading.Tasks.Task<ServiceReferenceMovimiento.Movimiento[]> GetMovimientosAsync(string cuenta)
        {
            return base.Channel.GetMovimientosAsync(cuenta);
        }
        
        public System.Threading.Tasks.Task<bool> RegistrarMovimientoAsync(ServiceReferenceMovimiento.MovimientoRequest req)
        {
            return base.Channel.RegistrarMovimientoAsync(req);
        }
        
        public virtual System.Threading.Tasks.Task OpenAsync()
        {
            return System.Threading.Tasks.Task.Factory.FromAsync(((System.ServiceModel.ICommunicationObject)(this)).BeginOpen(null, null), new System.Action<System.IAsyncResult>(((System.ServiceModel.ICommunicationObject)(this)).EndOpen));
        }
        
        private static System.ServiceModel.Channels.Binding GetBindingForEndpoint(EndpointConfiguration endpointConfiguration)
        {
            if ((endpointConfiguration == EndpointConfiguration.BasicHttpBinding_IMovimientoService))
            {
                System.ServiceModel.BasicHttpBinding result = new System.ServiceModel.BasicHttpBinding();
                result.MaxBufferSize = int.MaxValue;
                result.ReaderQuotas = System.Xml.XmlDictionaryReaderQuotas.Max;
                result.MaxReceivedMessageSize = int.MaxValue;
                result.AllowCookies = true;
                return result;
            }
            throw new System.InvalidOperationException(string.Format("No se pudo encontrar un punto de conexión con el nombre \"{0}\".", endpointConfiguration));
        }
        
        private static System.ServiceModel.EndpointAddress GetEndpointAddress(EndpointConfiguration endpointConfiguration)
        {
            if ((endpointConfiguration == EndpointConfiguration.BasicHttpBinding_IMovimientoService))
            {
                return new System.ServiceModel.EndpointAddress("http://localhost:5066/MovimientoService");
            }
            throw new System.InvalidOperationException(string.Format("No se pudo encontrar un punto de conexión con el nombre \"{0}\".", endpointConfiguration));
        }
        
        private static System.ServiceModel.Channels.Binding GetDefaultBinding()
        {
            return MovimientoServiceClient.GetBindingForEndpoint(EndpointConfiguration.BasicHttpBinding_IMovimientoService);
        }
        
        private static System.ServiceModel.EndpointAddress GetDefaultEndpointAddress()
        {
            return MovimientoServiceClient.GetEndpointAddress(EndpointConfiguration.BasicHttpBinding_IMovimientoService);
        }
        
        public enum EndpointConfiguration
        {
            
            BasicHttpBinding_IMovimientoService,
        }
    }
}
