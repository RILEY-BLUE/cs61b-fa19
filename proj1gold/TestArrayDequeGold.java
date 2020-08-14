/**
 * @ClassName: TestArrayDequeGold
 * @Author: Yurui DU
 * @Description: A rudimentary autograder for project 1A
 * @Date: 20200814 09:58
 * @Version: 0.0
 */
import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void testAutoGrader() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> sad2 = new ArrayDequeSolution<>();
        //addFirst
        int numberBetweenZeroAndOneHundred = StdRandom.uniform(100);
        sad1.addFirst(numberBetweenZeroAndOneHundred);
        sad2.addFirst(numberBetweenZeroAndOneHundred);

        Integer expect, actual;
        expect = sad2.get(0);
        actual = sad1.get(0);
        assertEquals("addFirst(" + numberBetweenZeroAndOneHundred
                + ")\n", expect, actual);
        System.out.println("addFirst(" + numberBetweenZeroAndOneHundred
                + ")");

        //addLast
        numberBetweenZeroAndOneHundred = StdRandom.uniform(100);
        sad1.addFirst(numberBetweenZeroAndOneHundred);
        sad2.addFirst(numberBetweenZeroAndOneHundred);

        expect = sad2.get(1);
        actual = sad1.get(1);
        assertEquals("addLast(" + numberBetweenZeroAndOneHundred
                + ")\n", expect, actual);
        System.out.println("addLast(" + numberBetweenZeroAndOneHundred
                + ")");

        //removeFirst

        expect = sad2.removeFirst();
        actual = sad1.removeFirst();
        assertEquals("removeFirst()\n", expect, actual);
        System.out.println("removeFirst()");

        //removeLast

        expect = sad2.removeLast();
        actual = sad1.removeLast();
        assertEquals("removeLast()\n", expect, actual);
        System.out.println("removeLast()");
    }
}