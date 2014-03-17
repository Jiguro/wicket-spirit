package com.spirit.ui.pages;

import com.spirit.elements.form.behaviour.ConfirmChangesUponSubmitListener;
import java.util.Date;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.Model;

import java.util.Arrays;

public class ConfirmBoxDemoPage extends WebPage {

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final Form form = new Form("form");
        form.setOutputMarkupId(true);

        TextField name = new TextField<String>("name", Model.of("My name"));
        DropDownChoice age = new DropDownChoice<Integer>("age", Model.of(1), Arrays.asList(0, 1, 2));
        CheckBox male = new CheckBox("male", Model.of(Boolean.TRUE));
        TextField misc = new TextField<String>("misc", Model.of("Miscellaneous"));
        Label timesteamp = new Label("timestamp", new AbstractReadOnlyModel<String>() {
            @Override
            public String getObject() {
                return new Date().toString();
            }
        });

        final ConfirmChangesUponSubmitListener confirmBoxListener = new ConfirmChangesUponSubmitListener(
                "userCoreData");
        confirmBoxListener.activateConfirmationForComponent(name);
        confirmBoxListener.activateConfirmationForComponent(age);
        confirmBoxListener.activateConfirmationForComponent(male);

        AjaxSubmitLink ajaxSubmit = new AjaxSubmitLink("ajaxSubmit", form) {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form form) {
                super.onSubmit(target, form);
                target.add(form);
            }

            @Override
            protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
                super.updateAjaxAttributes(attributes);
                attributes.getAjaxCallListeners().add(confirmBoxListener);
            }
        };

        add(form);
        add(ajaxSubmit);
        form.add(name);
        form.add(age);
        form.add(male);
        form.add(misc);
        form.add(timesteamp);
    }

}
