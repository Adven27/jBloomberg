package com.assylias.jbloomberg.mock.reference;

import com.assylias.jbloomberg.mock.AbstractElement;
import com.assylias.jbloomberg.mock.Rules;
import com.assylias.jbloomberg.mock.SchemaTypeDefinitionImpl;
import com.bloomberglp.blpapi.Element;
import com.bloomberglp.blpapi.Name;
import com.bloomberglp.blpapi.SchemaTypeDefinition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReferenceElementSecurityData extends AbstractElement {
    private final ReferenceElementString _elmSecurityName;
    private final ReferenceElementInt _elmSequenceNumber;
    private final ReferenceElementFieldData _elmFieldData;
    private final ReferenceElementFieldExceptionsArray _elmFieldExceptions;
    private final ReferenceElementSecurityError _elmSecError;
    private final boolean _isSecurityError;
    private final int _numElements;

    ReferenceElementSecurityData(String securityName, Map<String, Object> fieldData, int sequenceNumber) {
        this._isSecurityError = Rules.isSecurityError(securityName);
        List<String> badFields = findBadFields(fieldData);

        //remove bad field names from the dictionary
        for (String badField : badFields) {
            fieldData.remove(badField);
        }

        this._elmFieldExceptions = badFields.isEmpty() ? null : new ReferenceElementFieldExceptionsArray(badFields);

        this._elmSecurityName = new ReferenceElementString("security", securityName);
        if (this._isSecurityError) {
            this._elmSequenceNumber = new ReferenceElementInt("sequenceNumber", sequenceNumber);
            this._elmSecError = new ReferenceElementSecurityError(securityName);
            this._elmFieldData = new ReferenceElementFieldData(new HashMap<String, Object>());
        } else {
            this._elmSequenceNumber = new ReferenceElementInt("sequenceNumber", sequenceNumber);
            this._elmSecError = null;
            this._elmFieldData = new ReferenceElementFieldData(fieldData);
        }

        this._numElements = this._elmFieldExceptions == null ? 3 : 4;
    }

    private List<String> findBadFields(Map<String, Object> fieldData) {
        List<String> badFields = new ArrayList<String>();
        for (Map.Entry<String, Object> entry : fieldData.entrySet()) {
            if (Rules.isBadField(entry.getKey()))
                badFields.add(entry.getKey());
        }
        return badFields;
    }

    public SchemaTypeDefinition typeDefinition() {
        return new SchemaTypeDefinitionImpl(this.datatype(), new Name("ReferenceSecurityData"));
    }

    public Name name() {
        return new Name("securityData");
    }

    public int numValues() {
        return 0;
    }

    public int numElements() {
        return this._numElements;
    }

    public Element getElement(String name) {
        if (name.equals("fieldData") && !this._isSecurityError)
            return this._elmFieldData;

        else if (name.equals("security"))
            return this._elmSecurityName;

        else if (name.equals("sequenceNumber"))
            return this._elmSequenceNumber;

        else if (name.equals("fieldExceptions") && this._elmFieldExceptions != null)
            return this._elmFieldExceptions;

        else if (name.equals("securityError") && this._isSecurityError) //this element doesn't exist if the security exists
            return this._elmSecError;

        return super.getElement(name);
    }

    public boolean hasElement(String name) {
        return this.hasElement(name, false);
    }

    public boolean hasElement(String name, boolean excludeNullElements) {
        return
                (name.equals("fieldData") && !this._isSecurityError) ||
                        name.equals("security") ||
                        name.equals("sequenceNumber") ||
                        (name.equals("fieldExceptions") && this._elmFieldExceptions != null) ||
                        (name.equals("securityError") && this._isSecurityError);
    }

    public String getElementAsString(String name) {
        if (name == this._elmSecurityName.name().toString())
            return this._elmSecurityName.getValueAsString();
        else
            return super.getElementAsString(name);
    }

    public int getElementAsInt32(String name) {
        if (name == this._elmSequenceNumber.name().toString())
            return this._elmSecurityName.getValueAsInt32();
        else
            return super.getElementAsInt32(name);
    }

    protected StringBuilder prettyPrint(int tabIndent) {
        
        StringBuilder result = new StringBuilder();

        result.append(String.format("%s%s = {%s", TAB, this.name().toString(), System.getProperty("line.separator")));
        result.append(this._elmSecurityName.prettyPrint(tabIndent + 1));
        result.append(this._elmSequenceNumber.prettyPrint(tabIndent + 1));

        if (this._elmFieldExceptions != null)
            result.append(this._elmFieldExceptions.prettyPrint(tabIndent + 1));

        if (this._isSecurityError)
            result.append(this._elmSecError.prettyPrint(tabIndent + 1));
        else
            result.append(this._elmFieldData.prettyPrint(tabIndent + 1));

        result.append(String.format("%s}%s", TAB, System.getProperty("line.separator")));

        return result;
    }

}
