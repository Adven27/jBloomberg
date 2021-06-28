package com.assylias.jbloomberg.mock.market;

import com.assylias.jbloomberg.mock.AbstractElement;
import com.assylias.jbloomberg.mock.SchemaTypeDefinitionImpl;
import com.bloomberglp.blpapi.Element;
import com.bloomberglp.blpapi.Name;
import com.bloomberglp.blpapi.SchemaTypeDefinition;

public class MarketElementExceptions extends AbstractElement {
    private final MarketElementString _fieldId;
    private final MarketElementReason _reason;

    public MarketElementExceptions(String badField) {
        this._fieldId = new MarketElementString("fieldId", badField);
        this._reason = new MarketElementReason(MarketElementReason.ReasonTypeEnum.badField);
    }

    public SchemaTypeDefinition typeDefinition() {
        return new SchemaTypeDefinitionImpl(this.datatype(), new Name("SubscriptionException"));
    }

    public Name name() {
        return new Name("exceptions");
    }

    public int numValues() {
        return 1;
    }

    public int numElements() {
        return 2;
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

    public boolean hasElement(String name, boolean excludeNullElements) {
        return this.hasElement(name);
    }

    public boolean hasElement(String name) {
        return name.equals("fieldId") || name.equals("reason");
    }

    public String getElementAsString(String name) {
        return this.getElement(name).getValueAsString();
    }

    public Element getElement(String name) {
        if (name.equals("fieldId"))
            return this._fieldId;

        else if (name.equals("reason"))
            return this._reason;

        else
            throw new UnsupportedOperationException("name not recognized. names are case-sensitive.");
    }

    protected StringBuilder prettyPrint(int tabIndent) {
        
        StringBuilder result = new StringBuilder();

        result.append(String.format("%s%s = {%s", TAB, this.name().toString(), System.getProperty("line.separator")));
        result.append(this._fieldId.prettyPrint(tabIndent + 1));
        result.append(this._reason.prettyPrint(tabIndent + 1));
        result.append(String.format("%s}%s", TAB, System.getProperty("line.separator")));

        return result;
    }
}
