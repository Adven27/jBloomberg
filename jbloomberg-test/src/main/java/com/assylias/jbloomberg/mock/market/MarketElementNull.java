package com.assylias.jbloomberg.mock.market;

import com.assylias.jbloomberg.mock.AbstractElement;
import com.bloomberglp.blpapi.Name;

public class MarketElementNull extends AbstractElement {
    private final String _name;

    MarketElementNull(String name) {
        this._name = name;
    }

    public Name name() {
        return new Name(this._name);
    }

    public int numValues() {
        return 0;
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
        return true;
    }

    protected StringBuilder prettyPrint(int tabIndent) {
        return new StringBuilder();
    }
}
