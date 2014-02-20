/*
 * Jumio Inc.
 *
 * Copyright (C) 2010 - 2011
 * All rights reserved.
 */
package com.spirit.ui.pages;

import com.spirit.elements.footnote.FootnotePanel;
import java.util.HashMap;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.Model;

/**
 *
 */
public class FootnoteDemoPage extends WebPage {

    @Override
    protected void onInitialize() {
        super.onInitialize();

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("1", "abc");
        map.put("2", "XYZ");

        add(new FootnotePanel("footnotePanel", Model.of(map)));
    }

}
