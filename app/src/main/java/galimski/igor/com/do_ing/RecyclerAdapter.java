package galimski.igor.com.do_ing;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private Task[] tasks;

    // класс view holder-а с помощью которого мы получаем ссылку на каждый элемент
    // отдельного пункта списка
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // наш пункт состоит только из одного TextView
        public TextView shortDescrView;
        public ImageView iconView;

        public ViewHolder(View v) {
            super(v);

            shortDescrView = (TextView) v.findViewById(R.id.short_description_text);
            iconView = (ImageView) v.findViewById(R.id.iconView);
        }
    }

    // Конструктор
    public RecyclerAdapter(ArrayList<Task> tasksToShow) {
        tasks = new Task[tasksToShow.size()];
        tasks = tasksToShow.toArray(tasks);
    }

    // Создает новые views (вызывается layout manager-ом)
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);

        // тут можно программно менять атрибуты лэйаута (size, margins, paddings и др.)

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Заменяет контент отдельного view (вызывается layout manager-ом)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.shortDescrView.setText(tasks[position].ShortDescription);
        holder.iconView.setColorFilter(Color.RED);
    }

    // Возвращает размер данных (вызывается layout manager-ом)
    @Override
    public int getItemCount() {
        return tasks.length;
    }
}

