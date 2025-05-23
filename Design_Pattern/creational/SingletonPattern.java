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
        System.out.println("NOT THREAD SAFE :: LazySingleton instance: " + this.hashCode());
    }
}

// 3. Using syncronized : Thread Safe

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
        System.out.println("THREAD SAFE :: ThreadSafeSingleton instance using syncronize: " + this.hashCode());
    }
}

// 4.Bill Pugh Singleton pattern is the recomended pattern

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
        //EagerSingletone calling
        // EagerSingleton eagerSingleton1 = EagerSingleton.getInstance();
        // EagerSingleton eagerSingleton2 = EagerSingleton.getInstance();
        // eagerSingleton1.show();
        // eagerSingleton2.show();

        //Lazy Initialization (Not Thread Safe)
        // LazySingleton lazySingleton1 = LazySingleton.getInstance();
        // LazySingleton lazySingleton2 = LazySingleton.getInstance();
        // lazySingleton1.show();
        // lazySingleton2.show();

        //Using syncronized : Thread Safe
        // ThreadSafeSingleton threadSafeSingleton1 = ThreadSafeSingleton.getInstance();
        // ThreadSafeSingleton threadSafeSingleton2 = ThreadSafeSingleton.getInstance();

        // threadSafeSingleton1.show();
        // threadSafeSingleton2.show();

        //BILL PUGH SINGLETON
        BillPughSingleton billPughSingleton1 = BillPughSingleton.getInstance();
        BillPughSingleton billPughSingleton2 = BillPughSingleton.getInstance();

        billPughSingleton1.show();
        billPughSingleton2.show();





    }
    
}
