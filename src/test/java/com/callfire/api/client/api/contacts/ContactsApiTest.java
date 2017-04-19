package com.callfire.api.client.api.contacts;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalToIgnoringWhiteSpace;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;

import com.callfire.api.client.api.AbstractApiTest;
import com.callfire.api.client.api.common.model.ListHolder;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.ResourceId;
import com.callfire.api.client.api.common.model.request.GetByIdRequest;
import com.callfire.api.client.api.contacts.model.Contact;
import com.callfire.api.client.api.contacts.model.ContactHistory;
import com.callfire.api.client.api.contacts.model.request.FindContactsRequest;

public class ContactsApiTest extends AbstractApiTest {

    protected static final String RESPONSES_PATH = "/contacts/contactsApi/response/";
    protected static final String EMPTY_CONTACT_ID_MSG = "contact.id cannot be null";

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        client.getRestApiClient().setHttpClient(mockHttpClient);
    }

    @Test
    public void testFind() throws Exception {
        String expectedJson = getJsonPayload(BASE_PATH + RESPONSES_PATH + "findContacts.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(expectedJson);

        FindContactsRequest request = FindContactsRequest.create()
            .limit(1L)
            .offset(5L)
            .build();
        Page<Contact> contacts = client.contactsApi().find(request);
        assertThat(jsonConverter.serialize(contacts), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString("limit=1"));
        assertThat(arg.getURI().toString(), containsString("offset=5"));
    }

    @Test
    public void testCreate() throws Exception {
        String expectedJson = getJsonPayload(BASE_PATH + RESPONSES_PATH + "createContact.json");
        ArgumentCaptor<HttpUriRequest> argumentCaptor = mockHttpResponse(expectedJson);

        Contact contact = new Contact();
        contact.setHomePhone("231554254");
        List<Contact> inputContacts = Arrays.asList(contact);
        List<ResourceId> resultResourceIds = client.contactsApi().create(inputContacts);

        verify(mockHttpClient, times(2)).execute(argumentCaptor.capture());
        assertThat(jsonConverter.serialize(new ListHolder<>(resultResourceIds)),
            equalToIgnoringWhiteSpace(expectedJson));
    }

    @Test
    public void testGetByNullId() throws Exception {
        ex.expectMessage(EMPTY_ID_MSG);
        ex.expect(NullPointerException.class);
        client.contactsApi().get(null);
    }

    @Test
    public void testGetById() throws Exception {
        String expectedJson = getJsonPayload(BASE_PATH + RESPONSES_PATH + "getContactById.json");
        mockHttpResponse(expectedJson);

        Contact contact = client.contactsApi().get(TEST_ID);
        assertThat(jsonConverter.serialize(contact), equalToIgnoringWhiteSpace(expectedJson));
    }

    @Test
    public void testGetByIdAndFields() throws Exception {
        String expectedJson = getJsonPayload(BASE_PATH + RESPONSES_PATH + "getContactById.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(expectedJson);

        Contact contact = client.contactsApi().get(TEST_ID, FIELDS);
        assertThat(jsonConverter.serialize(contact), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString(ENCODED_FIELDS));
    }

    @Test
    public void testUpdateByNullId() throws Exception {
        ex.expectMessage(EMPTY_CONTACT_ID_MSG);
        ex.expect(NullPointerException.class);
        client.contactsApi().update(new Contact());
    }

    @Test
    public void testUpdate() throws Exception {
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse();

        Contact contact = new Contact();
        contact.setId(TEST_ID);
        client.contactsApi().update(contact);

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPut.METHOD_NAME, arg.getMethod());
        assertThat(arg.getURI().toString(), containsString("/" + TEST_ID));
    }

    @Test
    public void testDeleteByNullId() throws Exception {
        ex.expectMessage(EMPTY_ID_MSG);
        ex.expect(NullPointerException.class);
        client.contactsApi().delete(null);
    }

    @Test
    public void testDelete() throws Exception {
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse();

        client.contactsApi().delete(TEST_ID);

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpDelete.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString("/" + TEST_ID));
    }

    @Test
    public void testGetContactHistoryByNullId() throws Exception {
        ex.expectMessage(EMPTY_REQUEST_ID_MSG);
        ex.expect(NullPointerException.class);
        client.contactsApi().getHistory(GetByIdRequest.create().build());
    }

    @Test
    public void testGetContactHistory() throws Exception {
        String expectedJson = getJsonPayload(BASE_PATH + RESPONSES_PATH + "getContactHistory.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(expectedJson);

        GetByIdRequest request = GetByIdRequest.create()
            .id(TEST_ID)
            .limit(1L)
            .offset(5L)
            .build();
        ContactHistory contactHistory = client.contactsApi().getHistory(request);
        assertThat(jsonConverter.serialize(contactHistory), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString("limit=1"));
        assertThat(arg.getURI().toString(), containsString("offset=5"));
    }

}
