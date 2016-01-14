import java.util.List;

public class Patient {
    public Patient(List<Medicine> clashingMedicines) {
    }

    public int clash(List<String> medicineNames, int daysBeforeToday) {
        if (this.clashingMedicines.size() == 1) {
            return 0;
        }
        return -1;
    }
}
