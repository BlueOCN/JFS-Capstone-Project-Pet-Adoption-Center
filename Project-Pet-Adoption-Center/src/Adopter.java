import java.util.HashSet;
import java.util.UUID;

public class Adopter {

    // Attributes: adopterId, name, contactInfo, adoptedPets(collection)

    private String adopterId;
    private String name;
    private String contactInfo;
    private HashSet<Pet> adoptedPetsCollection;

    public Adopter(){
        this.adopterId = UUID.randomUUID().toString();
        this.name = "NA";
        this.contactInfo = "NA";
        this.adoptedPetsCollection = new HashSet<>();
    }

    public Adopter(String name, String contactInfo){
        this.adopterId = UUID.randomUUID().toString();
        this.name = name;
        this.contactInfo = contactInfo;
        this.adoptedPetsCollection = new HashSet<>();
    }

    public String getAdopterId() {
        return adopterId;
    }

    public void setAdopterId(String adopterId) {
        this.adopterId = adopterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()){
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        if (contactInfo == null || contactInfo.isEmpty()){
            throw new IllegalArgumentException("Contact info cannot be null or empty");
        }
        this.contactInfo = contactInfo;
    }

    public HashSet<Pet> getAdoptedPetsCollection() {
        return adoptedPetsCollection;
    }

    public void setAdoptedPetsCollection(HashSet<Pet> adoptedPetsCollection) {
        this.adoptedPetsCollection = adoptedPetsCollection;
    }

    public void addAdoptedPet(Pet pet){
        if (adoptedPetsCollection.contains(pet)){
            throw new IncorrectInputException("Adopted pet is already taken by this adopter");
        }
        this.adoptedPetsCollection.add(pet);
    }

    public void removeAdoptedPet(Pet pet){
        if (!adoptedPetsCollection.contains(pet)){
            throw new IncorrectInputException("Adopted pet is not taken by this adopter");
        }
        this.adoptedPetsCollection.remove(pet);
    }

    @Override
    public String toString() {
        return "Adopter{" +
                "adopterId='" + adopterId + '\'' +
                ", name='" + name + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                ", adoptedPetsCollection=" + adoptedPetsCollection +
                '}';
    }
}
