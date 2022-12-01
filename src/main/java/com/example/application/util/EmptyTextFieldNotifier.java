package com.example.application.util;

import com.vaadin.flow.component.textfield.TextField;

import java.util.List;

public class EmptyTextFieldNotifier {

    public static boolean checkAllTextFields(List<TextField> textFieldList) {
        boolean hasEmptyComponent = false;
        for (TextField textField : textFieldList) {
            if (textField.isEmpty()) {
                hasEmptyComponent = true;
            }
        }
        return hasEmptyComponent;
    }
}
