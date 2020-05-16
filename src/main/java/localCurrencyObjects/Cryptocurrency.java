package localCurrencyObjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cryptocurrency {
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("rate")
    @Expose
    private Double rate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
