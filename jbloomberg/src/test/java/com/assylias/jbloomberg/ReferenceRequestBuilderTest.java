package com.assylias.jbloomberg;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Test(groups="unit")
public class ReferenceRequestBuilderTest {

    @Test(expectedExceptions = NullPointerException.class)
    public void testConstructor_NullTickers() {
        new ReferenceRequestBuilder((Collection<String>) null, Arrays.asList("a"));
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testConstructor_TickersContainsNull() {
        new ReferenceRequestBuilder(Arrays.asList((String) null), Arrays.asList("a"));
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = ".*empty.*")
    public void testConstructor_EmptyTickers() {
        new ReferenceRequestBuilder(Collections.<String> emptyList(), Arrays.asList("a"));
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = ".*empty\\sstrings.*")
    public void testConstructor_TickersContainsEmptyString() {
        new ReferenceRequestBuilder(Arrays.asList(""), Arrays.asList("a"));
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testConstructor_NullFields() {
        new ReferenceRequestBuilder(Arrays.asList("a"), (Collection<String>) null);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testConstructor_FieldsContainsNull() {
        new ReferenceRequestBuilder(Arrays.asList("a"), Arrays.asList((String) null));
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = ".*empty.*")
    public void testConstructor_EmptyFields() {
        new ReferenceRequestBuilder(Arrays.asList("a"), Collections.<String> emptyList());
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = ".*empty\\sstrings.*")
    public void testConstructor_FieldsContainsEmptyString() {
        new ReferenceRequestBuilder(Arrays.asList("a"), Arrays.asList(""));
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testOverride_NullField() {
        ReferenceRequestBuilder builder = new ReferenceRequestBuilder("IBM US Equity", "PX_LAST");
        builder.addOverride(null, "asd");
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testOverride_NullValue() {
        ReferenceRequestBuilder builder = new ReferenceRequestBuilder("IBM US Equity", "PX_LAST");
        builder.addOverride("abc", null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testOverride_EmptyField() {
        ReferenceRequestBuilder builder = new ReferenceRequestBuilder("IBM US Equity", "PX_LAST");
        builder.addOverride("", "asd");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testOverride_EmptyValue() {
        ReferenceRequestBuilder builder = new ReferenceRequestBuilder("IBM US Equity", "PX_LAST");
        builder.addOverride("abc", "");
    }

    @Test
    public void testConstructor_AllOk() {
        new ReferenceRequestBuilder("IBM US Equity", "PX_LAST")
                .addOverride("abc", "def");
    }

    @Test
    public void testServiceType() {
        assertEquals(new ReferenceRequestBuilder("ABC", "DEF").getServiceType(),
                BloombergServiceType.REFERENCE_DATA);
    }

    @Test
    public void testRequestType() {
        assertEquals(new ReferenceRequestBuilder("ABC", "DEF").getRequestType(),
                BloombergRequestType.REFERENCE_DATA);
    }

    @Test
    public void testToString() {
        ReferenceRequestBuilder builder = new ReferenceRequestBuilder("ABC", "DEF");
        builder.addOverride("a", "b");
        builder.addOverride("c", "d");
        assertTrue(builder.toString().contains("a=b,c=d"));
    }
}
