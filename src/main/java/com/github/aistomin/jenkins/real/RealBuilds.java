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
import com.github.aistomin.xml.XmlString;
import com.jcabi.xml.XMLDocument;
import java.net.URLEncoder;
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
        final List<String> builds = RealBuilds.parseBuild(this.xml());
        Collections.sort(builds, String.CASE_INSENSITIVE_ORDER);
        return new EntityIterator<>(
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
            new XmlString(
                new PostRequest(this.api, this.creds.headers()).execute()
            ).field(
                "//lastSuccessfulBuild/displayName/text()"
            ), this.api, this.creds
        );
    }

    /**
     * Last failed build.
     *
     * @return Last failed build.
     * @throws Exception If reading the build was not successful.
     */
    public Build lastFailed() throws Exception {
        return new RealBuild(
            new XmlString(
                new PostRequest(this.api, this.creds.headers()).execute()
            ).field(
                "//lastFailedBuild/displayName/text()"
            ), this.api, this.creds
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
            new XmlString(
                new PostRequest(this.api, this.creds.headers()).execute()
            ).field(
                "//lastStableBuild/displayName/text()"
            ), this.api, this.creds
        );
    }

    /**
     * Last unstable build.
     *
     * @return Last unstable build.
     * @throws Exception If reading the build was not successful.
     */
    public Build lastUnsuccessful() throws Exception {
        return new RealBuild(
            new XmlString(
                new PostRequest(this.api, this.creds.headers()).execute()
            ).field(
                "//lastUnsuccessfulBuild/displayName/text()"
            ), this.api, this.creds
        );
    }

    /**
     * Find build by number.
     *
     * @param number Build's number.
     * @return Build.
     * @throws Exception If reading the build was not successful.
     */
    public Iterator<Build> findByNumber(final String number) throws Exception {
        return new EntityIterator<>(
            RealBuilds.parseBuild(
                new PostRequest(
                    this.url(
                        String.format(
                            "/build[displayName='%s']",
                            URLEncoder.encode(number, "UTF-8")
                        )
                    ), this.creds.headers()
                ).execute()
            ).iterator(), new RealBuild.Transformer(this.api, this.creds)
        );
    }

    /**
     * Find build by Git revision.
     *
     * @param rev Git revision.
     * @return Build.
     * @throws Exception If something goes wrong.
     * @todo: Let's implement this method and resolve issue #265.
     */
    public Iterator<Build> findByGitRevision(
        final String rev
    ) throws Exception {
        throw new NotImplementedException(
            String.format(
                "%s.gitRevision() is not implemented.", this.getClass()
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
        return new PostRequest(
            this.url("/build&wrapper=builds"), this.creds.headers()
        ).execute();
    }

    /**
     * Create full Jenkins API URL.
     * @param path URL path.
     * @return URL string.
     */
    private String url(final String path) {
        return String.format("%s%s", this.api, path);
    }

    /**
     * Parse users from XML.
     *
     * @param xml XML string.
     * @return Parsed users names.
     * @throws Exception If something goes wrong.
     */
    private static List<String> parseBuild(final String xml) throws Exception {
        final List<String> builds = new XMLDocument(xml).xpath(
            "//build/displayName/text()"
        );
        Collections.sort(builds, String.CASE_INSENSITIVE_ORDER);
        return builds;
    }
}
