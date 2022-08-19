package client;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ChatRoomBoxController extends Thread implements Initializable {

    public Pane chat;
    public Circle showProPic;
    public Pane emojiPane;
    public ImageView txtClientMsg;
    @FXML
    private Label clientName;


    @FXML
    private TextArea msgRoom;

    @FXML
    private TextField msgField;



    private FileChooser fileChooser;
    private File filePath;


    BufferedReader reader;
    PrintWriter writer;
    Socket socket;


    public void initialize(){
        emojiPane.setVisible(false);
    }



    public void connectSocket() {
        try {
            socket = new Socket("localhost", 5000);
            System.out.println("Socket is connected with server!");
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            this.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        try {
            while (true) {
                String msg = reader.readLine();
                String[] tokens = msg.split(" ");
                String cmd = tokens[0];
                System.out.println(cmd);
                StringBuilder fulmsg = new StringBuilder();
                for(int i = 1; i < tokens.length; i++) {
                    fulmsg.append(tokens[i]);
                }
                System.out.println(fulmsg);
                if (cmd.equalsIgnoreCase(LoginFormController.username + ":")) {
                    continue;
                } else if(fulmsg.toString().equalsIgnoreCase("bye")) {
                    break;
                }
                msgRoom.appendText(msg + "\n");
            }
            reader.close();
            writer.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void send() {
        String msg = msgField.getText();
        writer.println(LoginFormController.username + ": " + msg);
        msgRoom.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        msgRoom.appendText("Me: " + msg + "\n");
        msgField.setText("");
        if(msg.equalsIgnoreCase("BYE") || (msg.equalsIgnoreCase("logout"))) {
            System.exit(0);
        }
    }
    public boolean saveControl = false;

    public void sendMessageByKey(KeyEvent event) {
        if (event.getCode().toString().equals("ENTER")) {
            send();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        clientName.setText(LoginFormController.username);
        connectSocket();
    }

    public void handleSendEvent(javafx.scene.input.MouseEvent mouseEvent) {
        send();
        for(User user : LoginFormController.users) {
            System.out.println(user.name);
        }

    }


    public void selectImage(javafx.scene.input.MouseEvent mouseEvent) throws IOException {



    }

    public void emojiOnClick(MouseEvent mouseEvent) {
        if (!emojiPane.isVisible()) {
            emojiPane.setVisible(true);
        } else {
            emojiPane.setVisible(false);
        }
    }

    public void emoji1OnCLicked(MouseEvent mouseEvent) {
        msgField.appendText("\uD83D\uDE42");
    }

    public void emoji2OnCLicked(MouseEvent mouseEvent) {
        msgField.appendText("\uD83D\uDE02");
    }

    public void emoji3OnCLicked(MouseEvent mouseEvent) {
        msgField.appendText("\uD83D\uDE34");
    }

    public void emoji4OnCLicked(MouseEvent mouseEvent) {
        msgField.appendText("\uD83D\uDC97");
    }

    public void emoji5OnCLicked(MouseEvent mouseEvent) {
        msgField.appendText("\uD83C\uDF3C");
    }

    public void emoji6OnCLicked(MouseEvent mouseEvent) {
        msgField.appendText("\uD83C\uDF3F");
    }

    public void emoji7OnCLicked(MouseEvent mouseEvent) {
        msgField.appendText("\uD83C\uDF82");
    }

    public void emoji8OnCLicked(MouseEvent mouseEvent) {
        msgField.appendText("\uD83D\uDE0E");
    }

    public void emoji9OnCLicked(MouseEvent mouseEvent) {
        msgField.appendText("\uD83C\uDF6B");
    }

    public void emoji10OnCLicked(MouseEvent mouseEvent) {
        msgField.appendText("\uD83D\uDE0D");
    }

    public void emoji11OnCLicked(MouseEvent mouseEvent) {
        msgField.appendText("\uD83E\uDDC1");
    }

    public void emoji12OnCLicked(MouseEvent mouseEvent) {
        msgField.appendText("\uD83D\uDC24");
    }

    public void emoji13OnCLicked(MouseEvent mouseEvent) {
        msgField.appendText("\uD83D\uDC1F");
    }







    public void imgOnAction(MouseEvent mouseEvent) {
//        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
//        chooser = new FileChooser();
//        chooser.setTitle("Open Image");
//        this.path = chooser.showOpenDialog(stage);
//        printWriter.println(clientlbl.getText() + " " + "img" + path.getPath());
//        printWriter.flush();
    }
}
