/**
 * Copyright (c) 2016, Istomin Andrei
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.aistomin.xml;

import com.jcabi.xml.XMLDocument;
import java.util.List;

/**
 * XPath.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class XPath {

    /**
     * XPath string.
     */
    private final transient String path;

    /**
     * Ctor.
     *
     * @param xpath XPath string.
     */
    public XPath(final String xpath) {
        this.path = xpath;
    }

    /**
     * Get value from xml using XPath.
     *
     * @param xml XML string.
     * @return Value.
     */
    public String valueFrom(final String xml) {
        final List<String> values = new XMLDocument(xml)
            .xpath(this.path);
        if (values.size() != 1) {
            throw new IllegalStateException(
                "Field not found in build's XML."
            );
        }
        return values.get(0);
    }
}
