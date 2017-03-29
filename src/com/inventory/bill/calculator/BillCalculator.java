/**
 * 
 */
package com.inventory.bill.calculator;

import com.inventory.core.Apparel;
import com.inventory.object.factory.ApparelObjectFactory;

/**
 * @author amar
 *
 */
public class BillCalculator {
	public float getBill(String brandName, String productName, float price) {
		float bill = 0;
		Apparel object = new ApparelObjectFactory().getObject(brandName, productName);
		bill = price - ((price * object.getDiscount()) / 100);
		return bill;
	}
}
