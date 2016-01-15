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
        if (!isOverlapped(medicine, clashingMedicine)) {
            return 0;
        }
        return calculateOverlappedDays(medicine, clashingMedicine);
    }

    private boolean isOverlapped(Medicine medicine
            , Medicine clashingMedicine) {
        LocalDate startA = 
            medicine.getPrescriptions().get(0).getDispenseDate();
        LocalDate endA = 
            medicine.getPrescriptions().get(0).getDispenseDate()
                .plusDays(prescriptionA.getDaysSupply());
        LocalDate startB = 
            clashingMedicine.getPrescriptions().get(0).getDispenseDate();
        LocalDate endB = 
            clashingMedicine.getPrescriptions().get(0).getDispenseDate()
                .plusDays(clashingMedicine.getPrescriptions().get(0).getDaysSupply());
        
        return !(startA.isAfter(endB) || endA.isBefore(startB)) ? 
            true : false;
    }

    private long calculateOverlappedDays(Medicine medicine
            , Medicine clashingMedicine) {
        Prescription prescriptionA = 
            medicine.getPrescriptions().get(0);
        LocalDate startA = 
            prescriptionA.getDispenseDate();
        LocalDate endA = 
            prescriptionA.getDispenseDate().plusDays(prescriptionA.getDaysSupply());
        Prescription prescriptionB = 
            clashingMedicine.getPrescriptions().get(0);
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
