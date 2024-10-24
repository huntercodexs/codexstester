package com.huntercodexs.codexstester.util;

import org.junit.Assert;

import java.util.Arrays;
import java.util.LinkedList;

import static com.huntercodexs.codexstester.util.CodexsAssertion.resulted;
import static com.huntercodexs.codexstester.util.CodexsHelper.codexsHelperLogTermTests;

public abstract class CodexsLinkedListComparator extends CodexsLinkedHashMapComparator {

	/**
	 * <h1 style="color: #FFFF00; font-size: 11px">codexsTesterCompareLinkedListFormat</h1>@param linkedHashMapKeys (Object[])
	 * @param linkedListValues (Object[])
	 * @param linkedListTyped  (Object[])
	 * @param linkedListCompare (Object: LinkedList)
	 * @param strictMode (boolean)
	 * @see <a href="https://github.com/huntercodexs/codexstester">Codexs Tester (GitHub)</a>
	 * @author huntercodexs (powered by jereelton-devel)
	 * */
	protected void codexsTesterCompareLinkedListFormat(
			Object[] linkedListValues,
			Object[] linkedListTyped,
			LinkedList<Object> linkedListCompare,
			boolean strictMode
	) {

		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement element = stackTrace[2];

		codexsHelperLogTermTests("======== SUMMARY =======", "", true);
		codexsHelperLogTermTests("LINKED-LIST-VALUES......", Arrays.toString(linkedListValues), false);
		codexsHelperLogTermTests("LINKED-LIST-TYPED.......", Arrays.toString(linkedListTyped), false);
		codexsHelperLogTermTests("LINKED-LIST-COMPARE.....", linkedListCompare, false);
		codexsHelperLogTermTests("STRICT-MODE.............", strictMode, false);
		codexsHelperLogTermTests("CLASS-TYPE-NAME.........", String.valueOf(linkedListCompare.getClass()), false);
		codexsHelperLogTermTests("======== COMPARE =======", "", true);

		if (!String.valueOf(linkedListCompare.getClass()).contains("LinkedList")) {
			codexsHelperLogTermTests("ERROR ON LINKED-LIST DATA COMPARE (WRONG-CLASS-TYPED)", "", true);
			resulted(false, element);
			Assert.fail();
		}

		if (linkedListValues.length != linkedListTyped.length || linkedListValues.length != linkedListCompare.size()) {
			codexsHelperLogTermTests("ERROR ON LINKED-LIST DATA COMPARE (WRONG-LENGTH)", "", true);
			resulted(false, element);
			Assert.fail();
		}

		for (int i = 0; i < linkedListValues.length; i++) {

			if (linkedListCompare.get(i) == null && linkedListTyped[i] == null) {
				codexsHelperLogTermTests("OK -> CONTINUE", null, false);
				codexsHelperLogTermTests("TYPED....", linkedListTyped[i], false);
				codexsHelperLogTermTests("COMPARE..", linkedListCompare.get(i), false);
				continue;
			}

			if (linkedListCompare.get(i) == null && linkedListTyped[i] != null) {
				codexsHelperLogTermTests("> RESULT IS [FAIL] [CRITICAL] [WRONG-TYPED]", "INDEX-" + i, true);
				codexsHelperLogTermTests("EXPECTED....", linkedListTyped[i], false);
				codexsHelperLogTermTests("RECEIVED....", null, false);
				resulted(false, element);
				Assert.fail();
			}

			String expVal = String.valueOf(linkedListValues[i]).replaceFirst("(.*)@[0-9a-z]+", "$1");
			String fndVal = String.valueOf(linkedListCompare.get(i)).replaceFirst("(.*)@[0-9a-z]+", "$1");
			Object expType = linkedListTyped[i];
			Class<?> fndType = linkedListCompare.get(i).getClass();

			codexsHelperLogTermTests("===> INDEX <=== ", i, true);
			codexsHelperLogTermTests("EXPECTED NAME...", "", true);
			codexsHelperLogTermTests("FOUND NAME......", "", false);
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

	/**
	 * <h1 style="color: #FFFF00; font-size: 11px">codexsTesterCompareLinkedListFormat</h1>
	 *
	 * @param expectedLinkedListDataTree (Object[][])
	 * @param linkedListCompare (Object: LinkedList)
	 * @param strictMode (boolean)
	 * @see <a href="https://github.com/huntercodexs/codexstester">Codexs Tester (GitHub)</a>
	 * @author huntercodexs (powered by jereelton-devel)
	 * */
	protected void codexsTesterCompareLinkedListFormat(
			Object[][] expectedLinkedListDataTree,
			LinkedList<Object> linkedListCompare,
			boolean strictMode
	) {

		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement element = stackTrace[2];

		codexsHelperLogTermTests("======== SUMMARY =======", "", true);
		codexsHelperLogTermTests("LINKED-LIST-KEYS........", extractFields(expectedLinkedListDataTree, 0), false);
		codexsHelperLogTermTests("LINKED-LIST-VALUES......", extractFields(expectedLinkedListDataTree, 1), false);
		codexsHelperLogTermTests("LINKED-LIST-TYPED.......", extractFields(expectedLinkedListDataTree, 2), false);
		codexsHelperLogTermTests("LINKED-LIST-COMPARE.....", linkedListCompare, false);
		codexsHelperLogTermTests("STRICT-MODE.............", strictMode, false);
		codexsHelperLogTermTests("CLASS-TYPE-NAME.........", String.valueOf(linkedListCompare.getClass()), false);
		codexsHelperLogTermTests("======== COMPARE =======", "", true);

		if (!String.valueOf(linkedListCompare.getClass()).contains("LinkedList")) {
			codexsHelperLogTermTests("ERROR ON LINKED-LIST DATA COMPARE (WRONG-CLASS-TYPED)", "", true);
			resulted(false, element);
			Assert.fail();
		}

		int arraySize = 0;

		for (int i = 0; i < expectedLinkedListDataTree.length; i++) {

			if (i == 0) {
				arraySize = expectedLinkedListDataTree[i].length;
			}

			if (i > 0) {
				if (arraySize != expectedLinkedListDataTree[i].length) {
					codexsHelperLogTermTests("ERROR ON LINKED-LIST DATA COMPARE (WRONG-LENGTH)", expectedLinkedListDataTree[i], true);
					resulted(false, element);
					Assert.fail();
				}
			}

			if (linkedListCompare.get((Integer) expectedLinkedListDataTree[i][0]) == null && expectedLinkedListDataTree[i][2] == null) {
				codexsHelperLogTermTests("OK -> CONTINUE", null, false);
				codexsHelperLogTermTests("TYPED....", null, false);
				codexsHelperLogTermTests("COMPARE..", null, false);
				continue;
			}

			if (linkedListCompare.get((Integer) expectedLinkedListDataTree[i][0]) == null && expectedLinkedListDataTree[i][2] != null) {
				codexsHelperLogTermTests("> RESULT IS [FAIL] [CRITICAL] [WRONG-TYPED]", "INDEX-" + i, true);
				codexsHelperLogTermTests("EXPECTED....", expectedLinkedListDataTree[i][2], false);
				codexsHelperLogTermTests("RECEIVED....", null, false);
				resulted(false, element);
				Assert.fail();
			}

			String expVal = String.valueOf(expectedLinkedListDataTree[i][1])
					.replaceFirst("(.*)@[0-9a-z]+", "$1");
			String fndVal = String.valueOf(linkedListCompare.get((Integer) expectedLinkedListDataTree[i][0]))
					.replaceFirst("(.*)@[0-9a-z]+", "$1");
			Object expType = expectedLinkedListDataTree[i][2];
			Class<?> fndType = linkedListCompare.get(i).getClass();

			codexsHelperLogTermTests("===> INDEX <=== ", i, true);
			codexsHelperLogTermTests("EXPECTED NAME...", "", true);
			codexsHelperLogTermTests("FOUND NAME......", "", false);
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
