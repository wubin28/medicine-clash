import org.junit.*;
import static org.junit.Assert.*;

public class PatientTest {

    @Test
    public void no_clash_when_not_taking_both_medicines() {
        Prescription prescription = new Prescription(dispenseDate, 30);
        Medicine codeine = new Codeine(Arrays.asList(prescription));
        assertEquals(0, patient.clash(Arrays.asList(codeine, prozac), 90));
    }

    // TODO: no_clash_when_not_taking_both_medicines
    // TODO: no_clash_when_no_overlap
    // TODO: clash_when_medicines_taken_overlapping
    // TODO: clash_when_medicines_taken_overlapping_start_of_period
    // TODO: clash_when_medicines_taken_overlapping_current_date
    // TODO: clash_when_medicines_taken_continuously_with_the_same_period
    // TODO: clash_when_medicines_taken_continuously_with_partial_overlap
}
