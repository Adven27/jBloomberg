package com.assylias.jbloomberg.mock.reference;

import com.assylias.jbloomberg.mock.AbstractMessage;
import com.bloomberglp.blpapi.CorrelationID;
import com.bloomberglp.blpapi.Element;
import com.bloomberglp.blpapi.Name;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReferenceMessage extends AbstractMessage {
    private final ReferenceElementSecurityDataArray _securities;

    ReferenceMessage(CorrelationID corr, Map<String, Map<String, Object>> fieldData) throws Exception {
        super(new Name("ReferenceDataResponse"), corr, null);
        this._securities = new ReferenceElementSecurityDataArray(fieldData);
    }

    public int numElements() {
        return 1;
    }

    List<Element> elements() {
        List<Element> result = new ArrayList<Element>();
        result.add(this._securities);
        return result;
    }

    public Element getElement(String name) {
        if (name.equals(this._securities.name().toString()))
            return this._securities;

        else
            return super.getElement(name);
    }

    public String topicName() {
        return "";
    }

    public Element asElement() {
        return new ReferenceElementData(this);
    }

    protected StringBuilder prettyPrint(int tabIndent) throws Exception {
        StringBuilder result = new StringBuilder();

        result.append(String.format("fieldData[] = {%s", System.getProperty("line.separator")));
        result.append(this._securities.prettyPrint(1));
        result.append(String.format("}%s", System.getProperty("line.separator")));

        return result;
    }

}
