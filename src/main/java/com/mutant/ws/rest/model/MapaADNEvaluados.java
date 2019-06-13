package com.mutant.ws.rest.model;

import java.util.HashMap;
import java.util.Map;

public final class MapaADNEvaluados {
	
	private static Map<String, Boolean> mapa = new HashMap<String, Boolean>();
	
	static {
		ExpertoPersistencia experto = new ExpertoPersistencia();
		experto.cargarDatosDB();
	}
	
	private MapaADNEvaluados() {
		
	}
	
    public static void guardar(String adn, Boolean isMutant) {
        mapa.put(adn, isMutant);
    }
    
    public static Boolean buscar(String adn) {
        return mapa.get(adn);
    }
    
    public static void remover(String adn) {
        mapa.remove(adn);
    }
}
