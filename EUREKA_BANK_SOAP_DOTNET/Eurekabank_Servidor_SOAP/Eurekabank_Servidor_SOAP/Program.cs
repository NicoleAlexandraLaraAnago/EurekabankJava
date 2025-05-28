using CoreWCF;
using CoreWCF.Configuration;
using CoreWCF.Description;
using Eurekabank_Servidor_SOAP.Data;
using Eurekabank_Servidor_SOAP.Services;
using Microsoft.EntityFrameworkCore;

var builder = WebApplication.CreateBuilder(args);

// Conexión a la BD
builder.Services.AddDbContext<AppDbContext>(options =>
    options.UseSqlServer(builder.Configuration.GetConnectionString("DefaultConnection")));

// Servicios SOAP
builder.Services.AddScoped<AuthService>();
builder.Services.AddScoped<MovimientoService>();

// Habilita CoreWCF + metadatos (WSDL)
builder.Services.AddServiceModelServices();
builder.Services.AddServiceModelMetadata(); // 👈 NECESARIO para ?wsdl

var app = builder.Build();

// Configura los endpoints SOAP
app.UseServiceModel(serviceBuilder =>
{
    serviceBuilder.AddService<AuthService>();
    serviceBuilder.AddServiceEndpoint<AuthService, IAuthService>(
        new BasicHttpBinding(), "/AuthService");

    serviceBuilder.AddService<MovimientoService>();
    serviceBuilder.AddServiceEndpoint<MovimientoService, IMovimientoService>(
        new BasicHttpBinding(), "/MovimientoService");

    // 👇 Añade soporte WSDL con metadatos (importante)
    var serviceMetadataBehavior = app.Services.GetRequiredService<ServiceMetadataBehavior>();
    serviceMetadataBehavior.HttpGetEnabled = true;
    serviceMetadataBehavior.HttpsGetEnabled = true;
});

// Corre la aplicación
app.Run();
