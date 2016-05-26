package tcpserver;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class TCPServer extends Application implements Runnable
{
    public static void main(String argv[]) throws Exception
    {
        launch(argv);  	
    } 

    @Override
    public void start(Stage primaryStage) throws Exception {
         Parent root = FXMLLoader.load(getClass().getResource("serverGUI.fxml"));
        
        Scene scene = new Scene(root);
       
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void run() {
        
        int workerThreadCount = 0;
        int id = 1;
        ServerSocket welcomeSocket = null;
        try {
            welcomeSocket = new ServerSocket(12380);
        } catch (IOException ex) {
            Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(true)
        {
            Socket connectionSocket = null;
            try {
                connectionSocket = welcomeSocket.accept();
            } catch (IOException ex) {
                Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            WorkerThread wt = new WorkerThread(connectionSocket,id);
            Thread t = new Thread(wt);
            t.start();
            workerThreadCount++;
            System.out.println("Client [" + id + "] is now connected. No. of worker threads = " + workerThreadCount);
            id++;
        }
        
    }
    
    public void serverShowMessage(TextArea area)
    {
        area.appendText("Server started successfully!!\n");
    }
}
 class WorkerThread implements Runnable
{
    private Socket connectionSocket;
    private int id;
    public WorkerThread(Socket ConnectionSocket, int id) 
    {
        this.connectionSocket=ConnectionSocket;
        this.id=id;
    }
    public void run()
    {
        String clientSentence;
        String capitalizedSentence;
        while(true)
        {
            try
            {
                DataOutputStream outToServer = new DataOutputStream(connectionSocket.getOutputStream());
                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));    
                clientSentence = inFromServer.readLine();
                capitalizedSentence = clientSentence.toUpperCase();
                outToServer.writeBytes(capitalizedSentence + '\n');
                
            }
            catch(Exception e)
            {
                
            }
        }
    }
}