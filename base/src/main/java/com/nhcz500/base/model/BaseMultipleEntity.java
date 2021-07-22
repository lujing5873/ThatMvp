package com.nhcz500.base.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class BaseMultipleEntity<T>  implements MultiItemEntity {

    public BaseMultipleEntity() {
    }

    public BaseMultipleEntity(int type, T data) {
        this.type = type;
        this.data = data;
    }

    private int type;
    private  T data;
    @Override
    public int getItemType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
