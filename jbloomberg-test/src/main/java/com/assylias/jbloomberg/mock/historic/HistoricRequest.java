package com.assylias.jbloomberg.mock.historic;

import com.assylias.jbloomberg.mock.AbstractRequest;
import com.assylias.jbloomberg.mock.DisplayFormats;
import com.assylias.jbloomberg.mock.reference.ReferenceRequestElementOverrideArray;
import com.bloomberglp.blpapi.Datetime;
import com.bloomberglp.blpapi.Element;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HistoricRequest extends AbstractRequest {
    private final ReferenceRequestElementOverrideArray _overrides;
    private final HistoricRequestElementStringArray _securities, _fields;
    private HistoricRequestElementDate _dtStart, _dtEnd;

    private HistoricRequestElementBool _adjustmentNormalElement, _adjustmentAbnormalElement, _adjustmentSplitElement, _adjustmentFollowDPDF;

    private HistoricRequestElementInt _maxDataPointElement;

    private HistoricRequestElementString _periodicityAdjustmentElement, _periodicityElement, _overrideOptionsElement, _pricingOptionElement;

    private enum HistDataPeriodicityAdjustment {actual, calendar, fiscal}

    private HistDataPeriodicityAdjustment _periodicityAdjustment = HistDataPeriodicityAdjustment.actual;

    public enum HistDataPeriodicity {daily, weekly, monthly, quarterly, semi_annually, yearly}

    private HistDataPeriodicity _periodicity = HistDataPeriodicity.daily;

    public enum PricingOption {price, yield}

    @SuppressWarnings("unused")
    private PricingOption _pricingOption = PricingOption.price;

    public enum OverrideOptions {closingPrice, averagePrice}

    @SuppressWarnings("unused")
    private OverrideOptions _overrideOptions = OverrideOptions.closingPrice;

    public HistoricRequest() {
        this._dtStart = new HistoricRequestElementDate("startDate");
        this._dtEnd = new HistoricRequestElementDate("endDate");
        this._securities = new HistoricRequestElementStringArray("securities");
        this._fields = new HistoricRequestElementStringArray("fields");
        this._overrides = new ReferenceRequestElementOverrideArray();
    }

    public List<String> securities() {
        return this._securities.values();
    }

    List<String> fields() {
        return this._fields.values();
    }

    Datetime dtStart() {
        return this._dtStart.getDate();
    }

    Datetime dtEnd() {
        return this._dtEnd.getDate();
    }

    List<Datetime> getDates() {
        List<Datetime> result = this.getDatesBeforeMaxPoints();
        List<Datetime> truncatedResult = new ArrayList<Datetime>();

        if (this._maxDataPointElement == null)
            truncatedResult = result;
        else {
            if (result.size() > this._maxDataPointElement.getInt()) {
                for (int i = result.size() - this._maxDataPointElement.getInt(); i < result.size(); i++) {
                    truncatedResult.add(result.get(i));
                }
            } else
                truncatedResult = result;
        }

        return truncatedResult;
    }

    private List<Datetime> getDatesBeforeMaxPoints() {
        List<Datetime> result = new ArrayList<Datetime>();

        Datetime dtToday = new Datetime();

        Datetime dtStart;
        if (this._dtStart.getDate() == null) {
            dtStart = new Datetime();
            dtStart.calendar().add(Calendar.YEAR, -1); //com.bemu.Session.SendRequest assures that dtStart is not null
        } else
            dtStart = new Datetime(this._dtStart.getDate());


        Datetime dtEnd;
        if (this._dtEnd.getDate() == null) {
            dtEnd = new Datetime();
            dtEnd.calendar().add(Calendar.DAY_OF_MONTH, -1);
        } else
            dtEnd = new Datetime(this._dtEnd.getDate());

        if (this._periodicityAdjustment == HistDataPeriodicityAdjustment.fiscal) {
            dtStart.calendar().set(Calendar.DAY_OF_MONTH, 0);
        }

        Datetime dtCurrent = dtStart;
        do {
            result.add(new Datetime(dtCurrent));

            switch (this._periodicity) {
                case daily:
                    dtCurrent.calendar().add(Calendar.DAY_OF_MONTH, 1);
                    break;
                case weekly:
                    dtCurrent.calendar().add(Calendar.DAY_OF_MONTH, 7);
                    break;
                case monthly:
                    dtCurrent.calendar().add(Calendar.MONTH, 1);
                    break;
                case quarterly:
                    dtCurrent.calendar().add(Calendar.MONTH, 3);
                    break;
                case semi_annually:
                    dtCurrent.calendar().add(Calendar.MONTH, 6);
                    break;
                case yearly:
                    dtCurrent.calendar().add(Calendar.YEAR, 1);
                    break;
            }

            if (dtCurrent.calendar().get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
                dtCurrent.calendar().add(Calendar.DAY_OF_MONTH, 2);
            else if (dtCurrent.calendar().get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
                dtCurrent.calendar().add(Calendar.DAY_OF_MONTH, 1);

        } while (dtCurrent.calendar().getTimeInMillis() <= dtEnd.calendar().getTimeInMillis() && dtCurrent.calendar().getTimeInMillis() <= dtToday.calendar().getTimeInMillis());

        return result;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("HistoricalDataRequest = {").append(System.getProperty("line.separator"));

        if (this._securities.numValues() > 0)
            result.append(this._securities.prettyPrint(1));

        if (this._fields.numValues() > 0)
            result.append(this._fields.prettyPrint(1));

        Element[] elms = {this._dtStart, this._dtEnd, this._periodicityAdjustmentElement, this._periodicityElement,
            this._overrideOptionsElement, this._pricingOptionElement, this._maxDataPointElement, this._adjustmentNormalElement,
            this._adjustmentAbnormalElement, this._adjustmentSplitElement, this._adjustmentFollowDPDF};

        for (Element current : elms) {
            if (current != null) {
                try {
                    result.append(current);
                } catch (Exception ignored) {
                }
            }
        }

        result.append("}").append(System.getProperty("line.separator"));
        return result.toString();
    }

    public void append(String name, String elementValue) {
        if (name.equals("securities"))
            this._securities.appendValue(elementValue);

        else if (name.equals("fields"))
            this._fields.appendValue(elementValue);

        else
            throw new UnsupportedOperationException("not implemented. names are case-sensitive");
    }

    public void set(String name, String elementValue) {
        switch (name) {
            case "startDate":
                Datetime dtStart = DisplayFormats.HistRef_TryParseInput(elementValue);
                if (dtStart == null)
                    throw new IllegalArgumentException("startDate must be in the format yyyyMMdd");
                else
                    this._dtStart = new HistoricRequestElementDate("startDate", dtStart);
                break;
            case "endDate":
                Datetime dtEnd = DisplayFormats.HistRef_TryParseInput(elementValue);
                if (dtEnd == null)
                    throw new IllegalArgumentException("endDate must be in the format yyyyMMdd");
                else
                    this._dtEnd = new HistoricRequestElementDate("endDate", dtEnd);
                break;
            case "periodicityAdjustment":
                switch (elementValue) {
                    case "CALENDAR":
                        this._periodicityAdjustment = HistDataPeriodicityAdjustment.calendar;
                        break;
                    case "FISCAL":
                        this._periodicityAdjustment = HistDataPeriodicityAdjustment.fiscal;
                        break;
                    case "ACTUAL":
                        this._periodicityAdjustment = HistDataPeriodicityAdjustment.actual;
                        break;
                    default:
                        throw new IllegalArgumentException("periodicityadjustment must be CALENDAR, FISCAL, or ACTUAL. Values are case-sensitive.");
                }

                this._periodicityAdjustmentElement = new HistoricRequestElementString("periodicityAdjustment", elementValue);
                break;
            case "periodicitySelection":
                switch (elementValue) {
                    case "WEEKLY":
                        this._periodicity = HistDataPeriodicity.weekly;
                        break;
                    case "MONTHLY":
                        this._periodicity = HistDataPeriodicity.monthly;
                        break;
                    case "QUARTERLY":
                        this._periodicity = HistDataPeriodicity.quarterly;
                        break;
                    case "SEMI_ANNUALLY":
                        this._periodicity = HistDataPeriodicity.semi_annually;
                        break;
                    case "YEARLY":
                        this._periodicity = HistDataPeriodicity.yearly;
                        break;
                    case "DAILY":
                        this._periodicity = HistDataPeriodicity.daily;
                        break;
                    default:
                        throw new IllegalArgumentException("periodicityselection must be DAILY, WEEKLY, MONTHLY, QUARTERLY, SEMI_ANNUALLY, or YEARLY. Values are case-sensitive.");
                }

                this._periodicityElement = new HistoricRequestElementString("periodicitySelection", elementValue);
                break;
            case "pricingOption":
                switch (elementValue) {
                    case "PRICING_OPTION_YIELD":
                        this._pricingOption = PricingOption.yield;
                        break;
                    case "PRICING_OPTION_PRICE":
                        this._pricingOption = PricingOption.price;
                        break;
                    default:
                        throw new IllegalArgumentException("pricingoption must be either PRICING_OPTION_YIELD or PRICING_OPTION_PRICE. Values are case-sensitive.");
                }

                this._pricingOptionElement = new HistoricRequestElementString("pricingOption", elementValue);
                break;
            case "overrideOption":
                switch (elementValue) {
                    case "OVERRIDE_OPTION_GPA":
                        this._overrideOptions = OverrideOptions.averagePrice;
                        break;
                    case "OVERRIDE_OPTION_CLOSE":
                        this._overrideOptions = OverrideOptions.closingPrice;
                        break;
                    default:
                        throw new IllegalArgumentException("overrideoption must be either OVERRIDE_OPTION_GPA or OVERRIDE_OPTION_CLOSE. Values are case-sensitive.");
                }

                this._overrideOptionsElement = new HistoricRequestElementString("overrideOption", elementValue);
                break;
        }
    }

    public void set(String name, boolean elementValue) {
        switch (name) {
            case "adjustmentNormal":
                this._adjustmentNormalElement = new HistoricRequestElementBool("adjustmentNormal", elementValue);
                break;
            case "adjustmentAbnormal":
                this._adjustmentAbnormalElement = new HistoricRequestElementBool("adjustmentAbnormal", elementValue);
                break;
            case "adjustmentSplit":
                this._adjustmentSplitElement = new HistoricRequestElementBool("adjustmentSplit", elementValue);
                break;
            case "adjustmentFollowDPDF":
                this._adjustmentFollowDPDF = new HistoricRequestElementBool("adjustmentFollowDPDF", elementValue);
                break;
            default:
                throw new UnsupportedOperationException("not implemented. Names are case-sensitive.");
        }
    }

    public void set(String name, int elementValue) {
        if ("maxDataPoints".equals(name)) {
            this._maxDataPointElement = new HistoricRequestElementInt("maxDataPoints", elementValue);
        } else {
            throw new UnsupportedOperationException("not implemented. names are case-sensitive");
        }
    }

    public Element getElement(String name) {
        if (this._securities.name().toString().equals(name))
            return this._securities;

        else if (this._fields.name().toString().equals(name))
            return this._fields;

        else if (this._overrides.name().toString().equals(name))
            return this._overrides;

        else if (this._dtStart.name().toString().equals(name))
            return this._dtStart;

        else if (this._dtEnd.name().toString().equals(name))
            return this._dtEnd;

        else if (this._periodicityAdjustmentElement.name().toString().equals(name))
            return this._periodicityAdjustmentElement;

        else if (this._periodicityElement.name().toString().equals(name))
            return this._periodicityElement;

        else if (this._adjustmentNormalElement.name().toString().equals(name))
            return this._adjustmentNormalElement;

        else if (this._adjustmentAbnormalElement.name().toString().equals(name))
            return this._adjustmentAbnormalElement;

        else if (this._adjustmentSplitElement.name().toString().equals(name))
            return this._adjustmentSplitElement;

        else if (this._maxDataPointElement.name().toString().equals(name))
            return this._maxDataPointElement;

        else
            return super.getElement(name);
    }
}
