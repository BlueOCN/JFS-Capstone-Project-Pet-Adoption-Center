import java.io.*;
import java.util.ArrayList;

public class AdoptionApp {
    public static void main(String[] args) {

        // Reloading system's state from a file on program restart.
        String fileName = "./Project-Pet-Adoption-Center/AdoptionAppData.csv";
        ArrayList<Pet> petsDataSet = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;
            Pet pet;

            br.readLine();
            while ((line = br.readLine()) != null) {

                String[] values = line.split(","); // Split by comma

                if ("DOG".equalsIgnoreCase(values[2])) {
                    pet = new Dog(values[0],values[1],values[2], Integer.parseInt(values[3]), values[4], values[5]);
                    petsDataSet.add(pet);
                }
                else if ("CAT".equalsIgnoreCase(values[2])) {
                    pet = new Cat(values[0],values[1],values[2], Integer.parseInt(values[3]), values[4], values[5]);
                    petsDataSet.add(pet);
                }
                else if ("BIRD".equalsIgnoreCase(values[2])) {
                    pet = new Bird(values[0],values[1],values[2], Integer.parseInt(values[3]), values[4], values[5]);
                    petsDataSet.add(pet);
                }
                else {
                    throw new InvalidPetDataException("Invalid pet data: " + values[2]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the file.");
            e.printStackTrace();
        }


        PetAdoptionCenter PAC = new PetAdoptionCenter();
        PAC.addPet(petsDataSet);
        PAC.displayPets();

        // Registering new adopters and completing adoption transactions
        //      Step 1: Allow adopters to view available pets
        String searchCriteria = "AVAILABLE";
        PAC.displayPets(searchCriteria); // Searching for pets by criteria (e.g., species, age) and displaying the results.

        //      Step 2: Allow adopters to select a pet to adopt
        Pet selectedPet = PAC.findPetByFilter("Name", "Buddy"); // Searching for pets by criteria (e.g., species, age) and displaying the results.

        //      Step 3: Allow adopters to register as the new owner.
        Adopter newAdopter1 = new Adopter("J Cole", "5512345678");

        // Handling errors gracefully (e.g., trying to adopt a pet that’s already been adopted).
        try {
            PAC.registerAdopter(newAdopter1, selectedPet);
        } catch (IncorrectInputException e) {
            System.out.println();
            System.out.println(e.getMessage());
        }

        PAC.displayPets();
        PAC.displayAdopters();

        // Saving the system’s state to a file and reloading it on program restart.
        try (PrintWriter writer = new PrintWriter(fileName)) {
            writer.println("PetID,Name,Species,Age,Breed,AdoptionStatus");
            for(Pet pet: PAC.getPetsCollection()){
                writer.printf("%s,%s,%s,%s,%s,%s\n", pet.getPetId(), pet.getName(), pet.getSpecies(), pet.getAge(), pet.getBreed(), pet.getAdoptionStatus());
            }
            System.out.println("CSV file created successfully!");
        } catch (IOException e) {
            System.out.println("Error writing to file.");
            e.printStackTrace();
        }

    }
}