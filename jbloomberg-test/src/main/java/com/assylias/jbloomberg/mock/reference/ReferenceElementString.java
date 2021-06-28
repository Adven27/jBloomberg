package com.assylias.jbloomberg.mock.reference;

import com.assylias.jbloomberg.mock.AbstractElement;
import com.bloomberglp.blpapi.Name;
import com.bloomberglp.blpapi.Schema;

public class ReferenceElementString extends AbstractElement {
    private final String _name, _value;

    ReferenceElementString(String name, String value) {
        this._name = name;
        this._value = value;
    }

    public Name name() {
        return new Name(this._name);
    }

    public int numValues() {
        return 1;
    }

    public int numElements() {
        return 0;
    }

    public boolean isComplexType() {
        return false;
    }

    public boolean isArray() {
        return false;
    }

    public boolean isNull() {
        return false;
    }

    public Schema.Datatype datatype() {
        return Schema.Datatype.STRING;
    }

    protected StringBuilder prettyPrint(int tabIndent) {
        return super.prettyPrintHelper(this._value.toString());
    }

    public Object getValue() {
        return this._value;
    }

    public String getValueAsString() {
        return this._value;
    }

    public String getValueAsString(int index) {
        if (index == 0)
            return this._value;
        else
            return super.getValueAsString(index);
    }
}
