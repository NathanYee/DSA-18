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
    }

    // return true if this rubik's cube is equal to the other rubik's cube
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RubiksCube))
            return false;
        RubiksCube other = (RubiksCube) obj;
        return this.hashCode() == other.hashCode();
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
        int total_hash = 0;
        for(Character face:this.faceNames) {
            total_hash += Arrays.hashCode(this.faces.get(face));
        }
        return total_hash;
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


    // Given a character in ['u', 'U', 'r', 'R', 'f', 'F'], return a new rubik's cube with the rotation applied
    // Do not modify this rubik's cube.
    public RubiksCube rotate(char c) {
        boolean isClockwise = Character.isLowerCase(c)
        int dir = isClockwise ? 1 : -1;
        Character C = Character.toUpperCase(c);
        Integer[] indeces = rotations.get(C);

        for(int i = (isClockwise ? 0 : indeces.length-1); i >= 0 && i < indeces.length; i += dir * 2) {
            int j = i + (dir * 2) % indeces.length;
            Integer[] current = this.faces.get(diplomacy.get(C)[i / 2]);
            Integer[] swapWith = this.faces.get(diplomacy.get(C)[j / 2]);

        }

        Integer faceStore;
        for (int i = 0; i < 8; i++) {
            int faceIdx = i / 2;
            int j = (i + 2) % 8;
            faceStore = this.faces.get(this.diplomacy.get(C)[j])[0];


        }

        return this;
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


    // return the list of rotations needed to solve a rubik's cube
    public List<Character> solve() {
        // TODO
        return new ArrayList<>();
    }

}



