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

}
