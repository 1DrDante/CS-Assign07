package assign07;

/**
 * This class represents an edge between a source vertex and a destination
 * vertex in a directed graph.
 * 
 * The source of this edge is the vertex whose object has an adjacency list
 * containing this edge.
 * 
 * @author Prof. Parker
 * @version February 23, 2026
 */
public class Edge<Type> {
	
	private Vertex<Type> destination;

	/**
	 * Creates an Edge object, given the vertex that is the destination.
	 * (The Vertex object that stores this Edge object is the source.)
	 * 
	 * @param destination - destination Vertex
	 */
	public Edge(Vertex<Type> destination) {
		this.destination = destination;
	}

	/**
	 * Gets the destination vertex for this edge.
	 * 
	 * @return destination vertex of this edge
	 */
	public Vertex<Type> getOtherVertex() {
		return this.destination;
	}

	/**
	 * Generates a simple textual representation of this edge.
	 * 
	 * @return simple string representation of this edge
	 */
	public String toString() {
		return this.destination.toString();
	}
}
