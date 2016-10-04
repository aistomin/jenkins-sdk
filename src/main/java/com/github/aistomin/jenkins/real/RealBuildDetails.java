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

import com.github.aistomin.iterators.EntityIterator;
import com.github.aistomin.iterators.Transformation;
import com.github.aistomin.jenkins.BuildDetails;
import com.github.aistomin.jenkins.BuildParameter;
import com.github.aistomin.xml.Xml;
import com.github.aistomin.xml.XmlString;
import com.jcabi.xml.XML;
import com.jcabi.xml.XMLDocument;
import java.util.Iterator;

/**
 * Jenkins' job build details like: display name, url, duration etc.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class RealBuildDetails implements BuildDetails {

    /**
     * XML content of build details.
     */
    private final transient Xml content;

    /**
     * Ctor.
     *
     * @param xml XML content of build details.
     */
    public RealBuildDetails(final Xml xml) {
        this.content = xml;
    }

    @Override
    public String fullDisplayName() throws Exception {
        return this.content.field("//build/fullDisplayName/text()");
    }

    @Override
    public String displayName() throws Exception {
        return this.content.field("//build/displayName/text()");
    }

    @Override
    public Long estimated() throws Exception {
        return Long.parseLong(
            this.content.field("//build/estimatedDuration/text()")
        );
    }

    @Override
    public Long duration() throws Exception {
        return Long.parseLong(this.content.field("//build/duration/text()"));
    }

    @Override
    public Boolean building() throws Exception {
        return Boolean.parseBoolean(
            this.content.field("//build/building/text()")
        );
    }

    @Override
    public Long queue() throws Exception {
        return Long.parseLong(this.content.field("//build/queueId/text()"));
    }

    @Override
    public Iterator<BuildParameter> parameters() throws Exception {
        return new EntityIterator<>(
            new XMLDocument(this.content.content()).nodes("//parameter")
                .iterator(),
            new RealBuildDetails.ParamTransformer()
        );
    }

    @Override
    public String gitRevision() throws Exception {
        return this.content.field(
            "//build/action/lastBuiltRevision/SHA1/text()"
        );
    }

    /**
     * BuildParameter transformer.
     */
    private static final class ParamTransformer implements
        Transformation<BuildParameter, XML> {

        /**
         * Transform XML to JobParameter.
         *
         * @param source Source object.
         * @return JobParameter.
         * @checkstyle NonStaticMethodCheck (500 lines)
         */
        public BuildParameter transform(final XML source) {
            return new RealBuildParameter(
                new XmlString(source.toString())
            );
        }
    }
}
