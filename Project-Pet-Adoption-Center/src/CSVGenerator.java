import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class CSVGenerator {
    public static void main(String[] args) {

        int totalPets = 5;
        String fileName = "./Project-Pet-Adoption-Center/AdoptionAppData.csv";

        ArrayList<String> dataSet = new ArrayList<String>();
        dataSet.add("Pet Adoption Center");
        dataSet.add("PetID,Name,Species,Age,Breed,AdoptionStatus");
        for (int i = 0; i < totalPets; i++) {
            dataSet.add(generateRandomRow());
        }

        try (FileWriter writer = new FileWriter(fileName)) {
            for (String line : dataSet) {
                writer.write(line + "\n");
            }
            System.out.println("CSV file created successfully!");
        } catch (IOException e) {
            System.out.println("Error writing to file.");
            e.printStackTrace();
        }
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
        String[] ADOPTION_TYPES = { "AVAILABLE", "PENDING_ADOPTION", "ADOPTED", "RETURNED", "FOSTERED", "UNAVAILABLE" };
        adoptionStatus = ADOPTION_TYPES[random.nextInt(ADOPTION_TYPES.length)];
        return adoptionStatus;
    }
}
