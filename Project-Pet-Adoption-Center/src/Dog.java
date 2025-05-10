import java.util.HashSet;

public class Dog extends Pet {

    // Additional attributes
    private String trainingLevel;
    private int barkingLevel;
    private HashSet<String> trainingLevelTypes;

    // Additional behaviors

    public Dog(){
        super();
        this.trainingLevel = "NA";
        this.barkingLevel = 0;

        // Default Training Levels
        this.trainingLevelTypes = new HashSet<>();
        this.trainingLevelTypes.add("LOW");
        this.trainingLevelTypes.add("MEDIUM");
        this.trainingLevelTypes.add("HIGH");
    }

    public Dog(String name, String species, int age, String breed, String adoptionStatus){
        super(name, species, age, breed, adoptionStatus);
        this.trainingLevel = "NA";
        this.barkingLevel = 0;

        // Default Training Levels
        this.trainingLevelTypes = new HashSet<>();
        this.trainingLevelTypes.add("LOW");
        this.trainingLevelTypes.add("MEDIUM");
        this.trainingLevelTypes.add("HIGH");
    }

    public Dog(String id, String name, String species, int age, String breed, String adoptionStatus){
        super(id, name, species, age, breed, adoptionStatus);
        this.trainingLevel = "NA";
        this.barkingLevel = 0;

        // Default Training Levels
        this.trainingLevelTypes = new HashSet<>();
        this.trainingLevelTypes.add("LOW");
        this.trainingLevelTypes.add("MEDIUM");
        this.trainingLevelTypes.add("HIGH");
    }

    public Dog(String id, String name, String species, int age, String breed, String adoptionStatus, String trainingLevel, int barkingLevel){
        super(id, name, species, age, breed, adoptionStatus);
        this.trainingLevel = trainingLevel;
        this.barkingLevel = barkingLevel;

        // Default Training Levels
        this.trainingLevelTypes = new HashSet<>();
        this.trainingLevelTypes.add("LOW");
        this.trainingLevelTypes.add("MEDIUM");
        this.trainingLevelTypes.add("HIGH");
    }

    public Dog(String petName, String petSpecies, int petAge, String petBreed, String petAdoptionStatus, String dogTrainingLevel, int dogBarkingLevel) {
        super(petName, petSpecies, petAge, petBreed, petAdoptionStatus);
        this.trainingLevel = dogTrainingLevel;
        this.barkingLevel = dogBarkingLevel;
    }

    public HashSet<String> getTrainingLevelTypes() {
        return trainingLevelTypes;
    }

    public void setTrainingLevelTypes(HashSet<String> trainingLevelTypes) {
        this.trainingLevelTypes = trainingLevelTypes;
    }

    public int getBarkingLevel() {
        return barkingLevel;
    }

    public void setBarkingLevel(int barkingLevel) {
        this.barkingLevel = barkingLevel;
    }

    public String getTrainingLevel() {
        return trainingLevel;
    }

    public void setTrainingLevel(String trainingLevel) {
        this.trainingLevel = trainingLevel;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "trainingLevel='" + trainingLevel + '\'' +
                ", barkingLevel=" + barkingLevel +
                "} " + super.toString();
    }

}
