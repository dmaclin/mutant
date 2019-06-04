package com.mutant.ws.rest.model;

public final class Validador {

	public static boolean validarMatriz(String[] dna) throws ServicioException {
		try {
			for (int i = 0; i < dna.length; i++) {

				if (!dna[i].matches("[ATCGatcg]*")) {
					throw new ServicioException("ERROR: Tiene letras fuera del dominio ATCG");
				}

				if (dna[i].length() != dna.length) {
					throw new ServicioException("ERROR: una cadena tiene tamanio distinto al de la matriz");
				}
			}

			return true;
		} catch (Exception ex) {
			throw new ServicioException("Error inesperado de servicio, intente nuevamente");
		}

	}
}
