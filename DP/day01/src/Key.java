// https://stackoverflow.com/questions/14677993/how-to-create-a-hashmap-with-two-keys-key-pair-value
public class Key {

    private final int i;
    private final int j;

    public Key(int i, int j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Key)) return false;
        Key key = (Key) o;
        return i == key.i && j == key.j;
    }

    @Override
    public int hashCode() {
        int result = i;
        result = 31 * result + j;
        return result;
    }

}