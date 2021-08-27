package example.keanconsolacion.bottomnavigationactivitysample.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;
import example.keanconsolacion.bottomnavigationactivitysample.R;

public class CryptoAdapter extends RecyclerView.Adapter<CryptoAdapter.MyViewHolder> {

    Context context;
    List<Currency> currencies;

    public CryptoAdapter(Context ctx, List<Currency> currencies){
        this.context = ctx;
        this.currencies = currencies;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rate_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DecimalFormat df = new DecimalFormat("#0.00");
        df.setMaximumFractionDigits(5);

        holder.cryptoBase.setText(currencies.get(position).getBase());
        holder.cryptoQuote.setText(currencies.get(position).getQuote());
        holder.cryptoRate.setText(""+Helper.limitDouble(Double.parseDouble(currencies.get(position).getRate())));
        holder.imageView.setImageResource(currencies.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return currencies.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView cryptoBase;
        TextView cryptoQuote;
        TextView cryptoRate;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cryptoBase = itemView.findViewById(R.id.walletBase);
            cryptoQuote = itemView.findViewById(R.id.walletQuote);
            cryptoRate = itemView.findViewById(R.id.walletRateThen);
            imageView = itemView.findViewById(R.id.walletImage);
        }
    }
}
