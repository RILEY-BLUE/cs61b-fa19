/**
 * @ClassName OffByN
 * @Author Yurui Du
 * @Date 20200813 13:46
 * @Version 0.0
 */
public class OffByN implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        return (Math.abs(x - y) == N);
    }

    private int N;

    public OffByN(int N) {
        this.N = N;
    }
}