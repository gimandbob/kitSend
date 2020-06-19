package covidtest;

import covidtest.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{

    @Autowired
    SendRepository sendRepository;
    
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverInspectCancelled_SendCancel(@Payload InspectCancelled inspectCancelled) throws Exception {

        if(inspectCancelled.isMe()){
            Send send = sendRepository.findByInspectionId(inspectCancelled.getId())
                    .orElseThrow(() -> new Exception("send not found"));
            send.setStatus("CANCELLED");
            sendRepository.save(send);
            System.out.println("##### listener SendCancel : " + inspectCancelled.toJson());
        }
    }

}
