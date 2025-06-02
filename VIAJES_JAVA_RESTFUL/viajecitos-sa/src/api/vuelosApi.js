import axios from 'axios';

const BASE_URL = 'http://192.168.100.36:8080';

export const buscarVuelos = async (origen, destino, fecha) => {
  const response = await axios.get(`${BASE_URL}/vuelos`, {
    params: { origen, destino, fecha }
  });
  return response.data;
};

export const comprarVuelo = async (vueloId, comprador, fechaCompra) => {
  const response = await axios.post(`${BASE_URL}/compras`, {
    vuelo: { id: vueloId },
    comprador,
    fechaCompra
  });
  return response.data;
};
