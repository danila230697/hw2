import ru.pflb.mq.dummy.exception.DummyException;
import ru.pflb.mq.dummy.implementation.ConnectionImpl;
import ru.pflb.mq.dummy.implementation.DestinationImpl;
import ru.pflb.mq.dummy.implementation.ProducerImpl;
import ru.pflb.mq.dummy.implementation.SessionImpl;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
// Import the Scanner class to read text files
import java.util.concurrent.TimeUnit;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
public class hw_2 {

    //Ну вроде как задача вполнена
    public static void main(String[] args) throws DummyException, InterruptedException {
        //Различные траи )))
 /*       List<String> messageList = Arrays.asList("Раз", "Два", "Три");
        ConnectionImpl connection = new ConnectionImpl();
        SessionImpl session = (SessionImpl) connection.createSession(true);
        DestinationImpl destination = (DestinationImpl) session.createDestination("True");
        destination.getQueueName();
        ProducerImpl producer = new ProducerImpl(destination);
        Iterator<String> iter = messageList.iterator();
        //Решил посомтреть а вообще как это работает, прикольно
        producer.send("РАЗ");
        producer.send("ДВА");
        producer.send("ТРИ");
        while (iter.hasNext()) {
            producer.send(iter.next());
            TimeUnit.SECONDS.sleep(2);
        }

  */
 /*       try {
            File myObj = new File("D:\\hw2\\massege.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            File myObj = new File("D:\\hw2\\massege.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                //String data = myReader.nextLine();
                producer.send(myReader.next());
                TimeUnit.SECONDS.sleep(2);
              //  System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

       // session.close();
        //connection.close();
*/

        FileInputStream file_IN;
        try {
            file_IN = new FileInputStream(args[0]);
        }
        catch(FileNotFoundException e) {
            System.out.println("File Not Found!");
            return;
        }

        Scanner scanIn = new Scanner(file_IN);
        Scanner scanner = new Scanner(System.in);
        List<String> messageList = new ArrayList<String>();
        while (scanIn.hasNextLine()) {
            String line = scanIn.nextLine();
            messageList.add(line);
        }
        Iterator<String> iter = messageList.iterator();
        ConnectionImpl connection = new ConnectionImpl();
        SessionImpl session = (SessionImpl) connection.createSession(true);
        DestinationImpl destination = (DestinationImpl) session.createDestination("TRUE");
        ProducerImpl producer = new ProducerImpl(destination);

        do {
            for (int i = 0; i < messageList.size(); i++) {
                producer.send(messageList.get(i));
                TimeUnit.SECONDS.sleep(2);
            }
            System.out.println("while 1 go next. Else GG WP");
        }
        while (scanner.nextLine().equals("1"));
        session.close();
        connection.close();


    }
}
