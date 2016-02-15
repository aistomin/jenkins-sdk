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

import com.github.aistomin.jenkins.BuildDetails;
import com.github.aistomin.xml.XMLResource;
import org.junit.Assert;
import org.junit.Test;

/**
 * Integration tests for RealBuildDetails class.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class RealBuildDetailsTest {

    /**
     * Can read build's details.
     *
     * @throws Exception If something is not OK.
     */
    @Test
    public void testCanReadDetails() throws Exception {
        final BuildDetails details = new RealBuildDetails(
            new XMLResource("build.xml")
        );
        Assert.assertEquals(
            "test-different-builds-job #1", details.fullDisplayName()
        );
        Assert.assertEquals("#1", details.displayName());
        Assert.assertEquals(Long.parseLong("389"), (long) details.estimated());
        Assert.assertEquals(Long.parseLong("687"), (long) details.duration());
        Assert.assertFalse(details.building());
        Assert.assertEquals(1L, (long) details.queue());
    }
}
