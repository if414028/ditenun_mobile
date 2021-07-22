package com.example.ditenun.utility;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SimpleRecyclerAdapter<T> extends RecyclerView.Adapter<SimpleRecyclerAdapter.SimpleViewHolder> {

    protected List<T> mainData;
    private SimpleRecyclerAdapter.OnViewHolder<T> listener;
    private @LayoutRes
    int layoutRes;

    public SimpleRecyclerAdapter(List<T> mainData, int layoutRes, SimpleRecyclerAdapter.OnViewHolder<T> listener) {
        this.mainData = mainData;
        this.layoutRes = layoutRes;
        this.listener = listener;
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        private SimpleRecyclerAdapter.OnViewHolder listener;
        private ViewDataBinding layoutBinding;
        private SimpleRecyclerAdapter adapter;

        public SimpleViewHolder(View itemView, SimpleRecyclerAdapter.OnViewHolder listener, SimpleRecyclerAdapter adapter) {
            super(itemView);

            try {
                this.listener = listener;
                this.adapter = adapter;
                layoutBinding = DataBindingUtil.bind(itemView);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }

        public SimpleViewHolder(View itemView, SimpleRecyclerAdapter.OnViewHolder listener) {
            this (itemView, listener, null);
        }

        public ViewDataBinding getLayoutBinding() {
            return layoutBinding;
        }

        public SimpleRecyclerAdapter getAdapter () {
            return adapter;
        }
    }


    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutRes(), parent, false);
        return new SimpleViewHolder(view, getListener(), this);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        T t = mainData.get(position);
        getListener().onBindView(holder, t);
    }


    @Override
    public int getItemCount() {
        return mainData == null ? 0 : mainData.size();
    }

    public T getItemBy(T t) {
        for (T mainDatum : mainData) {
            if (mainDatum.equals(t)) {
                return mainDatum;
            }
        }
        return null;
    }

    public T getItemAt(int position) {
        return this.mainData.get(position);
    }

    public void addItem(final T t) {
        addItemAt(t, mainData.size());
    }

    public void addItemAt(final T t, final int i) {
        this.mainData.add(i, t);
    }

    public void setItemAt (T t, int i) {
        this.mainData.set(i, t);
    }

    public void addAllItemRelyingPassByValue(List<T> t) {
        this.mainData = t;
    }

    public void addAllItem(final List<T> t) {
        for (T item:t) {
            if (!mainData.contains(item)) {
                mainData.add(item);
            }
        }
    }

    public void addAllItemWithIndex(final int position, final List<T> t){
        this.mainData.addAll(position, t);
    }

    public void removeItem(final T t) {
        this.mainData.remove(t);
    }

    public void remove(final List<T> t) {
        for (T t1 : t) {
            this.mainData.remove(t1);
        }
    }

    public void removeAt(int position) {
        this.mainData.remove(position);
    }

    public void removeAll() {
        if (this.mainData != null) this.mainData.clear();
    }

    public List<T> getMainData() {
        return mainData;
    }

    public void setMainData(List<T> mainData) {
        this.mainData = mainData;
    }

    public SimpleRecyclerAdapter.OnViewHolder<T> getListener() {
        return listener;
    }

    public void setListener(SimpleRecyclerAdapter.OnViewHolder<T> listener) {
        this.listener = listener;
    }

    public int getLayoutRes() {
        return layoutRes;
    }

    public void setLayoutRes(int layoutRes) {
        this.layoutRes = layoutRes;
    }

    public interface OnViewHolder<T> {
        void onBindView(SimpleViewHolder holder, T item);
    }

}
