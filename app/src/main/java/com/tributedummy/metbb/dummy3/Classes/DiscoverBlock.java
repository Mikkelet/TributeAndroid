package com.tributedummy.metbb.dummy3.Classes;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.Objects;

public class DiscoverBlock {
    private String title;
    private View.OnClickListener action;
    private RecyclerView.Adapter adapter;

    public DiscoverBlock(String title, View.OnClickListener action, RecyclerView.Adapter adapter) {
        this.title = title;
        this.action = action;
        this.adapter = adapter;
    }

    public String getTitle() {
        return title;
    }

    public View.OnClickListener getAction() {
        return action;
    }

    public RecyclerView.Adapter getAdapter() {
        return adapter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DiscoverBlock)) return false;
        DiscoverBlock that = (DiscoverBlock) o;
        return Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {

        return Objects.hash(title, action, adapter);
    }
}
