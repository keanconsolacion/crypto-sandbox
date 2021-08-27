package example.keanconsolacion.bottomnavigationactivitysample.Model;

import android.util.Log;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import example.keanconsolacion.bottomnavigationactivitysample.R;

public class Wallet {

    public static Map<String, Integer> imagesMap = new HashMap<String, Integer>();

    static{
        imagesMap.put("BTC", R.drawable.bitcoin);
        imagesMap.put("ETH", R.drawable.ethereum);
        imagesMap.put("USDT", R.drawable.tether);
        imagesMap.put("XLM", R.drawable.stellar);
        imagesMap.put("XRP", R.drawable.xrp);
        imagesMap.put("ADA", R.drawable.cardano);
        imagesMap.put("LTC", R.drawable.litecoin);
        imagesMap.put("LINK", R.drawable.chainlink);
        imagesMap.put("BCH", R.drawable.bitcoincash);
        imagesMap.put("USDC", R.drawable.usdcoin);
    }

    private String quote;
    private String base = "USD";
    private double baseDeposit; //usd deposit
    private double value; //get from Data.currencies
    private double rateThen; //get from Data.currencies
    private double rateNow; //get from Data.currencies
    private double gainLoss; //calculate
    private int image;

    public static Wallet newWalletCalculate(String quote, double baseDeposit){
        DecimalFormat df = new DecimalFormat("#0.00");
        df.setMaximumFractionDigits(6);
        Currency referenceCurrency = new Currency();
        Wallet wallet = new Wallet();

        //get current currency using quote
        for(Currency c: Data.currencies){
            if(c.getQuote().equals(quote)){
                referenceCurrency = c;
                break;
            }
        }

        Log.d("RAW_RATE", referenceCurrency.getRate()+"");

        //populate new Wallet using currency.
        double rate = Helper.limitDouble(Double.parseDouble(df.format(Double.parseDouble(referenceCurrency.getRate()))));
        Log.d("RATE", rate+"");
        wallet.setQuote(quote);
        wallet.setBaseDeposit(baseDeposit);
        wallet.setRateThen(rate);
        wallet.setRateNow(rate);
        wallet.setGainLoss(0.0);
        double value = Double.parseDouble(df.format(baseDeposit / rate));
        Log.d("VALUE", value+"");
        wallet.setValue(value);

        return wallet;
    }

    public Wallet recalculate(){
        DecimalFormat df = new DecimalFormat("#0.00");
        df.setMaximumFractionDigits(6);

        /*in order for this to have change, Data.wallets must be refreshed also.*/
        Currency referenceCurrency = new Currency(); //must be updated.

        //get current currency using quote
        for(Currency c: Data.currencies){
            if(c.getQuote().equals(this.quote)){
                referenceCurrency = c;
                break;
            }
        }

        //recalculate value,rate now and gainLoss
        double updatedRate = Double.parseDouble(referenceCurrency.getRate());
        this.value = baseDeposit / updatedRate;
        this.rateNow = updatedRate;
        this.gainLoss = baseDeposit - ((rateThen / rateNow) * baseDeposit);

        return this;
    }

    public String getQuote() {
        return quote;
    }
    public void setQuote(String quote) {
        this.quote = quote;
        if(!quote.equals(""))
            image = imagesMap.get(this.quote);
    }
    public String getBase() {
        return base;
    }
    public double getValue() {
        return value;
    }
    public void setValue(double value) {
        this.value = value;
    }
    public double getGainLoss() {
        return gainLoss;
    }
    public void setGainLoss(double gainLoss) {
        this.gainLoss = gainLoss;
    }
    public double getRateThen() {
        return rateThen;
    }
    public void setRateThen(double rateThen) {
        this.rateThen = rateThen;
    }
    public double getRateNow() {
        return rateNow;
    }
    public void setRateNow(double rateNow) {
        this.rateNow = rateNow;
    }
    public int getImage() {
        return image;
    }
    public double getBaseDeposit() {
        return baseDeposit;
    }
    public void setBaseDeposit(double deposit) {
        this.baseDeposit = deposit;
    }
}
