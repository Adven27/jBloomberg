package com.assylias.jbloomberg.mock;

import com.bloomberglp.blpapi.*;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

public abstract class AbstractMessage extends Message {
    private Service _service;

    public Service service() {
        return this._service;
    }

    private Name _messageType;

    public Name messageType() {
        return this._messageType;
    }

    private CorrelationID _correlationID;

    public CorrelationID correlationID() {
        return this._correlationID;
    }

    protected AbstractMessage(Name messageType, CorrelationID corr, Service service) {
        this._correlationID = corr;
        this._messageType = messageType;
        this._service = service;
    }

    public Element getElement(Name name) {
        return this.getElement(name.toString());
    }

    public Element getElement(String name) {
        throw new UnsupportedOperationException("not implemented");
    }

    public boolean hasElement(String name, boolean excludeNullElements) {
        throw new UnsupportedOperationException("not implemented");
    }

    public boolean hasElement(String name) {
        return this.hasElement(name, false);
    }

    public boolean hasElement(Name name, boolean excludeNullElements) {
        return this.hasElement(name.toString(), excludeNullElements);
    }

    public boolean hasElement(Name name) {
        return this.hasElement(name.toString());
    }

    public int numElements() {
        throw new UnsupportedOperationException("not implemented");
    }

    public int numValues() {
        throw new UnsupportedOperationException("not implemented");
    }

    public String topicName() {
        throw new UnsupportedOperationException("not implemented");
    }


    public double getElementAsFloat64(String name) {
        return this.getElement(name).getValueAsFloat64();
    }

    public float getElementAsFloat32(String name) {
        return this.getElement(name).getValueAsFloat32();
    }

    public long getElementAsInt64(String name) {
        return this.getElement(name).getValueAsInt64();
    }

    public int getElementAsInt32(String name) {
        return this.getElement(name).getValueAsInt32();
    }

    public String getElementAsString(String name) {
        return this.getElement(name).getValueAsString();
    }

    public Datetime getElementAsDatetime(String name) {
        return this.getElement(name).getValueAsDatetime();
    }

    public Datetime getElementAsDate(String name) {
        return this.getElement(name).getValueAsDate();
    }

    public Datetime getElementAsTime(String name) {
        return this.getElement(name).getValueAsTime();
    }

    @Override
    public Fragment fragmentType() {
        return null;
    }

    @Override
    public Recap recapType() {
        return null;
    }

    @Override
    public CorrelationID correlationIDAt(int i) {
        return null;
    }

    @Override
    public CorrelationID correlationID(int i) {
        return null;
    }

    @Override
    public int numCorrelationIds() {
        return 0;
    }

    @Override
    public long timeReceivedMillis() {
        return 0;
    }

    @Override
    public Element asElement() {
        return null;
    }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public boolean getElementAsBool(Name name) {
        return false;
    }

    @Override
    public boolean getElementAsBool(String s) {
        return false;
    }

    @Override
    public byte[] getElementAsBytes(Name name) {
        return new byte[0];
    }

    @Override
    public byte[] getElementAsBytes(String s) {
        return new byte[0];
    }

    @Override
    public char getElementAsChar(Name name) {
        return 0;
    }

    @Override
    public char getElementAsChar(String s) {
        return 0;
    }

    @Override
    public int getElementAsInt32(Name name) {
        return 0;
    }

    @Override
    public long getElementAsInt64(Name name) {
        return 0;
    }

    @Override
    public double getElementAsFloat64(Name name) {
        return 0;
    }

    @Override
    public float getElementAsFloat32(Name name) {
        return 0;
    }

    @Override
    public String getElementAsString(Name name) {
        return null;
    }

    @Override
    public Datetime getElementAsDatetime(Name name) {
        return null;
    }

    @Override
    public Datetime getElementAsDate(Name name) {
        return null;
    }

    @Override
    public Datetime getElementAsTime(Name name) {
        return null;
    }

    @Override
    public Name getElementAsName(Name name) {
        return null;
    }

    @Override
    public Name getElementAsName(String s) {
        return null;
    }

    @Override
    public String getRequestId() {
        return null;
    }

    @Override
    public void print(OutputStream outputStream) throws IOException {

    }

    @Override
    public void print(Writer writer) throws IOException {

    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }
}
