import java.util.List;
import java.time.LocalDate;

public class Patient {
    private List<Medicine> clashingMedicines;

    public Patient(List<Medicine> clashingMedicines) {
        this.clashingMedicines = clashingMedicines;
    }

    public int clash(List<String> medicineNames, int daysBeforeToday) {
        if (this.clashingMedicines.size() <= 1) {
            return 0;
        }
        if (!isOverlapped(clashingMedicines)) {
            return 0;
        }
        return calculateOverlappedDays(clashingMedicines);
    }

    private boolean isOverlapped(List<Medicine> clashingMedicines) {
        Prescription prescriptionA = 
            clashingMedicines.get(0).getPrescriptions().get(0);
        LocalDate startA = 
            prescriptionA.getDispenseDate();
        LocalDate endA = 
            prescriptionA.getDispenseDate().plusDays(prescriptionA.getDaysSupply());
        Prescription prescriptionB = 
            clashingMedicines.get(1).getPrescriptions().get(0);
        LocalDate startB = 
            prescriptionB.getDispenseDate();
        LocalDate endB = 
            prescriptionB.getDispenseDate().plusDays(prescriptionB.getDaysSupply());
        
        return !(startA.isAfter(endB) || endA.isBefore(startB)) ? 
            true : false;
    }

    private int calculateOverlappedDays(List<Medicine> clashingMedicines) {
        Prescription prescriptionA = 
            clashingMedicines.get(0).getPrescriptions().get(0);
        LocalDate startA = 
            prescriptionA.getDispenseDate();
        LocalDate endA = 
            prescriptionA.getDispenseDate().plusDays(prescriptionA.getDaysSupply());
        Prescription prescriptionB = 
            clashingMedicines.get(1).getPrescriptions().get(0);
        LocalDate startB = 
            prescriptionB.getDispenseDate();
        LocalDate endB = 
            prescriptionB.getDispenseDate().plusDays(prescriptionB.getDaysSupply());

        return startA.until(endB, ChronoUnit.DAYS);
    }
}
