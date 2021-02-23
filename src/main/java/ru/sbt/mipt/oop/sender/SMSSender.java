package ru.sbt.mipt.oop.sender;

public class SMSSender implements Sender {
    @Override
    public void send(String message) {
        System.out.println(message);
    }

}
