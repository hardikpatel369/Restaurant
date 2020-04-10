package com.nspl.restaurant.RetrofitApi.ApiClasses.Kitchen;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

public class KitchenDiffUtil extends DiffUtil.Callback {

    private List<ClsKitchenSection> OldList;
    private List<ClsKitchenSection> NewList;

    public KitchenDiffUtil(List<ClsKitchenSection> OldList, List<ClsKitchenSection> NewList) {
        this.OldList = OldList;
        this.NewList = NewList;
    }

    @Override
    public int getOldListSize() {
        return OldList.size();
    }

    @Override
    public int getNewListSize() {
        return NewList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return OldList.get(oldItemPosition).getORDERNO().equals(NewList.get(
                newItemPosition).getORDERNO());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final ClsKitchenSection oldList = OldList.get(oldItemPosition);
        final ClsKitchenSection newList = NewList.get(newItemPosition);

        return oldList.getORDERTIME().equals(newList.getORDERTIME());
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
