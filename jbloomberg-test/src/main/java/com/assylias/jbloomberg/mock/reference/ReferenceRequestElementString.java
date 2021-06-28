package com.assylias.jbloomberg.mock.reference;

import com.assylias.jbloomberg.mock.AbstractElement;
import com.bloomberglp.blpapi.Name;
import com.bloomberglp.blpapi.Schema;

public class ReferenceRequestElementString extends AbstractElement {
    private final String _elementName, _value;

    ReferenceRequestElementString(String elementName, String value) {
        this._elementName = elementName;
        this._value = value;
    }

    public Name name() {
        return new Name(this._elementName);
    }

    public int numValues() {
        return 1;
    }

    public int numElements() {
        return 0;
    }

    public Schema.Datatype datatype() {
        return Schema.Datatype.STRING;
    }

    protected StringBuilder prettyPrint(int tabIndent) {
        
        StringBuilder result = new StringBuilder();

        result.append(String.format("%s%s = %s%s", TAB, this._elementName, this._value, System.getProperty("line.separator")));

        return result;
    }
}
