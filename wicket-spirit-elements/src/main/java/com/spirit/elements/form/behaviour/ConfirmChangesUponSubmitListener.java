package com.spirit.elements.form.behaviour;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.attributes.AjaxCallListener;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.model.AbstractReadOnlyModel;

/**
 * Ajax call listener to trigger Javascript confirm box whenever values changed within form components that are
 * activated for this functionality. Listener is meant to be assigned to any ajax form submit behavior.
 */
public class ConfirmChangesUponSubmitListener extends AjaxCallListener {

    private static final String CONFIRM_BOX_DEFAULT_TEXT = "Please confirm your changes.";

    private static final String CONFIRM_BOX_JS_LOGIC = "if ((function () { var anyChange = false; if (!attrs.f) return anyChange; jQuery('#' + attrs.f + ' [%1$s]').each(function () { var formComp = jQuery(this), uiVal = formComp.val(), origVal = formComp.attr('%1$s'); if (formComp.attr('type') === 'checkbox') uiVal = formComp.prop('checked').toString(); anyChange = anyChange || uiVal != origVal; }); return anyChange; }()) && !confirm('%2$s')) { location.reload(); return false; };";

    private final String attributeDesignator;

    private final String confirmTextResourceKey;

    /**
     * If multiple ConfirmChangesUponSubmitListeners should exist distinctly in a single ajax form submit, then ensure
     * that each listener has a different changeDesignator upon construction.
     * <p/>
     * The resource key for the confirm box text will become changeDesignator+".confirmText"
     */
    public ConfirmChangesUponSubmitListener(final String changeDesignator) {
        this.attributeDesignator = "original-".concat(changeDesignator);
        this.confirmTextResourceKey = changeDesignator.concat(".confirmText");
    }

    @Override
    public CharSequence getPrecondition(Component component) {
        String confirmBoxText = component.getString(this.confirmTextResourceKey, null, CONFIRM_BOX_DEFAULT_TEXT);
        return String.format(CONFIRM_BOX_JS_LOGIC, this.attributeDesignator, confirmBoxText);
    }

    public void activateConfirmationForComponent(final FormComponent formComponent) {
        //An attribute is added to the specified form component to hold its original model value.
        //This will later help us identify, if any component's value was changed by the user.
        formComponent.add(AttributeModifier.replace(this.attributeDesignator, new AbstractReadOnlyModel() {
            @Override
            public String getObject() {
                return formComponent.getDefaultModelObjectAsString();
            }
        }));
    }
}