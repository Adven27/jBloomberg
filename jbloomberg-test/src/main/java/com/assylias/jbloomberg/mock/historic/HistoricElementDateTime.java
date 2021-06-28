package com.assylias.jbloomberg.mock.historic;

import com.assylias.jbloomberg.mock.AbstractElement;
import com.assylias.jbloomberg.mock.DisplayFormats;
import com.bloomberglp.blpapi.Datetime;
import com.bloomberglp.blpapi.Name;
import com.bloomberglp.blpapi.Schema;

public class HistoricElementDateTime extends AbstractElement {
    private final Datetime _date;

    HistoricElementDateTime(Datetime date) {
        this._date = new Datetime(date);
    }

    public Name name() {
        return new Name("date");
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
        String strDate = DisplayFormats.HistRef_FormatDate(this._date);
        return super.prettyPrintHelper(strDate);
    }

    public Object getValue() {
        return this._date;
    }

    public Datetime getValueAsDate() {
        return this._date;
    }

    public Datetime getValueAsDatetime() {
        return this._date;
    }

    public Datetime getValueAsTime() {
        return this._date;
    }

    public String getValueAsString() {
        String strDate = DisplayFormats.HistRef_FormatDate(this._date);
        return strDate;
    }
}
