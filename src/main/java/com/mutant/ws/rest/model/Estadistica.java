package com.mutant.ws.rest.model;

public class Estadistica {
	private int count_mutant_dna;
	private int count_human_dna;
	private String ratio;
	
	public int getCount_mutant_dna() {
		return count_mutant_dna;
	}
	public void setCount_mutant_dna(int count_mutant_dna) {
		this.count_mutant_dna = count_mutant_dna;
	}
	public int getCount_human_dna() {
		return count_human_dna;
	}
	public void setCount_human_dna(int count_human_dna) {
		this.count_human_dna = count_human_dna;
	}
	public String getRatio() {
		return ratio;
	}
	public void setRatio(float ratio) {
		this.ratio = String.valueOf(ratio);
	}
	public void sumarMutante() {
		this.count_mutant_dna++;
	}
	public void sumarHumano() {
		this.count_human_dna++;
		
	}
	public void calcularProporcion() {
		setRatio(this.count_human_dna/this.count_mutant_dna);
		
	}
}
