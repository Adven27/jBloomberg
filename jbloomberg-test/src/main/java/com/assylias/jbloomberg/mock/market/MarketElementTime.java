package com.assylias.jbloomberg.mock.market;

import com.assylias.jbloomberg.mock.AbstractElement;
import com.assylias.jbloomberg.mock.DisplayFormats;
import com.bloomberglp.blpapi.Datetime;
import com.bloomberglp.blpapi.Name;
import com.bloomberglp.blpapi.Schema;

public class MarketElementTime extends AbstractElement {
    private final Datetime _date;
    private final String _name;

    MarketElementTime(String name, Datetime date) {
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
        return Schema.Datatype.TIME;
    }

    protected StringBuilder prettyPrint(int tabIndent) {
        String strDate = DisplayFormats.MarketDataRequests_FormatDateZone(this._date);
        return super.prettyPrintHelper(strDate);
    }

    public Object getValue() {
        return this._date;
    }

    public Datetime getValueAsTime() {
        return this._date;
    }
}
