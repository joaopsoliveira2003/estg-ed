import Collections.HashTables.HashSet;
import Collections.HashTables.SetADT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HashSetTest {
    private SetADT<Integer> set;

    @BeforeEach
    public void setUp() {
        set = new HashSet<>();
    }

    @Test
    public void testAdd() {
        int value = 1;
        set.add(value);
        assert set.contains(value);
    }

    @Test
    public void testRemove() {
        int value = 1;
        set.add(value);
        set.remove(value);
        assert !set.contains(value);
    }

    @Test
    public void testFind() {
        int value = 1;
        set.add(value);
        assert set.find(value) == value;
    }

    @Test
    public void testContains() {
        int value = 1;
        set.add(value);
        assert set.contains(value);
    }

    @Test
    public void testResize() {
        for (int i = 0; i < 100; i++) {
            set.add(i);
        }
        assert set.size() == 100;
    }

    @Test
    public void testAddDuplicate() {
        int value = 1;
        set.add(value);
        set.add(value);
        assert set.size() == 1;
    }


    @Test
    public void testContainsNonExistent() {
        int value = 1;
        assert !set.contains(value);
    }
}
