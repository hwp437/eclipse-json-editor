package com.mmi.dreampark.editor.model;

import java.util.Map;

/**
 * ApplicationObject is a model class object containing all the key value pair or nested key value pair.
 * This will be used in three ways:-
 * 1. As a repository holding all the top level nodes
 * 2. Key value pair
 * 3. Nested key value pair (tree structure). In this case the property value will be set to null.
 * @author HWP437
 *
 */
public class ApplicationObject {
	/*Holding the type of object. key-value pair or tree*/
	private int type;
	/*will hold mappings of property values*/
	private Map<String, ApplicationObject> properties;
	/*Key of this object*/
	private String key;
	/*Value of this object*/
	private String value;

	private final int ROOT = 0x0000;
	private final int KEY_VALUE_PAIR = 0x0001;
	private final int NESTED_KEY_VALUE_PAIR = 0x0002;
	private final int ARRAY = 0x0003;

	/**
	 * constructor used for key value pair
	 * @param key
	 * @param value
	 */
	public ApplicationObject(String key, String value) {
		this.type = KEY_VALUE_PAIR;
		this.key = key;
		this.value = value;
		this.properties = null;
	}

	/**
	 * constructor used for Nested key value pair
	 * @param key
	 * @param properties
	 */
	public ApplicationObject(String key, Map<String, ApplicationObject> properties) {
		this.type = NESTED_KEY_VALUE_PAIR;
		this.key = key;
		this.value = null;
		this.properties = properties;
	}

	/**
	 * constructor used for storing the top level json objects. This will be used just once for every json file
	 * @param properties
	 */
	public ApplicationObject(Map<String, ApplicationObject> properties) {
		this.type = ROOT;
		this.key = null;
		this.value = null;
		this.properties = properties;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
}
