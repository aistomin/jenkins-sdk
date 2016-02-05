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
import com.github.aistomin.jenkins.Build;
import com.github.aistomin.jenkins.Builds;
import com.github.aistomin.jenkins.Credentials;
import java.util.Iterator;
import org.apache.commons.lang3.NotImplementedException;

/**
 * Jenkins' job builds.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 1.0
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
     * @todo: Let's implement this method and solve Issue #105.
     */
    public Iterator<Builds> iterator() throws Exception {
        throw new NotImplementedException(
            String.format(
                "iterator() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Last successful build.
     *
     * @return Last successful build.
     * @throws Exception If reading the build was not successful.
     * @todo: Let's implement this method and solve Issue #106.
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
     * @todo: Let's implement this method and solve Issue #108.
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
