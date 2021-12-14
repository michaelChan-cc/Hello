package tool.jmeter;


import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;

public class RocketMqProducer implements JavaSamplerClient {
    String topic = "diagnosisEqtInfoDataPushT";
    String tag = "ccc";
    String message = "{\n" +
            "    \"manufacturerName\": \"ILINECN\",\n" +
            "    \"operateTag\": \"INSERT\",\n" +
            "    \"deviceId\": 11\n" +
            "}";

    @Override
    public void setupTest(JavaSamplerContext arg0) {
        topic = arg0.getParameter( "topic");
        tag = arg0.getParameter( "tag");
        message = arg0.getParameter("message");
    }

    @Override
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        SampleResult sr = new SampleResult();
        sr.setSampleLabel("RocketMQ测试");
        try {
            sr.sampleStart();
            DefaultMQProducer producer = new DefaultMQProducer("ProducerGroupName_");
            producer.setNamesrvAddr("192.168.14.13:9876");
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
                }

            }catch (Exception e){
                e.printStackTrace();;
            }

            producer.shutdown();

            sr.setResponseData("success","utf-8");
            sr.setDataType(SampleResult.TEXT);
            sr.setSuccessful(true);
        }catch(Exception e){
            sr.setSuccessful(false);
            sr.setResponseData(e.getMessage(),"utf-8");
            e.printStackTrace();
        }
        finally {

            sr.sampleEnd();
        }


        return sr;

    }

    @Override
    public void teardownTest(JavaSamplerContext javaSamplerContext) {

    }

    @Override
    public Arguments getDefaultParameters() {
        return null;
    }
}
