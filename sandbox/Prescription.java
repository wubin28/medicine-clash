import java.time.LocalDate;

public class Prescription {
    private LocalDate dispenseDate;

    public Prescription(LocalDate dispenseDate, int daysSupply) {
        this.dispenseDate = dispenseDate;
    }

    public LocalDate getDispenseDate() {
        return this.dispenseDate;
    }
}