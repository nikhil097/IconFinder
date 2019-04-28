package com.app.nikhil.iconfinderapp.Rest;

public interface ResponseCallback<T> {

    void success(T t);

    void  failure(T t);

}
