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

import com.github.aistomin.http.PostRequest;
import com.github.aistomin.iterators.EntityIterator;
import com.github.aistomin.jenkins.Build;
import com.github.aistomin.jenkins.Builds;
import com.github.aistomin.jenkins.Credentials;
import com.github.aistomin.xml.Xml;
import com.github.aistomin.xml.XmlString;
import com.jcabi.xml.XMLDocument;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.NotImplementedException;

/**
 * Jenkins' job builds.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class RealBuilds implements Builds {

    /**
     * Parent job's API URL.
     */
    private final transient String api;

    /**
     * Jenkins credentials.
     */
    private final transient Credentials creds;

    /**
     * Ctor.
     *
     * @param url Parent job's API URL.
     * @param credentials Jenkins credentials.
     */
    public RealBuilds(final String url, final Credentials credentials) {
        this.api = url;
        this.creds = credentials;
    }

    /**
     * Builds iterator.
     *
     * @return Builds iterator.
     * @throws Exception If reading the builds was not successful.
     */
    public Iterator<Build> iterator() throws Exception {
        final List<String> builds = new XMLDocument(this.xml())
            .xpath("//build/displayName/text()");
        Collections.sort(builds, String.CASE_INSENSITIVE_ORDER);
        return new EntityIterator<Build, String>(
            builds.iterator(), new RealBuild.Transformer(this.api, this.creds)
        );
    }

    /**
     * Last successful build.
     *
     * @return Last successful build.
     * @throws Exception If reading the build was not successful.
     */
    public Build lastSuccessful() throws Exception {
        return new RealBuild(
            this.detailed().field(
                "//lastSuccessfulBuild/displayName/text()"
            ), this.api, this.creds
        );
    }

    /**
     * Last failed build.
     *
     * @return Last failed build.
     * @throws Exception If reading the build was not successful.
     * @todo: Let's implement this method and solve Issue #107.
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
     * Last stable build.
     *
     * @return Last stable build.
     * @throws Exception If reading the build was not successful.
     */
    public Build lastStable() throws Exception {
        return new RealBuild(
            this.detailed().field(
                "//lastStableBuild/displayName/text()"
            ), this.api, this.creds
        );
    }

    /**
     * Last unstable build.
     *
     * @return Last unstable build.
     * @throws Exception If reading the build was not successful.
     * @todo: Let's implement this method and solve Issue #109.
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
     * Find build by number.
     *
     * @param number Build's number.
     * @return Build.
     * @throws Exception If reading the build was not successful.
     * @todo: Let's implement this method and solve Issue #110.
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
     * Builds' XML representation.
     *
     * @return XML's string.
     * @throws Exception If reading XML was not successful.
     */
    public String xml() throws Exception {
        return new PostRequest(this.request(), this.creds.headers()).execute();
    }

    /**
     * Detailed Job XML.
     *
     * @return XML.
     * @throws Exception If reading XML was not successful.
     */
    private Xml detailed() throws Exception {
        return new XmlString(
            new PostRequest(this.api, this.creds.headers()).execute()
        );
    }

    /**
     * Creates API URL to request Jenkins' job builds data.
     *
     * @return URL string.
     */
    private String request() {
        return String.format(
            "%s%s", this.api, "/build&wrapper=builds"
        );
    }
}
