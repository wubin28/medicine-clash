import java.time.LocalDate;

public class Prescription {
    private LocalDate dispenseDate;
    private int daysSupply;

    public Prescription(LocalDate dispenseDate, int daysSupply) {
        this.dispenseDate = dispenseDate;
        this.daysSupply = daysSupply;
    }

    public LocalDate getDispenseDate() {
        return this.dispenseDate;
    }

    public int getDaysSupply() {
        return this.daysSupply;
    }
}