package com.assylias.jbloomberg.mock;

import com.bloomberglp.blpapi.*;

public class SchemaTypeDefinitionImpl extends SchemaTypeDefinition {
    public SchemaTypeDefinitionImpl(Schema.Datatype datatype) throws Exception {
        this._datatype = datatype;
        this._name = new Name(this._datatype.toString());
    }

    public SchemaTypeDefinitionImpl(Schema.Datatype datatype, Name name) {
        this._datatype = datatype;
        this._name = name;
    }

    private final Schema.Datatype _datatype;
    private final Name _name;

    @Override
    public void setUserData(Object o) {

    }

    public Name name() {
        return this._name;
    }

    @Override
    public String description() {
        return null;
    }

    @Override
    public Schema.Status status() {
        return null;
    }

    @Override
    public Schema.Datatype datatype() {
        return null;
    }

    @Override
    public boolean isComplexType() {
        return false;
    }

    @Override
    public boolean isSimpleType() {
        return false;
    }

    @Override
    public boolean isEnumerationType() {
        return false;
    }

    @Override
    public ConstantsList enumeration() {
        return null;
    }

    @Override
    public ConstraintsList constraints() {
        return null;
    }

    @Override
    public Object userData() {
        return null;
    }

    @Override
    public boolean hasElementDefinition(Name name) {
        return false;
    }

    @Override
    public SchemaElementDefinition getElementDefinition(Name name) {
        return null;
    }

    @Override
    public SchemaElementDefinition getElementDefinition(String s) {
        return null;
    }

    @Override
    public int numElementDefinitions() {
        return 0;
    }

    @Override
    public SchemaElementDefinition getElementDefinition(int i) {
        return null;
    }
}
