package graphs;

public class Edge {
    private int start;
    private int end;

    public Edge(int start, int end) {
        if (start < 0 || end < 0) {
            throw new IndexOutOfBoundsException("Neither start nor end vertex may be non-positive");
        }
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}
