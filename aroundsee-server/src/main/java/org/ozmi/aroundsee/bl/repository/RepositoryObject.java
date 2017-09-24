package org.ozmi.aroundsee.bl.repository;

public abstract class RepositoryObject {
	protected RepositoryImpl _repository;
	
	public RepositoryObject(RepositoryImpl implementation) {
		_repository = implementation;
	}
}
