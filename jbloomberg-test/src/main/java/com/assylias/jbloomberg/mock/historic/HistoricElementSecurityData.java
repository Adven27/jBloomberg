package com.assylias.jbloomberg.mock.historic;

import com.assylias.jbloomberg.mock.AbstractElement;
import com.assylias.jbloomberg.mock.Rules;
import com.assylias.jbloomberg.mock.SchemaTypeDefinitionImpl;
import com.bloomberglp.blpapi.Datetime;
import com.bloomberglp.blpapi.Element;
import com.bloomberglp.blpapi.Name;
import com.bloomberglp.blpapi.SchemaTypeDefinition;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class HistoricElementSecurityData extends AbstractElement {
    private final HistoricElementString _elmSecurityName;
    private final HistoricElementInt _elmSequenceNumber;
    private final HistoricElementFieldDataArray _elmFieldDataArray;
    private final HistoricElementFieldExceptionsArray _elmFieldExceptions;
    private final HistoricElementSecurityError _elmSecError;
    private final boolean _isSecurityError;
    private final int _numElements;

    HistoricElementSecurityData(String securityName, List<String> badFields, Map<Datetime, Map<String, Object>> fieldData, int sequenceNumber)  {
        this._isSecurityError = Rules.isSecurityError(securityName);

        //remove bad field names from the dictionary
        this._elmFieldExceptions = badFields.size() == 0 ? null : new HistoricElementFieldExceptionsArray(badFields);

        this._elmSecurityName = new HistoricElementString("security", securityName);
        this._elmSequenceNumber = new HistoricElementInt("sequenceNumber", sequenceNumber);

        if (this._isSecurityError) {
            this._elmSecError = new HistoricElementSecurityError(securityName);
            this._elmFieldDataArray = null;
        } else {
            this._elmSecError = null;
            this._elmFieldDataArray = new HistoricElementFieldDataArray(fieldData);
        }

        this._numElements = this._elmFieldExceptions == null ? 3 : 4;
    }

    public SchemaTypeDefinition typeDefinition()  {
        return new SchemaTypeDefinitionImpl(this.datatype(), new Name("HistoricalDataTable"));
    }

    public Name name() {
        return new Name("securityData");
    }

    public int numValues() {
        return 1;
    }

    public int numElements() {
        return this._numElements;
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

    public Element getElement(String name)  {
        if (name.equals("fieldData") && !this._isSecurityError)
            return this._elmFieldDataArray;

        else if (name.equals("security"))
            return this._elmSecurityName;

        else if (name.equals("sequenceNumber"))
            return this._elmSequenceNumber;

        else if (name.equals("fieldExceptions") && this._elmFieldExceptions != null)
            return this._elmFieldExceptions;

        else if (name.equals("securityError") && this._isSecurityError) //this element doesn't exist if the security exists
            return this._elmSecError;

        else
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

    public String getElementAsString(String name)  {
        if (Objects.equals(name, this._elmSecurityName.name().toString()))
            return this._elmSecurityName.getValueAsString();
        else
            return super.getElementAsString(name);
    }

    public int getElementAsInt32(String name)  {
        if (Objects.equals(name, this._elmSequenceNumber.name().toString()))
            return this._elmSecurityName.getValueAsInt32();
        else
            return super.getElementAsInt32(name);
    }

    public StringBuilder prettyPrint(int tabIndent) {
        
        StringBuilder result = new StringBuilder();

        result.append(String.format("%s%s = {%s", TAB, this.name().toString(), System.getProperty("line.separator")));
        result.append(this._elmSecurityName.prettyPrint(tabIndent + 1));
        result.append(this._elmSequenceNumber.prettyPrint(tabIndent + 1));

        if (this._elmFieldExceptions != null)
            result.append(this._elmFieldExceptions.prettyPrint(tabIndent + 1));

        if (this._isSecurityError)
            result.append(this._elmSecError.prettyPrint(tabIndent + 1));
        else
            result.append(this._elmFieldDataArray.prettyPrint(tabIndent + 1));

        result.append(String.format("%s}%s", TAB, System.getProperty("line.separator")));

        return result;
    }
}
