package com.assylias.jbloomberg.mock.historic;

import com.assylias.jbloomberg.mock.AbstractElement;
import com.assylias.jbloomberg.mock.SchemaTypeDefinitionImpl;
import com.bloomberglp.blpapi.Datetime;
import com.bloomberglp.blpapi.Element;
import com.bloomberglp.blpapi.Name;
import com.bloomberglp.blpapi.SchemaTypeDefinition;

import java.util.HashMap;
import java.util.Map;

public class HistoricElementFieldData extends AbstractElement {
    private final Map<String, Element> _fields;

    HistoricElementFieldData(Datetime date, Map<String, Object> values) {
        this._fields = new HashMap<>();

        Element elmDate = new HistoricElementDateTime(date);
        this._fields.put(elmDate.name().toString(), elmDate);

        for (Map.Entry<String, Object> item : values.entrySet()) {
            if (item.getValue().getClass() == Double.class) {
                Element elmDouble = new HistoricElementDouble(item.getKey(), (Double) item.getValue());
                this._fields.put(elmDouble.name().toString(), elmDouble);
            }
        }
    }

    public SchemaTypeDefinition typeDefinition() {
        return new SchemaTypeDefinitionImpl(this.datatype(), new Name("HistoricalDataRow"));
    }

    public Name name() {
        return new Name("fieldData");
    }

    public int numValues() {
        return 1;
    }

    public int numElements() {
        return this._fields.size();
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
        return this._fields.get(name).getValueAsInt32();
    }

    public Datetime getElementAsDatetime(String name) {
        return this._fields.get(name).getValueAsDatetime();
    }

    public Datetime getElementAsDate(String name) {
        return this._fields.get(name).getValueAsDate();
    }

    public Datetime getElementAsTime(String name) {
        return this._fields.get(name).getValueAsTime();
    }

    public String getElementAsString(String name) {
        return this._fields.get(name).getValueAsString();
    }

    public float getElementAsFloat32(String name) {
        return this._fields.get(name).getValueAsFloat32();
    }

    public double getElementAsFloat64(String name) {
        return this._fields.get(name).getValueAsFloat64();
    }

    public long getElementAsInt64(String name) {
        return this._fields.get(name).getValueAsInt64();
    }

    public boolean hasElement(String name) {
        return this._fields.containsKey(name);
    }

    public boolean hasElement(String name, boolean excludeNullElements) {
        return this._fields.containsKey(name);
    }

    protected StringBuilder prettyPrint(int tabIndent) {
        
        StringBuilder result = new StringBuilder();

        result.append(String.format("%sfieldData = {%s", TAB, System.getProperty("line.separator")));

        for (Map.Entry<String, Element> item : this._fields.entrySet()) {
            result.append(item.getValue().toString());
        }

        result.append(String.format("%s}%s", TAB, System.getProperty("line.separator")));
        return result;
    }
}
