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

    public long clash(List<String> medicineNames, long daysBeforeToday
            , LocalDate now) {
        if (this.medicine == null || this.clashingMedicine == null) {
            return 0;
        }
        if (!isOverlapped(medicine, clashingMedicine
                , daysBeforeToday, now)) {
            return 0;
        }
        return calculateOverlappedDays(medicine, clashingMedicine
            , daysBeforeToday, now);
    }

    private boolean isOverlapped(Medicine medicine
            , Medicine clashingMedicine
            , long daysBeforeToday, LocalDate now) {
        LocalDate startA = getStartDate(medicine);
        LocalDate endA = getEndDate(medicine);
        LocalDate startB = getStartDate(clashingMedicine);
        LocalDate endB = getEndDate(clashingMedicine);
        
        LocalDate dateAfterConsidered = 
            now.minusDays(daysBeforeToday);
        return !(startA.isAfter(endB) || endA.isBefore(startB)) 
            ? true : false;
    }

    private long calculateOverlappedDays(Medicine medicine
            , Medicine clashingMedicine
            , long daysBeforeToday
            , LocalDate now) {
        LocalDate startA = getStartDate(medicine);
        LocalDate endA = getEndDate(medicine);
        LocalDate startB = getStartDate(clashingMedicine);
        LocalDate endB = getEndDate(clashingMedicine);

        LocalDate dateAfterConsidered = 
            now.minusDays(daysBeforeToday);
        if (startA.isAfter(startB)) {
            if (dateAfterConsidered.isAfter(startA) &&
                    dateAfterConsidered.isBefore(endB)) {
                return dateAfterConsidered.until(endB, ChronoUnit.DAYS);
            }
            if (now.isAfter(startA) &&
                    now.isBefore(endB)) {
                return startA.until(now, ChronoUnit.DAYS);
            }
            return startA.until(endB, ChronoUnit.DAYS);
        } else {
            if (dateAfterConsidered.isAfter(startB) &&
                    dateAfterConsidered.isBefore(endA)) {
                return dateAfterConsidered.until(endA, ChronoUnit.DAYS);
            }
            if (now.isAfter(startB) &&
                    now.isBefore(endA)) {
                return startB.until(now, ChronoUnit.DAYS);
            }
            return startB.until(endA, ChronoUnit.DAYS);
        }
    }

    private LocalDate getStartDate(Medicine medicine) {
        LocalDate startDate = 
            medicine.getPrescriptions().get(0).getDispenseDate();
        return startDate;
    }

    private LocalDate getEndDate(Medicine medicine) {
        int lastIndex = medicine.getPrescriptions().size() - 1;
        LocalDate endDate = 
            medicine.getPrescriptions().get(lastIndex).getDispenseDate()
            .plusDays(medicine.getPrescriptions().get(lastIndex)
                .getDaysSupply());
        return endDate;
    }
}