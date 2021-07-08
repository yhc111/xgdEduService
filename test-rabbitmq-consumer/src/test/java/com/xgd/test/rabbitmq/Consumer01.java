package com.xgd.test.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * 入门程序消费者
 * @Author: Shawn Yin
 * @Date: 2021/5/23 9:52
 */
public class Consumer01 {

    // 队列
    private static final String QUEUE = "Helloworld";

    public static void main(String[] args) throws Exception {
        // 通过连接工厂创建新的连接和mq建立连接
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672); // 端口
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        // 设置虚拟机，一个mq的服务可以设置多个虚拟机，每个虚拟机就相当于一个独立的mq
        connectionFactory.setVirtualHost("/");

        // 建立新连接
        Connection connection = connectionFactory.newConnection();
        // 创建会话通道，生产者和mq服务所有的通信都在channel通道中完成
        Channel channel = connection.createChannel();

        // 监听队列
        channel.queueDeclare(QUEUE, true, false, false, null);

        // 实现消费方法
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            /**
             * 当接收到消息后，此方法将被调用
             * @param consumerTag 消费者标签，用来标识消费者，在监听队列时设置channel.basicConsume
             * @param envelope 信封，通过envelope
             * @param properties 消息属性
             * @param body 消息内容
             * @throws IOException
             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                // 交换机
                String exchange = envelope.getExchange();
                // 消息id，mq在channel中用来标识消息的id，可用于确认消息已经接收
                long deliveryTag = envelope.getDeliveryTag();
                // 消息内容
                String message = new String(body, "utf-8");
                System.out.println("receive message:" + message);
            }
        };

        // 监听队列
        // 参数：String queue，boolean autoAck，Consumer callback
        channel.basicConsume(QUEUE, true, defaultConsumer);
    }

}
