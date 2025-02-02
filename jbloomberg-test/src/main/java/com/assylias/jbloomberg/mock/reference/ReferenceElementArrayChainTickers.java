package com.assylias.jbloomberg.mock.reference;

import com.assylias.jbloomberg.mock.DisplayFormats;
import com.assylias.jbloomberg.mock.OptionalityEnum;
import com.assylias.jbloomberg.mock.RandomDataGenerator;
import com.assylias.jbloomberg.mock.SchemaTypeDefinitionImpl;
import com.bloomberglp.blpapi.Datetime;
import com.bloomberglp.blpapi.Element;
import com.bloomberglp.blpapi.Name;
import com.bloomberglp.blpapi.SchemaTypeDefinition;

import java.util.ArrayList;
import java.util.Calendar;

public class ReferenceElementArrayChainTickers extends ReferenceElementArray {
    public ReferenceElementArrayChainTickers(String underlier, int numPoints, String strDtExp, OptionalityEnum optionality) throws Exception {
        super("CHAIN_TICKERS", new ArrayList<>());

        int indexSpace = underlier.indexOf(' ');
        String ticker = underlier.substring(0, indexSpace);

        Datetime dtExp;
        if (strDtExp == null) {
            dtExp = new Datetime();
            dtExp.calendar().add(Calendar.MONTH, 1);
            dtExp.calendar().set(Calendar.DAY_OF_MONTH, 20); //assume the 20th of the month
        } else if (strDtExp.length() == 8) {
            dtExp = DisplayFormats.HistRef_TryParseInput(strDtExp);
        } else if (strDtExp.length() == 6) {
            dtExp = DisplayFormats.HistRef_TryParseInput(strDtExp + "20");
        } else if (strDtExp.endsWith("F")) {
            String strNumMonths = strDtExp.substring(0, strDtExp.length() - 2);
            int numMonths = Integer.parseInt(strNumMonths);
            Calendar c = Calendar.getInstance();
            c.set(Calendar.HOUR, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.add(Calendar.MONTH, numMonths);
            c.set(Calendar.DAY_OF_MONTH, 20);
            dtExp = new Datetime(c);
        } else {
            throw new Exception("BEmu.ReferenceDataRequest.ElementReferenceArrayChainTickers: unable to determine the dtExp");
        }

        int strike = RandomDataGenerator.strike();
        for (int count = 0; count < numPoints; count++, strike += 5) {
            Element elm = new ReferenceElementArrayChainTickersItem(ticker, dtExp, optionality, strike);
            super._values.add(elm);
        }
    }

    public SchemaTypeDefinition typeDefinition() {
        return new SchemaTypeDefinitionImpl(this.datatype(), new Name("CHAIN_TICKERS"));
    }
}
