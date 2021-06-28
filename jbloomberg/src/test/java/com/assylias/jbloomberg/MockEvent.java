package com.assylias.jbloomberg;

import com.bloomberglp.blpapi.Event;
import com.bloomberglp.blpapi.Message;
import com.bloomberglp.blpapi.impl.iY;
import mockit.Mock;

import java.util.Iterator;
import java.util.List;

public class MockEvent extends Event {

  private final EventType type;
  private final List<Message> messages;

  public MockEvent(EventType type, List<Message> messages) {
    this.type = type;
    this.messages = messages;
  }

  @Mock
  public EventType eventType() {
    return type;
  }

  @Mock
  public Iterator<Message> iterator() {
    return messages.iterator();
  }

  @Override
  public boolean isValid() {
    throw new UnsupportedOperationException();
  }

  @Override
  protected iY i() {
    throw new UnsupportedOperationException();
  }
}
