package example.keanconsolacion.bottomnavigationactivitysample.Model;

import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/*This class updates Data class and is used to fetch new data (api calls)*/
public class APIHelper extends AsyncTask<String, String, String> {

    CryptoAdapter cryptoAdapter;
    WalletAdapter walletAdapter;
    String intent;

    public APIHelper(WalletAdapter walletAdapter, String intent){
        this.walletAdapter = walletAdapter;
        this.intent = intent;
    }

    public APIHelper(CryptoAdapter cryptoAdapter, String intent){
        this.cryptoAdapter = cryptoAdapter;
        this.intent = intent;
    }


    //Gets data from CoinAPI and resets Data.currencies
    @Override
    protected String doInBackground(String... strings) {
        Data.currencies.clear();
        Log.d("API_HELPER", "doInBackground is running");
        try{
            String responseText;
            String url = "https://rest.coinapi.io/v1/exchangerate/USD" +
                    "?filter_asset_id=BTC,ETH,XRP,XLM,USDT,ADA,LTC,LINK,BCH,USDC" +
                    "&invert=true" +
                    "&apikey=2B2209DC-FB53-47D8-ABC8-17A832E5C1E9";

            URL endPoint = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) endPoint.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            Log.d("RESPONSE-CODE", String.valueOf(responseCode));
            if(responseCode == 200){
                InputStream responseBody = connection.getInputStream();
                InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
                JsonReader jsonReader = new JsonReader(responseBodyReader);

                jsonReader.beginObject(); //start object
                jsonReader.skipValue(); //skip asset_id_base
                jsonReader.skipValue(); //skip "USD"
                String name = jsonReader.nextName();

                if(name.equals("rates")){
                    jsonReader.beginArray(); //begin rates
                    while (jsonReader.hasNext()){
                        jsonReader.beginObject();
                        Currency currency = new Currency();
                        while (jsonReader.hasNext()){
                            String key2 = jsonReader.nextName();
                            if(key2.equals("asset_id_quote")){
                                String value = jsonReader.nextString();
                                currency.setQuote(value);
                                currency.setImage();
                            }else if(key2.equals("rate")) {
                                double value = jsonReader.nextDouble();
                                currency.setRate(String.valueOf(value));
                                jsonReader.endObject();
                                Data.currencies.add(currency);
                                break;
                            }else{
                                jsonReader.skipValue();
                            }
                        }
                    }
                }
            }
        }catch(Exception e){
            Log.d("ASYNC TASK", e.getMessage());
        }
        return null;
    }

    /*If an adapter is associated within the class, it will notify on data change.*/
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.d("REQUEST STATUS", "DONE-FINISHED");

        if (intent.equals("HOME-REFRESH")){
            Log.d("ADAPTER STATUS", "Notifying crypto adapter");
            cryptoAdapter.notifyDataSetChanged();
        }else if (intent.equals("WALLET-REFRESH")){
            Log.d("ADAPTER STATUS", "Notifying wallet adapter");
            for (Wallet wallet: Data.wallets) {
                wallet.recalculate();
            }
            walletAdapter.notifyDataSetChanged();
        }
    }

}

