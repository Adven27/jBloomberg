package com.assylias.jbloomberg.mock.reference;

import com.assylias.jbloomberg.mock.AbstractElement;
import com.assylias.jbloomberg.mock.SchemaTypeDefinitionImpl;
import com.bloomberglp.blpapi.Element;
import com.bloomberglp.blpapi.Name;
import com.bloomberglp.blpapi.SchemaTypeDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReferenceElementSecurityDataArray extends AbstractElement {
    private final List<ReferenceElementSecurityData> _securities;

    ReferenceElementSecurityDataArray(Map<String, Map<String, Object>> securities) {
        this._securities = new ArrayList<ReferenceElementSecurityData>();

        for (Map.Entry<String, Map<String, Object>> item : securities.entrySet()) {
            String key = item.getKey();
            Map<String, Object> value = item.getValue();

            ReferenceElementSecurityData secData = new ReferenceElementSecurityData(key, value, this._securities.size());
            this._securities.add(secData);
        }
    }

    ReferenceElementSecurityDataArray(ReferenceElementSecurityDataArray arg) //copy constructor
    {
        this._securities = new ArrayList<ReferenceElementSecurityData>();
        for (int i = 0; i < arg._securities.size(); i++) {
            this._securities.add(arg._securities.get(i));
        }
    }

    public SchemaTypeDefinition typeDefinition() {
        return new SchemaTypeDefinitionImpl(this.datatype(), new Name("ReferenceSecurityData"));
    }

    public Name name() {
        return new Name("securityData");
    }

    public int numValues() {
        return this._securities.size();
    }

    public int numElements() {
        return 0;
    }

    public boolean hasElement(String name) {
        for (int i = 0; i < this._securities.size(); i++) {
            ReferenceElementSecurityData item = this._securities.get(i);
            if (item.name().toString().equals(name))
                return true;
        }
        return false;
    }

    public Element getValueAsElement(int index) {
        return this._securities.get(index);
    }

    protected StringBuilder prettyPrint(int tabIndent) {
        StringBuilder result = new StringBuilder();
        result.append(String.format("%s%s[] = {%s", TAB, this.name().toString(), System.getProperty("line.separator")));

        for (ReferenceElementSecurityData security : this._securities) {
            result.append(security.prettyPrint(tabIndent + 1));
        }

        result.append(String.format("%s}%s", TAB, System.getProperty("line.separator")));
        return result;
    }
}
