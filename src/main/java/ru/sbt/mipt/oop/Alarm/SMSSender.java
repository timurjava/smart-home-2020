package ru.sbt.mipt.oop.Alarm;

import org.springframework.stereotype.Service;

@Service
public class SMSSender  implements Sender{
    @Override
    public void send(String str){
        System.out.println("Send message: "+ str);
    }
}
