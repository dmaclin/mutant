package com.mutant.ws.rest.model;

public final class Validador {

	public static boolean validarMatriz(String[] dna) throws ServicioException {
		try {
			for (int i = 0; i < dna.length; i++) {

				if (!dna[i].matches("[ATCGatcg]*")) {
					throw new ServicioException("ERROR: Tiene letras fuera del dominio ATCG");
				}

				if (dna[i].length() != dna.length) {
					throw new ServicioException("ERROR: una cadena tiene tamaño distinto al de la matriz");
				}
			}

			return true;
		} catch (Exception ex) {
			throw new ServicioException("Error inesperado de servicio, intente nuevamente");
		}

	}
/*
	public static void main(String argv[]) { final int TAMAÑO_MUESTRA_ADN = 4;
	  String s = "aaaaa"; String[] a = s.split("(?<=(.))(?!\\1)"); String[] dna =
	  {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"}; int j = 0; int
	  nroInicioDiagonalColumn = 0; int nroInicioDiagonalFila = 0; int
	  cantidadCoincidenciasEnSecuencia = 0; int cantidadSecuenciasMutantes = 0;
	  
	  char ultimaLetra = 'Z'; boolean resultado = false;
	  
	  for (int i = 0; i < dna.length; i++) { if(j >= dna.length) {
	  nroInicioDiagonalColumn = 0; i = nroInicioDiagonalFila++; j = 0;
	  System.out.println("------"); cantidadCoincidenciasEnSecuencia = 0; continue;
	  }
	 
	  if(j > nroInicioDiagonalColumn + TAMAÑO_MUESTRA_ADN - 1) { j =
	  ++nroInicioDiagonalColumn; i = nroInicioDiagonalFila - 1;
	  System.out.println("------"); cantidadCoincidenciasEnSecuencia = 0; continue;
	  }
	  
	  if(dna[i].charAt(j) == ultimaLetra) { 
		  cantidadCoincidenciasEnSecuencia++;
	  if(cantidadCoincidenciasEnSecuencia == TAMAÑO_MUESTRA_ADN - 1) {
		  cantidadSecuenciasMutantes++; 
		  } 
	  }else { 
		  ultimaLetra = dna[i].charAt(j); 
	  }
	  System.out.println(dna[i].charAt(j)); j++;
	  
	  if(i >= dna.length - 1) { if(j == dna.length) { break; } i =
	  nroInicioDiagonalFila - 1; j = ++nroInicioDiagonalColumn;
	  System.out.println("------"); cantidadCoincidenciasEnSecuencia = 0; }
	  if(cantidadSecuenciasMutantes > 1) { resultado = true; break; }
	  
	  } System.out.println("Resultado: " + resultado);
	  System.out.println("Cantidad de Secuencias Mutantes " +
	  cantidadSecuenciasMutantes); */
	  //System.out.println(s.matches("(\\w)\\1+")); 
	  //}
	 
	/*public static void main(String argv[]) {
		String[] dna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
		final int TAMAÑO_MUESTRA_ADN = 4;
		int j = 0;
		int nroInicioColumna = 0;
        int cantidadCoincidenciasEnSecuencia = 0;
        int cantidadSecuenciasMutantes = 0;
        
        char ultimaLetra = 'Z';
        boolean resultado = false;
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
        	if(cantidadSecuenciasMutantes > 1) {
        		resultado = true;
        		break;
        	}
		}
		System.out.println("resultado " + resultado);
			
	}*/

}
