/*
 * Jumio Inc.
 *
 * Copyright (C) 2010 - 2011
 * All rights reserved.
 */
package com.spirit.ui.component.footnote;

import java.util.Map;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;

/**
 *
 */
public class FootnotePanel extends GenericPanel<Map<String, String>> {

    public FootnotePanel(String id) {
        super(id);
    }

    public FootnotePanel(String id, IModel<Map<String, String>> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
    }

}
