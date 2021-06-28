package com.assylias.jbloomberg.mock.market;

import com.assylias.jbloomberg.mock.AbstractElement;
import com.assylias.jbloomberg.mock.SchemaTypeDefinitionImpl;
import com.bloomberglp.blpapi.Element;
import com.bloomberglp.blpapi.Name;
import com.bloomberglp.blpapi.SchemaTypeDefinition;

import java.util.ArrayList;
import java.util.List;

public class MarketElementExceptionsArray extends AbstractElement {
    private final List<MarketElementExceptions> _exceptions;

    public MarketElementExceptionsArray(List<String> badFields) {
        this._exceptions = new ArrayList<>();

        for (String item : badFields) {
            this._exceptions.add(new MarketElementExceptions(item));
        }
    }

    public SchemaTypeDefinition typeDefinition() {
        return new SchemaTypeDefinitionImpl(this.datatype(), new Name("SubscriptionException"));
    }

    public Element getValueAsElement(int index) {
        return this._exceptions.get(index);
    }

    public Name name() {
        return new Name("exceptions");
    }

    public int numValues() {
        return this._exceptions.size();
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
        return this._exceptions.get(index);
    }

    protected StringBuilder prettyPrint(int tabIndent) {
        
        StringBuilder result = new StringBuilder();

        result.append(String.format("%s%s[] = {%s", TAB, this.name().toString(), System.getProperty("line.separator")));

        for (MarketElementExceptions exception : this._exceptions) {
            result.append(exception.prettyPrint(tabIndent + 1));
        }

        result.append(String.format("%s}%s", TAB, System.getProperty("line.separator")));
        return result;
    }
}
