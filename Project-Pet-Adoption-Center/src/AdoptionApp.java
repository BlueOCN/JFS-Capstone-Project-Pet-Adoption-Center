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

//        // Reloading system's state from a file on program restart.
//        String fileName = "./Project-Pet-Adoption-Center/AdoptionAppData.csv";
//        ArrayList<Pet> petsDataSet = new ArrayList<>();
//
//        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
//
//            String line;
//            Pet pet;
//
//            while ((line = br.readLine()) != null) {
//
//                if (line.startsWith("Pet")){
//                    continue;
//                }
//
//                String[] values = line.split(","); // Split by comma
//                if ("DOG".equalsIgnoreCase(values[2])) {
//                    pet = new Dog(values[0],
//                            values[1],
//                            values[2],
//                            Integer.parseInt(values[3]),
//                            values[4],
//                            values[5],
//                            values[6],
//                            Integer.parseInt(values[7]));
//                    petsDataSet.add(pet);
//                }
//                else if ("CAT".equalsIgnoreCase(values[2])) {
//                    pet = new Cat(values[0],
//                            values[1],
//                            values[2],
//                            Integer.parseInt(values[3]),
//                            values[4],
//                            values[5],
//                            Boolean.parseBoolean(values[6]),
//                            values[7]);
//                    petsDataSet.add(pet);
//                }
//                else if ("BIRD".equalsIgnoreCase(values[2])) {
//                    pet = new Bird(values[0],
//                            values[1],
//                            values[2],
//                            Integer.parseInt(values[3]),
//                            values[4],
//                            values[5],
//                            Boolean.parseBoolean(values[6]),
//                            Boolean.parseBoolean(values[7]));
//                    petsDataSet.add(pet);
//                }
//                else {
//                    throw new InvalidPetDataException("Invalid pet data: " + values[2]);
//                }
//            }
//        } catch (IOException e) {
//            System.out.println("Error reading the file.");
//            e.printStackTrace();
//        }
//
//
//        PetAdoptionCenter PAC = new PetAdoptionCenter();
//        PAC.addPet(petsDataSet);
//        PAC.displayPets();

//        // Registering new adopters and completing adoption transactions
//        //      Step 1: Allow adopters to view available pets
//        String searchCriteria = "AVAILABLE";
//        PAC.displayPets(searchCriteria); // Searching for pets by criteria (e.g., species, age) and displaying the results.
//
//        //      Step 2: Allow adopters to select a pet to adopt
//        Pet selectedPet = PAC.findPetByFilter("Name", "Buddy"); // Searching for pets by criteria (e.g., species, age) and displaying the results.
//
//        //      Step 3: Allow adopters to register as the new owner.
//        Adopter newAdopter1 = new Adopter("J Cole", "5512345678");
//
//        // Handling errors gracefully (e.g., trying to adopt a pet that’s already been adopted).
//        try {
//            PAC.registerAdopter(newAdopter1, selectedPet);
//        } catch (IncorrectInputException e) {
//            System.out.println();
//            System.out.println(e.getMessage());
//        }
//
//        PAC.displayPets();
//        PAC.displayAdopters();

//        // Saving the system’s state to a file and reloading it on program restart.
//        try (PrintWriter writer = new PrintWriter(fileName)) {
//
//            // Sort collection into Species
//            PAC.getPetsCollection().sort(Comparator.comparing(Pet::getSpecies));
//
//            // Write title
//            writer.println("Pet Adoption Center");
//            // Write headers
//            writer.println("PetID,Name,Species,Age,Breed,AdoptionStatus");
//
//            // Write collection into a csv file a row at a time
//            for(Pet pet: PAC.getPetsCollection()){
//
//                if(pet instanceof Dog) {
//                    Dog dog = (Dog) pet; // Downcast to Dog
//                    writer.printf("%s,%s,%s,%s,%s,%s,%s,%s\n",
//                            dog.getPetId(),
//                            dog.getName(),
//                            dog.getSpecies(),
//                            dog.getAge(),
//                            dog.getBreed(),
//                            dog.getAdoptionStatus(),
//                            dog.getTrainingLevel(),
//                            dog.getBarkingLevel());
//                } else if(pet instanceof Cat){
//                    Cat cat = (Cat) pet; // Downcast to Dog
//                    writer.printf("%s,%s,%s,%s,%s,%s,%s,%s\n",
//                            cat.getPetId(),
//                            cat.getName(),
//                            cat.getSpecies(),
//                            cat.getAge(),
//                            cat.getBreed(),
//                            cat.getAdoptionStatus(),
//                            cat.isIndoor(),
//                            cat.getScratchingHabit());
//                } else if(pet instanceof Bird){
//                    Bird bird = (Bird) pet; // Downcast to Dog
//                    writer.printf("%s,%s,%s,%s,%s,%s,%s,%s\n",
//                            bird.getPetId(),
//                            bird.getName(),
//                            bird.getSpecies(),
//                            bird.getAge(),
//                            bird.getBreed(),
//                            bird.getAdoptionStatus(),
//                            bird.isCanTalk(),
//                            bird.isCanFly());
//                } else {
//                    throw new InvalidPetDataException("Invalid pet data: " + pet.getSpecies());
//                }
//
//            }
//            System.out.println("CSV file created successfully!");
//
//        } catch (IOException e) {
//            System.out.println("Error writing to file.");
//            e.printStackTrace();
//        }





        States State = States.IDLE;

        String fileName;
        ArrayList<Pet> petsDataSet;

        PetAdoptionCenter PAC = null;
        String userInput;
        Scanner scanner = new Scanner(System.in);

        do {

            switch (State) {
                case States.IDLE:
                    // Run only once at the start of the system
                    // Go SETUP
                    State = States.SETUP;
                    break;

                case States.SETUP:
                    // Setup data
                    fileName = "./Project-Pet-Adoption-Center/AdoptionAppData.csv";
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
                    System.out.println("Welcome to the Pet Adoption Center.");

                    // wait for 10 sec
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // Go HOME
                    State = States.HOME;

                    break;

                case States.HOME:

//                    Scanner scanner = new Scanner(System.in);

                    // Prompt the user 5 options
                    System.out.println("What do you want to do?");
                    System.out.println("1. Track pets (Manage pet records)");
                    System.out.println("2. Track adoptions (Manage adoption records)");
                    System.out.println("3. Quick queries (Provide potential adopters with information about available pets)");
                    System.out.println("4. Reset the system");
                    System.out.println("5. Exit the system");
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
                               dogBarkingLevel;

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
                        }

                        // Add pet to the system
                        PAC.addPet(newPet);

                        // Display pets
                        PAC.displayPets();
                    }


                    // if 2 then find pet (search criteria)
                    if (userInput.equals("2")) {

                        String searchChoice;
                        String searchValue = null;

                        // Prompt user for search criteria
                        System.out.println("What do you want to do?");
                        System.out.println("1. Species (ie. Dog, Cat, Bird)");
                        System.out.println("2. Age (ie. 4)");
                        System.out.print("Please enter a number between 1 and 2: ");
                        searchChoice = scanner.nextLine();

                        // Prompt user for search value
                        if (searchChoice.equals("1")) {
                            System.out.println("Enter pet Species: ");
                            searchValue = scanner.nextLine();
                        } else if (searchChoice.equals("2")) {
                            System.out.println("Enter pet Age: ");
                            searchValue = scanner.nextLine();
                        }

                        // Find pet TODO modify findPetByFilter
                        Pet pet = PAC.findPetByFilter(searchChoice,searchValue);

                        // Remove pet TODO implement removal
                    }

                    // if 3 then find pet (search criteria)
                    //      Prompt user for search criteria
                    //      1. species
                    //      2. age
                    //
                    //      Prompt user for search value
                    //      Enter pet <criteria>:
                    //
                    // and update pet data
                    //      Prompt user for data to be updated
                    //      Update the data

                    // if 4 then find pet (search criteria)
                    //      Prompt user for search criteria
                    //      1. species
                    //      2. age
                    //
                    //      Prompt user for search value
                    //      Enter pet <criteria>:
                    // and show pet details

                    // Else Stay in TRACK_PETS

                    // Go HOME
                    State = States.HOME;

                    break;

                default:
                    break;
            }


        } while(MASTER_SWITCH);



    }
}