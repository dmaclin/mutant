package com.mutant.ws.rest.model;

public final class ExpertoEstadisticas {
	ExpertoPersistencia expertoPersistencia;
	
	public Estadistica obtenerEstadisticas() throws ServicioException {
		expertoPersistencia = new ExpertoPersistencia();
		return expertoPersistencia.obtenerEstadisticas();
	}	
}