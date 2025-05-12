package Thread;


class Filedownloader implements Runnable{
    @Override
    public  void run(){
        try {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Downloading file part " + i + "...");
                Thread.sleep(1000);  // Simulates time delay in downloading
            }
            System.out.println("Download complete!");
        } catch (InterruptedException e) {
            System.out.println("Download interrupted");
        }
    }
}

class MessagePrinter implements Runnable{
    @Override
    public void run(){
        try {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Reminder: Please wait while downloading...");
                Thread.sleep(700);  // Simulates message interval
            }
        } catch (InterruptedException e) {
            System.out.println("Message printing interrupted");
        }
    }
}



class DownloadSimulation{
    public static void main(String[] args) {
        System.err.println("Helloo");
        Thread downloadThread = new Thread(new Filedownloader());
        Thread messagePrinter = new Thread(new MessagePrinter());

        downloadThread.start();
        messagePrinter.start();


    }
}