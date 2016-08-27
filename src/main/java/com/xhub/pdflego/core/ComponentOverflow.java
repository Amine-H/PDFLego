package com.xhub.pdflego.core;

/**
 * ComponentOverflow is a RuntimeException that is thrown
 * when a {@link Component} has a bigger size than it's parent
 * @author Amine Hakkou
 */
public class ComponentOverflow extends RuntimeException{
	private static final long serialVersionUID = -759974799499339844L;

	public ComponentOverflow(){
		super("Child Component is Overflowing parent.");
	}
}
