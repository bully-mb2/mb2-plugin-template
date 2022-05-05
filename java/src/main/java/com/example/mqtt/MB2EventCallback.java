package com.example.mqtt;

public interface MB2EventCallback<T> {
    void onEvent(T event);
}
