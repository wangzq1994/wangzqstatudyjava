package com.itzq.spring.mq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @author shkstart
 * @create 2020-07-1911:39
 */
public class JmsConsumer {
    private static final String ACTIVEMQ_URL="tcp://172.20.10.4:61616";
    private static final String QUEEN="wzq";

    public static void main(String[] args) throws JMSException, IOException {
        // 1 按照给定的url创建连接工厂，这个构造器采用默认的用户名密码。该类的其他构造方法可以指定用户名和密码
        ActiveMQConnectionFactory activeMQConnectionFactory=new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        // 2 通过连接工厂，获得连接 connection 并启动访问。
        Connection connection = activeMQConnectionFactory.createConnection();
        //启动连接
        connection.start();
        // 3 创建会话session 。第一参数是是否开启事务， 第二参数是消息签收的方式
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建消息的生产者
        Queue queue = session.createQueue(QUEEN);
        // 5 创建消息的消费者
        MessageConsumer messageConsumer = session.createConsumer(queue);
        messageConsumer.setMessageListener((Message message)->{
            if(null != message  && message instanceof TextMessage){
                TextMessage textMessage = (TextMessage)message;
                try {
                    System.out.println("****消费者的消息："+textMessage.getText());
                }catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
            // 让主线程不要结束。因为一旦主线程结束了，其他的线程（如此处的监听消息的线程）也都会被迫结束。
            // 实际开发中，我们的程序会一直运行，这句代码都会省略
            System.in.read();
        messageConsumer.close();
        session.close();
        connection.close();
    }

    private static void jmswhile() throws JMSException {
        // 1 按照给定的url创建连接工厂，这个构造器采用默认的用户名密码。该类的其他构造方法可以指定用户名和密码
        ActiveMQConnectionFactory activeMQConnectionFactory=new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        // 2 通过连接工厂，获得连接 connection 并启动访问。
        Connection connection = activeMQConnectionFactory.createConnection();
        //启动连接
        connection.start();
        // 3 创建会话session 。第一参数是是否开启事务， 第二参数是消息签收的方式
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建消息的生产者
        Queue queue = session.createQueue(QUEEN);
        // 5 创建消息的消费者
        MessageConsumer messageConsumer = session.createConsumer(queue);
        // 6 通过messageProducer 生产 3 条 消息发送到消息队列中
        while(true){
            // reveive() 一直等待接收消息，在能够接收到消息之前将一直阻塞。 是同步阻塞方式 。和socket的accept方法类似的。
            // reveive(Long time) : 等待n毫秒之后还没有收到消息，就是结束阻塞。
            // 因为消息发送者是 TextMessage，所以消息接受者也要是TextMessage
            TextMessage message = (TextMessage)messageConsumer.receive();
            //TextMessage message = (TextMessage)messageConsumer.receive(2000);
            if (null != message){
                System.out.println("****消费者的消息："+message.getText());
            }else {

                break;
            }

        }

        messageConsumer.close();
        session.close();
        connection.close();
    }
}
