package com.assylias.jbloomberg.mock.reference;

import com.assylias.jbloomberg.mock.AbstractElement;
import com.bloomberglp.blpapi.Name;
import com.bloomberglp.blpapi.Schema;

public class ReferenceElementInt extends AbstractElement {
    private final int _value;
    private final String _name;

    ReferenceElementInt(String name, int value) {
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
        return Schema.Datatype.INT32;
    }

    protected StringBuilder prettyPrint(int tabIndent) {
        return super.prettyPrintHelper(String.valueOf(this._value));
    }

    public Object getValue() {
        return this._value;
    }

    public int getValueAsInt32() {
        return this._value;
    }

    public long getValueAsInt64() {
        return this._value;
    }

}
