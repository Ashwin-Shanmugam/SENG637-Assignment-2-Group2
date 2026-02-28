package org.jfree.data.test;

import static org.junit.Assert.*;
import org.jfree.data.Range;
import org.junit.*;

public class RangeTest {

    private Range range;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception { }

    @Before
    public void setUp() {
        // Default range; many tests override it as needed.
        range = new Range(-2.0, 5.0);
    }

    // -----------------------------------------------------------------
    // getLowerBound() Tests
    // -----------------------------------------------------------------

    @Test
    public void test_getLowerBound_NegativeNominal() {
        range = new Range(-10.0, 10.0);
        assertEquals(-10.0, range.getLowerBound(), 0.0000001);
    }

    @Test
    public void test_getLowerBound_JustBelowZero() {
        range = new Range(-0.1, 10.0);
        assertEquals(-0.1, range.getLowerBound(), 0.0000001);
    }

    @Test
    public void test_getLowerBound_ExtremeNegative() {
        range = new Range(-Double.MAX_VALUE, 0.0);
        assertEquals(-Double.MAX_VALUE, range.getLowerBound(), 0.000000001);
    }

    @Test
    public void test_getLowerBound_ZeroBoundary() {
        range = new Range(0.0, 10.0);
        assertEquals(0.0, range.getLowerBound(), 0.0000001);
    }

    @Test
    public void test_getLowerBound_PositiveNominal() {
        range = new Range(10.0, 20.0);
        assertEquals(10.0, range.getLowerBound(), 0.000000001);
    }

    @Test
    public void test_getLowerBound_JustAboveZero() {
        range = new Range(0.1, 10.0);
        assertEquals(0.1, range.getLowerBound(), 0.000000001);
    }

    @Test
    public void test_getLowerBound_EqualBounds() {
        range = new Range(5.0, 5.0);
        assertEquals(5.0, range.getLowerBound(), 0.000000001);
    }

    // -----------------------------------------------------------------
    // getUpperBound() Tests
    // -----------------------------------------------------------------

    @Test
    public void test_getUpperBound_NegativeNominal() {
        range = new Range(-20.0, -10.0);
        assertEquals(-10.0, range.getUpperBound(), 0.000000001);
    }

    @Test
    public void test_getUpperBound_JustBelowZero() {
        range = new Range(-10.0, -0.1);
        assertEquals(-0.1, range.getUpperBound(), 0.000000001);
    }

    @Test
    public void test_getUpperBound_ZeroBoundary() {
        range = new Range(-10.0, 0.0);
        assertEquals(0.0, range.getUpperBound(), 0.000000001);
    }

    @Test
    public void test_getUpperBound_PositiveNominal() {
        range = new Range(10.0, 20.0);
        assertEquals(20.0, range.getUpperBound(), 0.000000001);
    }

    @Test
    public void test_getUpperBound_JustAboveZero() {
        range = new Range(-10.0, 0.1);
        assertEquals(0.1, range.getUpperBound(), 0.000000001);
    }

    @Test
    public void test_getUpperBound_ExtremePositive() {
        range = new Range(0.0, Double.MAX_VALUE);
        assertEquals(Double.MAX_VALUE, range.getUpperBound(), 0.000000001);
    }

    @Test
    public void test_getUpperBound_EqualBounds() {
        range = new Range(5.0, 5.0);
        assertEquals(5.0, range.getUpperBound(), 0.000000001);
    }

    // ---------------------------------------------------------
    // getLength() Tests
    // ---------------------------------------------------------

    @Test
    public void test_getLength_BothPositive() {
        range = new Range(2.0, 5.0);
        assertEquals(3.0, range.getLength(), 0.000000001);
    }

    @Test
    public void test_getLength_BothNegative() {
        range = new Range(-10.0, -2.0);
        assertEquals(8.0, range.getLength(), 0.000000001);
    }

    @Test
    public void test_getLength_MixedSigns() {
        range = new Range(-5.0, 5.0);
        assertEquals(10.0, range.getLength(), 0.000000001);
    }

    @Test
    public void test_getLength_ZeroLength() {
        range = new Range(5.0, 5.0);
        assertEquals(0.0, range.getLength(), 0.000000001);
    }

    @Test
    public void test_getLength_LowerBoundZero() {
        range = new Range(0.0, 10.0);
        assertEquals(10.0, range.getLength(), 0.000000001);
    }

    @Test
    public void test_getLength_UpperBoundZero() {
        range = new Range(-10.0, 0.0);
        assertEquals(10.0, range.getLength(), 0.000000001);
    }

    @Test
    public void test_getLength_ExtremeValues() {
        range = new Range(-1_000_000.0, 1_000_000.0);
        assertEquals(2_000_000.0, range.getLength(), 0.000000001);
    }

    // ---------------------------------------------------------
    // intersects(double, double) Tests
    // ---------------------------------------------------------

    @Test
    public void intersectsWhenTouchingAtUpperBound() {
        assertTrue(range.intersects(5.0, 6.0)); // endpoint touching should intersect
    }

    @Test
    public void intersectsReturnsFalseForDisjointAbove() {
        assertFalse(range.intersects(6.0, 7.0));
    }

    @Test
    public void intersectsReturnsFalseForDisjointBelow() {
        assertFalse(range.intersects(-5.0, -3.0));
    }

    @Test
    public void intersectsWhenFullyInsideRange() {
        assertTrue(range.intersects(-1.0, 1.0));
    }

    @Test
    public void intersectsWhenRangeFullyInsideInterval() {
        assertTrue(range.intersects(-3.0, 6.0));
    }

    // ---------------------------------------------------------
    // constrain(double) Tests
    // ---------------------------------------------------------

    @Test
    public void constrainReturnsUpperWhenAbove() {
        assertEquals(5.0, range.constrain(10.0), 1e-9);
    }

    @Test
    public void constrainReturnsLowerWhenBelow() {
        assertEquals(-2.0, range.constrain(-10.0), 1e-9);
    }

    @Test
    public void constrainReturnsValueWhenInside() {
        assertEquals(1.5, range.constrain(1.5), 1e-9);
    }

    @Test
    public void constrainReturnsLowerAtExactLowerBound() {
        assertEquals(-2.0, range.constrain(-2.0), 1e-9);
    }

    @Test
    public void constrainReturnsUpperAtExactUpperBound() {
        assertEquals(5.0, range.constrain(5.0), 1e-9);
    }

    @After
    public void tearDown() throws Exception { }

    @AfterClass
    public static void tearDownAfterClass() throws Exception { }
}
