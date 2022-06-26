package it.polito.tdp.genes.model;

public class Ingegnere {
	private int ing;
	private Genes gene;
	public Ingegnere(int ing) {
		super();
		this.ing = ing;
	}
	public int getIng() {
		return ing;
	}
	public Genes getGene() {
		return gene;
	}
	public void setGene(Genes gene) {
		this.gene = gene;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ing;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ingegnere other = (Ingegnere) obj;
		if (ing != other.ing)
			return false;
		return true;
	}
	
	
}
