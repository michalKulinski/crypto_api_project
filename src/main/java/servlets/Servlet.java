package servlets;

import com.google.gson.Gson;
import localCurrencyObjects.ReturnedJson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import services.reponseAllCurrency;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Hello", urlPatterns = {"/currencies"})
public class Servlet extends HttpServlet {

    private static final Logger LOG = LogManager.getLogger();

    Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        LOG.info("Get rates list by currency name");

        String currency = request.getParameter("currency");
        LOG.info("Chosen currency: " + currency);

        ReturnedJson currencyInfo = reponseAllCurrency.getRateByCurrencyName(currency);

        if (currencyInfo != null) {

            String currencyJsonString = this.gson.toJson(currencyInfo);

            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(currencyJsonString);
            out.flush();
        }

        LOG.info("Rates list successfully displayed");
    }

}
