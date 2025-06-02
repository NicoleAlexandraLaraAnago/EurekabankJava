import React, { useState } from 'react';

const SearchForm = ({ onBuscar }) => {
  const [origen, setOrigen] = useState('');
  const [destino, setDestino] = useState('');
  const [fecha, setFecha] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    onBuscar(origen.toUpperCase(), destino.toUpperCase(), fecha);
  };

  return (
    <form onSubmit={handleSubmit} className="card p-4 shadow-sm mb-4" style={{ backgroundColor: '#407f89 ', color: '#0c262e' }}>
      <h5 className="card-title mb-3">Buscar Vuelos</h5>
      <div className="row g-3">
        <div className="col-md-4">
          <input className="form-control" placeholder="Origen (ej: UIO)" value={origen} onChange={(e) => setOrigen(e.target.value)} />
        </div>
        <div className="col-md-4">
          <input className="form-control" placeholder="Destino (ej: GYE)" value={destino} onChange={(e) => setDestino(e.target.value)} />
        </div>
        <div className="col-md-4">
          <input className="form-control" type="date" value={fecha} onChange={(e) => setFecha(e.target.value)} />
        </div>
      </div>
      <div className="mt-3 text-end">
        <button
          className="btn"
          type="submit"
          style={{ backgroundColor: '#677567', color: 'white' }}
        >
          Buscar Vuelo
        </button>
      </div>
    </form>
  );
};

export default SearchForm;
