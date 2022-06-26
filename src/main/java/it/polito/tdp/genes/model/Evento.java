package it.polito.tdp.genes.model;

public class Evento implements Comparable<Evento>{
	private int mese;
	private Event_Type tipo;
	private Ingegnere ing;
	
	public enum Event_Type {
		CONTINUA,
		CAMBIA
	}

	public Evento(int mese, Ingegnere ing, Event_Type tipo) {
		super();
		this.mese = mese;
		this.ing = ing;
		this.tipo = tipo;
	}

	public int getMese() {
		return mese;
	}

	public Ingegnere getIng() {
		return ing;
	}

	public Event_Type getTipo() {
		return tipo;
	}

	@Override
	public int compareTo(Evento o) {
		return mese-o.getMese();
	}

	
	
}
