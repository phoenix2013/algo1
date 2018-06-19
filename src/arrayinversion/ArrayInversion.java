package arrayinversion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayInversion {

    public static void main(String[] args) throws Exception {
        List<Integer> array = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(
                "C:/Users/vzkap/Dropbox/education/Algorithms, Roughgarden, Stanford Part I/week 2/IntegerArray.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                array.add(Integer.parseInt(line));
            }
        }
//		List<Integer> array = Arrays.asList(new Integer[] { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 });
        CountWrapper inversionCount = new CountWrapper(new BigInteger("0"));

        List<Integer> sortedArray = sort(array, inversionCount);

        System.out.println("number of inversions = " + inversionCount.getCount());

//		for (Integer v : sortedArray) {
//			System.out.println(v);
//		}
    }

    private static List<Integer> sort(List<Integer> array, CountWrapper inversionCount) {
        // return if at leaf node
        int size = array.size();
        if (size == 1) {
            return array;
        }

        // split
        List<Integer> first, second;
        if (size % 2 == 0) {
            first = array.subList(0, size / 2);
            second = array.subList(size / 2, size);
        } else {
            first = array.subList(0, (size + 1) / 2);
            second = array.subList((size + 1) / 2, size);
        }

        // recursive sort call
        first = sort(first, inversionCount);
        second = sort(second, inversionCount);

        // merge
        List<Integer> sortedArray = new ArrayList<>();

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
                inversionCount.setCount(inversionCount.getCount().add(new BigInteger(Integer.toString(first.size() - j))));
            }
        }

        return sortedArray;
    }
}
