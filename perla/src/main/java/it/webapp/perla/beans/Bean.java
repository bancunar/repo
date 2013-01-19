package it.webapp.perla.beans;

import java.io.Serializable;

public class Bean implements Serializable, Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
