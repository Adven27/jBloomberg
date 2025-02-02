package com.assylias.jbloomberg.mock.reference;

import com.assylias.jbloomberg.mock.AbstractElement;
import com.bloomberglp.blpapi.Element;
import com.bloomberglp.blpapi.Name;
import com.bloomberglp.blpapi.Schema;

public class ReferenceElementData extends AbstractElement {
    private final ReferenceElementSecurityDataArray _securities;

    ReferenceElementData(ReferenceMessage arg) {
        this._securities = new ReferenceElementSecurityDataArray((ReferenceElementSecurityDataArray) arg.elements().get(0));
    }

    public Name name() {
        return new Name("securityData");
    }

    public int numValues() {
        return 1;
    }

    public int numElements() {
        return 1;
    }

    public boolean isComplexType() {
        return true;
    }

    public boolean isArray() {
        return false;
    }

    public boolean isNull() {
        return false;
    }

    public Schema.Datatype datatype() {
        return Schema.Datatype.CHOICE;
    }

    public Element getElement(String name) {
        if (name.equals(this._securities.name().toString()))
            return this._securities;

        else
            return super.getElement(name);
    }

    protected StringBuilder prettyPrint(int tabIndent) {
        StringBuilder result = new StringBuilder();

        result.append(String.format("ReferenceDataResponse (choice) = {%s", System.getProperty("line.separator")));
        result.append(this._securities.prettyPrint(1));
        result.append(String.format("}%s", System.getProperty("line.separator")));

        return result;
    }

}
