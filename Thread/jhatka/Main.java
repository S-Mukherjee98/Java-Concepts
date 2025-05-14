package Thread.jhatka;

import java.util.*;
//kafka like message queue building 

//craete the Model class for a Message

class Message{
    private long offset;
    private String payload;
    private long timestamp;

    public Message(long offset, String payload, long timestamp){
        this.offset=offset;
        this.payload=payload;
        this.timestamp =timestamp;
    }

    public long getOffset(){
        return offset;
    }
    public String getPayload(){
        return payload;
    }
    public long getTimestamp(){
        return timestamp;
    }
}

//create the partition and topic 

//Partition to hold message
class Partition{
    private List<Message> messages = new ArrayList<>();
    private long currentOffset=0;

    public synchronized long addMessage(String payload){
        Message message = new Message(currentOffset++, payload, System.currentTimeMillis());
        messages.add(message);
        return message.getOffset();
    }

    public synchronized List<Message> getMessagesFromOffset(long offset){

        List<Message> result = new ArrayList<>();
        for (Message msg : messages){
            if(msg.getOffset()>= offset){
                result.add(msg);
            }
        }
        return result;
    }
}

//class managing multiple partition

class Topic{
    private String name;
    private List<Partition> partitions;

    public Topic(String name , int partitionCount){
        this.name=name;
        partitions = new ArrayList<>();
        for(int i =0; i<partitionCount;i++){
            partitions.add(new Partition());
        }
    }

    public Partition getPartition(int key){
        return partitions.get(key % partitions.size());
    }
    public Partition getPartitionByIndex(int index){
        return partitions.get(index);
    }
}


// Create The Broker server

class BrokerServer {
    private Map<String, Topic> topics = new HashMap<>();

    public synchronized void createTopic(String topicName, int partitions) {
        topics.put(topicName, new Topic(topicName, partitions));
    }

    public long publish(String topicName, String payload, int key) {
        Topic topic = topics.get(topicName);
        if (topic == null) throw new RuntimeException("Topic not found");
        return topic.getPartition(key).addMessage(payload);
    }

    public List<Message> consume(String topicName, int partitionIndex, long offset) {
        Topic topic = topics.get(topicName);
        if (topic == null) throw new RuntimeException("Topic not found");
        return topic.getPartitionByIndex(partitionIndex).getMessagesFromOffset(offset);
    }
}


class Producer implements Runnable {
    private BrokerServer broker;
    private String topic;
    private int key;
    private List<String> messages;

    public Producer(BrokerServer broker, String topic, int key, List<String> messages) {
        this.broker = broker;
        this.topic = topic;
        this.key = key;
        this.messages = messages;
    }

    @Override
    public void run() {
        for (String msg : messages) {
            long offset = broker.publish(topic, msg, key);
            System.out.println("[Producer] Sent to topic=" + topic + " offset=" + offset);
            try {
                Thread.sleep(100); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

// Consumer 
class Consumer implements Runnable {
    private BrokerServer broker;
    private String topic;
    private int partition;
    private long offset;

    public Consumer(BrokerServer broker, String topic, int partition, long offset) {
        this.broker = broker;
        this.topic = topic;
        this.partition = partition;
        this.offset = offset;
    }

    public void run() {
        while (true) {
            List<Message> messages = broker.consume(topic, partition, offset);
            for (Message msg : messages) {
                System.out.println("[Consumer] Consumed: " + msg.getPayload() + " (offset=" + msg.getOffset() + ")");
                offset = msg.getOffset() + 1;
            }
            try {
                Thread.sleep(200); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}



public class Main {
    public static void main(String[] args) {
        BrokerServer broker = new BrokerServer();
        broker.createTopic("test-topic", 2);

        List<String> producerMessages = Arrays.asList("Hello", "Kafka", "Like", "Tool", "Shubhra");

        Thread producerThread = new Thread(new Producer(broker, "test-topic", 1, producerMessages));
        Thread consumerThread = new Thread(new Consumer(broker, "test-topic", 1, 0));

        producerThread.start();
        consumerThread.start();
    }
}
