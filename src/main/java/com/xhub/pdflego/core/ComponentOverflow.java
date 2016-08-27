package com.xhub.pdflego.core;

public class ComponentOverflow extends RuntimeException{
	private static final long serialVersionUID = -759974799499339844L;

	public ComponentOverflow(){
		super("Child Component is Overflowing parent.");
	}
}
