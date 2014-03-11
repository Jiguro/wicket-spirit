package com.spirit.ui.pages;

import com.spirit.elements.footnote.FootnoteLabel;
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
        map.put("4", "Lorem ipsum");

        add(new FootnotePanel("footnotePanel", Model.ofMap(map)));

        add(new FootnoteLabel("a", Model.of("1")));
        add(new FootnoteLabel("b", Model.of("3")));
        add(new FootnoteLabel("c", Model.of("1")));
    }

}
