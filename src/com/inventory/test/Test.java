/**
 * 
 */
package com.inventory.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.inventory.object.factory.ApparelObjectFactory;

/**
 * @author amar
 *
 */
public class Test {
	public static void main(String[] args) {
		Class<?> clazz;
		try {
			clazz = Class.forName("com.inventory.test.Test2");
			Constructor<?> ctor = clazz.getConstructor(String.class, String.class);
			Object object = ctor.newInstance(new Object[] { "name", "test" });
			Test2 test = (Test2) object;
			test.printIt();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println(new ApparelObjectFactory().getClass("wrangler"));

	}
}
