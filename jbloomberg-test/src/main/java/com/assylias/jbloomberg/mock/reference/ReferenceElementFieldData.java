package com.assylias.jbloomberg.mock.reference;

import com.assylias.jbloomberg.mock.AbstractElement;
import com.assylias.jbloomberg.mock.SchemaTypeDefinitionImpl;
import com.bloomberglp.blpapi.Datetime;
import com.bloomberglp.blpapi.Element;
import com.bloomberglp.blpapi.Name;
import com.bloomberglp.blpapi.SchemaTypeDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReferenceElementFieldData extends AbstractElement {
    private final List<Element> _fields;

    ReferenceElementFieldData(Map<String, Object> values) {
        this._fields = new ArrayList<>();
        for (Map.Entry<String, Object> item : values.entrySet()) {
            String itemKey = item.getKey();
            Object itemValue = item.getValue();

            if (itemValue instanceof Double) {
                Element elmDouble = new ReferenceElementDouble(itemKey, (Double) itemValue);
                this._fields.add(elmDouble);
            } else if (itemValue instanceof Integer) {
                Element elmInt = new ReferenceElementInt(itemKey, (Integer) itemValue);
                this._fields.add(elmInt);
            } else if (itemValue instanceof Datetime) {
                Element elmDatetime = new ReferenceElementDateTime(itemKey, (Datetime) itemValue);
                this._fields.add(elmDatetime);
            } else if (itemValue instanceof String) {
                Element elmString = new ReferenceElementString(itemKey, (String) itemValue);
                this._fields.add(elmString);
            } else if (itemValue instanceof ReferenceElementArrayChainTickers)
                this._fields.add((ReferenceElementArrayChainTickers) itemValue);
        }
    }

    public SchemaTypeDefinition typeDefinition() {
        return new SchemaTypeDefinitionImpl(this.datatype(), new Name("ReferenceFieldData"));
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

    public Element getElement(int index) {
        return this._fields.get(index);
    }

    public Element getElement(String name) {
        for (int i = 0; i < this._fields.size(); i++) {
            Element item = this._fields.get(i);
            if (item.name().toString().equals(name)) {
                return item;
            }
        }
        return super.getElement(name);
    }

    protected StringBuilder prettyPrint(int tabIndent) {
        
        StringBuilder result = new StringBuilder();

        result.append(String.format("%sfieldData[] = {%s", TAB, System.getProperty("line.separator")));

        for (Element field : this._fields) {
            result.append(field.toString());
        }

        result.append(String.format("%s}%s", TAB, System.getProperty("line.separator")));
        return result;
    }

    public boolean hasElement(String name) {
        return this.hasElement(name, false);
    }

    public boolean hasElement(String name, boolean excludeNullElements) {
        for (Element field : this._fields) {
            if (field.name().toString().equals(name))
                return true;
        }
        return false;
    }

    public double getElementAsFloat64(String name) {
        return this.getElement(name).getValueAsFloat64();
    }

    public int getElementAsInt32(String name) {
        return this.getElement(name).getValueAsInt32();
    }

    public long getElementAsInt64(String name) {
        return this.getElement(name).getValueAsInt64();
    }

    public String getElementAsString(String name) {
        return this.getElement(name).getValueAsString();
    }
}
