package ca.dal;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Vertex of the HalifaxMap graph representing an intersection n the graph.
 * Each vertex as x and y coordinates.
 * @author Aman Vishnani (aman.vishnani@dal.ca) [CSID: vishnani]
 */
public class Vertex {
    private int x;
    private int y;

    private Set<Edge> edges;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Set<Edge> getEdges() {
        return edges;
    }

    public void setEdges(Set<Edge> edges) {
        this.edges = edges;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vertex)) return false;
        Vertex vertex = (Vertex) o;
        return getX() == vertex.getX() &&
                getY() == vertex.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }

    public Vertex(int x, int y) {
        setX(x);
        setY(y);
        setEdges(new HashSet<>());
    }

    /**
     * Method to print tab separated coordinates of a vertex for output.
     * @return the string with tab-separated x and y coordinates.
     */
    @Override
    public String toString() {
        return String.format("%d\t%d", getX(), getY());
    }
}
