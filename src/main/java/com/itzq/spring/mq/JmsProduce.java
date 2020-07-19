package com.itzq.spring.mq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author shkstart
 * @create 2020-07-1911:14
 */
public class JmsProduce {
    private static final String ACTIVEMQ_URL="tcp://172.20.10.4:61616";
    private static final String QUEEN="wzq";

    public static void main(String[] args) throws JMSException {
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
        // 5 创建消息的生产者
        MessageProducer messageProducer = session.createProducer(queue);
        //设置消息是否持久化
        messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
        // 6 通过messageProducer 生产 3 条 消息发送到消息队列中
        for (int i = 0; i < 3; i++) {
            // 7  创建消息
            TextMessage message=session.createTextMessage("MQ==QUEEN消息第"+i+"条");
            messageProducer.send(message);
        }
        messageProducer.close();
        session.close();
        connection.close();

    }
}
