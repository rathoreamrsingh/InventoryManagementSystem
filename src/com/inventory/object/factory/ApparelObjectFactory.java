/**
 * 
 */
package com.inventory.object.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
		brandName = brandName.trim();

		switch (brandName) {
		case "Wrangler":
			brandObject = new Wrangler("Wrangler", getCode(brandName));
			break;
		case "Arrow":
			brandObject = new Arrow("Arrow", getCode(brandName));
			break;
		case "Vero Moda":
			brandObject = new VeroModa("Vero Moda", getCode(brandName));
			break;
		case "UCB":
			brandObject = new UCB("UCB", getCode(brandName));
			break;
		case "Adidas":
			brandObject = new Adidas("Adidas", getCode(brandName));
			break;
		case "Provogue":
			brandObject = new Provogue("Provogue", getCode(brandName));
			break;
		}

		return brandObject;
	}

	private ProductCategory getProductObject(String productName) {
		ProductCategory productObject = null;
		productName = productName.trim();
		switch (productName) {
		case "Shirts":
			productObject = new Shirt("Shirts", getCode(productName));
			break;
		case "Casuals":
			productObject = new Casuals("Casuals", getCode(productName));
			break;
		case "Dresses":
			productObject = new Dresses("Dresses", getCode(productName));
			break;
		case "Footwear":
			productObject = new Footwear("Footwear", getCode(productName));
			break;
		case "Jeans":
			productObject = new Jeans("Jeans", getCode(productName));
			break;
		case "Trousers":
			productObject = new Trousers("Trousers", getCode(productName));
			break;

		}
		return productObject;
	}

	private ApparelCategory getCategoryObject(String categoryName) {
		ApparelCategory categoryObject = null;
		switch (categoryName) {
		case "Men":
			categoryObject = new Men("Men", getCode(categoryName));
			break;
		case "Women":
			categoryObject = new Men("women", categoryName);
			break;
		}
		return categoryObject;
	}

	private String getCode(String name) {
		name = name.trim().replaceAll(" ", "_%2_");
		String code = "";
		try {
			File file = new File("productAndCategoryMap.properties");
			FileInputStream fileInput = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(fileInput);
			fileInput.close();
			Enumeration enuKeys = properties.keys();
			while (enuKeys.hasMoreElements()) {
				String key = (String) enuKeys.nextElement();
				String value = properties.getProperty(key);
				if (key.equalsIgnoreCase(name)) {
					code = value;
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
		return code;
	}
	
	public String getClass(String code){
		String className = "";
		code = code.trim();
		try {
			File file = new File("classMapping.properties");
			FileInputStream fileInput = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(fileInput);
			fileInput.close();
			Enumeration enuKeys = properties.keys();
			while (enuKeys.hasMoreElements()) {
				String key = (String) enuKeys.nextElement();
				String value = properties.getProperty(key);
				if (key.equalsIgnoreCase(code)) {
					className = value;
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
		
		return className;
	}
}
