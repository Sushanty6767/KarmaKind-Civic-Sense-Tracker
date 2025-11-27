package com.karmakind.model;

public interface Trackable {
    int calculatePoints();
    default void onComplete() {}
}
