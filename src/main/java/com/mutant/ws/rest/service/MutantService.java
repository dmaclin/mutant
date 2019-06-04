package com.mutant.ws.rest.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mutant.ws.rest.model.Estadistica;
import com.mutant.ws.rest.model.ExpertoEstadisticas;
import com.mutant.ws.rest.model.ExpertoMutante;
import com.mutant.ws.rest.model.RespuestaServicio;
import com.mutant.ws.rest.model.SecuenciaADN;
import com.mutant.ws.rest.model.ServicioException;
import com.mutant.ws.rest.model.Validador;

@Path("/mutantService")
public class MutantService {
	private ExpertoMutante expertoMutante;
	private ExpertoEstadisticas expertoEstadisticas;
	
	@POST
	@Path("/mutant")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Response isMutant(SecuenciaADN secuencia) {
		RespuestaServicio<Boolean> respuesta = new RespuestaServicio<Boolean>();
		String[] dna = new String[secuencia.getDna().size()];
		dna = secuencia.getDna().toArray(dna);
		try {
			if(dna != null && Validador.validarMatriz(dna)) {
				expertoMutante = new ExpertoMutante();
				if(expertoMutante.isMutant(dna)) {
					respuesta.setResultado(true);
					respuesta.setHuboExcepcion(false);
					respuesta.setExcepcion("");
					expertoMutante.guardarADN(dna, true);
					return Response.status(200).entity(respuesta).build();
				}
			}
			expertoMutante.guardarADN(dna, false);
			respuesta.setResultado(false);
			respuesta.setHuboExcepcion(false);
			respuesta.setExcepcion("");
			return Response.status(403).entity(respuesta).build();
		}catch(ServicioException ex) {
			respuesta.setResultado(false);
			respuesta.setHuboExcepcion(true);
			respuesta.setExcepcion(ex.getMessage());
			return Response.status(500).entity(respuesta).build();
		}
	}
	
	@GET
	@Path("/stats")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Response obtenerEstadisticas() {
		RespuestaServicio<Estadistica> respuesta = new RespuestaServicio<Estadistica>();
		Estadistica estadistica = new Estadistica();
		expertoEstadisticas = new ExpertoEstadisticas();
		try {
			estadistica = expertoEstadisticas.obtenerEstadisticas();
			if(estadistica != null) {
				respuesta.setResultado(estadistica);
				respuesta.setHuboExcepcion(false);
				respuesta.setExcepcion("");
				return Response.status(200).entity(respuesta).build();
			}else {
				respuesta.setResultado(estadistica);
				respuesta.setHuboExcepcion(true);
				respuesta.setExcepcion("No se ha registrado estadisticas");
				return Response.status(403).entity(respuesta).build();
			}
		} catch (ServicioException e) {
			respuesta.setResultado(estadistica);
			respuesta.setHuboExcepcion(true);
			respuesta.setExcepcion("Error al intentar obtener estad�sticas");
			return Response.status(503).entity(respuesta).build();
		}
	}
}