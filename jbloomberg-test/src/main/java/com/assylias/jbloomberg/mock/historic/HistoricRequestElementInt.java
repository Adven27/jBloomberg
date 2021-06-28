package com.assylias.jbloomberg.mock.historic;

import com.bloomberglp.blpapi.Schema;

public class HistoricRequestElementInt extends HistoricRequestElementString {
    private final int _value;

    HistoricRequestElementInt(String elementName, int value) {
        super(elementName, String.valueOf(value));
        this._value = value;
    }

    //I can't override GetElementAsInt32 here because the Bloomberg Request object stores ints as strings, not ints.
    //You can't convert the string to an int
    int getInt() {
        return this._value;
    }

    public Schema.Datatype datatype() {
        return Schema.Datatype.INT32;
    }
}
