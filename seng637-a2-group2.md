**SENG 637 - Software Testing, Reliability, and Quality**

**Lab. Report \#2 – Requirements-Based Test Generation**

| Group \#:      |   1  |
| -------------- | --- |
| Student Names: | Eid    |
|    Noshin            |     |
|          Ashwin      |   30300738  |
|      Jasneet          |     |
|Salehin                | 30270206    |


# 1 Introduction

We designed and ran black‑box JUnit tests (ECT + BVA) for five Range and five DataUtilities methods in JFreeChart v1.0 using Java 8, JUnit 4.11, and JMock to validate behavior against the Javadoc specs.

The System Under Test (SUT) is the JFreeChart v1.0 implementation provided by the instructor. The test oracle is the official Javadoc specification, which defines the expected functional behavior for each method. Observed runtime behavior was not used to determine correctness — any deviation from Javadoc was recorded as a defect.

Purpose: We applied black‑box unit testing (ECT + BVA) to JFreeChart’s data layer as Assignment 2 for SENG 637.
Scope: 5 methods in org.jfree.data.DataUtilities (all required) and 5 chosen methods in org.jfree.data.Range.
Tools/Frameworks: Java 8, JUnit 4.11, jMock for Values2D/KeyedValues, supplied JFreeChart v1.0 artifacts.
Goal: Design clear, single‑case @Test methods mapped to our paper test cases; run them on the provided SUT versions, record passes/failures, and document observed defects against the Javadoc specs.

# 2 Detailed description of unit test strategy

## Partition Identification Process

For each method, input domains were analyzed to determine valid, invalid, and boundary input classes. For numeric parameters, partitions were created for negative values, zero, positive values, and extreme values. For collections and arrays, partitions were defined based on size (empty, single element, multiple elements) and content (null elements, zero values, mixed signs). For object parameters (Values2D, KeyedValues), partitions included null objects, empty structures, and populated structures. Boundary values were selected at the transition points between partitions such as 0, 1, empty size, and equal bounds.

We used a black‑box approach with Equivalence Class Testing (ECT) and Boundary Value Analysis (BVA) as described in the Javadoc specs. For each required method (all 5 in DataUtilities and 5 selected in Range), we first defined input partitions (ECs) and boundary points, then mapped one @Test method to each test case so every partition/boundary is exercised separately. The Javadoc served as the oracle: expected outputs came from the documented behavior, not from observed runs. We used Java 8, JUnit 4.11, and jMock to isolate Values2D/KeyedValues dependencies; mocks let us precisely control matrix and keyed inputs (including zeros, negatives, empty tables, large values, and null guards). Each test contains a single scenario and assertion set, keeping impact analysis simple. Any divergences (failing tests) are recorded as defects against the provided JFreeChart v1.0 build, not “fixed” in the tests.


# 3 Test cases developed

We organized Range tests by method: intersects (5 cases covering disjoint below/above, boundary touch, fully inside, range-contained) and constrain (5 cases covering below, on bounds, inside, above). Teammate-provided suites cover getLowerBound, getUpperBound, and getLength across negative/zero/positive/equal/extreme partitions per the strategy.

## Class: DataUtilitiesTest2

### calculateColumnTotal(Values2D, int)

calculateColumnTotalForTwoValues — EC1 nominal sum across 2 rows in a column  
calculateColumnTotalForSingleRow — EC2 minimal table (1 row), BVA on rowCount  
calculateColumnTotalForemptyTableIsZero — EC2 empty table boundary (rowCount = 0)  
calculateColumnTotalForNegativeValues — EC3 negative values in the column  
calculateColumnTotalForLargeNumbers — EC3 large magnitude values (upper-scale BVA)

### calculateRowTotal(Values2D, int)

calculateRowTotalForTwoValues — EC1 nominal sum across 2 columns in a row  
calculateRowTotalForSingleColumnSingleRow — EC2 minimal table (1 column), BVA on columnCount  
calculateRowTotalForEmptyTableIsZero — EC2 empty table boundary (columnCount = 0)  
calculateRowTotalForNegativeValues — EC3 negative values in the row  
calculateRowTotalForLargeNumbers — EC3 large magnitude values (upper-scale BVA)

### createNumberArray(double[])

createNumberArrayCopiesValues — EC1 nominal mixed-sign array is converted to Number[]  
createNumberArraySingleElement — EC2 minimal size (length = 1), BVA on array length  
createNumberArrayNullInputThrowsException — EC3 null guard (IllegalArgumentException)  
createNumberArrayHandlesZeros — EC2 zero boundary (+0.0 and -0.0)  
createNumberArrayLargeValues — EC3 large magnitude values (upper-scale BVA)

### createNumberArray2D(double[][])

createNumberArray2DCopyValues — EC1 nominal 2D matrix conversion (mixed signs)  
createNumberArray2DSingleCell — EC2 minimal size (1×1), BVA on matrix dimensions  
createNumberArray2DNullInputThrows — EC3 null guard (IllegalArgumentException)  
createNumberArray2DNullRowThrows — EC3 null row guard (IllegalArgumentException)  
createNumberArray2DEmptyMatrix — EC2 empty boundary (0 rows)

### getCumulativePercentages(KeyedValues)

getCumulativePercentagesTypical — EC1 nominal distribution (values sum to 10)  
getCumulativePercentagesSingleItemIsOne — EC2 minimal size (1 item), BVA on itemCount  
getCumulativePercentagesIncludesZeros — EC2 zero boundary (leading zero values)  
getCumulativePercentagesNullInputThrows — EC3 null guard (IllegalArgumentException)  
getCumulativePercentagesHandlesSmallTotals — EC3 small totals / precision boundary (values sum to 1.0)

## Class: RangeTest

### intersects(double, double)

intersectsWhenTouchingAtUpperBound — EC2 boundary overlap; BVA endpoint touch
intersectsReturnsFalseForDisjointAbove — EC3 disjoint above; BVA gap above upper
intersectsReturnsFalseForDisjointBelow — EC1 disjoint below; BVA gap below lower
intersectsWhenFullyInsideRange — EC2 fully inside range
intersectsWhenRangeFullyInsideInterval — EC2 range inside interval; span coverage
### constrain(double)

constrainReturnsLowerWhenBelow — EC1 below lower; BVA far below
constrainReturnsLowerAtExactLowerBound — EC2 on lower boundary
constrainReturnsValueWhenInside — EC2 inside nominal
constrainReturnsUpperAtExactUpperBound — EC2 on upper boundary
constrainReturnsUpperWhenAbove — EC3 above upper; BVA far above


### Range.getLowerBound()

test_getLowerBound_NegativeNominal — EC1 (negative), passed
test_getLowerBound_PositiveNominal — EC3 (positive), passed
test_getLowerBound_ZeroBoundary — EC2 (zero boundary), passed
test_getLowerBound_JustBelowZero — EC1 (just below zero), passed
test_getLowerBound_JustAboveZero — EC3 (just above zero), passed
test_getLowerBound_ExtremeNegative — EC1 (extreme negative), passed
test_getLowerBound_EqualBounds — EC3 (lower == upper), passed

### Range.getUpperBound()

test_getUpperBound_NegativeNominal — EC1 (negative), passed
test_getUpperBound_PositiveNominal — EC3 (positive), passed
test_getUpperBound_ZeroBoundary — EC2 (zero boundary), passed
test_getUpperBound_JustBelowZero — EC1 (just below zero), passed
test_getUpperBound_JustAboveZero — EC3 (just above zero), passed
test_getUpperBound_ExtremePositive — EC3 (extreme positive), passed
test_getUpperBound_EqualBounds — EC3 (lower == upper), passed

### Range.getLength()

test_getLength_BothPositive — EC1/EC3 nominal positive bounds, passed
test_getLength_BothNegative — EC1/EC4 nominal negative bounds, passed
test_getLength_MixedSigns — EC1/EC5 mixed bounds, passed
test_getLength_ZeroLength — EC2 (lower == upper) boundary, passed
test_getLength_LowerBoundZero — EC1 zero-to-positive boundary, passed
test_getLength_UpperBoundZero — EC1 negative-to-zero boundary, passed
test_getLength_ExtremeValues — EC1/EC5 extreme span, passed


# 4 How the team work/effort was divided and managed

We split into pairs: Ashwin and Noshin owned the DataUtilities test cases; Salehin and Jasneet owned the Range test cases. Each pair designed and coded their assigned methods, then we cross‑reviewed and fixed issues across both suites. Finally, all four of us worked together to compile and edit the report.

# 5 Difficulties encountered, challenges overcome, and lessons learned

Mocking quirks: jMock needed every call stubbed; missing expectations caused failures. We learned to use allowing for incidental calls and to assert counts only where needed.
Nulls in array-creation: createNumberArray* returned null elements in the provided JFreeChart build, causing NPEs. We guarded assertions and recorded these as SUT defects rather than changing expectations.
Boundary interpretation: intersects endpoint-touching behavior diverged from our expectation; we kept the failing tests and documented the mismatch.
Coordination: merging tests from two pairs required clear naming and one-case-per-@Test discipline; cross-review caught overlapping scenarios and missing partitions.
Takeaway: design from specs, not observed behavior; keep each test single-scenario for easier debugging and traceability.

# 6 Comments/feedback on the lab itself

Clear scope and good practice with JUnit + mocks; pairing ECT/BVA with Javadoc specs was useful.
Setup friction: jMock/JAR wiring in Eclipse took time; a Gradle/Maven starter could reduce config issues.
Some provided SUT methods (array creation, intersects) differ from spec, so explicit note about intentional defects would help expectations.
The single-case-per-@Test guidance was helpful for traceability.
Overall a solid intro to black-box unit testing; more examples of expected failing cases would further reduce confusion.
