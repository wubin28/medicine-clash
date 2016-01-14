import org.junit.*;
import static org.junit.Assert.*;

public class HikerTest {

    @Test
    public void life_the_universe_and_everything() {
        int expected = 42;
        int actual = Hiker.answer();
        assertEquals(expected, actual);
    }

    // TODO: no_clash_when_not_taking_both_medicines
    // TODO: no_clash_when_no_overlap
    // TODO: clash_when_medicines_taken_overlapping
    // TODO: clash_when_medicines_taken_overlapping_start_of_period
    // TODO: clash_when_medicines_taken_overlapping_current_date
    // TODO: clash_when_medicines_taken_continuously_with_the_same_period
    // TODO: clash_when_medicines_taken_continuously_with_partial_overlap
}
