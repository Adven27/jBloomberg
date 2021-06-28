package com.assylias.jbloomberg.mock.reference;

import com.assylias.jbloomberg.mock.AbstractElement;
import com.bloomberglp.blpapi.Element;
import com.bloomberglp.blpapi.Name;

public class ReferenceRequestElementOverride extends AbstractElement {
    private ReferenceElementString _fieldId;
    private ReferenceElementObject _value;

    ReferenceRequestElementOverride() {
        this._fieldId = new ReferenceElementString("fieldId", "");
        this._value = new ReferenceElementObject("value", "");
    }

    public void setElement(String name, Object value) {
        if (name.equals("fieldId"))
            this._fieldId = new ReferenceElementString(name, value.toString());

        else if (name.equals("value"))
            this._value = new ReferenceElementObject(name, value);

        else
            super.setElement(name, value);
    }

    public Element getElement(String name) {
        if (name.equals("fieldId"))
            return this._fieldId;

        else if (name.equals("value"))
            return this._value;

        else
            return super.getElement(name);
    }

    public Name name() {
        return new Name("overrides");
    }

    public int numValues() {
        return 1;
    }

    public int numElements() {
        return 2;
    }

    protected StringBuilder prettyPrint(int tabIndent) {
        
        StringBuilder result = new StringBuilder();

        result.append(String.format("%s%s = {%s", TAB, this.name().toString(), System.getProperty("line.separator")));
        result.append(this._fieldId.prettyPrint(tabIndent + 1));
        result.append(this._value.prettyPrint(tabIndent + 1));
        result.append(String.format("%s}%s", TAB, System.getProperty("line.separator")));

        return result;
    }
}
