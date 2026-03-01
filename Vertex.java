package assign07;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * This class represents a vertex (AKA node) in a directed graph. The vertex is
 * not generic, assumes that a string name is stored there.
 * 
 * @author Prof. Parker
 * @version February 23, 2026
 */
public class Vertex<Type>{
	private Type name;
	private int inDegree;
	private int distanceFromStart;
	private Vertex<Type> previous;
	
	private LinkedList<Edge<Type>> adjacencyList;
	

	/**
	 * Creates a new Vertex object, using the given name.
	 * 
	 * @param name - string used to identify this vertex
	 */
	public Vertex(Type name) {
		this.name = name;
		this.adjacencyList = new LinkedList<Edge<Type>>();
		this.previous = null;
		this.inDegree = 0;
		distanceFromStart = 0;
		
	}

	/**
	 * Gets the name string for this vertex
	 * 
	 * @return string used to identify this vertex
	 */
	public Type getName() {
		return name;
	}

	/**
	 * Adds a directed edge from this vertex to another vertex.
	 * 
	 * @param other - vertex that is the destination of the edge being added
	 */
	public void addEdge(Vertex<Type> other) {
		adjacencyList.add(new Edge<Type>(other));
		other.setInDegree(1);
	}
	
	public void setPrevious(Vertex<Type> other) {
		previous = other;
	}
	
	public void setInDegree(int degree) {
		this.inDegree = inDegree + degree;
	}
	
	public int getInDegree() {
		return this.inDegree;
	}
	
	public void setDistanceFromStart(int distance) {
		this.distanceFromStart = distanceFromStart + distance;
	}
	
	public int getDistanceFromStart() {
		return distanceFromStart;
	}
	
	/**
	 * Gets the iterator for accessing the edges for which this vertex is the source.
	 * 
	 * @return iterator for accessing the edges for this vertex
	 */
	public Iterator<Edge<Type>> edges() {
		return adjacencyList.iterator();
	}

	/**
	 * Generates a simple textual representation of this vertex.
	 * 
	 * @return simple string representation of this vertex
	 */
	public String toString() {
		String result = "Vertex " + name + " has edge(s) to vertice(s) ";
		Iterator<Edge<Type>> edges = adjacencyList.iterator();
		while(edges.hasNext())
			result += edges.next() + " ";
		return result;
	}
}
