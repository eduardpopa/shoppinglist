package uni.eduard.popa.shoppinglist;

public interface RecyclerViewInterface {
    void onItemEdit(int position);
    void onItemSelect(int position);
    void onRowMoved(int fromPosition, int toPosition);

    void onRowSelected(ItemViewHolder viewHolder);

    void onRowClear(ItemViewHolder viewHolder);
//
//    void onItemUndoDelete(int position);
}
