package graphcontraction;

import java.util.HashMap;
import java.util.Map;

public class Vertex {
	private static int maxIndex;
	private int index;
	private Map<Integer, Edge> edges = new HashMap<>();

	public Vertex(int index) {
		super();
		this.index = index;
	}

	public static int getAndBumpMaxIndex() {
		return ++maxIndex;
	}
	
	public static int getMaxIndex() {
		return maxIndex;
	}

	public static void setMaxIndex(int maxIndex) {
		Vertex.maxIndex = maxIndex;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Map<Integer, Edge> getEdges() {
		return edges;
	}

	public void setEdges(Map<Integer, Edge> edges) {
		this.edges = edges;
	}

}
