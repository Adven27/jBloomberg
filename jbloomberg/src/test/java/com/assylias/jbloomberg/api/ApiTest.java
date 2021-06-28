package com.assylias.jbloomberg.api;

import com.assylias.jbloomberg.*;
import com.assylias.jbloomberg.mock.MockedSession;
import com.bloomberglp.blpapi.CorrelationID;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.lang.ClassLoader.getSystemResource;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.Arrays.asList;
import static java.util.concurrent.TimeUnit.SECONDS;
import static java.util.stream.Collectors.*;
import static java.util.stream.LongStream.rangeClosed;
import static org.awaitility.Awaitility.await;
import static org.testng.Assert.assertEquals;

@Test(groups = "unit")
public class ApiTest {
    private static final MockedSession session = new MockedSession();
    private final Table<String, String, Double> securitiesToLookUp = securities();

    private static Table<String, String, Double> securities() {
        Table<String, String, Double> table = HashBasedTable.create();
        table.put("SEC1", "PX_LAST", 10.1d);
        table.put("SEC1", "BID", 10.2d);
        table.put("SEC2", "PX_LAST", 20.1d);
        table.put("SEC2", "BID", 20.2d);
        return table;
    }

    @BeforeClass
    public void beforeClass() throws Exception {
        session.start();
    }

    @AfterClass
    public void afterClass() {
        session.stop();
    }

    @Test
    public void test_ReferenceExample() throws Exception {
        session.mockRequestResponse(
            readFile("refdataSchema.xml"),
            asList("<Response>" + securityToXml(securitiesToLookUp.rowMap()) + "</Response>")
        );

        ReferenceData actual = session.submit(
            new ReferenceRequestBuilder(
                securitiesToLookUp.rowKeySet(), securitiesToLookUp.columnKeySet()
            ).addOverride("EQY_FUND_CRNCY", "JPY")
        ).get(5, SECONDS);

        assertEquals(actual.forSecurity("SEC1").forField("PX_LAST").asDouble(), 10.1d);
        assertEquals(actual.forSecurity("SEC1").forField("BID").asDouble(), 10.2d);
        assertEquals(actual.forSecurity("SEC2").forField("PX_LAST").asDouble(), 20.1d);
        assertEquals(actual.forSecurity("SEC2").forField("BID").asDouble(), 20.2d);
    }

    @Test
    public void test_HistoricalExample() throws Exception {
        LocalDate from = LocalDate.of(2021, 6, 26);
        LocalDate to = LocalDate.of(2021, 6, 28);
        session.mockRequestResponse(
            readFile("refdataSchema.xml"),
            //Historical request returns separate response for each security
            securitiesToLookUp.rowKeySet().stream().map(sec ->
                "<Response>"
                    + "<securityData>"
                    + "<security>" + sec + "</security>"
                    + fieldsForPeriod(from, to, sec)
                    + "</securityData>"
                    + "</Response>").collect(toList())
        );

        HistoricalData actual = session.submit(
            new HistoricalRequestBuilder(asList("SEC1", "SEC2"), asList("PX_LAST", "BID"), from, to)
                .fill(HistoricalRequestBuilder.Fill.NIL_VALUE)
                .days(HistoricalRequestBuilder.Days.ALL_CALENDAR_DAYS)
        ).get();

        assertEquals(actual.toString(), "[DATA]{" +
            "[SEC1]{" +
            "2021-06-26={BID=10.2, PX_LAST=10.1}, " +
            "2021-06-27={BID=10.2, PX_LAST=10.1}, " +
            "2021-06-28={BID=10.2, PX_LAST=10.1}}" +
            "[SEC2]{" +
            "2021-06-26={BID=20.2, PX_LAST=20.1}, " +
            "2021-06-27={BID=20.2, PX_LAST=20.1}, " +
            "2021-06-28={BID=20.2, PX_LAST=20.1}}}");
    }

    private String fieldsForPeriod(LocalDate from, LocalDate to, String sec) {
        return rangeClosed(0, DAYS.between(from, to)).mapToObj(from::plusDays)
            .map(d -> "<fieldData> <date>" + d + "</date>" + fieldsToXml(securitiesToLookUp.row(sec)) + "</fieldData>")
            .collect(joining());
    }

/*
    @Test
    public void test_IntradayBarExample() throws Exception {
        OffsetDateTime now = OffsetDateTime.now();
        RequestBuilder<IntradayBarData> hrb = new IntradayBarRequestBuilder("SPX Index", now.minusDays(7), now)
            .adjustSplits()
            .fillInitialBar()
            .period(1, TimeUnit.HOURS);
        IntradayBarData result = session.submit(hrb).get();
        Map<OffsetDateTime, TypedObject> data = result.forField(IntradayBarField.CLOSE).get();
        for (Map.Entry<OffsetDateTime, TypedObject> e : data.entrySet()) {
            OffsetDateTime dt = e.getKey();
            double price = e.getValue().asDouble();
            System.out.println("[" + dt + "] " + price);
        }
    }

    @Test
    public void test_IntradayTickExample() throws Exception {
        OffsetDateTime now = OffsetDateTime.now();
        RequestBuilder<IntradayTickData> hrb = new IntradayTickRequestBuilder("SPX Index", now.minusDays(3), now)
            .includeBrokerCodes()
            .includeConditionCodes();
        IntradayTickData result = session.submit(hrb).get();
        Multimap<OffsetDateTime, TypedObject> data = result.forField(IntradayTickField.VALUE);
        for (Map.Entry<OffsetDateTime, TypedObject> e : data.entries()) {
            OffsetDateTime dt = e.getKey();
            double price = e.getValue().asDouble();
            System.out.println("[" + dt + "] " + price);
            break; //to keep test short
        }
    }
*/

    @Test
    public void test_SubscriptionExample() throws BloombergException {
        Table<String, RealtimeField, Double> tickersToLookUp = HashBasedTable.create();
        tickersToLookUp.put("SEC1", RealtimeField.LAST_PRICE, 10.1d);
        tickersToLookUp.put("SEC1", RealtimeField.ASK, 10.2d);
        tickersToLookUp.put("SEC2", RealtimeField.LAST_PRICE, 20.1d);
        tickersToLookUp.put("SEC2", RealtimeField.ASK, 20.2d);

        List<DataChangeEvent> actual = new LinkedList<>();
        session.subscribe(
            new SubscriptionBuilder()
                .addSecurities(tickersToLookUp.rowKeySet())
                .addFields(tickersToLookUp.columnKeySet())
                .addListener(actual::add)
        );

        session.tickerSubscriptions().forEach((ticker, corId) -> {
            produceEvent(corId, incrementFieldValues(tickersToLookUp.row(ticker), 1));
            produceEvent(corId, incrementFieldValues(tickersToLookUp.row(ticker), 2));
        });

        await().atMost(2, SECONDS).until(() -> actual.size() == 8);

        assertEquals(
            actual.stream().map(DataChangeEvent::toString).collect(toList()),
            asList(
                "DataChangeEvent{SEC1,LAST_PRICE: null==>11.1}",
                "DataChangeEvent{SEC1,ASK: null==>11.2}",
                "DataChangeEvent{SEC1,LAST_PRICE: 11.1==>12.1}",
                "DataChangeEvent{SEC1,ASK: 11.2==>12.2}",
                "DataChangeEvent{SEC2,LAST_PRICE: null==>21.1}",
                "DataChangeEvent{SEC2,ASK: null==>21.2}",
                "DataChangeEvent{SEC2,LAST_PRICE: 21.1==>22.1}",
                "DataChangeEvent{SEC2,ASK: 21.2==>22.2}"
            )
        );
    }

    private static Map<String, Double> incrementFieldValues(Map<RealtimeField, Double> row, int increment) {
        return row.entrySet().stream().collect(toMap(f -> f.getKey().name(), f -> f.getValue() + increment));
    }

    private static void produceEvent(CorrelationID cId, Map<String, Double> fields) {
        session.mockMarketDataEvent(
            cId,
            readFile("mktdataSchema.xml"),
            "<MarketDataEvents>" + fieldsToXml(fields) + "</MarketDataEvents>"
        );
    }

    private static String securityToXml(Map<String, Map<String, Double>> securities) {
        return securities.entrySet().stream().map(s ->
            "<securityData>"
                + "<security>" + s.getKey() + "</security>"
                + "<fieldData>" + fieldsToXml(s.getValue()) + "</fieldData>"
                + "</securityData>"
        ).collect(joining());
    }

    private static String fieldsToXml(Map<String, Double> fields) {
        return fields.entrySet().stream()
            .map(f -> "<" + f.getKey() + ">" + f.getValue() + "</" + f.getKey() + ">")
            .collect(joining());
    }

    private static String readFile(String file) {
        try (Stream<String> stream = Files.lines(Paths.get(getSystemResource(file).toURI()), UTF_8)) {
            return stream.collect(joining());
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
