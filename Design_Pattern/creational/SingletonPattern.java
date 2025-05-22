package Design_Pattern.creational;

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


public class SingletonPattern {

    public static void main(String[] args) {
        
    }
    
}
