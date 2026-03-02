package assign07;

import java.util.List;

public class GraphUtility<Type> {
	
	public static <Type> Graph<Type> graphGenerator(List<Type> sources, List<Type> destinations) {
		if(sources.size() != destinations.size()) {
			throw new IllegalArgumentException("Graph can not be constructed!");
		}
		Graph<Type> graph = new Graph<>();
		while(sources.iterator().hasNext() && destinations.iterator().hasNext()) {
			Type src = sources.iterator().next();
			Type dst = destinations.iterator().next();
			graph.addEdge(src,dst);
		}
		return graph;	
	}

	public static <Type> boolean areConnected(List<Type> sources, List<Type> destinations, Type srcData, Type dstData) {
		if(!(sources.contains(srcData) && destinations.contains(dstData))) {
			throw new IllegalArgumentException("Sources or destinations is not inside list.");
		}
		
		Graph<Type> givenGraph = graphGenerator(sources, destinations);
		return givenGraph.depthFirstSearch(srcData, dstData);
	}
	

	public static <Type> List<Type> shortestPath(List<Type> sources, List<Type> destinations, Type srcData, Type dstData){
		if(!(sources.contains(srcData) && destinations.contains(dstData))) {
			throw new IllegalArgumentException("Sources or destinations is not inside list.");
		}
		Graph<Type> givenGraph = graphGenerator(sources, destinations);
		List<Type> returnList = givenGraph.breadthFirstSearch(srcData, dstData);
		if(returnList.equals(null)) {
			throw new IllegalArgumentException("No path exists between vertices!");
		}
		return returnList;
			 
	}
	
	public static <Type> List<Type> sort(List<Type> sources, List<Type> destinations) {
		Graph<Type> givenGraph = graphGenerator(sources, destinations);
		return givenGraph.topoSort();
	}
}
