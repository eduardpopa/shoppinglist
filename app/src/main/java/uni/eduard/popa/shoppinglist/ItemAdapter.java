package uni.eduard.popa.shoppinglist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Comparator;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    Context context;

    public void setItems(List<ItemModel> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    List<ItemModel> items;
    private final RecyclerViewInterface  recyclerViewInterface;

    public ItemAdapter(Context context, List<ItemModel> items, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.items = items;
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
        holder.nameView.setText(items.get(position).getName());
        holder.descriptionView.setText(items.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public void removeItem(int position) {
        items.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(ItemModel item, int position) {
        items.add(position, item);
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
                    return o1.getOrder()-o2.getOrder();
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
}
