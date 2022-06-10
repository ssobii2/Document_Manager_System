package hu.up.project.documentManagerSystem.view;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToLongConverter;
import com.vaadin.flow.router.Route;
import hu.up.project.documentManagerSystem.error.EntityNotFoundException;
import hu.up.project.documentManagerSystem.model.DocumentDTO;
import hu.up.project.documentManagerSystem.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Route
public class DocumentView extends VerticalLayout {
    @Autowired
    private DocumentService service;
    private VerticalLayout form = new VerticalLayout();
    private Binder<DocumentDTO> binder;
    private TextField name;
    private TextField description;
    private TextField parentId;
    private TextField url;
    private TextField type;
    private DocumentDTO selectedDto = new DocumentDTO();

    @PostConstruct
    public void init() {
        Grid<DocumentDTO> grid = new Grid<>();
        grid.setItems(service.findAll());
        grid.addColumn(DocumentDTO::getId).setHeader("ID");
        grid.addColumn(DocumentDTO::getName).setHeader("Name");
        grid.addColumn(DocumentDTO::getDescription).setHeader("Description");
        grid.addColumn(DocumentDTO::getUrl).setHeader("URL");
        grid.addColumn(DocumentDTO::getType).setHeader("Type");
        grid.addColumn(DocumentDTO::getParentId).setHeader("Parent ID");

        createButtonBarAndSelection(grid);
        add(grid);

        createForm(grid);
    }

    private void createForm(Grid<DocumentDTO> documentDTOGrid) {
        form.setVisible(false);
        binder = new Binder<>(DocumentDTO.class);
        name = new TextField();
        description = new TextField();
        parentId = new TextField();
        binder.forField(parentId).withNullRepresentation("").withConverter(new StringToLongConverter("It should be a number")).bind("parentId");
        url = new TextField();
        type = new TextField();

        form.add(new Text("Name"), name);
        form.add(new Text("Description"), description);
        form.add(new Text("Parent ID"), parentId);
        form.add(new Text("URL"), url);
        form.add(new Text("Type"), type);

        Button button = new Button();
        button.setText("Save");
        button.addClickListener(buttonClickEvent -> {
            try {
                if (selectedDto.getId() != null) {
                    service.update(selectedDto);
                } else {
                    service.save(selectedDto);

                }
                documentDTOGrid.setItems(service.findAll());
                selectedDto = new DocumentDTO();
                form.setVisible(false);
            } catch (EntityNotFoundException e) {
                e.printStackTrace();
                Notification.show("Delete error");
            }
        });
        binder.bindInstanceFields(this);
        binder.setBean(selectedDto);

        form.add(button);
        add(form);
    }

    private void createButtonBarAndSelection(Grid<DocumentDTO> grid) {
        HorizontalLayout layout = new HorizontalLayout();

        Button createButton = new Button();
        createButton.setText("Create");
        createButton.addClickListener(buttonClickEvent -> {
            selectedDto = new DocumentDTO();
            binder.setBean(selectedDto);
            form.setVisible(true);
        });
        layout.add(createButton);

        Button editButton = new Button();
        editButton.setText("Edit");
        editButton.addClickListener(buttonClickEvent -> {
            binder.setBean(selectedDto);
            form.setVisible(true);
        });
        editButton.setVisible(true);
        layout.add(editButton);

        Button deleteButton = new Button();
        deleteButton.setText("Delete");
        deleteButton.addClickListener(buttonClickEvent -> {
            try {
                service.delete(selectedDto.getId());
                grid.setItems(service.findAll());
            } catch (EntityNotFoundException e) {
                e.printStackTrace();
                Notification.show("Delete error");
            }
        });
        deleteButton.setVisible(true);
        layout.add(deleteButton);

        add(layout);

        grid.asSingleSelect().addValueChangeListener(event -> {
            selectedDto = event.getValue();
            deleteButton.setVisible(selectedDto != null);
            editButton.setVisible(selectedDto != null);
            binder.setBean(selectedDto);
        });
    }
}
