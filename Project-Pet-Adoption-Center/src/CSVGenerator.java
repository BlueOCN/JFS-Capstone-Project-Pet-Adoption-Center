import java.io.*;
import java.util.*;

public class CSVGenerator {
    public static void main(String[] args) {

        int AdopterPetPairs = 15;
        ArrayList<String> pets = new ArrayList<>();
        ArrayList<String> adopters = new ArrayList<>();

        for (int i = 0; i < AdopterPetPairs; i++) {
            String randomAdopterId = UUID.randomUUID().toString();
            String randomAdopter = randomAdopterId + "," +
                    genUniqueRandomName() + "," +
                    genRandomContactInfo();


            String species = genRandomSpecies();
            String randomPet = UUID.randomUUID().toString() + "," +
                    genUniqueRandomName() + "," +
                    species + "," +
                    genRandomAge() + "," +
                    genRandomBreed() + "," +
                    "ADOPTED," +
                    randomAdopterId;

            switch(species){
                case "Dog":
                    String dogData = "," + genRandomTrainingLevel() + "," + genRandomBarkingLevel();
                    randomPet = randomPet.concat(dogData);
                    break;

                case "Cat":
                    String catData = "," + genRandomIsIndoor() + "," + genRandomScratchingHabitLevel();
                    randomPet = randomPet.concat(catData);
                    break;

                case "Bird":
                    String birdData = "," + genRandomCanFly() + "," + genRandomCanTalk();
                    randomPet = randomPet.concat(birdData);
                    break;
            }
            pets.add(randomPet);
            adopters.add(randomAdopter);
        }

        try (FileWriter writer = new FileWriter("./Project-Pet-Adoption-Center/AdoptionAppData/Pets.csv")) {
            writer.write("PetID,Name,Species,Age,Breed,AdoptionStatus,AdopterID,A,B\n");
            for (String line : pets) {
                writer.write(line + "\n");
            }
            System.out.println("CSV file created successfully!");
        } catch (IOException e) {
            System.out.println("Error writing to file.");
            e.printStackTrace();
        }

        try (FileWriter writer = new FileWriter("./Project-Pet-Adoption-Center/AdoptionAppData/Adopters.csv")) {
            writer.write("AdopterId,Name,ContactInfo\n");
            for (String line : adopters) {
                writer.write(line + "\n");
            }
            System.out.println("CSV file created successfully!");
        } catch (IOException e) {
            System.out.println("Error writing to file.");
            e.printStackTrace();
        }

        pets.clear();

        for (int i = 0; i < AdopterPetPairs; i++) {
            String species = genRandomSpecies();
            String randomPet = UUID.randomUUID().toString() + "," +
                    genUniqueRandomName() + "," +
                    species + "," +
                    genRandomAge() + "," +
                    genRandomBreed() + "," +
                    genRandomAdoptionStatus() + "," +
                    "";

            switch(species){
                case "Dog":
                    String dogData = "," + genRandomTrainingLevel() + "," + genRandomBarkingLevel();
                    randomPet = randomPet.concat(dogData);
                    break;

                case "Cat":
                    String catData = "," + genRandomIsIndoor() + "," + genRandomScratchingHabitLevel();
                    randomPet = randomPet.concat(catData);
                    break;

                case "Bird":
                    String birdData = "," + genRandomCanFly() + "," + genRandomCanTalk();
                    randomPet = randomPet.concat(birdData);
                    break;
            }

            pets.add(randomPet);
        }

        try (FileWriter writer = new FileWriter("./Project-Pet-Adoption-Center/AdoptionAppData/Pets.csv", true)) {
            for (String line : pets) {
                writer.write(line + "\n");
            }
            System.out.println("CSV file updated successfully!");
        } catch (IOException e) {
            System.out.println("Error writing to file.");
            e.printStackTrace();
        }




        // Import Adopters

        // Store adopters in a hashmap
        HashMap<String, Adopter> adoptersMap = new HashMap<>();

        // Load data from csv file on program restart.
        try (BufferedReader br = new BufferedReader(new FileReader("./Project-Pet-Adoption-Center/AdoptionAppData/Adopters.csv"))) {

            String line;
            Adopter adopter;

            while ((line = br.readLine()) != null) {

                if (line.startsWith("Adopter")) {
                    continue;
                }

                String[] values = line.split(","); // Split by comma

                adopter = new Adopter(values[0], values[1], values[2]);
                adoptersMap.put(values[0], adopter);
            }

        } catch (IOException e) {
            System.out.println("Error reading the file.");
            e.printStackTrace();
        }

        System.out.println(adoptersMap);
        System.out.println();



        // Import pets:
        // Store pets in a hashmap
        HashMap<String, Pet> petsMap = new HashMap<>();

        // Load data from csv file on program restart.
        try (BufferedReader br = new BufferedReader(new FileReader("./Project-Pet-Adoption-Center/AdoptionAppData/Pets.csv"))) {

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
                            values[7],
                            Integer.parseInt(values[8]));

                    petsMap.put(values[0], pet);

                    if (adoptersMap.containsKey(values[6])) {
                        Adopter adopter = adoptersMap.get(values[6]);
                        adopter.addAdoptedPet(pet);
                    }
                }
                else if ("CAT".equalsIgnoreCase(values[2])) {
                    pet = new Cat(values[0],
                            values[1],
                            values[2],
                            Integer.parseInt(values[3]),
                            values[4],
                            values[5],
                            Boolean.parseBoolean(values[7]),
                            values[8]);
                    petsMap.put(values[0], pet);

                    if (adoptersMap.containsKey(values[6])) {
                        Adopter adopter = adoptersMap.get(values[6]);
                        adopter.addAdoptedPet(pet);
                    }
                }
                else if ("BIRD".equalsIgnoreCase(values[2])) {
                    pet = new Bird(values[0],
                            values[1],
                            values[2],
                            Integer.parseInt(values[3]),
                            values[4],
                            values[5],
                            Boolean.parseBoolean(values[7]),
                            Boolean.parseBoolean(values[8]));
                    petsMap.put(values[0], pet);

                    if (adoptersMap.containsKey(values[6])) {
                        Adopter adopter = adoptersMap.get(values[6]);
                        adopter.addAdoptedPet(pet);
                    }
                }
                else {
                    throw new InvalidPetDataException("Invalid pet data: " + values[2]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the file.");
            e.printStackTrace();
        }

        System.out.println(petsMap);
        System.out.println();
        System.out.println(adoptersMap);
        System.out.println();

        System.out.println(petsMap);


        // Data export


    }

    private static String generateRandomRow2() {

        String row = UUID.randomUUID().toString() + "," +
                genUniqueRandomName() + "," +
                genRandomContactInfo() + "," +
                genAdoptedPets();
        return row;
    }

    private static String genAdoptedPets() {

        String row;
        int rand = new Random().nextInt(0,5);

        ArrayList<String> petList = new ArrayList<>();

        for (int i = 0; i < rand; i++) {
            row = generateRandomRow();
            String[] parsed = row.split(",");
            if ("ADOPTED".equals(parsed[5])){
                petList.add(parsed[0]);
            }
        }

        System.out.println(petList);

        return petList.toString();

    }

    private static String genRandomContactInfo() {
        Random random = new Random();
        int areaCode = random.nextInt(900) + 100; // Ensures a 3-digit area code
        int centralOfficeCode = random.nextInt(900) + 100; // Ensures a 3-digit exchange code
        int lineNumber = random.nextInt(10000); // Ensures a 4-digit line number

        return String.format("(%d) %d-%04d", areaCode, centralOfficeCode, lineNumber);
    }


    public static String generateRandomRow(){

        String species = genRandomSpecies();

        String row = UUID.randomUUID().toString() + "," +
                genUniqueRandomName() + "," +
                species + "," +
                genRandomAge() + "," +
                genRandomBreed() + "," +
                genRandomAdoptionStatus();

        switch(species){
            case "Dog":
                String dogData = "," + genRandomTrainingLevel() + "," + genRandomBarkingLevel();
                row = row.concat(dogData);
                break;

            case "Cat":
                String catData = "," + genRandomIsIndoor() + "," + genRandomScratchingHabitLevel();
                row = row.concat(catData);
                break;

            case "Bird":
                String birdData = "," + genRandomCanFly() + "," + genRandomCanTalk();
                row = row.concat(birdData);
                break;
        }

        return row;
    }

    private static String genRandomCanTalk() {
        boolean canTalk = new Random().nextBoolean();
        return Boolean.toString(canTalk);
    }

    private static String genRandomCanFly() {
        boolean canFly = new Random().nextBoolean();
        return Boolean.toString(canFly);
    }

    private static String genRandomScratchingHabitLevel() {
        String scratchingHabitLevel;
        Random random = new Random();
        String[] LEVELS = { "LOW", "MEDIUM", "HIGH" };
        scratchingHabitLevel = LEVELS[random.nextInt(LEVELS.length)];
        return scratchingHabitLevel;
    }

    private static String genRandomIsIndoor() {
        boolean isIndoor = new Random().nextBoolean();
        return Boolean.toString(isIndoor);
    }

    private static String genRandomBarkingLevel() {
        int level = new Random().nextInt(1,10);
        return Integer.toString(level);
    }

    private static String genRandomTrainingLevel() {
        String trainingLevel;
        Random random = new Random();
        String[] LEVELS = { "LOW", "MEDIUM", "HIGH" };
        trainingLevel = LEVELS[random.nextInt(LEVELS.length)];
        return trainingLevel;
    }

    public static String genUniqueRandomName() {

        String name;
        Random random = new Random();
        String[] NAMES = {
                "Buddy", "Luna", "Milo", "Charlie", "Whiskers", "Bella", "Max", "Daisy",
                "Oscar", "Coco", "Rocky", "Oliver", "Zazu", "Gizmo", "Sunny", "Leo", "Rex",
                "Tiger", "Peppy", "Bruce", "Bailey", "Buster", "Rosie", "Bentley", "Nala",
                "Sasha", "Dexter", "Mochi", "Toby", "Mittens", "Simba", "Hazel", "Finn",
                "Ziggy", "Poppy", "Bandit", "Smokey", "Chester", "Ruby", "Baxter", "Scout",
                "Hank", "Kiki", "Waffles", "Pumpkin", "Marley", "Goose", "Jasper", "Teddy",
                "Misty", "Shadow", "Fluffy", "Echo", "Fiona", "Chico", "Phoebe", "Raven",
                "Penny", "Nova", "Casper", "Mango", "Blue", "Otis", "Rusty", "Pepper",
                "Zeus", "Tofu", "Koda", "Aspen", "Socks", "Chili", "Maple", "Ace", "Pablo",
                "Freckles", "Sunny", "Cloud", "Loki", "Mocha", "Sparky", "Ricky", "Oreo",
                "Holly", "Bubbles", "Cinnamon", "Juno", "Snowball", "Snickers", "Boomer",
                "Thor", "Sprout", "Tango", "Fudge", "Trixie", "Buttercup", "Cupcake",
                "Blaze", "Clover", "Chip", "Skye", "Nugget", "Rolo", "Pebbles", "Zorro"
        };

        name = NAMES[random.nextInt(NAMES.length)];

        return name;
    }

    public static String genRandomSpecies(){
        String species;
        Random random = new Random();
        String[] SPECIES = { "Dog", "Cat", "Bird" };
        species = SPECIES[random.nextInt(SPECIES.length)];
        return species;
    }

    public static String genRandomAge(){
        int age;
        Random random = new Random();
        age = random.nextInt(1,25);
        return Integer.toString(age);
    }

    public static String genRandomBreed(){
        String breed;
        Random random = new Random();
        String[] BREEDS = {
                "Macaw", "Finch", "Bengal", "Golden Retriever", "Labrador", "Siamese", "Maine Coon",
                "Cockatiel", "Parrot", "Persian", "Scottish Fold", "Doberman", "Ragdoll",
                "Husky", "Poodle", "British Shorthair", "Canary", "Lovebird", "Chihuahua",
                "Beagle", "Shiba Inu", "Russian Blue", "Boxer", "Greyhound", "Birman",
                "Savannah", "Norwegian Forest", "Exotic Shorthair", "American Bulldog",
                "Pomeranian", "Newfoundland", "Springer Spaniel", "Papillon", "Bichon Frise",
                "Chow Chow", "Maltese", "Akita", "Basenji", "Great Dane", "Dalmatian",
                "Weimaraner", "Cornish Rex", "Abyssinian", "Oriental Shorthair", "Burmese",
                "Sphynx", "Angora", "Munchkin", "Cocker Spaniel", "Pekingese", "Samoyed"
        };
        breed = BREEDS[random.nextInt(BREEDS.length)];
        return breed;
    }

    public static String genRandomAdoptionStatus(){
        String adoptionStatus;
        Random random = new Random();
        String[] ADOPTION_TYPES = { "AVAILABLE", "PENDING_ADOPTION", "RETURNED", "FOSTERED", "UNAVAILABLE" };
        adoptionStatus = ADOPTION_TYPES[random.nextInt(ADOPTION_TYPES.length)];
        return adoptionStatus;
    }

}
