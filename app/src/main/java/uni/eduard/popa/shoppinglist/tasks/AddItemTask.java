package uni.eduard.popa.shoppinglist.tasks;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import uni.eduard.popa.shoppinglist.models.ItemModel;
import uni.eduard.popa.shoppinglist.persistance.ShoppingListDatabase;

public class AddItemTask extends AsyncTaskLoader<ItemModel>  {
    ItemModel item;
    public AddItemTask(@NonNull Context context, ItemModel item) {
        super(context);
        this.item = item;
    }

    @Nullable
    @Override
    public ItemModel loadInBackground() {
        long id = ShoppingListDatabase.getDatabase(getContext()).itemModel().insert(item);
        item.setId(id);
        return item;
    }
}
