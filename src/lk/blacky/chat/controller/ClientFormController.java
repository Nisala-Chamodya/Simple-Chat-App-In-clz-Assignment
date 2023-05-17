package lk.blacky.chat.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientFormController {

    public TextArea ClientTxtArea;
    public TextField clientTxtField;

    ServerSocket serverSocket;
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    String message=" ";

    public void initialize(){
        new Thread(()->{
            try {
                socket=new Socket("localhost",3000);
                dataInputStream=new DataInputStream(socket.getInputStream());
                dataOutputStream=new DataOutputStream(socket.getOutputStream());

                while (!message.equals("Finish")){
                    message=dataInputStream.readUTF();
                    ClientTxtArea.appendText("\n server : "+message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }



    public void clientSendBtnOnAction(ActionEvent actionEvent) throws IOException {
        dataOutputStream.writeUTF(clientTxtField.getText().trim());
        dataOutputStream.flush();
    }
}
