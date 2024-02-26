import java.util.*;
import java.io.*;

public class GraphM {
    public class Vertex {
        HashMap<String, Integer> nbrs = new HashMap<>();
    }

    private HashMap<String, Vertex> vtces;

    public GraphM() {
        vtces = new HashMap<>();
    }

    public int numVertices() {
        return this.vtces.size();
    }

    public boolean containsVertex(String vname) {
        return this.vtces.containsKey(vname);
    }

    public void addVertex(String vname) {
        Vertex vtx = new Vertex();
        vtces.put(vname, vtx);
    }

    public void removeVertex(String vname) {
        Vertex vtx = vtces.get(vname);
        ArrayList<String> keys = new ArrayList<>(vtx.nbrs.keySet());

        for (String key : keys) {
            Vertex nbrVtx = vtces.get(key);
            nbrVtx.nbrs.remove(vname);
        }

        vtces.remove(vname);
    }

    public int numEdges() {
        int count = 0;

        for (Vertex vtx : vtces.values()) {
            count += vtx.nbrs.size();
        }

        return count / 2; // Divide by 2 because each edge is counted twice in the adjacency list
    }

    public boolean containsEdge(String vname1, String vname2) {
        Vertex vtx1 = vtces.get(vname1);
        Vertex vtx2 = vtces.get(vname2);

        if (vtx1 == null || vtx2 == null || !vtx1.nbrs.containsKey(vname2)) {
            return false;
        }

        return true;
    }

    public void addEdge(String vname1, String vname2, int value) {
        Vertex vtx1 = vtces.get(vname1);
        Vertex vtx2 = vtces.get(vname2);

        if (vtx1 == null || vtx2 == null || vtx1.nbrs.containsKey(vname2)) {
            return;
        }

        vtx1.nbrs.put(vname2, value);
        vtx2.nbrs.put(vname1, value);
    }

    public void removeEdge(String vname1, String vname2) {
        Vertex vtx1 = vtces.get(vname1);
        Vertex vtx2 = vtces.get(vname2);

        if (vtx1 == null || vtx2 == null || !vtx1.nbrs.containsKey(vname2)) {
            return;
        }

        vtx1.nbrs.remove(vname2);
        vtx2.nbrs.remove(vname1);
    }

    public void displayMap() {
        System.out.println("\tMetro Map");
        System.out.println("\t------------------");

        for (Map.Entry<String, Vertex> entry : vtces.entrySet()) {
            String source = entry.getKey();
            Vertex vtx = entry.getValue();

            for (Map.Entry<String, Integer> edge : vtx.nbrs.entrySet()) {
                String destination = edge.getKey();
                int distance = edge.getValue();
                System.out.printf("%s => %s : %d km\n", source, destination, distance);
            }
        }

        System.out.println("\t------------------");
    }

    public static void main(String[] args) throws IOException {
        GraphM metroMap = new GraphM();

        // Add metro stations and connections
        metroMap.addVertex("PCMC");
        metroMap.addVertex("Swargate");
        metroMap.addVertex("Vanaz");
        metroMap.addVertex("Ramwadi");
        metroMap.addVertex("Hinjawadi");
        metroMap.addVertex("Shivaji Nagar");
        metroMap.addVertex("Nigdi");
        metroMap.addVertex("Katraj");

        metroMap.addEdge("PCMC", "Swargate", 16589);
        metroMap.addEdge("Vanaz", "Ramwadi", 14665);
        metroMap.addEdge("Hinjawadi", "Shivaji Nagar", 23330);
        metroMap.addEdge("PCMC", "Nigdi", 4410);
        metroMap.addEdge("Swargate", "Katraj", 4410);

        // Display the metro map
        metroMap.displayMap();

        // Additional logic can be added here as needed
    }
}
