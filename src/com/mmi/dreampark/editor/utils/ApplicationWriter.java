package com.mmi.dreampark.editor.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.stream.JsonWriter;
import com.mmi.dreampark.editor.model.ApplicationObject;

public class ApplicationWriter {

	private JsonWriter jsonWriter;

	public ApplicationWriter() {

	}

	public void doIOWrite(ApplicationObject object) throws IOException {
		jsonWriter = new JsonWriter(new FileWriter("C:\\Users\\HWP437\\learning\\test.json"));

		/*"object was null"*/
		if(object != null) {
			writeJsonRootStream(object);
		}

		jsonWriter.close();
	}

	private void writeJsonStream(ApplicationObject object) throws IOException {

	    switch(object.getType()) {
	    case ApplicationObject.ROOT:
	    	writeJsonRootStream(object);
	    	break;

	    case ApplicationObject.KEY_VALUE_OBJECT:
	    	writeJsonKeyValueObjectStream(object);
	    	break;

	    case ApplicationObject.NESTED_KEY_VALUE_OBJECT:
	    	writeJsonNestedObjectStream(object);
	    	break;

	    case ApplicationObject.ARRAY:
	    	writeJsonArrayObjectStream(object);
	    	break;

	    case ApplicationObject.ARRAY_VALUE:
	    	writeJsonArrayValueStream(object);
	    	break;

	    default:
	    	return;
		}

	}

	private void writeJsonRootStream(ApplicationObject root) throws IOException {
		Map<String, ApplicationObject> jsonObjects = root.getProperties();

		/*No json objects. Empty json file*/
		if(jsonObjects == null || jsonObjects.isEmpty()) {
			return;
		}

		Set<String> keys = jsonObjects.keySet();

		for(String key : keys) {
			ApplicationObject object = jsonObjects.get(key);
			writeJsonStream(object);
		}

	}

	private void writeJsonKeyValueObjectStream(ApplicationObject keyValueObject) throws IOException {
		 jsonWriter.beginObject();

	     jsonWriter.name(keyValueObject.getKey()).value(keyValueObject.getValue());

	     jsonWriter.endObject();
	}

	private void writeJsonNestedObjectStream(ApplicationObject nestedObject) throws IOException {
		jsonWriter.beginObject();

		jsonWriter.name(nestedObject.getKey());
		writeJsonRootStream(nestedObject);

		jsonWriter.endObject();
	}

	private void writeJsonArrayObjectStream(ApplicationObject arrayObject) throws IOException{
		jsonWriter.beginObject();

		jsonWriter.name(arrayObject.getKey());
		List<ApplicationObject> values = arrayObject.getValues();
		if(values == null || values.isEmpty()) {
			jsonWriter.nullValue();
		}

		else {
			writeJsonArrayValuesStream(values);
		}

		jsonWriter.endObject();
	}

	private void writeJsonArrayValuesStream(List<ApplicationObject> arrayValues) throws IOException {
		jsonWriter.beginArray();

		for(ApplicationObject object : arrayValues) {
			writeJsonStream(object);
		}

		jsonWriter.endArray();
	}

	private void writeJsonArrayValueStream(ApplicationObject object) throws IOException {
		jsonWriter.value(object.getValue());
	}
}
