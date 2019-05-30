package com.mutant.ws.rest.model;

public final class ExpertoEstadisticas {
	
	public Estadistica obtenerEstadisticas() throws ServicioException {
		return ExpertoPersistencia.getInstancia().obtenerEstadisticas();
	}	
}
