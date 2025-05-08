import java.util.ArrayList;
import java.util.HashSet;

public class PetAdoptionCenter {

    // Collection of pets
    private ArrayList<Pet> petsCollection;
    // Collection of adopters
    private ArrayList<Adopter> adoptersCollection;
    // Set of Adoption Status Types
    private HashSet<String> adoptionStatusTypes;


    PetAdoptionCenter() {
        this.petsCollection = new ArrayList<>();
        this.adoptersCollection = new ArrayList<>();
        this.adoptionStatusTypes = new HashSet<>();

        // Default Adoption Status Types
        this.adoptionStatusTypes.add("AVAILABLE");
        this.adoptionStatusTypes.add("PENDING_ADOPTION");
        this.adoptionStatusTypes.add("ADOPTED");
        this.adoptionStatusTypes.add("RETURNED");
        this.adoptionStatusTypes.add("FOSTERED");
        this.adoptionStatusTypes.add("UNAVAILABLE");
    }

    // Methods to manage pets
    // - Add new pet to the collection of pets
    public void addPet(Pet pet) {}
    public void addPet(Pet... pet) {}
    // - Update pet information on the collection of pets
    // - Mark the pet as adopted
    public void updateAdoptionStatus(Pet pet, String status) {}
    // - Remove pets from the system

    // Methods from the adoption process
    // - Allow adopters to view available pets
    // - Allow adopters to select a pet to adopt
    // - Allow adopters to register as the new owner
    public void registerAdopter(Adopter adopter, Pet pet) {}
    // - Allow adopters to update the pet`s adoption status

    // Search and Filter Pets
    // - Search for pets based on species, age, breed or availability
    public Pet findPetByFilter(String filter, String value){ return null; }
    public void displayPets() {}
    public void displayPets(String filter) {}

}
