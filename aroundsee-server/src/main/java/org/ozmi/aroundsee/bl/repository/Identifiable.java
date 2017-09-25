package org.ozmi.aroundsee.bl.repository;

import org.bson.types.ObjectId;

public interface Identifiable {
	public ObjectId get_id();
	public void set_id(ObjectId id);
}
