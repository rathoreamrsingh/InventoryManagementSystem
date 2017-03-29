/**
 * 
 */
package com.inventory.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

/**
 * @author amar
 *
 */
public abstract class ApparelImpl implements Apparel {
	private String name;
	private String code;

	/**
	 * 
	 */
	public ApparelImpl(String name, String code) {
		this.name = name;
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 
	 * @return
	 */
	@Override
	public float getDiscount() {
		float discount = 0.0f;
		try {
			File file = new File("discountList.properties");
			FileInputStream fileInput = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(fileInput);
			fileInput.close();
			Enumeration enuKeys = properties.keys();
			while (enuKeys.hasMoreElements()) {
				String key = (String) enuKeys.nextElement();
				String value = properties.getProperty(key);
				if (key.equalsIgnoreCase(code)) {
					discount = Float.parseFloat(value);
					break;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		return discount;
	}

}
