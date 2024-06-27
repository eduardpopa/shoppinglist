package uni.eduard.popa.shoppinglist.callbacks;

import android.content.Context;
import android.os.Bundle;
import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import uni.eduard.popa.shoppinglist.activities.ListItemsActivity;
import uni.eduard.popa.shoppinglist.activities.ListItemsAdapter;
import uni.eduard.popa.shoppinglist.models.ItemModel;
import uni.eduard.popa.shoppinglist.tasks.SelectItemTask;
import uni.eduard.popa.shoppinglist.tasks.UpdateItemTask;

public class SelectItemCallback implements LoaderManager.LoaderCallbacks<Pair<Integer, ItemModel>> {
    Context context;
    ListItemsAdapter adapter;
    public SelectItemCallback(Context context, ListItemsAdapter adapter){

        this.context=context;
        this.adapter=adapter;
    }
    @NonNull
    @Override
    public Loader<Pair<Integer, ItemModel>> onCreateLoader(int id, @Nullable Bundle args) {
        if(args==null) throw  new NullPointerException("Cannot update null item");
        ItemModel item = args.getParcelable(ListItemsActivity.KEY_ITEM);
        if(item==null )throw  new NullPointerException("Cannot update null item");

        return new SelectItemTask(context, item ,  args.getInt(ListItemsActivity.KEY_POSITION));
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Pair<Integer, ItemModel>> loader, Pair<Integer, ItemModel> data) {
        adapter.notifyItemChanged(data.first);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Pair<Integer, ItemModel>> loader) {

    }
}
