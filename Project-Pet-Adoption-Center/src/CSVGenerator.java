import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CSVGenerator {
    public static void main(String[] args) {
        String fileName = "./Project-Pet-Adoption-Center/pets.csv";

        List<String> petsData = Arrays.asList(
                "PetID,Name,Species,Age,Breed,AdoptionStatus",
                "1,Buddy,Dog,2,Golden Retriever,ADOPTED",
                "2,Whiskers,Cat,3,Siamese,AVAILABLE",
                "3,Tweety,Bird,1,Canary,FOSTERED",
                "4,Luna,Dog,4,Labrador,PENDING_ADOPTION",
                "5,Milo,Cat,2,Bengal,ADOPTED",
                "6,Charlie,Bird,5,Parrot,AVAILABLE",
                "7,Rex,Dog,6,German Shepherd,UNAVAILABLE",
                "8,Sophie,Cat,1,Persian,PENDING_ADOPTION",
                "9,Coco,Bird,3,Cockatiel,AVAILABLE",
                "10,Max,Dog,7,Bulldog,RETURNED",
                "11,Oscar,Cat,4,Maine Coon,FOSTERED",
                "12,Sunny,Bird,2,Lovebird,ADOPTED",
                "13,Bella,Dog,5,Husky,AVAILABLE",
                "14,Tiger,Cat,3,Scottish Fold,ADOPTED",
                "15,Zazu,Bird,1,Macaw,UNAVAILABLE",
                "16,Daisy,Dog,2,Poodle,PENDING_ADOPTION",
                "17,Gizmo,Cat,6,Ragdoll,AVAILABLE",
                "18,Peppy,Bird,4,Finch,FOSTERED",
                "19,Bruce,Dog,3,Doberman,ADOPTED",
                "20,Leo,Cat,5,British Shorthair,RETURNED"
        );

        try (FileWriter writer = new FileWriter(fileName)) {
            for (String line : petsData) {
                writer.write(line + "\n");
            }
            System.out.println("CSV file created successfully!");
        } catch (IOException e) {
            System.out.println("Error writing to file.");
            e.printStackTrace();
        }
    }
}
