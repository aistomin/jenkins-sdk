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

import com.github.aistomin.jenkins.BuildDetails;
import org.apache.commons.lang3.NotImplementedException;

/**
 * Fake Jenkins' job build details like: display name, url, duration etc.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class FakeBuildDetails implements BuildDetails {

    /**
     * Fake build's full display name.
     *
     * @return Full display name.
     * @todo: Let's implement this method and solve Issue #123.
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
     * Fake build's display name.
     *
     * @return Display name.
     * @todo: Let's implement this method and solve Issue #124.
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
     * Fake build's estimated duration in milliseconds.
     *
     * @return Build's estimated duration.
     * @todo: Let's implement this method and solve Issue #125.
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
     * Fake build's duration in milliseconds.
     *
     * @return Build's duration.
     * @todo: Let's implement this method and solve Issue #126.
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
     * Fake "in process" value.
     *
     * @return Is build in process?
     * @todo: Let's implement this method and solve Issue #127.
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
     * Fake build's queue ID.
     *
     * @return Queue ID.
     * @todo: Let's implement this method and solve Issue #128.
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
     * Fake build details' XML representation.
     *
     * @return XML's string.
     * @throws Exception If something goes wrong.
     * @todo: Let's implement this method and solve Issue #129.
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
