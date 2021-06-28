package com.assylias.jbloomberg.mock.market;

import com.assylias.jbloomberg.mock.AbstractElement;
import com.bloomberglp.blpapi.Element;
import com.bloomberglp.blpapi.Name;

import java.util.LinkedList;
import java.util.List;

public class MarketElementSubscriptionCancelReason extends AbstractElement {
    private final MarketElementString _source, _category, _description, _subCategory;
    private final MarketElementInt _errorCode;

    MarketElementSubscriptionCancelReason() {
        this._source = new MarketElementString("source", "Session");
        this._errorCode = new MarketElementInt("errorCode", 0);
        this._category = new MarketElementString("category", "CANCELED");
        this._description = new MarketElementString("description", "Subscription canceled");
        this._subCategory = new MarketElementString("subcategory", "");
    }

    public Name name() {
        return new Name("reason");
    }

    public int numValues() {
        return 1;
    }

    public int numElements() {
        return 5;
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

    private List<Element> Elements() {
        List<Element> result = new LinkedList<Element>();
        result.add(this._source);
        result.add(this._errorCode);
        result.add(this._category);
        result.add(this._description);
        result.add(this._subCategory);
        return result;
    }

    protected StringBuilder prettyPrint(int tabIndent) {
        
        StringBuilder result = new StringBuilder();

        List<Element> elements = this.Elements();
        result.append(String.format("%sreason = {%s", TAB, System.getProperty("line.separator")));
        for (Element elm : elements) {
            result.append(elm.toString());
        }
        result.append(String.format("%s}%s", TAB, System.getProperty("line.separator")));

        return result;
    }
}
