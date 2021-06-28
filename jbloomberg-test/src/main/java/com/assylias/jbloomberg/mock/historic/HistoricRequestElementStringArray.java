package com.assylias.jbloomberg.mock.historic;

import com.assylias.jbloomberg.mock.AbstractElement;
import com.bloomberglp.blpapi.Name;

import java.util.ArrayList;
import java.util.List;

public class HistoricRequestElementStringArray extends AbstractElement {
    private final String _elementName;
    private final List<String> _values;

    HistoricRequestElementStringArray(String elementName) {
        this._elementName = elementName;
        this._values = new ArrayList<>();
    }

    public Name name() {
        return new Name(this._elementName);
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

    public void appendValue(String value) {
        this._values.add(value);
    }

    List<String> values() {
        return this._values;
    }

    protected StringBuilder prettyPrint(int tabIndent) {
        
        StringBuilder result = new StringBuilder();

        result.append(String.format("%s%s[] = {%s", TAB, this._elementName, System.getProperty("line.separator")));

        StringBuilder secs = new StringBuilder();
        for (int i = 0; i < this._values.size(); i++) {
            secs.append(this._values.get(i));
            if (i < this._values.size() - 1)
                secs.append(",");
        }
        result.append(String.format("%s%s%s%s", TAB, TAB, secs, System.getProperty("line.separator")));
        result.append(String.format("%s}%s", TAB, System.getProperty("line.separator")));
        return result;
    }
}
