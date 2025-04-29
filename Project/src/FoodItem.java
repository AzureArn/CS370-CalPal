public class FoodItem {
    private String name;
    private int caloriesPerServing;
    private int gramsPerServing;

    public FoodItem(String name, int caloriesPerServing, int gramsPerServing){
        this.name = name;
        this.caloriesPerServing = caloriesPerServing;
        this.gramsPerServing = gramsPerServing;
    }


    public String display() { return this.name + " " + this.caloriesPerServing + " " + this.gramsPerServing; }

    @Override
    public String toString(){ return this.name; }
}
