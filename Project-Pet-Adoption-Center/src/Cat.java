import java.util.HashSet;

public class Cat extends Pet {

    // Additional attributes or behaviors
    private boolean isIndoor;
    private String scratchingHabit;
    private HashSet<String> scratchingHabitLevels;

    Cat(){
        super();
        isIndoor = false;
        scratchingHabit = "NA";

        // Default Scratching Habit Levels
        this.scratchingHabitLevels = new HashSet<>();
        this.scratchingHabitLevels.add("LOW");
        this.scratchingHabitLevels.add("MEDIUM");
        this.scratchingHabitLevels.add("HIGH");
    }

    Cat(String name, String species, int age, String breed, String adoptionStatus){
        super(name, species, age, breed, adoptionStatus);
    }

    Cat(String id, String name, String species, int age, String breed, String adoptionStatus){
        super(id, name, species, age, breed, adoptionStatus);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "isIndoor=" + isIndoor +
                ", scratchingHabit='" + scratchingHabit + '\'' +
                ", scratchingHabitLevels=" + scratchingHabitLevels +
                "} " + super.toString();
    }
}
