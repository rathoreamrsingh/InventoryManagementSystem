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
		new InventoryRunner().runner();
	}

	/**
	 * 
	 */
	private void runner() {
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

		float bill = 0;
		for (String order : ordersData) {
			bill = 0;
			for (String orderID : order.split(",")) {
				bill += new BillCalculator().getBill(
						priceSheet[((int) Integer.parseInt(orderID)) - 1].split(",")[1].trim(),
						priceSheet[((int) Integer.parseInt(orderID)) - 1].split(",")[2].trim(),
						Float.parseFloat(priceSheet[((int) Integer.parseInt(orderID)) - 1].split(",")[3].trim()));
			}
			System.out.println((int)bill);
		}
	}

}
