package com.example.application.views.main;

import com.example.application.dal.entity.Employee;
import com.example.application.exception.EmployeeAlreadyExistsException;
import com.example.application.exception.EmployeeDoesNotExistException;
import com.example.application.service.EmployeeService;
import com.example.application.service.EmployeeServiceImpl;
import com.example.application.util.EmptyTextFieldNotifier;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@PageTitle("Main")
@Route(value = "")
public class MainView extends HorizontalLayout {

    @Autowired
    private EmployeeService employeeService;

    private final TextField name;
    private final TextField surname;
    private final TextField email;

    public MainView() {

        name = new TextField("Name");
        surname = new TextField("Surname");
        email = new TextField("Email");
        Button submit = new Button("Add employee");
        List<TextField> textFieldList = new ArrayList<>();
        textFieldList.add(name);
        textFieldList.add(surname);
        textFieldList.add(email);
        submit.addClickShortcut(Key.ENTER);
        setMargin(true);
        setVerticalComponentAlignment(Alignment.END, name, surname, email, submit);
        add(name, surname, email, submit);

        submit.addClickListener(e -> {

            String text;
            if(EmptyTextFieldNotifier.checkAllTextFields(textFieldList)){
                text = "Please fill all fields";
            } else {
                Employee employee = Employee.generateEmployee(name.getValue(), surname.getValue(), email.getValue());
                try {
                    employeeService.saveEmployee(employee);
                    text = "Hello " + employee.toString();
                } catch (EmployeeDoesNotExistException exception) {
                    text = "Oops";
                } catch (EmployeeAlreadyExistsException exception) {
                    text = exception.getMessage();
                }
            }

            Notification.show(text);
        });
    }
}
