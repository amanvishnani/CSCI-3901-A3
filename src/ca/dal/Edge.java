package ca.dal;

/**
 * @author Aman Vishnani (aman.vishnani@dal.ca) [CSID: vishnani]
 */
public class Edge {
    private Vertex vertex1;
    private Vertex vertex2;

    public Vertex getVertex1() {
        return vertex1;
    }

    public void setVertex1(Vertex vertex1) {
        this.vertex1 = vertex1;
    }

    public Vertex getVertex2() {
        return vertex2;
    }

    public void setVertex2(Vertex vertex2) {
        this.vertex2 = vertex2;
    }

    public Edge(Vertex v1, Vertex v2) {
        setVertex1(v1);
        setVertex2(v2);
    }

    /**
     * Method to calculate weight of the edge.
     * @return the weight/distance of edge.
     */
    public Double weight() {
        Double x = Math.pow(vertex1.getX() - vertex2.getX(), 2);
        Double y = Math.pow(vertex1.getY() - vertex2.getY(), 2);
        return Math.sqrt(x+y);
    }

    /**
     * Checks if two edges are equal. Covers bidirectional check, ie, Edge(v1,v2) == Edge(v2,v1)
     * @param o edge to check with.
     * @return true if bot edges are equal. false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge)) return false;
        Edge edge = (Edge) o;
        Boolean outcome1 =  getVertex1().equals(edge.getVertex1()) &&
                getVertex2().equals(edge.getVertex2());
        Boolean outcome2 = getVertex1().equals(edge.getVertex2()) &&
                getVertex2().equals(edge.getVertex1());
        return outcome1 || outcome2;
    }

    /**
     * Hash function for HashMaps.
     * @return the hash code.
     */
    @Override
    public int hashCode() {
        return getVertex1().getX() * getVertex1().getY() * getVertex2().getX() * getVertex2().getY();
    }
}
