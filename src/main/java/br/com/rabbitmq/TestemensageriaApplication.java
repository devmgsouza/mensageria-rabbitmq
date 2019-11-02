package br.com.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@SpringBootApplication
public class TestemensageriaApplication {




	public static void main(String[] args) {
		SpringApplication.run(TestemensageriaApplication.class, args);



	System.out.println(Runtime.getRuntime().availableProcessors());


		System.out.println("Iniciando Thread Leitura");
		Thread tr = new Read();

		System.out.println("Iniciando Thread Envio");
		Thread t1 = new Send("THREAD 1", 2000);
		Thread t2 = new Send("THREAD 2", 5000);
		Thread t3 = new Send("THREAD 3", 1000);
		Thread t4 = new Send("THREAD 4", 2000);


		tr.start();

		t2.setPriority(Thread.MAX_PRIORITY);
		t1.start();
		t2.start();
		t3.start();
		t4.start();

		System.out.println("Todas as threas instanciadas");


	}
















}
