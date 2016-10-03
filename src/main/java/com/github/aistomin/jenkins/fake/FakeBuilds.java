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

import com.github.aistomin.jenkins.Build;
import com.github.aistomin.jenkins.BuildResult;
import com.github.aistomin.jenkins.Builds;
import com.github.aistomin.xml.Xml;
import com.github.aistomin.xml.XmlResource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Fake Jenkins' builds for tests.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class FakeBuilds implements Builds {

    /**
     * XML resource file name.
     */
    private static final String RESOURCE = "builds.xml";

    /**
     * XML content that should be returned in xml() method.
     */
    private final transient Xml content;

    /**
     * List of builds that will be used in iterator() and findByNumber()
     *  methods.
     */
    private final transient List<Build> list;

    /**
     * Default ctor.
     */
    public FakeBuilds() {
        this(new XmlResource(FakeBuilds.RESOURCE), FakeBuilds.defaultBuilds());
    }

    /**
     * Secondary ctor.
     *
     * @param xml XML content that should be returned in xml() method.
     */
    public FakeBuilds(final Xml xml) {
        this(xml, FakeBuilds.defaultBuilds());
    }

    /**
     * Secondary ctor.
     *
     * @param builds List of builds that will be used in iterator() and \
     *  findByNumber() methods.
     */
    public FakeBuilds(final List<Build> builds) {
        this(new XmlResource(FakeBuilds.RESOURCE), builds);
    }

    /**
     * Primary ctor.
     *
     * @param xml XML content that should be returned in xml() method.
     * @param builds List of builds that will be used in iterator() and
     *  findByNumber() methods.
     */
    public FakeBuilds(final Xml xml, final List<Build> builds) {
        this.content = xml;
        this.list = builds;
    }

    @Override
    public Iterator<Build> iterator() throws Exception {
        return this.list.iterator();
    }

    @Override
    public Build lastSuccessful() throws Exception {
        Build result = null;
        for (final Build build : this.list) {
            if (build.result() == BuildResult.SUCCESS) {
                result = build;
                break;
            }
        }
        return result;
    }

    @Override
    public Build lastFailed() throws Exception {
        Build result = null;
        for (final Build build : this.list) {
            if (build.result() == BuildResult.FAILURE) {
                result = build;
                break;
            }
        }
        return result;
    }

    @Override
    public Build lastStable() throws Exception {
        return this.lastSuccessful();
    }

    @Override
    public Build lastUnsuccessful() throws Exception {
        Build result = null;
        for (final Build build : this.list) {
            if (build.result() != BuildResult.SUCCESS) {
                result = build;
                break;
            }
        }
        return result;
    }

    @Override
    public Iterator<Build> findByNumber(final String number) throws Exception {
        final List<Build> result = new ArrayList<>(1);
        for (final Build job : this.list) {
            if (number.equals(job.number())) {
                result.add(job);
            }
        }
        return result.iterator();
    }

    @Override
    public Iterator<Build> findByGitRevision(
        final String rev
    ) throws Exception {
        final List<Build> result = new ArrayList<>(1);
        for (final Build job : this.list) {
            if (rev.equals(job.gitRevision())) {
                result.add(job);
            }
        }
        return result.iterator();
    }

    @Override
    public String xml() throws Exception {
        return this.content.content();
    }

    /**
     * Create default builds list.
     *
     * @return List of builds.
     */
    private static List<Build> defaultBuilds() {
        final ArrayList<Build> builds = new ArrayList<>(2);
        builds.add(new FakeBuild());
        builds.add(new FakeBuild());
        return builds;
    }
}
