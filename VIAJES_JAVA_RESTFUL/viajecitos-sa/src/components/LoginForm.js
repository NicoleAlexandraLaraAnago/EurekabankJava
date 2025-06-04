import React, { useState } from 'react';
import axios from 'axios';

const LoginForm = ({ onLoginSuccess }) => {
  const [correo, setCorreo] = useState('');
  const [password, setPassword] = useState('');
  const [mensaje, setMensaje] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await axios.post('http://10.40.15.238:8080/auth/login', {
        correo,
        password,
      });
      setMensaje('🛫 ¡Bienvenido abordo!');
      onLoginSuccess(res.data.nombre, res.data.rol); // pasamos nombre y rol
    } catch (err) {
      setMensaje('❌ Credenciales incorrectas');
    }
  };

  return (
    <div className="card p-4 shadow-sm mx-auto" style={{ maxWidth: '400px' }}>
      <h4 className="text-center mb-3">✈️ Iniciar Sesión</h4>
      <form onSubmit={handleSubmit}>
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
        <button className="btn btn-primary w-100" type="submit">
          Ingresar
        </button>
      </form>
      {mensaje && (
        <div className="alert alert-info text-center mt-3">{mensaje}</div>
      )}
    </div>
  );
};

export default LoginForm;
