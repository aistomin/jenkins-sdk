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
package com.github.aistomin.xml;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Test for XPath.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class XPathTest {

    /**
     * Expected exception rule.
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * Can read field that exists in XML.
     *
     * @throws Exception If error occurred.
     */
    @Test
    public void testCanReadExistingField() throws Exception {
        MatcherAssert.assertThat(
            new XPath("//field/text()").valueFrom(
                "<root><field>value123</field></root>"
            ),
            new IsEqual<String>("value123")
        );
    }

    /**
     * Can not read field that doesn't exist in XML.
     *
     * @throws Exception If error occurred.
     */
    @Test
    public void testCanNotReadNotExistingField() throws Exception {
        this.thrown.expectMessage("Field not found in build's XML.");
        new XPath("//notexisting/text()").valueFrom(
            "<root><existing>value123</existing></root>"
        );
    }
}
