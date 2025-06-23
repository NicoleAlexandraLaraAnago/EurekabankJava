package modelo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.climov_eurekabank_soap_java.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class MovimientoAdapter extends RecyclerView.Adapter<MovimientoAdapter.ViewHolder> {

    private List<Movimiento> movimientos;
    private final SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

    public MovimientoAdapter(List<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvFecha, tvTipoAccion, tvImporte, tvNroMov;

        public ViewHolder(View itemView) {
            super(itemView);
            tvFecha = itemView.findViewById(R.id.tv_fecha);
            tvTipoAccion = itemView.findViewById(R.id.tv_tipo_accion);
            tvImporte = itemView.findViewById(R.id.tv_importe);
            tvNroMov = itemView.findViewById(R.id.tv_nromov);
        }
    }

    @Override
    public MovimientoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movimiento, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movimiento m = movimientos.get(position);
        holder.tvNroMov.setText("Nro: " + m.getNromov());
        holder.tvFecha.setText(formatoFecha.format(m.getFecha()));
        holder.tvTipoAccion.setText(m.getTipo() + " / " + m.getAccion());
        holder.tvImporte.setText(String.format("$ %.2f", m.getImporte()));
    }

    @Override
    public int getItemCount() {
        return movimientos.size();
    }
}
