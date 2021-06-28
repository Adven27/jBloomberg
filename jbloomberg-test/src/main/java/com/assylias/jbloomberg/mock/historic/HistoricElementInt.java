package com.assylias.jbloomberg.mock.historic;

import com.assylias.jbloomberg.mock.AbstractElement;
import com.bloomberglp.blpapi.Name;
import com.bloomberglp.blpapi.Schema;

public class HistoricElementInt extends AbstractElement {
    private final int _value;
    private final String _name;

    HistoricElementInt(String name, int value) {
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

    public Object getValue(int index) {
        if (index == 0)
            return this.getValue();
        else
            return super.getValue();
    }

    public int getValueAsInt32() {
        return this._value;
    }

    public int getValueAsInt32(int index) {
        if (index == 0)
            return this.getValueAsInt32();
        else
            return super.getValueAsInt32(index);
    }

    public long getValueAsInt64() {
        return this._value;
    }

    public long getValueAsInt64(int index) {
        if (index == 0)
            return this.getValueAsInt64();
        else
            return super.getValueAsInt64(index);
    }

    public float getValueAsFloat32() {
        return this._value;
    }

    public float getValueAsFloat32(int index) {
        if (index == 0)
            return this.getValueAsFloat32();
        else
            return super.getValueAsFloat32(index);
    }

    public double getValueAsFloat64() {
        return this._value;
    }

    public double getValueAsFloat64(int index) {
        if (index == 0)
            return this.getValueAsFloat64();
        else
            return super.getValueAsFloat64(index);
    }

    public String getValueAsString() {
        return String.valueOf(this._value);
    }
}
