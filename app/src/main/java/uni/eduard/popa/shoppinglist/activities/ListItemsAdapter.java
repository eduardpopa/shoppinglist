package uni.eduard.popa.shoppinglist.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.loader.app.LoaderManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import uni.eduard.popa.shoppinglist.R;
import uni.eduard.popa.shoppinglist.callbacks.UpdateItemCallback;
import uni.eduard.popa.shoppinglist.models.ItemModel;

public class ListItemsAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    Context context;

    public void setItems(List<ItemModel> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    List<ItemModel> items;
    private final RecyclerViewInterface  recyclerViewInterface;

    public ListItemsAdapter(Context context, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.items = new ArrayList<>();
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false);
        ItemViewHolder holder = new ItemViewHolder(view, recyclerViewInterface);
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int position =holder.getAdapterPosition();
//                items.get(position).setBought(!items.get(position).getBought());
//                notifyItemChanged(position);
//            }
//        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.imageView.setImageResource(items.get(position).getImage());
        holder.nameView.setText(items.get(position).getName() );
        holder.descriptionView.setText(items.get(position).getDescription() +" ID:"+items.get(position).getId() +" POS:"+items.get(position).getPosition());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public void removeItem(int position) {
        items.remove(position);
        notifyItemRemoved(position);
    }
    public void addItem(ItemModel item) {
        items.add(item);
        notifyItemInserted(items.size()-1);
    }
    public void restoreItem(ItemModel item, int position) {
        items.add(position, item);
        notifyItemInserted(position);
    }
    public void updateItem(ItemModel item, int position) {
        items.set(position, item);
        notifyItemInserted(position);
    }

    public List<ItemModel> getData() {
        return items;
    }
    
    public void sortItems(){
        Comparator<ItemModel> comparator = new Comparator<ItemModel>() {
            @Override
            public int compare(ItemModel o1, ItemModel o2) {
                if(o1.getChecked() && !o2.getChecked()){
                    return 1;
                }else if(!o1.getChecked() && o2.getChecked()){
                    return -1;
                }else {
                    return o1.getPosition()-o2.getPosition();
                }
            }
        };
        items.sort(comparator);
        notifyDataSetChanged();
    }
    public void resetItems(){
        for(ItemModel item :items){
            item.setChecked(false);
        }
        sortItems();
    }

    public void swapItems(int fromPosition, int toPosition){
        Collections.swap(items, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }
}
