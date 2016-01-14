import java.util.List;

public class Patient {
    private List<Medicine> clashingMedicines;

    public Patient(List<Medicine> clashingMedicines) {
        this.clashingMedicines = clashingMedicines;
    }

    public int clash(List<String> medicineNames, int daysBeforeToday) {
        if (this.clashingMedicines.size() == 1) {
            return 0;
        }
        return 0;
    }
}
