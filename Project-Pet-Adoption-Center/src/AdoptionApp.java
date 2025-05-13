import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class AdoptionApp {

    private static boolean MASTER_SWITCH = true;

    private enum States {
        IDLE,
        SETUP,
        HOME,
        TRACK_PETS,
        TRACK_ADOPTIONS,
        QUICK_QUERIES,
        RESET,
        EXIT,
    }

    public static void main(String[] args) {

        States State = States.IDLE;

        String fileName = "./Project-Pet-Adoption-Center/AdoptionAppData.csv";
        ArrayList<Pet> petsDataSet;

        PetAdoptionCenter PAC = null;
        String userInput;
        Scanner scanner = new Scanner(System.in);

        do {

            switch (State) {
                case States.IDLE:
                    // Run only once at the start of the system
                    // Go SETUP only when minimum requirements are met
                    State = States.SETUP;
                    break;

                case States.SETUP:

                    // Setup data
                    petsDataSet = new ArrayList<>();

                    // Load data from csv file on program restart.
                    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

                        String line;
                        Pet pet;

                        while ((line = br.readLine()) != null) {

                            if (line.startsWith("Pet")){
                                continue;
                            }

                            String[] values = line.split(","); // Split by comma
                            if ("DOG".equalsIgnoreCase(values[2])) {
                                pet = new Dog(values[0],
                                        values[1],
                                        values[2],
                                        Integer.parseInt(values[3]),
                                        values[4],
                                        values[5],
                                        values[6],
                                        Integer.parseInt(values[7]));
                                petsDataSet.add(pet);
                            }
                            else if ("CAT".equalsIgnoreCase(values[2])) {
                                pet = new Cat(values[0],
                                        values[1],
                                        values[2],
                                        Integer.parseInt(values[3]),
                                        values[4],
                                        values[5],
                                        Boolean.parseBoolean(values[6]),
                                        values[7]);
                                petsDataSet.add(pet);
                            }
                            else if ("BIRD".equalsIgnoreCase(values[2])) {
                                pet = new Bird(values[0],
                                        values[1],
                                        values[2],
                                        Integer.parseInt(values[3]),
                                        values[4],
                                        values[5],
                                        Boolean.parseBoolean(values[6]),
                                        Boolean.parseBoolean(values[7]));
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

                    // Create and Initiate Pet Adoption Center
                    PAC = new PetAdoptionCenter();

                    // Load data to the Pet Adoption Center
                    PAC.addPet(petsDataSet);

                    // Welcome message
                    System.out.println("\nWelcome to the Pet Adoption Center.");

                    // wait for 1 sec
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // Go HOME
                    State = States.HOME;

                    break;

                case States.HOME:

//                    Scanner scanner = new Scanner(System.in);

                    // Prompt the user 5 options
                    System.out.println("\nWhat do you want to do?");
                    System.out.println("1. Track pets (Manage pet records)");
                    System.out.println("2. Track adoptions (Manage adoption records)");
                    System.out.println("3. Quick queries (Provide quick access to pets and adoptions records)");
                    System.out.println("4. Reset");
                    System.out.println("5. Exit");
                    System.out.print("Please enter a number between 1 and 5: ");
                    userInput = scanner.nextLine();


                    // if 1 then go to TRACK_PETS
                    State = switch (userInput) {
                        case "1" -> States.TRACK_PETS;
                        // if 2 then go to TRACK_ADOPTIONS
                        case "2" -> States.TRACK_ADOPTIONS;
                        // if 3 then go to QUICK_QUERIES
                        case "3" -> States.QUICK_QUERIES;
                        // if 4 then go to RESET
                        case "4" -> States.RESET;
                        // if 5 then go to EXIT
                        case "5" -> States.EXIT;
                        // Stay HOME otherwise
                        default -> States.HOME;
                    };

                    break;

                case States.TRACK_PETS:

                    // Show a table of all pets
                    PAC.displayPets();

                    // Prompt for action
                    System.out.println("What do you want to do?");
                    System.out.println("1. Create new pet (Manage pet records)");
                    System.out.println("2. Remove a pet (Manage adoption records)");
                    System.out.println("3. Update a pet (Provide potential adopters with information about available pets)");
                    System.out.println("4. Read a pet");
                    System.out.println("5. Reset");
                    System.out.println("6. Exit");
                    System.out.print("Please enter a number between 1 and 6: ");
                    userInput = scanner.nextLine();

                    // if 1 create new pet and add it to the system
                    if (userInput.equals("1")) {

                        Pet newPet = null;
                        String petName,
                               petSpecies,
                               petAge,
                               petBreed,
                               petAdoptionStatus,
                               dogTrainingLevel,
                               dogBarkingLevel,
                               isIndoor,
                               scratchingHabit,
                                canFly,
                                canTalk;

                        System.out.println("Creating new pet");
                        System.out.print("Enter name of pet you wish to register: ");
                        petName = scanner.nextLine();
                        System.out.print("Enter species of pet you wish to register: ");
                        petSpecies = scanner.nextLine();
                        System.out.print("Enter age of pet you wish to register: ");
                        petAge = scanner.nextLine();
                        System.out.print("Enter breed of pet you wish to register: ");
                        petBreed = scanner.nextLine();
                        System.out.print("Enter adoption status of pet you wish to register: ");
                        petAdoptionStatus = scanner.nextLine();

                        if (petSpecies.equalsIgnoreCase("Dog")) {
                            System.out.print("Enter training level of the dog you wish to register: ");
                            dogTrainingLevel = scanner.nextLine();
                            System.out.print("Enter barking level of the dog you wish to register: ");
                            dogBarkingLevel = scanner.nextLine();

                            // Create new pet
                            newPet = new Dog(petName,
                                    petSpecies,
                                    Integer.parseInt(petAge),
                                    petBreed,
                                    petAdoptionStatus,
                                    dogTrainingLevel,
                                    Integer.parseInt(dogBarkingLevel));

                        } else if (petSpecies.equalsIgnoreCase("Cat")) {
                            System.out.print("Enter if pet is an indoor cat [true/false]: ");
                            isIndoor = scanner.nextLine();
                            System.out.print("Enter scratching habit level of the cat you wish to register [LOW/MEDIUM/HIGH]: ");
                            scratchingHabit = scanner.nextLine();

                            // Create new pet
                            newPet = new Cat(petName,
                                    petSpecies,
                                    Integer.parseInt(petAge),
                                    petBreed,
                                    petAdoptionStatus,
                                    Boolean.parseBoolean(isIndoor),
                                    scratchingHabit);

                        }
                        else if (petSpecies.equalsIgnoreCase("Bird")) {
                            System.out.println("Enter if the bird you wish to register can fly [true/false]: ]");
                            canFly = scanner.nextLine();
                            System.out.println("Enter if the bird you wish to register can talk [true/false]: ]");
                            canTalk = scanner.nextLine();

                            // Create new pet
                            newPet = new Bird(petName,
                                    petSpecies,
                                    Integer.parseInt(petAge),
                                    petBreed,
                                    petAdoptionStatus,
                                    Boolean.parseBoolean(canFly),
                                    Boolean.parseBoolean(canTalk));

                        }
                        else {
                            System.out.println("Invalid pet data: " + petSpecies);
                            break;
                        }

                        try {
                            // Add pet to the system
                            PAC.addPet(newPet);

                            // Display pets
                            PAC.displayPets();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }

                        // Go HOME
                        State = States.HOME;
                    }


                    // if 2 then find pet (search criteria)
                    else if (userInput.equals("2")) {

                        String searchChoice;
                        String petId;
                        String searchFilter = null;
                        String searchValue = null;

                        // Prompt user for search criteria
                        System.out.println("What search criteria do you want to use?");
                        System.out.println("1. Species (ie. Dog, Cat, Bird)");
                        System.out.println("2. Age (ie. 4)");
                        System.out.print("Please enter a number between 1 and 2: ");
                        searchChoice = scanner.nextLine();

                        // Prompt user for search value
                        if (searchChoice.equals("1")) {
                            System.out.print("Enter pet Species: ");
                            searchValue = scanner.nextLine();
                            searchFilter = "Species";
                        } else if (searchChoice.equals("2")) {
                            System.out.print("Enter pet Age: ");
                            searchValue = scanner.nextLine();
                            searchFilter = "Age";
                        } else {
                            throw new InvalidPetDataException("Invalid search criteria: " + searchChoice);
                        }

                        // Display pet that meet the search criteria and value given by the user
                        PAC.displayPetByFilter(searchFilter, searchValue);

                        // Prompt user for pet ID
                        System.out.print("Enter the pet ID you wish to delete: ");
                        petId = scanner.nextLine().strip();

                        try {
                            // Find pet
                            Pet pet = PAC.findPetById(petId);

                            // Remove pet
                            PAC.removePet(pet);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }

                        // Go HOME
                        State = States.HOME;
                    }

                    // if 3 then find pet (search criteria)
                    else if (userInput.equals("3")) {

                        String searchChoice;
                        String petId;
                        String searchFilter = null;
                        String searchValue = null;

                        // Prompt user for search criteria
                        System.out.println("What search criteria do you want to use?");
                        System.out.println("1. Species (ie. Dog, Cat, Bird)");
                        System.out.println("2. Age (ie. 4)");
                        System.out.print("Please enter a number between 1 and 2: ");
                        searchChoice = scanner.nextLine();

                        // Prompt user for search value
                        if (searchChoice.equals("1")) {
                            System.out.print("Enter pet Species: ");
                            searchValue = scanner.nextLine();
                            searchFilter = "Species";
                        } else if (searchChoice.equals("2")) {
                            System.out.print("Enter pet Age: ");
                            searchValue = scanner.nextLine();
                            searchFilter = "Age";
                        }

                        // Display pet that meet the search criteria and value given by the user
                        try {
                            PAC.displayPetByFilter(searchFilter, searchValue);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            State = States.TRACK_PETS;
                            break;
                        }

                        // Prompt user for pet ID
                        System.out.print("Enter the ID of the pet you wish to update: ");
                        petId = scanner.nextLine().strip();

                        // Find pet
                        Pet pet = PAC.findPetById(petId);

                        // Display pet details
                        System.out.println(pet);

                        // Create updated pet
                        Pet updatedPet = pet;

                        // Prompt user for new data
                        System.out.print("Please enter a new name: ");
                        userInput = scanner.nextLine().strip();
                        updatedPet.setName(userInput);
                        System.out.print("Please enter a new species: ");
                        userInput = scanner.nextLine().strip();
                        updatedPet.setSpecies(userInput);
                        System.out.print("Please enter a new age: ");
                        userInput = scanner.nextLine().strip();
                        updatedPet.setAge(Integer.parseInt(userInput));
                        System.out.print("Please enter a new breed: ");
                        userInput = scanner.nextLine().strip();
                        updatedPet.setBreed(userInput);
                        System.out.print("Please enter a new adoption status: ");
                        userInput = scanner.nextLine().strip();
                        updatedPet.setAdoptionStatus(userInput);

                        if (updatedPet instanceof Dog) {
                            // Update dog object
                            Dog dog = (Dog) updatedPet;

                            System.out.print("Please enter a new training level: ");
                            userInput = scanner.nextLine().strip();
                            dog.setTrainingLevel(userInput);
                            System.out.print("Please enter a new barking level: ");
                            userInput = scanner.nextLine().strip();
                            dog.setBarkingLevel(Integer.parseInt(userInput));

                        } else if (updatedPet instanceof Cat) {
                            // Update cat object
                            Cat cat = (Cat) updatedPet;

                            System.out.print("Please enter if cat is indoor [true/false]: ");
                            userInput = scanner.nextLine().strip();
                            cat.setIndoor(Boolean.parseBoolean(userInput));
                            System.out.print("Please enter cat scratching habit level [LOW, MEDIUM, HIGH]: ");
                            userInput = scanner.nextLine().strip();
                            cat.setScratchingHabit(userInput);

                        } else if (updatedPet instanceof Bird) {
                            // Update cat object
                            Bird bird = (Bird) updatedPet;
                            System.out.print("Please enter if bird can fly: ");
                            userInput = scanner.nextLine().strip();
                            bird.setCanFly(Boolean.parseBoolean(userInput));
                            System.out.print("Please enter if bird can talk: ");
                            userInput = scanner.nextLine().strip();
                            bird.setCanTalk(Boolean.parseBoolean(userInput));
                        }

                        try {
                            // Display pet updated details
                            System.out.println();
                            System.out.println(updatedPet);

                            // Update pet info
                            PAC.updatePet(pet, updatedPet);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }

                        // Go HOME
                        State = States.HOME;

                    }

                    // if 4 then find pet (search criteria)
                    else if (userInput.equals("4")) {

                        String searchChoice;
                        String petId;
                        String searchFilter = null;
                        String searchValue = null;

                        // Prompt user for search criteria
                        System.out.println("What search criteria do you want to use?");
                        System.out.println("1. Species (ie. Dog, Cat, Bird)");
                        System.out.println("2. Age (ie. 4)");
                        System.out.print("Please enter a number between 1 and 2: ");
                        searchChoice = scanner.nextLine();

                        // Prompt user for search value
                        if (searchChoice.equals("1")) {
                            System.out.print("Enter pet Species: ");
                            searchValue = scanner.nextLine();
                            searchFilter = "Species";
                        } else if (searchChoice.equals("2")) {
                            System.out.print("Enter pet Age: ");
                            searchValue = scanner.nextLine();
                            searchFilter = "Age";
                        } else {
                            System.out.println("Invalid search criteria");
                            break;
                        }

                        try {
                            // Display pet that meet the search criteria and value given by the user
                            PAC.displayPetByFilter(searchFilter, searchValue);

                            // Prompt user for pet ID
                            System.out.print("Enter the pet ID you wish to see on detail: ");
                            petId = scanner.nextLine().strip();

                            // Find pet
                            Pet pet = PAC.findPetById(petId);

                            // Display pet details
                            System.out.println(pet);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }

                        // Go HOME
                        State = States.HOME;

                    } else if (userInput.equals("5")) {
                        State = States.RESET;

                    } else if (userInput.equals("6")) {
                        State = States.EXIT;

                    }

                    break;

                case TRACK_ADOPTIONS:

                    // Show a table of all pets
                    PAC.displayAdopters();

                    // Prompt for action
                    System.out.println("What do you want to do?");
                    System.out.println("1. Create new adopter (Register new adopters)");
                    System.out.println("2. Remove an adopter (Delete adopter)");
                    System.out.println("3. Update an adopter (Modify adopter information)");
                    System.out.println("4. Read an adopter (Display adopter details)");
                    System.out.println("5. Reset");
                    System.out.println("6. Exit");
                    System.out.print("Please enter a number between 1 and 6: ");
                    userInput = scanner.nextLine().strip();

                    // Create a new adopter
                    if (userInput.equals("1")) {

                        // Display all available pets
                        PAC.displayPets("AVAILABLE");

                        // Prompt user for pet ID
                        System.out.print("Enter the pet ID you wish to adopt: ");
                        String petId = scanner.nextLine().strip();

                        // Retrieve pet
                        Pet pet = PAC.findPetById(petId);

                        // Get adopter details
                        Adopter newAdopter = new Adopter();

                        // Get name
                        try {
                            System.out.print("Enter the new adopter name: ");
                            userInput = scanner.nextLine().strip();
                            newAdopter.setName(userInput);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            break;
                        }

                        // Get number
                        try {
                            System.out.print("Enter adopter contact number: ");
                            userInput = scanner.nextLine().strip();
                            newAdopter.setContactInfo(userInput);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            break;
                        }


                        // Register new adopter and assign him a pet
                        try {
                            PAC.registerAdopter(newAdopter, pet);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            break;
                        }

                        // Go home
                        State = States.HOME;

                    }
                    else if (userInput.equals("2")) {

                        // Display adopters
                        PAC.displayAdopters();

                        // Prompt user for adopter ID
                        System.out.print("Enter the adopter ID you wish to delete: ");
                        String adopterId = scanner.nextLine().strip();

                        // Get adopter
                        Adopter adopter = PAC.findAdopterById(adopterId);

                        // Remove adopter
                        PAC.removeAdopter(adopter);

                        // Notify the user
                        System.out.println("Deleted adopter " + adopterId);

                        // Go home
                        State = States.HOME;
                        break;
                    }
                    else if (userInput.equals("3")) {

                        // Display adopters
                        PAC.displayAdopters();

                        // Prompt user for adopter ID
                        System.out.print("Enter the adopter ID you wish to update: ");
                        String adopterId = scanner.nextLine().strip();

                        // Get adopter
                        Adopter adopter = PAC.findAdopterById(adopterId);

                        System.out.print("Enter adopter updated name: ");
                        String newAdopterName = scanner.nextLine().strip();
                        adopter.setName(newAdopterName);

                        System.out.print("Enter adopter updated contact number: ");
                        String newAdopterContact = scanner.nextLine().strip();
                        adopter.setContactInfo(newAdopterContact);

                        System.out.println(adopter);

                        State = States.HOME;
                        break;

                    }
                    else if (userInput.equals("4")) {

                        // Display adopters
                        PAC.displayAdopters();

                        // Prompt user for adopter ID
                        System.out.print("Enter the adopter ID you wish to update: ");
                        String adopterId = scanner.nextLine().strip();

                        // Get adopter
                        Adopter adopter = PAC.findAdopterById(adopterId);

                        // Display details
                        System.out.println(adopter);

                        State = States.HOME;
                        break;

                    }
                    else if (userInput.equals("5")) {
                        State = States.RESET;
                    }
                    else if (userInput.equals("6")) {
                        State = States.EXIT;
                    }
                    else {
                        System.out.println("Invalid input, please try again");
                    }

                    break;

                case QUICK_QUERIES:

                    // Prompt for action
                    System.out.println("What do you want to do?");
                    System.out.println("1. Add new pet (Add new pet to the system");
                    System.out.println("2. Register new adopter (Add adopter to the system and pet assignment)");
                    System.out.println("3. Find pet by id");
                    System.out.println("4. Find Adopter by id");
                    System.out.println("5. Reset");
                    System.out.println("6. Exit");
                    System.out.print("Please enter a number between 1 and 6: ");
                    userInput = scanner.nextLine().strip();

                    if (userInput.equals("1")) {

                        Pet newPet = null;
                        String petName,
                                petSpecies,
                                petAge,
                                petBreed,
                                petAdoptionStatus,
                                dogTrainingLevel,
                                dogBarkingLevel,
                                isIndoor,
                                scratchingHabit,
                                canFly,
                                canTalk;

                        System.out.println("Creating new pet");
                        System.out.print("Enter name of pet you wish to register: ");
                        petName = scanner.nextLine();
                        System.out.print("Enter species of pet you wish to register: ");
                        petSpecies = scanner.nextLine();
                        System.out.print("Enter age of pet you wish to register: ");
                        petAge = scanner.nextLine();
                        System.out.print("Enter breed of pet you wish to register: ");
                        petBreed = scanner.nextLine();
                        System.out.print("Enter adoption status of pet you wish to register: ");
                        petAdoptionStatus = scanner.nextLine();

                        if (petSpecies.equalsIgnoreCase("Dog")) {
                            System.out.print("Enter training level of the dog you wish to register: ");
                            dogTrainingLevel = scanner.nextLine();
                            System.out.print("Enter barking level of the dog you wish to register: ");
                            dogBarkingLevel = scanner.nextLine();

                            // Create new pet
                            newPet = new Dog(petName,
                                    petSpecies,
                                    Integer.parseInt(petAge),
                                    petBreed,
                                    petAdoptionStatus,
                                    dogTrainingLevel,
                                    Integer.parseInt(dogBarkingLevel));

                        } else if (petSpecies.equalsIgnoreCase("Cat")) {
                            System.out.print("Enter if pet is an indoor cat [true/false]: ");
                            isIndoor = scanner.nextLine();
                            System.out.print("Enter scratching habit level of the cat you wish to register [LOW/MEDIUM/HIGH]: ");
                            scratchingHabit = scanner.nextLine();

                            // Create new pet
                            newPet = new Cat(petName,
                                    petSpecies,
                                    Integer.parseInt(petAge),
                                    petBreed,
                                    petAdoptionStatus,
                                    Boolean.parseBoolean(isIndoor),
                                    scratchingHabit);

                        }
                        else if (petSpecies.equalsIgnoreCase("Bird")) {
                            System.out.println("Enter if the bird you wish to register can fly [true/false]: ]");
                            canFly = scanner.nextLine();
                            System.out.println("Enter if the bird you wish to register can talk [true/false]: ]");
                            canTalk = scanner.nextLine();

                            // Create new pet
                            newPet = new Bird(petName,
                                    petSpecies,
                                    Integer.parseInt(petAge),
                                    petBreed,
                                    petAdoptionStatus,
                                    Boolean.parseBoolean(canFly),
                                    Boolean.parseBoolean(canTalk));

                        }
                        else {
                            System.out.println("Invalid pet data: " + petSpecies);
                            break;
                        }

                        try {
                            // Add pet to the system
                            PAC.addPet(newPet);

                            // Display pets
                            PAC.displayPets();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }

                        // Go HOME
                        State = States.HOME;

                    }
                    else if (userInput.equals("2")) {

                        // Display all available pets
                        PAC.displayPets("AVAILABLE");

                        // Prompt user for pet ID
                        System.out.print("Enter the pet ID you wish to adopt: ");
                        String petId = scanner.nextLine().strip();

                        // Retrieve pet
                        Pet pet = PAC.findPetById(petId);

                        // Get adopter details
                        Adopter newAdopter = new Adopter();

                        // Get name
                        try {
                            System.out.print("Enter the new adopter name: ");
                            userInput = scanner.nextLine().strip();
                            newAdopter.setName(userInput);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            State = States.TRACK_ADOPTIONS;
                            break;
                        }

                        // Get number
                        try {
                            System.out.print("Enter adopter contact number: ");
                            userInput = scanner.nextLine().strip();
                            newAdopter.setContactInfo(userInput);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            State = States.TRACK_ADOPTIONS;
                            break;
                        }


                        // Register new adopter and assign him a pet
                        try {
                            PAC.registerAdopter(newAdopter, pet);
                            System.out.println(newAdopter);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            State = States.TRACK_ADOPTIONS;
                            break;
                        }

                        // Go home
                        State = States.HOME;
                        break;
                    }
                    else if (userInput.equals("3")) {
                        State = States.HOME;
                        break;
                    }
                    else if (userInput.equals("4")) {

                        // Display all adopters
                        PAC.displayAdopters();

                        // Prompt user for pet ID
                        System.out.print("Enter the adopter ID you wish to find: ");
                        String adopterId = scanner.nextLine().strip();

                        try {
                            // Retrieve adopter
                            Adopter adopter = PAC.findAdopterById(adopterId);
                            // Display adopter
                            System.out.println(adopter);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            break;
                        }

                        State = States.HOME;
                        break;
                    }
                    else if (userInput.equals("5")) {
                        State = States.RESET;
                        break;
                    }
                    else if (userInput.equals("6")) {
                        State = States.EXIT;
                        break;
                    }
                    else {
                        System.out.println("Invalid input, please try again");
                        break;
                    }

                case RESET:

                    // Reset process start
                    System.out.println("Changes made during this session will be lost after reset.");
                    System.out.print("Do you want to continue? [Y/N]: ");
                    userInput = scanner.nextLine().strip().toUpperCase();

                    switch (userInput) {
                        case "Y":
                            State = States.SETUP;
                            // Shutdown process end
                            System.out.println("\nResetting the system...");
                            break;

                        case "N":
                            State = States.HOME;
                            break;
                    }

                    break;

                case States.EXIT:

                    // Shutdown process start
                    System.out.println("\nShutting down...");

                    // Saving the system’s state to a file and reloading it on program restart.
                    try (PrintWriter writer = new PrintWriter(fileName)) {

                        // Sort collection into Species
                        PAC.getPetsCollection().sort(Comparator.comparing(Pet::getSpecies));

                        // Write title
                        writer.println("Pet Adoption Center");
                        // Write headers
                        writer.println("PetID,Name,Species,Age,Breed,AdoptionStatus");

                        // Write collection into a csv file a row at a time
                        for(Pet pet: PAC.getPetsCollection()){

                            if(pet instanceof Dog) {
                                Dog dog = (Dog) pet; // Downcast to Dog
                                writer.printf("%s,%s,%s,%s,%s,%s,%s,%s\n",
                                        dog.getPetId(),
                                        dog.getName(),
                                        dog.getSpecies(),
                                        dog.getAge(),
                                        dog.getBreed(),
                                        dog.getAdoptionStatus(),
                                        dog.getTrainingLevel(),
                                        dog.getBarkingLevel());
                            } else if(pet instanceof Cat){
                                Cat cat = (Cat) pet; // Downcast to Dog
                                writer.printf("%s,%s,%s,%s,%s,%s,%s,%s\n",
                                        cat.getPetId(),
                                        cat.getName(),
                                        cat.getSpecies(),
                                        cat.getAge(),
                                        cat.getBreed(),
                                        cat.getAdoptionStatus(),
                                        cat.isIndoor(),
                                        cat.getScratchingHabit());
                            } else if(pet instanceof Bird){
                                Bird bird = (Bird) pet; // Downcast to Dog
                                writer.printf("%s,%s,%s,%s,%s,%s,%s,%s\n",
                                        bird.getPetId(),
                                        bird.getName(),
                                        bird.getSpecies(),
                                        bird.getAge(),
                                        bird.getBreed(),
                                        bird.getAdoptionStatus(),
                                        bird.isCanTalk(),
                                        bird.isCanFly());
                            } else {
                                throw new InvalidPetDataException("Invalid pet data: " + pet.getSpecies());
                            }

                        }
                        System.out.println("\nApp data saved successfully at " + fileName);

                        // Shutdown process end
                        System.out.println("\nThank you for using Pet Adoption Center.");

                    } catch (IOException e) {
                        System.out.println("\nError writing to file.");
                        e.printStackTrace();
                    }

                    // Shutdown
                    MASTER_SWITCH = false;
                    break;

                default:
                    // Shutdown
                    MASTER_SWITCH = false;
                    break;
            }


        } while(MASTER_SWITCH);



    }
}