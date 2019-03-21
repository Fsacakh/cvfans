package com.auto.common;


public interface IEnum<T> {
	public T[] getValues();
	
	public T get(int Code);
}
