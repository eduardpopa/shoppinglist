package uni.eduard.popa.shoppinglist.callbacks;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import java.util.List;

import uni.eduard.popa.shoppinglist.activities.ListItemsAdapter;
import uni.eduard.popa.shoppinglist.models.ItemModel;
import uni.eduard.popa.shoppinglist.tasks.LoadItemsTask;
import uni.eduard.popa.shoppinglist.tasks.ResetIemsTask;

public class ResetItemsCallback implements LoaderManager.LoaderCallbacks<List<ItemModel>> {
    Context context;
    ListItemsAdapter adapter;
    public ResetItemsCallback(Context context, ListItemsAdapter adapter){
        this.context=context;
        this.adapter=adapter;
    }
    @NonNull
    @Override
    public Loader<List<ItemModel>> onCreateLoader(int id, @Nullable Bundle args) {
        return new ResetIemsTask(context);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<ItemModel>> loader, List<ItemModel> data) {
        adapter.setItems(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<ItemModel>> loader) {

    }
}
