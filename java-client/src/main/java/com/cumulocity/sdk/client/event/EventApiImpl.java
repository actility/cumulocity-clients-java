/*
 * Copyright (C) 2013 Cumulocity GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of 
 * this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.cumulocity.sdk.client.event;

import java.util.Map;

import com.cumulocity.model.idtype.GId;
import com.cumulocity.rest.representation.event.EventCollectionRepresentation;
import com.cumulocity.rest.representation.event.EventMediaType;
import com.cumulocity.rest.representation.event.EventRepresentation;
import com.cumulocity.rest.representation.event.EventsApiRepresentation;
import com.cumulocity.rest.representation.platform.PlatformApiRepresentation;
import com.cumulocity.rest.representation.platform.PlatformMediaType;
import com.cumulocity.sdk.client.PagedCollectionResource;
import com.cumulocity.sdk.client.RestConnector;
import com.cumulocity.sdk.client.SDKException;
import com.cumulocity.sdk.client.UrlProcessor;

public class EventApiImpl implements EventApi {

    private final String platformApiUrl;

    private final RestConnector restConnector;

    private final int pageSize;

    private EventsApiRepresentation eventsApiRepresentation = null;
    
    private UrlProcessor urlProcessor;

    public EventApiImpl(RestConnector restConnector, UrlProcessor urlProcessor, String platformApiUrl, int pageSize) {
        this.restConnector = restConnector;
        this.urlProcessor = urlProcessor;
        this.platformApiUrl = platformApiUrl;
        this.pageSize = pageSize;
    }

    private EventsApiRepresentation getEventApiRepresentation() throws SDKException {
        if (null == eventsApiRepresentation) {
            createApiRepresentation();
        }
        return eventsApiRepresentation;
    }
    
    private void createApiRepresentation() throws SDKException
    {
        PlatformApiRepresentation platformApiRepresentation =  restConnector.get(platformApiUrl,PlatformMediaType.PLATFORM_API, PlatformApiRepresentation.class);
        eventsApiRepresentation = platformApiRepresentation.getEvent();
    }

    @Override
    public EventRepresentation getEvent(GId eventId) throws SDKException {
        String url = getSelfUri() + "/" + eventId.getValue();
        return restConnector.get(url, EventMediaType.EVENT, EventRepresentation.class);
    }

    @Override
    public PagedCollectionResource<EventCollectionRepresentation> getEvents() throws SDKException {
        String url = getSelfUri();
        return new EventCollectionImpl(restConnector, url, pageSize);
    }

    @Override
    public EventRepresentation create(EventRepresentation representation) throws SDKException {
        return restConnector.post(getSelfUri(), EventMediaType.EVENT, representation);
    }

    @Override
    public void delete(EventRepresentation event) throws SDKException {
        String url = getSelfUri() + "/" + event.getId().getValue();
        restConnector.delete(url);
    }

    @Override
    public PagedCollectionResource<EventCollectionRepresentation> getEventsByFilter(EventFilter filter) throws SDKException {
        if (filter == null) {
            return getEvents();
        }
        Map<String, String> params = filter.getQueryParams();
        return new EventCollectionImpl(restConnector, urlProcessor.replaceOrAddQueryParam(getSelfUri(), params), pageSize);
    }

    private String getSelfUri() throws SDKException {
        return getEventApiRepresentation().getEvents().getSelf();
    }
}
