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

import com.github.aistomin.jenkins.BuildDetails;

/**
 * Jenkins' job build details like: display name, url, duration etc.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class RealBuildDetails implements BuildDetails {

    /**
     * Full display name.
     */
    private final transient String fname;

    /**
     * Normal display name.
     */
    private final transient String nname;

    /**
     * Estimated duration in milliseconds.
     */
    private final transient Long est;

    /**
     * Duration in milliseconds.
     */
    private final transient Long dur;

    /**
     * Is build in process?
     */
    private final transient Boolean inproc;

    /**
     * Build's queue ID.
     */
    private final transient Long number;

    /**
     * Ctor.
     *
     * @param full Full display name.
     * @param normal Normal display name.
     * @param estimation Estimated duration in milliseconds.
     * @param duration Duration in milliseconds.
     * @param building Is build in process?
     * @param queue Build's queue ID.
     * @checkstyle ParameterNumberCheck (50 lines)
     */
    public RealBuildDetails(
        final String full, final String normal, final Long estimation,
        final Long duration, final Boolean building, final Long queue
    ) {
        this.fname = full;
        this.nname = normal;
        this.est = estimation;
        this.dur = duration;
        this.inproc = building;
        this.number = queue;
    }

    /**
     * Build's full display name.
     *
     * @return Full display name.
     */
    public String fullDisplayName() {
        return this.fname;
    }

    /**
     * Build's display name.
     *
     * @return Display name.
     */
    public String displayName() {
        return this.nname;
    }

    /**
     * Build's estimated duration in milliseconds.
     *
     * @return Build's estimated duration.
     */
    public Long estimated() {
        return this.est;
    }

    /**
     * Build's duration in milliseconds.
     *
     * @return Build's duration.
     */
    public Long duration() {
        return this.dur;
    }

    /**
     * Is build in process?
     *
     * @return Is build in process?
     */
    public Boolean building() {
        return this.inproc;
    }

    /**
     * Build's queue ID.
     *
     * @return Queue ID.
     */
    public Long queue() {
        return this.number;
    }
}
