public class AdoptionApp {
    public static void main(String[] args) {

        PetAdoptionCenter PAC = new PetAdoptionCenter();

        // Adding new pets and displaying available ones
        Pet pet1 = new Dog("Hugo", 14, "Coquer", "AVAILABLE");
        Pet pet2 = new Cat("Catster", 6, "Evilthon", "AVAILABLE");
        Pet pet3 = new Bird("Frank", 2, "Parrot", "AVAILABLE");
        PAC.addPet(pet1, pet2, pet3);
        PAC.displayPets();

        // Registering new adopters and completing adoption transactions
        //      Step 1: Allow adopters to view available pets
        PAC.displayPets("AVAILABLE");

        //      Step 2: Allow adopters to select a pet to adopt
        Pet selectedPet = PAC.findPetByFilter("Name", "Hugo");

        //      Step 3: Allow adopters to register as the new owner.
        Adopter newAdopter1 = new Adopter("J Cole", "5512345678");
        PAC.registerAdopter(newAdopter1, selectedPet);

        //      Step 4: Allow adopters to update the pet's adoption status
        PAC.updateAdoptionStatus(selectedPet, "ADOPTED");


        // Searching for pets by criteria (e.g., species, age) and displaying the results.

        // Handling errors gracefully (e.g., trying to adopt a pet that’s already been adopted).

        // Saving the system’s state to a file and reloading it on program restart.

    }
}