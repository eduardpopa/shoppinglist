package uni.eduard.popa.shoppinglist.tasks;

import android.content.Context;
import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

import uni.eduard.popa.shoppinglist.models.ItemModel;
import uni.eduard.popa.shoppinglist.persistance.ShoppingListDatabase;

public class ResetIemsTask extends AsyncTaskLoader<List<ItemModel>> {
    public ResetIemsTask(Context context) {
        super(context);
    }

    @Nullable
    @Override
    public List<ItemModel> loadInBackground() {
        List<ItemModel> items = ShoppingListDatabase.getDatabase(getContext()).itemModel().loadAll();
        for(ItemModel item:items) {
            if(item.getChecked()) {
                item.setChecked(false);
                ShoppingListDatabase.getDatabase(getContext()).itemModel().update(item);
            }
        }
        return items;
    }
}
