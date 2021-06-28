package com.assylias.jbloomberg.mock.historic;

import com.assylias.jbloomberg.mock.AbstractElement;
import com.assylias.jbloomberg.mock.SchemaTypeDefinitionImpl;
import com.bloomberglp.blpapi.Element;
import com.bloomberglp.blpapi.Name;
import com.bloomberglp.blpapi.SchemaTypeDefinition;

import java.util.ArrayList;
import java.util.List;

public class HistoricElementFieldExceptionsArray extends AbstractElement {
    private final List<HistoricElementFieldExceptions> _exceptions;

    public HistoricElementFieldExceptionsArray(List<String> badFields) {
        this._exceptions = new ArrayList<>(badFields.size());
        for (String badField : badFields) {
            this._exceptions.add(new HistoricElementFieldExceptions(badField));
        }
    }

    public SchemaTypeDefinition typeDefinition() {
        return new SchemaTypeDefinitionImpl(this.datatype(), new Name("FieldException"));
    }

    public Name name() {
        return new Name("fieldExceptions");
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

    public Element getValueAsElement(int index) {
        return this._exceptions.get(index);
    }

    protected StringBuilder prettyPrint(int tabIndent) {
        
        StringBuilder result = new StringBuilder();

        result.append(String.format("%s%s[] = {%s", TAB, this.name().toString(), System.getProperty("line.separator")));

        for (int i = 0; i < this._exceptions.size(); i++) {
            result.append(this._exceptions.get(i).prettyPrint(tabIndent + 1));
        }

        result.append(String.format("%s}%s", TAB, System.getProperty("line.separator")));
        return result;
    }

}
