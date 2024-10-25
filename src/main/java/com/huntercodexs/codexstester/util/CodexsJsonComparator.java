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

	/**
	 * <h1 style="color: #FFFF00; font-size: 11px">codexsTesterCompareJsonFormat</h1>
	 *
	 * <p style="color: #CDCDCD">Data process to compare a JSON Data structure</p>
	 *
	 * <p>
	 *     This method can process a complex JSON Data structure to check and validate each field
	 *     affordable in the jsonCompare argument.
	 *     <br>
	 *     The values for refactorMode are: {none,easy,middle,regular,complex}
	 * </p>
	 *
	 * <p>Example</p>
	 *
	 * <blockquote><pre>
	 * public static Object[][] expectedJsonDataTree() {
	 *
	 * 		String string = "value1";
	 * 		QuickJson quickJson = new QuickJson();
	 * 		HeadersDto headersDto = new HeadersDto();
	 * 		HashMap&lt;Object, Object&gt; hashMap = new HashMap&lt;&gt;();
	 * 		ArrayList&lt;Object&gt; arrayList = new ArrayList&lt;&gt;();
	 * 		LinkedList&lt;Object&gt; linkedList = new LinkedList&lt;&gt;();
	 * 		List&lt;String&gt; list = new ArrayList&lt;&gt;();
	 * 		LinkedHashMap&lt;Object, Object&gt; linkedHashMap = new LinkedHashMap&lt;&gt;();
	 *
	 * 		return new Object[][]{
	 * 		    {"field1", string,        String.class},
	 * 		    {"field2", quickJson,     QuickJson.class},
	 * 		    {"field3", headersDto,    HeadersDto.class},
	 * 		    {"field4", hashMap,       HashMap.class},
	 * 		    {"field5", arrayList,     ArrayList.class},
	 * 		    {"field6", linkedList,    LinkedList.class},
	 * 		    {"field7", list,          List.class},
	 * 		    {"field8", linkedHashMap, LinkedHashMap.class}
	 * 		};
	 * }
	 *
	 * //SIMULATE RESPONSE SAMPLE
	 * String string = "value1";
	 * QuickJson quickJson = new QuickJson();
	 * HeadersDto headersDto = new HeadersDto();
	 * HashMap&lt;Object, Object&gt; hashMap = new HashMap&lt;&gt;();
	 * ArrayList&lt;Object&gt; arrayList = new ArrayList&lt;&gt;();
	 * LinkedList&lt;Object&gt; linkedList = new LinkedList&lt;&gt;();
	 * List&lt;String&gt; list = new ArrayList&lt;&gt;();
	 * LinkedHashMap&lt;Object, Object&gt; linkedHashMap = new LinkedHashMap&lt;&gt;();
     *
	 * org.json.JSONObject orgJsonResponseSimulate = new org.json.JSONObject();
	 * orgJsonResponseSimulate.put("field1", string);
	 * orgJsonResponseSimulate.put("field2", quickJson);
	 * orgJsonResponseSimulate.put("field3", headersDto);
	 * orgJsonResponseSimulate.put("field4", hashMap);
	 * orgJsonResponseSimulate.put("field5", arrayList);
	 * orgJsonResponseSimulate.put("field6", linkedList);
	 * orgJsonResponseSimulate.put("field7", list);
	 * orgJsonResponseSimulate.put("field8", linkedHashMap);
     *
	 * codexsTesterCompareJsonFormat(
	 *         expectedJsonDataTree(),
	 *         orgJsonResponseSimulate,
	 *         true,
	 *         "none",
	 *         true);
	 * </pre></blockquote>
	 *
	 * @param expectedJsonDataTree (Object[][]: A DataTree composed by expected values)
	 * @param jsonCompare (org.json.JSONObject: The JSON Data to compare)
	 * @param strictMode (boolean: Mean if the process should be restricted)
	 * @param refactorMode (String: Set the mode of refactor - if required - to fix a JSON value)
	 * @param debug (boolean: Enable or Disable debug in the stdout)
	 * @throws Exception Exception (Exception: generic exception)
	 * @see <a href="https://github.com/huntercodexs/codexstester">Codexs Tester (GitHub)</a>
	 * @author huntercodexs (powered by jereelton-devel)
	 * */
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

	/**
	 * <h1 style="color: #FFFF00; font-size: 11px">codexsTesterCompareJsonFormat</h1>
	 *
	 * <p style="color: #CDCDCD">Data process to compare a JSON Data structure using QuickJson</p>
	 *
	 * <p>
	 *     This method can process a complex JSON Data structure to check and validate each field
	 *     affordable in the jsonCompare argument.
	 *     <br>
	 *     The values for refactorMode are: {none,easy,middle,regular,complex}
	 * </p>
	 *
	 * <p>Example</p>
	 *
	 * <blockquote><pre>
	 * public static Object[][] expectedJsonDataTree() {
	 *
	 * 		String string = "value1";
	 * 		QuickJson quickJson = new QuickJson();
	 * 		HeadersDto headersDto = new HeadersDto();
	 * 		HashMap&lt;Object, Object&gt; hashMap = new HashMap&lt;&gt;();
	 * 		ArrayList&lt;Object&gt; arrayList = new ArrayList&lt;&gt;();
	 * 		LinkedList&lt;Object&gt; linkedList = new LinkedList&lt;&gt;();
	 * 		List&lt;String&gt; list = new ArrayList&lt;&gt;();
	 * 		LinkedHashMap&lt;Object, Object&gt; linkedHashMap = new LinkedHashMap&lt;&gt;();
	 *
	 * 		return new Object[][]{
	 * 		    {"field1", string,        String.class},
	 * 		    {"field2", quickJson,     QuickJson.class},
	 * 		    {"field3", headersDto,    HeadersDto.class},
	 * 		    {"field4", hashMap,       HashMap.class},
	 * 		    {"field5", arrayList,     ArrayList.class},
	 * 		    {"field6", linkedList,    LinkedList.class},
	 * 		    {"field7", list,          List.class},
	 * 		    {"field8", linkedHashMap, LinkedHashMap.class}
	 * 		};
	 * }
	 *
	 * //SIMULATE RESPONSE SAMPLE
	 *  String string = "value1";
	 *  QuickJson quickJson = new QuickJson();
	 *  HeadersDto headersDto = new HeadersDto();
	 *  HashMap&lt;Object, Object&gt; hashMap = new HashMap&lt;&gt;();
	 *  ArrayList&lt;Object&gt; arrayList = new ArrayList&lt;&gt;();
	 *  LinkedList&lt;Object&gt; linkedList = new LinkedList&lt;&gt;();
	 *  List&lt;String&gt; list = new ArrayList&lt;&gt;();
	 *  LinkedHashMap&lt;Object, Object&gt; linkedHashMap = new LinkedHashMap&lt;&gt;();
	 *
	 *  QuickJson quickJsonResponseSimulate = new QuickJson();
	 *  quickJsonResponseSimulate.add("field1", string);
	 *  quickJsonResponseSimulate.add("field2", quickJson);
	 *  quickJsonResponseSimulate.add("field3", headersDto);
	 *  quickJsonResponseSimulate.add("field4", hashMap);
	 *  quickJsonResponseSimulate.add("field5", arrayList);
	 *  quickJsonResponseSimulate.add("field6", linkedList);
	 *  quickJsonResponseSimulate.add("field7", list);
	 *  quickJsonResponseSimulate.add("field8", linkedHashMap);
	 *
	 *  codexsTesterCompareJsonFormat(
	 *  		expectedJsonDataTree(),
	 *  		quickJsonResponseSimulate,
	 *  		true,
	 *  		"none",
	 *  		true);
	 * </pre></blockquote>
	 *
	 * @param expectedJsonDataTree (Object[][]: A DataTree composed by expected values)
	 * @param jsonCompare (QuickJson: The JSON Data to compare)
	 * @param strictMode (boolean: Mean if the process should be restricted)
	 * @param refactorMode (String: Set the mode of refactor - if required - to fix a JSON value)
	 * @param debug (boolean: Enable or Disable debug in the stdout)
	 * @throws Exception Exception (Exception: generic exception)
	 * @see <a href="https://github.com/huntercodexs/codexstester">Codexs Tester (GitHub)</a>
	 * @author huntercodexs (powered by jereelton-devel)
	 * */
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

	/**
	 * <h1 style="color: #FFFF00; font-size: 11px">codexsTesterCompareJsonFormat</h1>
	 *
	 * <p style="color: #CDCDCD">Data process to compare a JSON Data structure using Org JSON</p>
	 *
	 * <p>
	 *     This method can process a complex JSON Data structure to check and validate each field
	 *     affordable in the jsonCompare argument.
	 *     <br>
	 *     The values for refactorMode are: {none,easy,middle,regular,complex}
	 * </p>
	 *
	 * <p>Example</p>
	 *
	 * <blockquote><pre>
	 * public static String[] expectedJsonKeys() {
	 * 		return new String[]{"field1", "field2", "field3", "field4", "field5", "field6", "field7", "field8"};
	 * }
	 *
	 * public static Object[] expectedJsonValues() {
	 * 		String string = "value1";
	 * 		QuickJson quickJson = new QuickJson();
	 * 		HeadersDto headersDto = new HeadersDto();
	 * 		HashMap&lt;Object, Object&gt; hashMap = new HashMap&lt;&gt;();
	 * 		ArrayList&lt;Object&gt; arrayList = new ArrayList&lt;&gt;();
	 * 		LinkedList&lt;Object&gt; linkedList = new LinkedList&lt;&gt;();
	 * 		List&lt;String&gt; list = new ArrayList&lt;&gt;();
	 * 		LinkedHashMap&lt;Object, Object&gt; linkedHashMap = new LinkedHashMap&lt;&gt;();
	 * 		return new Object[]{string, quickJson, headersDto, hashMap, arrayList, linkedList, list, linkedHashMap};
	 * }
	 *
	 * public static Object[] expectedJsonTyped() {
	 * 		return new Object[]{
	 * 			String.class,
	 * 			QuickJson.class,
	 * 			HeadersDto.class,
	 * 			HashMap.class,
	 * 			ArrayList.class,
	 * 			LinkedList.class,
	 * 			List.class,
	 * 			LinkedHashMap.class
	 * 		};
	 * }
	 *
	 * //SIMULATE RESPONSE SAMPLE
	 * String string = "value1";
	 * QuickJson quickJson = new QuickJson();
	 * HeadersDto headersDto = new HeadersDto();
	 * HashMap&lt;Object, Object&gt; hashMap = new HashMap&lt;&gt;();
	 * ArrayList&lt;Object&gt; arrayList = new ArrayList&lt;&gt;();
	 * LinkedList&lt;Object&gt; linkedList = new LinkedList&lt;&gt;();
	 * List&lt;String&gt; list = new ArrayList&lt;&gt;();
	 * LinkedHashMap&lt;Object, Object&gt; linkedHashMap = new LinkedHashMap&lt;&gt;();
	 *
	 * org.json.JSONObject orgJsonResponseSimulate = new org.json.JSONObject();
	 * orgJsonResponseSimulate.put("field1", string);
	 * orgJsonResponseSimulate.put("field2", quickJson);
	 * orgJsonResponseSimulate.put("field3", headersDto);
	 * orgJsonResponseSimulate.put("field4", hashMap);
	 * orgJsonResponseSimulate.put("field5", arrayList);
	 * orgJsonResponseSimulate.put("field6", linkedList);
	 * orgJsonResponseSimulate.put("field7", list);
	 * orgJsonResponseSimulate.put("field8", linkedHashMap);
	 *
	 * codexsTesterCompareJsonFormat(
	 *  		expectedJsonKeys(),
	 *  		expectedJsonValues(),
	 *  		expectedJsonTyped(),
	 *  		orgJsonResponseSimulate,
	 *  		true,
	 *  		"none",
	 *  		true);
	 * </pre></blockquote>
	 *
	 * @param jsonKeys (Object[]: The expected JSON keys)
	 * @param jsonValues (Object[]: The expected JSON values)
	 * @param jsonTyped (Object[]: The expected JSON Data content types)
	 * @param jsonCompare (QuickJson: The JSON Data to compare)
	 * @param strictMode (boolean: Mean if the process should be restricted)
	 * @param refactorMode (String: Set the mode of refactor - if required - to fix a JSON value)
	 * @param debug (boolean: Enable or Disable debug in the stdout)
	 * @throws Exception Exception (Exception: generic exception)
	 * @see <a href="https://github.com/huntercodexs/codexstester">Codexs Tester (GitHub)</a>
	 * @author huntercodexs (powered by jereelton-devel)
	 * */
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

	/**
	 * <h1 style="color: #FFFF00; font-size: 11px">codexsTesterCompareJsonFormat</h1>
	 *
	 * <p style="color: #CDCDCD">Data process to compare a JSON Data structure using QuickJson</p>
	 *
	 * <p>
	 *     This method can process a complex JSON Data structure to check and validate each field
	 *     affordable in the jsonCompare argument.
	 *     <br>
	 *     The values for refactorMode are: {none,easy,middle,regular,complex}
	 * </p>
	 *
	 * <p>Example</p>
	 *
	 * <blockquote><pre>
	 * public static String[] expectedJsonKeys() {
	 * 		return new String[]{"field1", "field2", "field3", "field4", "field5", "field6", "field7", "field8"};
	 * }
	 *
	 * public static Object[] expectedJsonValues() {
	 * 		String string = "value1";
	 * 		QuickJson quickJson = new QuickJson();
	 * 		HeadersDto headersDto = new HeadersDto();
	 * 		HashMap&lt;Object, Object&gt; hashMap = new HashMap&lt;&gt;();
	 * 		ArrayList&lt;Object&gt; arrayList = new ArrayList&lt;&gt;();
	 * 		LinkedList&lt;Object&gt; linkedList = new LinkedList&lt;&gt;();
	 * 		List&lt;String&gt; list = new ArrayList&lt;&gt;();
	 * 		LinkedHashMap&lt;Object, Object&gt; linkedHashMap = new LinkedHashMap&lt;&gt;();
	 * 		return new Object[]{string, quickJson, headersDto, hashMap, arrayList, linkedList, list, linkedHashMap};
	 * }
	 *
	 * public static Object[] expectedJsonTyped() {
	 * 		return new Object[]{
	 * 			String.class,
	 * 			QuickJson.class,
	 * 			HeadersDto.class,
	 * 			HashMap.class,
	 * 			ArrayList.class,
	 * 			LinkedList.class,
	 * 			List.class,
	 * 			LinkedHashMap.class
	 * 		};
	 * }
	 *
	 * //SIMULATE RESPONSE SAMPLE
	 * String string = "value1";
	 * QuickJson quickJson = new QuickJson();
	 * HeadersDto headersDto = new HeadersDto();
	 * HashMap&lt;Object, Object&gt; hashMap = new HashMap&lt;&gt;();
	 * ArrayList&lt;Object&gt; arrayList = new ArrayList&lt;&gt;();
	 * LinkedList&lt;Object&gt; linkedList = new LinkedList&lt;&gt;();
	 * List&lt;String&gt; list = new ArrayList&lt;&gt;();
	 * LinkedHashMap&lt;Object, Object&gt; linkedHashMap = new LinkedHashMap&lt;&gt;();
	 *
	 * QuickJson quickJsonResponseSimulate = new QuickJson();
	 * quickJsonResponseSimulate.add("field1", string);
	 * quickJsonResponseSimulate.add("field2", quickJson);
	 * quickJsonResponseSimulate.add("field3", headersDto);
	 * quickJsonResponseSimulate.add("field4", hashMap);
	 * quickJsonResponseSimulate.add("field5", arrayList);
	 * quickJsonResponseSimulate.add("field6", linkedList);
	 * quickJsonResponseSimulate.add("field7", list);
	 * quickJsonResponseSimulate.add("field8", linkedHashMap);
	 *
	 * codexsTesterCompareJsonFormat(
	 *  		expectedJsonKeys(),
	 *  		expectedJsonValues(),Z
	 *  		expectedJsonTyped(),
	 *  		quickJsonResponseSimulate,
	 *  		true,
	 *  		"none",
	 *  		true);
	 * </pre></blockquote>
	 *
	 * @param jsonKeys (Object[]: The expected JSON keys)
	 * @param jsonValues (Object[]: The expected JSON values)
	 * @param jsonTyped (Object[]: The expected JSON Data content types)
	 * @param jsonCompare (QuickJson: The JSON Data to compare)
	 * @param strictMode (boolean: Mean if the process should be restricted)
	 * @param refactorMode (String: Set the mode of refactor - if required - to fix a JSON value)
	 * @param debug (boolean: Enable or Disable debug in the stdout)
	 * @throws Exception Exception (Exception: generic exception)
	 * @see <a href="https://github.com/huntercodexs/codexstester">Codexs Tester (GitHub)</a>
	 * @author huntercodexs (powered by jereelton-devel)
	 * */
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
