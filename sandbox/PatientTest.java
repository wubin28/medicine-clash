import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDate;
import java.util.Arrays;

public class PatientTest {

    @Test
    public void no_clash_when_not_taking_both_medicines() {
        LocalDate now = LocalDate.now();
        Prescription prescription = 
            new Prescription(now.minusDays(30), 30);
        Medicine codeine = new Medicine("Codeine", 
            Arrays.asList(prescription));
        Patient patient = new Patient(codeine, null);

        assertEquals(0, 
            patient.clash(Arrays.asList("Codeine", "Prozac"), 90, now));
    }

    @Test
    public void no_clash_when_no_overlap() {
        LocalDate now = LocalDate.now();
        Prescription prescriptionCodeine = 
            new Prescription(now.minusDays(90), 30);
        Medicine codeine = new Medicine("Codeine", 
            Arrays.asList(prescriptionCodeine));
        Prescription prescriptionProzac = 
            new Prescription(now.minusDays(30), 30);
        Medicine prozac = new Medicine("Prozac", 
            Arrays.asList(prescriptionProzac));
        Patient patient = new Patient(codeine, prozac);

        assertEquals(0, 
            patient.clash(Arrays.asList("Codeine", "Prozac"), 90, now));
    }

    @Test
    public void clash_when_medicines_taken_overlapping() {
        LocalDate now = LocalDate.now();
        Prescription prescriptionCodeine = 
            new Prescription(now.minusDays(30), 30);
        Medicine codeine = new Medicine("Codeine", 
            Arrays.asList(prescriptionCodeine));
        Prescription prescriptionProzac = 
            new Prescription(now.minusDays(40), 30);
        Medicine prozac = new Medicine("Prozac", 
            Arrays.asList(prescriptionProzac));
        Patient patient = new Patient(codeine, prozac);

        assertEquals(20, 
            patient.clash(Arrays.asList("Codeine", "Prozac"), 90, now));
    }

    @Test
    public void clash_when_medicines_taken_overlapping_start_of_period() {
        LocalDate now = LocalDate.now();
        Prescription prescriptionCodeine = 
            new Prescription(now.minusDays(91), 30);
        Medicine codeine = new Medicine("Codeine", 
            Arrays.asList(prescriptionCodeine));
        Prescription prescriptionProzac = 
            new Prescription(now.minusDays(119), 30);
        Medicine prozac = new Medicine("Prozac", 
            Arrays.asList(prescriptionProzac));
        Patient patient = new Patient(codeine, prozac);

        assertEquals(1, 
            patient.clash(Arrays.asList("Codeine", "Prozac"), 90, now));
    }

    @Test
    public void clash_when_medicines_taken_overlapping_current_date() {
        LocalDate now = LocalDate.now();
        Prescription prescriptionCodeine = 
            new Prescription(now.minusDays(1), 30);
        Medicine codeine = new Medicine("Codeine", 
            Arrays.asList(prescriptionCodeine));
        Prescription prescriptionProzac = 
            new Prescription(now.minusDays(5), 30);
        Medicine prozac = new Medicine("Prozac", 
            Arrays.asList(prescriptionProzac));
        Patient patient = new Patient(codeine, prozac);

        assertEquals(1, 
            patient.clash(Arrays.asList("Codeine", "Prozac"), 90, now));
    }

    @Test
    public void clash_when_medicines_taken_continuously_with_the_same_period() {
        LocalDate now = LocalDate.now();
        Medicine codeine = new Medicine("Codeine", 
            Arrays.asList(
                new Prescription(now.minusDays(90), 30)
                , new Prescription(now.minusDays(60), 30)
                , new Prescription(now.minusDays(30), 30)
            ));
        Medicine prozac = new Medicine("Prozac", 
            Arrays.asList(
                new Prescription(now.minusDays(90), 30)
                , new Prescription(now.minusDays(60), 30)
                , new Prescription(now.minusDays(30), 30)
            ));
        Patient patient = new Patient(codeine, prozac);

        assertEquals(90, 
            patient.clash(Arrays.asList("Codeine", "Prozac"), 90, now));
    }

    // TODO: clash_when_medicines_taken_continuously_with_partial_overlap
}
