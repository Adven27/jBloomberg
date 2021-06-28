package com.assylias.jbloomberg.mock.market;

import com.assylias.jbloomberg.mock.AbstractMessage;
import com.bloomberglp.blpapi.CorrelationID;
import com.bloomberglp.blpapi.Element;
import com.bloomberglp.blpapi.Name;

public class MarketMessageServiceStatus extends AbstractMessage {
    private final MarketElementString _serviceName;

    MarketMessageServiceStatus(CorrelationID corr) {
        super(new Name("ServiceOpened"), corr, null);

        this._serviceName = new MarketElementString("serviceName", "//blp/mktdata");
    }

    public int numElements() {
        return 1;
    }

    public String topicName() {
        return "";
    }

    public boolean hasElement(String name, boolean excludeNullElements) {
        return this._serviceName.name().toString().equals(name);
    }

    public Element getElement(String name) {
        return name.equals(this._serviceName.name().toString()) ? this._serviceName : super.getElement(name);
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(String.format("%s = {%s", this.messageType().toString(), System.getProperty("line.separator")));
        try {
            result.append(this._serviceName.prettyPrint(1));
        } catch (Exception ignored) {
        }
        result.append("}").append(System.getProperty("line.separator"));

        return result.toString();
    }

}
