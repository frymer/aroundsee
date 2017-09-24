package org.ozmi.aroundsee.bl.repository;

import java.util.List;

public interface Repository<T> {	
	public List<T> all() throws Throwable;

	public void update(T object) throws Throwable;
	
	public void create(T object) throws Throwable;

	public T read(Object id) throws Throwable;

	public void delete(Object id) throws Throwable;
	
	public List<T> query(String query) throws Throwable;
}