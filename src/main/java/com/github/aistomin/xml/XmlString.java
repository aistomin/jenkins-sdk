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
 * XML string.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class XmlString implements Xml {

    /**
     * XML string content.
     */
    private final transient String string;

    /**
     * Ctor.
     * @param xml XML string content.
     */
    public XmlString(final String xml) {
        this.string = xml;
    }

    /**
     * XML string content.
     * @return XML string.
     * @throws Exception If reading XML was not successful.
     */
    public String content() throws Exception {
        return this.string;
    }

    /**
     * Search field value by XPath.
     *
     * @param xpath XPath.
     * @return Field's value.
     * @throws Exception If reading XML was not successful.
     */
    public String field(final String xpath) throws Exception {
        final List<String> values = new XMLDocument(this.string)
            .xpath(xpath);
        if (values.size() != 1) {
            throw new IllegalStateException(
                "Field not found in build's XML."
            );
        }
        return values.get(0);
    }
}
