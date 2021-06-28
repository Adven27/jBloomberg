package com.assylias.jbloomberg.mock.market;

import com.assylias.jbloomberg.mock.AbstractElement;
import com.bloomberglp.blpapi.Name;
import com.bloomberglp.blpapi.Schema;

public class MarketElementSubscriptionStarted extends AbstractElement {
    @SuppressWarnings("unused")
    private final MarketElementNull _exceptions;

    MarketElementSubscriptionStarted(MarketMessageSubscriptionStarted arg) {
        this._exceptions = (MarketElementNull) arg.getElement("exceptions");
    }

    public Name name() {
        return new Name("SubscriptionStarted");
    }

    public int numValues() {
        return 1;
    }

    public int numElements() {
        return 1;
    }

    public boolean isComplexType() {
        return true;
    }

    public boolean isArray() {
        return false;
    }

    public boolean isNull() {
        return false;
    }

    public Schema.Datatype datatype() {
        return Schema.Datatype.SEQUENCE;
    }

    protected StringBuilder prettyPrint(int tabIndent) {
        return new StringBuilder();
    }

    public String toString() {
        return String.format("SubscriptionStarted = {%s}%s", System.getProperty("line.separator"), System.getProperty("line.separator"));
    }
}
