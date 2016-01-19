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
import org.rising.jenkins.Users;

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
     * Users instance that should be returned in users() method.
     */
    private final transient Users usrs;

    /**
     * Ctor.
     * @param jobs Jobs instance that should be returned in jobs() method.
     * @param users Users instance that should be returned in users() method.
     */
    public FakeJenkins(final Jobs jobs, final Users users) {
        this.projects = jobs;
        this.usrs = users;
    }

    /**
     * Return test jobs instance that was set via ctor.
     * @return Jobs.
     * @throws Exception If reading jobs was not successful.
     */
    public Jobs jobs() throws Exception {
        return this.projects;
    }

    /**
     * Return test users instance that was set via ctor.
     * @return Users
     * @throws Exception If reading users was not successful.
     */
    public Users users() throws Exception {
        return this.usrs;
    }
}
