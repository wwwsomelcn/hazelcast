/*
 * Copyright (c) 2008-2013, Hazelcast, Inc. All Rights Reserved.
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

package com.hazelcast.config;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(com.hazelcast.util.RandomBlockJUnit4ClassRunner.class)
public class InterfacesTest {

    final String interfaceA = "127.0.0.1";
    final String interfaceB = "127.0.0.2";
    final String interfaceC = "127.0.0.3";

    @Test
    public void testIsEnabledByDefault() {
        Interfaces interfaces = new Interfaces();
        assertFalse(interfaces.isEnabled());
    }

    @Test
    public void testSetEnabled() {
        Interfaces interfaces = new Interfaces().setEnabled(true);
        assertTrue(interfaces.isEnabled());
    }

    @Test
    public void testAddInterface() {
        Interfaces interfaces = new Interfaces().addInterface(interfaceA);
        assertTrue(interfaces.getInterfaces().contains(interfaceA));
    }

    @Test
    public void testClear() {
        Interfaces interfaces = new Interfaces()
                .addInterface(interfaceA)
                .addInterface(interfaceB)
                .addInterface(interfaceC);
        assertTrue(interfaces.getInterfaces().size() == 3);
        interfaces.clear();
        assertTrue(interfaces.getInterfaces().size() == 0);
    }

    @Test
    public void testGetInterfaceList() {
        Interfaces interfaces = new Interfaces();
        assertNotNull(interfaces.getInterfaces());
    }

    @Test
    public void testSetInterfaceList() {
        List<String> interfaceList = new ArrayList<String>();
        interfaceList.add(interfaceA);
        interfaceList.add(interfaceB);
        interfaceList.add(interfaceC);
        Interfaces interfaces = new Interfaces().setInterfaces(interfaceList);
        assertTrue(interfaces.getInterfaces().contains(interfaceA));
        assertTrue(interfaces.getInterfaces().contains(interfaceB));
        assertTrue(interfaces.getInterfaces().contains(interfaceC));
    }

    @Test
    public void shouldNotContainDuplicateInterfaces() {
        Interfaces interfaces = new Interfaces().addInterface(interfaceA);
        assertTrue(interfaces.getInterfaces().size() == 1);
        interfaces.addInterface(interfaceA);
        assertTrue(interfaces.getInterfaces().size() == 1);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldNotBeModifiable() {
        new Interfaces()
                .addInterface(interfaceA)
                .getInterfaces()
                .clear();
    }
}
