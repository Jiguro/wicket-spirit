/*
 * Jumio Inc.
 *
 * Copyright (C) 2010 - 2011
 * All rights reserved.
 */
package com.spirit.ui.pages;

import com.spirit.elements.footnote.FootnotePanel;
import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.Model;

/**
 *
 */
public class FootnoteDemoPage extends WebPage {

    @Override
    protected void onInitialize() {
        super.onInitialize();

        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "abc");
        map.put("2", "XYZ");
        map.put("3", "");
        map.put("4", null);

        add(new FootnotePanel("footnotePanel", Model.ofMap(map)));
    }

}
