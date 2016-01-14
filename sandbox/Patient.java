import java.util.List;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Patient {
    private List<Medicine> clashingMedicines;
    public Patient(List<Medicine> clashingMedicines) {
        this.clashingMedicines = clashingMedicines;
    }

    public long clash(List<String> medicineNames, long daysBeforeToday) {
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

    private long calculateOverlappedDays(List<Medicine> clashingMedicines) {
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

        if (startA.isAfter(startB)) {
            return startA.until(endB, ChronoUnit.DAYS);
        } else {
            return startB.until(endA, ChronoUnit.DAYS);
        }

    }
}
