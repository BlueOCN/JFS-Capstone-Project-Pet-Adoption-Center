public class Bird extends Pet{

    // Additional attributes or behaviors
    private boolean canFly;
    private boolean canTalk;

    Bird(){
        super();
    }

    Bird(String name, String species, int age, String breed, String adoptionStatus){
        super(name, species, age, breed, adoptionStatus);
    }

    Bird(String id, String name, String species, int age, String breed, String adoptionStatus){
        super(id, name, species, age, breed, adoptionStatus);
    }

    public boolean isCanTalk() {
        return canTalk;
    }

    public void setCanTalk(boolean canTalk) {
        this.canTalk = canTalk;
    }

    public boolean isCanFly() {
        return canFly;
    }

    public void setCanFly(boolean canFly) {
        this.canFly = canFly;
    }

    @Override
    public String toString() {
        return "Bird{" +
                "canFly=" + canFly +
                ", canTalk=" + canTalk +
                "} " + super.toString();
    }
}
