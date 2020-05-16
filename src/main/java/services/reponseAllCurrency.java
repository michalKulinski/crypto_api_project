package services;

import coinMarketCapObjects.CurrenciesNotification;
import coinMarketCapObjects.Datum;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import connection.ExternalApiConnection;
import localCurrencyObjects.Cryptocurrency;
import localCurrencyObjects.ReturnedJson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.StrictMath.round;

public class reponseAllCurrency {

    private static final Logger LOG = LogManager.getLogger();

    public static ReturnedJson getRateByCurrencyName(String currencyName){
        ReturnedJson returnedJson = new ReturnedJson();
        List<Cryptocurrency> rates = new ArrayList<>();
        String jsonResult = null;

        try {
            LOG.debug("Get JsonResult from External API");
            jsonResult = ExternalApiConnection.makeAPICall();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        LOG.debug("Make object from Json");
        CurrenciesNotification currencyInfoObject = toEntity(jsonResult);

        Double currencyPrice = 1.0;

        LOG.debug("Prepare response object");



        for (Datum str : currencyInfoObject.getData()){
            if(str.getSymbol().equals(currencyName)){
                returnedJson.setSource(currencyName);
                currencyPrice = str.getQuote().getUSD().getPrice();
            }
        }

        for (Datum str : currencyInfoObject.getData()) {
            if(!str.getSymbol().equals(currencyName)) {
                Cryptocurrency cryptoCurrency = new Cryptocurrency();
                cryptoCurrency.setName(str.getSymbol());
                Double currentRate = currencyPrice / str.getQuote().getUSD().getPrice();
                cryptoCurrency.setRate(currentRate);
                rates.add(cryptoCurrency);
            }
        }
        returnedJson.setRates(rates);
        return returnedJson;
    }

    private static CurrenciesNotification toEntity(String jsonString) {
        try {
            Gson gson = new Gson();
            CurrenciesNotification currencyInfo = gson.fromJson(jsonString, CurrenciesNotification.class);
            return currencyInfo;

        } catch (JsonSyntaxException ex) {
            ex.printStackTrace();
            return null;
        }
    }



}
