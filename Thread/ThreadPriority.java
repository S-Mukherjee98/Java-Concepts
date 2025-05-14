package Thread;

class PriorityThread1 implements Runnable{
    @Override
    public void run(){
        for (int i = 0; i < 10; i++) {
            
            try {
                System.out.println("Thread 1 is on its way ....");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();

            }
            
        }
    }
}

class PriorityThread2 implements Runnable{
    @Override
    public void run(){
        for(int i=0; i<10; i++){
            try {
                System.out.println("Thread 2 is on its way . . . ");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                
                Thread.currentThread().interrupt();
            }
            
        }
    }
}


public class ThreadPriority {
    public static void main(String[] args) {

        PriorityThread1 thread1 = new PriorityThread1();
        PriorityThread2 thread2 = new PriorityThread2();

        Thread t1= new Thread(thread1);
        Thread t2 = new Thread(thread2);

        t2.setPriority(1);
        t1.setPriority(10);
        
        t2.start();
        t1.start();
        
    }
}
