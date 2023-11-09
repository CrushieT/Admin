package VotingSystem.Model;

public class Candidate {
    private String name;
    private String surname;
    private String position;
    private String imageBase64; // Image data in Base64 format

    public Candidate(String name, String surname, String position, String imageBase64) {
        this.name = name;
        this.surname = surname;
        this.position = position;
        this.imageBase64 = imageBase64;
    }

    // Getter and setter methods for the attributes

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public String toDataString() {
        // Create a string representation of the candidate's data
        return name + "," + surname + "," + position + "," + imageBase64;
    }
}
