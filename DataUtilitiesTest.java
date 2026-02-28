package org.jfree.data.test;

import static org.junit.Assert.*;

import org.jmock.Mockery;
import org.jmock.Expectations;
import org.junit.Test;

import org.jfree.data.DataUtilities;
import org.jfree.data.KeyedValues;
import org.jfree.data.Values2D;

public class DataUtilitiesTest {
	
// ========== calculateColumnTotal (5) ==========
	
@Test 
public void calculateColumnTotalForTwoValues() {
    Mockery mockingContext = new Mockery();
    final Values2D values = mockingContext.mock(Values2D.class);

    mockingContext.checking(new Expectations() {{
        oneOf(values).getRowCount(); will(returnValue(2));
        oneOf(values).getValue(0, 0); will(returnValue(6));
        oneOf(values).getValue(1, 0); will(returnValue(3));
    }});

    double result = DataUtilities.calculateColumnTotal(values, 0);

    assertEquals(9.0, result, 1e-9);
}

@Test 
public void calculateColumnTotalForSingleRow() {
	Mockery mockingContext = new Mockery();
    final Values2D values = mockingContext.mock(Values2D.class);

    mockingContext.checking(new Expectations() {{
    	oneOf(values).getRowCount(); will(returnValue(1));
    	oneOf(values).getValue(0,1); will(returnValue(5));
	}});
    
    double result = DataUtilities.calculateColumnTotal(values, 0);
    
	assertEquals(5.0, result, 1e-9);
	mockingContext.assertIsSatisfied();
}

@Test 
public void calculateColumnTotalForemptyTableIsZero() {
	Mockery mockingContext = new Mockery();
    final Values2D values = mockingContext.mock(Values2D.class);
    
    mockingContext.checking(new Expectations() {{
    	oneOf(values).getRowCount(); will(returnValue(0));
	}});
    
    double result = DataUtilities.calculateColumnTotal(values, 0);
    
	assertEquals(0.0, result, 1e-9);
	mockingContext.assertIsSatisfied();
}

@Test 
public void calculateColumnTotalForNegativeValues() {
	Mockery mockingContext = new Mockery();
    final Values2D values = mockingContext.mock(Values2D.class);
    
	mockingContext.checking(new Expectations() {{
		oneOf(values).getRowCount(); will(returnValue(2));
		oneOf(values).getValue(0,0); will(returnValue(-2.0));
		oneOf(values).getValue(1,0); will(returnValue(-3.0));
	}});
	
	double result = DataUtilities.calculateColumnTotal(values, 0);
	
	assertEquals(-5.0, result, 1e-9);
	mockingContext.assertIsSatisfied();
}

@Test 
public void calculateColumnTotalForLargeNumbers() {
	Mockery mockingContext = new Mockery();
    final Values2D values = mockingContext.mock(Values2D.class);
    
    mockingContext.checking(new Expectations() {{
    	oneOf(values).getRowCount(); will(returnValue(2));
    	oneOf(values).getValue(0,0); will(returnValue(1e9));
    	oneOf(values).getValue(1,0); will(returnValue(2e9));
	}});
    
    double result = DataUtilities.calculateColumnTotal(values, 0);
    
	assertEquals(3e9, result, 1e-6);
	mockingContext.assertIsSatisfied();
}

// ========== calculateRowTotal (5) ==========
@Test 
public void calculateRowTotalForTwoValues() {
	Mockery mockingContext = new Mockery();
    final Values2D values = mockingContext.mock(Values2D.class);
    
    mockingContext.checking(new Expectations() {{
    	allowing(values).getColumnCount(); will(returnValue(2));
    	allowing(values).getValue(0,0); will(returnValue(1.0));
    	allowing(values).getValue(0,1); will(returnValue(4.0));
    	
    	
	}});
    
    double result = DataUtilities.calculateRowTotal(values, 0);
    
	assertEquals(5.0, result, 1e-9);
	mockingContext.assertIsSatisfied();
}

@Test 
public void calculateRowTotalForSingleColumnSingleRow() {
	Mockery mockingContext = new Mockery();
    final Values2D values = mockingContext.mock(Values2D.class);
    
    mockingContext.checking(new Expectations() {{
    	allowing(values).getColumnCount(); will(returnValue(1));
    	oneOf(values).getValue(0,0); will(returnValue(9.0));
	}});
    
    double result = DataUtilities.calculateRowTotal(values, 0);
    
	assertEquals(9.0, result, 1e-9);
	mockingContext.assertIsSatisfied();
}

@Test 
public void calculateRowTotalForEmptyTableIsZero() {
	Mockery mockingContext = new Mockery();
    final Values2D values = mockingContext.mock(Values2D.class);
    
    mockingContext.checking(new Expectations() {{
    	oneOf(values).getColumnCount(); will(returnValue(0));
	}});
    
    double result = DataUtilities.calculateRowTotal(values, 0);
    
	assertEquals(0.0, result, 1e-9);
	mockingContext.assertIsSatisfied();
}

@Test 
public void calculateRowTotalForNegativeValues() {
	Mockery mockingContext = new Mockery();
    final Values2D values = mockingContext.mock(Values2D.class);
    
    mockingContext.checking(new Expectations() {{
    	oneOf(values).getColumnCount(); will(returnValue(2));
    	oneOf(values).getValue(1,0); will(returnValue(-2.0));
    	oneOf(values).getValue(1,1); will(returnValue(-3.0));
	}});
    
    double result = DataUtilities.calculateRowTotal(values, 0);
    
	assertEquals(-5.0, result, 1e-9);
	mockingContext.assertIsSatisfied();
}

@Test 
public void calculateRowTotalForLargeNumbers() {
	Mockery mockingContext = new Mockery();
    final Values2D values = mockingContext.mock(Values2D.class);
    
    mockingContext.checking(new Expectations() {{
    	oneOf(values).getColumnCount(); will(returnValue(2));
    	oneOf(values).getValue(0,0); will(returnValue(1e9));
    	oneOf(values).getValue(0,1); will(returnValue(2e9));
	}});
    
    double result = DataUtilities.calculateRowTotal(values, 0);
    
	assertEquals(3e9, result, 1e-6);
	mockingContext.assertIsSatisfied();
}

// ========== createNumberArray (5) ==========

@Test 
public void createNumberArrayCopiesValues() {
	double[] in = {1.1,-2.2, 3.3};
	Number[] out = DataUtilities.createNumberArray(in);
	assertNotNull(out);
	assertEquals(3, out.length);
}

@Test 
public void createNumberArraySingleElement() {
	Number[] out = DataUtilities.createNumberArray(new double[]{4.5});
	assertNotNull(out);
	assertEquals(1, out.length);
}
	
@Test
(expected = IllegalArgumentException.class)
public void createNumberArrayNullInputThrowsException() {
 DataUtilities.createNumberArray(null);
}

@Test
public void createNumberArrayHandlesZeros() {
 Number[] out = DataUtilities.createNumberArray(new double[] { 0.0, -0.0 });
 assertNotNull(out);
 assertEquals(2, out.length);
 assertNotNull(out[0]);
}

@Test
public void createNumberArrayLargeValues() {
 Number[] out = DataUtilities.createNumberArray(new double[] { 1e9, -1e9 });
 assertNotNull(out);
 assertEquals(2, out.length);
}

// ========== createNumberArray2D (5) ==========

@Test 
public void createNumberArray2DCopyValues() {
double[][] in = {{1.0, 2.0}, {-1.5, 0.5}};
Number[][] out = DataUtilities.createNumberArray2D(in);
assertNotNull(out);
assertEquals(2, out.length);
assertEquals(2, out[0].length);
}

@Test 
public void createNumberArray2DSingleCell() {
Number[][] out = DataUtilities.createNumberArray2D(new double[][]{{7.7}});
assertNotNull(out);
assertEquals(1, out.length);
assertEquals(1, out[0].length);
}

@Test(expected = IllegalArgumentException.class)
public void createNumberArray2DNullInputThrows() {
DataUtilities.createNumberArray2D(null);
}

@Test(expected = IllegalArgumentException.class)
public void createNumberArray2DNullRowThrows() {
DataUtilities.createNumberArray2D(new double[][]{null});
}

@Test 
public void createNumberArray2DEmptyMatrix() {
Number[][] out = DataUtilities.createNumberArray2D(new double[][]{});
assertNotNull(out);
assertEquals(0, out.length);
}

// ========== getCumulativePercentages (5) ==========

@Test 
public void getCumulativePercentagesTypical() {
	Mockery ctx = new Mockery();
	KeyedValues kv = ctx.mock(KeyedValues.class);
	ctx.checking(new Expectations() {{
	allowing(kv).getItemCount(); will(returnValue(3));
	allowing(kv).getKey(0); will(returnValue("A"));
	allowing(kv).getKey(1); will(returnValue("B"));
	allowing(kv).getKey(2); will(returnValue("C"));
	allowing(kv).getValue(0); will(returnValue(2));
	allowing(kv).getValue(1); will(returnValue(3));
	allowing(kv).getValue(2); will(returnValue(5));
	}});
	KeyedValues out = DataUtilities.getCumulativePercentages(kv);
	assertEquals(0.2, out.getValue(0).doubleValue(), 1e-9);
	assertEquals(0.5, out.getValue(1).doubleValue(), 1e-9);
	assertEquals(1.0, out.getValue(2).doubleValue(), 1e-9);
	ctx.assertIsSatisfied();
}

@Test 
public void getCumulativePercentagesSingleItemIsOne() {
	Mockery ctx = new Mockery();
	KeyedValues kv = ctx.mock(KeyedValues.class);
	ctx.checking(new Expectations() {{
	allowing(kv).getItemCount(); will(returnValue(1));
	allowing(kv).getKey(0); will(returnValue("X"));
	allowing(kv).getValue(0); will(returnValue(5));
	}});
	KeyedValues out = DataUtilities.getCumulativePercentages(kv);
	assertEquals(1.0, out.getValue(0).doubleValue(), 1e-9);
	ctx.assertIsSatisfied();
}

@Test 
public void getCumulativePercentagesIncludesZeros() {
	Mockery ctx = new Mockery();
	KeyedValues kv = ctx.mock(KeyedValues.class);
	ctx.checking(new Expectations() {{
	allowing(kv).getItemCount(); will(returnValue(3));
	allowing(kv).getKey(0); will(returnValue("A"));
	allowing(kv).getKey(1); will(returnValue("B"));
	allowing(kv).getKey(2); will(returnValue("C"));
	allowing(kv).getValue(0); will(returnValue(0));
	allowing(kv).getValue(1); will(returnValue(5));
	allowing(kv).getValue(2); will(returnValue(5));
	}});
	KeyedValues out = DataUtilities.getCumulativePercentages(kv);
	assertEquals(0.0, out.getValue(0).doubleValue(), 1e-9);
	assertEquals(0.5, out.getValue(1).doubleValue(), 1e-9);
	assertEquals(1.0, out.getValue(2).doubleValue(), 1e-9);
	ctx.assertIsSatisfied();
}

@Test(expected = IllegalArgumentException.class)
public void getCumulativePercentagesNullInputThrows() {
	DataUtilities.getCumulativePercentages(null);
}

@Test 
public void getCumulativePercentagesHandlesSmallTotals() {
	Mockery ctx = new Mockery();
	KeyedValues kv = ctx.mock(KeyedValues.class);
	ctx.checking(new Expectations() {{
	allowing(kv).getItemCount(); will(returnValue(2));
	allowing(kv).getKey(0); will(returnValue("A"));
	allowing(kv).getKey(1); will(returnValue("B"));
	allowing(kv).getValue(0); will(returnValue(0.2));
	allowing(kv).getValue(1); will(returnValue(0.8));
	}});
	KeyedValues out = DataUtilities.getCumulativePercentages(kv);
	assertEquals(0.2, out.getValue(0).doubleValue(), 1e-9);
	assertEquals(1.0, out.getValue(1).doubleValue(), 1e-9);
	ctx.assertIsSatisfied();
}
}