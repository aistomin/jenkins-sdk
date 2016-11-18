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
package com.github.aistomin.jenkins.fake;

import com.github.aistomin.jenkins.Build;
import com.github.aistomin.jenkins.BuildDetails;
import com.github.aistomin.jenkins.BuildResult;
import com.github.aistomin.jenkins.real.RealBuildDetails;
import com.github.aistomin.xml.Xml;
import com.github.aistomin.xml.XmlResource;
import com.github.aistomin.xml.XmlString;
import java.util.Date;

/**
 * Fake Jenkins' job build.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class FakeBuild implements Build {

    /**
     * XML content of the build.
     */
    private final transient Xml content;

    /**
     * Build's actions.
     */
    private final transient FakeBuildActions actions;

    /**
     * Default ctor.
     */
    public FakeBuild() {
        this(new XmlResource("build.xml"), new DefaultBuildActions());
    }

    /**
     * Primary ctor.
     *
     * @param xml XML content that should be returned in xml() method.
     * @param callbacks Build's actions.
     */
    public FakeBuild(
        final Xml xml, final FakeBuildActions callbacks
    ) {
        this.content = xml;
        this.actions = callbacks;
    }

    @Override
    public String number() throws Exception {
        return this.content.field("//build/displayName/text()");
    }

    @Override
    public BuildResult result() throws Exception {
        return BuildResult.valueOf(this.content.field("//build/result/text()"));
    }

    @Override
    public Date date() throws Exception {
        return new Date(
            Long.parseLong(this.content.field("//build/timestamp/text()"))
        );
    }

    @Override
    public String url() throws Exception {
        return this.content.field("//build/url/text()");
    }

    @Override
    public BuildDetails details() throws Exception {
        return new RealBuildDetails(new XmlString(this.xml()));
    }

    @Override
    public void delete() throws Exception {
        this.actions.delete(this);
    }

    @Override
    public void cancel() throws Exception {
        this.actions.cancel(this);
    }

    /**
     * Set/unset flag that allows to keep build logs forever.
     *
     * @throws Exception If error occurred.
     */
    public void toggleLogKeep() throws Exception {
        this.actions.toggleLogKeep(this);
    }

    @Override
    public String xml() throws Exception {
        return this.content.content();
    }
}
