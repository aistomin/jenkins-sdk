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

import com.github.aistomin.jenkins.JobParameter;
import com.github.aistomin.xml.Xml;

/**
 * Jenkins job parameter.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class RealJobParameter implements JobParameter {

    /**
     * XML content of build details.
     */
    private final transient Xml content;

    /**
     * Ctor.
     * @param xml XML content of build details.
     */
    public RealJobParameter(final Xml xml) {
        this.content = xml;
    }

    /**
     * Parameter's name.
     *
     * @return Parameter's name.
     * @throws Exception If error occurred.
     */
    public String name() throws Exception {
        return this.content.field("//parameterDefinition/name/text()");
    }

    /**
     * Parameter's type.
     *
     * @return Parameter's type.
     * @throws Exception If error occurred.
     */
    public String type() throws Exception {
        return this.content.field("//parameterDefinition/type/text()");
    }

    /**
     * Parameter's description.
     *
     * @return Parameter's description.
     * @throws Exception If error occurred.
     */
    public String description() throws Exception {
        return this.content.field("//parameterDefinition/description/text()");
    }
}
