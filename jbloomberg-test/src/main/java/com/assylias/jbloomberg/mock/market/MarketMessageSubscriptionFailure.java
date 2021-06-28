package com.assylias.jbloomberg.mock.market;

import com.assylias.jbloomberg.mock.AbstractMessage;
import com.assylias.jbloomberg.mock.AbstractSubscription;
import com.bloomberglp.blpapi.Element;
import com.bloomberglp.blpapi.Name;

public class MarketMessageSubscriptionFailure extends AbstractMessage {
    private final String _topicName;
    private final MarketElementReason _reason;

    MarketMessageSubscriptionFailure(AbstractSubscription sub) {
        super(new Name("SubscriptionFailure"), sub.correlationID(), null);

        this._topicName = sub.security();
        this._reason = new MarketElementReason(MarketElementReason.ReasonTypeEnum.badSecurity);
    }

    public String topicName() {
        return this._topicName;
    }

    public int numElements() {
        return 1;
    }

    public boolean hasElement(String name, boolean excludeNullElements) {
        return this._reason.name().toString().equals(name);
    }

    public Element getElement(String name) {
        return this._reason.name().toString().equals(name) ? this._reason : super.getElement(name);
    }

    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append(String.format("SubscriptionFailure = {%s", System.getProperty("line.separator")));
        try {
            result.append(this._reason.prettyPrint(1));
        } catch (Exception ignored) {
        }
        result.append(String.format("}%s", System.getProperty("line.separator")));

        return result.toString();
    }
}
