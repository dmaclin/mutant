/**
 * 
 */
package com.mutant.ws.rest.model;

public class ExpertoMutante {

	private static final int TAMAÑO_MUESTRA_ADN = 4;
	
	public boolean isMutant(String[] dna) throws ServicioException {
		int cantidadSecuencias = 0;
		cantidadSecuencias += validarSecuenciasDiagonales(dna);
		if(cantidadSecuencias > 1) {
			return true;
		}
		cantidadSecuencias += validarSecuenciasHorizontales(dna); 
		if(cantidadSecuencias > 1) {
			return true;
		}
		cantidadSecuencias += validarSecuenciasVerticales(dna);
		if(cantidadSecuencias > 1) {
			return true;
		}
		return false;
	}

	private int validarSecuenciasHorizontales(String[] dna) {
		int j = 0;
		int nroInicioColumna = 0;
        int cantidadCoincidenciasEnSecuencia = 0;
        int cantidadSecuenciasMutantes = 0;        
        char ultimaLetra = 'Z';

		for (int i = 0; i < dna.length; i++) {
			if (i > nroInicioColumna + TAMAÑO_MUESTRA_ADN - 1) {
				j++;
				if (j > dna.length - 1) {
					j = 0;
					nroInicioColumna++;
				}
				i = nroInicioColumna - 1;
				cantidadCoincidenciasEnSecuencia = 0;
				continue;
			}
        	if(dna[j].charAt(i) == ultimaLetra) {
        		cantidadCoincidenciasEnSecuencia++;
        		if(cantidadCoincidenciasEnSecuencia == TAMAÑO_MUESTRA_ADN - 1) {
        			cantidadSecuenciasMutantes++;
        		}
        	}else {
        		ultimaLetra = dna[j].charAt(i);
        		cantidadCoincidenciasEnSecuencia = 0;
        	}
			if (i >= dna.length - 1) {
				j++;
				if (j > dna.length - 1) {
					break;
				} else {
					i = nroInicioColumna - 1;
					cantidadCoincidenciasEnSecuencia = 0;
				}
			}
		}
		return cantidadSecuenciasMutantes;
	}
	
	private int validarSecuenciasVerticales(String[] dna) {
		int j = 0;
		int nroInicioColumna = 0;
        int cantidadCoincidenciasEnSecuencia = 0;
        int cantidadSecuenciasMutantes = 0;        
        char ultimaLetra = 'Z';

		for (int i = 0; i < dna.length; i++) {
			if (i > nroInicioColumna + TAMAÑO_MUESTRA_ADN - 1) {
				j++;
				if (j > dna.length - 1) {
					j = 0;
					nroInicioColumna++;
				}
				i = nroInicioColumna - 1;
				cantidadCoincidenciasEnSecuencia = 0;
				continue;
			}
        	if(dna[i].charAt(j) == ultimaLetra) {
        		cantidadCoincidenciasEnSecuencia++;
        		if(cantidadCoincidenciasEnSecuencia == TAMAÑO_MUESTRA_ADN - 1) {
        			cantidadSecuenciasMutantes++;
        		}
        	}else {
        		ultimaLetra = dna[i].charAt(j);
        		cantidadCoincidenciasEnSecuencia = 0;
        	}
			if (i >= dna.length - 1) {
				j++;
				if (j > dna.length - 1) {
					break;
				} else {
					i = nroInicioColumna - 1;
					cantidadCoincidenciasEnSecuencia = 0;
				}
			}
		}
		return cantidadSecuenciasMutantes;
	}
	
	private int validarSecuenciasDiagonales(String[] dna) {
		int j = 0;
        int nroInicioDiagonalColumn = 0;
        int nroInicioDiagonalFila = 0;
        int cantidadCoincidenciasEnSecuencia = 0;
        int cantidadSecuenciasMutantes = 0;
        char ultimaLetra = 'Z';
        
        for (int i = 0; i < dna.length; i++) {        	
        	if(j >= dna.length) {
				nroInicioDiagonalColumn = 0;
				i = nroInicioDiagonalFila++;
				j = 0;
				cantidadCoincidenciasEnSecuencia = 0;
				continue;
			}
			
        	if(j > nroInicioDiagonalColumn + TAMAÑO_MUESTRA_ADN - 1) {
				j = ++nroInicioDiagonalColumn;
				i = nroInicioDiagonalFila - 1;
				cantidadCoincidenciasEnSecuencia = 0;
				continue;
			}
        	
        	if(dna[i].charAt(j) == ultimaLetra) {
        		cantidadCoincidenciasEnSecuencia++;
        		if(cantidadCoincidenciasEnSecuencia == TAMAÑO_MUESTRA_ADN - 1) {
        			cantidadSecuenciasMutantes++;
        		}
        	}else {
        		ultimaLetra = dna[i].charAt(j);
        		cantidadCoincidenciasEnSecuencia = 0;
        	}
        	j++;
        	if(i >= dna.length - 1) {
        		if(j == dna.length) {
					break;
				}
				i = nroInicioDiagonalFila - 1;
				j = ++nroInicioDiagonalColumn; 
				cantidadCoincidenciasEnSecuencia = 0;
			}
		}
		return cantidadSecuenciasMutantes;
	}

	public void guardarADN(String[] dna, boolean isMutant) throws ServicioException {
		ExpertoPersistencia.getInstancia().guardarADN(dna, isMutant);
	}
}
