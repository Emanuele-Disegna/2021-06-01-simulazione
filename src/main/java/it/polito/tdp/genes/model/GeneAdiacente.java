package it.polito.tdp.genes.model;

public class GeneAdiacente {
	private Genes gene;
	private double peso;
	public GeneAdiacente(Genes gene, double peso) {
		super();
		this.gene = gene;
		this.peso = peso;
	}
	public Genes getGene() {
		return gene;
	}
	public void setGene(Genes gene) {
		this.gene = gene;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	@Override
	public String toString() {
		return gene + " dal peso " + peso +"\n";
	}
	
	
}
