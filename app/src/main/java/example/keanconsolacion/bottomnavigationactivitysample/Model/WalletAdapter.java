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

public class WalletAdapter extends RecyclerView.Adapter<WalletAdapter.WalletViewHolder>{
    Context context;
    List<Wallet> wallets;

    public WalletAdapter(Context ctx, List<Wallet> wallets){
        this.context = ctx;
        this.wallets = wallets;
    }

    @NonNull
    @Override
    public WalletViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.wallet_row, parent, false);
        return new WalletViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WalletViewHolder holder, int position) {
        DecimalFormat df = new DecimalFormat("#0.00");
        df.setMaximumFractionDigits(6);

        String base = wallets.get(position).getBase();
        String quote = wallets.get(position).getQuote();
        String value = df.format(wallets.get(position).getValue());
        String rateThen = String.valueOf(wallets.get(position).getRateThen());
        String rateNow = String.valueOf(Helper.limitDouble(wallets.get(position).getRateNow()));
        String gainLoss = df.format(wallets.get(position).getGainLoss());
        String baseDeposit = String.valueOf(wallets.get(position).getBaseDeposit());

        if(wallets.get(position).getGainLoss() > 0){
            holder.walletGainLoss.setTextColor(0xFF4CAF50);
        }else if (wallets.get(position).getGainLoss() < 0){
            holder.walletGainLoss.setTextColor(0xFFEA1616);
        }

        holder.walletBase.setText(base);
        holder.walletQuote.setText(quote);
        holder.walletValue.setText(value + " " + quote);
        holder.walletRateThen.setText(rateThen);
        holder.walletRateNow.setText(rateNow);
        holder.walletGainLoss.setText(gainLoss+" USD");
        holder.walletBaseDeposit.setText(baseDeposit + " USD");
        holder.imageView.setImageResource(wallets.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return wallets.size();
    }

    public class WalletViewHolder extends RecyclerView.ViewHolder{
        TextView walletBase;
        TextView walletQuote;
        TextView walletValue;
        TextView walletRateThen;
        TextView walletRateNow;
        TextView walletGainLoss;
        TextView walletBaseDeposit;
        ImageView imageView;

        public WalletViewHolder(@NonNull View itemView) {
            super(itemView);
            walletBase = itemView.findViewById(R.id.walletBase);
            walletQuote = itemView.findViewById(R.id.walletQuote);
            walletValue = itemView.findViewById(R.id.walletValue);
            walletRateThen = itemView.findViewById(R.id.walletRateThen);
            walletRateNow = itemView.findViewById(R.id.walletRateNow);
            walletGainLoss = itemView.findViewById(R.id.walletGainLoss);
            walletBaseDeposit = itemView.findViewById(R.id.walletBaseDeposit);
            imageView = itemView.findViewById(R.id.walletImage);
        }
    }
}
