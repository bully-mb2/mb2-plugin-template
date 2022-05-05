package com.example.mqtt;

public class MB2EventListener<T> {

    private final MB2EventCallback<T> callback;

    public MB2EventListener(MB2EventCallback<T> callback) {
        this.callback = callback;
    }

    @SuppressWarnings("unchecked")
    public void raiseEvent(Object event) {
        callback.onEvent((T) event);
    }

}
