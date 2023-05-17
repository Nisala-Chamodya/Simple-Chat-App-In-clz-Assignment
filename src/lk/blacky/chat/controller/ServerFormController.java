package lk.blacky.chat.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerFormController {
    public TextArea serverTxtArea;
    public TextField serverTxtField;
    /*Server*/
    ServerSocket serverSocket;
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    String message=" ";
    public void initialize(){
        new Thread(()->{
            try {
                serverSocket=new ServerSocket(3000);
                serverTxtArea.appendText("Server Started!");
                socket = serverSocket.accept();
                serverTxtArea.appendText("\n Client Accepted");
                dataInputStream=new DataInputStream(socket.getInputStream());
                dataOutputStream=new DataOutputStream(socket.getOutputStream());
                while (!message.equals("Finish")){
                    message=dataInputStream.readUTF();
                    serverTxtArea.appendText("\n client : "+message);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();

    }

    public void serverSendBtnOnAction(ActionEvent actionEvent) throws IOException {
        dataOutputStream.writeUTF(serverTxtField.getText().trim());
        dataOutputStream.flush();

    }
}
