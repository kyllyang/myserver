package org.kyll.myserver.base.util;

/**
 * User: Kyll
 * Date: 2015-07-15 14:35
 */
public final class NumericalUtils {
	public static double toPrimitive(Double value) {
		return value == null ? 0 : value;
	}

	private NumericalUtils() {
	}
}
