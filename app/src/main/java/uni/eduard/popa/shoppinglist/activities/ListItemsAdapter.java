package uni.eduard.popa.shoppinglist.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import uni.eduard.popa.shoppinglist.R;
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
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.imageView.setImageResource(items.get(position).getImage());
        holder.nameView.setText(items.get(position).getName() );
        holder.descriptionView.setText(items.get(position).getDescription() );
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
    public void updateItem(ItemModel item, int position) {
        items.set(position, item);
        notifyItemChanged(position);
    }

    public List<ItemModel> getData() {
        return items;
    }
    public void swapItems(int fromPosition, int toPosition){
        Collections.swap(items, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }
}
