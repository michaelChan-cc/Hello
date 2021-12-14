package mq.rocket;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;

public class RocketMqTest {
//    static String topic = "diagnosisEqtInfoDataPushT";
static String topic = "thirdNeedAreaInfoT";

    static String tag = "ccc";
    static String message = "{\n" +
            "    \"orgId\": 116521458741\n" +
            "}";
//    static String message = "{\n" +
//            "    \"manufacturerName\": \"ILINECN\",\n" +
//            "    \"operateTag\": \"INSERT\",\n" +
//            "    \"deviceId\": 11\n" +
//            "}";

    public static void main(String[] args) throws MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer("ProducerGroupName_");
//        producer.setNamesrvAddr("192.168.14.13:9876");
        producer.setNamesrvAddr("58.213.162.82:8451");
        producer.setInstanceName("ProducerByCC");
        producer.setVipChannelEnabled(false);

        producer.start();// once


        Message msg = new Message(topic,
                tag,
                "WangXiaoRui",
                message.getBytes());
        try {
            {
                SendResult sendResult = producer.send(msg);
                if(sendResult ==null || sendResult.getSendStatus() != SendStatus.SEND_OK){
                    System.err.println(sendResult);
                }
                System.out.println(sendResult.toString());
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        producer.shutdown();
    }
}
