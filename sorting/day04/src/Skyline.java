import java.util.*;


public class Skyline {

    static class Point {
        int x, y;

        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Building {
        private int l, r, h;

        Building(int l, int r, int h) {
            this.l = l;
            this.r = r;
            this.h = h;
        }
    }

    // Given an array of buildings, return a list of points representing the skyline
    public static List<Point> skyline(Building[] B) {
        // base case
        if (B.length == 1) {
            LinkedList<Point> l = new LinkedList<>();
            l.add(new Point(B[0].l, B[0].h));
            l.add(new Point(B[0].r, 0));
            return l;
        }

        // divide
        Building[] l = Arrays.copyOfRange(B, 0, B.length / 2);
        Building[] r = Arrays.copyOfRange(B, B.length / 2, B.length);

        // merge
        return merge(skyline(l), skyline(r));
    }

    // Merge function for two lists of points (skylines)
    public static List<Point> merge(List<Point> l1, List<Point> l2) {
        LinkedList<Point> l = new LinkedList<>();

        int h1 = 0; int h2 = 0;
        int h = 0; int x = 0;

        while (l1.size() > 0 && l2.size() > 0) {
            Point p1 = l1.get(0);
            Point p2 = l2.get(0);

            // find smaller x coordinate and update x and h to add new point. Remove chosen point
            if (p1.x < p2.x) {
                x = p1.x;
                h1 = p1.y;
                h = Math.max(h1, h2);
                l1.remove(0);
            } else if (p2.x < p1.x) {
                x = p2.x;
                h2 = p2.y;
                h = Math.max(h1, h2);
                l2.remove(0);
            } else { // same x coordinate. This can happen when two buildings end on the same x coordinate
                x = p1.x;
                h1 = p1.y;
                h2 = p2.y;
                h = Math.max(h1, h2);
                l1.remove(0);
                l2.remove(0);
            }


            if (l.size() == 0 || h != l.getLast().y) { // if empty don't check for last, short circuit
                l.add(new Point(x, h));
            }
        }
        l.addAll(l1);
        l.addAll(l2);
        return l;
    }

}
