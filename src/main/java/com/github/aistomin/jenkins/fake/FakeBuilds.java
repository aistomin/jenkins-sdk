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
import com.github.aistomin.jenkins.Builds;
import com.github.aistomin.xml.XML;
import com.github.aistomin.xml.XMLResource;
import java.util.Iterator;
import org.apache.commons.lang3.NotImplementedException;

/**
 * Fake Jenkins' builds for tests.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class FakeBuilds implements Builds {

    /**
     * XML resource file name.
     */
    private static final String RESOURCE = "builds.xml";

    /**
     * XML content that should be returned in xml() method.
     */
    private final transient XML content;

    /**
     * Default ctor.
     *
     * @throws Exception If reading XML was not successful.
     */
    public FakeBuilds() throws Exception {
        this(new XMLResource(FakeBuilds.RESOURCE));
    }

    /**
     * Ctor.
     *
     * @param xml XML content that should be returned in xml() method.
     */
    public FakeBuilds(final XML xml) {
        this.content = xml;
    }

    /**
     * Fake builds iterator.
     *
     * @return Fake builds iterator.
     * @throws Exception If something goes wrong.
     * @todo: Let's implement this method and solve Issue #96.
     */
    public Iterator<Build> iterator() throws Exception {
        throw new NotImplementedException(
            String.format(
                "iterator() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Last successful fake build.
     *
     * @return Last successful build.
     * @throws Exception If something goes wrong.
     * @todo: Let's implement this method and solve Issue #97.
     */
    public Build lastSuccessful() throws Exception {
        throw new NotImplementedException(
            String.format(
                "lastSuccessful() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Last failed fake build.
     *
     * @return Last failed build.
     * @throws Exception If something goes wrong.
     * @todo: Let's implement this method and solve Issue #98.
     */
    public Build lastFailed() throws Exception {
        throw new NotImplementedException(
            String.format(
                "lastFailed() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Last stable fake build.
     *
     * @return Last stable build.
     * @throws Exception If something goes wrong.
     * @todo: Let's implement this method and solve Issue #99.
     */
    public Build lastStable() throws Exception {
        throw new NotImplementedException(
            String.format(
                "lastStable() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Last unstable fake build.
     *
     * @return Last unstable build.
     * @throws Exception If something goes wrong.
     * @todo: Let's implement this method and solve Issue #100.
     */
    public Build lastUnstable() throws Exception {
        throw new NotImplementedException(
            String.format(
                "lastUnstable() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Find fake build by number.
     *
     * @param number Build's number.
     * @return Build.
     * @throws Exception If something goes wrong.
     * @todo: Let's implement this method and solve Issue #101.
     */
    public Build find(final String number) throws Exception {
        throw new NotImplementedException(
            String.format(
                "find() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Fake builds' XML representation.
     *
     * @return XML's string.
     * @throws Exception If something goes wrong.
     */
    public String xml() throws Exception {
        return this.content.content();
    }
}
