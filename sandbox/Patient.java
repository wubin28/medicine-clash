import java.util.List;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Patient {
    private Medicine medicine;
    private Medicine clashingMedicine;

    public Patient(Medicine medicine, Medicine clashingMedicine) {
        this.medicine = medicine;
        this.clashingMedicine = clashingMedicine;
    }

    public long clash(List<String> medicineNames, long daysBeforeToday) {
        if (this.medicine == null || this.clashingMedicine == null) {
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
