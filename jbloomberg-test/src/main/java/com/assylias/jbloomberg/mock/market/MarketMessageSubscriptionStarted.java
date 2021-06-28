package com.assylias.jbloomberg.mock.market;

import com.assylias.jbloomberg.mock.AbstractMessage;
import com.assylias.jbloomberg.mock.AbstractSubscription;
import com.assylias.jbloomberg.mock.Rules;
import com.bloomberglp.blpapi.Element;
import com.bloomberglp.blpapi.Name;

import java.util.ArrayList;
import java.util.List;

public class MarketMessageSubscriptionStarted extends AbstractMessage {
    private final String _topicName;
    private final MarketElementNull _exceptionsNull;
    private final MarketElementExceptionsArray _exceptionsBadFields;

    MarketMessageSubscriptionStarted(AbstractSubscription sub) {
        super(new Name("SubscriptionStarted"), sub.correlationID(), null);

        this._topicName = sub.security();

        List<String> badFields = badFields(sub);
        if (badFields.size() == 0) {
            this._exceptionsNull = new MarketElementNull("exceptions");
            this._exceptionsBadFields = null;
        } else {
            this._exceptionsNull = null;
            this._exceptionsBadFields = new MarketElementExceptionsArray(badFields);
        }
    }

    private List<String> badFields(AbstractSubscription sub) {
        List<String> badFields = new ArrayList<>();
        for (int i = sub.fields().size() - 1; i >= 0; i--) {
            if (Rules.isBadField(sub.fields().get(i))) {
                badFields.add(sub.fields().get(i));
                sub.fields().remove(i);
            }
        }
        return badFields;
    }

    public boolean hasElement(String name, boolean excludeNullElements) {
        return (this._exceptionsNull != null && this._exceptionsNull.name().toString().equals(name)) ||
                (this._exceptionsBadFields != null && this._exceptionsBadFields.name().toString().equals(name));
    }

    public Element getElement(String name) {
        if (this._exceptionsNull != null && name.equals(this._exceptionsNull.name().toString()))
            return this._exceptionsNull;

        else if (this._exceptionsBadFields != null && name.equals(this._exceptionsBadFields.name().toString()))
            return this._exceptionsBadFields;

        else
            throw new UnsupportedOperationException("not implemented");
    }

    public int numElements() {
        return 1;
    }

    public String topicName() {
        return this._topicName;
    }

    public String toString() {
        String result;

        if (this._exceptionsNull != null)
            result = String.format("SubscriptionStarted = {%s}%s", System.getProperty("line.separator"), System.getProperty("line.separator"));

        else if (this._exceptionsBadFields != null) {
            StringBuilder strb = new StringBuilder();
            strb.append(String.format("SubscriptionStarted = {%s", this.messageType().toString(), System.getProperty("line.separator")));
            try {
                strb.append(this._exceptionsBadFields.prettyPrint(1));
            } catch (Exception ignored) {
            }
            strb.append("}").append(System.getProperty("line.separator"));
            result = strb.toString();
        } else
            result = "";

        return result;
    }
}
