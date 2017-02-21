package goeurolab.search.sort;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static goeurolab.search.sort.JourneySearcher.BERLIN;
import static goeurolab.search.sort.JourneySearcher.PRAGUE;
import static goeurolab.search.sort.JourneySearcher.SORT_BY_PRICE;

public class SortJourneyByPriceTest {


    @Test
    public void shouldSortJourneysByPriceAscending() throws IOException {
        // given
        long previousPrice = 0;
        final String searchId = JourneySearcher.initSearch(BERLIN, PRAGUE);

        // when
        final JsonNode journeys = JourneySearcher.performSearch(searchId, SORT_BY_PRICE);

        // then
        for (JsonNode outbound : journeys) {
            final long price = outbound.get("price").asLong();
            Assert.assertTrue(price + " should be >= than " + previousPrice, price >= previousPrice);
            previousPrice = price;
        }
    }
}
