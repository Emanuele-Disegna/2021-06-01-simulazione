package it.polito.tdp.genes.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.genes.db.GenesDao;

public class Model {
	private Graph<Genes, DefaultWeightedEdge> grafo;
	private GenesDao dao;
	private List<Adiacenza> adiacenze;
	private Map<String, Genes> idMap;
	
	public Model() {
		dao = new GenesDao();
		idMap = new HashMap<>();
	}
	
	public void creaGrafo() {
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		dao.getVertici(idMap);
		
		Graphs.addAllVertices(grafo, idMap.values());
		
		adiacenze = new ArrayList<>();
		dao.getAdiacenze1(idMap, adiacenze);
		System.out.println(adiacenze.size());
		dao.getAdiacenze2(idMap, adiacenze);
		System.out.println(adiacenze.size());
		
		for(Adiacenza a : adiacenze) {
			Graphs.addEdge(grafo, a.getG1(), a.getG2(), a.getPeso());
		}
		
		System.out.println(grafo.vertexSet().size());
		System.out.println(grafo.edgeSet().size());

	}
	
	public List<GeneAdiacente> getAdiacenti(Genes g) {
		List<GeneAdiacente> ret = new ArrayList<>();
		
		for(Genes gg : Graphs.neighborListOf(grafo, g)) {
			GeneAdiacente gene = new GeneAdiacente(gg, grafo.getEdgeWeight(grafo.getEdge(gg, g)));
			ret.add(gene);
		}
		
		Collections.sort(ret, Comparator.comparing(GeneAdiacente::getPeso));
		Collections.reverse(ret);
		
		return ret;
	}
	
	public Collection<Genes> getGeni() {
		return  idMap.values();
	}
}
