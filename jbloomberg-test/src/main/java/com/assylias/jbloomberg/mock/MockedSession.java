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

    public void mockMarketDataEvent(CorrelationID cId, String scheme, String response) {
        eventsSimulatorThread.produceEvent(cId, scheme, response);
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
}

