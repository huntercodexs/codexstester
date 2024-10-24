package com.huntercodexs.codexstester.util;

import org.junit.Assert;

import java.lang.reflect.Field;
import java.util.Arrays;

import static com.huntercodexs.codexstester.util.CodexsAssertion.resulted;
import static com.huntercodexs.codexstester.util.CodexsHelper.codexsHelperLogTermTests;

public abstract class CodexsDtoComparator extends CodexsListComparator {

	protected void codexsTesterCompareDtoFormat(
			String[] dtoValues,
			Object dtoToCompare,
			Class<?> dtoClass,
			boolean strictMode
	) {

		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement element = stackTrace[2];

		codexsHelperLogTermTests("======== SUMMARY =======", "", true);
		codexsHelperLogTermTests("VALUES..................", Arrays.toString(dtoValues), false);
		codexsHelperLogTermTests("CLASS...................", dtoClass, false);
		codexsHelperLogTermTests("CLASS-FIELDS............", Arrays.toString(dtoClass.getDeclaredFields()), false);
		codexsHelperLogTermTests("CLASS-FIELDS-LENGTH.....", dtoClass.getDeclaredFields().length, false);
		codexsHelperLogTermTests("COMPARE.................", dtoToCompare, false);
		codexsHelperLogTermTests("COMPARE-GET-CLASS.......", dtoToCompare.getClass(), false);
		codexsHelperLogTermTests("COMPARE-TO-STRING.......", String.valueOf(dtoToCompare), false);
		codexsHelperLogTermTests("COMPARE-FIELDS..........", Arrays.toString(dtoToCompare.getClass().getFields()), false);
		codexsHelperLogTermTests("COMPARE-DECLARED-FIELDS.", Arrays.toString(dtoToCompare.getClass().getDeclaredFields()), false);
		codexsHelperLogTermTests("COMPARE-LENGTH..........", dtoToCompare.getClass().getDeclaredFields().length, false);
		codexsHelperLogTermTests("STRICT-MODE.............", strictMode, false);
		codexsHelperLogTermTests("CLASS-TYPE-NAME.........", String.valueOf(dtoToCompare.getClass()), false);
		codexsHelperLogTermTests("======== COMPARE =======", "", true);

		if (dtoClass != dtoToCompare.getClass() && strictMode) {
			codexsHelperLogTermTests("**** ERROR ****", "", true);
			codexsHelperLogTermTests("ERROR ON DTO DATA COMPARE (WRONG-DTO-CLASS)", "", false);
			codexsHelperLogTermTests("  ==> EXPECTED...: ", dtoClass, false);
			codexsHelperLogTermTests("  ==> RECEIVED...: ", dtoToCompare.getClass(), false);
			resulted(false, element);
			Assert.fail();
		}

		int sizeClass = dtoClass.getDeclaredFields().length;
		int sizeCompare = dtoToCompare.getClass().getDeclaredFields().length;

		if (sizeClass != sizeCompare) {
			codexsHelperLogTermTests("**** ERROR ****", "", true);
			codexsHelperLogTermTests("ERROR ON DTO DATA COMPARE (WRONG-DTO-CLASS-LENGTH)", "", false);
			codexsHelperLogTermTests("  ==> CLASS......", dtoClass, false);
			codexsHelperLogTermTests("  ==> COMPARE....", dtoToCompare.getClass(), false);
			codexsHelperLogTermTests("  ==> EXPECTED...", sizeClass, false);
			codexsHelperLogTermTests("  ==> RECEIVED...", sizeCompare, false);
			resulted(false, element);
			Assert.fail();
		}

		Field[] dtoFieldsClass = dtoClass.getDeclaredFields();
		Field[] dtoFieldsCompare = dtoToCompare.getClass().getDeclaredFields();

		for (int i = 0; i < dtoFieldsClass.length; i++) {

			String expName = dtoFieldsClass[i].getName();
			String fndName = dtoFieldsCompare[i].getName();
			String expVal = dtoValues[i];
			Object fndVal = dtoExtractor(dtoToCompare, expName);
			Class<?> expType = dtoFieldsClass[i].getType();
			Class<?> fndType = dtoFieldsCompare[i].getType();

			codexsHelperLogTermTests("===> INDEX <=== ", i, true);
			codexsHelperLogTermTests("EXPECTED NAME...", expName, false);
			codexsHelperLogTermTests("FOUND NAME......", fndName, false);
			codexsHelperLogTermTests("EXPECTED TYPE...", expType, false);
			codexsHelperLogTermTests("FOUND TYPE......", fndType, false);
			codexsHelperLogTermTests("EXPECTED VALUE..", expVal, false);
			codexsHelperLogTermTests("FOUND VALUE.....", fndVal, false);

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

	protected void codexsTesterCompareDtoFormat(
			Object[][] expectedDtoDataTree,
			Object dtoToCompare,
			Class<?> dtoClass,
			boolean strictMode
	) {

		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement element = stackTrace[2];

		codexsHelperLogTermTests("=========== SUMMARY =========", "", true);
		codexsHelperLogTermTests("FIELDS.......................", extractFields(expectedDtoDataTree, 0), false);
		codexsHelperLogTermTests("VALUES.......................", extractFields(expectedDtoDataTree, 1), false);
		codexsHelperLogTermTests("TYPES........................", extractFields(expectedDtoDataTree, 2), false);
		codexsHelperLogTermTests("CLASS........................", dtoClass, false);
		codexsHelperLogTermTests("DTO-CLASS-FIELDS.............", Arrays.toString(dtoClass.getDeclaredFields()), false);
		codexsHelperLogTermTests("DTO-CLASS-FIELDS-LENGTH......", dtoClass.getDeclaredFields().length, false);
		codexsHelperLogTermTests("DTO-COMPARE..................", dtoToCompare, false);
		codexsHelperLogTermTests("DTO-COMPARE-GET-CLASS........", dtoToCompare.getClass(), false);
		codexsHelperLogTermTests("DTO-COMPARE-TO-STRING........", String.valueOf(dtoToCompare), false);
		codexsHelperLogTermTests("DTO-COMPARE-FIELDS...........", Arrays.toString(dtoToCompare.getClass().getFields()), false);
		codexsHelperLogTermTests("DTO-COMPARE-DECLARED-FIELDS..", Arrays.toString(dtoToCompare.getClass().getDeclaredFields()), false);
		codexsHelperLogTermTests("DTO-COMPARE-LENGTH...........", dtoToCompare.getClass().getDeclaredFields().length, false);
		codexsHelperLogTermTests("STRICT-MODE..................", strictMode, false);
		codexsHelperLogTermTests("CLASS-TYPE-NAME..............", String.valueOf(dtoToCompare.getClass()), false);
		codexsHelperLogTermTests("=========== COMPARE =========", "", true);

		if (dtoClass != dtoToCompare.getClass() && strictMode) {
			codexsHelperLogTermTests("**** ERROR ****", "", true);
			codexsHelperLogTermTests("ERROR ON DTO DATA COMPARE (WRONG-DTO-CLASS)", "", false);
			codexsHelperLogTermTests("  ==> EXPECTED...: ", dtoClass, false);
			codexsHelperLogTermTests("  ==> RECEIVED...: ", dtoToCompare.getClass(), false);
			resulted(false, element);
			Assert.fail();
		}

		int sizeClass = dtoClass.getDeclaredFields().length;
		int sizeCompare = dtoToCompare.getClass().getDeclaredFields().length;
		int sizeDataTree = expectedDtoDataTree.length;

		if (sizeClass != sizeCompare || sizeClass != sizeDataTree) {
			codexsHelperLogTermTests("**** ERROR ****", "", true);
			codexsHelperLogTermTests("ERROR ON DTO DATA COMPARE (WRONG-DTO-CLASS-LENGTH)", "", false);
			codexsHelperLogTermTests("  ==> CLASS..................", dtoClass, false);
			codexsHelperLogTermTests("  ==> COMPARE................", dtoToCompare.getClass(), false);
			codexsHelperLogTermTests("  ==> EXPECTED-SIZE-CLASS....", sizeClass, false);
			codexsHelperLogTermTests("  ==> RECEIVED-DTO-COMPARE...", sizeCompare, false);
			codexsHelperLogTermTests("  ==> RECEIVED-DATA-TREE.....", sizeDataTree, false);
			resulted(false, element);
			Assert.fail();
		}

		Field[] dtoFieldsClass = dtoClass.getDeclaredFields();

		for (int i = 0; i < expectedDtoDataTree.length; i++) {

			String expName = String.valueOf(expectedDtoDataTree[i][0]);
			String fndName = dtoFieldsClass[i].getName();
			String expVal = String.valueOf(expectedDtoDataTree[i][1]);
			Object fndVal = dtoExtractor(dtoToCompare, expName);
			Class<?> expType = (Class<?>) expectedDtoDataTree[i][2];
			Class<?> fndType = dtoFieldsClass[i].getType();

			codexsHelperLogTermTests("===> INDEX <=== ", i, true);
			codexsHelperLogTermTests("EXPECTED NAME...", expName, false);
			codexsHelperLogTermTests("FOUND NAME......", fndName, false);
			codexsHelperLogTermTests("EXPECTED TYPE...", expType, false);
			codexsHelperLogTermTests("FOUND TYPE......", fndType, false);
			codexsHelperLogTermTests("EXPECTED VALUE..", expVal, false);
			codexsHelperLogTermTests("FOUND VALUE.....", fndVal, false);

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
