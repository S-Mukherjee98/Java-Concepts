package Thread;



class Worker implements Runnable{
    @Override
    public void run(){
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println("Worker thread is working . . .");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
           
        }
    }
}

public class ThreadJoin {

    public static void main(String[] args) throws InterruptedException {
        Worker worker = new Worker();
        Thread thread = new Thread(worker);
        thread.start();
        thread.join();  
        System.out.println("Now main thread can proceed");
    }
}
