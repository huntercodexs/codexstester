package com.huntercodexs.codexstester.util;

import org.junit.Assert;

import java.util.Arrays;
import java.util.LinkedHashMap;

import static com.huntercodexs.codexstester.util.CodexsAssertion.resulted;
import static com.huntercodexs.codexstester.util.CodexsHelper.codexsHelperLogTermTests;

public abstract class CodexsLinkedHashMapComparator extends CodexsAdvanced {

	protected void codexsTesterCompareLinkedHashMapFormat(
			String[] linkedHashMapKeys,
			Object[] linkedHashMapValues,
			Object[] linkedHashMapTyped,
			LinkedHashMap<Object, Object> linkedHashMapCompare,
			boolean strictMode
	) {

		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement element = stackTrace[2];

		codexsHelperLogTermTests("======== SUMMARY =======", "", true);
		codexsHelperLogTermTests("LINKED-HASH-MAP-KEYS....", Arrays.toString(linkedHashMapKeys), false);
		codexsHelperLogTermTests("LINKED-HASH-MAP-VALUES..", Arrays.toString(linkedHashMapValues), false);
		codexsHelperLogTermTests("LINKED-HASH-MAP-TYPED...", Arrays.toString(linkedHashMapTyped), false);
		codexsHelperLogTermTests("LINKED-HASH-MAP-COMPARE.", linkedHashMapCompare, false);
		codexsHelperLogTermTests("STRICT-MODE.............", strictMode, false);
		codexsHelperLogTermTests("CLASS-TYPE-NAME.........", String.valueOf(linkedHashMapCompare.getClass()), false);
		codexsHelperLogTermTests("======== COMPARE =======", "", true);

		if (!String.valueOf(linkedHashMapCompare.getClass()).contains("LinkedHashMap")) {
			codexsHelperLogTermTests("ERROR ON LINKED-HASH-MAP DATA COMPARE (WRONG-CLASS-TYPED)", "", true);
			resulted(false, element);
			Assert.fail();
		}

		if (linkedHashMapKeys.length != linkedHashMapValues.length || linkedHashMapKeys.length != linkedHashMapTyped.length || linkedHashMapKeys.length != linkedHashMapCompare.size()) {
			codexsHelperLogTermTests("ERROR ON LINKED-HASH-MAP DATA COMPARE (WRONG-LENGTH)", "", true);
			resulted(false, element);
			Assert.fail();
		}

		for (int i = 0; i < linkedHashMapKeys.length; i++) {

			if (linkedHashMapCompare.get(linkedHashMapKeys[i]) == null && linkedHashMapTyped[i] == null) {
				codexsHelperLogTermTests("OK -> CONTINUE", null, false);
				codexsHelperLogTermTests("TYPED....", linkedHashMapTyped[i], false);
				codexsHelperLogTermTests("COMPARE..", linkedHashMapCompare.get(linkedHashMapKeys[i]), false);
				continue;
			}

			if (!linkedHashMapCompare.containsKey(linkedHashMapKeys[i]) || linkedHashMapCompare.get(linkedHashMapKeys[i]) == null && linkedHashMapTyped[i] != null) {
				codexsHelperLogTermTests("> RESULT IS [FAIL] [CRITICAL] [MISSING-KEY]", linkedHashMapKeys[i], true);
				codexsHelperLogTermTests("EXPECTED....", linkedHashMapKeys[i], false);
				codexsHelperLogTermTests("RECEIVED....", null, false);
				resulted(false, element);
				Assert.fail();
			}

			String expName = linkedHashMapKeys[i];
			String fndName = linkedHashMapKeys[i];
			String expVal = String.valueOf(linkedHashMapValues[i])
					.replaceFirst("(.*)@[0-9a-z]+", "$1");
			String fndVal = String.valueOf(linkedHashMapCompare.get(linkedHashMapKeys[i]))
					.replaceFirst("(.*)@[0-9a-z]+", "$1");
			Object expType = linkedHashMapTyped[i];
			Class<?> fndType = linkedHashMapCompare.get(linkedHashMapKeys[i]).getClass();

			codexsHelperLogTermTests("===> INDEX <=== ", i, true);
			codexsHelperLogTermTests("EXPECTED NAME...", expName, false);
			codexsHelperLogTermTests("FOUND NAME......", fndName, false);
			codexsHelperLogTermTests("EXPECTED TYPE...", expType, false);
			codexsHelperLogTermTests("FOUND TYPE......", fndType, false);
			codexsHelperLogTermTests("EXPECTED VALUE..", expVal, false);
			codexsHelperLogTermTests("FOUND VALUE.....", fndVal, false);

			if (expVal.equals(fndVal) && expType != fndType && (isInterface(expType, fndType))) {
				codexsHelperLogTermTests("> RESULT IS [WARNING] [DIFF-TYPED] [COMPATIBLE]", expName, true);
				defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
				strictMessage(true);
				continue;
			}

			if (strictMode) {

				if (expType != fndType) {
					if (!isInterface(expType, fndType)) {
						codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [WRONG-TYPED]", expName, true);
						defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
						resulted(false, element);
						Assert.fail();
					}
					codexsHelperLogTermTests("> RESULT IS [ABORTED] [STRICT] [WRONG-TYPED]", expName, true);
					defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
					strictMessage(false);
					resulted(false, element);
					Assert.fail();
				}

				if (!expName.equals(fndName)) {
					codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [WRONG-NAME]", expName, true);
					defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
					strictMessage(false);
					resulted(false, element);
					Assert.fail();
				}

				if (!expVal.equals(fndVal)) {
					codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [WRONG-VALUE]", expName, true);
					defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
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

				if (!expName.equals(fndName)) {
					codexsHelperLogTermTests("> RESULT IS [WARNING] [NO-STRICT] [WRONG-NAME]", expName, true);
					defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
					strictMessage(true);
					continue;
				}

				if (!expVal.equals(fndVal)) {
					codexsHelperLogTermTests("> RESULT IS [WARNING] [NO-STRICT] [WRONG-VALUE]", expName, true);
					defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
					strictMessage(true);
					continue;
				}

			}
			codexsHelperLogTermTests("> RESULT IS [OK]", expName, false);
		}
		resulted(true, element);
		Assert.assertTrue(true);

	}

	protected void codexsTesterCompareLinkedHashMapFormat(
			Object[][] expectedLinkedHashMapDataTree,
			LinkedHashMap<Object, Object> linkedHashMapCompare,
			boolean strictMode
	) {

		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement element = stackTrace[2];

		codexsHelperLogTermTests("======== SUMMARY =======", "", true);
		codexsHelperLogTermTests("LINKED-HASH-MAP-KEYS....", extractFields(expectedLinkedHashMapDataTree, 0), false);
		codexsHelperLogTermTests("LINKED-HASH-MAP-VALUES..", extractFields(expectedLinkedHashMapDataTree, 1), false);
		codexsHelperLogTermTests("LINKED-HASH-MAP-TYPED...", extractFields(expectedLinkedHashMapDataTree, 2), false);
		codexsHelperLogTermTests("LINKED-HASH-MAP-COMPARE.", linkedHashMapCompare, false);
		codexsHelperLogTermTests("STRICT-MODE.............", strictMode, false);
		codexsHelperLogTermTests("CLASS-TYPE-NAME.........", String.valueOf(linkedHashMapCompare.getClass()), false);
		codexsHelperLogTermTests("======== COMPARE =======", "", true);

		if (!String.valueOf(linkedHashMapCompare.getClass()).contains("LinkedHashMap")) {
			codexsHelperLogTermTests("ERROR ON LINKED-HASH-MAP DATA COMPARE (WRONG-CLASS-TYPED)", "", true);
			resulted(false, element);
			Assert.fail();
		}

		int arraySize = 0;

		for (int i = 0; i < expectedLinkedHashMapDataTree.length; i++) {

			if (i == 0) {
				arraySize = expectedLinkedHashMapDataTree[i].length;
			}

			if (i > 0) {
				if (arraySize != expectedLinkedHashMapDataTree[i].length) {
					codexsHelperLogTermTests("ERROR ON LINKED-HASH-MAP DATA COMPARE (WRONG-LENGTH)", expectedLinkedHashMapDataTree[i], true);
					resulted(false, element);
					Assert.fail();
				}
			}

			if (linkedHashMapCompare.get(expectedLinkedHashMapDataTree[i][0]) == null && expectedLinkedHashMapDataTree[i][2] == null) {
				codexsHelperLogTermTests("OK -> CONTINUE", null, false);
				codexsHelperLogTermTests("TYPED....", null, false);
				codexsHelperLogTermTests("COMPARE..", null, false);
				continue;
			}

			if (
					!linkedHashMapCompare.containsKey(expectedLinkedHashMapDataTree[i][0]) ||
							linkedHashMapCompare.get(expectedLinkedHashMapDataTree[i][0]) == null &&
									expectedLinkedHashMapDataTree[i][2] != null
			) {
				codexsHelperLogTermTests("> RESULT IS [FAIL] [CRITICAL] [MISSING-KEY]", expectedLinkedHashMapDataTree[i][0], true);
				codexsHelperLogTermTests("EXPECTED....", expectedLinkedHashMapDataTree[i][0], false);
				codexsHelperLogTermTests("RECEIVED....", null, false);
				resulted(false, element);
				Assert.fail();
			}

			String expName = String.valueOf(expectedLinkedHashMapDataTree[i][0]);
			String fndName = String.valueOf(expectedLinkedHashMapDataTree[i][0]);
			String expVal = String.valueOf(expectedLinkedHashMapDataTree[i][1])
					.replaceFirst("(.*)@[0-9a-z]+", "$1");
			String fndVal = String.valueOf(linkedHashMapCompare.get(expectedLinkedHashMapDataTree[i][0]))
					.replaceFirst("(.*)@[0-9a-z]+", "$1");
			Object expType = expectedLinkedHashMapDataTree[i][2];
			Class<?> fndType = linkedHashMapCompare.get(expectedLinkedHashMapDataTree[i][0]).getClass();

			codexsHelperLogTermTests("===> INDEX <=== ", i, true);
			codexsHelperLogTermTests("EXPECTED NAME...", expName, false);
			codexsHelperLogTermTests("FOUND NAME......", fndName, false);
			codexsHelperLogTermTests("EXPECTED TYPE...", expType, false);
			codexsHelperLogTermTests("FOUND TYPE......", fndType, false);
			codexsHelperLogTermTests("EXPECTED VALUE..", expVal, false);
			codexsHelperLogTermTests("FOUND VALUE.....", fndVal, false);

			if (expVal.equals(fndVal) && expType != fndType && (isInterface(expType, fndType))) {
				codexsHelperLogTermTests("> RESULT IS [WARNING] [DIFF-TYPED] [COMPATIBLE]", expName, true);
				defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
				strictMessage(true);
				continue;
			}

			if (strictMode) {

				if (expType != fndType) {
					if (!isInterface(expType, fndType)) {
						codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [WRONG-TYPED]", expName, true);
						defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
						resulted(false, element);
						Assert.fail();
					}
					codexsHelperLogTermTests("> RESULT IS [ABORTED] [STRICT] [WRONG-TYPED]", expName, true);
					defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
					strictMessage(false);
					resulted(false, element);
					Assert.fail();
				}

				if (!expName.equals(fndName)) {
					codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [WRONG-NAME]", expName, true);
					defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
					strictMessage(false);
					resulted(false, element);
					Assert.fail();
				}

				if (!expVal.equals(fndVal)) {
					codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [WRONG-VALUE]", expName, true);
					defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
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

				if (!expName.equals(fndName)) {
					codexsHelperLogTermTests("> RESULT IS [WARNING] [NO-STRICT] [WRONG-NAME]", expName, true);
					defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
					strictMessage(true);
					continue;
				}

				if (!expVal.equals(fndVal)) {
					codexsHelperLogTermTests("> RESULT IS [WARNING] [NO-STRICT] [WRONG-VALUE]", expName, true);
					defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
					strictMessage(true);
					continue;
				}

			}
			codexsHelperLogTermTests("> RESULT IS [OK]", expName, false);
		}
		resulted(true, element);
		Assert.assertTrue(true);

	}

}
