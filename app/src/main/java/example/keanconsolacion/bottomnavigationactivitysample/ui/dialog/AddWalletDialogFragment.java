package example.keanconsolacion.bottomnavigationactivitysample.ui.dialog;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import example.keanconsolacion.bottomnavigationactivitysample.Model.Data;
import example.keanconsolacion.bottomnavigationactivitysample.Model.Wallet;
import example.keanconsolacion.bottomnavigationactivitysample.Model.WalletAdapter;
import example.keanconsolacion.bottomnavigationactivitysample.R;

public class AddWalletDialogFragment extends BottomSheetDialogFragment {

    EditText addNewWalletEditText;
    Spinner addNewWalletSpinner;
    Button addNewWalletButton;
    WalletAdapter walletAdapter;

    public AddWalletDialogFragment(WalletAdapter walletAdapter){
        this.walletAdapter = walletAdapter;
    }

    public static AddWalletDialogFragment newInstance(WalletAdapter walletAdapter) {
        return new AddWalletDialogFragment(walletAdapter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.insert_data_dialog, container, false);
        // get the views and attach the listener
        addNewWalletButton = view.findViewById(R.id.addNewWalletButton);
        addNewWalletSpinner = view.findViewById(R.id.addNewWalletSpinner);
        addNewWalletEditText = view.findViewById(R.id.addNewWalletEditText);
        addNewWalletButton.setOnClickListener(this::onPressAddNewWalletButton);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.crypto_currencies, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        addNewWalletSpinner.setAdapter(adapter);

        return view;
    }

    public void onPressAddNewWalletButton(View view){
        //create new wallet and save to db
        String quoteCurrency = addNewWalletSpinner.getSelectedItem().toString();
        double baseDeposit = Double.parseDouble(addNewWalletEditText.getText().toString());

        Wallet newWallet = Wallet.newWalletCalculate(quoteCurrency, baseDeposit);
        Data.wallets.add(newWallet);

        //refresh wallet recyclerView
        walletAdapter.notifyDataSetChanged();
        this.dismiss();
    }
}
