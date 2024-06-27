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
import uni.eduard.popa.shoppinglist.tasks.SwapItemsTask;
import uni.eduard.popa.shoppinglist.tasks.UpdateItemTask;

public class SwapItemCallback implements LoaderManager.LoaderCallbacks<Pair<Integer, Integer>> {
    Context context;
    ListItemsAdapter adapter;
    public SwapItemCallback(Context context, ListItemsAdapter adapter){

        this.context=context;
        this.adapter=adapter;
    }
    @NonNull
    @Override
    public Loader<Pair<Integer, Integer>> onCreateLoader(int id, @Nullable Bundle args) {
        if(args==null) throw  new NullPointerException("Cannot update null item");
        return new SwapItemsTask(context, args.getInt(ListItemsActivity.KEY_FROM_POSITION), args.getInt(ListItemsActivity.KEY_TO_POSITION));
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Pair<Integer, Integer>> loader, Pair<Integer, Integer> data) {
        adapter.swapItems(data.first, data.second);
    }


    @Override
    public void onLoaderReset(@NonNull Loader<Pair<Integer, Integer>> loader) {

    }
}
