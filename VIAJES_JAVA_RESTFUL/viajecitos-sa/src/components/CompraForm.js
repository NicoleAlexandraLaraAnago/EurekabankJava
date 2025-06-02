import React from 'react';

const CompraForm = ({ vuelo, onConfirmar, nombreComprador }) => {
  const handleSubmit = (e) => {
    e.preventDefault();
    const fechaCompra = new Date().toISOString();
    onConfirmar(vuelo.id, nombreComprador, fechaCompra);
  };

  return (
    <form
      onSubmit={handleSubmit}
      className="card p-4 shadow-sm mb-4"
      style={{ backgroundColor: '#C5DDD6', color: '#0c262e' }}
    >
      <h5 className="card-title mb-3">Confirmar Compra</h5>
      <div className="mb-3">
        <center><p className="form-control-plaintext ps-2 fw-bold">{nombreComprador}</p></center>
      </div>

      <button
        className="btn"
        type="submit"
        style={{
          backgroundColor: '#677567',
          color: 'white',
          width: '120px',
          display: 'block',
          margin: '0 auto',
        }}
      >
        Comprar
      </button>
    </form>
  );
};

export default CompraForm;
