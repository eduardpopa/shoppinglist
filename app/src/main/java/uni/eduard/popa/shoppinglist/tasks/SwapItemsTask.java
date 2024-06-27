package uni.eduard.popa.shoppinglist.tasks;

import android.content.Context;
import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.Collections;
import java.util.List;

import uni.eduard.popa.shoppinglist.models.ItemModel;
import uni.eduard.popa.shoppinglist.persistance.ShoppingListDatabase;

public class SwapItemsTask extends AsyncTaskLoader<Pair<Integer, Integer>> {
    int fromPosition;
    int toPosition;
    public SwapItemsTask(@NonNull Context context, int fromPosition, int toPosition) {
        super(context);
        this.fromPosition=fromPosition;
        this.toPosition=toPosition;
    }

    @Nullable
    @Override
    public Pair<Integer, Integer> loadInBackground() {

        List<ItemModel> items = ShoppingListDatabase.getDatabase(getContext()).itemModel().loadAll();
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                items.get(i).setPosition(i + 1);
                ShoppingListDatabase.getDatabase(getContext()).itemModel().update(items.get(i));
                items.get(i + 1).setPosition(i);
                ShoppingListDatabase.getDatabase(getContext()).itemModel().update(items.get(i+1));

            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                items.get(i).setPosition(i - 1);
                ShoppingListDatabase.getDatabase(getContext()).itemModel().update(items.get(i));
                items.get(i - 1).setPosition(i);
                ShoppingListDatabase.getDatabase(getContext()).itemModel().update(items.get(i-1));
            }
        }

        return new Pair<>(fromPosition, toPosition);
    }
}
