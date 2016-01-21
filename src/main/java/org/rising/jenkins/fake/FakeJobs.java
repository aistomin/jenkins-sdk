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

import java.util.List;
import org.apache.commons.lang3.NotImplementedException;
import org.rising.jenkins.Job;
import org.rising.jenkins.Jobs;
import org.rising.xml.XML;
import org.rising.xml.XMLResource;

/**
 * Fake Jenkins' jobs for tests.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class FakeJobs implements Jobs {

    /**
     * XML resource file name.
     */
    private static final String RESOURCE = "jobs.xml";

    /**
     * XML content that should be returned in xml() method.
     */
    private final transient XML content;

    /**
     * Default ctor.
     *
     * @throws Exception If reading XML was not successful.
     */
    public FakeJobs() throws Exception {
        this(new XMLResource(RESOURCE));
    }

    /**
     * Secondary ctor.
     *
     * @param xml XML content that should be returned in xml() method.
     */
    public FakeJobs(final XML xml) {
        this.content = xml;
    }

    /**
     * Return jobs that were set via ctor.
     *
     * @return List of jobs.
     * @todo: Let's implement this method and solve Issue #32.
     */
    public List<Job> list() {
        throw new NotImplementedException(
            String.format(
                "list() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Find by Jenkins' job name.
     *
     * @param name Job's name.
     * @return Job.
     * @todo: Let's implement this method and solve Issue #33.
     */
    public Job findByName(final String name) {
        throw new NotImplementedException(
            String.format(
                "findByName() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
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
