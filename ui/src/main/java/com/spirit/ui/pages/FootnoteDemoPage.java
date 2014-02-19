/*
 * Jumio Inc.
 *
 * Copyright (C) 2010 - 2011
 * All rights reserved.
 */
package com.spirit.ui.pages;

import com.spirit.ui.component.footnote.FootnotePanel;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

/**
 *
 */
public class FootnoteDemoPage extends WebPage {

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new FootnotePanel("footnotePanel"));
    }

}
