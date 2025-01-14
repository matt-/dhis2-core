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
package org.hisp.dhis.analytics.event;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.Builder;
import lombok.Data;

import org.apache.commons.collections4.CollectionUtils;
import org.hisp.dhis.common.BaseDimensionalItemObject;
import org.hisp.dhis.common.BaseDimensionalObject;
import org.hisp.dhis.hibernate.HibernateProxyUtils;

@Data
@Builder( toBuilder = true )
public class EventsAnalyticsDimensionalItems
{

    public final static EventsAnalyticsDimensionalItems EMPTY_ANALYTICS_DIMENSIONAL_ITEMS = EventsAnalyticsDimensionalItems
        .builder()
        .build();

    @Builder.Default
    private final Collection<? extends BaseDimensionalItemObject> programIndicators = Collections.emptyList();

    @Builder.Default
    private final Collection<? extends BaseDimensionalItemObject> dataElements = Collections.emptyList();

    @Builder.Default
    private final Collection<? extends BaseDimensionalItemObject> trackedEntityAttributes = Collections.emptyList();

    @Builder.Default
    private final Collection<? extends BaseDimensionalObject> comboCategories = Collections.emptyList();

    @Builder.Default
    private final Collection<? extends BaseDimensionalObject> attributeCategoryOptionGroupSets = Collections
        .emptyList();

    public Collection<DimensionWrapper> getDimensionalItems()
    {
        return Stream.of(
            programIndicators,
            dataElements,
            trackedEntityAttributes,
            comboCategories,
            attributeCategoryOptionGroupSets )
            .map( CollectionUtils::emptyIfNull )
            .flatMap( Collection::stream )
            .map( HibernateProxyUtils::unproxy )
            .map( DimensionWrapper::new )
            .collect( Collectors.toList() );
    }
}
