import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
        if (pet == null) {
            throw new IllegalArgumentException("Pet cannot be null");
        }
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

    public void importPets(HashMap<String, Pet> petHashMap) {
        this.petsCollection.addAll(petHashMap.values());
    }


    // - Update pet information on the collection of pets
    public void updatePet(Pet pet, Pet updatedPet) {
        if (pet == null || updatedPet == null) {
            throw new IncorrectInputException("Pet or updatedPet is null");
        }

        if (!petsCollection.contains(pet)) {
            throw new IncorrectInputException("Pet is not in the system");
        }

        // Find the adopter that contains the pet to be updated
        for (Adopter adopter : adoptersCollection) {
            // Get adopted pets collection of the adopter
            HashSet<Pet> adopterCollection = adopter.getAdoptedPetsCollection();
            // Find the pet to be updated
            for (Pet targetPet : adopterCollection) {
                // If pet is found remove pet from the collection and add the updated pet to the collection
                if (pet.equals(targetPet)) {
                    adopterCollection.remove(pet);
                    adopterCollection.add(updatedPet);
                }
            }
        }
        // Update the pet on the pet collection
        this.petsCollection.remove(pet);
        this.petsCollection.add(updatedPet);
    }


    // - Mark the pet as adopted
    public void updateAdoptionStatus(Pet pet, String status) {}


    // - Remove pets from the system
    public void removePet(Pet pet) {

        if (pet == null){
            throw new IncorrectInputException("Pet is null");
        }

        if (!petsCollection.contains(pet)) {
            throw new IncorrectInputException("Pet is not in the system");
        }

        // Remove pet from the user`s pet collection
        for (Adopter adopter : adoptersCollection) {
            if (adopter.getAdoptedPetsCollection().contains(pet)) {
                adopter.removeAdoptedPet(pet);
            }
        }

        // Remove pet from the pet collection
        petsCollection.remove(pet);
    }

    public void importAdopters(HashMap<String, Adopter> adopterHashMap) {
        this.adoptersCollection.addAll(adopterHashMap.values());
    }

    // Methods from the adoption process
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

    public void removeAdopter(Adopter adopter) {
        if (adopter == null) {
            throw new IncorrectInputException("Adopter is null");
        }
        this.adoptersCollection.remove(adopter);
    }


    public Pet findPetById(String petId) {

        if (petId.isEmpty()){
            throw new IncorrectInputException("Pet id is empty");
        }

        for (Pet pet : petsCollection) {
            if (pet.getPetId().equals(petId)) {
                return pet;
            }
        }
        throw new IncorrectInputException("Pet not found");
    }


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

    public Adopter findAdopterById(String adopterId) {
        if (adopterId.isEmpty()){
            throw new IncorrectInputException("Adopter id is empty");
        }
        if (adoptersCollection.isEmpty()) {
            throw new IncorrectInputException("Adopters collection is empty");
        }

        for (Adopter adopter : adoptersCollection) {
            if (adopter.getAdopterId().equals(adopterId)) {
                return adopter;
            }
        }
        throw new IncorrectInputException("Adopter not found");
    }

    public Adopter findAdopterByPetId(String petId) {
        if (petId.isEmpty()){
            throw new IncorrectInputException("Pet id is empty");
        }
        for (Adopter adopter : adoptersCollection) {
            for (Pet pet : adopter.getAdoptedPetsCollection()) {
                if (pet.getPetId().equals(petId)) {
                    return adopter;
                }
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


    public void displayPetByFilter(String filter, String value) {

        // Verify fitler is not blank or empty
        if (filter.isBlank() || filter == null){
            throw new IncorrectInputException("Filter is blank or empty");
        }
        // Validate value is not blank or empty
        if (value.isBlank()){
            throw new IncorrectInputException("Value is blank or empty");
        }
        // Assert filter type
        if(!petFilterTypes.contains(filter.toUpperCase())){
            throw new IncorrectInputException("Invalid filter");
        }

        System.out.println();
        for (Pet pet : petsCollection) {
            if (filter.equalsIgnoreCase("SPECIES")) {
                if (pet.getSpecies().equals(value)) {
                    System.out.println(pet);
                }
            } else if (filter.equalsIgnoreCase("Age")) {
                int age = pet.getAge();
                int ageInt = Integer.parseInt(value);
                if (ageInt == age) {
                    System.out.println(pet);
                }
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
