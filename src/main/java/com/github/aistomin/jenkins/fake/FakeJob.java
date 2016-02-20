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

import com.github.aistomin.jenkins.Builds;
import com.github.aistomin.jenkins.Job;
import com.github.aistomin.jenkins.JobDetails;
import com.github.aistomin.jenkins.JobParameter;
import com.github.aistomin.xml.Xml1;
import com.github.aistomin.xml.XmlResource;
import java.util.Iterator;
import org.apache.commons.lang3.NotImplementedException;

/**
 * Fake jenkins' job.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class FakeJob implements Job {

    /**
     * XML resource file name.
     */
    private static final String RESOURCE = "job.xml";

    /**
     * Unique job identifier.
     */
    private final transient String identifier;

    /**
     * XML content that should be returned in xml() method.
     */
    private final transient Xml1 content;

    /**
     * Default ctor.
     */
    public FakeJob() {
        this(FakeJob.defaultName(), new XmlResource(FakeJob.RESOURCE));
    }

    /**
     * Secondary ctor.
     *
     * @param xml XML content that should be returned in xml() method.
     */
    public FakeJob(final Xml1 xml) {
        this(FakeJob.defaultName(), xml);
    }

    /**
     * Secondary ctor.
     *
     * @param name Job name.
     */
    public FakeJob(final String name) {
        this(name, new XmlResource(FakeJob.RESOURCE));
    }

    /**
     * Primary ctor.
     *
     * @param name Job name.
     * @param xml XML content that should be returned in xml() method.
     */
    public FakeJob(final String name, final Xml1 xml) {
        this.identifier = name;
        this.content = xml;
    }

    /**
     * Job name.
     *
     * @return Job name.
     * @throws Exception If something goes wrong.
     */
    public String name() throws Exception {
        return this.identifier;
    }

    /**
     * Fake job details.
     *
     * @return Job details.
     * @throws Exception If something goes wrong.
     * @todo: Let's implement this method and solve Issue #54.
     */
    public JobDetails details() throws Exception {
        throw new NotImplementedException(
            String.format(
                "details() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Fake job URL.
     *
     * @return URL string.
     * @throws Exception If something goes wrong.
     * @todo: Let's implement this method and solve Issue #55.
     */
    public String url() throws Exception {
        throw new NotImplementedException(
            String.format(
                "url() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Fake job builds.
     *
     * @return Builds.
     * @throws Exception If error occurred.
     * @todo: Let's implement this method and solve Issue #56.
     */
    public Builds builds() throws Exception {
        throw new NotImplementedException(
            String.format(
                "builds() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Fake job parameters.
     *
     * @return Job's parameters.
     * @throws Exception If something goes wrong.
     * @todo: Let's implement this method and solve Issue #57.
     */
    public Iterator<JobParameter> parameters() throws Exception {
        throw new NotImplementedException(
            String.format(
                "parameters() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Fake job's XML representation.
     *
     * @return XML's string.
     * @throws Exception If something goes wrong.
     * @todo: Let's implement this method and solve Issue #58.
     */
    public String xml() throws Exception {
        return this.content.content();
    }

    /**
     * Construct default job name.
     * @return Default job name.
     */
    private static String defaultName() {
        return String.format("job%d", System.currentTimeMillis());
    }
}
