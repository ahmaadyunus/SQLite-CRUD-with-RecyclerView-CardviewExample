package com.example.ahmaadyunus.sqlite;

import android.view.View;

/**
 * Created by ahmaadyunus on 21/12/16.
 */

public interface RecyclerViewClickListener {
    void onClick(View view, int position);

    void onLongClick(View view, int position);
}
