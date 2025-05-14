package Thread;

class Bank{
    private int balance=1000;

    synchronized void withdraw(int ammount){
        if(balance>=ammount){
            System.out.println("Withdrawing ammount : "+ammount);
            balance-=ammount;
        }
        else{
            System.out.println("Insufficient balance");
        }
    }
}

public class BankWithdrawal {

    public static void main(String[] args) {
        
        Bank bank = new Bank();
    
        Thread t1 = new Thread(() ->bank.withdraw(800));
        Thread t2 = new Thread(() ->bank.withdraw(300));
    
        t1.start();
        t2.start();
    }

    
}
