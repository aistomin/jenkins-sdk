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

import com.github.aistomin.jenkins.Jenkins;
import com.github.aistomin.jenkins.Jobs;
import com.github.aistomin.jenkins.Users;

/**
 * Jenkins instance that we use for integration tests.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class TestJenkins implements Jenkins {

    /**
     * Real Jenkins instance for integration tests.
     */
    private final transient Jenkins jenkins;

    /**
     * Ctor.
     */
    public TestJenkins() {
        this.jenkins = new RealJenkins(
            "https://cisdk-istomin.rhcloud.com",
            new UsernamePasswordCredentials("integration", "2mFxM5tMVAKHMzTx")
        );
    }

    /**
     * All the jobs of test Jenkins instance.
     *
     * @return Jobs.
     * @throws Exception If reading jobs was not successful.
     */
    public Jobs jobs() throws Exception {
        return this.jenkins.jobs();
    }

    /**
     * All the registered Jenkins' users.
     *
     * @return Users.
     * @throws Exception If reading users was not successful.
     */
    public Users users() throws Exception {
        return this.jenkins.users();
    }

    /**
     * Jenkins' version.
     *
     * @return Version.
     * @throws Exception If reading users was not successful.
     */
    public String version() throws Exception {
        return this.jenkins.version();
    }

    /**
     * Jenkins' XML representation.
     *
     * @return XML's string.
     * @throws Exception If something goes wrong.
     */
    public String xml() throws Exception {
        return this.jenkins.xml();
    }
}
