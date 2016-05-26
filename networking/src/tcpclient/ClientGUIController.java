/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpclient;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ClientGUIController implements Initializable {
    
        String sentence;
        String modifiedSentence;
        BufferedReader inFromUser;
        Socket clientSocket ;
        DataOutputStream outToServer ;
        BufferedReader inFromServer ;
    
    @FXML
    public Button Send;
    @FXML
    public TextArea messageArea;
    @FXML
    public TextField messageField;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        inFromUser = new BufferedReader(new InputStreamReader(System.in));
            try {
                clientSocket = new Socket("localhost", 6789);
            } catch (IOException ex) {
                Logger.getLogger(ClientGUIController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                outToServer = new DataOutputStream(clientSocket.getOutputStream());
            } catch (IOException ex) {
                Logger.getLogger(ClientGUIController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            } catch (IOException ex) {
                Logger.getLogger(ClientGUIController.class.getName()).log(Level.SEVERE, null, ex);
            }
       
    }
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
          
        sentence = inFromUser.readLine();
        outToServer.writeBytes(sentence + '\n');
        modifiedSentence = inFromServer.readLine();
        messageArea.appendText(modifiedSentence);
        System.out.println("From Server : "+modifiedSentence);
        
    }
    
    
    
}
