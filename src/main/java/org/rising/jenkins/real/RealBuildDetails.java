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
package org.rising.jenkins.real;

import org.apache.commons.lang3.NotImplementedException;
import org.rising.jenkins.BuildDetails;

/**
 * Jenkins' job build details like: display name, url, duration etc.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class RealBuildDetails implements BuildDetails {

    /**
     * Build's full display name.
     *
     * @return Full display name.
     * @todo: Let's implement this method and solve Issue #130.
     */
    public String fullDisplayName() {
        throw new NotImplementedException(
            String.format(
                "fullDisplayName() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Build's display name.
     *
     * @return Display name.
     * @todo: Let's implement this method and solve Issue #131.
     */
    public String displayName() {
        throw new NotImplementedException(
            String.format(
                "displayName() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Build's estimated duration in milliseconds.
     *
     * @return Build's estimated duration.
     * @todo: Let's implement this method and solve Issue #132.
     */
    public Long estimated() {
        throw new NotImplementedException(
            String.format(
                "estimated() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Build's duration in milliseconds.
     *
     * @return Build's duration.
     * @todo: Let's implement this method and solve Issue #133.
     */
    public Long duration() {
        throw new NotImplementedException(
            String.format(
                "duration() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Is build in process?
     *
     * @return Is build in process?
     * @todo: Let's implement this method and solve Issue #134.
     */
    public Boolean building() {
        throw new NotImplementedException(
            String.format(
                "building() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Build's queue ID.
     *
     * @return Queue ID.
     * @todo: Let's implement this method and solve Issue #135.
     */
    public Long queue() {
        throw new NotImplementedException(
            String.format(
                "queue() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Build details' XML representation.
     *
     * @return XML's string.
     * @throws Exception If something goes wrong.
     * @todo: Let's implement this method and solve Issue #136.
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
