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

import com.github.aistomin.jenkins.Build;
import com.github.aistomin.jenkins.BuildDetails;
import com.github.aistomin.jenkins.BuildResult;
import java.util.Date;
import org.apache.commons.lang3.NotImplementedException;

/**
 * Jenkins' job build.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class RealBuild implements Build {

    /**
     * Build's identifier.
     */
    private final transient String identifier;

    /**
     * Ctor.
     * @param number Build's number.
     */
    public RealBuild(final String number) {
        this.identifier = number;
    }

    /**
     * Build's number.
     *
     * @return Build number.
     */
    public String number() {
        return this.identifier;
    }

    /**
     * Build's result.
     *
     * @return Build's result.
     * @todo: Let's implement this method and solve Issue #158.
     */
    public BuildResult result() {
        throw new NotImplementedException(
            String.format(
                "result() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Build's date.
     *
     * @return Build's date.
     * @todo: Let's implement this method and solve Issue #159.
     */
    public Date date() {
        throw new NotImplementedException(
            String.format(
                "date() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Build's URL.
     *
     * @return URL string.
     * @todo: Let's implement this method and solve Issue #160.
     */
    public String url() {
        throw new NotImplementedException(
            String.format(
                "url() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Build's details.
     *
     * @return Build's details.
     * @todo: Let's implement this method and solve Issue #161.
     */
    public BuildDetails details() {
        throw new NotImplementedException(
            String.format(
                "details() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Jenkins build's XML representation.
     *
     * @return XML's string.
     * @throws Exception If something goes wrong.
     * @todo: Let's implement this method and solve Issue #162.
     */
    public String xml() throws Exception {
        throw new NotImplementedException(
            String.format(
                "xml() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }
}
