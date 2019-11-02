package br.com.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send extends Thread implements Runnable{

    String threadName;
    long milisec;

    public Send(String threadName, long milisec){
        this.threadName = threadName;
        this.milisec = milisec;
    }


    public void sendMessage(String MENSAGEM, String QUEUE_NAME) throws Exception{


        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

            Connection connection = factory.newConnection();
             Channel channel = connection.createChannel();

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = MENSAGEM;
            System.out.println("Enviando mensagem");
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());



    }

    @Override
    public synchronized void run() {
        try {
            Thread.sleep(milisec);


            while (true) {
                for (int i = 0; i < 10; i++) {
                    try {
                        this.sendMessage(this.threadName + ":" + i, "HELLOW");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                Thread.sleep(milisec);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
