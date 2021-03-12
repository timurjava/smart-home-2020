package ru.sbt.mipt.oop.Alarm;

public class SMSSender  implements Sender{
    @Override
    public void send(String str){
        System.out.println("Send message: "+ str);
    }
}
