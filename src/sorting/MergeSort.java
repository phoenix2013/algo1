package sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeSort {

    public static void main(String[] args) {
        List<Double> array = Arrays.asList(new Double[]{122d, 12d, 4563d, 124d, 15d, 6d, 7.01001d, 118d, 909.11d, 0d});

        for (Double v : sort(array)) {
            System.out.println(v);
        }
    }

    private static List<Double> sort(List<Double> array) {
        // return if at leaf node
        int size = array.size();
        if (size == 1) {
            return array;
        }

        // split
        List<Double> first, second;
        if (size % 2 == 0) {
            first = array.subList(0, size / 2);
            second = array.subList(size / 2, size);
        } else {
            first = array.subList(0, (size + 1) / 2);
            second = array.subList((size + 1) / 2, size);
        }

        // recursive sort call
        first = sort(first);
        second = sort(second);

        // merge
        List<Double> sortedArray = new ArrayList<>();

        int j = 0, k = 0;
        for (int i = 0; i < size; ++i) {
            if (j >= first.size()) {
                sortedArray.add(second.get(k));
                k++;
            } else if (k >= second.size()) {
                sortedArray.add(first.get(j));
                j++;
            } else if (first.get(j) < second.get(k)) {
                sortedArray.add(first.get(j));
                j++;
            } else {
                sortedArray.add(second.get(k));
                k++;
            }
        }

        return sortedArray;
    }
}
