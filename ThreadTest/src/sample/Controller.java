package sample;

import javafx.event.ActionEvent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Controller {

    public void thread1(ActionEvent actionEvent) {
        ArrayClass sc = new ArrayClass(20);
        SampleThread s1 = new SampleThread(sc,1);


        SampleThread s12= new SampleThread(sc,10);



        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(s1);
        executorService.execute(s12);
        executorService.shutdown();
    }

    public void thread2(ActionEvent actionEvent) {
    }
}
