public class Dog extends Pet {

    // Additional attributes
    private String trainingLevel;
    private int barkingLevel;

    // Additional behaviors

    Dog(){
        super();
    }

    Dog(String name, String species, int age, String breed, String adoptionStatus){
        super(name, species, age, breed, adoptionStatus);
    }

    Dog(String id, String name, String species, int age, String breed, String adoptionStatus){
        super(id, name, species, age, breed, adoptionStatus);
    }

    @Override
    public String toString() {
        return "Dog{" +
                "trainingLevel='" + trainingLevel + '\'' +
                ", barkingLevel=" + barkingLevel +
                "} " + super.toString();
    }

}
