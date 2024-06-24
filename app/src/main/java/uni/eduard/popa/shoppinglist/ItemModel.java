package uni.eduard.popa.shoppinglist;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class ItemModel implements Parcelable {
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    private int order;
    protected ItemModel(Parcel in) {
        order = in.readInt();
        checked = in.readByte() != 0;
        image = in.readInt();
        name = in.readString();
        description = in.readString();
    }

    public static final Creator<ItemModel> CREATOR = new Creator<ItemModel>() {
        @Override
        public ItemModel createFromParcel(Parcel in) {
            return new ItemModel(in);
        }

        @Override
        public ItemModel[] newArray(int size) {
            return new ItemModel[size];
        }
    };

    public boolean getChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    private boolean checked;
    public ItemModel( String name, String description) {
        this.name = name;
        this.description = description;
        this.checked =false;
        this.order =0;
    }

    public int getImage() {
        return checked ? R.drawable.grocery4 : R.drawable.grocery1;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(order);
        dest.writeByte((byte) (checked ? 1 : 0));
        dest.writeInt(image);
        dest.writeString(name);
        dest.writeString(description);
    }
}
