package com.assylias.jbloomberg.mock;

import com.bloomberglp.blpapi.*;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

public abstract class AbstractElement extends Element {
    protected abstract StringBuilder prettyPrint(int tabIndent);

    protected static final String TAB = "    ";

    public abstract Name name();

    public abstract int numValues();

    public abstract int numElements();

    public Schema.Datatype datatype() {
        return Schema.Datatype.SEQUENCE;
    }

    public SchemaTypeDefinition typeDefinition() {
        throw new UnsupportedOperationException("Element doesn't support typeDefinition");
//        return new SchemaTypeDefinition(this.datatype());
    }

    public String toString() {
        try {
            return this.prettyPrint(0).toString();
        } catch (Exception e) {
            return "exception thrown";
        }
    }

    protected StringBuilder prettyPrintHelper(String value) {
        return new StringBuilder(
            String.format(
                "%s%s = %s%s",
                TAB, this.name(), value, System.getProperty("line.separator")
            )
        );
    }

    public boolean isNull() {
        throw new UnsupportedOperationException("Element doesn't support IsNull");
    }

    public boolean isArray() {
        throw new UnsupportedOperationException("Element doesn't support IsArray");
    }

    public boolean isComplexType() {
        throw new UnsupportedOperationException("Element doesn't support IsComplexType");
    }

    public Object getValue() {
        return this.getValue(0);
    }

    public Object getValue(int index) {
        throw new UnsupportedOperationException("Element is not a simple object (index)");
    }

    public String getElementAsString(Name name) {
        return this.getElementAsString(name.toString());
    }

    public String getElementAsString(String name) {
        throw new UnsupportedOperationException("Element doesn't support getting elements as strings");
    }

    public Datetime getElementAsTime(Name name) {
        return this.getElementAsTime(name.toString());
    }

    public Datetime getElementAsTime(String name) {
        throw new UnsupportedOperationException("Element doesn't support getting elements as Times");
    }

    public Datetime getElementAsDate(Name name) {
        return this.getElementAsDate(name.toString());
    }

    public Datetime getElementAsDate(String name) {
        throw new UnsupportedOperationException("Element doesn't support getting elements as Dates");
    }

    public Datetime getElementAsDatetime(Name name) {
        return this.getElementAsDatetime(name.toString());
    }

    public Datetime getElementAsDatetime(String name) {
        throw new UnsupportedOperationException("Element doesn't support getting elements as Datetimes");
    }

    public int getElementAsInt32(Name name) {
        return this.getElementAsInt32(name.toString());
    }

    public int getElementAsInt32(String name) {
        throw new UnsupportedOperationException("Element doesn't support getting elements as Int32");
    }

    public long getElementAsInt64(Name name) {
        return this.getElementAsInt64(name.toString());
    }

    public long getElementAsInt64(String name) {
        throw new UnsupportedOperationException("Element doesn't support getting elements as Int64");
    }

    public double getElementAsFloat64(Name name) {
        return this.getElementAsFloat64(name.toString());
    }

    public double getElementAsFloat64(String name) {
        throw new UnsupportedOperationException("Element doesn't support getting elements as Float64");
    }

    public boolean getElementAsBool(Name name) {
        return this.getElementAsBool(name.toString());
    }

    public boolean getElementAsBool(String name) {
        throw new UnsupportedOperationException("Element doesn't support getting elements as bool");
    }

    public float getElementAsFloat32(Name name) {
        return this.getElementAsFloat32(name.toString());
    }

    public float getElementAsFloat32(String name) {
        throw new UnsupportedOperationException("Element doesn't support getting elements as Float32");
    }

    public Element getValueAsElement() {
        return this.getValueAsElement(0);
    }

    public Element getValueAsElement(int index) {
        throw new UnsupportedOperationException("Element doesn't support getting values as elements (index)");
    }

    public boolean getValueAsBool() {
        throw new UnsupportedOperationException("Element doesn't support getValueAsBool");
    }

    public boolean getValueAsBool(int index) {
        throw new UnsupportedOperationException("Element doesn't support getValueAsBool by index");
    }

    public String getValueAsString(int i) {
        throw new UnsupportedOperationException("Element doesn't support getValueAsString by index");
    }

    public float getValueAsFloat32() {
        throw new UnsupportedOperationException("Element doesn't support getValueAsFloat32");
    }

    public float getValueAsFloat32(int index) {
        throw new UnsupportedOperationException("Element doesn't support getValueAsFloat32");
    }

    public double getValueAsFloat64() {
        throw new UnsupportedOperationException("Element doesn't support getValueAsFloat64");
    }

    public double getValueAsFloat64(int index) {
        throw new UnsupportedOperationException("Element doesn't support getValueAsFloat64");
    }

    public int getValueAsInt32() {
        throw new UnsupportedOperationException("Element doesn't support getValueAsInt32");
    }

    public int getValueAsInt32(int index) {
        throw new UnsupportedOperationException("Element doesn't support getValueAsInt32");
    }

    public long getValueAsInt64() {
        throw new UnsupportedOperationException("Element doesn't support getValueAsInt64");
    }

    public long getValueAsInt64(int index) {
        throw new UnsupportedOperationException("Element doesn't support getValueAsInt64");
    }

    public String getValueAsString() {
        throw new UnsupportedOperationException("Element doesn't support getValueAsString");
    }

    public Datetime getValueAsDatetime() {
        throw new UnsupportedOperationException("Element doesn't support getValueAsDatetime");
    }

    public Datetime getValueAsDatetime(int index) {
        throw new UnsupportedOperationException("Element doesn't support getValueAsDatetime");
    }

    public Datetime getValueAsDate() {
        throw new UnsupportedOperationException("Element doesn't support getValueAsDate");
    }

    public Datetime getValueAsDate(int index) {
        throw new UnsupportedOperationException("Element doesn't support getValueAsDate");
    }

    public Datetime getValueAsTime() {
        throw new UnsupportedOperationException("Element doesn't support getValueAsTime");
    }

    public Datetime getValueAsTime(int index) {
        throw new UnsupportedOperationException("Element doesn't support getValueAsTime");
    }


    public Element getElement(Name name) {
        return this.getElement(name.toString());
    }

    public Element getElement(String name) {
        throw new UnsupportedOperationException("Element doesn't support getting elements by name. 'name' is case-sensitive.");
    }

    public Element getElement(int index) {
        throw new UnsupportedOperationException("Element doesn't support getting elements by index");
    }

    public boolean hasElement(Name name) {
        return this.hasElement(name.toString(), false);
    }

    public boolean hasElement(String name) {
        throw new UnsupportedOperationException("Element doesn't support HasElement");
    }

    public boolean hasElement(Name name, boolean excludeNullElements) {
        return this.hasElement(name.toString(), excludeNullElements);
    }

    public boolean hasElement(String name, boolean excludeNullElements) {
        throw new UnsupportedOperationException("Element doesn't support HasElement");
    }

    public Element appendElement() {
        throw new UnsupportedOperationException("Element doesn't support AppendElement");
    }

    public void setElement(Name name, Object value) {
        this.setElement(name.toString(), value);
    }

    public void setElement(String name, Object value) {
        throw new UnsupportedOperationException("Element doesn't support SetElement(Name name, string value)");
    }

    @Override
    public boolean isReadOnly() {
        return false;
    }

    @Override
    public SchemaElementDefinition elementDefinition() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean valueIsNull(int i) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isNullValue(int i) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEqualTo(Constant constant) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEqualTo(Constant constant, int i) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Constant findConstant(ConstantsList constantsList) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Constant findConstant(ConstantsList constantsList, int i) {
        throw new UnsupportedOperationException();
    }

    @Override
    public char getValueAsChar() {
        throw new UnsupportedOperationException();
    }

    @Override
    public char getValueAsChar(int i) {
        throw new UnsupportedOperationException();
    }

    @Override
    public byte[] getValueAsBytes() {
        throw new UnsupportedOperationException();
    }

    @Override
    public byte[] getValueAsBytes(int i) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Constant getValueAsEnumeration() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Constant getValueAsEnumeration(int i) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Name getValueAsName() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Name getValueAsName(int i) {
        throw new UnsupportedOperationException();
    }

    @Override
    public char getElementAsChar(Name name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public char getElementAsChar(String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public byte[] getElementAsBytes(Name name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public byte[] getElementAsBytes(String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Name getElementAsName(Name name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Name getElementAsName(String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Element getChoice() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void print(OutputStream outputStream) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void print(Writer writer) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object clone() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void appendValue(boolean b) {

    }

    @Override
    public void appendValue(char c) {

    }

    @Override
    public void appendValue(int i) {

    }

    @Override
    public void appendValue(long l) {

    }

    @Override
    public void appendValue(double v) {

    }

    @Override
    public void appendValue(float v) {

    }

    @Override
    public void appendValue(Datetime datetime) {

    }

    @Override
    public void appendValue(Constant constant) {

    }

    @Override
    public void appendValue(Name name) {

    }

    @Override
    public void appendValue(String s) {

    }

    @Override
    public void appendValue(byte[] bytes) {

    }

    @Override
    public void setElement(Name name, boolean b) {

    }

    @Override
    public void setElement(String s, boolean b) {

    }

    @Override
    public void setElement(Name name, char c) {

    }

    @Override
    public void setElement(String s, char c) {

    }

    @Override
    public void setElement(Name name, int i) {

    }

    @Override
    public void setElement(String s, int i) {

    }

    @Override
    public void setElement(Name name, long l) {

    }

    @Override
    public void setElement(String s, long l) {

    }

    @Override
    public void setElement(Name name, double v) {

    }

    @Override
    public void setElement(String s, double v) {

    }

    @Override
    public void setElement(Name name, float v) {

    }

    @Override
    public void setElement(String s, float v) {

    }

    @Override
    public void setElement(Name name, Datetime datetime) {

    }

    @Override
    public void setElement(String s, Datetime datetime) {

    }

    @Override
    public void setElement(Name name, Constant constant) {

    }

    @Override
    public void setElement(String s, Constant constant) {

    }

    @Override
    public void setElement(Name name, Name name1) {

    }

    @Override
    public void setElement(String s, Name name) {

    }

    @Override
    public void setElement(Name name, String s) {

    }

    @Override
    public void setElement(String s, String s1) {

    }

    @Override
    public void setElement(Name name, byte[] bytes) {

    }

    @Override
    public void setElement(String s, byte[] bytes) {

    }

    @Override
    public Element setChoice(Name name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Element setChoice(String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setValue(boolean b) {

    }

    @Override
    public void setValue(boolean b, int i) {

    }

    @Override
    public void setValue(char c) {

    }

    @Override
    public void setValue(char c, int i) {

    }

    @Override
    public void setValue(int i) {

    }

    @Override
    public void setValue(int i, int i1) {

    }

    @Override
    public void setValue(long l) {

    }

    @Override
    public void setValue(long l, int i) {

    }

    @Override
    public void setValue(double v) {

    }

    @Override
    public void setValue(double v, int i) {

    }

    @Override
    public void setValue(float v) {

    }

    @Override
    public void setValue(float v, int i) {

    }

    @Override
    public void setValue(Datetime datetime) {

    }

    @Override
    public void setValue(Datetime datetime, int i) {

    }

    @Override
    public void setValue(Constant constant) {

    }

    @Override
    public void setValue(Constant constant, int i) {

    }

    @Override
    public void setValue(Name name) {

    }

    @Override
    public void setValue(Name name, int i) {

    }

    @Override
    public void setValue(String s) {

    }

    @Override
    public void setValue(String s, int i) {

    }

    @Override
    public void setValue(byte[] bytes) {

    }

    @Override
    public void setValue(byte[] bytes, int i) {

    }
}
