package com.assylias.jbloomberg.mock.market;

import com.assylias.jbloomberg.mock.AbstractMessage;
import com.bloomberglp.blpapi.Name;

public class MarketMessageSessionOpened extends AbstractMessage {
    MarketMessageSessionOpened() {
        super(new Name("SessionStarted"), null, null);
    }

    public String topicName() {
        return "";
    }
}
