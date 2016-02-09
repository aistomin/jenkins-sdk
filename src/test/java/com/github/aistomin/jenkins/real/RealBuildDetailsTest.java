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
import java.util.Random;
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
    public void testCnReadDetails() throws Exception {
        final Random random = new Random();
        final String full = "full job name";
        final String normal = "normal job name";
        final Long estimation = Math.abs(random.nextLong());
        final Long duration = Math.abs(random.nextLong());
        final Boolean building = random.nextBoolean();
        final Long queue = Math.abs(random.nextLong());
        final BuildDetails details = new RealBuildDetails(
            full, normal, estimation, duration, building, queue
        );
        Assert.assertEquals(full, details.fullDisplayName());
        Assert.assertEquals(normal, details.displayName());
        Assert.assertEquals(estimation, details.estimated());
        Assert.assertEquals(duration, details.duration());
        Assert.assertEquals(building, details.building());
        Assert.assertEquals(queue, details.queue());
    }
}
