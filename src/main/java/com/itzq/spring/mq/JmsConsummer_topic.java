package com.itzq.spring.mq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @author shkstart
 * @create 2020-07-1917:20
 */
public class JmsConsummer_topic {
    private static final String ACTIVEMQ_URL="tcp://172.20.10.4:61616";
    private static final String QUEEN="wzq";
    private static final String TOPICNAME="wzqtocip";
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
        Topic topic = session.createTopic(TOPICNAME);
        // 5 创建消息的消费者
        MessageConsumer messageConsumer = session.createConsumer(topic);
        messageConsumer.setMessageListener((Message message)->{
            if(null != message  && message instanceof TextMessage){
                TextMessage textMessage = (TextMessage)message;
                try {
                    System.out.println("****消费者的消息："+textMessage.getText());
                }catch (JMSException e) {
                    e.printStackTrace();
                }
            }
            if(null != message  && message instanceof MapMessage){
                MapMessage mapMessage=(MapMessage)message;
                try {
                    System.out.println("MAP****消费者的消息："+mapMessage.getString("A"));
                } catch (JMSException e) {
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
}
