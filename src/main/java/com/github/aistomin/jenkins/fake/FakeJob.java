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
import com.github.aistomin.jenkins.real.RealJobParameter;
import com.github.aistomin.xml.Xml;
import com.github.aistomin.xml.XmlResource;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
    private final transient Xml content;

    /**
     * Job details that will be returned in details() method.
     */
    private final transient JobDetails detailed;

    /**
     * Job's URL that will be returned in url() method.
     */
    private final transient String uri;

    /**
     * Job's builds that will be returned in builds() method.
     */
    private final transient Builds bds;

    /**
     * Job's parameters that will be returned in parameters() method.
     */
    private final transient List<JobParameter> params;

    /**
     * Default ctor.
     */
    public FakeJob() {
        this(
            FakeJob.defaultName(), new XmlResource(FakeJob.RESOURCE),
            new RealJobDetails(new XmlResource(FakeJob.RESOURCE)),
            FakeJob.defaultUrl(), new FakeBuilds(), FakeJob.defaultParams()
        );
    }

    /**
     * Secondary ctor.
     *
     * @param xml XML content that should be returned in xml() method.
     */
    public FakeJob(final Xml xml) {
        this(
            FakeJob.defaultName(), xml, new RealJobDetails(xml),
            FakeJob.defaultUrl(), new FakeBuilds(), FakeJob.defaultParams()
        );
    }

    /**
     * Secondary ctor.
     *
     * @param name Job name.
     */
    public FakeJob(final String name) {
        this(
            name, new XmlResource(FakeJob.RESOURCE),
            new RealJobDetails(new XmlResource(FakeJob.RESOURCE)),
            FakeJob.defaultUrl(), new FakeBuilds(), FakeJob.defaultParams()
        );
    }

    /**
     * Secondary ctor.
     *
     * @param details Job details that will be returned in details() method.
     */
    public FakeJob(final JobDetails details) {
        this(
            FakeJob.defaultName(), new XmlResource(FakeJob.RESOURCE),
            details, FakeJob.defaultUrl(), new FakeBuilds(),
            FakeJob.defaultParams()
        );
    }

    /**
     * Secondary ctor.
     *
     * @param url Job's URL that will be returned in url() method.
     */
    public FakeJob(final URL url) {
        this(
            FakeJob.defaultName(), new XmlResource(FakeJob.RESOURCE),
            new RealJobDetails(new XmlResource(FakeJob.RESOURCE)),
            url.toString(), new FakeBuilds(), FakeJob.defaultParams()
        );
    }

    /**
     * Secondary ctor.
     *
     * @param builds Job's builds that will be returned in builds() method.
     */
    public FakeJob(final Builds builds) {
        this(
            FakeJob.defaultName(), new XmlResource(FakeJob.RESOURCE),
            new RealJobDetails(new XmlResource(FakeJob.RESOURCE)),
            FakeJob.defaultUrl(), builds, FakeJob.defaultParams()
        );
    }

    /**
     * Secondary ctor.
     *
     * @param parameters Job's parameters that will be returned in parameters()
     *  method.
     */
    public FakeJob(final List<JobParameter> parameters) {
        this(
            FakeJob.defaultName(), new XmlResource(FakeJob.RESOURCE),
            new RealJobDetails(new XmlResource(FakeJob.RESOURCE)),
            FakeJob.defaultUrl(), new FakeBuilds(), parameters
        );
    }

    /**
     * Primary ctor.
     *
     * @param name Job name.
     * @param xml XML content that should be returned in xml() method.
     * @param details Job details that will be returned in details() method.
     * @param url Job's URL that will be returned in url() method.
     * @param builds Job's builds that will be returned in builds() method.
     * @param parameters Job's parameters that will be returned in parameters()
     *  method.
     * @checkstyle ParameterNumberCheck (500 lines)
     */
    public FakeJob(
        final String name, final Xml xml, final JobDetails details,
        final String url, final Builds builds,
        final List<JobParameter> parameters
    ) {
        this.identifier = name;
        this.content = xml;
        this.detailed = details;
        this.uri = url;
        this.bds = builds;
        this.params = parameters;
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
     */
    public JobDetails details() throws Exception {
        return this.detailed;
    }

    /**
     * Fake job URL.
     *
     * @return URL string.
     * @throws Exception If something goes wrong.
     */
    public String url() throws Exception {
        return this.uri;
    }

    /**
     * Fake job builds.
     *
     * @return Builds.
     * @throws Exception If error occurred.
     */
    public Builds builds() throws Exception {
        return this.bds;
    }

    /**
     * Fake job parameters.
     *
     * @return Job's parameters.
     * @throws Exception If something goes wrong.
     */
    public Iterator<JobParameter> parameters() throws Exception {
        return this.params.iterator();
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

    /**
     * Construct default job name.
     *
     * @return Default job name.
     */
    private static String defaultName() {
        return String.format("job%d", System.currentTimeMillis());
    }

    /**
     * Construct default job URL.
     *
     * @return Default job  URL.
     */
    private static String defaultUrl() {
        return String.format("http://my-jenkins.com/%s", FakeJob.defaultName());
    }

    /**
     * Construct default job parameters.
     *
     * @return Default job  URL.
     */
    private static List<JobParameter> defaultParams() {
        final List<JobParameter> params = new ArrayList<JobParameter>(1);
        params.add(new RealJobParameter(new XmlResource("job-param.xml")));
        return params;
    }
}
