package com.micro.service.webmvc.designModel;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by xiaos 2018/6/22
 * java自带观察者模式
 */
public class MObserver extends Observable{

    public static void main(String[] args) {
        MObserver subject = new MObserver();
        subject.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object value) {
                System.out.println(value);
            }
        });
        subject.setChanged();
        subject.notifyObservers("hello world");
    }
}
