package graphcontraction;

import java.util.HashMap;
import java.util.Map;

public class Edge {
	private static int maxIndex;
	private int index;
	private Map<Integer, Vertex> endPoints = new HashMap<>();

	public Edge(int index, Vertex endPoint1, Vertex endPoint2) {
		super();
		this.index = index;
		this.endPoints.put(endPoint1.getIndex(), endPoint1);
		this.endPoints.put(endPoint2.getIndex(), endPoint2);
	}

	public static int getAndBumpMaxIndex() {
		return ++maxIndex;
	}

	public int getIndex() {
		return index;
	}

	public static int getMaxIndex() {
		return maxIndex;
	}

	public static void setMaxIndex(int maxIndex) {
		Edge.maxIndex = maxIndex;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Map<Integer, Vertex> getEndPoints() {
		return endPoints;
	}

	public void setEndPoints(Map<Integer, Vertex> endPoints) {
		this.endPoints = endPoints;
	}

}
