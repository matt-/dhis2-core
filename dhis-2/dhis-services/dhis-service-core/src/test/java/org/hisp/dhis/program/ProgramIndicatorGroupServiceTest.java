/*
 * Copyright (c) 2004-2021, University of Oslo
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * Neither the name of the HISP project nor the names of its contributors may
 * be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.hisp.dhis.program;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.hisp.dhis.DhisSpringTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Viet Nguyen <viet@dhis2.org>
 */
class ProgramIndicatorGroupServiceTest extends DhisSpringTest
{

    @Autowired
    private ProgramIndicatorService service;

    private ProgramIndicatorGroup programIndicatorGroupA;

    @Override
    public void setUpTest()
    {
        programIndicatorGroupA = new ProgramIndicatorGroup( "A" );
    }

    @Test
    void testAddProgramIndicatorGroup()
    {
        service.addProgramIndicatorGroup( programIndicatorGroupA );
        assertNotNull( programIndicatorGroupA.getUid() );
    }

    @Test
    void testUpdateProgramIndicatorGroup()
    {
        service.addProgramIndicatorGroup( programIndicatorGroupA );
        programIndicatorGroupA.setName( "B" );
        assertEquals( "B", service.getProgramIndicatorGroup( programIndicatorGroupA.getId() ).getName() );
    }

    @Test
    void testDeleteProgramIndicatorGroup()
    {
        long id = service.addProgramIndicatorGroup( programIndicatorGroupA );
        service.deleteProgramIndicatorGroup( programIndicatorGroupA );
        assertEquals( null, service.getProgramIndicatorGroup( id ) );
    }
}
