import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

public class MCCR {
        public static int MCCR(EdgeWeightedGraph G) {
            HashSet<Integer> visited = new HashSet<>();
            PriorityQueue<Edge> edgePQ = new PriorityQueue<>(Comparator.comparingInt(Edge::weight));

            int distance = 0;

            // assume 0 is a vertex of G
            int v = 0;

            // keep going until all visited have been visited
            while (visited.size() < G.numberOfV()) {
                // add vertex to visited
                visited.add(v);

                // add all of v's edges to edge priority queue
                for (Edge e : G.edges(v)) {
                    edgePQ.offer(e);
                }

                // look through all edges in priority queue
                // until vertex not in visited has been found
                while (edgePQ.peek() != null) {
                    Edge e = edgePQ.poll();
                    v = e.either();

                    // check if both vertices have been visited
                    if (visited.contains(v) && visited.contains(e.other(v))) {
                        continue;
                    }

                    // use the vertex not in visited
                    v = visited.contains(v) ? e.other(v) : v;

                    // add to distance
                    // v will be added to visited in the next loop
                    distance += e.weight();
                    break;
                }
            }

            return distance;
        }

    }

