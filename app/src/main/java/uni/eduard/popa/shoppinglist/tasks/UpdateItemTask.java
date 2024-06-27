package uni.eduard.popa.shoppinglist.tasks;

import android.content.Context;
import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import uni.eduard.popa.shoppinglist.models.ItemModel;
import uni.eduard.popa.shoppinglist.persistance.ShoppingListDatabase;

public class UpdateItemTask extends AsyncTaskLoader<Pair<Integer, ItemModel>> {
    ItemModel item;
    int position;
    public UpdateItemTask(@NonNull Context context, ItemModel item, int position) {
        super(context);
        this.item=item;
        this.position = position;
    }

    @Nullable
    @Override
    public Pair<Integer, ItemModel> loadInBackground() {
        ShoppingListDatabase.getDatabase(getContext()).itemModel().update(item);
        return new Pair<>(position, item);
    }
}
