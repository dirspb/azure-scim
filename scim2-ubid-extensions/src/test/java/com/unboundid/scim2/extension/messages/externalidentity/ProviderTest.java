/*
 * Copyright 2015 UnboundID Corp.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License (GPLv2 only)
 * or the terms of the GNU Lesser General Public License (LGPLv2.1 only)
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see <http://www.gnu.org/licenses>.
 */

package com.unboundid.scim2.extension.messages.externalidentity;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.unboundid.scim2.common.utils.JsonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.URL;

@Test
public class ProviderTest
{
  /**
   * Tests serialization of Provider objects.
   *
   * @throws Exception if an error occurs.
   */
  @Test
  public void testSerialization() throws Exception
  {
    String name = "testName";
    String description = "testDescription";
    String iconUrl = "https://localhost:12345/test/url";
    String type = "testType";

    ObjectNode objectNode = JsonUtils.getJsonNodeFactory().objectNode();
    objectNode.put("name", name);
    objectNode.put("description", description);
    objectNode.put("iconUrl", iconUrl);
    objectNode.put("type", type);

    Provider provider1 = JsonUtils.getObjectReader().forType(Provider.class).
        readValue(objectNode.toString());
    Assert.assertEquals(name, provider1.getName());
    Assert.assertEquals(description, provider1.getDescription());
    Assert.assertEquals(type, provider1.getType());
    Assert.assertEquals(new URL(iconUrl), provider1.getIconUrl());

    Provider provider2 =
        JsonUtils.getObjectReader().forType(Provider.class).readValue(
            JsonUtils.getObjectWriter().writeValueAsString(provider1));
    Assert.assertEquals(provider1, provider2);
  }
}