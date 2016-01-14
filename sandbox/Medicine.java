import java.util.List;

public class Medicine {
    private List<Prescription> prescriptions;

    public Medicine(String name, List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public List<Prescription> getPrescriptions() {
        return this.prescriptions;
    }
}