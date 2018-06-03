package com.martin.andolib_les2_3;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class RxBus {
    private static RxBus instance;
    private PublishSubject<Object> subject = PublishSubject.create();

    public static RxBus getInstance() {
        if (instance == null) instance = new RxBus();
        return instance;
    }

    public void send(Object object) {
        subject.onNext(object);
    }

    public Observable<Object> getEvents() {
        return subject;
    }
}
