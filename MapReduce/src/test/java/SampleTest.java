import mr.Coordinator;
import mr.Worker;
import java.io.File;

public class SampleTest {

    public static void main(String[] args) throws Exception{
        String input_folderPath = "D:\\product\\Redit\\Redit-push\\Redit-Benchmark\\MapReduce\\src\\main\\java\\files";
        File file = new File(input_folderPath);
        File[] files = file.listFiles();
        Coordinator coordinator = new Coordinator(files, 10, 12000);
        System.out.println("SampleTest : create coordinator success !!!");

        new Thread( () ->  {
            try {
                coordinator.start();
                while (!coordinator.isDone()){
                    Thread.sleep(2000);
                    System.out.println("SampleTest loop listen: " + "all tasks are not done");
                }
                System.out.println("SampleTest: " + "all tasks are done, coordinator exiting...");
            } catch (Exception e){
                e.printStackTrace();
            }
        } ).start();

        Thread.sleep(5000);
        Worker worker = new Worker();
        worker.CallExample();
        coordinator.allDone = true;
        Thread.sleep(3000);
    }

}
