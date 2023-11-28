/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FXMLDocuments;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 *
 * @author danie
 */

public class ComboBoxItemWrap<T> {
    //This whole class is to create the CheckComboBox which can be imported if we'd had used a maven project
    private BooleanProperty check = new SimpleBooleanProperty(false);
    private ObjectProperty<T> item = new SimpleObjectProperty<>();

    public ComboBoxItemWrap() {
    }
    
    public ComboBoxItemWrap(T item) {
        this.item.set(item);
    }
    
    public ComboBoxItemWrap(T item,Boolean check) {
        this.item.set(item);
        this.check.set(check);
    }
    
    public BooleanProperty checkProperty(){
        return check;
    }

    public BooleanProperty getCheck() {
        return check;
    }

    public void setCheck(BooleanProperty check) {
        this.check = check;
    }

    public ObjectProperty<T> getItem() {
        return item;
    }

    public void setItem(ObjectProperty<T> item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return item.getValue().toString();
    }
}
