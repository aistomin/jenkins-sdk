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
import com.github.aistomin.jenkins.real.RealJobDetails;
import com.github.aistomin.xml.Xml;
import com.github.aistomin.xml.XmlResource;
import java.util.Iterator;
import java.util.Map;
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
     * XML content that should be returned in xml() method.
     */
    private final transient Xml content;

    /**
     * Action that will be done on build trigger.
     */
    private final transient Runnable cbtrigger;

    /**
     * Default ctor.
     */
    public FakeJob() {
        this(new XmlResource("job.xml"), new DoNothing());
    }

    /**
     * Primary ctor.
     *
     * @param xml XML content that should be returned in xml() method.
     *  method.
     * @param ontrigger Action that will be done on build trigger.
     */
    public FakeJob(final Xml xml, final Runnable ontrigger) {
        this.content = xml;
        this.cbtrigger = ontrigger;
    }

    /**
     * Job name.
     *
     * @return Job name.
     * @throws Exception If something goes wrong.
     */
    public String name() throws Exception {
        return this.content.field("//job/displayName/text()");
    }

    /**
     * Fake job details.
     *
     * @return Job details.
     * @throws Exception If something goes wrong.
     */
    public JobDetails details() throws Exception {
        return new RealJobDetails(this.content);
    }

    /**
     * Fake job URL.
     *
     * @return URL string.
     * @throws Exception If something goes wrong.
     */
    public String url() throws Exception {
        return this.content.field("//job/url/text()");
    }

    /**
     * Fake job builds.
     *
     * @return Builds.
     * @throws Exception If error occurred.
     * @todo: Let's implement this method in issue #284
     */
    public Builds builds() throws Exception {
        throw new NotImplementedException(
            String.format(
                "%s.builds() is not implemented.", this.getClass()
            )
        );
    }

    /**
     * Fake job parameters.
     *
     * @return Job's parameters.
     * @throws Exception If something goes wrong.
     * @todo: Let's implement this method in issue #284
     */
    public Iterator<JobParameter> parameters() throws Exception {
        throw new NotImplementedException(
            String.format(
                "%s.parameters() is not implemented.", this.getClass()
            )
        );
    }

    /**
     * Fake job triggering imitation.
     *
     * @throws Exception If something goes wrong.
     */
    public void trigger() throws Exception {
        this.cbtrigger.run();
    }

    /**
     * Trigger new job's build.
     *
     * @param parameters Build parameters map.
     * @throws Exception If something goes wrong.
     * @todo: Let's implement this method and solve issue #270.
     */
    public void trigger(final Map<String, String> parameters) throws Exception {
        throw new NotImplementedException(
            String.format(
                "%s.trigger() is not implemented.", this.getClass()
            )
        );
    }

    /**
     * Fake job's XML representation.
     *
     * @return XML's string.
     * @throws Exception If something goes wrong.
     */
    public String xml() throws Exception {
        return this.content.content();
    }
}
