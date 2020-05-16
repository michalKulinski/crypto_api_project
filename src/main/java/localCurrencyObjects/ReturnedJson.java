package localCurrencyObjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReturnedJson {
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("rates")
    @Expose
    private List<Cryptocurrency> rates = null;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<Cryptocurrency> getRates() {
        return rates;
    }

    public void setRates(List<Cryptocurrency> rates) {
        this.rates = rates;
    }
}
