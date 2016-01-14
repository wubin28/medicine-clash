import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDate;
import java.util.Arrays;

public class PatientTest {

    @Test
    public void no_clash_when_not_taking_both_medicines() {
        Prescription prescription = 
            new Prescription(LocalDate.now().minusDays(30), 30);
        Medicine codeine = new Medicine("Codeine", 
            Arrays.asList(prescription));
        Patient patient = new Patient(Arrays.asList(codeine));

        assertEquals(0, 
            patient.clash(Arrays.asList("Codeine", "Prozac"), 90));
    }

    @Test
    public void no_clash_when_no_overlap() {
        Prescription prescriptionCodeine = 
            new Prescription(LocalDate.now().minusDays(90), 30);
        Medicine codeine = new Medicine("Codeine", 
            Arrays.asList(prescriptionCodeine));
        Prescription prescriptionProzac = 
            new Prescription(LocalDate.now().minusDays(30), 30);
        Medicine prozac = new Medicine("Prozac", 
            Arrays.asList(prescriptionProzac));
        Patient patient = new Patient(Arrays.asList(codeine, prozac));

        assertEquals(0, 
            patient.clash(Arrays.asList("Codeine", "Prozac"), 90));
    }

    @Test
    public void clash_when_medicines_taken_overlapping() {
        Prescription prescriptionCodeine = 
            new Prescription(LocalDate.now().minusDays(30), 30);
        Medicine codeine = new Medicine("Codeine", 
            Arrays.asList(prescriptionCodeine));
        Prescription prescriptionProzac = 
            new Prescription(LocalDate.now().minusDays(40), 30);
        Medicine prozac = new Medicine("Prozac", 
            Arrays.asList(prescriptionProzac));
        Patient patient = new Patient(Arrays.asList(codeine, prozac));

        assertEquals(20, 
            patient.clash(Arrays.asList("Codeine", "Prozac"), 90));
    }

    // TODO: clash_when_medicines_taken_overlapping_start_of_period
    // TODO: clash_when_medicines_taken_overlapping_current_date
    // TODO: clash_when_medicines_taken_continuously_with_the_same_period
    // TODO: clash_when_medicines_taken_continuously_with_partial_overlap
}
