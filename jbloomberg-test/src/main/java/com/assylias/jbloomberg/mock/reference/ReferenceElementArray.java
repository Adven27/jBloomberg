package com.assylias.jbloomberg.mock.reference;

import com.assylias.jbloomberg.mock.AbstractElement;
import com.bloomberglp.blpapi.Element;
import com.bloomberglp.blpapi.Name;

import java.util.List;

public class ReferenceElementArray extends AbstractElement {
    protected final List<Element> _values;
    private final String _name;

    ReferenceElementArray(String name, List<Element> elements) {
        this._name = name;
        this._values = elements;
    }

    public Name name() {
        return new Name(this._name);
    }

    public int numValues() {
        return this._values.size();
    }

    public int numElements() {
        return 0;
    }

    public boolean isComplexType() {
        return false;
    }

    public boolean isArray() {
        return true;
    }

    public boolean isNull() {
        return false;
    }

    public Object getValue(int index) {
        return this._values.get(index);
    }

    public Element getValueAsElement(int index) {
        return this._values.get(index);
    }

    protected StringBuilder prettyPrint(int tabIndent) {
        
        StringBuilder result = new StringBuilder();

        result.append(String.format("%s%s[] = {%s", TAB, this.name().toString(), System.getProperty("line.separator")));

        for (int i = 0; i < this._values.size(); i++) {
            result.append(this._values.get(i).toString());
        }

        result.append(String.format("%s}%s", TAB, System.getProperty("line.separator")));
        return result;
    }
}
