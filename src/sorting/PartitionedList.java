package sorting;

import java.util.List;

public class PartitionedList {
	private List<Integer> first;
	private List<Integer> second;
	private int pivot;
	
	public PartitionedList(List<Integer> first, List<Integer> second, int pivot) {
		super();
		this.first = first;
		this.second = second;
		this.pivot = pivot;
	}

	public List<Integer> getFirst() {
		return first;
	}

	public void setFirst(List<Integer> first) {
		this.first = first;
	}

	public List<Integer> getSecond() {
		return second;
	}

	public void setSecond(List<Integer> second) {
		this.second = second;
	}

	public int getPivot() {
		return pivot;
	}

	public void setPivot(int pivot) {
		this.pivot = pivot;
	}
}
