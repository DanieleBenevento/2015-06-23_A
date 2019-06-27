package it.polito.tdp.music.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.traverse.BreadthFirstIterator;

import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.music.db.MusicDAO;

public class Model {
	
	MusicDAO dao;
	private Map<Integer,Country>idMap;
	private Map<Integer,Artist>idMapA;
	private Graph<Country,DefaultWeightedEdge>grafo;
 LatLngTool l;
 double best;
	
	public Model() {
		 best=Double.MIN_NORMAL;
		idMap=new HashMap<Integer,Country>();
		idMapA=new HashMap<Integer,Artist>();
		 dao=new MusicDAO();
		 dao.getAllCountries(idMap);
		 dao.getAllArtists(idMapA);
		 
		 
	}
	
	public List<AutoreAscolti> elencoAscolti(int valore) {
		List<AutoreAscolti> list=new ArrayList<>();
		list.clear();
		list=dao.getAutoriAscolti(valore, idMapA);
		Collections.sort(list);
		return list.subList(0, 20);
	}
	
	public void creaGrafo(int valore) {
		grafo= new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		 		for(AutoreAscolti a:elencoAscolti(valore)) {
		for(Country c:dao.getTopCountry(valore, idMap)) {
			if(!grafo.vertexSet().contains(c))
			grafo.addVertex(c);
		}
	}
		for(Country c: grafo.vertexSet()) {
			for(Country c1:grafo.vertexSet()) {
				if(!c.equals(c1) && dao.getPeso(c, c1)>0) {
					Graphs.addEdge(grafo, c, c1, dao.getPeso(c, c1));
					if(LatLngTool.distance(dao.getLatLngCountry(c), dao.getLatLngCountry(c1), LengthUnit.KILOMETER)>best) {
						best=LatLngTool.distance(dao.getLatLngCountry(c), dao.getLatLngCountry(c1), LengthUnit.KILOMETER);
					}
				}
			}
		}
		

}

	public Map<Integer, Country> getIdMap() {
		return idMap;
	}

	public void setIdMap(Map<Integer, Country> idMap) {
		this.idMap = idMap;
	}

	public Map<Integer, Artist> getIdMapA() {
		return idMapA;
	}

	public void setIdMapA(Map<Integer, Artist> idMapA) {
		this.idMapA = idMapA;
	}

	public Graph<Country, DefaultWeightedEdge> getGrafo() {
		return grafo;
	}

	public void setGrafo(Graph<Country, DefaultWeightedEdge> grafo) {
		this.grafo = grafo;
	}

	public double getBest() {
		return best;
	}

	public void setBest(double best) {
		this.best = best;
	}

	
}