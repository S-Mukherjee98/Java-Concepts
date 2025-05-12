package Thread;

class SharedQueue{
    int item ;
    boolean available=false;
    public synchronized void producer(int val){
        while(available){
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        this.item = val;
        this.available=true;
        System.out.println("Produced : "+item);
        notify();
    }

    public synchronized void consumer(){
        while (!available) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        this.available=false;
        System.out.println("Consumed : "+item);
        notify();
    }
}

class Producer implements Runnable{
    private  final SharedQueue queue;
    public Producer(SharedQueue queue){
        this.queue=queue;
    }
    @Override
    public void run(){
        for(int i = 0; i<10; i++){
            queue.producer(i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

class Consumer implements Runnable{
    private final SharedQueue queue;
    public Consumer(SharedQueue queue){
        this.queue=queue;
    }
    @Override
    public void run(){
        for (int i = 0; i < 10; i++) {
            queue.consumer();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

public class ProducerConsumerExample {
    public static void main(String[] args) {
        SharedQueue sharedQueue = new SharedQueue();
        Thread producerThread = new Thread(new Producer(sharedQueue));
        Thread consumerThread = new Thread(new Consumer(sharedQueue) );

        producerThread.start();
        consumerThread.start();
    }
}
