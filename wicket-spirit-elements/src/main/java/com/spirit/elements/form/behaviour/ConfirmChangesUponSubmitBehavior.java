package com.spirit.elements.form.behaviour;

import java.io.Serializable;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnEventHeaderItem;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.model.AbstractReadOnlyModel;

/**
 * Triggers a Javascript confirm box upon form submit, if any of the specified form components' values changed. Use
 * resource key "confirmBox.text" to specify the text that should appear in the confirm box.
 */
public class ConfirmChangesUponSubmitBehavior extends Behavior {

    private Form<?> submittingForm = null;

    private final ConfirmBoxJsLogic confirmBoxJsLogic = new ConfirmBoxJsLogic();

    public ConfirmChangesUponSubmitBehavior(FormComponent... observedComponents) {
        //An attribute is added to the specified form components to hold their original model values.
        //This will later help us identify, if any component's value was changed by the user.
        for (final FormComponent fc : observedComponents) {
            fc.add(AttributeModifier.replace("data-original-value", new AbstractReadOnlyModel<String>() {
                @Override
                public String getObject() {
                    return fc.getDefaultModelObjectAsString();
                }
            }));
        }
    }

    @Override
    public void bind(Component component) {
        super.bind(component);

        submittingForm = (component instanceof Form<?>) ? (Form<?>)component : component.findParent(Form.class);
        if (submittingForm == null) {
            throw new IllegalStateException("form cannot be found in the hierarchy of the component this behavior" +
                    " is attached to: Component=" + component.toString(false));
        }
        submittingForm.setOutputMarkupId(true);
    }

    @Override
    public void renderHead(Component component, IHeaderResponse response) {
        super.renderHead(component, response);
        String targetId = String.format("'%s'", submittingForm.getMarkupId());
        response.render(OnEventHeaderItem.forScript(targetId, "submit", confirmBoxJsLogic.getJsLogicAsString()));
    }

    /**
     * This object will return the confirm box javascript logic as a String
     */
    private class ConfirmBoxJsLogic implements Serializable {

        private static final String CONFIRM_BOX_TEXT_KEY = "confirmBox.text";

        private static final String CONFIRM_BOX_DEFAULT_TEXT = "Please confirm your changes.";

        private static final String CONFIRM_BOX_JS_LOGIC = "if ((function() {var anyChange = false; $('#%s [data-original-value]').each(function () {var formComp = $(this), uiVal = formComp.val(), origVal = formComp.attr('data-original-value'); if (formComp.attr('type') === 'checkbox') {uiVal = formComp.prop('checked').toString()} anyChange = anyChange || uiVal != origVal;}); return anyChange;} ()) && !confirm('%s')) {location.reload(); return false;};";

        public String getJsLogicAsString() {
            String markupId = ConfirmChangesUponSubmitBehavior.this.submittingForm.getMarkupId();
            String confirmBoxText = ConfirmChangesUponSubmitBehavior.this.submittingForm.getString(CONFIRM_BOX_TEXT_KEY,
                    null, CONFIRM_BOX_DEFAULT_TEXT);

            return String.format(CONFIRM_BOX_JS_LOGIC, markupId, confirmBoxText);
        }
    }

}
