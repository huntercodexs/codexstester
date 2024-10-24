package com.huntercodexs.codexstester.util;

import org.junit.Assert;

import java.util.Arrays;
import java.util.HashMap;

import static com.huntercodexs.codexstester.util.CodexsAssertion.resulted;
import static com.huntercodexs.codexstester.util.CodexsHelper.codexsHelperLogTermTests;

public abstract class CodexsHashMapComparator extends CodexsLinkedListComparator {

	protected void codexsTesterCompareHashMapFormat(
			String[] hashMapKeys,
			Object[] hashMapValues,
			Object[] hashMapTyped,
			HashMap<Object, Object> hashMapCompare,
			boolean strictMode
	) {

		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement element = stackTrace[2];

		codexsHelperLogTermTests("======== SUMMARY =======", "", true);
		codexsHelperLogTermTests("HASH-MAP-KEYS...........", Arrays.toString(hashMapKeys), false);
		codexsHelperLogTermTests("HASH-MAP-VALUES.........", Arrays.toString(hashMapValues), false);
		codexsHelperLogTermTests("HASH-MAP-TYPED..........", Arrays.toString(hashMapTyped), false);
		codexsHelperLogTermTests("HASH-MAP-COMPARE........", hashMapCompare, false);
		codexsHelperLogTermTests("STRICT-MODE.............", strictMode, false);
		codexsHelperLogTermTests("CLASS-TYPE-NAME.........", String.valueOf(hashMapCompare.getClass()), false);
		codexsHelperLogTermTests("======== COMPARE =======", "", true);

		if (!String.valueOf(hashMapCompare.getClass()).contains("HashMap")) {
			codexsHelperLogTermTests("ERROR ON HASH-MAP DATA COMPARE (WRONG-CLASS-TYPED)", "", true);
			resulted(false, element);
			Assert.fail();
		}

		if (hashMapKeys.length != hashMapValues.length || hashMapKeys.length != hashMapTyped.length || hashMapKeys.length != hashMapCompare.size()) {
			codexsHelperLogTermTests("ERROR ON HASH-MAP DATA COMPARE (WRONG-LENGTH)", "", true);
			resulted(false, element);
			Assert.fail();
		}

		for (int i = 0; i < hashMapKeys.length; i++) {

			if (hashMapCompare.get(hashMapKeys[i]) == null && hashMapTyped[i] == null) {
				codexsHelperLogTermTests("OK -> CONTINUE", null, false);
				codexsHelperLogTermTests("TYPED....", hashMapTyped[i], false);
				codexsHelperLogTermTests("COMPARE..", hashMapCompare.get(hashMapKeys[i]), false);
				continue;
			}

			if (!hashMapCompare.containsKey(hashMapKeys[i]) || hashMapCompare.get(hashMapKeys[i]) == null && hashMapTyped[i] != null) {
				codexsHelperLogTermTests("> RESULT IS [FAIL] [CRITICAL] [MISSING-KEY]", hashMapKeys[i], true);
				codexsHelperLogTermTests("EXPECTED....", hashMapKeys[i], false);
				codexsHelperLogTermTests("RECEIVED....", null, false);
				resulted(false, element);
				Assert.fail();
			}

			String expName = hashMapKeys[i];
			String fndName = hashMapKeys[i];
			String expVal = String.valueOf(hashMapValues[i]).replaceFirst("(.*)@[0-9a-z]+", "$1");
			String fndVal = String.valueOf(hashMapCompare.get(hashMapKeys[i])).replaceFirst("(.*)@[0-9a-z]+", "$1");
			Object expType = hashMapTyped[i];
			Class<?> fndType = hashMapCompare.get(hashMapKeys[i]).getClass();

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
						codexsHelperLogTermTests("> RESULT IS [WARNING] [NO-STRICT] [DIFF-TYPED] [COMPATIBLE]", expName, true);
						defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
						continue;
					} else {
						codexsHelperLogTermTests("> RESULT IS [FAIL] [NO-STRICT] [WRONG-TYPED]", expName, true);
						defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
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
	
	protected void codexsTesterCompareHashMapFormat(
			Object[][] expectedHashMapDataTree,
			HashMap<Object, Object> hashMapCompare,
			boolean strictMode
	) {

		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement element = stackTrace[2];

		codexsHelperLogTermTests("======== SUMMARY =======", "", true);
		codexsHelperLogTermTests("HASH-MAP-KEYS...........", extractFields(expectedHashMapDataTree, 0), false);
		codexsHelperLogTermTests("HASH-MAP-VALUES.........", extractFields(expectedHashMapDataTree, 1), false);
		codexsHelperLogTermTests("HASH-MAP-TYPED..........", extractFields(expectedHashMapDataTree, 2), false);
		codexsHelperLogTermTests("HASH-MAP-COMPARE........", hashMapCompare, false);
		codexsHelperLogTermTests("STRICT-MODE.............", strictMode, false);
		codexsHelperLogTermTests("CLASS-TYPE-NAME.........", String.valueOf(hashMapCompare.getClass()), false);
		codexsHelperLogTermTests("======== COMPARE =======", "", true);

		if (!String.valueOf(hashMapCompare.getClass()).contains("HashMap")) {
			codexsHelperLogTermTests("ERROR ON HASH-MAP DATA COMPARE (WRONG-CLASS-TYPED)", "", true);
			resulted(false, element);
			Assert.fail();
		}

		int arraySize = 0;

		for (int i = 0; i < expectedHashMapDataTree.length; i++) {

			if (i == 0) {
				arraySize = expectedHashMapDataTree[i].length;
			}

			if (i > 0) {
				if (arraySize != expectedHashMapDataTree[i].length) {
					codexsHelperLogTermTests("ERROR ON HASH-MAP DATA COMPARE (WRONG-LENGTH)", expectedHashMapDataTree[i], true);
					resulted(false, element);
					Assert.fail();
				}
			}

			if (hashMapCompare.get(expectedHashMapDataTree[i][0]) == null && expectedHashMapDataTree[i][2] == null) {
				codexsHelperLogTermTests("OK -> CONTINUE", null, false);
				codexsHelperLogTermTests("TYPED....", expectedHashMapDataTree[i][2], false);
				codexsHelperLogTermTests("COMPARE..", hashMapCompare.get(expectedHashMapDataTree[i][0]), false);
				continue;
			}

			if (!hashMapCompare.containsKey(expectedHashMapDataTree[i][0]) || hashMapCompare.get(expectedHashMapDataTree[i][0]) == null && expectedHashMapDataTree[i][2] != null) {
				codexsHelperLogTermTests("> RESULT IS [FAIL] [CRITICAL] [MISSING-KEY]", expectedHashMapDataTree[i][0], true);
				codexsHelperLogTermTests("EXPECTED....", expectedHashMapDataTree[i][0], false);
				codexsHelperLogTermTests("RECEIVED....", null, false);
				resulted(false, element);
				Assert.fail();
			}

			String expName = String.valueOf(expectedHashMapDataTree[i][0]);
			String fndName = String.valueOf(expectedHashMapDataTree[i][0]);
			String expVal = String.valueOf(expectedHashMapDataTree[i][1])
					.replaceFirst("(.*)@[0-9a-z]+", "$1");
			String fndVal = String.valueOf(hashMapCompare.get(expectedHashMapDataTree[i][0]))
					.replaceFirst("(.*)@[0-9a-z]+", "$1");
			Object expType = expectedHashMapDataTree[i][2];
			Class<?> fndType = hashMapCompare.get(expectedHashMapDataTree[i][0]).getClass();

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
						codexsHelperLogTermTests("> RESULT IS [WARNING] [NO-STRICT] [DIFF-TYPED] [COMPATIBLE]", expName, true);
						defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
						continue;
					} else {
						codexsHelperLogTermTests("> RESULT IS [FAIL] [NO-STRICT] [WRONG-TYPED]", expName, true);
						defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
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
