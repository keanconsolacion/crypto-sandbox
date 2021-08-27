package example.keanconsolacion.bottomnavigationactivitysample.ui.home;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import example.keanconsolacion.bottomnavigationactivitysample.Model.CryptoAdapter;
import example.keanconsolacion.bottomnavigationactivitysample.Model.Data;
import example.keanconsolacion.bottomnavigationactivitysample.R;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    CryptoAdapter cryptoAdapter;
    ProgressBar progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //bind views
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar =  view.findViewById(R.id.walletProgressBar);
        view.findViewById(R.id.refreshButton).setOnClickListener(this::refreshRates);

        //set layout
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        //set adapter
        cryptoAdapter = new CryptoAdapter(getActivity(), Data.currencies);
        recyclerView.setAdapter(cryptoAdapter);

        //fetch rates and initialize RecyclerView on startup.
        loadData();
    }

    public void refreshRates(View view) {
        loadData();
    }

    public void loadData(){
        showProgressBar();
        Data.fetchNewCurrencyData(cryptoAdapter); //send request
    }

    public void showProgressBar(){
        CountDownTimer mCountDownTimer;
        progressBar.setProgress(0);

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