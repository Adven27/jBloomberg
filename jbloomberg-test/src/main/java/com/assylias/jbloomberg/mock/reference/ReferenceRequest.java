package com.assylias.jbloomberg.mock.reference;

import com.assylias.jbloomberg.mock.AbstractRequest;
import com.bloomberglp.blpapi.Element;

import java.util.List;

public class ReferenceRequest extends AbstractRequest {
    private final ReferenceRequestElementStringArray _securities, _fields;
    private final ReferenceRequestElementOverrideArray _overrides;

    public ReferenceRequest() {
        this._securities = new ReferenceRequestElementStringArray("securities");
        this._fields = new ReferenceRequestElementStringArray("fields");
        this._overrides = new ReferenceRequestElementOverrideArray();
    }

    public List<String> securities() {
        return this._securities.values();
    }

    public List<String> fields() {
        return this._fields.values();
    }

    public Element getElement(String name) {
        if (this._securities.name().toString().equals(name))
            return this._securities;

        else if (this._fields.name().toString().equals(name))
            return this._fields;

        else if (this._overrides.name().toString().equals(name))
            return this._overrides;

        else
            return super.getElement(name);
    }

    public boolean hasElement(String name) {
        if (this._securities.name().toString().equals(name))
            return this._securities.values().size() > 0;

        else if (this._fields.name().toString().equals(name))
            return this._fields.values().size() > 0;

        else if (this._overrides.name().toString().equals(name))
            return this._overrides.numValues() > 0;

        else
            return false;
    }

    public void append(String name, String elementValue) {
        if (name.equals("securities"))
            this._securities.appendValue(elementValue);

        else if (name.equals("fields"))
            this._fields.appendValue(elementValue);

        else
            throw new UnsupportedOperationException(String.format("BEmu.RequestReference.Append: Element name %s not supported. names are case-sensitive.", name));
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("ReferenceDataRequest = {").append(System.getProperty("line.separator"));

        if (this._securities.numValues() > 0)
            result.append(this._securities.prettyPrint(1));

        if (this._fields.numValues() > 0)
            result.append(this._fields.prettyPrint(1));

        try {
            if (this._overrides.numValues() > 0)
                result.append(this._overrides.prettyPrint(1));
        } catch (Exception ignored) {
        }

        result.append("}").append(System.getProperty("line.separator"));

        return result.toString();
    }
}
