import React from 'react';

const ListaVuelos = ({ vuelos, onSeleccionar }) => {
  return (
    <div className="card p-3 shadow-sm" style={{ backgroundColor: '#E0F2F1' }}>
      <h5 className="mb-3">🛫 Vuelos disponibles</h5>
      <ul className="list-group">
        {vuelos.map((vuelo) => (
          <li
            key={vuelo.id}
            className="list-group-item d-flex justify-content-between align-items-center list-group-item-action"
            style={{ cursor: 'pointer' }}
            onClick={() => onSeleccionar(vuelo)}
          >
            {vuelo.ciudadOrigen} → {vuelo.ciudadDestino} | {vuelo.fecha} | {vuelo.valor.toFixed(2)} USD
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ListaVuelos;
