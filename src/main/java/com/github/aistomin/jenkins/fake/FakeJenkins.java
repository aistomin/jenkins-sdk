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

import com.github.aistomin.jenkins.Jenkins;
import com.github.aistomin.jenkins.Jobs;
import com.github.aistomin.jenkins.Users;
import com.github.aistomin.xml.Xml;
import com.github.aistomin.xml.XmlResource;

/**
 * Fake Jenkins instance for tests.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class FakeJenkins implements Jenkins {

    /**
     * XML resource file name.
     */
    private static final String RESOURCE = "jenkins.xml";

    /**
     * Users instance that should be returned in users() method.
     */
    private final transient Users usrs;

    /**
     * XML content that should be returned in xml() method.
     */
    private final transient Xml content;

    /**
     * Fake Jenkins version.
     */
    private final transient String ver;

    /**
     * Restart callback.
     */
    private final Runnable cbrestart;

    /**
     * Default ctor. Sets all the defaults.
     *
     * @throws Exception If something goes wrong.
     */
    public FakeJenkins() throws Exception {
        this(
            new FakeUsers(), new XmlResource(FakeJenkins.RESOURCE),
            new DoNothing()
        );
    }

    /**
     * Secondary ctor.
     *
     * @param users Jobs instance that should be returned in jobs() method.
     * @throws Exception If something goes wrong.
     */
    public FakeJenkins(final Users users) throws Exception {
        this(
            users, new XmlResource(FakeJenkins.RESOURCE), new DoNothing()
        );
    }

    /**
     * Secondary ctor.
     *
     * @param xml XML content that should be returned in xml() method.
     * @throws Exception If something goes wrong.
     */
    public FakeJenkins(final Xml xml) throws Exception {
        this(new FakeUsers(), xml, new DoNothing());
    }

    /**
     * Secondary ctor.
     *
     * @param onrestart Restart callback.
     * @throws Exception If something goes wrong.
     */
    public FakeJenkins(final Runnable onrestart) throws Exception {
        this(
            new FakeUsers(), new XmlResource(FakeJenkins.RESOURCE), onrestart
        );
    }

    /**
     * Primary ctor.
     *
     * @param users Users instance that should be returned in users() method.
     * @param xml XML content that should be returned in xml() method.
     * @param onrestart Restart callback.
     */
    public FakeJenkins(
        final Users users, final Xml xml, final Runnable onrestart
    ) {
        this.usrs = users;
        this.content = xml;
        this.ver = "1.642.2";
        this.cbrestart = onrestart;
    }

    /**
     * Return test jobs instance that was set via ctor.
     *
     * @return Jobs.
     * @throws Exception If reading jobs was not successful.
     */
    public Jobs jobs() throws Exception {
        return new FakeJobs(this.content);
    }

    /**
     * Return test users instance that was set via ctor.
     *
     * @return Users
     * @throws Exception If reading users was not successful.
     */
    public Users users() throws Exception {
        return this.usrs;
    }

    /**
     * Fake Jenkins' version.
     *
     * @return Version.
     * @throws Exception If reading users was not successful.
     */
    public String version() throws Exception {
        return this.ver;
    }

    /**
     * Restart Jenkins.
     * @throws Exception If reading users was not successful.
     */
    public void restart() throws Exception {
        this.cbrestart.run();
    }

    /**
     * Return XML content that was set in ctor.
     *
     * @return XML string.
     * @throws Exception If something goes wrong.
     */
    public String xml() throws Exception {
        return this.content.content();
    }
}
