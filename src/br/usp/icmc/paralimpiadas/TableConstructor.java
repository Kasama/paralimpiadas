package br.usp.icmc.paralimpiadas;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TableConstructor {
	public static TableView build(ResultSet rs){
		TableView<ObservableList> tbl = new TableView<>();
		ObservableList<ObservableList> data = FXCollections.observableArrayList();
		try {
			for(int i = 0 ; i < rs.getMetaData().getColumnCount(); i++){
				//We are using non property style for making dynamic table
				final int j = i;
				TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
				col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){
					public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
						return new SimpleStringProperty(param.getValue().get(j).toString());
					}
				});
				tbl.getColumns().addAll(col);
			}

			while(rs.next()){
				ObservableList<String> row = FXCollections.observableArrayList();
				for(int i = 1 ; i <= rs.getMetaData().getColumnCount(); i++){
					//Iterate Column
					row.add(rs.getString(i));
				}
				data.add(row);
			}

			tbl.setItems(data);

		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Error building table");
		}
		return tbl;
	}
}
