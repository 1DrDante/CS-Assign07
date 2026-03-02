package assign07;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Represents a sparse, unweighted, directed graph (a set of vertices and a set
 * of edges). The graph is generic and assumes that a string name is stored at
 * each vertex.
 * 
 * @author Prof. Parker
 * @version February 23, 2026
 */
public class Graph<Type> {
	// the graph -- a set of vertices (String name mapped to Vertex instance)
	private Map<Type, Vertex<Type>> vertices;

	/**
	 * Creates an empty Graph object.
	 */
	public Graph() {
		vertices = new HashMap<Type, Vertex<Type>>();
	}

	/**
	 * Adds to this graph a directed edge from the vertex with name "name1" to the
	 * vertex with name "name2". (If either vertex does not already exist in the
	 * graph, it is added.)
	 * 
	 * @param srcName - string name for source vertex
	 * @param dstName - string name for destination vertex
	 */
	public void addEdge(Type srcName, Type dstName) {
		Vertex<Type> srcVertex;
		// if vertex already exists in graph, get its object
		if (vertices.containsKey(srcName))
			srcVertex = vertices.get(srcName);
		// else, create a new object and add to graph
		else {
			srcVertex = new Vertex<Type>(srcName);
			vertices.put(srcName, srcVertex);
		}

		Vertex<Type> dstVertex;
		if (vertices.containsKey(dstName))
			dstVertex = vertices.get(dstName);
		else {
			dstVertex = new Vertex<Type>(dstName);
			vertices.put(dstName, dstVertex);
		}

		// add new directed edge from srcVertex to dstVertex
		srcVertex.addEdge(dstVertex);
	}
	
	
	// To Do: Find the shortest Path, this only layout the distant from start.
	public boolean depthFirstSearch(Type source, Type destination) {
		//This for each loop should be in the driver method
		for (Vertex<Type> vertex : vertices.values()) {
			vertex.setDistanceFromStart(-1);
			vertex.setPrevious(null);
		}
		Vertex<Type> srcVertex = vertices.get(source);
		srcVertex.setDistanceFromStart(1);
		
		return recursiveDepthFirstSearch(source, destination);
		
	}
	
	private boolean recursiveDepthFirstSearch(Type source, Type destination) {
		Vertex<Type> srcVertex = vertices.get(source);
		while(srcVertex.edges().hasNext()) {
			Vertex<Type> adjVertex = srcVertex.edges().next().getOtherVertex();
			if(adjVertex.getName().equals(destination)) {
				return true;
			}
			if(adjVertex.getDistanceFromStart() == -1) {
				adjVertex.setDistanceFromStart(srcVertex.getDistanceFromStart() + 1);
				adjVertex.setPrevious(srcVertex);
				recursiveDepthFirstSearch(adjVertex.getName(), destination);
			}
		}
		
		//Need to return backTracking not whatever this is.
		return recursiveDepthFirstSearch(destination, destination);
	}

	public List<Type> breadthFirstSearch(Type source, Type destination) {
		// Set all vertices distanceFromStart to -1 and previous as null
		for (Vertex<Type> vertex : vertices.values()) {
			vertex.setDistanceFromStart(-1);
			vertex.setPrevious(null);
		}
		
		List<Type> list = new ArrayList<Type>();
		Queue<Vertex<Type>> queue = new LinkedList<Vertex<Type>>();
		
		Vertex<Type> srcVertex = vertices.get(source);
		queue.offer(srcVertex);
		// srcVertex distance from start is 0
		srcVertex.setDistanceFromStart(1); // +1 to -1 to set it to 0
		
		while (!queue.isEmpty()) {
			Vertex<Type> nextVertex = queue.poll();
			Iterator<Edge<Type>> it = nextVertex.edges();
			// Iterate over adjacency list of nextVertex
			while (nextVertex.edges().hasNext()) {
				Vertex<Type> adjVertex = it.next().getOtherVertex();
				
				// Check is adjacent Vertex if our destination vertex
				if(adjVertex.getName().equals(destination)) {
					adjVertex.setPrevious(nextVertex);
					//list.add(adjVertex.getName());
					
					Vertex<Type> vertexToAdd = adjVertex;
					// add all previous vertices to list
					while(!vertexToAdd.getPrevious().getName().equals(source)) {
						list.add(vertexToAdd.getPrevious().getName());
						vertexToAdd = vertexToAdd.getPrevious();
					}
					list.add(source);
					Collections.reverse(list);
					return list;
					
				}
				// Sets the distanceFromStart and previous of the adjacent vertex 
				// and adds it to queue
				if (adjVertex.getDistanceFromStart() == -1) {
					adjVertex.setDistanceFromStart(nextVertex.getDistanceFromStart() + 1); // This add 1 to the
																							// distanceFromStart
					adjVertex.setPrevious(nextVertex);
					queue.offer(adjVertex);
				}
				
			}
		}
		// returns null if there is no path between the source and destination
		return list;
	}

	public List<Type> topoSort(){
		List<Type> list = new ArrayList<Type>();
		Queue<Vertex<Type>> queue = new LinkedList<Vertex<Type>>();
		for(Vertex<Type> vertex : vertices.values()) {
			if(vertex.getInDegree() == 0) {
				queue.offer(vertex);
			}
		}
		
		while(!queue.isEmpty()) {
			Vertex<Type> vertex = queue.poll();
			list.add(vertex.getName());
			while(vertex.edges().hasNext()) {
				if(list.size() > vertices.size()) {
					throw new IllegalArgumentException("A cycle had appeared in the graph!");
				}
				
				Vertex<Type> adjVertex = vertex.edges().next().getOtherVertex();
				adjVertex.setInDegree(-1);
				if(adjVertex.getInDegree() == 0) {
					queue.offer(adjVertex);
				}
			}
		}
		return list;
	}

	/**
	 * Generates the DOT encoding of this graph as string, which can be pasted into
	 * http://www.webgraphviz.com to produce a visualization.
	 * 
	 * @return DOT representation of this graph to be copy-and-pasted at
	 *         www.webgraphviz.com
	 */
	public String generateDot() {
		String result = "digraph d {\n";
		for (Vertex<Type> vertex : vertices.values()) {
			Iterator<Edge<Type>> edges = vertex.edges();
			while (edges.hasNext())
				result += "  " + vertex.getName() + " -> " + edges.next() + "\n";
		}
		return result + "}";
	}

	/**
	 * Generates a simple textual representation of this graph.
	 * 
	 * @return simple string representation of this graph
	 */
	public String toString() {
		String result = "";
		for (Vertex<Type> v : vertices.values())
			result += v + "\n";
		return result;
	}
}
