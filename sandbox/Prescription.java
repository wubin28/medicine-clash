import java.time.LocalDate;

public class Prescription {
    private LocalDate dispenseDate;
    private long daysSupply;

    public Prescription(LocalDate dispenseDate, long daysSupply) {
        this.dispenseDate = dispenseDate;
        this.daysSupply = daysSupply;
    }

    public LocalDate getDispenseDate() {
        return this.dispenseDate;
    }

    public long getDaysSupply() {
        return this.daysSupply;
    }
}