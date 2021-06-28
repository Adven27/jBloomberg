package com.assylias.jbloomberg.mock.market;

import com.assylias.jbloomberg.mock.AbstractElement;
import com.bloomberglp.blpapi.Name;

public class MarketElementSubscriptionCanceled extends AbstractElement {
    private final MarketElementSubscriptionCancelReason _reason;

    MarketElementSubscriptionCanceled(MarketMessageSubscriptionCanceled arg) {
        this._reason = (MarketElementSubscriptionCancelReason) arg.getElement("reason");
    }

    public Name name() {
        return new Name("SubscriptionTerminated");
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

    protected StringBuilder prettyPrint(int tabIndent) {
        
        StringBuilder result = new StringBuilder();

        result.append(String.format("%s%s = {%s", TAB, this.name().toString(), System.getProperty("line.separator")));
        result.append(this._reason.prettyPrint(tabIndent + 1));
        result.append(String.format("%s}%s", TAB, System.getProperty("line.separator")));

        return result;
    }


}
