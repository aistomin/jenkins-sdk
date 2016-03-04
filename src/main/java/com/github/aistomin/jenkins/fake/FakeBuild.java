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
import com.github.aistomin.xml.Xml;
import com.github.aistomin.xml.XmlResource;
import java.util.Date;
import org.apache.commons.lang3.NotImplementedException;

/**
 * Fake Jenkins' job build.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class FakeBuild implements Build {

    /**
     * XML resource file name.
     */
    private static final String RESOURCE = "build.xml";

    /**
     * XML content that should be returned in xml() method.
     */
    private final transient Xml content;

    /**
     * Build's number that will be returned in number() method.
     */
    private final transient String identifier;

    /**
     * Fake build's result.
     */
    private final transient BuildResult status;

    /**
     * Fake build's start date.
     */
    private final transient Date start;

    /**
     * Default ctor.
     */
    public FakeBuild() {
        this(
            new XmlResource(FakeBuild.RESOURCE), FakeBuild.defaultNumber(),
            BuildResult.SUCCESS, new Date()
        );
    }

    /**
     * Secondary ctor.
     *
     * @param xml XML content that should be returned in xml() method.
     */
    public FakeBuild(final Xml xml) {
        this(xml, FakeBuild.defaultNumber(), BuildResult.SUCCESS, new Date());
    }

    /**
     * Secondary ctor.
     *
     * @param result Fake build's result.
     */
    public FakeBuild(final BuildResult result) {
        this(
            new XmlResource(FakeBuild.RESOURCE), FakeBuild.defaultNumber(),
            result, new Date()
        );
    }

    /**
     * Secondary ctor.
     *
     * @param number Build's number that will be returned in number() method.
     */
    public FakeBuild(final String number) {
        this(
            new XmlResource(FakeBuild.RESOURCE), number, BuildResult.SUCCESS,
            new Date()
        );
    }

    /**
     * Secondary ctor.
     *
     * @param date Fake build's start date.
     */
    public FakeBuild(final Date date) {
        this(
            new XmlResource(FakeBuild.RESOURCE), FakeBuild.defaultNumber(),
            BuildResult.SUCCESS, date
        );
    }

    /**
     * Primary ctor.
     *
     * @param xml XML content that should be returned in xml() method.
     * @param number Build's number that will be returned in number() method.
     * @param result Fake build's result.
     * @param date Fake build's start date.
     * @checkstyle ParameterNumberCheck (500 lines)
     */
    public FakeBuild(
        final Xml xml, final String number, final BuildResult result,
        final Date date
    ) {
        this.content = xml;
        this.identifier = number;
        this.status = result;
        this.start = date;
    }

    /**
     * Fake build's number.
     *
     * @return Build number.
     * @throws Exception If error occurred.
     */
    public String number() throws Exception {
        return this.identifier;
    }

    /**
     * Fake build's result.
     *
     * @return Build's result.
     * @throws Exception If error occurred.
     */
    public BuildResult result() throws Exception {
        return this.status;
    }

    /**
     * Fake build's date.
     *
     * @return Build's date.
     * @throws Exception If error occurred.
     */
    public Date date() throws Exception {
        return this.start;
    }

    /**
     * Fake build's URL.
     *
     * @return URL string.
     * @throws Exception If error occurred.
     * @todo: Let's implement this method and solve Issue #154.
     */
    public String url() throws Exception {
        throw new NotImplementedException(
            String.format(
                "url() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Fake build's details.
     *
     * @return Build's details.
     * @throws Exception If error occurred.
     * @todo: Let's implement this method and solve Issue #155.
     */
    public BuildDetails details() throws Exception {
        throw new NotImplementedException(
            String.format(
                "details() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Fake build's XML representation.
     *
     * @return XML's string.
     * @throws Exception If something goes wrong.
     */
    public String xml() throws Exception {
        return this.content.content();
    }

    /**
     * Generate random build's number.
     * @return Build's number.
     */
    private static String defaultNumber() {
        return String.format("#%d", System.currentTimeMillis());
    }
}
