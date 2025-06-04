import React, { useState } from 'react';
import { buscarVuelos, comprarVuelo } from './api/vuelosApi';
import SearchForm from './components/SearchForm';
import VueloDetalle from './components/VueloDetalle';
import CompraForm from './components/CompraForm';
import ListaVuelos from './components/ListaVuelos';
import LoginForm from './components/LoginForm';
import RegistroForm from './components/RegistroForm';
import PanelCompras from './components/PanelCompras';

const App = () => {
  const [vuelo, setVuelo] = useState(null);
  const [mensaje, setMensaje] = useState('');
  const [usuarioAutenticado, setUsuarioAutenticado] = useState(false);
  const [mostrarRegistro, setMostrarRegistro] = useState(false);
  const [nombreUsuario, setNombreUsuario] = useState('');
  const [rolUsuario, setRolUsuario] = useState('');
  const [listaVuelos, setListaVuelos] = useState([]);
  const [compras, setCompras] = useState([]);

  const handleLoginSuccess = (nombre, rol) => {
    setNombreUsuario(nombre);
    setUsuarioAutenticado(true);
    setRolUsuario(rol);
  };

  const alternarFormulario = () => {
    setMostrarRegistro(!mostrarRegistro);
    setMensaje('');
  };

  const handleCerrarSesion = () => {
    setUsuarioAutenticado(false);
    setNombreUsuario('');
    setRolUsuario('');
    setVuelo(null);
    setListaVuelos([]);
    setCompras([]);
    setMensaje('');
  };

  const handleBuscar = async (origen, destino, fecha) => {
    try {
      let vuelos = await buscarVuelos(origen, destino, fecha);
      vuelos = vuelos.sort((a, b) => b.valor - a.valor);
      setListaVuelos(vuelos);

      if (vuelos.length === 0) {
        setVuelo(null);
        setMensaje('No hay vuelos disponibles.');
      } else {
        setVuelo(vuelos[0]);
        setMensaje('');
      }
    } catch (err) {
      setMensaje('Error al buscar vuelos.');
      setVuelo(null);
    }
  };

  const handleConfirmarCompra = async (vueloId, comprador, fechaCompra) => {
    try {
      await comprarVuelo(vueloId, comprador, fechaCompra);
      setMensaje('✈️ ¡Gracias por comprar con Aerolíneas Cóndor!');
    } catch (err) {
      setMensaje('❌ Error al registrar la compra.');
    }
  };

  const cargarTodasLasCompras = async () => {
    try {
      const res = await fetch('http://10.40.15.238:8080/compras');
      const data = await res.json();
      setCompras(data);
    } catch (err) {
      alert('Error al cargar las compras');
    }
  };

  if (!usuarioAutenticado) {
    return (
      <div
        style={{
          backgroundImage: 'url("/images/fondo.jpg")',
          backgroundSize: 'cover',
          backgroundPosition: 'center',
          backgroundRepeat: 'no-repeat',
          minHeight: '100vh',
          padding: '2rem',
        }}
      >
        <div className="container py-4">
          <div className="text-center mb-4">
            <h1 className="mb-4" style={{ color: '#0c262e' }}>✈️ Viajecitos SA</h1>
          </div>

          {mostrarRegistro ? (
            <>
              <RegistroForm />
              <div className="text-center mt-3">
                <button className="btn btn-link" onClick={alternarFormulario}>
                  ¿Ya tienes cuenta? Inicia sesión aquí
                </button>
              </div>
            </>
          ) : (
            <>
              <LoginForm onLoginSuccess={handleLoginSuccess} />
              <div className="text-center mt-3">
                <button className="btn btn-link" onClick={alternarFormulario}>
                  ¿No tienes cuenta? Regístrate aquí
                </button>
              </div>
            </>
          )}

          {mensaje && (
            <div className="alert alert-info text-center mt-3">{mensaje}</div>
          )}
        </div>
      </div>
    );
  }

  return (
    <div
      style={{
        backgroundImage: 'url("/images/fondo.jpg")',
        backgroundSize: 'cover',
        backgroundPosition: 'center',
        backgroundRepeat: 'no-repeat',
        minHeight: '100vh',
        padding: '2rem',
      }}
    >
      <div className="container py-4">
        <div className="text-center mb-4">
          <h1 className="mb-4" style={{ color: '#0c262e' }}>✈️ Viajecitos SA</h1>
          <div className="d-flex justify-content-between align-items-center mb-4">
            <h5 style={{ color: '#1c2833' }}>👋 Bienvenid@, {nombreUsuario}</h5>
            <button className="btn btn-danger" onClick={handleCerrarSesion}>
              Cerrar sesión
            </button>
          </div>
          <img src="/images/logo.jpg" alt="Logo" className="mb-2" style={{ width: '100%', maxWidth: '1200px' }} />
        </div>

        {rolUsuario === 'ADMIN' ? (
          <>
            <h5>📋 Panel de Administración</h5>
            <button className="btn btn-secondary mb-3" onClick={cargarTodasLasCompras}>
              Ver todas las compras
            </button>
            {compras.length > 0 && <PanelCompras compras={compras} />}
          </>
        ) : (
          <>
            <SearchForm onBuscar={handleBuscar} />

            <div className="row mt-4">
              <div className="col-md-6">
                <VueloDetalle vuelo={vuelo} />
                {vuelo && (
                  <CompraForm
                    vuelo={vuelo}
                    nombreComprador={nombreUsuario}
                    onConfirmar={handleConfirmarCompra}
                  />
                )}
              </div>

              <div className="col-md-6">
                {listaVuelos.length > 0 && (
                  <ListaVuelos vuelos={listaVuelos} onSeleccionar={(v) => setVuelo(v)} />
                )}
              </div>
            </div>
          </>
        )}

        {mensaje && (
          <div
            className="alert alert-info text-center mt-4"
            style={{ backgroundColor: '#407f89', color: 'white', border: 'none' }}
          >
            {mensaje}
          </div>
        )}
      </div>
    </div>
  );
};

export default App;
