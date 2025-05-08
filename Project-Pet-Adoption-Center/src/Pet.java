import java.util.UUID;

public class Pet implements Adoptable, Vaccinable{

    // Attributes: petId, name, species, age, breed, adoptionStatus
    private String petId;
    private String name;
    private int age;
    private String breed;
    private String adoptionStatus;

    Pet(){
        this.petId = UUID.randomUUID().toString();
        this.name = "NA";
        this.age = 0;
        this.breed = "NA";
        this.adoptionStatus = "NA";
    }

    Pet(String name, int age, String breed, String adoptionStatus){
        this.petId = UUID.randomUUID().toString();
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.adoptionStatus = adoptionStatus;
    }

    // Getters and Setters:

    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getAdoptionStatus() {
        return adoptionStatus;
    }

    public void setAdoptionStatus(String adoptionStatus) {
        this.adoptionStatus = adoptionStatus;
    }

    // Interfaces

    @Override
    public void adopt(String adopterName) {
        this.setAdoptionStatus("ADOPTED");
        System.out.println(getName() + " has been adopted by " + adopterName + "!");
    }

    @Override
    public void vaccinate(String vaccineName) {
        System.out.println(getName() + " received the " + vaccineName + " vaccine.");
    }

    // Methods


    @Override
    public String toString() {
        return "Pet{" +
                "petId='" + petId + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", breed='" + breed + '\'' +
                ", adoptionStatus='" + adoptionStatus + '\'' +
                '}';
    }
}
