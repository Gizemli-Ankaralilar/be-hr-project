package com.team1.config.rabbitmq;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {


    //create auth
    //kuyruk oluşturabilmek için bize gerekli olan 3 sabit değişken üretildi
    @Value("${rabbitmqKey.auth-exchange}")
    private String exchange = "auth-exchange";
    @Value("${rabbitmqKey.register-binding-key}")
    private String createAuthQueue = "auth-company-queue";
    @Value("${rabbitmqKey.register-queue}")
    private String createAuthBindingKey = "auth-company-bindingkey";

    //Bundan sonrası ezber

    @Bean
    DirectExchange exchange(){
        return new DirectExchange(exchange);//amqp.QUEUEU DEN OLUŞTURULACAK
    }


    @Bean
    Queue createAuthQueue(){
        return new Queue(createAuthQueue);//amqp.QUEUEU DEN OLUŞTURULACAK
    }



    ////BU İKİ DEĞER KULLANILARAK BAĞLANACAKLAR.İÇERİSİNE ALACAĞI PARAMETRELER ÜZERİNDE YAZILAN METOTLARLA AYNI İSMİ ALICAK
    //DİKKAT EDİLMESİ GEREKEN ŞEYLER AYNI DEĞİŞKEN VE İSİMERİ KULLANILMASI GEREKLİ
    @Bean
    public Binding createPostBindingKey(final Queue createAuthQueue, final DirectExchange exchange){
        return BindingBuilder.bind(createAuthQueue).to(exchange).with(createAuthBindingKey);
    }
}
