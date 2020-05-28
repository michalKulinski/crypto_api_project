package connection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.ApiConfig;

import java.io.IOException;
import java.net.URISyntaxException;

public class ExternalApiConnection {

    private static final Logger LOG = LogManager.getLogger();

    public static String makeAPICall()
            throws URISyntaxException, IOException {

        String response_content = "";

        URIBuilder query = new URIBuilder(ApiConfig.API_URL);
        query.addParameter("CMC_PRO_API_KEY", ApiConfig.getApiKey());

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(query.build());

        request.setHeader(HttpHeaders.ACCEPT, "application/json");

        CloseableHttpResponse response = client.execute(request);

        try {
            LOG.debug("Status response: " + response.getStatusLine());
            HttpEntity entity = response.getEntity();
            response_content = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
        } finally {
            response.close();
        }

        return response_content;
    }
}
