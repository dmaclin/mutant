package com.mutant.ws.rest.model;

public class RespuestaServicio <T> {
	private String excepcion;
	private boolean huboExcepcion;
	private T resultado;
	public String getExcepcion() {
		return excepcion;
	}
	public void setExcepcion(String excepcion) {
		this.excepcion = excepcion;
	}
	public boolean isHuboExcepcion() {
		return huboExcepcion;
	}
	public void setHuboExcepcion(boolean huboExcepcion) {
		this.huboExcepcion = huboExcepcion;
	}
	public T getResultado() {
		return resultado;
	}
	public void setResultado(T resultado) {
		this.resultado = resultado;
	}

}
