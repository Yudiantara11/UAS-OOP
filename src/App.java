import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.css.converter.InsetsConverter;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
    Stage windowStage;
    TableView<motor> table;
    TableView<motor> tableView = new TableView<motor>();
    TextField idInput, merk_motorInput, nama_motorInput, warnaInput, hargaInput, kode_motorInput;

    @Override
    public void start(Stage stage) {

        // Menampilkan nama window
        windowStage = stage;
        windowStage.setTitle("DataBase - Motor");
       
        //Menampilkan tabel
        TableColumn<motor, String> columnID = new TableColumn<>("ID");
        columnID.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<motor, String> columnMerk_Motor = new TableColumn<>("Merk Motor");
        columnMerk_Motor.setCellValueFactory(new PropertyValueFactory<>("merk_motor"));

        TableColumn<motor, String> columnNama_Motor = new TableColumn<>("Nama Motor");
        columnNama_Motor.setCellValueFactory(new PropertyValueFactory<>("nama_motor"));

        TableColumn<motor, String> columnWarna = new TableColumn<>("Warna");
        columnWarna.setCellValueFactory(new PropertyValueFactory<>("warna"));

        TableColumn<motor, String> columnHarga = new TableColumn<>("Harga");
        columnHarga.setCellValueFactory(new PropertyValueFactory<>("harga"));

        TableColumn<motor, String> columnKode_Motor = new TableColumn<>("Kode Motor");
        columnKode_Motor.setCellValueFactory(new PropertyValueFactory<>("kode_motor"));
        
        tableView.getColumns().add(columnID);
        tableView.getColumns().add(columnMerk_Motor);
        tableView.getColumns().add(columnNama_Motor);
        tableView.getColumns().add(columnWarna);
        tableView.getColumns().add(columnHarga);
        tableView.getColumns().add(columnKode_Motor);

        //Input id
        idInput = new TextField();
        idInput.setPromptText("id");
        idInput.setMinWidth(10);

        //Input nama barang
        merk_motorInput = new TextField();
        merk_motorInput.setPromptText("Merk Motor");
        merk_motorInput.setMinWidth(20);

        //Input merk barang
        nama_motorInput = new TextField();
        nama_motorInput.setPromptText("Nama Motor");
        nama_motorInput.setMinWidth(20);

        //Input seri barang
        warnaInput = new TextField();
        warnaInput.setPromptText("Warna");
        warnaInput.setMinWidth(20);

        //Input harga
        hargaInput = new TextField();
        hargaInput.setPromptText("Harga");
        hargaInput.setMinWidth(20);

        //Input harga
        kode_motorInput = new TextField();
        kode_motorInput.setPromptText("Kode Motor");
        kode_motorInput.setMinWidth(20);

        //Button
        Button editButton = new Button("Update");
        editButton.setOnAction(e -> editButtonClicked());

        Button updateButton = new Button("Insert");
        updateButton.setOnAction(e -> updateButtonClicked());

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> deleteButtonClicked());

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(idInput, merk_motorInput, nama_motorInput, warnaInput, hargaInput, kode_motorInput, editButton, updateButton, deleteButton);

        String url = "jdbc:mysql://localhost:3306/db_motor";
        String user = "root";
        String pass = "";

        try {
            Connection conn = DriverManager.getConnection(url, user, pass);
            Statement stmt = conn.createStatement();
            ResultSet record = stmt.executeQuery("select*from tb_motor");

            while (record.next()) {
                tableView.getItems().add(new motor(record.getInt("id"), record.getString("merk_motor"), record.getString("nama_motor"), record.getString("warna"), record.getString("harga"), record.getString("kode_motor")));
            }
        }
        catch (SQLException e) {
            System.out.print("koneksi gagal");
        }
        

        VBox vbox = new VBox(tableView);
        vbox.getChildren().addAll(hBox);

        Scene scene = new Scene(vbox);

        stage.setScene(scene);
        stage.show();

    }




    //Update Button Clicked
    private void updateButtonClicked(){

        Database db = new Database();
                try {
                    Statement state = db.conn.createStatement();
                    String sql = "insert into tb_motor SET merk_motor='%s', nama_motor='%s', warna='%s', harga='%s', kode_motor='%s'";
                    sql = String.format(sql, merk_motorInput.getText(), nama_motorInput.getText(), warnaInput.getText(), hargaInput.getText(), kode_motorInput.getText());
                    state.execute(sql);
                    // idInput.clear();
                    merk_motorInput.clear();
                    nama_motorInput.clear();
                    warnaInput.clear();
                    hargaInput.clear();
                    kode_motorInput.clear();
                    loadData();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            
        };


    //Edit Button Clicked
    private void editButtonClicked(){

        Database db = new Database();
        try {
            Statement state = db.conn.createStatement();
            String sql = "update tb_motor set merk_motor = '%s' , nama_motor = '%s', warna = '%s', harga = '%s' WHERE kode_motor ='%s'";
            sql = String.format(sql, merk_motorInput.getText(), nama_motorInput.getText(), warnaInput.getText(), hargaInput.getText(), kode_motorInput.getText());
            state.execute(sql);
            merk_motorInput.clear();
            nama_motorInput.clear();
            warnaInput.clear();
            hargaInput.clear();
            kode_motorInput.clear();
            loadData();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //Delete button Clicked
    private void deleteButtonClicked(){

        Database db = new Database();
        try{
            Statement state = db.conn.createStatement();
            String sql = "delete from tb_motor where kode_motor ='%s';";
            sql = String.format(sql, kode_motorInput.getText());
            state.execute(sql);
            kode_motorInput.clear();
            loadData();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    

    public static void main(String[] args) {
        launch();
    }

    private void loadData() {
        Statement stmt;
        try {
            Database db = new Database();
            stmt = db.conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from tb_motor");
            tableView.getItems().clear();
            while(rs.next()){
                tableView.getItems().add(new motor(rs.getInt("id"), rs.getString("merk_motor"), rs.getString("nama_motor"), rs.getString("warna"), rs.getString("harga"), rs.getString("kode_motor")));
            }
            
            stmt.close();
            db.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    

}