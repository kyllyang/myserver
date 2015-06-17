package org.kyll.lighter.base.common;

/**
 * User: Kyll
 * Date: 2014-11-08 13:01
 */
public interface BaseService<T, P> {
	T get(P p);
}
