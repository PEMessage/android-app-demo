package org.example.application.framework;


import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class ItemTest {
    @Test
    public void testMain() {
        Item root = new Item("root");
        Item documents = new Item("documents");
        Item downloads = new Item("downloads");
        Item pictures = new Item("pictures");
        root.addChild(documents);
        root.addChild(downloads);
        root.addChild(pictures);

        Item news = new Item("news");
        ItemUtils.addChildChain(documents, "misc/2025-09-09".split("/"), news);

        System.out.println(root.toStringAll());
        // assertTrue(false);
        
        root.initSegment();
        System.out.println(root.toStringAll());
    }

}
