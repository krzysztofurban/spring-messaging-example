package pl.krzysztofurban.springmessagingexample.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaProducer {
    private static final String TOPIC = "orders";

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Scheduled
    public void sendMessage() {
        String message = "Next order reveiver at " + Calendar.getInstance().getTime();
        log.info("Kafka Message sent : {}", message);
        kafkaTemplate.send(TOPIC, message);
    }
}
