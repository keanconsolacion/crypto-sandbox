package example.keanconsolacion.bottomnavigationactivitysample.Model;

import java.util.ArrayList;
import java.util.List;

public class Data {
    public static List<Currency> currencies;
    public static List<Wallet> wallets;

    static {
        currencies = new ArrayList<>();
        wallets = new ArrayList<>();
    }

    public static void fetchNewCurrencyData(WalletAdapter walletAdapter){
        /*Upon running this method, currencies will be wiped and replace with another.*/
        APIHelper apiRequest = new APIHelper(walletAdapter, "WALLET-REFRESH");
        apiRequest.execute();
    }

    public static void fetchNewCurrencyData(CryptoAdapter cryptoAdapter){
        /*Upon running this method, currencies will be wiped and replace with another.*/
        APIHelper apiRequest = new APIHelper(cryptoAdapter, "HOME-REFRESH");
        apiRequest.execute();
    }

}
