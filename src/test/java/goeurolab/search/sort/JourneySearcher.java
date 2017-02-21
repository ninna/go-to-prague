package goeurolab.search.sort;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;

import java.io.IOException;
import java.nio.charset.Charset;

public class JourneySearcher {

    public final static String BERLIN = "376217";
    public final static String PRAGUE = "375859";
    private final static String LIMIT = "100";
    public final static String SORT_BY_PRICE = "price";
    private static final int TIMEOUT_IN_MS = 5000;

    public static String initSearch(String from, String to) throws IOException {
        String s = "{\"searchOptions\":{\"departurePosition\":{\"id\":" + from + "},"
                + "\"arrivalPosition\":{\"id\":" + to + "},\"travelModes\":[\"Train\"],"
                + "\"departureDate\":\"2017-03-01\",\"passengers\":[{\"age\":24,\"discountCards\":[]}],"
                + "\"userInfo\":{\"identifier\":\"" + System.currentTimeMillis() + "\","
                + "\"domain\":\".com\",\"locale\":\"en\",\"currency\":\"EUR\"},"
                + "\"abTestParameters\":[]}}";
        StringEntity myEntity = new StringEntity(s, ContentType.create("application/json", "UTF-8"));

        final Content content = Request.Post("http://www.goeuro.com/GoEuroAPI/rest/api/v5/searches").body(myEntity)
                .socketTimeout(TIMEOUT_IN_MS).execute().returnContent();
        return new ObjectMapper().readTree(content.asString()).get("searchId").asText();
    }

    public static JsonNode performSearch(String searchId, String sort_by) throws IOException {
        Charset charset = Charset.defaultCharset();

        String s2 = "http://www.goeuro.com/GoEuroAPI/rest/api/v5/results?price_from=1&"
                + "travel_mode=train&limit=" + LIMIT + "&offset=0&position_report_enabled=true&"
                + "all_positions=true&search_id=" + searchId + "&"
                + "sort_by=" + sort_by + "&sort_variants=inboundDepartureTime&"
                + "use_stats=true&defaultSorting=outboundDepartureTime&ts=" + System.currentTimeMillis();

        Content returnContent = Request.Get(s2).socketTimeout(TIMEOUT_IN_MS).execute().returnContent();

        return new ObjectMapper().readTree(returnContent.asString(charset)).get("outbounds");
    }
}
