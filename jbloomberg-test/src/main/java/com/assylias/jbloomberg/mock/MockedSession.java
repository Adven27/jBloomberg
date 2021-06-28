package com.assylias.jbloomberg.mock;

import com.assylias.jbloomberg.BloombergRequestType;
import com.assylias.jbloomberg.BloombergServiceType;
import com.assylias.jbloomberg.DefaultBloombergSession;
import com.assylias.jbloomberg.mock.historic.HistoricRequest;
import com.assylias.jbloomberg.mock.reference.ReferenceRequest;
import com.bloomberglp.blpapi.*;
import com.bloomberglp.blpapi.test.TestUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class MockedSession extends DefaultBloombergSession {
    private final Map<CorrelationID, Request> pendingRequests = new HashMap<>();
    private EventsSimulatorThread eventsSimulatorThread;

    public MockedSession() {
        super();
        Session mock = mock(Session.class);
        try {
            when(mock.openService(anyString())).thenReturn(true);

            doAnswer(invocation -> {
                Service service = mock(Service.class);
                when(service.createRequest(eq(BloombergRequestType.REFERENCE_DATA.toString())))
                    .thenReturn(new ReferenceRequest());
                when(service.createRequest(eq(BloombergRequestType.HISTORICAL_DATA.toString())))
                    .thenReturn(new HistoricRequest());
                return service;
            }).when(mock).getService(BloombergServiceType.REFERENCE_DATA.getUri());

            doAnswer(invocation -> null)
                    .when(mock).getService(BloombergServiceType.MARKET_DATA.getUri());

            doAnswer(invocation -> {
                Request request = invocation.getArgumentAt(0, Request.class);
                CorrelationID cId = invocation.getArgumentAt(2, CorrelationID.class);
                pendingRequests.put(cId, request);
                return cId;
            }).when(mock).sendRequest(any(Request.class), any(Identity.class), any(CorrelationID.class));

            doAnswer(invocation -> {
                Event event = TestUtil.createEvent(Event.EventType.SESSION_STATUS);
                TestUtil.appendMessage(event, TestUtil.getAdminMessageDefinition(Name.getName("SessionStarted")));
                eventHandler().processEvent(event, getBloombergSession());
                eventsSimulatorThread.start();
                return null;
            }).when(mock).startAsync();

            doAnswer(invocation -> {
                eventsSimulatorThread.interrupt();
                return null;
            }).when(mock).stop();
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
        setSession(mock);
        this.eventsSimulatorThread = new EventsSimulatorThread(this);
    }

    public Map<String, CorrelationID> tickerSubscriptions() {
        return getTickerSubscriptions();
    }

    public void mockRequestResponse(String scheme, List<String> response) {
        eventsSimulatorThread.mockEvent(scheme, response);
    }

    public void mockRequestResponse(List<String> response) {
        eventsSimulatorThread.mockEvent(REF_SCHEME, response);
    }
    
    /**
     *
     * @param response <pre> {@code <MarketDataEvents><LAST_PRICE>11.1</LAST_PRICE><ASK>11.2</ASK></MarketDataEvents> }
     */
    public void mockMarketDataEvent(CorrelationID cId, String scheme, String response) {
        eventsSimulatorThread.produceEvent(cId, scheme, response);
    }

    /**
     *
     * @param response <pre> {@code <MarketDataEvents><LAST_PRICE>11.1</LAST_PRICE><ASK>11.2</ASK></MarketDataEvents> }
     */
    public void mockMarketDataEvent(CorrelationID cId, String response) {
        eventsSimulatorThread.produceEvent(cId, MKT_SCHEME, response);
    }

    Map<CorrelationID, Request> getPendingRequests() {
        return pendingRequests;
    }

    EventHandler eventHandler() {
        return eventHandler;
    }

    @Override
    protected void startBbProcessIfLocal() {
        //DO NOTHING
    }
    
    private static final String REF_SCHEME = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" +
        "<ServiceDefinition name=\"blp.refdata\" version=\"1.0.1.0\">" +
        "    <service name=\"//blp/refdata\" version=\"1.0.0.0\">" +
        "        <operation name=\"ReferenceDataRequest\" serviceId=\"84\">" +
        "            <request>ReferenceDataRequest</request>" +
        "            <response>Response</response>" +
        "            <responseSelection>ReferenceDataResponse</responseSelection>" +
        "        </operation>" +
        "        <operation name=\"HistoricalDataRequest\" serviceId=\"84\">" +
        "            <request>HistoricalDataRequest</request>" +
        "            <response>Response</response>" +
        "            <responseSelection>HistoricalDataResponse</responseSelection>" +
        "        </operation>" +
        "    </service>" +
        "    <schema>" +
        "        <sequenceType name=\"ReferenceDataRequest\">" +
        "            <element name=\"securities\" type=\"String\" maxOccurs=\"unbounded\"/>" +
        "            <element name=\"fields\" type=\"String\" maxOccurs=\"unbounded\"/>" +
        "            <element name=\"overrides\" type=\"FieldOverride\" minOccurs=\"0\" maxOccurs=\"unbounded\"/>" +
        "        </sequenceType>" +
        "        <sequenceType name=\"HistoricalDataRequest\">" +
        "            <element name=\"securities\" type=\"String\" maxOccurs=\"unbounded\"/>" +
        "            <element name=\"fields\" type=\"String\" maxOccurs=\"unbounded\"/>" +
        "        </sequenceType>" +
        "        <sequenceType name=\"FieldOverride\">" +
        "            <element name=\"fieldId\" type=\"String\"/>" +
        "            <element name=\"value\" type=\"String\"/>" +
        "        </sequenceType>" +
        "        <choiceType name=\"Response\">" +
        "            <element name=\"ReferenceDataResponse\" type=\"ReferenceDataResponseType\">" +
        "                <cacheable>true</cacheable>" +
        "                <cachedOnlyOnInitialPaint>false</cachedOnlyOnInitialPaint>" +
        "            </element>" +
        "            <element name=\"HistoricalDataResponse\" type=\"HistoricalDataResponseType\">" +
        "                <cacheable>true</cacheable>" +
        "                <cachedOnlyOnInitialPaint>false</cachedOnlyOnInitialPaint>" +
        "            </element>" +
        "        </choiceType>" +
        "        <sequenceType name=\"HistoricalDataResponseType\">" +
        "            <element name=\"responseError\" type=\"ErrorInfo\" minOccurs=\"0\"/>" +
        "            <element name=\"securityData\" type=\"HistoricalSecurityData\" minOccurs=\"1\" maxOccurs=\"1\"/>" +
        "        </sequenceType>" +
        "        <sequenceType name=\"ReferenceDataResponseType\">" +
        "            <element name=\"responseError\" type=\"ErrorInfo\" minOccurs=\"0\"/>" +
        "            <element name=\"securityData\" type=\"ReferenceSecurityData\" minOccurs=\"1\" maxOccurs=\"unbounded\"/>" +
        "        </sequenceType>" +
        "        <sequenceType name=\"HistoricalSecurityData\">" +
        "            <element name=\"security\" type=\"String\"/>" +
        "            <element name=\"eidData\" type=\"Int64\" minOccurs=\"0\" maxOccurs=\"unbounded\"/>" +
        "            <element name=\"securityError\" type=\"ErrorInfo\" minOccurs=\"0\" maxOccurs=\"1\"/>" +
        "            <element name=\"fieldExceptions\" type=\"FieldException\" minOccurs=\"0\" maxOccurs=\"unbounded\"/>" +
        "            <element name=\"sequenceNumber\" type=\"Int64\" minOccurs=\"0\" maxOccurs=\"1\"/>" +
        "            <element name=\"fieldData\" type=\"HistoricalFieldData\" minOccurs=\"1\" maxOccurs=\"unbounded\"/>" +
        "        </sequenceType>" +
        "        <sequenceType name=\"ReferenceSecurityData\">" +
        "            <element name=\"security\" type=\"String\"/>" +
        "            <element name=\"eidData\" type=\"Int64\" minOccurs=\"0\" maxOccurs=\"unbounded\"/>" +
        "            <element name=\"securityError\" type=\"ErrorInfo\" minOccurs=\"0\" maxOccurs=\"1\"/>" +
        "            <element name=\"fieldExceptions\" type=\"FieldException\" minOccurs=\"0\" maxOccurs=\"unbounded\"/>" +
        "            <element name=\"sequenceNumber\" type=\"Int64\" minOccurs=\"0\" maxOccurs=\"1\"/>" +
        "            <element name=\"fieldData\" type=\"FieldData\"/>" +
        "        </sequenceType>" +
        "        <sequenceType name=\"FieldData\">" +
        "            <description>The contents of this type depends on the response</description>" +
        "            <element name=\"BID\" type=\"Float64\"/>" +
        "            <element name=\"PX_LAST\" type=\"Float64\"/>" +
        "        </sequenceType>" +
        "        <sequenceType name=\"HistoricalFieldData\">" +
        "            <description>The contents of this type depends on the response, date is required</description>" +
        "            <element name=\"date\" type=\"Date\" minOccurs=\"1\"/>" +
        "            <element name=\"BID\" type=\"Float64\"/>" +
        "            <element name=\"PX_LAST\" type=\"Float64\"/>" +
        "        </sequenceType>" +
        "        <sequenceType name=\"FieldException\">" +
        "            <element name=\"fieldId\" type=\"String\"/>" +
        "            <element name=\"errorInfo\" type=\"ErrorInfo\"/>" +
        "        </sequenceType>" +
        "        <sequenceType name=\"ErrorInfo\">" +
        "            <element name=\"source\" type=\"String\"/>" +
        "            <element name=\"code\" type=\"Int64\"/>" +
        "            <element name=\"category\" type=\"String\"/>" +
        "            <element name=\"message\" type=\"String\"/>" +
        "            <element name=\"subcategory\" type=\"String\" minOccurs=\"0\" maxOccurs=\"1\"/>" +
        "        </sequenceType>" +
        "    </schema>" +
        "</ServiceDefinition>";
    
    
    private static final String MKT_SCHEME = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" +
        "<ServiceDefinition name=\"blp.mktdata\" version=\"1.0.1.0\">" +
        "   <service name=\"//blp/mktdata\" version=\"1.0.0.0\" authorizationService=\"//blp/apiauth\">" +
        "      <event name=\"MarketDataEvents\" eventType=\"MarketDataUpdate\">" +
        "         <eventId>0</eventId>" +
        "         <eventId>1</eventId>" +
        "         <eventId>2</eventId>" +
        "         <eventId>3</eventId>" +
        "         <eventId>4</eventId>" +
        "         <eventId>9999</eventId>" +
        "      </event>" +
        "      <defaultServiceId>134217729</defaultServiceId> <!-- 0X8000001 -->" +
        "      <resolutionService></resolutionService>" +
        "      <recapEventId>9999</recapEventId>" +
        "   </service>" +
        "   <schema>" +
        "      <sequenceType name=\"MarketDataUpdate\">" +
        "         <description>fields in subscription</description>" +
        "         <element name=\"LAST_PRICE\" type=\"Float64\" id=\"1\" minOccurs=\"0\" maxOccurs=\"1\">" +
        "            <description>Last Trade/Last Price</description>" +
        "            <alternateId>65536</alternateId>" +
        "         </element>" +
        "         <element name=\"BID\" type=\"Float64\" id=\"2\" minOccurs=\"0\" maxOccurs=\"1\">" +
        "            <description>Bid Price</description>" +
        "            <alternateId>131072</alternateId>" +
        "         </element>" +
        "         <element name=\"ASK\" type=\"Float64\" id=\"3\" minOccurs=\"0\" maxOccurs=\"1\">" +
        "            <description>Ask Price</description>" +
        "            <alternateId>196608</alternateId>" +
        "         </element>" +
        "         <element name=\"VOLUME\" type=\"Int64\" id=\"4\" minOccurs=\"0\" maxOccurs=\"1\">" +
        "            <description>Volume</description>" +
        "            <alternateId>458753</alternateId>" +
        "         </element>" +
        "      </sequenceType>" +
        "   </schema>" +
        "</ServiceDefinition>";
}

