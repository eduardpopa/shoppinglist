package uni.eduard.popa.shoppinglist;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView nameView;
    TextView descriptionView;
    public ItemViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
        super(itemView);
        imageView =itemView.findViewById(R.id.imageView);
        nameView = itemView.findViewById(R.id.nameView);
        descriptionView = itemView.findViewById(R.id.descriptionView);

        itemView.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             if (recyclerViewInterface != null) {
                                                 int position = getAdapterPosition();
                                                 if (position != RecyclerView.NO_POSITION) {
                                                     recyclerViewInterface.onItemSelect(position);
                                                 }
                                             }
                                         }
                                     });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
                                            @Override
                                            public boolean onLongClick(View v) {
                                                if (recyclerViewInterface != null) {
                                                    int position = getAdapterPosition();
                                                    if (position != RecyclerView.NO_POSITION) {
                                                        recyclerViewInterface.onItemEdit(position);
                                                        return true;
                                                    }
                                                }
                                                return false;
                                            }
                                        });


    }
}
