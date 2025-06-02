import React from 'react';

const PanelCompras = ({ compras }) => {
  return (
    <div className="table-responsive">
      <table className="table table-bordered table-hover bg-white">
        <thead className="table-dark">
          <tr>
            <th>ID</th>
            <th>Comprador</th>
            <th>Fecha Compra</th>
            <th>Origen</th>
            <th>Destino</th>
            <th>Valor</th>
          </tr>
        </thead>
        <tbody>
          {compras.map((compra) => (
            <tr key={compra.id}>
              <td>{compra.id}</td>
              <td>{compra.comprador}</td>
              <td>{compra.fechaCompra}</td>
              <td>{compra.vuelo.ciudadOrigen}</td>
              <td>{compra.vuelo.ciudadDestino}</td>
              <td>{compra.vuelo.valor.toFixed(2)} USD</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default PanelCompras;
