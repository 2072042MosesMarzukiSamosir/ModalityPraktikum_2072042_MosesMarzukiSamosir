package controller;

import com.example.modalityprak.Modality;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Barang;

import java.io.IOException;


public class FirstController {

    public Pane paneView;
    public TableView tableView;
    public TextField txtID;
    public TextField txtName;
    public ComboBox cboxSuplier;
    public TableColumn columnID;
    public TableColumn columnNama;
    public TableColumn columnSupplier;
    public ObservableList<Barang> oListBarang;
    public Button btnUpdate;
    public Button btnSave;

    SecondController sc;

    private Stage stage;
    private Barang selBarang;

    public void initialize() throws IOException {
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader(Modality.class.getResource("second.fxml"));
        Scene scene = new Scene(loader.load());

        stage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
        stage.setTitle("Add Supplier");
        stage.setScene(scene);

        sc = loader.getController();

        cboxSuplier.setItems(sc.oListSupplier);
        cboxSuplier.getSelectionModel();


        oListBarang = FXCollections.observableArrayList(
                new Barang("1", "Komputer(Dummy)", "ASUS(Dummy) ")
        );
        tableView.setItems(oListBarang);
        columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        columnSupplier.setCellValueFactory(new PropertyValueFactory<>("supplier"));
    }

    public void onReset() {
        txtID.clear();
        txtName.clear();
        cboxSuplier.getSelectionModel().select(null);
        btnSave.setDisable(false);
    }

    public void onSave(ActionEvent event) {
        Barang barang = new Barang(txtID.getText(), txtName.getText(), String.valueOf(cboxSuplier.getValue()));
        tableView.getItems().add(barang);
    }

    public void onUpdate(ActionEvent event) {
        selBarang.setId(txtID.getText());
        selBarang.setNama(txtName.getText());
        selBarang.setSupplier(String.valueOf(cboxSuplier.getValue()));;
        onReset();
        oListBarang.set(tableView.getSelectionModel().getFocusedIndex(), selBarang);
        btnUpdate.setDisable(false);
        btnSave.setDisable(false);
    }

    public void goToSupplierManagement(ActionEvent event) {
        stage.showAndWait();


    }

    public void onTableClicked(MouseEvent event) {
        selBarang = (Barang) tableView.getSelectionModel().getSelectedItem();
        if (tableView.getSelectionModel().getSelectedCells().isEmpty()) {
            btnUpdate.setDisable(true);
        }
        if (selBarang != null){
            txtID.setText(selBarang.getId());
            txtName.setText(selBarang.getNama());
            cboxSuplier.getSelectionModel().select(String.valueOf(selBarang.getSupplier()));
            btnSave.setDisable(true);

        }
    }

    public void onClose(ActionEvent event) {
        tableView.getScene().getWindow().hide();
    }
}