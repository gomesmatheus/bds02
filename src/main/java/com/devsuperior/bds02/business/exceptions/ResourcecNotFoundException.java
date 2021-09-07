package com.devsuperior.bds02.business.exceptions;

public class ResourcecNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ResourcecNotFoundException(String msg) {
		super(msg);
	}
}
