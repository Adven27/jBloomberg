package com.assylias.jbloomberg.mock.market;

import com.assylias.jbloomberg.mock.AbstractMessage;
import com.assylias.jbloomberg.mock.AbstractSubscription;
import com.bloomberglp.blpapi.Element;
import com.bloomberglp.blpapi.Name;

public class MarketMessageSubscriptionCanceled extends AbstractMessage {
    private final String _topicName;
    private final MarketElementSubscriptionCancelReason _reason;

    MarketMessageSubscriptionCanceled(AbstractSubscription sub) {
        super(new Name("SubscriptionTerminated"), sub.correlationID(), null);

        this._topicName = sub.security();
        this._reason = new MarketElementSubscriptionCancelReason();
    }

    public int numElements() {
        return 1;
    }

    public String topicName() {
        return this._topicName;
    }

    public boolean hasElement(String name, boolean excludeNullElements) {
        return this._reason.name().toString().equals(name);
    }

    public Element getElement(String name) {
        if (name.equals(this._reason.name().toString()))
            return this._reason;

        else
            return super.getElement(name);
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(String.format("%s = {%s", this.messageType().toString(), System.getProperty("line.separator")));
        try {
            result.append(this._reason.prettyPrint(1));
        } catch (Exception ignored) {
        }
        result.append("}").append(System.getProperty("line.separator"));

        return result.toString();
    }
}
