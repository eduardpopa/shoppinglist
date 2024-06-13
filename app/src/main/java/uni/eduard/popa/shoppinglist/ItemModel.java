package uni.eduard.popa.shoppinglist;

public class ItemModel {
    public boolean getBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }

    private boolean bought;
    public ItemModel( String name, String description) {
        this.name = name;
        this.description = description;
        bought =false;
    }

    public int getImage() {
        return bought? R.drawable.grocery4 : R.drawable.grocery1;
    }

    public void setImage(int image) {
        this.image = image;
    }

    private int image;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
