package Thread;

class Shared{
    volatile  boolean flag = false;

    void write(){
        System.out.println("Writer : Setting the flag to TRUE ");
        this.flag=true;
    }

    void read(){
        System.out.println("Reader : Waiting for flag to become true...");
        while (!flag) { 
            
        }
        System.out.println("Reader: Detected flag is true !!!");
    }
}


public class VolitileExample {

    public static void main(String[] args) {
        
        Shared shared = new Shared();
        
        Thread readeThread = new Thread(()->{
            shared.read();
        });

        Thread writeThread = new Thread(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupted();
            }
            shared.write();
        });

        readeThread.start();
        writeThread.start();
    }
    
}
