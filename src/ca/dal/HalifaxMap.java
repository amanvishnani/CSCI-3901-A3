package ca.dal;

import java.util.*;

/**
 * @author Aman Vishnani (aman.vishnani@dal.ca) [CSID: vishnani]
 */
public class HalifaxMap {

    private Set<Vertex> vertices;

    private Set<Edge> edges;

    public Set<Vertex> getVertices() {
        return vertices;
    }

    public void setVertices(Set<Vertex> vertices) {
        this.vertices = vertices;
    }

    public Set<Edge> getEdges() {
        return edges;
    }

    public void setEdges(Set<Edge> edges) {
        this.edges = edges;
    }

    /**
     * Defines a new Intersection or Vertex.
     * @param x the coordinate x
     * @param y the coordinate y
     * @return true on success and false on failure (Vertex already exists).
     */
    public Boolean newIntersection( int x, int y ) {
        // Assert if the point already exists in the set
        Vertex v = new Vertex(x, y);
        try {
            if(getVertices().contains(v)) {
                return false;
            } else {
                getVertices().add(v);
                return true;
            }
        } catch (Exception ignored) {

        }
        return false;
    }

    /**
     * Defines a new road/edge in HalifaxMap.
     * @param x1 the coordinate x for source vertex.
     * @param y1 the coordinate y for source vertex.
     * @param x2 the coordinate x for destination vertex.
     * @param y2 the coordinate y for destination vertex.
     * @return true on success, false on failure (Edge already exists or vertex not found.)
     */
    public Boolean defineRoad( int x1, int y1, int x2, int y2 ) {
        Vertex v1 = new Vertex(x1, y1);
        Vertex v2 = new Vertex(x2, y2);
        try {
            // assert if Vertices Exists
            if(!getVertices().contains(v1) || !getVertices().contains(v2)) {
                // Vertices not found.
                return false;
            }
            // Get vertex from original set to retain all edges.
            v1 = getRefVertex(v1);
            v2 = getRefVertex(v2);

            // Setup a new Edge
            Edge edge = new Edge(v1, v2);

            // assert edge not already exists.
            if(v1.getEdges().contains(edge) || v2.getEdges().contains(edge)) {
                return false;
            }
            v1.getEdges().add(edge);
            v2.getEdges().add(edge);
            return true;
        } catch (Exception ignored) {}
        return false;
    }

    /**
     * A utility method to get reference vertex, used to retain existing set of edges of the vertex.
     * @param v the artificial vertex.
     * @return the original vertex in HalifaxMap.
     */
    private Vertex getRefVertex(Vertex v) {
        for (Vertex vertex : getVertices()) {
            if (vertex.equals(v)) {
                return vertex;
            }
        }
        return null;
    }

    /**
     * A utility method to get reference vertex, used to retain existing set of edges of the vertex.
     * @param x coordinate
     * @param y coordinate
     * @return the original vertex in HalifaxMap.
     */
    private Vertex getRefVertex(int x, int y) {
        Vertex v = new Vertex(x,y);
        return getRefVertex(v);
    }

    void navigate( int x1, int y1, int x2, int y2 ) {
        Vertex source = getRefVertex(x1,y1);
        Vertex dest = getRefVertex(x2,y2);
        if(source == null) {
            System.out.println("Source vertex not found.");
            return;
        } else if (dest == null) {
            System.out.println("Destination vertex not found.");
            return;
        }
        // Initializing variables for Dijkstra's Algorithm.
        Map<Vertex, Vertex> previous = new HashMap<>();
        Map<Vertex, Double> distance = new HashMap<>();
        List<Vertex> allNodes = new ArrayList<>();

        for (Vertex v: getVertices()) {
            if(v.equals(source)) {
                distance.put(v, 0.0);
            } else {
                distance.put(v, Double.POSITIVE_INFINITY);
            }
            previous.put(v, null);
            allNodes.add(v);
        }

        // The Dijkstra's graph traversal Algorithm.
        while(allNodes.size() != 0) {
            // Greedily get the nearest vertex.
            Vertex u = poll(allNodes, distance);
            // Remove it from unvisited node.
            allNodes.remove(u);
            // Skip if the distance from source is Infinite.
            if(distance.get(u).isInfinite()) {
                continue;
            }
            // Relax all edges.
            for (Edge e :
                    u.getEdges()) {
                // Get destination vertex.
                Vertex v = e.getVertex2().equals(u) ? e.getVertex1() : e.getVertex2();
                // Skip relaxation if the node is already marked as visited.
                if(!allNodes.contains(v)) continue;

                // Relax the edge
                Double alt = distance.get(u) + e.weight();
                if(alt < distance.get(v)) {
                    distance.put(v, alt);
                    previous.put(v, u);
                }
            }
        }

        // Print Path if path exist.
        if(previous.get(dest) != null) {
            printPath(dest, previous);
        } else {
            System.out.println("no path");
        }
    }

    /**
     * A utility method to request an unvisited node closest to the source.
     * @param allNodes the list of unvisited nodes
     * @param distance th map of distance for each node from source.
     * @return the nearest unvisited node with least distance.
     */
    private Vertex poll(List<Vertex> allNodes, Map<Vertex, Double> distance) {
        Vertex minV = allNodes.get(0);
        Double min = distance.get(minV);
        for (Vertex v :
                allNodes) {
            Double d = distance.get(v);
            if(min > d) {
                minV = v;
                min = d;
            }
        }
        return minV;
    }

    /**
     * A utility method to print path recursively.
     * @param dest the destination node.
     * @param previous a map of previous node in path.
     */
    private void printPath(Vertex dest, Map<Vertex, Vertex> previous) {
        if(dest != null) {
            Vertex v = previous.get(dest);
            // Recursively call for previous node.
            printPath(v, previous);
            // Print the vertex.
            System.out.println(dest);
        }
    }

    public HalifaxMap() {
        this.setEdges(new HashSet<>());
        this.setVertices(new HashSet<>());
    }
}
