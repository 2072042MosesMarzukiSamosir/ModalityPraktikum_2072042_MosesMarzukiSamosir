package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Supplier;

public class SecondController {
    public TextField txtID;
    public TextField txtNamaSup;
    public TableView tableView;
    public TextField txtAlamat;
    public TableColumn columnIDSup;
    public TableColumn columnNamaSup;
    public TableColumn columnAlamatSup;
    public Button btnUpdate;
    public Button btnSave;

    private Supplier selSuplier;

    FirstController fs;
    public ObservableList<Supplier> oListSupplier;

    public void initialize(){
         oListSupplier = FXCollections.observableArrayList(
                new Supplier("1", "ACER", "Jakarta")
        );
        tableView.setItems(oListSupplier);
        columnIDSup.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnNamaSup.setCellValueFactory(new PropertyValueFactory<>("nama"));
        columnAlamatSup.setCellValueFactory(new PropertyValueFactory<>("alamat"));
    }

    public void onSaveSup(ActionEvent event) {
        Supplier supplier = new Supplier(txtID.getText(), txtNamaSup.getText(), txtAlamat.getText());
        tableView.getItems().add(supplier);
    }

    public void onResetSup() {
        txtID.clear();
        txtNamaSup.clear();
        txtAlamat.clear();
        btnSave.setDisable(false);
    }

    public void onUpdateSup(ActionEvent event) {
        selSuplier.setId(txtID.getText());
        selSuplier.setNama(txtNamaSup.getText());
        selSuplier.setAlamat(txtAlamat.getText());
        onResetSup();
        oListSupplier.set(tableView.getSelectionModel().getFocusedIndex(), selSuplier);
        btnUpdate.setDisable(false);
        btnSave.setDisable(false);
    }

    public void onClose(ActionEvent event) {
        tableView.getScene().getWindow().hide();
    }

    public void onTableClicked(MouseEvent event) {
        selSuplier = (Supplier) tableView.getSelectionModel().getSelectedItem();
        if (tableView.getSelectionModel().getSelectedCells().isEmpty()) {
            btnUpdate.setDisable(true);
        }
        if (selSuplier != null){
            txtID.setText(selSuplier.getId());
            txtNamaSup.setText(selSuplier.getNama());
            txtAlamat.setText(selSuplier.getAlamat());
            btnSave.setDisable(true);
        }
    }
}
