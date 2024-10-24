package com.huntercodexs.codexstester.util;

import com.huntercodexs.codexstester.resource.quickjson.QuickJson;
import org.json.JSONException;
import org.junit.Assert;

import java.util.Arrays;

import static com.huntercodexs.codexstester.util.CodexsAssertion.resulted;
import static com.huntercodexs.codexstester.util.CodexsHelper.codexsHelperLogTermTests;
import static com.huntercodexs.codexstester.util.CodexsHelper.codexsHelperMd5;
import static com.huntercodexs.codexstester.util.CodexsJsonParser.codexsTesterJsonRefactor;

public abstract class CodexsJsonComparator extends CodexsDtoComparator {

	private final String JSON_DEFAULT = "com.huntercodexs.codexstester.resource.quickjson.QuickJson";

	protected void codexsTesterCompareJsonFormat(
			Object[][] expectedJsonDataTree,
			org.json.JSONObject jsonCompare,
			boolean strictMode,
			String refactorMode, /*none,middle,regular,complex*/
			boolean debug
	) throws Exception {

		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement element = stackTrace[2];

		codexsHelperLogTermTests("======== SUMMARY =======", "", true);
		codexsHelperLogTermTests("JSON-KEYS...............", extractFields(expectedJsonDataTree, 0), false);
		codexsHelperLogTermTests("JSON-VALUES.............", extractFields(expectedJsonDataTree, 1), false);
		codexsHelperLogTermTests("JSON-TYPED..............", extractFields(expectedJsonDataTree, 2), false);
		codexsHelperLogTermTests("JSON-COMPARE............", jsonCompare, false);
		codexsHelperLogTermTests("STRICT-MODE.............", strictMode, false);
		codexsHelperLogTermTests("REFACTOR-MODE...........", refactorMode, false);
		codexsHelperLogTermTests("DEBUG...................", debug, false);
		codexsHelperLogTermTests("CLASS-TYPE-NAME.........", String.valueOf(jsonCompare.getClass()), false);
		codexsHelperLogTermTests("======== COMPARE =======", "", true);

		if (!String.valueOf(jsonCompare.getClass()).contains("org.json.JSONObject")) {
			codexsHelperLogTermTests("ERROR ON JSON DATA COMPARE (WRONG-JSON-TYPED)", "", true);
			resulted(false, element);
			Assert.fail();
		}

		int arraySize = 0;

		for (int i = 0; i < expectedJsonDataTree.length; i++) {

			if (i == 0) {
				arraySize = expectedJsonDataTree[i].length;
			}

			if (i > 0) {
				if (arraySize != expectedJsonDataTree[i].length) {
					codexsHelperLogTermTests("ERROR ON JSON DATA COMPARE (WRONG-LENGTH)", expectedJsonDataTree[i], true);
					resulted(false, element);
					Assert.fail();
				}
			}

			try {
				Object tmp = jsonCompare.get(String.valueOf(expectedJsonDataTree[i][0]));
			} catch (JSONException je) {
				codexsHelperLogTermTests("> RESULT IS [ABORT] [WRONG-KEY]", String.valueOf(expectedJsonDataTree[i][0]), true);
				resulted(false, element);
				Assert.fail();
			}

			if (jsonCompare.get(String.valueOf(expectedJsonDataTree[i][0])) == null && expectedJsonDataTree[i][2] == null) {
				codexsHelperLogTermTests("> RESULT IS [OK] CONTINUE...", null, false);
				codexsHelperLogTermTests("TYPED....", expectedJsonDataTree[i][2], false);
				codexsHelperLogTermTests("COMPARE..", jsonCompare.get(String.valueOf(expectedJsonDataTree[i][0])), false);
				continue;
			}

			if (jsonCompare.get(String.valueOf(expectedJsonDataTree[i][0])) == null && expectedJsonDataTree[i][2] != null) {
				codexsHelperLogTermTests("> RESULT IS [FAIL] [CRITICAL] [WRONG-TYPED]", expectedJsonDataTree[i][0], false);
				codexsHelperLogTermTests("EXPECTED..", expectedJsonDataTree[i][2], false);
				codexsHelperLogTermTests("RECEIVED..", null, false);
				resulted(false, element);
				Assert.fail();
			}

			String expKey = String.valueOf(expectedJsonDataTree[i][0]);
			Object fndKey = null;
			Object expVal = String.valueOf(expectedJsonDataTree[i][1]).replaceFirst("(.*)@[0-9a-z]+", "$1");
			Object fndVal = String.valueOf(jsonCompare.get(expKey)).replaceFirst("(.*)@[0-9a-z]+", "$1");
			Object expType = expectedJsonDataTree[i][2];
			Class<?> fndType = jsonCompare.get(expKey).getClass();

			if (
					(jsonCompare.get(expKey) == null || jsonCompare.getString(expKey).equals("null")) &&
							(expVal == null || String.valueOf(expVal).equals("null")) &&
							(String.valueOf(fndType).contains("org.json.JSONObject$1"))
			) {
				codexsHelperLogTermTests("> RESULT IS [OK] [DATA IS NULL] CONTINUE...", null, true);
				defaultMessage(expVal, fndVal, expType, fndType, expKey, "IGNORED");
				continue;
			}

			if (!refactorMode.equals("none")) {

                /*if (codexsTesterIsNetJson(expVal, debug)) {
                    expVal = codexsTesterNetJsonToOrgJson((JSONObject) expVal, debug);
                }*/

				if (debug) codexsHelperLogTermTests("[BEFORE] EXPECTED", expVal, true);
				expVal = codexsTesterJsonRefactor(refactorMode, expVal, debug);
				if (debug) codexsHelperLogTermTests("[AFTER] EXPECTED", expVal, true);

                /*if (codexsTesterIsNetJson(fndVal, debug)) {
                    fndVal = codexsTesterNetJsonToOrgJson((JSONObject) fndVal, debug);
                }*/

				if (debug) codexsHelperLogTermTests("[BEFORE] FOUNDED", fndVal, true);
				fndVal = codexsTesterJsonRefactor(refactorMode, fndVal, debug);
				if (debug) codexsHelperLogTermTests("[AFTER] FOUNDED", fndVal, true);

			}

			codexsHelperLogTermTests("===> INDEX <=== ", i, true);
			codexsHelperLogTermTests("EXPECTED KEY....", expKey, false);
			codexsHelperLogTermTests("FOUND KEY.......:", null, false);
			codexsHelperLogTermTests("EXPECTED TYPE...", expType, false);
			codexsHelperLogTermTests("FOUND TYPE......", fndType, false);
			codexsHelperLogTermTests("EXPECTED VALUE..", expVal, false);
			codexsHelperLogTermTests("FOUND VALUE.....", fndVal, false);
			codexsHelperLogTermTests("EXPECTED MD5....", hash(expVal), false);
			codexsHelperLogTermTests("FOUND MD5.......", hash(fndVal), false);

			if (strictMode) {

				if (fndVal.equals(expVal) && fndType != expType && isInterface(expType, fndType)) {
					codexsHelperLogTermTests("> RESULT IS [WARNING] [DIFF-TYPED] [COMPATIBLE]", expKey, true);
					defaultMessage(expVal, fndVal, expType, fndType, expKey, "IGNORED");
					continue;
				}

				if (fndVal.equals(expVal) && fndType != expType && !isInterface(expType, fndType)) {
					codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [CRITICAL-ERROR] [WRONG-TYPED-1]", expKey, true);
					defaultMessage(expVal, fndVal, expType, fndType, expKey, "IGNORED");
					resulted(false, element);
					Assert.fail();
				}

				if (fndType != expType && !isInterface(expType, fndType)) {
					codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [CRITICAL-ERROR] [WRONG-TYPED-2]", expKey, true);
					defaultMessage(expVal, fndVal, expType, fndType, expKey, "IGNORED");
					resulted(false, element);
					Assert.fail();
				}

				if (!codexsHelperMd5(String.valueOf(fndVal)).equals(codexsHelperMd5(String.valueOf(expVal)))) {
					codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [ERROR] [WRONG-VALUE]", expKey, true);
					defaultMessage(expVal, fndVal, expType, fndType, expKey, "IGNORED");
					resulted(false, element);
					Assert.fail();
				}

			} else {

				if (expType != fndType) {
					if (isInterface(expType, fndType)) {
						codexsHelperLogTermTests("> RESULT IS [WARNING] [NO-STRICT] [DIFF-TYPED] [COMPATIBLE]", expKey, true);
						defaultMessage(expVal, fndVal, expType, fndType, expKey, "IGNORED");
						continue;
					} else {
						codexsHelperLogTermTests("> RESULT IS [FAIL] [NO-STRICT] [WRONG-TYPED]", expKey, true);
						defaultMessage(expVal, fndVal, expType, fndType, expKey, "IGNORED");
						resulted(false, element);
						Assert.fail();
					}
				}

				if (!expVal.equals(fndVal)) {
					codexsHelperLogTermTests("> RESULT IS [WARNING] [NO-STRICT] [WRONG-VALUE]", expKey, true);
					defaultMessage(expVal, fndVal, expType, fndType, expKey, "IGNORED");
					strictMessage(true);
					continue;
				}
			}
			codexsHelperLogTermTests("> RESULT IS [OK]", expKey, false);
		}
		resulted(true, element);
		Assert.assertTrue(true);

	}

	protected void codexsTesterCompareJsonFormat(
			Object[][] expectedJsonDataTree,
			QuickJson jsonCompare,
			boolean strictMode,
			String refactorMode, /*none,easy,middle,regular,complex*/
			boolean debug
	) throws Exception {

		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement element = stackTrace[2];

		codexsHelperLogTermTests("======== SUMMARY =======", "", true);
		codexsHelperLogTermTests("JSON-KEYS...............", extractFields(expectedJsonDataTree, 0), false);
		codexsHelperLogTermTests("JSON-VALUES.............", extractFields(expectedJsonDataTree, 1), false);
		codexsHelperLogTermTests("JSON-TYPED..............", extractFields(expectedJsonDataTree, 2), false);
		codexsHelperLogTermTests("JSON-COMPARE............", jsonCompare.json(), false);
		codexsHelperLogTermTests("STRICT-MODE.............", strictMode, false);
		codexsHelperLogTermTests("REFACTOR-MODE...........", refactorMode, false);
		codexsHelperLogTermTests("DEBUG...................", debug, false);
		codexsHelperLogTermTests("CLASS-TYPE-NAME.........", String.valueOf(jsonCompare.getClass()), false);
		codexsHelperLogTermTests("======== COMPARE =======", "", true);

		if (!String.valueOf(jsonCompare.getClass()).contains(JSON_DEFAULT)) {
			codexsHelperLogTermTests("ERROR ON JSON DATA COMPARE (WRONG-JSON-TYPED)", "", true);
			resulted(false, element);
			Assert.fail();
		}

		int arraySize = 0;
		QuickJson qj = new QuickJson(jsonCompare.json());

		for (int i = 0; i < expectedJsonDataTree.length; i++) {

			if (i == 0) {
				arraySize = expectedJsonDataTree[i].length;
			}

			if (i > 0) {
				if (arraySize != expectedJsonDataTree[i].length) {
					codexsHelperLogTermTests("ERROR ON JSON DATA COMPARE (WRONG-LENGTH)", expectedJsonDataTree[i], true);
					resulted(false, element);
					Assert.fail();
				}
			}

			Object jsonValue = null;
			try {
				jsonValue = qj.getObject(String.valueOf(expectedJsonDataTree[i][0]));
			} catch (Exception ex) {
				codexsHelperLogTermTests("> RESULT IS [ABORT] [WRONG-KEY]", String.valueOf(expectedJsonDataTree[i][0]), true);
				resulted(false, element);
				Assert.fail();
			}

			if (jsonValue == null && expectedJsonDataTree[i][2] == null) {
				codexsHelperLogTermTests("> RESULT IS [OK] CONTINUE...", null, false);
				codexsHelperLogTermTests("TYPED....", expectedJsonDataTree[i][2], false);
				codexsHelperLogTermTests("COMPARE..", jsonValue, false);
				continue;
			}

			if (jsonValue == null && expectedJsonDataTree[i][2] != null) {
				codexsHelperLogTermTests("> RESULT IS [FAIL] [CRITICAL] [WRONG-TYPED]", expectedJsonDataTree[i][0], false);
				codexsHelperLogTermTests("EXPECTED..", expectedJsonDataTree[i][2], false);
				codexsHelperLogTermTests("RECEIVED..", null, false);
				resulted(false, element);
				Assert.fail();
			}

			String expKey = String.valueOf(expectedJsonDataTree[i][0]);
			Object fndKey = null;
			Object expVal = String.valueOf(expectedJsonDataTree[i][1]).replaceFirst("(.*)@[0-9a-z]+", "$1");
			Object fndVal = String.valueOf(jsonValue).replaceFirst("(.*)@[0-9a-z]+", "$1");
			Object expType = expectedJsonDataTree[i][2];
			Class<?> fndType = jsonCompare.get(expKey).getClass();

			if (
					(qj.getObject(expKey) == null || qj.getObject(expKey).equals("null")) &&
					(expVal == null || String.valueOf(expVal).equals("null")) &&
					(String.valueOf(fndType).contains("org.json.JSONObject$1"))
			) {
				codexsHelperLogTermTests("> RESULT IS [OK] [DATA IS NULL] CONTINUE...", null, true);
				defaultMessage(expVal, fndVal, expType, fndType, expKey, "IGNORED");
				continue;
			}

			if (!refactorMode.equals("none")) {

				if (debug) codexsHelperLogTermTests("[BEFORE] EXPECTED", expVal, true);
				expVal = codexsTesterJsonRefactor(refactorMode, expVal, debug);
				if (debug) codexsHelperLogTermTests("[AFTER] EXPECTED", expVal, true);

				if (debug) codexsHelperLogTermTests("[BEFORE] FOUNDED", fndVal, true);
				fndVal = codexsTesterJsonRefactor(refactorMode, fndVal, debug);
				if (debug) codexsHelperLogTermTests("[AFTER] FOUNDED", fndVal, true);

			}

			codexsHelperLogTermTests("===> INDEX <=== ", i, true);
			codexsHelperLogTermTests("EXPECTED KEY....", expKey, false);
			codexsHelperLogTermTests("FOUND KEY.......:", null, false);
			codexsHelperLogTermTests("EXPECTED TYPE...", expType, false);
			codexsHelperLogTermTests("FOUND TYPE......", fndType, false);
			codexsHelperLogTermTests("EXPECTED VALUE..", expVal, false);
			codexsHelperLogTermTests("FOUND VALUE.....", fndVal, false);
			codexsHelperLogTermTests("EXPECTED MD5....", hash(expVal), false);
			codexsHelperLogTermTests("FOUND MD5.......", hash(fndVal), false);

			if (strictMode && fndType != null) {

				if (fndVal.equals(expVal) && fndType != expType && isInterface(expType, fndType)) {
					codexsHelperLogTermTests("> RESULT IS [WARNING] [DIFF-TYPED] [COMPATIBLE]", expKey, true);
					defaultMessage(expVal, fndVal, expType, fndType, expKey, "IGNORED");
					continue;
				}

				if (fndVal.equals(expVal) && fndType != expType && !isInterface(expType, fndType)) {
					codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [CRITICAL-ERROR] [WRONG-TYPED-1]", expKey, true);
					defaultMessage(expVal, fndVal, expType, fndType, expKey, "IGNORED");
					resulted(false, element);
					Assert.fail();
				}

				if (fndType != expType && !isInterface(expType, fndType)) {
					codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [CRITICAL-ERROR] [WRONG-TYPED-2]", expKey, true);
					defaultMessage(expVal, fndVal, expType, fndType, expKey, "IGNORED");
					resulted(false, element);
					Assert.fail();
				}

				if (!codexsHelperMd5(String.valueOf(fndVal)).equals(codexsHelperMd5(String.valueOf(expVal)))) {
					codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [ERROR] [WRONG-VALUE]", expKey, true);
					defaultMessage(expVal, fndVal, expType, fndType, expKey, "IGNORED");
					resulted(false, element);
					Assert.fail();
				}

			} else {

				if (expType != fndType) {
					if (isInterface(expType, fndType)) {
						codexsHelperLogTermTests("> RESULT IS [WARNING] [NO-STRICT] [DIFF-TYPED] [COMPATIBLE]", expKey, true);
						defaultMessage(expVal, fndVal, expType, fndType, expKey, "IGNORED");
						continue;
					} else {
						codexsHelperLogTermTests("> RESULT IS [FAIL] [NO-STRICT] [WRONG-TYPED]", expKey, true);
						defaultMessage(expVal, fndVal, expType, fndType, expKey, "IGNORED");
						resulted(false, element);
						Assert.fail();
					}
				}

				if (!expVal.equals(fndVal)) {
					codexsHelperLogTermTests("> RESULT IS [WARNING] [NO-STRICT] [WRONG-VALUE]", expKey, true);
					defaultMessage(expVal, fndVal, expType, fndType, expKey, "IGNORED");
					strictMessage(true);
					continue;
				}
			}
			codexsHelperLogTermTests("> RESULT IS [OK]", expKey, false);
		}
		resulted(true, element);
		Assert.assertTrue(true);
	}

	protected void codexsTesterCompareJsonFormat(
			String[] jsonKeys,
			Object[] jsonValues,
			Object[] jsonTyped,
			org.json.JSONObject jsonCompare,
			boolean strictMode,
			String refactorMode, /*none,easy,middle,regular,complex*/
			boolean debug
	) throws Exception {

		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement element = stackTrace[2];

		codexsHelperLogTermTests("======== SUMMARY =======", "", true);
		codexsHelperLogTermTests("JSON-KEYS...............", Arrays.toString(jsonKeys), false);
		codexsHelperLogTermTests("JSON-VALUES.............", Arrays.toString(jsonValues), false);
		codexsHelperLogTermTests("JSON-TYPED..............", Arrays.toString(jsonTyped), false);
		codexsHelperLogTermTests("JSON-COMPARE............", jsonCompare, false);
		codexsHelperLogTermTests("STRICT-MODE.............", strictMode, false);
		codexsHelperLogTermTests("REFACTOR-MODE...........", refactorMode, false);
		codexsHelperLogTermTests("DEBUG...................", debug, false);
		codexsHelperLogTermTests("CLASS-TYPE-NAME.........", String.valueOf(jsonCompare.getClass()), false);
		codexsHelperLogTermTests("======== COMPARE =======", "", true);

		if (!String.valueOf(jsonCompare.getClass()).contains("org.json.JSONObject")) {
			codexsHelperLogTermTests("ERROR ON JSON DATA COMPARE (WRONG-JSON-TYPED)", "", true);
			resulted(false, element);
			Assert.fail();
		}

		if (jsonKeys.length != jsonValues.length || jsonKeys.length != jsonTyped.length || jsonKeys.length != jsonCompare.length()) {
			codexsHelperLogTermTests("ERROR ON JSON DATA COMPARE (WRONG-LENGTH)", "", true);
			resulted(false, element);
			Assert.fail();
		}

		for (int i = 0; i < jsonKeys.length; i++) {

			try {
				Object tmp = jsonCompare.get(jsonKeys[i]);
			} catch (Exception ex) {
				codexsHelperLogTermTests("> RESULT IS [ABORT] [WRONG-KEY]", jsonKeys[i], true);
				resulted(false, element);
				Assert.fail();
			}

			if (jsonCompare.getString(jsonKeys[i]) == null && jsonTyped[i] == null) {
				codexsHelperLogTermTests("> RESULT IS [OK] CONTINUE...", null, false);
				codexsHelperLogTermTests("TYPED....", jsonTyped[i], false);
				codexsHelperLogTermTests("COMPARE..", jsonCompare.get(jsonKeys[i]), false);
				continue;
			}

			if (jsonCompare.get(jsonKeys[i]) == null && jsonTyped[i] != null) {
				codexsHelperLogTermTests("> RESULT IS [FAIL] [CRITICAL] [WRONG-TYPED]", jsonKeys[i], false);
				codexsHelperLogTermTests("EXPECTED..", jsonTyped[i], false);
				codexsHelperLogTermTests("RECEIVED..", null, false);
				resulted(false, element);
				Assert.fail();
			}

			String expKey = jsonKeys[i];
			Object fndKey = null;
			Object expVal = String.valueOf(jsonValues[i]).replaceFirst("(.*)@[0-9a-z]+", "$1");
			Object fndVal = jsonCompare.getString(expKey).replaceFirst("(.*)@[0-9a-z]+", "$1");
			Object expType = jsonTyped[i];
			Class<?> fndType = jsonCompare.get(expKey).getClass();

			if (
					(jsonCompare.get(expKey) == null || jsonCompare.getString(expKey).equals("null")) &&
							(expVal == null || String.valueOf(expVal).equals("null")) &&
							(String.valueOf(fndType).contains("org.json.JSONObject$1"))
			) {
				codexsHelperLogTermTests("> RESULT IS [OK] [DATA IS NULL] CONTINUE...", null, true);
				defaultMessage(expVal, fndVal, expType, fndType, expKey, "IGNORED");
				continue;
			}

			if (!refactorMode.equals("none")) {

                /*if (codexsTesterIsNetJson(expVal, debug)) {
                    expVal = codexsTesterNetJsonToOrgJson((JSONObject) expVal, debug);
                }*/

				if (debug) codexsHelperLogTermTests("[BEFORE] EXPECTED", expVal, true);
				expVal = codexsTesterJsonRefactor(refactorMode, expVal, debug);
				if (debug) codexsHelperLogTermTests("[AFTER] EXPECTED", expVal, true);

                /*if (codexsTesterIsNetJson(fndVal, debug)) {
                    fndVal = codexsTesterNetJsonToOrgJson((JSONObject) fndVal, debug);
                }*/

				if (debug) codexsHelperLogTermTests("[BEFORE] FOUNDED", fndVal, true);
				fndVal = codexsTesterJsonRefactor(refactorMode, fndVal, debug);
				if (debug) codexsHelperLogTermTests("[AFTER] FOUNDED", fndVal, true);

			}

			codexsHelperLogTermTests("===> INDEX <=== ", i, true);
			codexsHelperLogTermTests("EXPECTED KEY....", expKey, true);
			codexsHelperLogTermTests("FOUND KEY.......", null, false);
			codexsHelperLogTermTests("EXPECTED TYPE...", expType, false);
			codexsHelperLogTermTests("FOUND TYPE......", fndType, false);
			codexsHelperLogTermTests("EXPECTED VALUE..", expVal, false);
			codexsHelperLogTermTests("FOUND VALUE.....", fndVal, false);
			codexsHelperLogTermTests("EXPECTED MD5....", hash(expVal), false);
			codexsHelperLogTermTests("FOUND MD5.......", hash(fndVal), false);

			if (strictMode) {

				if (fndVal.equals(expVal) && fndType != expType && isInterface(expType, fndType)) {
					codexsHelperLogTermTests("> RESULT IS [WARNING] [DIFF-TYPED] [COMPATIBLE]", expKey, true);
					defaultMessage(expVal, fndVal, expType, fndType, expKey, "IGNORED");
					continue;
				}

				if (fndVal.equals(expVal) && fndType != expType && !isInterface(expType, fndType)) {
					codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [CRITICAL-ERROR] [WRONG-TYPED-1]", expKey, true);
					defaultMessage(expVal, fndVal, expType, fndType, expKey, "IGNORED");
					resulted(false, element);
					Assert.fail();
				}

				if (fndType != expType && !isInterface(expType, fndType)) {
					codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [CRITICAL-ERROR] [WRONG-TYPED-2]", expKey, true);
					defaultMessage(expVal, fndVal, expType, fndType, expKey, "IGNORED");
					resulted(false, element);
					Assert.fail();
				}

				if (!codexsHelperMd5(String.valueOf(fndVal)).equals(codexsHelperMd5(String.valueOf(expVal)))) {
					codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [ERROR] [WRONG-VALUE]", expKey, true);
					defaultMessage(expVal, fndVal, expType, fndType, expKey, "IGNORED");
					resulted(false, element);
					Assert.fail();
				}

			} else {

				if (expType != fndType) {
					if (isInterface(expType, fndType)) {
						codexsHelperLogTermTests("> RESULT IS [WARNING] [NO-STRICT] [DIFF-TYPED] [COMPATIBLE]", expKey, true);
						defaultMessage(expVal, fndVal, expType, fndType, expKey, "IGNORED");
						continue;
					} else {
						codexsHelperLogTermTests("> RESULT IS [FAIL] [NO-STRICT] [WRONG-TYPED]", expKey, true);
						defaultMessage(expVal, fndVal, expType, fndType, expKey, "IGNORED");
						resulted(false, element);
						Assert.fail();
					}
				}

				if (!expVal.equals(fndVal)) {
					codexsHelperLogTermTests("> RESULT IS [WARNING] [NO-STRICT] [WRONG-VALUE]", expKey, true);
					defaultMessage(expVal, fndVal, expType, fndType, expKey, "IGNORED");
					strictMessage(true);
					continue;
				}
			}
			codexsHelperLogTermTests("> RESULT IS [OK]", expKey, false);
		}
		resulted(true, element);
		Assert.assertTrue(true);
	}

	protected void codexsTesterCompareJsonFormat(
			String[] jsonKeys,
			Object[] jsonValues,
			Object[] jsonTyped,
			QuickJson jsonCompare,
			boolean strictMode,
			String refactorMode, /*none,easy,middle,regular,complex*/
			boolean debug
	) throws Exception {

		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement element = stackTrace[2];

		codexsHelperLogTermTests("======== SUMMARY =======", "", true);
		codexsHelperLogTermTests("JSON-KEYS...............", Arrays.toString(jsonKeys), false);
		codexsHelperLogTermTests("JSON-VALUES.............", Arrays.toString(jsonValues), false);
		codexsHelperLogTermTests("JSON-TYPED..............", Arrays.toString(jsonTyped), false);
		codexsHelperLogTermTests("JSON-COMPARE............", jsonCompare, false);
		codexsHelperLogTermTests("STRICT-MODE.............", strictMode, false);
		codexsHelperLogTermTests("REFACTOR-MODE...........", refactorMode, false);
		codexsHelperLogTermTests("DEBUG...................", debug, false);
		codexsHelperLogTermTests("CLASS-TYPE-NAME.........", String.valueOf(jsonCompare.getClass()), false);
		codexsHelperLogTermTests("======== COMPARE =======", "", true);

		if (!String.valueOf(jsonCompare.getClass()).contains(JSON_DEFAULT)) {
			codexsHelperLogTermTests("ERROR ON JSON DATA COMPARE (WRONG-JSON-TYPED)", "", true);
			resulted(false, element);
			Assert.fail();
		}

		if (jsonKeys.length != jsonValues.length || jsonKeys.length != jsonTyped.length/* || jsonKeys.length != jsonCompare.size()*/) {
			codexsHelperLogTermTests("ERROR ON JSON DATA COMPARE (WRONG-LENGTH)", "", true);
			resulted(false, element);
			Assert.fail();
		}

		QuickJson qj = new QuickJson(jsonCompare.json());

		for (int i = 0; i < jsonKeys.length; i++) {

			Object jsonValue = null;
			try {
				jsonValue = qj.getObject(String.valueOf(jsonKeys[i]));
			} catch (Exception ex) {
				codexsHelperLogTermTests("> RESULT IS [ABORT] [WRONG-KEY]", jsonKeys[i], true);
				resulted(false, element);
				Assert.fail();
			}

			if (jsonValue == null && jsonTyped[i] == null) {
				codexsHelperLogTermTests("> RESULT IS [OK] CONTINUE...", null, false);
				codexsHelperLogTermTests("TYPED....", jsonTyped[i], false);
				codexsHelperLogTermTests("COMPARE..", jsonValue, false);
				continue;
			}

			if (jsonValue == null && jsonTyped[i] != null) {
				codexsHelperLogTermTests("> RESULT IS [FAIL] [CRITICAL] [WRONG-TYPED]", jsonTyped[i], false);
				codexsHelperLogTermTests("EXPECTED..", jsonTyped[i], false);
				codexsHelperLogTermTests("RECEIVED..", null, false);
				resulted(false, element);
				Assert.fail();
			}

			String expKey = jsonKeys[i];
			Object fndKey = null;
			Object expVal = String.valueOf(jsonValues[i]).replaceFirst("(.*)@[0-9a-z]+", "$1");
			Object fndVal = String.valueOf(jsonValue).replaceFirst("(.*)@[0-9a-z]+", "$1");
			Object expType = jsonTyped[i];
			Class<?> fndType = jsonCompare.get(expKey).getClass();

			if (
					(qj.getObject(expKey) == null || qj.getObject(expKey).equals("null")) &&
					(expVal == null || String.valueOf(expVal).equals("null")) &&
					((String.valueOf(fndType).contains("org.json.JSONObject$1")) || (String.valueOf(fndType).contains("QuickJson")))
			) {
				codexsHelperLogTermTests("> RESULT IS [OK] [DATA IS NULL] CONTINUE...", null, true);
				defaultMessage(expVal, fndVal, expType, fndType, expKey, "IGNORED");
				continue;
			}

			if (!refactorMode.equals("none")) {

				if (debug) codexsHelperLogTermTests("[BEFORE] EXPECTED", expVal, true);
				expVal = codexsTesterJsonRefactor(refactorMode, expVal, debug);
				if (debug) codexsHelperLogTermTests("[AFTER] EXPECTED", expVal, true);

				if (debug) codexsHelperLogTermTests("[BEFORE] FOUNDED", fndVal, true);
				fndVal = codexsTesterJsonRefactor(refactorMode, fndVal, debug);
				if (debug) codexsHelperLogTermTests("[AFTER] FOUNDED", fndVal, true);

			}

			codexsHelperLogTermTests("===> INDEX <=== ", i, true);
			codexsHelperLogTermTests("EXPECTED KEY....", expKey, true);
			codexsHelperLogTermTests("FOUND KEY.......", null, false);
			codexsHelperLogTermTests("EXPECTED TYPE...", expType, false);
			codexsHelperLogTermTests("FOUND TYPE......", fndType, false);
			codexsHelperLogTermTests("EXPECTED VALUE..", expVal, false);
			codexsHelperLogTermTests("FOUND VALUE.....", fndVal, false);
			codexsHelperLogTermTests("EXPECTED MD5....", hash(expVal), false);
			codexsHelperLogTermTests("FOUND MD5.......", hash(fndVal), false);

			if (strictMode) {

				if (fndVal.equals(expVal) && fndType != expType && isInterface(expType, fndType)) {
					codexsHelperLogTermTests("> RESULT IS [WARNING] [DIFF-TYPED] [COMPATIBLE]", expKey, true);
					defaultMessage(expVal, fndVal, expType, fndType, expKey, "IGNORED");
					continue;
				}

				if (fndVal.equals(expVal) && fndType != expType && !isInterface(expType, fndType)) {
					codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [CRITICAL-ERROR] [WRONG-TYPED-1]", expKey, true);
					defaultMessage(expVal, fndVal, expType, fndType, expKey, "IGNORED");
					resulted(false, element);
					Assert.fail();
				}

				if (fndType != expType && !isInterface(expType, fndType)) {
					codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [CRITICAL-ERROR] [WRONG-TYPED-2]", expKey, true);
					defaultMessage(expVal, fndVal, expType, fndType, expKey, "IGNORED");
					resulted(false, element);
					Assert.fail();
				}

				if (!codexsHelperMd5(String.valueOf(fndVal)).equals(codexsHelperMd5(String.valueOf(expVal)))) {
					codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [ERROR] [WRONG-VALUE]", expKey, true);
					defaultMessage(expVal, fndVal, expType, fndType, expKey, "IGNORED");
					resulted(false, element);
					Assert.fail();
				}

			} else {

				if (expType != fndType) {
					if (isInterface(expType, fndType)) {
						codexsHelperLogTermTests("> RESULT IS [WARNING] [NO-STRICT] [DIFF-TYPED] [COMPATIBLE]", expKey, true);
						defaultMessage(expVal, fndVal, expType, fndType, expKey, "IGNORED");
						continue;
					} else {
						codexsHelperLogTermTests("> RESULT IS [FAIL] [NO-STRICT] [WRONG-TYPED]", expKey, true);
						defaultMessage(expVal, fndVal, expType, fndType, expKey, "IGNORED");
						resulted(false, element);
						Assert.fail();
					}
				}

				if (!expVal.equals(fndVal)) {
					codexsHelperLogTermTests("> RESULT IS [WARNING] [NO-STRICT] [WRONG-VALUE]", expKey, true);
					defaultMessage(expVal, fndVal, expType, fndType, expKey, "IGNORED");
					strictMessage(true);
					continue;
				}
			}
			codexsHelperLogTermTests("> RESULT IS [OK]", expKey, false);
		}
		resulted(true, element);
		Assert.assertTrue(true);
	}

}
