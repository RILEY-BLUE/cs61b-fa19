/**
 * @ClassName OffByOne
 * @Author Yurui Du
 * @Date 20200813 10:39
 * @Version 0.0
 */
public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        return (x == y - 1 || x == y + 1);
    }
}