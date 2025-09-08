package com.studentregistration.controller;

import com.studentregistration.dao.StudentDao;
import com.studentregistration.model.Student;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.beans.property.SimpleStringProperty;

public class StudentController {

    @FXML private TextField regNoField, nameField, emailField;
    @FXML private ComboBox<String> courseBox;
    @FXML private DatePicker dobPicker;
    @FXML private RadioButton maleRadio, femaleRadio;
    @FXML private TableView<Student> studentTable;
    @FXML private TableColumn<Student,String> regNoColumn, nameColumn, emailColumn, courseColumn, dobColumn, genderColumn;

    private final StudentDao dao = new StudentDao();

    @FXML
    private void initialize() {
        // Table bindings
        regNoColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getRegNo()));
        nameColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getName()));
        emailColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getEmail()));
        courseColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getCourse()));
        dobColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getDob()));
        genderColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getGender()));

        courseBox.setItems(FXCollections.observableArrayList("IT","Business","Engineering"));
        refreshTable();
    }

    @FXML
    private void handleSave() {
        try {
            String gender = maleRadio.isSelected() ? "Male" : "Female";
            String dob = (dobPicker.getValue() == null) ? "" : dobPicker.getValue().toString();
            Student s = new Student(
                    regNoField.getText(), nameField.getText(), emailField.getText(),
                    courseBox.getValue(), dob, gender
            );
            dao.insert(s);
            clearForm();
            refreshTable();
        } catch (Exception e) {
            showError("Save failed", e.getMessage());
        }
    }

    @FXML private void handleClear() { clearForm(); }

    @FXML
    private void handleDeleteSelected() {
        Student sel = studentTable.getSelectionModel().getSelectedItem();
        if (sel == null) { showError("No selection", "Select a row to delete."); return; }
        try { dao.deleteByRegNo(sel.getRegNo()); refreshTable(); }
        catch (Exception e) { showError("Delete failed", e.getMessage()); }
    }

    private void refreshTable() {
        try { studentTable.setItems(FXCollections.observableArrayList(dao.findAll())); }
        catch (Exception e) { showError("Load failed", e.getMessage()); }
    }

    private void clearForm() {
        regNoField.clear(); nameField.clear(); emailField.clear();
        courseBox.setValue(null); dobPicker.setValue(null);
        maleRadio.setSelected(false); femaleRadio.setSelected(false);
    }

    private void showError(String head, String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setHeaderText(head); a.setContentText(msg); a.showAndWait();
    }
}
