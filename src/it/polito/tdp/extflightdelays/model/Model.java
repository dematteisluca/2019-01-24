package it.polito.tdp.extflightdelays.model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {

	Graph<String, DefaultWeightedEdge> grafo;
	ExtFlightDelaysDAO dao;
	
	public Model() {
		dao =  new ExtFlightDelaysDAO();
	}
	
	public Graph<String, DefaultWeightedEdge> getGrafo(){
		return this.grafo;
	}
	
	public void creaGrafo() {
		 
		           //Cappio -> Self-Loops
		grafo = new DefaultDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		List<String> vertici= dao.loadAllStates();
		Graphs.addAllVertices(grafo, vertici);
		List<Accoppiamenti> accoppiamenti= new ArrayList<>();
		accoppiamenti= dao.loadAllAccoppiamenti();
		
		for(Accoppiamenti a: accoppiamenti) {
			String source = a.getA1();
			String target = a.getA2();
			double weight = a.getPeso();
			
			if (grafo.containsVertex(source) && grafo.containsVertex(target))
			Graphs.addEdge(grafo, source, target, weight);

		}
		
		//Stampo cose
		System.out.println("GRAFO CREATO!");
		System.out.println("#NODI: "+grafo.vertexSet().size());
		System.out.println("#ARCHI :"+grafo.edgeSet().size());
	}
		
	public List<String> getViciniPesati (String s){
		List<String> result= new ArrayList<>();
        Set<DefaultWeightedEdge> edges =grafo.outgoingEdgesOf(s);
		
		List<DefaultWeightedEdge> edgess = new ArrayList<>();
		
		for ( DefaultWeightedEdge e : edges ) {
			edgess.add(e);
		}
		//Riordino la lista           //Comparatore interno alla lista
				Collections.sort(edgess, new Comparator<DefaultWeightedEdge>(){

					@Override
					public int compare(DefaultWeightedEdge o1, DefaultWeightedEdge o2) {
						
						if (grafo.getEdgeWeight(o1)<grafo.getEdgeWeight(o2)) return 1;
						if (grafo.getEdgeWeight(o1)==grafo.getEdgeWeight(o2)) return 0;
						else return -1;
					}});
		
	for (DefaultWeightedEdge e : edgess) {
		
		String string = grafo.getEdgeTarget(e);
		int r = (int) grafo.getEdgeWeight(e);
		
		result.add("State: "+string +" , N°Airplane: "+r+"\n");
	}
	
	return result;
}
}

