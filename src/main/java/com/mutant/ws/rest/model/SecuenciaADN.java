package com.mutant.ws.rest.model;

import java.util.List;

public class SecuenciaADN {
	private List<String> dna;
	private boolean mutante;

	public boolean isMutante() {
		return mutante;
	}

	public void setMutante(boolean mutante) {
		this.mutante = mutante;
	}

	public List<String> getDna() {
		return dna;
	}

	public void setDna(List<String> dna) {
		this.dna = dna;
	}
}
