package ca.dal;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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

    public HalifaxMap() {
        this.setEdges(new HashSet<>());
        this.setVertices(new HashSet<>());
    }
}
