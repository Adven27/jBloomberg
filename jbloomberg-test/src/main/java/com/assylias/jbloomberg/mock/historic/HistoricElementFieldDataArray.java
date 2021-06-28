package com.assylias.jbloomberg.mock.historic;

import com.assylias.jbloomberg.mock.AbstractElement;
import com.assylias.jbloomberg.mock.SchemaTypeDefinitionImpl;
import com.bloomberglp.blpapi.Datetime;
import com.bloomberglp.blpapi.Element;
import com.bloomberglp.blpapi.Name;
import com.bloomberglp.blpapi.SchemaTypeDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HistoricElementFieldDataArray extends AbstractElement {
    private final List<HistoricElementFieldData> _fieldData;

    HistoricElementFieldDataArray(Map<Datetime, Map<String, Object>> fieldData) {
        this._fieldData = new ArrayList<>();

        for (Map.Entry<Datetime, Map<String, Object>> item : fieldData.entrySet()) {
            HistoricElementFieldData elmFieldData = new HistoricElementFieldData(item.getKey(), item.getValue());
            this._fieldData.add(elmFieldData);
        }
    }

    public SchemaTypeDefinition typeDefinition() {
        return new SchemaTypeDefinitionImpl(this.datatype(), new Name("HistoricalDataRow"));
    }

    public Element getValueAsElement(int index) {
        return this._fieldData.get(index);
    }

    public Name name() {
        return new Name("fieldData");
    }

    public int numValues() {
        return this._fieldData.size();
    }

    public int numElements() {
        return 0;
    }

    public boolean isComplexType() {
        return true;
    }

    public boolean isArray() {
        return true;
    }

    public boolean isNull() {
        return false;
    }

    protected StringBuilder prettyPrint(int tabIndent) {
        
        StringBuilder result = new StringBuilder();

        result.append(String.format("%s%s[] = {%s", TAB, this.name().toString(), System.getProperty("line.separator")));

        for (HistoricElementFieldData fieldDatum : this._fieldData) {
            result.append(fieldDatum.prettyPrint(tabIndent + 1));
        }

        result.append(String.format("%s}%s", TAB, System.getProperty("line.separator")));
        return result;
    }
}
