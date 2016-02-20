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
     * Default ctor.
     *
     * @throws Exception If reading XML was not successful.
     */
    public FakeBuild() throws Exception {
        this(new XmlResource(FakeBuild.RESOURCE));
    }

    /**
     * Ctor.
     *
     * @param xml XML content that should be returned in xml() method.
     */
    public FakeBuild(final Xml xml) {
        this.content = xml;
    }

    /**
     * Fake build's number.
     *
     * @return Build number.
     * @throws Exception If error occurred.
     * @todo: Let's implement this method and solve Issue #151.
     */
    public String number() throws Exception {
        throw new NotImplementedException(
            String.format(
                "number() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Fake build's result.
     *
     * @return Build's result.
     * @throws Exception If error occurred.
     * @todo: Let's implement this method and solve Issue #152.
     */
    public BuildResult result() throws Exception {
        throw new NotImplementedException(
            String.format(
                "result() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Fake build's date.
     *
     * @return Build's date.
     * @throws Exception If error occurred.
     * @todo: Let's implement this method and solve Issue #153.
     */
    public Date date() throws Exception {
        throw new NotImplementedException(
            String.format(
                "date() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
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
}
