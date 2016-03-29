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
import org.apache.commons.lang3.NotImplementedException;

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

    /**
     * Fake builds iterator.
     *
     * @return Fake builds iterator.
     * @throws Exception If something goes wrong.
     */
    public Iterator<Build> iterator() throws Exception {
        return this.list.iterator();
    }

    /**
     * Last successful fake build.
     *
     * @return Last successful build.
     * @throws Exception If something goes wrong.
     */
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

    /**
     * Last failed fake build.
     *
     * @return Last failed build.
     * @throws Exception If something goes wrong.
     */
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

    /**
     * Last stable fake build.
     *
     * @return Last stable build.
     * @throws Exception If something goes wrong.
     */
    public Build lastStable() throws Exception {
        return this.lastSuccessful();
    }

    /**
     * Last unstable fake build.
     *
     * @return Last unstable build.
     * @throws Exception If something goes wrong.
     */
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

    /**
     * Find fake build by number.
     *
     * @param number Build's number.
     * @return Build.
     * @throws Exception If something goes wrong.
     */
    public Iterator<Build> findByNumber(final String number) throws Exception {
        final List<Build> result = new ArrayList<Build>(1);
        for (final Build job : this.list) {
            if (number.equals(job.number())) {
                result.add(job);
            }
        }
        return result.iterator();
    }

    /**
     * Find build by Git revision.
     *
     * @param rev Git revision.
     * @return Build.
     * @throws Exception If something goes wrong.
     * @todo: Let's implement this method and resolve issue #264.
     */
    public Iterator<Build> findByGitRevision(
        final String rev
    ) throws Exception {
        throw new NotImplementedException(
            String.format(
                "%s.gitRevision() is not implemented.", this.getClass()
            )
        );
    }

    /**
     * Fake builds' XML representation.
     *
     * @return XML's string.
     * @throws Exception If something goes wrong.
     */
    public String xml() throws Exception {
        return this.content.content();
    }

    /**
     * Create default builds list.
     *
     * @return List of builds.
     */
    private static List<Build> defaultBuilds() {
        final ArrayList<Build> builds = new ArrayList<Build>(2);
        builds.add(new FakeBuild());
        builds.add(new FakeBuild());
        return builds;
    }
}
