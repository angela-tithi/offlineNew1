/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpserver;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import tcpclient.TCPClient;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ServerGUIController implements Initializable {

    @FXML
    private TextArea serverStuffs;
    
    @FXML
    private Button Start ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        
        TCPServer server = new TCPServer() ;
        Thread thread = new Thread(server) ;
        thread.start();
        server.serverShowMessage(serverStuffs);
       
    }
    
}
