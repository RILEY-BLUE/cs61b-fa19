/**
 * @ClassName: TestArrayDequeGold
 * @Author: Yurui DU
 * @Description: A rudimentary autograder for project 1A
 * @Date: 20200814 09:58
 * @Version: 1.0
 */
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList; // A quick touch to make failure sequences.
import java.util.List;

public class TestArrayDequeGold {
    @Test
    public void testArrayDeque() {
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        // addLast
        for (int i=0; i<10; i++) {
            int random = StdRandom.uniform(100);
            ads.addLast(random);
            sad.addLast(random);
        }
        for (int i=0; i<10; i++) {
            int actual = ads.get(i);
            int expected = sad.get(i);
            assertEquals("Oh noooo!\nThis is bad in addLast():\n   Random number " + actual
                            + " not equal to " + expected + "!",
                    expected, actual);
        }

        // addFirst
        for (int i=0; i<10; i++) {
            int random = StdRandom.uniform(100);
            ads.addFirst(random);
            sad.addFirst(random);
        }
        for (int i=0; i<10; i++) {
            int actual = ads.get(i);
            int expected = sad.get(i);
            assertEquals("Oh noooo!\nThis is bad in addFirst():\n   Random number " + actual
                            + " not equal to " + expected + "!",
                    expected, actual);
        }

        // removeFirst
        List<Integer> actualList = new ArrayList<>();
        List<Integer> expectedList = new ArrayList<>();
        for (int i=0; i<10; i++) {
            actualList.add(ads.removeFirst());
            expectedList.add(sad.removeFirst());
        }
        for (int i=0; i<10; i++) {
            int actual = ads.get(i);
            int expected = sad.get(i);
            assertEquals("removeFirst()",
                    expected, actual);
        }
        for (int i=0; i<10; i++) {
            int actual = actualList.get(i);
            int expected = expectedList.get(i);
            assertEquals("Oh noooo!\nThis is bad in removeFirst():\n   Random number " + actual
                            + " not equal to " + expected + "!",
                    expected, actual);
        }


        // removeLast
        actualList.clear();
        expectedList.clear();
        for (int i=0; i<10; i++) {
            actualList.add(ads.removeLast());
            expectedList.add(sad.removeLast());
        }
        int actual = ads.size();
        int expected = sad.size();
        assertEquals("removeLast()",
                expected, actual);
        for (int i=0; i<10; i++) {
            assertEquals("removeLast()",
                    expectedList.get(i), actualList.get(i));
        }

    }

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