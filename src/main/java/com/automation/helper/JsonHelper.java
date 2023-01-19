package com.automation.helper;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonHelper {
	JsonElement root;

	public JsonHelper(String fileName) throws Exception {

		String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
				+ File.separator + "resources" + File.separator + fileName;
		this.root = new JsonParser().parse(new FileReader(filePath));
	}

	public JsonHelper(String fileName, String folderName) throws Exception {

		String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
				+ File.separator + "resources" + File.separator + folderName + File.separator + fileName;
		this.root = new JsonParser().parse(new FileReader(filePath));
	}

	public Map<String, String> readJsonElement(String elementName) throws Exception {
		Map<String, String> testDataMap = new HashMap();
		JsonObject jsonObject = root.getAsJsonObject();
		JsonElement some = jsonObject.get(elementName);
		JsonObject testData = some.getAsJsonObject();
		for (Map.Entry<String, JsonElement> entry : testData.entrySet()) {
			testDataMap.put(entry.getKey().toString(), entry.getValue().getAsString());
		}
		return testDataMap;
	}

}
