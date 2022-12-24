package org.exposeproject.config;

@FunctionalInterface
public interface Starter<W, T, R> {

	public default void print(String msg) {
		System.out.print("What!!");
	}

	public W start(T t, R r);
}
