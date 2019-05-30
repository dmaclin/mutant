package com.mutant.ws.rest.model;

public class ServicioException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServicioException(String string, Throwable throwable, boolean b, boolean b1) {
        super(string, throwable, b, b1);
    }

    public ServicioException(Throwable throwable) {
        super(throwable);
    }

    public ServicioException(String string, Throwable throwable) {
        super(string, throwable);
    }

    public ServicioException(String string) {
        super(string);
    }

    public ServicioException() {
        super();
    }
}
