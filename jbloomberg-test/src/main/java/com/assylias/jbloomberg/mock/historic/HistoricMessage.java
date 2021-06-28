package com.assylias.jbloomberg.mock.historic;

import com.assylias.jbloomberg.mock.AbstractMessage;
import com.bloomberglp.blpapi.CorrelationID;
import com.bloomberglp.blpapi.Datetime;
import com.bloomberglp.blpapi.Element;
import com.bloomberglp.blpapi.Name;

import java.util.List;
import java.util.Map;

public class HistoricMessage extends AbstractMessage {
    private final HistoricElementSecurityData _security;

    HistoricMessage(CorrelationID corr, String securityName, List<String> badFields, Map<Datetime, Map<String, Object>> fieldData, int sequenceNumber) {
        super(new Name("HistoricalDataResponse"), corr, null);
        this._security = new HistoricElementSecurityData(securityName, badFields, fieldData, sequenceNumber);
    }

    public HistoricElementSecurityData security() {
        return this._security;
    }

    public Element getElement(String name) {
        if (name.equals(this._security.name().toString()))
            return this._security;
        else
            throw new UnsupportedOperationException("not implemented. case-sensitive.");
    }

    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append("HistoricalDataResponse (choice) = {").append(System.getProperty("line.separator"));
        try {
            result.append(this._security.prettyPrint(1));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        result.append("}");

        return result.toString();
    }
}
