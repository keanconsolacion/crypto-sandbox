package example.keanconsolacion.bottomnavigationactivitysample.Model;

import java.util.HashMap;
import java.util.Map;

import example.keanconsolacion.bottomnavigationactivitysample.R;

public class Currency {
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

    private String base = "USD";
    private String quote = "";
    private String rate = "";
    private int image;

    public String getBase() {
        return base;
    }
    public void setBase(String base) {
        this.base = base;
    }
    public String getQuote() {
        return quote;
    }
    public void setQuote(String quote) {
        this.quote = quote;
    }
    public String getRate() {
        return rate;
    }
    public void setRate(String rate) {
        this.rate = rate;
    }
    public int getImage() {
        return this.image;
    }
    public void setImage() {
        if(!quote.equals("")){
            this.image = imagesMap.get(this.quote);
        }
    }
}
