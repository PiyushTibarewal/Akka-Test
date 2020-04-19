import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;

class EchoThread extends Thread {
    protected Socket socket;
    String OUTPUT = "<html><head><title>Example</title></head><body><p>Worked!!!</p></body></html>";
    String OUTPUT_HEADERS = "HTTP/1.1 200 OK\r\n" + "Content-Type: text/html\r\n" + "Content-Length: ";
    String OUTPUT_END_OF_HEADERS = "\r\n\r\n";

    public EchoThread(Socket clientSocket) {
        this.socket = clientSocket;
    }
    // static int b=0;
    // double fun(double a){
    //     for (int i = 0; i < 100; i++) {
    //         a = Math.random()+a;
    //     }
    //     return a; 
    // }
    public void testWait(){
        final long INTERVAL = 1000000000;
        long start = System.nanoTime();
        long end=0;
        do{
            end = System.nanoTime();
        }while(start + INTERVAL >= end);
        // System.out.println("endF - start");
        // System.out.println(end - start);
    }
    
    public void run() {
        try {
            // long start = System.nanoTime();

            // InputStreamReader isr = new InputStreamReader(socket.getInputStream());
            // BufferedReader reader = new BufferedReader(isr);
            // String line = reader.readLine();
            // while (!line.isEmpty()) {
            //     System.out.println(line);
            //     line = reader.readLine();
            // }
            // long end1 = System.nanoTime();
            // System.out.println("end1 - start");
            // System.out.println(end1 - start);

            // // Date date = new Date();
            // // // Pattern for showing milliseconds in the time "SSS"
            // // DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            // // String stringDate = sdf.format(date);
            // // // System.out.println(stringDate);
            // // // System.out.println("stringDate");
            // // // Thread.sleep(1000);
            // // // double rand = Math.random(); 
            // // // double x=fun(rand+ b);
            // // // System.out.println(x);
            // // b = b+2;

            // long end2 = System.nanoTime();
            // System.out.println("end2 - start");
            // System.out.println(end2 - start);

            testWait();

            // long end3 = System.nanoTime();
            // System.out.println("end3 - start");
            // System.out.println(end3 - start);

            // // System.out.println("stringDate1");
            // // OutputStream output = clientSocket.getOutputStream();
            // // PrintWriter writer = new PrintWriter(output, true);
            // // writer.println("OK");
            // Date date1 = new Date();
            // // Pattern for showing milliseconds in the time "SSS"
            // DateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            // String stringDate1 = sdf1.format(date1);
            // // System.out.println(stringDate1);

            // long end4 = System.nanoTime();
            // System.out.println("end4 - start");
            // System.out.println(end4 - start);

            BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(new BufferedOutputStream(socket.getOutputStream())));
            out.write(OUTPUT_HEADERS + OUTPUT.length() + OUTPUT_END_OF_HEADERS + OUTPUT);
            out.flush();
            // out.close();
            // long end = System.nanoTime();
            // System.out.println("end5 - start");
            // System.out.println(end - start);
            
        } catch (IOException e) {
            System.out.println("I/O error: " + e);
        }
        // catch(InterruptedException e) {
        // System.out.println("Interrupted error: " + e);
        // }
    }
}

public class SimpleHTTPServer {

    public static void main(String args[]) throws IOException {

        int MAX_T = 8; //Size of max-pool
        ExecutorService pool = Executors.newFixedThreadPool(MAX_T);

        ServerSocket server = new ServerSocket(8040);
        System.out.println("Listening for connection on port 8040 ....");
        Socket socket = null;
        Runnable r = null; 
        while (true) {

            try {
                socket = server.accept();
                // if(b){
                //     b=false;
                //     long start = System.nanoTime();
                // }
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
            // new thread for a client
            r = new EchoThread(socket);

            pool.execute(r);
        }

    }
}
