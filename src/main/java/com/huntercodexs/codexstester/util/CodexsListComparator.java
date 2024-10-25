package com.huntercodexs.codexstester.util;

import org.junit.Assert;

import java.util.Arrays;
import java.util.List;

import static com.huntercodexs.codexstester.util.CodexsAssertion.resulted;
import static com.huntercodexs.codexstester.util.CodexsHelper.codexsHelperLogTermTests;

public abstract class CodexsListComparator extends CodexsArrayListComparator {

	/**
	 * <p style="color: #FFFF00; font-size: 11px; weight: bold">codexsTesterCompareListFormat</p>
	 *
	 * <p style="color: #CDCDCD">To compare a data structure from one Java List Object</p>
	 *
	 * <p>Example</p>
	 *
	 * <blockquote><pre>
	 * public static Object[] expectedListValues() {
	 * 		String string = "john smith";
	 * 		QuickJson quickJson = new QuickJson();
	 * 		quickJson.add("age", 30);
	 * 		quickJson.add("gender", "mens");
	 * 		return new Object[]{string, quickJson};
	 * }
	 *
	 * public static Object[] expectedListTyped() {
	 * 		return new Object[]{String.class, String.class};
	 * }
	 *
	 * QuickJson quickJson = new QuickJson();
	 * quickJson.add("age", 30);
	 * quickJson.add("gender", "mens");
	 *
	 * List&lt;String&gt; listResponse = new ArrayList&lt;&gt;();
	 * listResponse.add("john smith");
	 * listResponse.add(quickJson.toString());
	 *
	 * codexsTesterCompareListFormat(
	 * 			expectedListValues(),
	 * 			expectedListTyped(),
	 * 			listResponse,
	 * 			true);
	 * </pre></blockquote>
	 *
	 * @param listValues (Object[]: )
	 * @param listTyped (Object[]: )
	 * @param listCompare (List&lt;String&gt;: )
	 * @param strictMode (boolean: )
	 * @see <a href="https://github.com/huntercodexs/codexstester">Codexs Tester (GitHub)</a>
	 * @author huntercodexs (powered by jereelton-devel)
	 * */
	protected void codexsTesterCompareListFormat(
			Object[] listValues,
			Object[] listTyped,
			List<String> listCompare,
			boolean strictMode
	) {

		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement element = stackTrace[2];

		codexsHelperLogTermTests("======== SUMMARY =======", "", true);
		codexsHelperLogTermTests("LIST-VALUES.............", Arrays.toString(listValues), false);
		codexsHelperLogTermTests("LIST-TYPED..............", Arrays.toString(listTyped), false);
		codexsHelperLogTermTests("LIST-COMPARE............", listCompare, false);
		codexsHelperLogTermTests("STRICT-MODE.............", strictMode, false);
		codexsHelperLogTermTests("CLASS-TYPE-NAME.........", String.valueOf(listCompare.getClass()), false);
		codexsHelperLogTermTests("======== COMPARE =======", "", true);

		if (!String.valueOf(listCompare.getClass()).contains("ArrayList") && !String.valueOf(listCompare.getClass()).contains("List")) {
			codexsHelperLogTermTests("ERROR ON LIST<I> DATA COMPARE (WRONG-CLASS-TYPED)", "", true);
			resulted(false, element);
			Assert.fail();
		}

		if (listValues.length != listTyped.length || listValues.length != listCompare.size()) {
			codexsHelperLogTermTests("ERROR ON LIST<I> DATA COMPARE (WRONG-LENGTH)", "", true);
			resulted(false, element);
			Assert.fail();
		}

		for (int i = 0; i < listValues.length; i++) {

			if (listCompare.get(i) == null && listTyped[i] == null) {
				codexsHelperLogTermTests("OK -> CONTINUE", null, false);
				codexsHelperLogTermTests("TYPED....", listTyped[i], false);
				codexsHelperLogTermTests("COMPARE..", listCompare.get(i), false);
				continue;
			}

			if (listCompare.get(i) == null && listTyped[i] != null) {
				codexsHelperLogTermTests("> RESULT IS [FAIL] [CRITICAL] [WRONG-TYPED]", "INDEX-" + i, true);
				codexsHelperLogTermTests("EXPECTED....", listTyped[i], false);
				codexsHelperLogTermTests("RECEIVED....", null, false);
				resulted(false, element);
				Assert.fail();
			}

			String expVal = String.valueOf(listValues[i]).replaceFirst("(.*)@[0-9a-z]+", "$1");
			String fndVal = String.valueOf(listCompare.get(i)).replaceFirst("(.*)@[0-9a-z]+", "$1");
			Object expType = listTyped[i];
			Class<?> fndType = listCompare.get(i).getClass();

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

	/**
	 * <p style="color: #FFFF00; font-size: 11px; weight: bold">codexsTesterCompareListFormat</p>
	 *
	 * <p style="color: #CDCDCD">To compare a data structure from one Java List Object - Using Data Tree</p>
	 *
	 * <p>Example</p>
	 *
	 * <blockquote><pre>
	 * public static Object[][] expectedListDataTree() {
	 * 		return new Object[][]{
	 * 		        {0, "john smith", String.class},
	 * 		        {1, "{\"gender\":\"mens\",\"age\":30}", String.class},
	 * 		};
	 * }
	 *
	 * QuickJson quickJson = new QuickJson();
	 * quickJson.add("age", 30);
	 * quickJson.add("gender", "mens");
	 *
	 * List&lt;String&gt; listResponse = new ArrayList&lt;&gt;();
	 * listResponse.add("john smith");
	 * listResponse.add(quickJson.toString());
	 *
	 * codexsTesterCompareListFormat(
	 * 			expectedListDataTree(),
	 * 			listResponse,
	 * 			true);
	 * </pre></blockquote>
	 *
	 * @param expectedListDataTree (Object[]: )
	 * @param listCompare (List&lt;String&gt;: )
	 * @param strictMode (boolean: )
	 * @see <a href="https://github.com/huntercodexs/codexstester">Codexs Tester (GitHub)</a>
	 * @author huntercodexs (powered by jereelton-devel)
	 * */
	protected void codexsTesterCompareListFormat(
			Object[][] expectedListDataTree,
			List<String> listCompare,
			boolean strictMode
	) {

		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement element = stackTrace[2];

		codexsHelperLogTermTests("======== SUMMARY =======", "", true);
		codexsHelperLogTermTests("LIST-KEYS...............", extractFields(expectedListDataTree, 0), false);
		codexsHelperLogTermTests("LIST-VALUES.............", extractFields(expectedListDataTree, 1), false);
		codexsHelperLogTermTests("LIST-TYPED..............", extractFields(expectedListDataTree, 2), false);
		codexsHelperLogTermTests("LIST-COMPARE............", listCompare, false);
		codexsHelperLogTermTests("STRICT-MODE.............", strictMode, false);
		codexsHelperLogTermTests("CLASS-TYPE-NAME.........", String.valueOf(listCompare.getClass()), false);
		codexsHelperLogTermTests("======== COMPARE =======", "", true);

		if (!String.valueOf(listCompare.getClass()).contains("ArrayList") && !String.valueOf(listCompare.getClass()).contains("List")) {
			codexsHelperLogTermTests("ERROR ON LIST<I> DATA COMPARE (WRONG-CLASS-TYPED)", "", true);
			resulted(false, element);
			Assert.fail();
		}

		int arraySize = 0;

		for (int i = 0; i < expectedListDataTree.length; i++) {

			if (i == 0) {
				arraySize = expectedListDataTree[i].length;
			}

			if (i > 0) {
				if (arraySize != expectedListDataTree[i].length) {
					codexsHelperLogTermTests("ERROR ON LIST DATA COMPARE (WRONG-LENGTH)", expectedListDataTree[i], true);
					resulted(false, element);
					Assert.fail();
				}
			}

			if (listCompare.get((Integer) expectedListDataTree[i][0]) == null && expectedListDataTree[i][2] == null) {
				codexsHelperLogTermTests("OK -> CONTINUE", null, false);
				codexsHelperLogTermTests("TYPED....", null, false);
				codexsHelperLogTermTests("COMPARE..", null, false);
				continue;
			}

			if (listCompare.get((Integer) expectedListDataTree[i][0]) == null && expectedListDataTree[i][2] != null) {
				codexsHelperLogTermTests("> RESULT IS [FAIL] [CRITICAL] [WRONG-TYPED]", "INDEX-" + i, true);
				codexsHelperLogTermTests("EXPECTED....", expectedListDataTree[i][2], false);
				codexsHelperLogTermTests("RECEIVED....", null, false);
				resulted(false, element);
				Assert.fail();
			}

			String expVal =  String.valueOf(expectedListDataTree[i][1]).replaceFirst("(.*)@[0-9a-z]+", "$1");
			String fndVal = String.valueOf(listCompare.get((Integer) expectedListDataTree[i][0]))
					.replaceFirst("(.*)@[0-9a-z]+", "$1");
			Object expType = expectedListDataTree[i][2];
			Class<?> fndType = listCompare.get(i).getClass();

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
