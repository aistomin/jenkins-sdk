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
package org.rising.jenkins.fake;

import org.rising.jenkins.Jenkins;
import org.rising.jenkins.Jobs;

/**
 * Fake Jenkins instance for tests.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class FakeJenkins implements Jenkins {

    /**
     * Jobs instance that should be returned in jobs() method.
     */
    private final transient Jobs projects;

    /**
     * Ctor.
     * @param jobs Jobs instance that should be returned in jobs() method.
     */
    public FakeJenkins(final Jobs jobs) {
        this.projects = jobs;
    }

    /**
     * Return test instance that was set via ctor.
     * @return Jobs.
     * @throws Exception If reading jobs was not successful.
     */
    public Jobs jobs() throws Exception {
        return this.projects;
    }
}
