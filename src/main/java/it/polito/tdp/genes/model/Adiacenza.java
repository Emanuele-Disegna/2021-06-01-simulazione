package it.polito.tdp.genes.model;

public class Adiacenza {
	private Genes g1;
	private Genes g2;
	private double peso;
	public Adiacenza(Genes g1, Genes g2, double peso) {
		super();
		this.g1 = g1;
		this.g2 = g2;
		this.peso = peso;
	}
	public Genes getG1() {
		return g1;
	}
	public Genes getG2() {
		return g2;
	}
	public double getPeso() {
		return peso;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((g1 == null) ? 0 : g1.hashCode());
		result = prime * result + ((g2 == null) ? 0 : g2.hashCode());
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
		Adiacenza other = (Adiacenza) obj;
		if (g1 == null) {
			if (other.g1 != null)
				return false;
		} else if (!g1.equals(other.g1))
			return false;
		if (g2 == null) {
			if (other.g2 != null)
				return false;
		} else if (!g2.equals(other.g2))
			return false;
		return true;
	}
	
	
}
