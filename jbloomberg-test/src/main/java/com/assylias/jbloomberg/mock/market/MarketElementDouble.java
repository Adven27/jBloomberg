package com.assylias.jbloomberg.mock.market;

import com.assylias.jbloomberg.mock.AbstractElement;
import com.assylias.jbloomberg.mock.DisplayFormats;
import com.bloomberglp.blpapi.Name;
import com.bloomberglp.blpapi.Schema;

public class MarketElementDouble extends AbstractElement {
    private final double _value;
    private final String _name;

    MarketElementDouble(String name, double value) {
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
        return Schema.Datatype.FLOAT64;
    }

    protected StringBuilder prettyPrint(int tabIndent) {
        return super.prettyPrintHelper(DisplayFormats.FormatNumberNoSeparators(this._value, 2));
    }

    public Object getValue() {
        return this._value;
    }

    public double getValueAsFloat64() {
        return this._value;
    }
}
