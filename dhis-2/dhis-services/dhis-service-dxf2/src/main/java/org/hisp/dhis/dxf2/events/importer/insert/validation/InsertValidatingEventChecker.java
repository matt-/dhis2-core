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
package org.hisp.dhis.dxf2.events.importer.insert.validation;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.hisp.dhis.importexport.ImportStrategy.CREATE;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import lombok.Getter;

import org.hisp.dhis.dxf2.events.importer.Checker;
import org.hisp.dhis.dxf2.events.importer.EventImporterValidationRunner;
import org.hisp.dhis.dxf2.events.importer.ImportStrategyUtils;
import org.hisp.dhis.dxf2.events.importer.ValidatingEventChecker;
import org.hisp.dhis.importexport.ImportStrategy;
import org.springframework.stereotype.Component;

/**
 * @author Luciano Fiandesio
 */
@Component
public class InsertValidatingEventChecker extends ValidatingEventChecker
{
    @Getter
    private final Predicate<ImportStrategy> supportedPredicate = ImportStrategyUtils::isInsert;

    public InsertValidatingEventChecker( final Map<ImportStrategy, List<Checker>> checkersByImportStrategy,
        EventImporterValidationRunner validationRunner )
    {
        super( checkNotNull(
            checkNotNull( checkersByImportStrategy ).get( CREATE ) ), validationRunner );
    }
}