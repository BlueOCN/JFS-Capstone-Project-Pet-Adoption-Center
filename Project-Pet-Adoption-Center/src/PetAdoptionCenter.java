import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class PetAdoptionCenter {

    // Collection of pets
    private ArrayList<Pet> petsCollection;
    // Collection of adopters
    private ArrayList<Adopter> adoptersCollection;
    // Set of Adoption Status Types
    private HashSet<String> adoptionStatusTypes;
    // Set of Pet Filter Types
    private HashSet<String> petFilterTypes;


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

        // Default Pet Filter Types
        this.petFilterTypes = new HashSet<>();
        this.petFilterTypes.add("NAME");
        this.petFilterTypes.add("SPECIES");
        this.petFilterTypes.add("AGE");
        this.petFilterTypes.add("BREED");
        this.petFilterTypes.add("ADOPTION_STATUS");
    }

    public ArrayList<Pet> getPetsCollection() {
        return petsCollection;
    }

    public void setPetsCollection(ArrayList<Pet> petsCollection) {
        this.petsCollection = petsCollection;
    }

    public ArrayList<Adopter> getAdoptersCollection() {
        return adoptersCollection;
    }

    public void setAdoptersCollection(ArrayList<Adopter> adoptersCollection) {
        this.adoptersCollection = adoptersCollection;
    }

    public HashSet<String> getAdoptionStatusTypes() {
        return adoptionStatusTypes;
    }

    public void setAdoptionStatusTypes(HashSet<String> adoptionStatusTypes) {
        this.adoptionStatusTypes = adoptionStatusTypes;
    }

    public HashSet<String> getPetFilterTypes() {
        return petFilterTypes;
    }

    public void setPetFilterTypes(HashSet<String> petFilterTypes) {
        this.petFilterTypes = petFilterTypes;
    }

    // Methods to manage pets
    // - Add new pet to the collection of pets
    public void addPet(Pet pet) {
        if (petsCollection.contains(pet)) {
            throw new IncorrectInputException("Pet is already in the collection");
        }
        this.petsCollection.add(pet);
    }
    public void addPet(Pet... pet) {
        this.petsCollection.addAll(Arrays.asList(pet));
    }

    public void addPet(ArrayList<Pet> petsDataSet) {
        this.petsCollection.addAll(petsDataSet);
    }
    // - Update pet information on the collection of pets
    // - Mark the pet as adopted
    public void updateAdoptionStatus(Pet pet, String status) {}
    // - Remove pets from the system

    // Methods from the adoption process
    // - Allow adopters to view available pets
    // - Allow adopters to select a pet to adopt
    // - Allow adopters to register as the new owner
    public void registerAdopter(Adopter adopter, Pet pet) {
        // Check if adopter is null
        if (adopter == null) {
            throw new IncorrectInputException("Adopter is null");
        }
        // Check is pet is null
        if (pet == null) {
            throw new IncorrectInputException("Pet is null");
        }

        // Check if the pet Adoption Status
        if (!pet.getAdoptionStatus().equalsIgnoreCase("AVAILABLE")) {
            throw new IncorrectInputException("Pet is not AVAILABLE");
        }

        // Change the Adoption Status of the Pet to ADOPTED
        Pet updatedPet = pet;
        updatedPet.adopt();
        // Add the pet to the Adopted Pets collection of the Adopter
        adopter.addAdoptedPet(updatedPet);
        // Add adopter to the adopter collection
        this.adoptersCollection.add(adopter);
        // Update the Adoption Status of the Pet in the Pet Collection to ADOPTED
        this.petsCollection.remove(pet);
        this.petsCollection.add(updatedPet);

    }
    // - Allow adopters to update the pet`s adoption status

    // Search and Filter Pets
    // - Search for pets based on species, age, breed or availability
    public <T> Pet findPetByFilter(String filter, T value){

        filter = filter.toUpperCase();
        if (!this.petFilterTypes.contains(filter)) {
            throw new IncorrectInputException("Invalid filter");
        }

        for (Pet pet : this.petsCollection) {
            if(filter.equals("NAME") && pet.getName().equals(value)){
                System.out.println(pet);
                return pet;
            }
            else if(filter.equals("SPECIES") && pet.getSpecies().equals(value)){
                System.out.println(pet);
                return pet;
            }
            else if(filter.equals("AGE") && value instanceof Integer && pet.getAge() == (Integer) value) {
                System.out.println(pet);
                return pet;
            }
            else if(filter.equals("BREED") && pet.getBreed().equals(value)){
                System.out.println(pet);
                return pet;
            }
            else if(filter.equals("ADOPTION_STATUS") && pet.getAdoptionStatus().equals(value)) {
                System.out.println(pet);
                return pet;
            }
        }
        return null;
    }


    public void displayPets() {
        System.out.println();
        for (Pet pet : petsCollection) {
            System.out.println(pet);
        }
        System.out.println();
    }


    public void displayPets(String adoptionStatus) {
        System.out.println();
        for (Pet pet : petsCollection) {
            if (adoptionStatus.equals(pet.getAdoptionStatus())) {
                System.out.println(pet);
            }
        }
        System.out.println();
    }

    public void displayAdopters() {
        System.out.println();
        for (Adopter adopter : adoptersCollection) {
            System.out.println(adopter);
        }
        System.out.println();
    }

}
