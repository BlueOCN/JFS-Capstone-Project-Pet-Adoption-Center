import java.util.UUID;

public class Pet implements Adoptable, Vaccinable{

    // Attributes: petId, name, species, age, breed, adoptionStatus
    private String petId;
    private String name;
    private String species;
    private int age;
    private String breed;
    private String adoptionStatus;

    Pet(){
        this.petId = UUID.randomUUID().toString();
        this.name = "NA";
        this.species = "NA";
        this.age = 0;
        this.breed = "NA";
        this.adoptionStatus = "NA";
    }

    Pet(String name, String species, int age, String breed, String adoptionStatus){
        this.petId = UUID.randomUUID().toString();
        this.name = name;
        this.species = species;
        this.age = age;
        this.breed = breed;
        this.adoptionStatus = adoptionStatus;
    }

    Pet(String petId, String name, String species, int age, String breed, String adoptionStatus){
        this.petId = petId;
        this.name = name;
        this.species = species;
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

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
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
    public void adopt() {
        this.setAdoptionStatus("ADOPTED");
    }

    @Override
    public void vaccinate(String vaccineName) {
        System.out.println(getName() + " received the " + vaccineName + " vaccine.");
    }

    // Methods

    public void updatePet(String name, String species, int age, String breed, String adoptionStatus){
        this.setName(name);
        this.setSpecies(species);
        this.setAge(age);
        this.setBreed(breed);
        this.setAdoptionStatus(adoptionStatus);
    }

    @Override
    public String toString() {
        return "Pet{" +
                "petId='" + petId + '\'' +
                ", name='" + name + '\'' +
                ", species='" + species + '\'' +
                ", age=" + age +
                ", breed='" + breed + '\'' +
                ", adoptionStatus='" + adoptionStatus + '\'' +
                '}';
    }
}
