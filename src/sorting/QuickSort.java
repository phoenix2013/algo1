package sorting;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class QuickSort {

    public static void main(String[] args) throws Exception {
        // List<Integer> array = Arrays.asList(new Integer[] { 3, 8, 4, 9, 2, 6
        // });
        List<Integer> array = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(
                "C:/Users/vzkap/Dropbox/education/Algorithms, Roughgarden, Stanford Part I/week 3/IntegerArray.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                array.add(Integer.parseInt(line));
            }
        }
        System.out.println(partition(array));
        for (int i : array) {
            System.out.println(i + " ");
        }
    }

    private static int partition(List<Integer> array) {
        if (array.size() <= 1) {
            return 0;
        }

        int pivotIndex = choosePivotIndex(array);

//		for (int i : array) {
//			System.out.print(i);
//		}
//		System.out.print(" - " + pivotIndex + "\n");
        int numOfComp = 0;
        swap(array, 0, pivotIndex);
        int pivot = array.get(0);
        int i = 1, j = 1;

        while (array.size() > j) {
            ++numOfComp;
            if (array.get(j) < pivot) {
                swap(array, i, j);
                ++i;
                ++j;
            } else {
                ++j;
            }
        }
        swap(array, 0, i - 1);

        numOfComp += partition(array.subList(0, i - 1));
        numOfComp += partition(array.subList(i, array.size()));

        return numOfComp;
    }

    private static int choosePivotIndex(List<Integer> array) {
        int first = 0;
        int last = array.size() - 1;
        int middle = last % 2 == 0 ? last / 2 : (last - 1) / 2;

//		return median(array, first, middle, last);
        return last;
    }

    private static int median(List<Integer> array, int f, int s, int t) {
        int first = array.get(f);
        int second = array.get(s);
        int third = array.get(t);

        int largest = first;
        int smallest = first;
        if (second > largest) {
            largest = second;
        } else if (second < smallest) {
            smallest = second;
        }

        if (third > largest) {
            largest = third;
        } else if (third < smallest) {
            smallest = third;
        }

        int median = first;
        if (second > smallest && second < largest) {
            median = second;
        } else if (third > smallest && third < largest) {
            median = third;
        }

        return median == first ? f : median == second ? s : t;
    }

    private static void swap(List<Integer> array, int firstIndex, int secondIndex) {
        int firstValue = array.get(firstIndex);

        array.set(firstIndex, array.get(secondIndex));
        array.set(secondIndex, firstValue);
    }
}
