package com.assylias.jbloomberg.mock.reference;

import com.assylias.jbloomberg.mock.AbstractElement;
import com.bloomberglp.blpapi.Name;

public class ReferenceElementObject extends AbstractElement {
    private final Object _value;
    private final String _name;

    ReferenceElementObject(String name, Object value) {
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

    protected StringBuilder prettyPrint(int tabIndent) {
        return super.prettyPrintHelper(String.valueOf(this._value));
    }

    public Object getValue() {
        return this._value;
    }

    public String getValueAsString() {
        return String.valueOf(this._value);
    }

    public String getValueAsString(int index) {
        return index == 0 ? this.getValueAsString() : super.getValueAsString(index);
    }
}
