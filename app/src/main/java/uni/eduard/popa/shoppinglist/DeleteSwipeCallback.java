package uni.eduard.popa.shoppinglist;


    import android.content.Context;
import android.graphics.Canvas;
    import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

    import androidx.annotation.NonNull;
    import androidx.core.content.ContextCompat;
    import androidx.recyclerview.widget.ItemTouchHelper;
    import androidx.recyclerview.widget.RecyclerView;

abstract public class DeleteSwipeCallback extends SwipeCallback{

    DeleteSwipeCallback(Context context, int direction, int icon, int backgroundColor) {
        super(context, direction, icon, backgroundColor);
    }
}

