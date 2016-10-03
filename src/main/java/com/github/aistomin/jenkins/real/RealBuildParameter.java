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
package com.github.aistomin.jenkins.real;

import com.github.aistomin.jenkins.BuildParameter;
import com.github.aistomin.xml.Xml;

/**
 * Jenkins build parameter.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.2
 */
public final class RealBuildParameter implements BuildParameter {

    /**
     * XML content of parameter details.
     */
    private final transient Xml content;

    /**
     * Ctor.
     * @param xml XML content of parameter details.
     */
    public RealBuildParameter(final Xml xml) {
        this.content = xml;
    }

    @Override
    public String name() throws Exception {
        return this.content.field("//parameter/name/text()");
    }

    @Override
    public String value() throws Exception {
        return this.content.field("//parameter/value/text()");
    }
}
