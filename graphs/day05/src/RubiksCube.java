import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

// use this class if you are designing your own Rubik's cube implementation
public class RubiksCube {
    Map<Character, Integer[]> faces = new HashMap<>();
    HashMap<Character, Character[]> diplomacy = new HashMap<>(); // faces have diplomatic relationships with other faces
    HashMap<Character, Integer[]> rotations = new HashMap<>();
    Character[] faceNames = {'F', 'U', 'L', 'R', 'B', 'D'};
    private int solvedHash;
    private int hash;
    private Integer moves;
    private RubiksCube prev = null;
    private Character rotation = null;

    private void initDiplomacy() {
        Character F[] = {'L', 'U', 'R', 'D'};
        Character U[] = {'L', 'B', 'R', 'F'};
        Character R[] = {'F', 'U', 'B', 'D'};

        diplomacy.put('F', F);
        diplomacy.put('U', U);
        diplomacy.put('R', R);
    }

    private void initRotations() {
        Integer F[] = {2,1,3,2,0,3,1,0};
        Integer U[] = {0,1,0,1,0,1,0,1};
        Integer R[] = {1,2,1,2,3,0,1,2};

        rotations.put('F', F);
        rotations.put('U', U);
        rotations.put('R', R);
    }

    private void initFaces() {
        for (int i = 0; i < faceNames.length; i++) {
            Integer[] face = {i, i, i, i}; // ul, ur, lr, ll
            faces.put(faceNames[i], face);
        }

        solvedHash = this.hashCode();
    }


    // initialize a solved rubiks cube
    public RubiksCube() {
        initDiplomacy();
        initRotations();
        initFaces();
    }


    // creates a copy of the rubics cube
    public RubiksCube(RubiksCube r) {
        initDiplomacy();
        initRotations();
        initFaces();
        for (Character faceName : faceNames) {
            faces.put(faceName, r.faces.get(faceName).clone());
        }
        this.prev = r;
    }

    // return true if this rubik's cube is equal to the other rubik's cube
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RubiksCube))
            return false;
        RubiksCube other = (RubiksCube) obj;
        return this.hash == other.hash;
//        return this.hashCode() == other.hashCode();
    }

    /**
     * return a hashCode for this rubik's cube.
     *
     * Your hashCode must follow this specification:
     *   if A.equals(B), then A.hashCode() == B.hashCode()
     *
     * Note that this does NOT mean:
     *   if A.hashCode() == B.hashCode(), then A.equals(B)
     */
    @Override
    public int hashCode() {
        int totalHash = 1;
        for(Character face:this.faceNames) {
            for (Integer i : this.faces.get(face)) {
                totalHash = 31 * totalHash + i;
            }
        }
        return totalHash;
    }

    public boolean isSolved() {
        return hashCode() == solvedHash;
    }


    // given a list of rotations, return a rubik's cube with the rotations applied
    public RubiksCube rotate(List<Character> c) {
        RubiksCube rub = this;
        for (char r : c) {
            rub = rub.rotate(r);
        }
        return rub;
    }

    private void rotateFaceCounterClockwise(Integer face[])
    {
        Integer temp;
        int i;
        temp = face[0]; // Save the one that's going to be overwritten

        for (i = 0; i < face.length - 1; i++) {
            face[i] = face[i+1];
        }
        face[face.length-1] = temp;
    }

    private void rotateFaceClockwise(Integer face[])
    {
        Integer temp;
        int i;
        temp = face[face.length-1]; // Save the one that's going to be overwritten

        for (i = face.length-1; i > 0; i--) {
            face[i] = face[i-1];
        }
        face[0] = temp;
    }

    // Given a character in ['u', 'U', 'r', 'R', 'f', 'F'], return a new rubik's cube with the rotation applied
    // Do not modify this rubik's cube.
    public RubiksCube rotate(char c) {
        RubiksCube rotated = new RubiksCube(this);
        rotated.rotation = c;

        boolean isClockwise = Character.isLowerCase(c);
        int dir = isClockwise ? 1 : -1;
        Character C = Character.toUpperCase(c);
        Integer[] indeces = rotations.get(C);
        LinkedList<Integer> queue = new LinkedList<>();

        // store all numbers that will be rotated
        for(int i = (isClockwise ? 0:7); i < indeces.length && i > -1; i += dir) {
            int faceIndex = Math.floorMod(i/2, 4); // Index of desired face in diplomacy
            int j = Math.floorMod(i, indeces.length); // Wraps the index into indeces so that -1 is 7
            Character currentFace = diplomacy.get(C)[faceIndex]; // Selects the face we care about...
            queue.addLast(faces.get(currentFace)[indeces[j]]); // ...and stores the desired element from that face at the end of the queue
        }

        Integer clockwise[] = {2,3,4,5,6,7,0,1};
        Integer cclockwise[] = {5,4,3,2,1,0,7,6};
        // reassign all numbers that will be rotated
        for(Integer i:(isClockwise ? clockwise:cclockwise)) {
            int faceIndex = Math.floorMod(i/2, 4);

            Character currentFace = rotated.diplomacy.get(C)[faceIndex];
            rotated.faces.get(currentFace)[indeces[i]] = queue.poll();
            i+=dir;
        }

        // Rotate the face we are looking at
        if(isClockwise) rotateFaceClockwise(rotated.faces.get(C));
        else rotateFaceCounterClockwise(rotated.faces.get(C));

        rotated.hash = rotated.hashCode();

        return rotated;
    }

    // returns a random scrambled rubik's cube by applying random rotations
    public static RubiksCube scrambledCube(int numTurns) {
        RubiksCube r = new RubiksCube();
        char[] listTurns = getScramble(numTurns);
        for (int i = 0; i < numTurns; i++) {
            r = r.rotate(listTurns[i]);
        }
        return r;
    }

    public static char[] getScramble(int size){
        char[] listTurns = new char[size];
        for (int i = 0; i < size; i++) {
            switch (ThreadLocalRandom.current().nextInt(0, 6)) {
                case 0:
                    listTurns[i] = 'u';
                    break;
                case 1:
                    listTurns[i] = 'U';
                    break;
                case 2:
                    listTurns[i] = 'r';
                    break;
                case 3:
                    listTurns[i] = 'R';
                    break;
                case 4:
                    listTurns[i] = 'f';
                    break;
                case 5:
                    listTurns[i] = 'F';
                    break;
            }
        }
        return listTurns;
    }

    // Returns all of the options for how to rotate the cube to get the next step
    public Iterable<RubiksCube> neighbors() {
        LinkedList<RubiksCube> neighborhood = new LinkedList<>();
        Character singleMoves[] = {'r','R','u','U','f','F'};
        for(Character move:singleMoves) {
            neighborhood.add(this.rotate(move));
        }
        return neighborhood;
    }


    // return the list of rotations needed to solve a rubik's cube
    public List<Character> solve() {
//        Queue<RubiksCube> open = new PriorityQueue<>(5, Comparator.comparingInt(a -> a.moves));
        Queue<RubiksCube> open = new LinkedList<>();
        Map<RubiksCube, Integer> minCostVisited = new HashMap<>();
        open.add(this);

        whileLoop: while (open.peek() != null) {
            // retrieve the lowest cost cube
            RubiksCube q = open.poll();

            // add cube to visited
            minCostVisited.put(q, q.moves);

            // visit all the neighbors
            for (RubiksCube u : this.neighbors()) {
                if (u.isSolved()) {
                    break whileLoop;
                }

                if (minCostVisited.containsKey(u)) {
                    if (minCostVisited.get(u) > u.moves) {
                        open.add(u);
                    }
                } else {
                    open.add(u);
                }
            }
        }

        LinkedList<Character> solution = new LinkedList<>();

        while (this.prev != null) {
            solution.addFirst(this.rotation);
        }

        return solution;
    }

}



