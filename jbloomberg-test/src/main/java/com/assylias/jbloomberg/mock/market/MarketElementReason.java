package com.assylias.jbloomberg.mock.market;

import com.assylias.jbloomberg.mock.AbstractElement;
import com.assylias.jbloomberg.mock.RandomDataGenerator;
import com.assylias.jbloomberg.mock.SchemaTypeDefinitionImpl;
import com.bloomberglp.blpapi.Element;
import com.bloomberglp.blpapi.Name;
import com.bloomberglp.blpapi.SchemaTypeDefinition;

public class MarketElementReason extends AbstractElement {
    private final MarketElementString _source, _category, _description;
    private final MarketElementInt _errorCode;

    public enum ReasonTypeEnum {badSecurity, badField}

    public MarketElementReason(ReasonTypeEnum reasonType) {
        if (reasonType == ReasonTypeEnum.badSecurity) {
            this._source = new MarketElementString("source", String.format("%s%s@%s", RandomDataGenerator.randomString(5).toLowerCase(), RandomDataGenerator.randomInt(9), RandomDataGenerator.randomInt(99)));
            this._errorCode = new MarketElementInt("errorCode", RandomDataGenerator.randomInt(99));
            this._category = new MarketElementString("category", "BAD_SEC");
            this._description = new MarketElementString("description", "Invalid security, rcode = -1");
        } else //if (reasonType == ReasonTypeEnum.badField)
        {
            this._source = new MarketElementString("source", String.format("%s@%s", RandomDataGenerator.randomString(7).toLowerCase(), RandomDataGenerator.randomInt(999)));
            this._errorCode = new MarketElementInt("errorCode", RandomDataGenerator.randomInt(99));
            this._category = new MarketElementString("category", "BAD_FLD");
            this._description = new MarketElementString("description", "Unknown Field");
        }
    }

    public SchemaTypeDefinition typeDefinition() {
        return new SchemaTypeDefinitionImpl(this.datatype(), new Name("ErrorInfo"));
    }

    public Name name() {
        return new Name("reason");
    }

    public int numValues() {
        return 1;
    }

    public int numElements() {
        return 4;
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

    public int getElementAsInt32(String name) {
        return this.getElement(name).getValueAsInt32();
    }

    public String getElementAsString(String name) {
        return this.getElement(name).getValueAsString();
    }

    public Element getElement(String name) {
        if (name.equals("source"))
            return this._source;

        else if (name.equals(this._errorCode.name().toString()))
            return this._errorCode;

        else if (name.equals("category"))
            return this._category;

        else if (name.equals("description"))
            return this._description;

        else
            throw new UnsupportedOperationException("name not recognized. names are case-sensitive.");
    }

    public boolean hasElement(String name, boolean excludeNullElements) {
        return this.hasElement(name);
    }

    public boolean hasElement(String name) {
        return
                name.equals("source") ||
                        name.equals(this._errorCode.name().toString()) ||
                        name.equals("category") ||
                        name.equals("description");
    }

    protected StringBuilder prettyPrint(int tabIndent) {
        
        StringBuilder result = new StringBuilder();

        result.append(String.format("%s%s = {%s", TAB, this.name().toString(), System.getProperty("line.separator")));
        result.append(this._source.prettyPrint(tabIndent + 1));
        result.append(this._errorCode.prettyPrint(tabIndent + 1));
        result.append(this._category.prettyPrint(tabIndent + 1));
        result.append(this._description.prettyPrint(tabIndent + 1));
        result.append(String.format("%s}%s", TAB, System.getProperty("line.separator")));

        return result;
    }

}
