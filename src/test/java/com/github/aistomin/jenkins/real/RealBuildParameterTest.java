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

import com.github.aistomin.jenkins.BuildParameter;
import com.github.aistomin.xml.XmlString;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

/**
 * Test for {@link RealBuildParameter}.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.2
 */
public final class RealBuildParameterTest {

    /**
     * Can read build parameter's details.
     * @throws Exception If something is wrong.
     */
    @Test
    public void testCanRead() throws Exception {
        final BuildParameter parameter = new RealBuildParameter(
            new XmlString(
                new StringBuilder()
                    .append("<parameter>")
                    .append("<name>niceParameter</name>")
                    .append("<value>some_value</value>")
                    .append("</parameter>")
                    .toString()
            )
        );
        MatcherAssert.assertThat(
            parameter.name(), new IsEqual<String>("niceParameter")
        );
        MatcherAssert.assertThat(
            parameter.value(), new IsEqual<String>("some_value")
        );
    }
}
