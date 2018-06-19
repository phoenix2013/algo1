package graphcontraction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;

public class KargerMinCut {

    private static SortedMap<Integer, Vertex> vertices;
    private static SortedMap<Integer, Edge> edges;

    public static void main(String[] args) throws Exception {
        int mincut = Integer.MAX_VALUE;
        for (long rand = 1; rand <= 10000; ++rand) {
            vertices = new TreeMap<>();
            edges = new TreeMap<>();
            Vertex.setMaxIndex(0);
            Edge.setMaxIndex(0);

            try (BufferedReader br = new BufferedReader(new FileReader(
                    "C:/Users/vzkap/Dropbox/education/Algorithms, Roughgarden, Stanford Part I/week 4/kargerMinCut.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] row = line.split("\t");

                    Vertex vertex;
                    if (vertices.containsKey(Integer.parseInt(row[0]))) {
                        vertex = vertices.get(Integer.parseInt(row[0]));
                    } else {
                        vertex = new Vertex(Integer.parseInt(row[0]));
                        Vertex.getAndBumpMaxIndex();
                        vertices.put(vertex.getIndex(), vertex);
                    }

                    for (int i = 1; i < row.length; ++i) {
                        Vertex adjVertex;
                        if (vertices.containsKey(Integer.parseInt(row[i]))) {
                            adjVertex = vertices.get(Integer.parseInt(row[i]));
                        } else {
                            adjVertex = new Vertex(Integer.parseInt(row[i]));
                            Vertex.getAndBumpMaxIndex();
                            vertices.put(adjVertex.getIndex(), adjVertex);
                        }

                        boolean exists = false;
                        List<Integer> pair = Arrays.asList(new Integer[]{vertex.getIndex(), adjVertex.getIndex()});
                        Collections.sort(pair);
                        for (Edge edge : edges.values()) {
                            List<Integer> existingPair = new ArrayList<>(edge.getEndPoints().keySet());
                            Collections.sort(existingPair);

                            if (pair.get(0).intValue() == existingPair.get(0).intValue()
                                    && pair.get(1).intValue() == existingPair.get(1).intValue()) {
                                exists = true;
                            }
                        }

                        if (!exists) {
                            Edge edge = new Edge(Edge.getAndBumpMaxIndex(), vertex, adjVertex);
                            edges.put(edge.getIndex(), edge);
                            vertex.getEdges().put(edge.getIndex(), edge);
                            adjVertex.getEdges().put(edge.getIndex(), edge);
                        }
                    }
                }

                // System.out.println("Vertices: " + vertices.size() + " Edges:
                // " + edges.size());
                Random r = new Random(rand);

                while (vertices.size() > 2) {
                    int index = r.nextInt(edges.size());
                    Edge edgeToFuse = new ArrayList<>(edges.values()).get(index);

                    fuse(edgeToFuse);
                }
                System.out.println("Min Cut: " + edges.size());
                if (edges.size() < mincut) {
                    mincut = edges.size();
                }
            }
        }

        System.out.println("Min cuts after " + 10000 + "simulations = " + mincut);
    }

    public static void fuse(Edge edge) {
        Vertex vertex = new Vertex(Vertex.getAndBumpMaxIndex());
        Map<Integer, Integer> loopedEdges = new HashMap<>();

        for (Vertex v : edge.getEndPoints().values()) {
            for (Edge e : v.getEdges().values()) {
                List<Integer> thisEdgeVertices = new ArrayList<>(e.getEndPoints().keySet());
                Collections.sort(thisEdgeVertices);
                List<Integer> toFuseEdgeVertices = new ArrayList<>(edge.getEndPoints().keySet());
                Collections.sort(toFuseEdgeVertices);
                if (thisEdgeVertices.get(0).intValue() == toFuseEdgeVertices.get(0).intValue()
                        && thisEdgeVertices.get(1).intValue() == toFuseEdgeVertices.get(1).intValue()) {
                    loopedEdges.put(e.getIndex(), null);
                } else {
                    vertex.getEdges().put(e.getIndex(), e);
                    e.getEndPoints().remove(v.getIndex());
                    e.getEndPoints().put(vertex.getIndex(), vertex);
                }
            }

            vertices.remove(v.getIndex());
        }

        vertices.put(vertex.getIndex(), vertex);

        for (int i : loopedEdges.keySet()) {
            edges.remove(i);
        }

    }
}
