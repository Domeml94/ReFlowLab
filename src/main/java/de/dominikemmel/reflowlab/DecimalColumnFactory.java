package de.dominikemmel.reflowlab;

import java.text.DecimalFormat;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class DecimalColumnFactory<S, T extends Number> implements Callback<TableColumn<S, T>, TableCell<S, T>> {

    private DecimalFormat format;

    public DecimalColumnFactory(DecimalFormat format) {
        super();
        this.format = format;
    }

    @Override
    public TableCell<S, T> call(TableColumn<S, T> param) {
        return new TableCell<S, T>() {

            @Override
            protected void updateItem(T item, boolean empty) {
                if (!empty && item != null) {
                    setText(format.format(item.doubleValue()));
                } else {
                    setText("");
                }
            }
        };
    }
}
