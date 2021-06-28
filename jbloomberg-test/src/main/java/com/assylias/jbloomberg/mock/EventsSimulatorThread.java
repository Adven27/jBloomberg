package com.assylias.jbloomberg.mock;

import com.assylias.jbloomberg.mock.historic.HistoricRequest;
import com.assylias.jbloomberg.mock.reference.ReferenceRequest;
import com.bloomberglp.blpapi.*;
import com.bloomberglp.blpapi.test.MessageProperties;
import com.bloomberglp.blpapi.test.TestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static com.bloomberglp.blpapi.Event.EventType.*;
import static java.util.stream.Collectors.toList;

public class EventsSimulatorThread extends Thread {
    private static final Logger logger = LoggerFactory.getLogger(EventsSimulatorThread.class);

    private final MockedSession mockedSession;
    private String schema;
    private List<String> response;

    public EventsSimulatorThread(MockedSession mockedSession) {
        this.mockedSession = mockedSession;
    }

    public void run() {
        try {
            while (!isInterrupted()) {
                List<Event> events = mockedSession.getPendingRequests().entrySet().stream()
                    .flatMap(this::generateEvents)
                    .collect(toList());
                mockedSession.getPendingRequests().clear();
                events.forEach(this::emulateEventReceiving);
                Thread.sleep(200);
            }
        } catch (InterruptedException ignored) {
        }
    }

    private void emulateEventReceiving(Event event) {
        mockedSession.eventHandler().processEvent(event, mockedSession.getBloombergSession());
    }

    private static Service serviceFor(String schema) {
        return TestUtil.deserializeService(new ByteArrayInputStream(schema.getBytes(StandardCharsets.UTF_8)));
    }

    private Stream<Event> generateEvents(Map.Entry<CorrelationID, Request> e) {
        logger.info("Generate response events for correlationId " + e.getKey());

        if (e.getValue() instanceof ReferenceRequest) {
            return mockReferenceDataResponseEvent(e.getKey());
        } else if (e.getValue() instanceof HistoricRequest) {
            return mockHistoricalDataResponseEvent(e.getKey());
        }
        throw new UnsupportedOperationException("Unsupported request type");
    }

    protected Stream<Event> mockReferenceDataResponseEvent(CorrelationID cId) {
        AtomicInteger countDown = new AtomicInteger(response.size());
        return response.stream().map(r -> {
            int count = countDown.decrementAndGet();
            Event event = TestUtil.createEvent(count == 0 ? RESPONSE : PARTIAL_RESPONSE);
            TestUtil.appendMessage(
                event,
                serviceFor(schema).getOperation(Name.getName("ReferenceDataRequest")).responseDefinition(0),
                new MessageProperties().setCorrelationId(cId)
            ).formatMessageXml(r);
            return event;
        });
    }

    protected Stream<Event> mockHistoricalDataResponseEvent(CorrelationID cId) {
        AtomicInteger countDown = new AtomicInteger(response.size());
        return response.stream().map(r -> {
            int count = countDown.decrementAndGet();
            Event event = TestUtil.createEvent(count == 0 ? RESPONSE : PARTIAL_RESPONSE);
            TestUtil.appendMessage(
                event,
                serviceFor(schema).getOperation(Name.getName("HistoricalDataRequest")).responseDefinition(0),
                new MessageProperties().setCorrelationId(cId)
            ).formatMessageXml(r);
            return event;
        });
    }

    public void mockEvent(String schema, List<String> response) {
        this.schema = schema;
        this.response = response;
    }

    public void produceEvent(CorrelationID cId, String schema, String response) {
        emulateEventReceiving(mockMarketDataEvent(schema, response, cId));
    }

    private Event mockMarketDataEvent(String schema, String response, CorrelationID cId) {
        Event event = TestUtil.createEvent(SUBSCRIPTION_DATA);
        TestUtil.appendMessage(
            event,
            serviceFor(schema).getEventDefinition(Name.getName("MarketDataEvents")),
            new MessageProperties().setCorrelationId(cId)
        ).formatMessageXml(response);
        return event;
    }
}
