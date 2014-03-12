package com.spirit.ui.pages;

import com.spirit.elements.form.behaviour.ConfirmChangesUponSubmitBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.model.Model;

import java.util.Arrays;

public class ConfirmBoxDemoPage extends WebPage {

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final Form form = new Form("form");
        form.setOutputMarkupId(true);

        TextField name = new TextField<String>("name", Model.of("My name"));
        DropDownChoice age = new DropDownChoice<Integer>("age", Model.of(Integer.valueOf(1)), Arrays.asList(Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(2)));
        CheckBox male = new CheckBox("male", Model.of(Boolean.TRUE));
        TextField misc = new TextField<String>("misc", Model.of("Miscellaneous"));

        Button innerSubmit = new Button("innerSubmit");
        innerSubmit.add(new AjaxFormSubmitBehavior(form, "onclick") {
            @Override
            protected void onSubmit(AjaxRequestTarget target) {
                super.onSubmit(target);
                target.add(form);
            }

            @Override
            protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
                super.updateAjaxAttributes(attributes);
                attributes.setAllowDefault(true);
            }
        });

        SubmitLink outerSubmit = new SubmitLink("outerSubmit", form);
        AjaxSubmitLink ajaxSubmit = new AjaxSubmitLink("ajaxSubmit", form) {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form form) {
                super.onSubmit(target, form);
                target.add(form);
            }

            @Override
            protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
                super.updateAjaxAttributes(attributes);
                attributes.setAllowDefault(true);
            }
        };

        form.add(new ConfirmChangesUponSubmitBehavior(name, age, male));

        add(form);
        add(outerSubmit);
        add(ajaxSubmit);
        form.add(name);
        form.add(age);
        form.add(male);
        form.add(misc);
        form.add(innerSubmit);
    }

}
