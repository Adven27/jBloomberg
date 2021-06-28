package com.assylias.jbloomberg.mock.reference;

import com.assylias.jbloomberg.mock.AbstractElement;
import com.bloomberglp.blpapi.Datetime;
import com.bloomberglp.blpapi.Name;
import com.bloomberglp.blpapi.Schema;

public class ReferenceElementDateTime extends AbstractElement {
    private final Datetime _value;
    private final String _name;

    ReferenceElementDateTime(String name, Datetime value) {
        this._value = new Datetime(value);
        this._name = name;
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
        return Schema.Datatype.DATE;
    }

    protected StringBuilder prettyPrint(int tabIndent) {
        return super.prettyPrintHelper(this._value.toString());
    }

    public Object getValue() {
        return this._value;
    }

    public Datetime getValueAsDate() {
        return this._value;
    }

    public Datetime getValueAsDatetime() {
        return this._value;
    }

    public Datetime getValueAsTime() {
        return this._value;
    }
}
