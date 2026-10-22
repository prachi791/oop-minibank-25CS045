
class Point {
    private int x;
    private int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "Point(" + x + "," + y + ")";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Point)) {
            return false;
        }

        Point o = (Point) obj;

        if (x == o.x && y == o.y) {
            return true;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return 31 * x + y;
    }
}
