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

import com.github.aistomin.jenkins.JobParameter;
import com.github.aistomin.xml.XMLString;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test for RealJobParameter.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class RealJobParameterTest {

    /**
     * Can read parameter's details.
     * @throws Exception If something is wrong.
     */
    @Test
    public void testCanRead() throws Exception {
        final JobParameter parameter = new RealJobParameter(
            new XMLString(
                new StringBuilder()
                    .append("<parameterDefinition>")
                    .append("<defaultParameterValue>")
                    .append("<name>niceParameter</name><value/>")
                    .append("</defaultParameterValue>")
                    .append("<description>Nice parameter</description>")
                    .append("<name>niceParameter</name>")
                    .append("<type>StringParameterDefinition</type>")
                    .append("</parameterDefinition>")
                    .toString()
            )
        );
        Assert.assertEquals("niceParameter", parameter.name());
        Assert.assertEquals("Nice parameter", parameter.description());
        Assert.assertEquals("StringParameterDefinition", parameter.type());
    }
}
