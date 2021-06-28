package com.assylias.jbloomberg.mock.market;

import com.assylias.jbloomberg.mock.AbstractElement;
import com.bloomberglp.blpapi.Datetime;
import com.bloomberglp.blpapi.Name;
import com.bloomberglp.blpapi.Schema;

public class MarketElementDatetime extends AbstractElement {
    private final Datetime _date;
    private final String _name;

    MarketElementDatetime(String name, Datetime date) {
        this._date = new Datetime(date);
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
        return Schema.Datatype.DATETIME;
    }

    protected StringBuilder prettyPrint(int tabIndent) {
        return super.prettyPrintHelper(this._date.toString());
    }

    public Object getValue() {
        return this._date;
    }

    public Datetime getValueAsDatetime() {
        return this._date;
    }

    public Datetime getValueAsDate() {
        return this._date;
    }

    public Datetime getValueAsTime() {
        return this._date;
    }
}
