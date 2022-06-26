package it.polito.tdp.genes.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.genes.model.Evento.Event_Type;

public class Simulatore {
	//Parametri
	private int numeroIng;
	private Genes geneDiPartenza;
	private Graph<Genes, DefaultWeightedEdge> grafo;
	private int maxMesi;
	private List<Ingegnere> ingegneri;
	
	//Output
	private Statistiche stats;
	
	//Stato del mondo
	private int meseCorrente;
	
	//Coda di eventi 
	private Queue<Evento> coda;

	public Simulatore(Genes gene, Graph<Genes, DefaultWeightedEdge> grafo) {
		super();
		this.geneDiPartenza = gene;
		this.grafo = grafo;
	}
	
	public void init(int numeroIng) {
		this.numeroIng = numeroIng;
		this.maxMesi = 3 * 12 ;
		meseCorrente = 0;
		coda = new PriorityQueue<>();
		ingegneri = new ArrayList<>();
		
		for(int i=0; i<numeroIng; i++) {
			Ingegnere ing = new Ingegnere(i);
			ing.setGene(geneDiPartenza);
			ingegneri.add(ing);
			coda.add(new Evento(0, ing, Event_Type.CONTINUA));
		}
		
	}
	
	public void simula() {
		while(!coda.isEmpty()) {
			Evento e = coda.poll();
			processaEvento(e);
		}
	}

	private void processaEvento(Evento e) {
		switch(e.getTipo()) {
		case CONTINUA:
			
			//Modifica le statische
			//TODO
			
			
			if(Math.random()<0.3) {
				coda.add(new Evento(e.getMese()+1, e.getIng(), Event_Type.CONTINUA));
			} else {
				List<Genes> vicini = Graphs.neighborListOf(grafo, e.getIng().getGene());
				List<GeneAdiacente> viciniConPeso = new ArrayList<>(); //segno i pesi associati a ogni vicino
				List<GeneAdiacente> probabilita = new ArrayList<>(); //segno le probabilita associate a ogni gene
				double sommaPesi = 0;
				
				for(Genes g : vicini) {
					sommaPesi += grafo.getEdgeWeight(grafo.getEdge(g, e.getIng().getGene()));
					viciniConPeso.add(new GeneAdiacente(g, grafo.getEdgeWeight(grafo.getEdge(g, e.getIng().getGene()))));
				}
				
				for(GeneAdiacente g : viciniConPeso) {
					probabilita.add(new GeneAdiacente(g.getGene(), g.getPeso()/sommaPesi));
				}
				
				Collections.sort(probabilita, Comparator.comparing(GeneAdiacente::getPeso));
				
				double prob = Math.random();
				
				for(GeneAdiacente g : probabilita) {
					if(g.getPeso()<prob) {
						coda.add(new Evento(e.getMese()+1, e.getIng(), Event_Type.CAMBIA));
						e.getIng().setGene(g.getGene());
						break;
					}
				}
			}
			break;
		case CAMBIA:
			
			
			
			
			break;
		}
		
	}
	
}
