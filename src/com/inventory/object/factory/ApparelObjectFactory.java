/**
 * 
 */
package com.inventory.object.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.Properties;

import com.inventory.brands.Adidas;
import com.inventory.brands.Arrow;
import com.inventory.brands.Provogue;
import com.inventory.brands.UCB;
import com.inventory.brands.VeroModa;
import com.inventory.brands.Wrangler;
import com.inventory.categories.Men;
import com.inventory.core.Apparel;
import com.inventory.core.ApparelCategory;
import com.inventory.core.ProductCategory;
import com.inventory.products.Casuals;
import com.inventory.products.Dresses;
import com.inventory.products.Footwear;
import com.inventory.products.Jeans;
import com.inventory.products.Shirt;
import com.inventory.products.Trousers;

/**
 * @author amar
 *
 */
public class ApparelObjectFactory {
	public Apparel getObject(String brandName, String productName) {
		Apparel output = null;

		Apparel brand = getBrandObject(brandName);
		ProductCategory product = getProductObject(productName);
		ApparelCategory category = productName.equalsIgnoreCase("Dresses") || productName.equalsIgnoreCase("Footwear")
				? getCategoryObject("Women") : getCategoryObject("Men");

		product.setApparel(brand);
		category.setApparel(product);

		output = category;

		return output;
	}

	private Apparel getBrandObject(String brandName) {
		Apparel brandObject = null;
		brandName = brandName.trim().replaceAll(" ", "_%2_");
		String className = readFromPropertyFile("classMapping.properties", getCode(brandName));
		Object obj = convertClassNameToInstance(className, brandName, getCode(brandName));

		brandObject = obj instanceof Apparel ? (Apparel) obj : null;

		return brandObject;
	}

	private ProductCategory getProductObject(String productName) {
		ProductCategory productObject = null;
		productName = productName.trim();

		String className = readFromPropertyFile("classMapping.properties", productName);
		Object obj = convertClassNameToInstance(className, productName, getCode(productName));

		productObject = obj instanceof ProductCategory ? (ProductCategory) obj : null;
		return productObject;
	}

	private ApparelCategory getCategoryObject(String categoryName) {
		ApparelCategory categoryObject = null;

		String className = readFromPropertyFile("classMapping.properties", categoryName);
		Object obj = convertClassNameToInstance(className, categoryName, getCode(categoryName));

		categoryObject = obj instanceof ApparelCategory ? (ApparelCategory) obj : null;
		return categoryObject;
	}

	private String getCode(String name) {
		name = name.trim().replaceAll(" ", "_%2_");
		return readFromPropertyFile("productAndCategoryMap.properties", name);
	}

	public String getClass(String code) {
		code = code.trim();
		return readFromPropertyFile("classMapping.properties", code);
	}

	private String readFromPropertyFile(String fileName, String searchString) {
		String output = "";
		fileName = fileName.trim();
		searchString = searchString.trim();
		try {
			File file = new File(fileName);
			FileInputStream fileInput = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(fileInput);
			fileInput.close();
			Enumeration enuKeys = properties.keys();
			while (enuKeys.hasMoreElements()) {
				String key = (String) enuKeys.nextElement();
				String value = properties.getProperty(key);
				if (key.equalsIgnoreCase(searchString)) {
					output = value;
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
		return output;
	}

	private Object convertClassNameToInstance(String className, String name, String code) {
		Object obj = null;
		Class<?> clazz;
		try {
			clazz = Class.forName(className);
			Constructor<?> ctor = clazz.getConstructor(String.class, String.class);
			obj = ctor.newInstance(new Object[] { name, code });
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return obj;
	}
}
