import React, { useState } from 'react';
import axios from 'axios';

const RegistroForm = () => {
  const [nombre, setNombre] = useState('');
  const [correo, setCorreo] = useState('');
  const [password, setPassword] = useState('');
  const [mensaje, setMensaje] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post('http://10.40.15.238:8080/auth/registro', {
        nombre,
        correo,
        password
      });
      setMensaje('✅ ¡Registro exitoso! Ya puedes iniciar sesión.');
      setNombre('');
      setCorreo('');
      setPassword('');
    } catch (err) {
      setMensaje('❌ El correo ya está registrado.');
    }
  };

  return (
    <div className="card p-4 shadow-sm mx-auto" style={{ maxWidth: '400px' }}>
      <h4 className="text-center mb-3">📝 Registro</h4>
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label>Nombre completo</label>
          <input
            type="text"
            className="form-control"
            value={nombre}
            onChange={(e) => setNombre(e.target.value)}
            required
          />
        </div>
        <div className="mb-3">
          <label>Correo electrónico</label>
          <input
            type="email"
            className="form-control"
            value={correo}
            onChange={(e) => setCorreo(e.target.value)}
            required
          />
        </div>
        <div className="mb-3">
          <label>Contraseña</label>
          <input
            type="password"
            className="form-control"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        <button className="btn btn-success w-100" type="submit">Registrarse</button>
      </form>
      {mensaje && <div className="alert alert-info text-center mt-3">{mensaje}</div>}
    </div>
  );
};

export default RegistroForm;
