package example.keanconsolacion.bottomnavigationactivitysample.ui.wallet;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import example.keanconsolacion.bottomnavigationactivitysample.Model.Data;
import example.keanconsolacion.bottomnavigationactivitysample.Model.WalletAdapter;
import example.keanconsolacion.bottomnavigationactivitysample.R;
import example.keanconsolacion.bottomnavigationactivitysample.ui.dialog.AddWalletDialogFragment;

public class WalletFragment extends Fragment {

    RecyclerView walletRecyclerView;
    Button walletRefreshButton;
    FloatingActionButton walletFAB;
    WalletAdapter walletAdapter;
    ProgressBar progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_wallet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //bind views
        progressBar = view.findViewById(R.id.walletProgressBar);
        walletRecyclerView = view.findViewById(R.id.walletRecyclerView);
        walletRefreshButton = view.findViewById(R.id.walletRefreshButton);
        walletRefreshButton.setOnClickListener(this::refreshButton);
        walletFAB = view.findViewById(R.id.walletFab);
        walletFAB.setOnClickListener(this::addWalletBottomSheetDialog);
        progressBar.setVisibility(View.INVISIBLE);

        //set layout
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        walletRecyclerView.setLayoutManager(layoutManager);

        //set adapter
        walletAdapter = new WalletAdapter(getActivity(), Data.wallets);
        walletRecyclerView.setAdapter(walletAdapter);
    }

    public void addWalletBottomSheetDialog(View view){
        /* Initialize and run a BottomSheetDialogFragment*/
        AddWalletDialogFragment addWalletDialogFragment = AddWalletDialogFragment.newInstance(walletAdapter);
        addWalletDialogFragment.show(getParentFragmentManager(), "add_Wallet_Dialog_Fragment");
    }
    
    public void refreshButton(View view){
        showProgressBar();
        loadData();
        Toast.makeText(getActivity(), "Refreshing Wallets", Toast.LENGTH_SHORT).show();
    }

    public void loadData(){
        Data.fetchNewCurrencyData(walletAdapter);
    }

    public void showProgressBar(){
        progressBar.setVisibility(View.VISIBLE);
        CountDownTimer mCountDownTimer;

        mCountDownTimer=new CountDownTimer(3000,1000) {
            @Override
            public void onTick(long millisUntilFinished) { }
            @Override
            public void onFinish(){
                progressBar.setVisibility(View.INVISIBLE);
            }
        };

        mCountDownTimer.start();
    }
}