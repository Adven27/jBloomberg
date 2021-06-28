package com.assylias.jbloomberg.mock;

import com.bloomberglp.blpapi.*;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

public abstract class AbstractRequest implements Request {
    protected AbstractRequest() {
        this._operation = new Operation() {
            @Override
            public Name name() {
                return null;
            }

            @Override
            public Schema.Status status() {
                return null;
            }

            @Override
            public SchemaTypeDefinition requestType() {
                return null;
            }

            @Override
            public SchemaElementDefinition requestDefinition() {
                return null;
            }

            @Override
            public SchemaTypeDefinition[] responseType() {
                return new SchemaTypeDefinition[0];
            }

            @Override
            public int numResponseDefinitions() {
                return 0;
            }

            @Override
            public SchemaElementDefinition responseDefinition(int i) {
                return null;
            }

            @Override
            public SchemaElementDefinition responseDefinition(Name name) {
                return null;
            }

            @Override
            public String description() {
                return null;
            }
        };
    }

    private CorrelationID _correlationId;
    private final Operation _operation;

    public Operation operation() {
        return this._operation;
    }

    public Element asElement()  {
        throw new UnsupportedOperationException("not implemented");
    }

    public CorrelationID correlationId() {
        return this._correlationId;
    }

    void correlationId(CorrelationID corr) {
        this._correlationId = corr;
    }

    public void append(String name, String elementValue)  {
        throw new UnsupportedOperationException("BEmu.Request.Append: Append is not implemented");
    }

    public void set(String name, String elementValue)  {
        throw new UnsupportedOperationException("BEmu.Request.Set: string is not implemented");
    }

    public void set(String name, boolean elementValue)  {
        throw new UnsupportedOperationException("BEmu.Request.Set: bool is not implemented");
    }

    public void set(String name, int elementValue)  {
        throw new UnsupportedOperationException("BEmu.Request.Set: int is not implemented");
    }

    public void set(String name, Datetime elementValue)  {
        throw new UnsupportedOperationException("BEmu.Request.Set: Datetime is not implemented");
    }

    public void set(Name name, String elementValue)  {
        this.set(name.toString(), elementValue);
    }

    public void set(Name name, boolean elementValue)  {
        this.set(name.toString(), elementValue);
    }

    public void set(Name name, int elementValue)  {
        this.set(name.toString(), elementValue);
    }

    public void set(Name name, Datetime elementValue)  {
        this.set(name.toString(), elementValue);
    }

    public Element getElement(Name name)  {
        return this.getElement(name.toString());
    }

    public Element getElement(String name)  {
        throw new UnsupportedOperationException("BEmu.Request.this[]: by string is not implemented");
    }

    public boolean hasElement(String name)  {
        throw new UnsupportedOperationException("BEmu.Request.HasElement: string is not implemented");
    }

    @Override
    public boolean hasElement(Name name) {
        return false;
    }

    @Override
    public String getRequestId() {
        return null;
    }

    @Override
    public void append(Name name, boolean b) {

    }

    @Override
    public void append(String s, boolean b) {

    }

    @Override
    public void append(Name name, byte[] bytes) {

    }

    @Override
    public void append(String s, byte[] bytes) {

    }

    @Override
    public void append(Name name, char c) {

    }

    @Override
    public void append(String s, char c) {

    }

    @Override
    public void append(Name name, int i) {

    }

    @Override
    public void append(String s, int i) {

    }

    @Override
    public void append(Name name, long l) {

    }

    @Override
    public void append(String s, long l) {

    }

    @Override
    public void append(Name name, double v) {

    }

    @Override
    public void append(String s, double v) {

    }

    @Override
    public void append(Name name, float v) {

    }

    @Override
    public void append(String s, float v) {

    }

    @Override
    public void append(Name name, Datetime datetime) {

    }

    @Override
    public void append(String s, Datetime datetime) {

    }

    @Override
    public void append(Name name, Constant constant) {

    }

    @Override
    public void append(String s, Constant constant) {

    }

    @Override
    public void append(Name name, Name name1) {

    }

    @Override
    public void append(String s, Name name) {

    }

    @Override
    public void append(Name name, String s) {

    }

    @Override
    public void set(Name name, char c) {

    }

    @Override
    public void set(String s, char c) {

    }

    @Override
    public void set(Name name, long l) {

    }

    @Override
    public void set(String s, long l) {

    }

    @Override
    public void set(Name name, double v) {

    }

    @Override
    public void set(String s, double v) {

    }

    @Override
    public void set(Name name, float v) {

    }

    @Override
    public void set(String s, float v) {

    }

    @Override
    public void set(Name name, Constant constant) {

    }

    @Override
    public void set(String s, Constant constant) {

    }

    @Override
    public void set(Name name, Name name1) {

    }

    @Override
    public void set(String s, Name name) {

    }

    @Override
    public void set(Name name, byte[] bytes) {

    }

    @Override
    public void print(OutputStream outputStream) throws IOException {

    }

    @Override
    public void print(Writer writer) throws IOException {

    }

}
