package com.huntercodexs.codexstester.util;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;

import static com.huntercodexs.codexstester.util.CodexsAssertion.resulted;
import static com.huntercodexs.codexstester.util.CodexsHelper.codexsHelperLogTermTests;

public abstract class CodexsArrayListComparator extends CodexsHashMapComparator {

	protected void codexsTesterCompareArrayListFormat(
			Object[] arrayListValues,
			Object[] arrayListTyped,
			ArrayList<Object> arrayListCompare,
			boolean strictMode
	) {

		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement element = stackTrace[2];

		codexsHelperLogTermTests("======== SUMMARY =======", "", true);
		codexsHelperLogTermTests("ARRAY-LIST-VALUES.......", Arrays.toString(arrayListValues), false);
		codexsHelperLogTermTests("ARRAY-LIST-TYPED........", Arrays.toString(arrayListTyped), false);
		codexsHelperLogTermTests("ARRAY-LIST-COMPARE......", arrayListCompare, false);
		codexsHelperLogTermTests("STRICT-MODE.............", strictMode, false);
		codexsHelperLogTermTests("CLASS-TYPE-NAME.........", String.valueOf(arrayListCompare.getClass()), false);
		codexsHelperLogTermTests("======== COMPARE =======", "", true);

		if (!String.valueOf(arrayListCompare.getClass()).contains("ArrayList")) {
			codexsHelperLogTermTests("ERROR ON ARRAY-LIST DATA COMPARE (WRONG-CLASS-TYPED)", "", true);
			resulted(false, element);
			Assert.fail();
		}

		if (arrayListValues.length != arrayListTyped.length || arrayListValues.length != arrayListCompare.size()) {
			codexsHelperLogTermTests("ERROR ON ARRAY-LIST DATA COMPARE (WRONG-LENGTH)", "", true);
			resulted(false, element);
			Assert.fail();
		}

		for (int i = 0; i < arrayListValues.length; i++) {

			if (arrayListCompare.get(i) == null && arrayListTyped[i] == null) {
				codexsHelperLogTermTests("OK -> CONTINUE", null, false);
				codexsHelperLogTermTests("TYPED....", arrayListTyped[i], false);
				codexsHelperLogTermTests("COMPARE..", arrayListCompare.get(i), false);
				continue;
			}

			if (arrayListCompare.get(i) == null && arrayListTyped[i] != null) {
				codexsHelperLogTermTests("> RESULT IS [FAIL] [CRITICAL] [WRONG-TYPED]", "INDEX-" + i, true);
				codexsHelperLogTermTests("EXPECTED....", arrayListTyped[i], false);
				codexsHelperLogTermTests("RECEIVED....", null, false);
				resulted(false, element);
				Assert.fail();
			}

			String expVal = String.valueOf(arrayListValues[i]).replaceFirst("(.*)@[0-9a-z]+", "$1");
			String fndVal = String.valueOf(arrayListCompare.get(i)).replaceFirst("(.*)@[0-9a-z]+", "$1");
			Object expType = arrayListTyped[i];
			Class<?> fndType = arrayListCompare.get(i).getClass();

			codexsHelperLogTermTests("===> INDEX <=== ", i, true);
			codexsHelperLogTermTests("EXPECTED TYPE...", expType, false);
			codexsHelperLogTermTests("FOUND TYPE......", fndType, false);
			codexsHelperLogTermTests("EXPECTED VALUE..", expVal, false);
			codexsHelperLogTermTests("FOUND VALUE.....", fndVal, false);

			if (expVal.equals(fndVal) && expType != fndType && (isInterface(expType, fndType))) {
				codexsHelperLogTermTests("> RESULT IS [WARNING] [DIFF-TYPED] [COMPATIBLE]", expVal, true);
				defaultMessage(expVal, fndVal, expType, fndType, null, null);
				strictMessage(true);
				continue;
			}

			if (strictMode) {

				if (expType != fndType) {
					if (!isInterface(expType, fndType)) {
						codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [WRONG-TYPED]", "INDEX-" + i, true);
						defaultMessage(expVal, fndVal, expType, fndType, null, null);
						resulted(false, element);
						Assert.fail();
					}
					codexsHelperLogTermTests("> RESULT IS [ABORTED] [STRICT] [WRONG-TYPED]", "INDEX-" + i, true);
					defaultMessage(expVal, fndVal, expType, fndType, null, null);
					strictMessage(false);
					resulted(false, element);
					Assert.fail();
				}

				if (!expVal.equals(fndVal)) {
					codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [WRONG-VALUE]", "INDEX-" + i, true);
					defaultMessage(expVal, fndVal, expType, fndType, null, null);
					strictMessage(false);
					resulted(false, element);
					Assert.fail();
				}

			} else {

				if (expType != fndType) {
					if (isInterface(expType, fndType)) {
						codexsHelperLogTermTests("> RESULT IS [WARNING] [NO-STRICT] [DIFF-TYPED] [COMPATIBLE]", "INDEX-" + i, true);
						defaultMessage(expVal, fndVal, expType, fndType, null, null);
						continue;
					} else {
						codexsHelperLogTermTests("> RESULT IS [FAIL] [NO-STRICT] [WRONG-TYPED]", "INDEX-" + i, true);
						defaultMessage(expVal, fndVal, expType, fndType, null, null);
						resulted(false, element);
						Assert.fail();
					}
				}

				if (!expVal.equals(fndVal)) {
					codexsHelperLogTermTests("> RESULT IS [WARNING] [NO-STRICT] [WRONG-VALUE]", "INDEX-" + i, true);
					defaultMessage(expVal, fndVal, expType, fndType, null, null);
					strictMessage(true);
					continue;
				}

			}
			codexsHelperLogTermTests("> RESULT IS [OK]", "INDEX-" + i, false);
		}
		resulted(true, element);
		Assert.assertTrue(true);

	}

	protected void codexsTesterCompareArrayListFormat(
			Object[][] expectedArrayListDataTree,
			ArrayList<Object> arrayListCompare,
			boolean strictMode
	) {

		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement element = stackTrace[2];

		codexsHelperLogTermTests("======== SUMMARY =======", "", true);
		codexsHelperLogTermTests("ARRAY-LIST-KEYS.........", extractFields(expectedArrayListDataTree, 0), false);
		codexsHelperLogTermTests("ARRAY-LIST-VALUES.......", extractFields(expectedArrayListDataTree, 1), false);
		codexsHelperLogTermTests("ARRAY-LIST-TYPED........", extractFields(expectedArrayListDataTree, 2), false);
		codexsHelperLogTermTests("ARRAY-LIST-COMPARE......", arrayListCompare, false);
		codexsHelperLogTermTests("STRICT-MODE.............", strictMode, false);
		codexsHelperLogTermTests("CLASS-TYPE-NAME.........", String.valueOf(arrayListCompare.getClass()), false);
		codexsHelperLogTermTests("======== COMPARE =======", "", true);

		if (!String.valueOf(arrayListCompare.getClass()).contains("ArrayList")) {
			codexsHelperLogTermTests("ERROR ON ARRAY-LIST DATA COMPARE (WRONG-CLASS-TYPED)", "", true);
			resulted(false, element);
			Assert.fail();
		}

		int arraySize = 0;

		for (int i = 0; i < expectedArrayListDataTree.length; i++) {

			if (i == 0) {
				arraySize = expectedArrayListDataTree[i].length;
			}

			if (i > 0) {
				if (arraySize != expectedArrayListDataTree[i].length) {
					codexsHelperLogTermTests("ERROR ON ARRAY-LIST DATA COMPARE (WRONG-LENGTH)", expectedArrayListDataTree[i], true);
					resulted(false, element);
					Assert.fail();
				}
			}

			if (arrayListCompare.get((Integer) expectedArrayListDataTree[i][0]) == null && expectedArrayListDataTree[i][2] == null) {
				codexsHelperLogTermTests("OK -> CONTINUE", null, false);
				codexsHelperLogTermTests("TYPED....", null, false);
				codexsHelperLogTermTests("COMPARE..", null, false);
				continue;
			}

			if (arrayListCompare.get((Integer) expectedArrayListDataTree[i][0]) == null && expectedArrayListDataTree[i][2] != null) {
				codexsHelperLogTermTests("> RESULT IS [FAIL] [CRITICAL] [WRONG-TYPED]", "INDEX-" + i, true);
				codexsHelperLogTermTests("EXPECTED....", expectedArrayListDataTree[i][2], false);
				codexsHelperLogTermTests("RECEIVED....", null, false);
				resulted(false, element);
				Assert.fail();
			}

			String expVal = String.valueOf(expectedArrayListDataTree[i][1]);
			String fndVal = String.valueOf(arrayListCompare.get((Integer) expectedArrayListDataTree[i][0]));
			Object expType = expectedArrayListDataTree[i][2];
			Class<?> fndType = arrayListCompare.get(i).getClass();

			codexsHelperLogTermTests("===> INDEX <=== ", i, true);
			codexsHelperLogTermTests("EXPECTED TYPE...", expType, false);
			codexsHelperLogTermTests("FOUND TYPE......", fndType, false);
			codexsHelperLogTermTests("EXPECTED VALUE..", expVal, false);
			codexsHelperLogTermTests("FOUND VALUE.....", fndVal, false);

			if (expVal.equals(fndVal) && expType != fndType && (isInterface(expType, fndType))) {
				codexsHelperLogTermTests("> RESULT IS [WARNING] [DIFF-TYPED] [COMPATIBLE]", expVal, true);
				defaultMessage(expVal, fndVal, expType, fndType, null, null);
				strictMessage(true);
				continue;
			}

			if (strictMode) {

				if (expType != fndType) {
					if (!isInterface(expType, fndType)) {
						codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [WRONG-TYPED]", "INDEX-" + i, true);
						defaultMessage(expVal, fndVal, expType, fndType, null, null);
						resulted(false, element);
						Assert.fail();
					}
					codexsHelperLogTermTests("> RESULT IS [ABORTED] [STRICT] [WRONG-TYPED]", "INDEX-" + i, true);
					defaultMessage(expVal, fndVal, expType, fndType, null, null);
					strictMessage(false);
					resulted(false, element);
					Assert.fail();
				}

				if (!expVal.equals(fndVal)) {
					codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [WRONG-VALUE]", "INDEX-" + i, true);
					defaultMessage(expVal, fndVal, expType, fndType, null, null);
					strictMessage(false);
					resulted(false, element);
					Assert.fail();
				}

			} else {

				if (expType != fndType) {
					if (isInterface(expType, fndType)) {
						codexsHelperLogTermTests("> RESULT IS [WARNING] [NO-STRICT] [DIFF-TYPED] [COMPATIBLE]", "INDEX-" + i, true);
						defaultMessage(expVal, fndVal, expType, fndType, null, null);
						continue;
					} else {
						codexsHelperLogTermTests("> RESULT IS [FAIL] [NO-STRICT] [WRONG-TYPED]", "INDEX-" + i, true);
						defaultMessage(expVal, fndVal, expType, fndType, null, null);
						resulted(false, element);
						Assert.fail();
					}
				}

				if (!expVal.equals(fndVal)) {
					codexsHelperLogTermTests("> RESULT IS [WARNING] [NO-STRICT] [WRONG-VALUE]", "INDEX-" + i, true);
					defaultMessage(expVal, fndVal, expType, fndType, null, null);
					strictMessage(true);
					continue;
				}

			}
			codexsHelperLogTermTests("> RESULT IS [OK]", "INDEX-" + i, false);
		}
		resulted(true, element);
		Assert.assertTrue(true);

	}

}
