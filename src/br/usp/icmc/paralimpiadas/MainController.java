package br.usp.icmc.paralimpiadas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

public class MainController {

    @FXML
    ComboBox<Map.Entry> querySelector;
	@FXML
	Button button;

    ObservableList<Map<Object, Object>> data;
	Database db;

    public void setup(Properties queries) {
		try {
			this.db = new Database("database.properties");
		} catch (IOException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		this.data = FXCollections.observableArrayList(queries);
		querySelector.getItems().addAll(queries.entrySet());
		querySelector.setCellFactory((e)-> new ListCell<Map.Entry>() {
			@Override
			protected void updateItem(Map.Entry item, boolean empty){
				super.updateItem(item, empty);
				if (item != null){
					setText(normalize(item.getKey()));
				}
			}
		});
        querySelector.getSelectionModel().selectFirst();
        querySelector.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, previous, selected) -> {
					if (selected != null)
						System.out.println("selected: " + selected.getValue());
				}
		);
    }

	@FXML
	private void doit(Event e){
		String sql = querySelector.
			getSelectionModel().getSelectedItem().getValue().toString();
		String[] arr = {"endereco", "nice"};
		sql = String.format(sql, arr);
		ResultSet rs = db.executeQuery(sql);
		new TableDialog(TableConstructor.build(rs)).show();
	}

	private String normalize(Object obj){
		String ret = obj.toString().replace('_', ' ');
		return ret;
	}
}
