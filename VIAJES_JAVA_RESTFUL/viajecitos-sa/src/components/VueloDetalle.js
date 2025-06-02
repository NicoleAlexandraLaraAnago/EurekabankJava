import React from 'react';

const VueloDetalle = ({ vuelo }) => {
  if (!vuelo) return null;

  return (
<div className="d-flex justify-content-center">
  <div
    className="card p-4 shadow-sm mb-4"
    style={{
      backgroundColor: '#E1EFC8',
      color: '#0c262e',
      maxWidth: '400px', // ancho reducido
      width: '100%'       // permite que sea responsive
    }}
  >
    <h5 className="card-title mb-3 text-center">Vuelo encontrado VALIDO IDA Y VUELTA</h5>
    <ul className="list-group list-group-flush">
      <li className="list-group-item text-center" style={{ backgroundColor: 'white' }}>
        Origen: <strong>{vuelo.ciudadOrigen}</strong>
      </li>
      <li className="list-group-item text-center" style={{ backgroundColor: 'white' }}>
        Destino: <strong>{vuelo.ciudadDestino}</strong>
      </li>
      <li className="list-group-item text-center" style={{ backgroundColor: 'white' }}>
        Valor: <strong>${vuelo.valor}</strong>
      </li>
      <li className="list-group-item text-center" style={{ backgroundColor: 'white' }}>
        Hora de salida: <strong>{vuelo.horaSalida}</strong>
      </li>
    </ul>
  </div>
</div>

  );
};

export default VueloDetalle;
