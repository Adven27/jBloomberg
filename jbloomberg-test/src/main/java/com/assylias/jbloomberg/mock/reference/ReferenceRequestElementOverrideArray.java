package com.assylias.jbloomberg.mock.reference;

import com.assylias.jbloomberg.mock.AbstractElement;
import com.bloomberglp.blpapi.Element;
import com.bloomberglp.blpapi.Name;

import java.util.ArrayList;
import java.util.List;

public class ReferenceRequestElementOverrideArray extends AbstractElement {
    private final List<ReferenceRequestElementOverride> _overrides;

    public ReferenceRequestElementOverrideArray() {
        this._overrides = new ArrayList<>();
    }

    public Name name() {
        return new Name("overrides");
    }

    public int numValues() {
        return this._overrides.size();
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

    public Object getValue() {
        return this.getValue(0);
    }

    public Object getValue(int index) {
        return this._overrides.get(index);
    }

    public Element getValueAsElement() {
        return this.getValueAsElement(0);
    }

    public Element getValueAsElement(int index) {
        return (Element) this._overrides.get(index);
    }

    public Element appendElement() {
        ReferenceRequestElementOverride result = new ReferenceRequestElementOverride();
        this._overrides.add(result);
        return result;
    }

    protected StringBuilder prettyPrint(int tabIndent) {
        
        StringBuilder result = new StringBuilder();

        result.append(String.format("%s%s[] = {%s", TAB, this.name().toString(), System.getProperty("line.separator")));

        for (ReferenceRequestElementOverride override : this._overrides) {
            result.append(override.prettyPrint(tabIndent + 1));
        }

        result.append(String.format("%s}%s", TAB, System.getProperty("line.separator")));
        return result;
    }
}
