package com.spirit.elements.footnote;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.wicket.Component;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

/**
 *
 */
public class FootnotePanel extends GenericPanel<Map<String, String>> {

    private static final JavaScriptResourceReference FOOTNOTE_JS =
            new JavaScriptResourceReference(FootnotePanel.class, "FootnotePanel.js");

    private boolean renumberFootnotes = true;

    private WebMarkupContainer scopeArea = null;

    public FootnotePanel(String id) {
        super(id);
    }

    public FootnotePanel(String id, IModel<Map<String, String>> model) {
        super(id, model);
    }

    public boolean isRenumberFootnotes() {
        return renumberFootnotes;
    }

    public void setRenumberFootnotes(boolean renumberFootnotes) {
        this.renumberFootnotes = renumberFootnotes;
    }

    public Component getScopeArea() {
       return scopeArea;
    }

    public void setScopeArea(WebMarkupContainer scopeArea) {
        this.scopeArea = scopeArea;
        if (this.scopeArea != null) {
            this.scopeArea.setOutputMarkupId(true);
        }
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);

        if (this.renumberFootnotes) {
            response.render(JavaScriptReferenceHeaderItem.forReference(
                    getApplication().getJavaScriptLibrarySettings().getJQueryReference()));
            response.render(JavaScriptReferenceHeaderItem.forReference(FOOTNOTE_JS, true));
        }
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new ListView<Map.Entry<String, String>>("footnoteLine", new FootnoteModel(this.getModel())) {
            @Override
            protected void populateItem(ListItem<Map.Entry<String, String>> item) {
                Map.Entry<String, String> footnoteEntry = item.getModelObject();
                item.add(new Label("footnoteSymbol", new StringResourceModel(footnoteEntry.getKey(), null,
                        footnoteEntry.getKey())));
                item.add(new Label("footnoteText", new StringResourceModel(footnoteEntry.getValue(), null,
                        footnoteEntry.getValue())));
            }
        });
    }

    private static class FootnoteModel implements IModel<List<Map.Entry<String, String>>> {

        IModel<Map<String, String>> footnoteMapModel;

        private transient List<Map.Entry<String, String>> footnoteList;

        public FootnoteModel(IModel<Map<String, String>> mapModel) {
            this.footnoteMapModel = mapModel;
            this.footnoteList = null;
        }

        @Override
        public List<Map.Entry<String, String>> getObject() {
            if (this.footnoteList == null) {
                Map<String, String> footnoteMap = footnoteMapModel.getObject();
                this.footnoteList = (footnoteMap != null ? new ArrayList<Map.Entry<String, String>>(
                        footnoteMap.entrySet()) : null);
            }
            return this.footnoteList;
        }

        @Override
        public void setObject(List<Map.Entry<String, String>> entries) {
            //not being used
        }

        @Override
        public void detach() {
            this.footnoteList = null;
            this.footnoteMapModel.detach();
        }
    }

}
