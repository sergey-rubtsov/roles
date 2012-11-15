package com.roles.assignment.ui.windows;

import com.vaadin.ui.*;

import java.util.Arrays;
import java.util.List;


public class Incoming extends Window {

    private AbsoluteLayout mainLayout;

    private AbsoluteLayout footer;

    private HorizontalLayout buttonsPanel;

    private NativeButton cancelButton;

    private NativeButton saveButton;

    private NativeButton printButton;

    private NativeButton editButton;

    private AbsoluteLayout body;

    private TabSheet basicTab;

    private HorizontalLayout extra;

    private VerticalLayout extraMain;

    private AbsoluteLayout operatorPanel;

    private Label operator;

    private GridLayout works;

    private Button groupOfWorkButton;

    private TextField groupWorkVal;

    private Label groupWork;

    private Button typeOfWorkButton;

    private TextField typeOfWorkVal;

    private Label typeOfWork;

    private HorizontalLayout priority;

    private OptionGroup prioritySelect;

    private HorizontalLayout payment;

    private OptionGroup paymentSelect;

    private GridLayout codes;

    private TextArea textArea_2;

    private TextArea textArea_1;

    private HorizontalLayout horizontalLayout_2;

    private Button button_2;

    private TextField textField_4;

    private Label label_3;

    private HorizontalLayout horizontalLayout_1;

    private Button button_1;

    private TextField textField_3;

    private Label label_1;

    private VerticalLayout verticalLayout_2;

    private Label numberOfCause;

    private Label statementNumberLabel;

    private HorizontalLayout basic;

    private VerticalLayout main;

    private GridLayout gridLayout_5;

    private Button button_4;

    private TextField executorVal;

    private Label executor;

    private Button button_3;

    private TextField textField_2;

    private Label registrator;

    private GridLayout gridLayout_3;

    private PopupDateField parentDocDate;

    private Label label_11;

    private TextField parentDocNumber;

    private Label label_10;

    private GridLayout gridLayout_2;

    private TextField textField_6;

    private Label label_8;

    private TextField textField_5;

    private Label label_7;

    private VerticalLayout verticalLayout_1;

    private HorizontalLayout organisation;

    private TextField textField_1;

    private Label label_4;

    private CheckBox lawCheck;

    private TextArea problemDescriptionVal;

    private Label problemDescription;

    private GridLayout gridLayout_1;

    private TextField contractorVal;

    private Label contractor;

    private TextField accountVal;

    private Label account;

    private HorizontalLayout address;

    private HorizontalLayout rightAddressPanel;

    private ComboBox roomValue;

    private Label room;

    private ComboBox buildingValue;

    private Label building;

    private HorizontalLayout leftAddressPanel;

    private ComboBox streetValue;

    private Label street;

    private HorizontalLayout horizontalLayout_3;

    private Button selectDocTypesButton;

    private TextField documentTypeVal;

    private Label documentType;

    private HorizontalLayout headDate;

    private PopupDateField dateField;

    private Label dataLabel;

    private HorizontalLayout title;

    private HorizontalLayout task;

    private Button helpTask;

    private Label titleTask;

    private Label titleType;

    public Incoming() {
        buildMainLayout();
        setContent(mainLayout);
        //setCompositionRoot(mainLayout);

        // TODO add user code here
    }


    private AbsoluteLayout buildMainLayout() {
        // common part: create layout
        mainLayout = new AbsoluteLayout();
        mainLayout.setImmediate(false);
        mainLayout.setWidth("100%");
        mainLayout.setHeight("100%");
        mainLayout.setMargin(false);

        // top-level component properties
        setWidth("100.0%");
        setHeight("100.0%");

        // body
        body = buildBody();
        mainLayout.addComponent(body,
                "top:10.0px;right:10.0px;bottom:70.0px;left:10.0px;");

        // footer
        footer = buildFooter();
        mainLayout.addComponent(footer,
                "right:10.0px;bottom:0.0px;left:10.0px;");

        return mainLayout;
    }


    private AbsoluteLayout buildBody() {
        // common part: create layout
        body = new AbsoluteLayout();
        body.setImmediate(false);
        body.setWidth("100.0%");
        body.setHeight("100.0%");
        body.setMargin(false);

        // title
        title = buildTitle();
        body.addComponent(title, "top:0.0px;left:0.0px;");

        // basicTab
        basicTab = buildBasicTab();
        body.addComponent(basicTab,
                "top:30.0px;right:40.0px;bottom:0.0px;left:40.0px;");

        return body;
    }


    private HorizontalLayout buildTitle() {
        // common part: create layout
        title = new HorizontalLayout();
        title.setImmediate(false);
        title.setWidth("100.0%");
        title.setHeight("30px");
        title.setMargin(false);

        // titleType
        titleType = new Label();
        titleType.setImmediate(false);
        titleType.setWidth("-1px");
        titleType.setHeight("-1px");
        titleType.setValue("Регистрационный номер: 880");
        title.addComponent(titleType);
        title.setComponentAlignment(titleType, new Alignment(33));

        // task
        task = buildTask();
        title.addComponent(task);
        title.setExpandRatio(task, 1.0f);
        title.setComponentAlignment(task, new Alignment(34));

        return title;
    }


    private HorizontalLayout buildTask() {
        // common part: create layout
        task = new HorizontalLayout();
        task.setImmediate(false);
        task.setWidth("-1px");
        task.setHeight("-1px");
        task.setMargin(false);

        // titleTask
        titleTask = new Label();
        titleTask.setImmediate(false);
        titleTask.setWidth("-1px");
        titleTask.setHeight("-1px");
        titleTask.setValue("Регистрация документа");
        task.addComponent(titleTask);
        task.setComponentAlignment(titleTask, new Alignment(33));

        // helpTask
        helpTask = new Button();
        helpTask.setCaption("?");
        helpTask.setImmediate(true);
        helpTask.setWidth("-1px");
        helpTask.setHeight("-1px");
        task.addComponent(helpTask);
        task.setComponentAlignment(helpTask, new Alignment(34));

        return task;
    }


    private TabSheet buildBasicTab() {
        // common part: create layout
        basicTab = new TabSheet();
        basicTab.setImmediate(true);
        basicTab.setWidth("100.0%");
        basicTab.setHeight("100.0%");

        // basic
        basic = buildBasic();
        basicTab.addTab(basic, "Основные реквизиты", null);

        // extra
        extra = buildExtra();
        basicTab.addTab(extra, "Дополнительные реквизиты", null);

        return basicTab;
    }


    private HorizontalLayout buildBasic() {
        // common part: create layout
        basic = new HorizontalLayout();
        basic.setStyleName("main");
        basic.setImmediate(false);
        basic.setWidth("100.0%");
        basic.setHeight("100.0%");
        basic.setMargin(false);

        // main
        main = buildMain();
        basic.addComponent(main);
        basic.setComponentAlignment(main, new Alignment(20));

        return basic;
    }


    private VerticalLayout buildMain() {
        // common part: create layout
        main = new VerticalLayout();
        main.setImmediate(false);
        main.setWidth("100.0%");
        main.setHeight("100.0%");
        main.setMargin(true);
        main.setSpacing(true);

        // headDate
        headDate = buildHeadDate();
        main.addComponent(headDate);
        main.setComponentAlignment(headDate, new Alignment(33));

        // horizontalLayout_3
        horizontalLayout_3 = buildHorizontalLayout_3();
        main.addComponent(horizontalLayout_3);

        // address
        address = buildAddress();
        main.addComponent(address);
        main.setComponentAlignment(address, new Alignment(33));

        // gridLayout_1
        gridLayout_1 = buildGridLayout_1();
        main.addComponent(gridLayout_1);

        // problemDescription
        problemDescription = new Label();
        problemDescription.setImmediate(false);
        problemDescription.setWidth("-1px");
        problemDescription.setHeight("-1px");
        problemDescription.setValue("Общее описание проблемы:");
        main.addComponent(problemDescription);

        // problemDescriptionVal
        problemDescriptionVal = new TextArea();
        problemDescriptionVal.setImmediate(false);
        problemDescriptionVal.setWidth("100.0%");
        problemDescriptionVal.setHeight("50px");
        main.addComponent(problemDescriptionVal);

        // verticalLayout_1
        verticalLayout_1 = buildVerticalLayout_1();
        main.addComponent(verticalLayout_1);

        // gridLayout_2
        gridLayout_2 = buildGridLayout_2();
        main.addComponent(gridLayout_2);

        // gridLayout_3
        gridLayout_3 = buildGridLayout_3();
        main.addComponent(gridLayout_3);

        // gridLayout_5
        gridLayout_5 = buildGridLayout_5();
        main.addComponent(gridLayout_5);

        return main;
    }


    private HorizontalLayout buildHeadDate() {
        // common part: create layout
        headDate = new HorizontalLayout();
        headDate.setImmediate(false);
        headDate.setWidth("100.0%");
        headDate.setHeight("-1px");
        headDate.setMargin(false);
        headDate.setSpacing(true);

        // dataLabel
        dataLabel = new Label();
        dataLabel.setImmediate(false);
        dataLabel.setWidth("-1px");
        dataLabel.setHeight("-1px");
        dataLabel.setValue("Дата регистрации:");
        headDate.addComponent(dataLabel);
        headDate.setComponentAlignment(dataLabel, new Alignment(33));

        // dateField
        dateField = new PopupDateField();
        dateField.setImmediate(false);
        dateField.setWidth("13.0em");
        dateField.setHeight("-1px");
        dateField.setInvalidAllowed(false);
        headDate.addComponent(dateField);
        headDate.setExpandRatio(dateField, 1.0f);
        headDate.setComponentAlignment(dateField, new Alignment(33));

        return headDate;
    }


    private HorizontalLayout buildHorizontalLayout_3() {
        // common part: create layout
        horizontalLayout_3 = new HorizontalLayout();
        horizontalLayout_3.setImmediate(false);
        horizontalLayout_3.setWidth("100.0%");
        horizontalLayout_3.setHeight("-1px");
        horizontalLayout_3.setMargin(false);
        horizontalLayout_3.setSpacing(true);

        // documentType
        documentType = new Label();
        documentType.setImmediate(false);
        documentType.setWidth("-1px");
        documentType.setHeight("-1px");
        documentType.setValue("Вид документа:");
        horizontalLayout_3.addComponent(documentType);
        horizontalLayout_3.setComponentAlignment(documentType,
                new Alignment(33));

        // documentTypeVal
        documentTypeVal = new TextField();
        documentTypeVal.setImmediate(false);
        documentTypeVal.setWidth("-1px");
        documentTypeVal.setHeight("-1px");
        documentTypeVal.setSecret(false);
        horizontalLayout_3.addComponent(documentTypeVal);
        horizontalLayout_3.setComponentAlignment(documentTypeVal,
                new Alignment(34));

        // selectDocTypesButton
        selectDocTypesButton = new Button();
        selectDocTypesButton.setCaption("+");
        selectDocTypesButton.setImmediate(true);
        selectDocTypesButton.setWidth("-1px");
        selectDocTypesButton.setHeight("-1px");
        horizontalLayout_3.addComponent(selectDocTypesButton);
        horizontalLayout_3.setExpandRatio(selectDocTypesButton, 1.0f);
        horizontalLayout_3.setComponentAlignment(selectDocTypesButton,
                new Alignment(33));

        return horizontalLayout_3;
    }


    private HorizontalLayout buildAddress() {
        // common part: create layout
        address = new HorizontalLayout();
        address.setImmediate(false);
        address.setWidth("-1px");
        address.setHeight("-1px");
        address.setMargin(false);
        address.setSpacing(true);

        // leftAddressPanel
        leftAddressPanel = buildLeftAddressPanel();
        address.addComponent(leftAddressPanel);
        address.setExpandRatio(leftAddressPanel, 1.6f);

        // rightAddressPanel
        rightAddressPanel = buildRightAddressPanel();
        address.addComponent(rightAddressPanel);
        address.setExpandRatio(rightAddressPanel, 3.0f);

        return address;
    }


    private HorizontalLayout buildLeftAddressPanel() {
        // common part: create layout
        leftAddressPanel = new HorizontalLayout();
        leftAddressPanel.setImmediate(false);
        leftAddressPanel.setWidth("-1px");
        leftAddressPanel.setHeight("-1px");
        leftAddressPanel.setMargin(false);
        leftAddressPanel.setSpacing(true);

        // street
        street = new Label();
        street.setImmediate(false);
        street.setWidth("-1px");
        street.setHeight("-1px");
        street.setValue("Улица:");
        leftAddressPanel.addComponent(street);

        // streetValue
        streetValue = new ComboBox();
        streetValue.setImmediate(false);
        streetValue.setWidth("-1px");
        streetValue.setHeight("-1px");
        leftAddressPanel.addComponent(streetValue);

        return leftAddressPanel;
    }


    private HorizontalLayout buildRightAddressPanel() {
        // common part: create layout
        rightAddressPanel = new HorizontalLayout();
        rightAddressPanel.setImmediate(false);
        rightAddressPanel.setWidth("-1px");
        rightAddressPanel.setHeight("-1px");
        rightAddressPanel.setMargin(false);
        rightAddressPanel.setSpacing(true);

        // building
        building = new Label();
        building.setImmediate(false);
        building.setWidth("-1px");
        building.setHeight("-1px");
        building.setValue("Дом:");
        rightAddressPanel.addComponent(building);

        // buildingValue
        buildingValue = new ComboBox();
        buildingValue.setImmediate(false);
        buildingValue.setWidth("50px");
        buildingValue.setHeight("-1px");
        rightAddressPanel.addComponent(buildingValue);

        // room
        room = new Label();
        room.setImmediate(false);
        room.setWidth("-1px");
        room.setHeight("-1px");
        room.setValue("Квартира:");
        rightAddressPanel.addComponent(room);

        // roomValue
        roomValue = new ComboBox();
        roomValue.setImmediate(false);
        roomValue.setWidth("50px");
        roomValue.setHeight("-1px");
        rightAddressPanel.addComponent(roomValue);

        return rightAddressPanel;
    }


    private GridLayout buildGridLayout_1() {
        // common part: create layout
        gridLayout_1 = new GridLayout();
        gridLayout_1.setImmediate(false);
        gridLayout_1.setWidth("-1px");
        gridLayout_1.setHeight("-1px");
        gridLayout_1.setMargin(false);
        gridLayout_1.setSpacing(true);
        gridLayout_1.setColumns(2);
        gridLayout_1.setRows(2);

        // account
        account = new Label();
        account.setImmediate(false);
        account.setWidth("85px");
        account.setHeight("-1px");
        account.setValue("Лицевой счет:");
        gridLayout_1.addComponent(account, 0, 0);
        gridLayout_1.setComponentAlignment(account, new Alignment(6));

        // accountVal
        accountVal = new TextField();
        accountVal.setImmediate(false);
        accountVal.setWidth("30.0em");
        accountVal.setHeight("-1px");
        accountVal.setSecret(false);
        gridLayout_1.addComponent(accountVal, 1, 0);

        // contractor
        contractor = new Label();
        contractor.setImmediate(false);
        contractor.setWidth("85px");
        contractor.setHeight("-1px");
        contractor.setValue("Подрядчик:");
        gridLayout_1.addComponent(contractor, 0, 1);
        gridLayout_1.setComponentAlignment(contractor, new Alignment(6));

        // contractorVal
        contractorVal = new TextField();
        contractorVal.setImmediate(false);
        contractorVal.setWidth("30.0em");
        contractorVal.setHeight("-1px");
        contractorVal.setSecret(false);
        gridLayout_1.addComponent(contractorVal, 1, 1);

        return gridLayout_1;
    }


    private VerticalLayout buildVerticalLayout_1() {
        // common part: create layout
        verticalLayout_1 = new VerticalLayout();
        verticalLayout_1.setImmediate(false);
        verticalLayout_1.setWidth("100.0%");
        verticalLayout_1.setHeight("-1px");
        verticalLayout_1.setMargin(false);
        verticalLayout_1.setSpacing(true);

        // lawCheck
        lawCheck = new CheckBox();
        lawCheck.setCaption("Юридическое лицо");
        lawCheck.setImmediate(false);
        lawCheck.setWidth("-1px");
        lawCheck.setHeight("-1px");
        verticalLayout_1.addComponent(lawCheck);

        // organisation
        organisation = buildOrganisation();
        verticalLayout_1.addComponent(organisation);

        return verticalLayout_1;
    }


    private HorizontalLayout buildOrganisation() {
        // common part: create layout
        organisation = new HorizontalLayout();
        organisation.setImmediate(false);
        organisation.setWidth("100.0%");
        organisation.setHeight("-1px");
        organisation.setMargin(false);
        organisation.setSpacing(true);

        // label_4
        label_4 = new Label();
        label_4.setImmediate(false);
        label_4.setWidth("-1px");
        label_4.setHeight("-1px");
        label_4.setValue("Организация-заявитель:");
        organisation.addComponent(label_4);

        // textField_1
        textField_1 = new TextField();
        textField_1.setImmediate(false);
        textField_1.setWidth("90.0%");
        textField_1.setHeight("-1px");
        textField_1.setSecret(false);
        organisation.addComponent(textField_1);
        organisation.setExpandRatio(textField_1, 1.0f);

        return organisation;
    }


    private GridLayout buildGridLayout_2() {
        // common part: create layout
        gridLayout_2 = new GridLayout();
        gridLayout_2.setImmediate(false);
        gridLayout_2.setWidth("-1px");
        gridLayout_2.setHeight("-1px");
        gridLayout_2.setMargin(false);
        gridLayout_2.setSpacing(true);
        gridLayout_2.setColumns(2);
        gridLayout_2.setRows(2);

        // label_7
        label_7 = new Label();
        label_7.setImmediate(false);
        label_7.setWidth("130px");
        label_7.setHeight("-1px");
        label_7.setValue("Контактное лицо:");
        gridLayout_2.addComponent(label_7, 0, 0);

        // textField_5
        textField_5 = new TextField();
        textField_5.setImmediate(false);
        textField_5.setWidth("27.0em");
        textField_5.setHeight("-1px");
        textField_5.setSecret(false);
        gridLayout_2.addComponent(textField_5, 1, 0);

        // label_8
        label_8 = new Label();
        label_8.setImmediate(false);
        label_8.setWidth("130px");
        label_8.setHeight("-1px");
        label_8.setValue("Контактный телефон:");
        gridLayout_2.addComponent(label_8, 0, 1);

        // textField_6
        textField_6 = new TextField();
        textField_6.setImmediate(false);
        textField_6.setWidth("27.0em");
        textField_6.setHeight("-1px");
        textField_6.setSecret(false);
        gridLayout_2.addComponent(textField_6, 1, 1);

        return gridLayout_2;
    }


    private GridLayout buildGridLayout_3() {
        // common part: create layout
        gridLayout_3 = new GridLayout();
        gridLayout_3.setImmediate(false);
        gridLayout_3.setWidth("-1px");
        gridLayout_3.setHeight("-1px");
        gridLayout_3.setMargin(false);
        gridLayout_3.setSpacing(true);
        gridLayout_3.setColumns(2);
        gridLayout_3.setRows(2);

        // label_10
        label_10 = new Label();
        label_10.setImmediate(false);
        label_10.setWidth("190px");
        label_10.setHeight("-1px");
        label_10.setValue("Номер исходного документа:");
        gridLayout_3.addComponent(label_10, 0, 0);

        // parentDocNumber
        parentDocNumber = new TextField();
        parentDocNumber.setImmediate(false);
        parentDocNumber.setWidth("22.0em");
        parentDocNumber.setHeight("-1px");
        parentDocNumber.setSecret(false);
        gridLayout_3.addComponent(parentDocNumber, 1, 0);

        // label_11
        label_11 = new Label();
        label_11.setImmediate(false);
        label_11.setWidth("190px");
        label_11.setHeight("-1px");
        label_11.setValue("Дата исходного документа:");
        gridLayout_3.addComponent(label_11, 0, 1);

        // parentDocDate
        parentDocDate = new PopupDateField();
        parentDocDate.setImmediate(false);
        parentDocDate.setWidth("13.0em");
        parentDocDate.setHeight("-1px");
        parentDocDate.setInvalidAllowed(false);
        gridLayout_3.addComponent(parentDocDate, 1, 1);

        return gridLayout_3;
    }


    private GridLayout buildGridLayout_5() {
        // common part: create layout
        gridLayout_5 = new GridLayout();
        gridLayout_5.setImmediate(false);
        gridLayout_5.setWidth("-1px");
        gridLayout_5.setHeight("-1px");
        gridLayout_5.setMargin(false);
        gridLayout_5.setColumns(3);
        gridLayout_5.setRows(2);

        // registrator
        registrator = new Label();
        registrator.setImmediate(false);
        registrator.setWidth("85px");
        registrator.setHeight("-1px");
        registrator.setValue("Регистратор:");
        gridLayout_5.addComponent(registrator, 0, 0);
        gridLayout_5.setComponentAlignment(registrator, new Alignment(34));

        // textField_2
        textField_2 = new TextField();
        textField_2.setImmediate(false);
        textField_2.setWidth("20.0em");
        textField_2.setHeight("-1px");
        textField_2.setSecret(false);
        gridLayout_5.addComponent(textField_2, 1, 0);
        gridLayout_5.setComponentAlignment(textField_2, new Alignment(34));

        // button_3
        button_3 = new Button();
        button_3.setCaption("+");
        button_3.setImmediate(true);
        button_3.setWidth("-1px");
        button_3.setHeight("-1px");
        gridLayout_5.addComponent(button_3, 2, 0);
        gridLayout_5.setComponentAlignment(button_3, new Alignment(33));

        // executor
        executor = new Label();
        executor.setImmediate(false);
        executor.setWidth("85px");
        executor.setHeight("-1px");
        executor.setValue("Исполнитель:");
        gridLayout_5.addComponent(executor, 0, 1);
        gridLayout_5.setComponentAlignment(executor, new Alignment(34));

        // executorVal
        executorVal = new TextField();
        executorVal.setImmediate(false);
        executorVal.setWidth("20.0em");
        executorVal.setHeight("-1px");
        executorVal.setSecret(false);
        gridLayout_5.addComponent(executorVal, 1, 1);
        gridLayout_5.setComponentAlignment(executorVal, new Alignment(34));

        // button_4
        button_4 = new Button();
        button_4.setCaption("+");
        button_4.setImmediate(true);
        button_4.setWidth("-1px");
        button_4.setHeight("-1px");
        gridLayout_5.addComponent(button_4, 2, 1);
        gridLayout_5.setComponentAlignment(button_4, new Alignment(33));

        return gridLayout_5;
    }


    private HorizontalLayout buildExtra() {
        // common part: create layout
        extra = new HorizontalLayout();
        extra.setStyleName("main");
        extra.setImmediate(false);
        extra.setWidth("100.0%");
        extra.setHeight("100.0%");
        extra.setMargin(false);

        // extraMain
        extraMain = buildExtraMain();
        extra.addComponent(extraMain);

        return extra;
    }


    private VerticalLayout buildExtraMain() {
        // common part: create layout
        extraMain = new VerticalLayout();
        extraMain.setImmediate(false);
        extraMain.setWidth("100.0%");
        extraMain.setHeight("-1px");
        extraMain.setMargin(true);
        extraMain.setSpacing(true);

        // verticalLayout_2
        verticalLayout_2 = buildVerticalLayout_2();
        extraMain.addComponent(verticalLayout_2);

        // codes
        codes = buildCodes();
        extraMain.addComponent(codes);

        // payment
        payment = buildPayment();
        extraMain.addComponent(payment);

        // priority
        priority = buildPriority();
        extraMain.addComponent(priority);

        // works
        works = buildWorks();
        extraMain.addComponent(works);

        // operatorPanel
        operatorPanel = buildOperatorPanel();
        extraMain.addComponent(operatorPanel);

        return extraMain;
    }


    private VerticalLayout buildVerticalLayout_2() {
        // common part: create layout
        verticalLayout_2 = new VerticalLayout();
        verticalLayout_2.setImmediate(false);
        verticalLayout_2.setWidth("100.0%");
        verticalLayout_2.setHeight("-1px");
        verticalLayout_2.setMargin(false);
        verticalLayout_2.setSpacing(true);

        // statementNumberLabel
        statementNumberLabel = new Label();
        statementNumberLabel.setImmediate(false);
        statementNumberLabel.setWidth("-1px");
        statementNumberLabel.setHeight("-1px");
        statementNumberLabel.setValue("Номер заявки: 789");
        verticalLayout_2.addComponent(statementNumberLabel);

        // numberOfCause
        numberOfCause = new Label();
        numberOfCause.setImmediate(false);
        numberOfCause.setWidth("-1px");
        numberOfCause.setHeight("-1px");
        numberOfCause.setValue("Номер основания:");
        verticalLayout_2.addComponent(numberOfCause);

        return verticalLayout_2;
    }


    private GridLayout buildCodes() {
        // common part: create layout
        codes = new GridLayout();
        codes.setImmediate(false);
        codes.setWidth("90.0%");
        codes.setHeight("-1px");
        codes.setMargin(false);
        codes.setColumns(2);
        codes.setRows(2);

        // horizontalLayout_1
        horizontalLayout_1 = buildHorizontalLayout_1();
        codes.addComponent(horizontalLayout_1, 0, 0);

        // horizontalLayout_2
        horizontalLayout_2 = buildHorizontalLayout_2();
        codes.addComponent(horizontalLayout_2, 1, 0);

        // textArea_1
        textArea_1 = new TextArea();
        textArea_1.setImmediate(false);
        textArea_1.setWidth("95.0%");
        textArea_1.setHeight("60px");
        codes.addComponent(textArea_1, 0, 1);

        // textArea_2
        textArea_2 = new TextArea();
        textArea_2.setImmediate(false);
        textArea_2.setWidth("95.0%");
        textArea_2.setHeight("60px");
        codes.addComponent(textArea_2, 1, 1);

        return codes;
    }


    private HorizontalLayout buildHorizontalLayout_1() {
        // common part: create layout
        horizontalLayout_1 = new HorizontalLayout();
        horizontalLayout_1.setImmediate(false);
        horizontalLayout_1.setWidth("95.0%");
        horizontalLayout_1.setHeight("-1px");
        horizontalLayout_1.setMargin(false);

        // label_1
        label_1 = new Label();
        label_1.setImmediate(false);
        label_1.setWidth("-1px");
        label_1.setHeight("-1px");
        label_1.setValue("Добавьте код домофона:");
        horizontalLayout_1.addComponent(label_1);
        horizontalLayout_1.setComponentAlignment(label_1, new Alignment(33));

        // textField_3
        textField_3 = new TextField();
        textField_3.setImmediate(false);
        textField_3.setWidth("40px");
        textField_3.setHeight("-1px");
        textField_3.setSecret(false);
        horizontalLayout_1.addComponent(textField_3);
        horizontalLayout_1.setExpandRatio(textField_3, 1.0f);
        horizontalLayout_1.setComponentAlignment(textField_3, new Alignment(6));

        // button_1
        button_1 = new Button();
        button_1.setCaption("+");
        button_1.setImmediate(true);
        button_1.setWidth("-1px");
        button_1.setHeight("-1px");
        horizontalLayout_1.addComponent(button_1);
        horizontalLayout_1.setComponentAlignment(button_1, new Alignment(6));

        return horizontalLayout_1;
    }


    private HorizontalLayout buildHorizontalLayout_2() {
        // common part: create layout
        horizontalLayout_2 = new HorizontalLayout();
        horizontalLayout_2.setImmediate(false);
        horizontalLayout_2.setWidth("95.0%");
        horizontalLayout_2.setHeight("-1px");
        horizontalLayout_2.setMargin(false);

        // label_3
        label_3 = new Label();
        label_3.setImmediate(false);
        label_3.setWidth("-1px");
        label_3.setHeight("-1px");
        label_3.setValue("Добавьте код подъезда:");
        horizontalLayout_2.addComponent(label_3);
        horizontalLayout_2.setComponentAlignment(label_3, new Alignment(33));

        // textField_4
        textField_4 = new TextField();
        textField_4.setImmediate(false);
        textField_4.setWidth("40px");
        textField_4.setHeight("-1px");
        textField_4.setSecret(false);
        horizontalLayout_2.addComponent(textField_4);
        horizontalLayout_2.setExpandRatio(textField_4, 1.0f);
        horizontalLayout_2.setComponentAlignment(textField_4, new Alignment(6));

        // button_2
        button_2 = new Button();
        button_2.setCaption("+");
        button_2.setImmediate(true);
        button_2.setWidth("-1px");
        button_2.setHeight("-1px");
        horizontalLayout_2.addComponent(button_2);
        horizontalLayout_2.setComponentAlignment(button_2, new Alignment(6));

        return horizontalLayout_2;
    }

    private HorizontalLayout buildPayment() {

        final List<String> paymentValues = Arrays.asList(new String[]{"бесплатно", "платно", "частичная оплата"});

        // common part: create layout
        payment = new HorizontalLayout();
        payment.setImmediate(false);
        payment.setWidth("100.0%");
        payment.setHeight("55px");
        payment.setMargin(false);

        // paymentSelect
        paymentSelect = new OptionGroup("payment", paymentValues);
        paymentSelect.setCaption("Плата:");
        paymentSelect.setImmediate(false);
        paymentSelect.setWidth("100.0%");
        paymentSelect.setHeight("55px");
        paymentSelect.addStyleName("horizontal");
        paymentSelect.setSizeUndefined();
        paymentSelect.setNullSelectionAllowed(true);
        //paymentSelect.setTabIndex(1);
        payment.addComponent(paymentSelect);

        return payment;
    }

    private HorizontalLayout buildPriority() {

        final List<String> priorityValues = Arrays.asList(new String[]{"текущая", "плановая", "специальная", "аварийная"});

        // common part: create layout
        priority = new HorizontalLayout();
        priority.setImmediate(false);
        priority.setWidth("100.0%");
        priority.setHeight("55px");
        priority.setMargin(false);

        // prioritySelect
        prioritySelect = new OptionGroup("priority", priorityValues);
        prioritySelect.setCaption("Приоритет:");
        prioritySelect.setImmediate(false);
        prioritySelect.setWidth("100.0%");
        prioritySelect.setHeight("55px");
        prioritySelect.addStyleName("horizontal");
        prioritySelect.setSizeUndefined();
        prioritySelect.setNullSelectionAllowed(true);

        priority.addComponent(prioritySelect);
        return priority;
    }


    private GridLayout buildWorks() {
        // common part: create layout
        works = new GridLayout();
        works.setImmediate(false);
        works.setWidth("-1px");
        works.setHeight("-1px");
        works.setMargin(false);
        works.setColumns(3);
        works.setRows(2);

        // typeOfWork
        typeOfWork = new Label();
        typeOfWork.setImmediate(false);
        typeOfWork.setWidth("90px");
        typeOfWork.setHeight("-1px");
        typeOfWork.setValue("Вид работ:");
        works.addComponent(typeOfWork, 0, 0);
        works.setComponentAlignment(typeOfWork, new Alignment(33));

        // typeOfWorkVal
        typeOfWorkVal = new TextField();
        typeOfWorkVal.setImmediate(false);
        typeOfWorkVal.setWidth("30.0em");
        typeOfWorkVal.setHeight("-1px");
        typeOfWorkVal.setSecret(false);
        works.addComponent(typeOfWorkVal, 1, 0);
        works.setComponentAlignment(typeOfWorkVal, new Alignment(34));

        // typeOfWorkButton
        typeOfWorkButton = new Button();
        typeOfWorkButton.setCaption("+");
        typeOfWorkButton.setImmediate(true);
        typeOfWorkButton.setWidth("-1px");
        typeOfWorkButton.setHeight("-1px");
        works.addComponent(typeOfWorkButton, 2, 0);
        works.setComponentAlignment(typeOfWorkButton, new Alignment(33));

        // groupWork
        groupWork = new Label();
        groupWork.setImmediate(false);
        groupWork.setWidth("90px");
        groupWork.setHeight("-1px");
        groupWork.setValue("Группа работ:");
        works.addComponent(groupWork, 0, 1);
        works.setComponentAlignment(groupWork, new Alignment(33));

        // groupWorkVal
        groupWorkVal = new TextField();
        groupWorkVal.setImmediate(false);
        groupWorkVal.setWidth("30.0em");
        groupWorkVal.setHeight("-1px");
        groupWorkVal.setSecret(false);
        works.addComponent(groupWorkVal, 1, 1);
        works.setComponentAlignment(groupWorkVal, new Alignment(34));

        // groupOfWorkButton
        groupOfWorkButton = new Button();
        groupOfWorkButton.setCaption("+");
        groupOfWorkButton.setImmediate(true);
        groupOfWorkButton.setWidth("-1px");
        groupOfWorkButton.setHeight("-1px");
        works.addComponent(groupOfWorkButton, 2, 1);
        works.setComponentAlignment(groupOfWorkButton, new Alignment(33));

        return works;
    }


    private AbsoluteLayout buildOperatorPanel() {
        // common part: create layout
        operatorPanel = new AbsoluteLayout();
        operatorPanel.setImmediate(false);
        operatorPanel.setWidth("100.0%");
        operatorPanel.setHeight("30px");
        operatorPanel.setMargin(false);

        // operator
        operator = new Label();
        operator.setImmediate(false);
        operator.setWidth("-1px");
        operator.setHeight("100.0%");
        operator.setValue("Оператор-контроллер:");
        operatorPanel.addComponent(operator, "top:0.0px;bottom:0.0px;");

        return operatorPanel;
    }


    private AbsoluteLayout buildFooter() {
        // common part: create layout
        footer = new AbsoluteLayout();
        footer.setImmediate(false);
        footer.setWidth("100.0%");
        footer.setHeight("70px");
        footer.setMargin(false);

        // buttonsPanel
        buttonsPanel = buildButtonsPanel();
        footer.addComponent(buttonsPanel, "top:0.0px;right:0.0px;bottom:0.0px;");

        return footer;
    }


    private HorizontalLayout buildButtonsPanel() {
        // common part: create layout
        buttonsPanel = new HorizontalLayout();
        buttonsPanel.setImmediate(false);
        buttonsPanel.setWidth("-1px");
        buttonsPanel.setHeight("100.0%");
        buttonsPanel.setMargin(true);
        buttonsPanel.setSpacing(true);

        // editButton
        editButton = new NativeButton();
        editButton.setCaption("В работу");
        editButton.setImmediate(true);
        editButton.setWidth("120px");
        editButton.setHeight("35px");
        buttonsPanel.addComponent(editButton);
        buttonsPanel.setComponentAlignment(editButton, new Alignment(34));

        // printButton
        printButton = new NativeButton();
        printButton.setCaption("Печать");
        printButton.setImmediate(true);
        printButton.setWidth("120px");
        printButton.setHeight("35px");
        buttonsPanel.addComponent(printButton);
        buttonsPanel.setComponentAlignment(printButton, new Alignment(34));

        // saveButton
        saveButton = new NativeButton();
        saveButton.setCaption("Сохранить");
        saveButton.setImmediate(true);
        saveButton.setWidth("120px");
        saveButton.setHeight("35px");
        buttonsPanel.addComponent(saveButton);
        buttonsPanel.setComponentAlignment(saveButton, new Alignment(34));

        // cancelButton
        cancelButton = new NativeButton();
        cancelButton.setCaption("Отмена");
        cancelButton.setImmediate(true);
        cancelButton.setWidth("120px");
        cancelButton.setHeight("35px");
        buttonsPanel.addComponent(cancelButton);
        buttonsPanel.setComponentAlignment(cancelButton, new Alignment(34));

        return buttonsPanel;
    }
}
