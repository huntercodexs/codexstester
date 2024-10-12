package com.huntercodexs.codexstester.util;

import com.huntercodexs.codexstester.ignition.CodexsTesterIgnition;
import com.huntercodexs.codexstester.resource.quickjson.QuickJson;

import java.lang.reflect.Field;

import static com.huntercodexs.codexstester.util.CodexsHelper.codexsHelperLogTermTests;
import static com.huntercodexs.codexstester.util.CodexsHelper.codexsHelperMd5;

public abstract class CodexsAdvanced extends CodexsTesterIgnition {

	protected String hash(Object data) {
		if (data == null || String.valueOf(data) == null || String.valueOf(data).equals("null")) return "DATA IS NULL";
		return codexsHelperMd5(String.valueOf(data));
	}

	protected void strictMessage(boolean flag) {
		if (flag) {
			codexsHelperLogTermTests(" Use strictMode = true if you don't want to ignore it", "", false);
		} else {
			codexsHelperLogTermTests(" Use strictMode = false if you want to ignore it", "", false);
		}
	}

	protected boolean isInterface(Object expType, Object fndType) {
		return String.valueOf(expType).contains("interface") || String.valueOf(fndType).contains("interface");
	}

	protected void defaultMessage(
			Object expectedValue,
			Object foundValue,
			Object expectedTyped,
			Object foundTyped,
			Object expectedName,
			Object foundName
	) {
		codexsHelperLogTermTests("  ==> EXPECTED NAME....", expectedName, false);
		codexsHelperLogTermTests("  ==> FOUND NAME.......", foundName, false);
		codexsHelperLogTermTests("  ==> EXPECTED TYPED...", expectedTyped, false);
		codexsHelperLogTermTests("  ==> FOUND TYPED......", foundTyped, false);
		codexsHelperLogTermTests("  ==> EXPECTED VALUE...", expectedValue, false);
		codexsHelperLogTermTests("  ==> FOUND VALUE......", foundValue, false);
	}

	protected String extractFields(Object[][] expectedDataTree, int index) {
		StringBuilder fields = new StringBuilder();
		for (Object[] objects : expectedDataTree) {
			fields.append(objects[index]);
			fields.append(", ");
		}
		return "[" + String.valueOf(fields).trim().replaceFirst(",$", "") + "]";
	}

	protected String dtoToJson(Object dtoCompare, int sizeCompare) {

		Field[] fieldsCompare = dtoCompare.getClass().getDeclaredFields();
		StringBuilder dtoFields = new StringBuilder();
		for (Field field : fieldsCompare) {
			dtoFields.append(field.getName());
			dtoFields.append("=|");
		}
		//System.out.println(String.valueOf(dtoFields).replaceAll("\\|$", ""));

		String[] dtoValues = String.valueOf(dtoCompare)
				.replaceFirst("^[0-9a-zA-Z]+\\(", "")
				.replaceFirst("\\)$", "")
				.split(String.valueOf(dtoFields).replaceAll("\\|$", ""));
		//System.out.println(Arrays.toString(dtoValues));

		String[] arrayCompare = new String[sizeCompare];
		int n = 0;
		for (String dtoValue : dtoValues) {
			String val = dtoValue.trim().replaceFirst(",$", "");
			if (!val.equals("")) {
				arrayCompare[n] = val;
				n++;
			}
		}
		//System.out.println(Arrays.toString(arrayCompare));

		QuickJson quickJson = new QuickJson();
		for (int i = 0; i < arrayCompare.length; i++) {
			quickJson.add(fieldsCompare[i].getName(), arrayCompare[i]);
		}
		//System.out.println(dtoMapped);

		return quickJson.json();
	}

	protected Object dtoExtractor(Object dtoToExtract, String fieldToExtract) {

		String[] packages = dtoToExtract.getClass().getName().split("\\.");
		String className = packages[packages.length-1];
		String data = String.valueOf(dtoToExtract)
				.replaceFirst("^" + className + "\\(", "")
				.replaceFirst("\\)$", "");
		String[] fieldValue = data.split(", ");

		for (String field : fieldValue) {

			field = field.replaceFirst("=", "@CUT@");
			String[] split = field.split("@CUT@");

			if (split[0].equals(fieldToExtract)) {
				return split[1];
			}
		}

		throw new RuntimeException("[Critical Error] Field not found " + fieldToExtract);
	}

}
