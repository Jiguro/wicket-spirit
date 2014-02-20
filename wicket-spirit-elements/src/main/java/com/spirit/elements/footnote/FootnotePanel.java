/*
 * Jumio Inc.
 *
 * Copyright (C) 2010 - 2011
 * All rights reserved.
 */
package com.spirit.elements.footnote;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;

/**
 *
 */
public class FootnotePanel extends GenericPanel<Map<String, String>> {

    private FootnoteModel footnoteModel;

    public FootnotePanel(String id, IModel<? extends Map<String, String>> model) {
        super(id, (IModel<Map<String, String>>)model);
        this.footnoteModel = new FootnoteModel();
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        response.render(OnDomReadyHeaderItem.forScript("console.log('Hello World!');"));
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new ListView<Map.Entry<String, String>>("footnoteLine", this.footnoteModel) {
            @Override
            protected void populateItem(ListItem<Map.Entry<String, String>> item) {
                Map.Entry<String, String> entry = item.getModelObject();
                item.add(new Label("footnoteSymbol", entry.getKey()));
                item.add(new Label("footnoteText", entry.getValue()));
            }
        });
    }

    private class FootnoteModel implements IModel<List<Map.Entry<String, String>>> {

        @Override
        public List<Map.Entry<String, String>> getObject() {
            Map<String, String> footnoteMap = FootnotePanel.this.getModelObject();
            return new ArrayList<Map.Entry<String, String>>(footnoteMap.entrySet());
        }

        @Override
        public void setObject(List<Map.Entry<String, String>> entries) {
        }

        @Override
        public void detach() {
        }
    }

}
