package Design_Pattern.creational;


// 1. Eager Initialization - Thread Safe
class EagerSingleton{
   private static final EagerSingleton instance = new EagerSingleton();
   private EagerSingleton(){}
   public static EagerSingleton getInstance(){
    return instance;
   }

   public void show(){
    System.out.println("THREAD SAFE :: Eager singleton design pattern : "+this.hashCode());
   }

}


// 2. Lazy Initialization (Not Thread Safe)

class LazySingleton {
    private static LazySingleton instance;

    private LazySingleton() {}

    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }

    public void show() {
        System.out.println("LazySingleton instance: " + this.hashCode());
    }
}

//Using syncronized : Thread Safe

class ThreadSafeSingleton {

    private static  ThreadSafeSingleton  instance;

    private ThreadSafeSingleton(){}

    public static synchronized ThreadSafeSingleton getInstance(){
        if(instance==null){
            instance = new ThreadSafeSingleton();
        }
        return instance;
    }

    public void show() {
        System.out.println("ThreadSafeSingleton instance: " + this.hashCode());
    }
}

//Bill Pugh Singleton pattern is the recomended pattern

class BillPughSingleton {

    // Step 1: Private constructor
    private BillPughSingleton() {
        System.out.println("Instance created!");
    }

    // Step 2: Static inner class (loaded only when getInstance is called)
    private static class Helper {
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }

    // Step 3: Global access point
    public static BillPughSingleton getInstance() {
        return Helper.INSTANCE;
    }

    public void show() {
        System.out.println("BillPughSingleton instance hash: " + this.hashCode());
    }
}




public class SingletonPattern {

    public static void main(String[] args) {
        
    }
    
}
