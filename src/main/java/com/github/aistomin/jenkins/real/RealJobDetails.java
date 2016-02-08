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

import com.github.aistomin.jenkins.JobDetails;

/**
 * Jenkins job details like: display name, description etc.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class RealJobDetails implements JobDetails {

    /**
     * Job's display name.
     */
    private final transient String display;

    /**
     * Job's description.
     */
    private final transient String desc;

    /**
     * Is job buildable?
     */
    private final transient Boolean enabled;

    /**
     * Ctor.
     *
     * @param name Job's display name.
     * @param description Job's description.
     * @param buildable Is job buildable?
     */
    public RealJobDetails(
        final String name, final String description,
        final Boolean buildable
    ) {
        this.display = name;
        this.desc = description;
        this.enabled = buildable;
    }

    /**
     * Job's display name.
     *
     * @return Display name.
     */
    public String displayName() {
        return this.display;
    }

    /**
     * Job's description.
     *
     * @return Description.
     */
    public String description() {
        return this.desc;
    }

    /**
     * Is job buildable?
     *
     * @return Is job buildable.
     */
    public Boolean buildable() {
        return this.enabled;
    }
}
