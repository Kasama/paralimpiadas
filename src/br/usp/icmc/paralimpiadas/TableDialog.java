package br.usp.icmc.paralimpiadas;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableView;

public class TableDialog extends Dialog {

	public TableDialog(TableView content) {
		ButtonType buttonCancel = new ButtonType (
			"Fechar", ButtonBar.ButtonData.CANCEL_CLOSE
		);

		this.setTitle("Visualização");
		this.getDialogPane().setContent(content);
		this.getDialogPane().getButtonTypes().addAll(buttonCancel);
	}
}
