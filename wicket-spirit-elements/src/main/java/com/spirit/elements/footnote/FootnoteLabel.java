package com.spirit.elements.footnote;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;

public class FootnoteLabel extends Label {

    public FootnoteLabel(String id) {
        super(id);
    }

    public FootnoteLabel(String id, IModel<String> model) {
        super(id, model);
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);
        tag.append("class", "footnoteWSE", " ");
    }

}
