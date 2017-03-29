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
public abstract class ApparelCategory extends ApparelImpl {
	private Apparel apparel;

	/**
	 * @param name
	 * @param code
	 */
	public ApparelCategory(String name, String code) {
		super(name, code);
	}

	/**
	 * @return the apparel
	 */
	public Apparel getApparel() {
		return apparel;
	}

	/**
	 * @param apparel
	 *            the apparel to set
	 */
	public void setApparel(Apparel apparel) {
		this.apparel = apparel;
	}

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
				if (key.equalsIgnoreCase(getCode())) {
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
		return getApparel().getDiscount() > discount ? getApparel().getDiscount() : discount;
	}

}
