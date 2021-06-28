package com.assylias.jbloomberg.mock.reference;

import com.assylias.jbloomberg.mock.AbstractElement;
import com.assylias.jbloomberg.mock.SchemaTypeDefinitionImpl;
import com.bloomberglp.blpapi.Element;
import com.bloomberglp.blpapi.Name;
import com.bloomberglp.blpapi.SchemaTypeDefinition;

public class ReferenceElementFieldExceptions extends AbstractElement {
    private final ReferenceElementString _fieldId;
    private final ReferenceElementErrorInfo _errorInfo;

    public ReferenceElementFieldExceptions(String badField) {
        this._fieldId = new ReferenceElementString("fieldId", badField);
        this._errorInfo = new ReferenceElementErrorInfo();
    }

    public SchemaTypeDefinition typeDefinition() {
        return new SchemaTypeDefinitionImpl(this.datatype(), new Name("FieldException"));
    }

    public Name name() {
        return new Name("fieldExceptions");
    }

    public int numValues() {
        return 1;
    }

    public int numElements() {
        return 2;
    }

    public String getElementAsString(String name) {
        return this.getElement(name).getValueAsString();
    }

    public int getElementAsInt32(String name) {
        return this.getElement(name).getValueAsInt32();
    }

    public Element getElement(String name) {
        if (name.equals("fieldId"))
            return this._fieldId;

        else if (name.equals("errorInfo"))
            return this._errorInfo;

        else
            return super.getElement(name);
    }

    public boolean hasElement(String name) {
        return name.equals("fieldId") || name.equals("errorInfo");
    }

    protected StringBuilder prettyPrint(int tabIndent) {
        
        StringBuilder result = new StringBuilder();

        result.append(String.format("%s%s = {%s", TAB, this.name().toString(), System.getProperty("line.separator")));
        result.append(this._fieldId.prettyPrint(tabIndent + 1));
        result.append(this._errorInfo.prettyPrint(tabIndent + 1));
        result.append(String.format("%s}%s", TAB, System.getProperty("line.separator")));

        return result;
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
}
