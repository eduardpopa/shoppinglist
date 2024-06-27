package uni.eduard.popa.shoppinglist.activities;

import uni.eduard.popa.shoppinglist.activities.ItemViewHolder;

public interface RecyclerViewInterface {
    void onItemEdit(int position);
    void onItemDelete(int position);
    void onItemSelect(int position);
    void onRowMoved(int fromPosition, int toPosition);

    void onRowSelected(ItemViewHolder viewHolder);
    void onRowClear(ItemViewHolder viewHolder);

}
