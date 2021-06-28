package com.assylias.jbloomberg.mock.reference;

import com.assylias.jbloomberg.mock.AbstractElement;
import com.assylias.jbloomberg.mock.OptionalityEnum;
import com.assylias.jbloomberg.mock.SchemaTypeDefinitionImpl;
import com.bloomberglp.blpapi.Datetime;
import com.bloomberglp.blpapi.Name;
import com.bloomberglp.blpapi.SchemaTypeDefinition;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ReferenceElementArrayChainTickersItem extends AbstractElement {
    private final ReferenceElementString _element;

    ReferenceElementArrayChainTickersItem(String ticker, Datetime dtExp, OptionalityEnum optionality, int strike) {
        DateFormat df = new SimpleDateFormat("MM/dd/yy");
        String optionTicker = String.format(
                "%s US %s %s%s",
                ticker,
                df.format(dtExp.calendar().getTime()),
                optionality.toString().toUpperCase(),
                strike
        );

        this._element = new ReferenceElementString("Ticker", optionTicker);
    }

    public SchemaTypeDefinition typeDefinition() {
        return new SchemaTypeDefinitionImpl(this.datatype(), new Name("CHAIN_TICKERS"));
    }

    public Name name() {
        return new Name("Ticker");
    }

    public int numValues() {
        return 0;
    }

    public int numElements() {
        return 1;
    }

    public boolean isComplexType() {
        return false;
    }

    public boolean isArray() {
        return false;
    }

    public boolean isNull() {
        return false;
    }

    public String getElementAsString(String name) {
        if (this._element.name().toString().equals(name))
            return this._element.getValueAsString();

        else
            return super.getElementAsString(name);
    }

    protected StringBuilder prettyPrint(int tabIndent) {
        
        StringBuilder result = new StringBuilder();

        result.append(String.format("%sCHAIN_TICKERS = {%s", TAB, System.getProperty("line.separator")));
        result.append(this._element.prettyPrint(tabIndent + 1));
        result.append(String.format("%s}%s", TAB, System.getProperty("line.separator")));

        return result;
    }
}
