package hospital;

public class Patient {
    private String name;
    private int birthYear;
    private String address;
    private final Disease disease;

    public Patient(String name, int birthYear, String address, Disease disease) {
        this.name = name;
        this.birthYear = birthYear;
        this.address = address;
        this.disease = disease;
    }

    public Disease getDisease() {
        return disease;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    @Override
    public String toString() {
        return " " +
                name +
                ", " + birthYear +
                ", " + address +
                ", " + disease +
                "} \n";
    }
}
