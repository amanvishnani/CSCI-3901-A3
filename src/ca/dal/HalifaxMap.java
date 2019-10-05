package ca.dal;

import java.util.*;

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

    public Boolean defineRoad( int x1, int y1, int x2, int y2 ) {
        Vertex v1 = new Vertex(x1, y1);
        Vertex v2 = new Vertex(x2, y2);
        try {
            // assert if Vertices Exists
            if(!getVertices().contains(v1) || !getVertices().contains(v2)) {
                // Vertices not found.
                return false;
            }
            // Get from original set to retain all edges.
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

    private Vertex getRefVertex(Vertex v) {
        for (Vertex vertex : getVertices()) {
            if (vertex.equals(v)) {
                return vertex;
            }
        }
        return null;
    }

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
        while(allNodes.size() != 0) {
            Vertex u = poll(allNodes, distance);
            allNodes.remove(u);
            if(distance.get(u).isInfinite()) {
                continue;
            }
            for (Edge e :
                    u.getEdges()) {
                Vertex v = e.getVertex2().equals(u) ? e.getVertex1() : e.getVertex2();
                if(!allNodes.contains(v)) continue;

                Double alt = distance.get(u) + e.weight();
                if(alt < distance.get(v)) {
                    distance.put(v, alt);
                    previous.put(v, u);
                }
            }
        }
        if(previous.get(dest) != null) {
            printPath(dest, previous);
        } else {
            System.out.println("no path");
        }
    }

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

    private void printPath(Vertex dest, Map<Vertex, Vertex> previous) {
        if(dest != null) {
            Vertex v = previous.get(dest);
            printPath(v, previous);
            System.out.println(dest);
        }

        return;
    }

    public HalifaxMap() {
        this.setEdges(new HashSet<>());
        this.setVertices(new HashSet<>());
    }
}
