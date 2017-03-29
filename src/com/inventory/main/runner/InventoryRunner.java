/**
 * 
 */
package com.inventory.main.runner;

import java.util.Scanner;

import com.inventory.bill.calculator.BillCalculator;

/**
 * @author amar
 *
 */
public class InventoryRunner {
	public static void main(String[] args) {
		int input = 0;
		Scanner in = new Scanner(System.in);
		input = in.nextInt();
		in.nextLine();
		String[] priceSheet = new String[input];
		for (int i = 0; i < input; i++) {
			priceSheet[i] = in.nextLine();
		}

		int orders = in.nextInt();
		String[] ordersData = new String[orders];
		in.nextLine();
		for (int i = 0; i < orders; i++) {
			ordersData[i] = in.nextLine();
		}
		new InventoryRunner().runner(priceSheet, ordersData);
	}

	/**
	 * 
	 */
	public int[] runner(String[] priceSheet, String[] ordersData) {
		int[] result = new int[ordersData.length];
		float bill = 0;
		int counter = 0;
		for (String order : ordersData) {
			bill = 0;
			for (String orderID : order.split(",")) {
				bill += new BillCalculator().getBill(
						priceSheet[((int) Integer.parseInt(orderID)) - 1].split(",")[1].trim(),
						priceSheet[((int) Integer.parseInt(orderID)) - 1].split(",")[2].trim(),
						Float.parseFloat(priceSheet[((int) Integer.parseInt(orderID)) - 1].split(",")[3].trim()));
			}
			System.out.println((int) bill);
			result[counter] = (int) bill;
		}
		return result;
	}
}
