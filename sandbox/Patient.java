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
        if (!isOverlapped(medicine, clashingMedicine, daysBeforeToday)) {
            return 0;
        }
        return calculateOverlappedDays(medicine, clashingMedicine
            , daysBeforeToday);
    }

    private boolean isOverlapped(Medicine medicine
            , Medicine clashingMedicine
            , long daysBeforeToday) {
        LocalDate startA = getStartDate(medicine, daysBeforeToday);
        LocalDate endA = getEndDate(medicine, daysBeforeToday);
        LocalDate startB = getStartDate(clashingMedicine, daysBeforeToday);
        LocalDate endB = getEndDate(clashingMedicine, daysBeforeToday);
        
        return !(startA.isAfter(endB) || endA.isBefore(startB)) 
            ? true : false;
    }

    private long calculateOverlappedDays(Medicine medicine
            , Medicine clashingMedicine
            , long daysBeforeToday) {
        LocalDate startA = getStartDate(medicine);
        LocalDate endA = getEndDate(medicine);
        LocalDate startB = getStartDate(clashingMedicine);
        LocalDate endB = getEndDate(clashingMedicine);

        LocalDate dateAfterConsidered = 
            LocalDate.now().minusDays(daysBeforeToday);
        if (startA.isAfter(startB)) {
            return startA.until(endB, ChronoUnit.DAYS);
        } else {
            return startB.until(endA, ChronoUnit.DAYS);
        }
    }

    private LocalDate getStartDate(Medicine medicine) {
        LocalDate startDate = 
            medicine.getPrescriptions().get(0).getDispenseDate();
        return startDate;
    }

    private LocalDate getEndDate(Medicine medicine) {
        LocalDate endDate = 
            medicine.getPrescriptions().get(0).getDispenseDate()
            .plusDays(medicine.getPrescriptions().get(0)
                .getDaysSupply());
        return endDate;
    }
}