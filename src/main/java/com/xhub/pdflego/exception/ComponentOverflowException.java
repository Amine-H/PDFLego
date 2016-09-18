package com.xhub.pdflego.exception;

import com.xhub.pdflego.core.Component;

/**
 * ComponentOverflowException is a RuntimeException that is thrown
 * when a {@link Component} has a bigger size than it's parent
 * @author Amine Hakkou
 */
public class ComponentOverflowException extends RuntimeException{
	private static final long serialVersionUID = -759974799499339844L;

	public ComponentOverflowException(){
		super("Child Component is Overflowing parent.");
	}
}
