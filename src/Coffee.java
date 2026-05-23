public class Coffee extends Product{

    private String sugar;
    private boolean extraShot;
    private boolean extraVeganMilk;

    public Coffee(String name, double price, String sugar, boolean extraShot,boolean extraVeganMilk) {
        super(name, price);
        this.sugar = sugar;
        this.extraShot = extraShot;
        this.extraVeganMilk = extraVeganMilk;
    }

    @Override
    public double calculateTotal() {
        double finalPrice = price;
        if(extraShot) {
            finalPrice = finalPrice + 0.60;
        }
        if (extraVeganMilk) {
            finalPrice = finalPrice + 0.60;
        }
        return finalPrice;
    }

    @Override
    public String toString() {
        String shot = extraShot ? "(Με Extra Δοση)" : "";
        String milk = extraVeganMilk ? "(Με Extra Γαλα Vegan)" : "";
        return String.format("%s [%s]%s %s: %.2f €", getName(), sugar, shot, milk, calculateTotal());
    }
}
