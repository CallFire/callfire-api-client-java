package com.callfire.api.client.api.contacts;

import com.callfire.api.client.JsonConverter;
import com.callfire.api.client.api.AbstractApiTest;
import com.callfire.api.client.api.common.model.ListHolder;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.ResourceId;
import com.callfire.api.client.api.common.model.request.GetByIdRequest;
import com.callfire.api.client.api.contacts.model.Contact;
import com.callfire.api.client.api.contacts.model.ContactHistory;
import com.callfire.api.client.api.contacts.model.request.AddContactListItemsRequest;
import com.callfire.api.client.api.contacts.model.request.CreateContactListRequest;
import com.callfire.api.client.api.contacts.model.request.FindContactsRequest;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class ContactsApiTest extends AbstractApiTest {

    protected static final String RESPONSES_PATH = "/contacts/contactsApi/response/";
    protected static final Long TEST_ID = new Long(100500);
    protected static final String TEST_CONTACTS_FIELDS = "id,firstName,lastName,zipcode,workPhone,deleted";
    protected static final String EMPTY_ID_MSG = "id cannot be null";
    protected static final String EMPTY_REQUEST_ID_MSG = "request.id cannot be null";
    protected static final String EMPTY_CONTACT_ID_MSG = "contact.id cannot be null";

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        client.getRestApiClient().setHttpClient(mockHttpClient);
    }

    @Test
    public void testDynamicPropertiesSerializationStringNumbers() throws Exception {
        // contactNumbers
        CreateContactListRequest requestString = CreateContactListRequest.<String>create()
            .name("listFromNumbers")
            .contacts(asList("12345678881", "12345678882"))
            .build();
        JsonConverter jsonConverter = new JsonConverter();
        String serialized = jsonConverter.serialize(requestString);
        System.out.println("contactNumbers: " + serialized);
        assertThat(serialized, containsString("\"contactNumbers\":"));
    }

    @Test
    public void testDynamicPropertiesSerializationContactIds() throws Exception {
        JsonConverter jsonConverter = new JsonConverter();
        // contactIds
        CreateContactListRequest requestLong = CreateContactListRequest.<Long>create()
            .name("listFromIds")
            .contacts(asList(1L, 2L))
            .build();

        String serialized = jsonConverter.serialize(requestLong);
        System.out.println("contactIds: " + serialized);
        assertThat(serialized, containsString("\"contactIds\":"));
        assertThat(serialized, containsString("\"listFromIds\""));
    }

    @Test
    public void testDynamicPropertiesSerializationContactPojos() throws Exception {
        JsonConverter jsonConverter = new JsonConverter();
        Contact c1 = new Contact();
        c1.setFirstName("name1");
        Contact c2 = new Contact();
        c2.setFirstName("name2");
        // contacts
        CreateContactListRequest requestObjects = CreateContactListRequest.<Contact>create()
            .name("listFromContacts")
            .contacts(asList(c1, c2))
            .build();
        String serialized = jsonConverter.serialize(requestObjects);
        System.out.println("contacts: " + serialized);
        assertThat(serialized, containsString("\"contacts\":"));
        assertThat(serialized, containsString("\"listFromContacts\""));
    }

    @Test
    public void testDynamicPropertiesSerializationWithOtherProps() throws Exception {
        AddContactListItemsRequest requestObjects = AddContactListItemsRequest.<Long>create()
            .contactNumbersField("field")
            .contactListId(5L)
            .contacts(asList(1L, 2L))
            .build();
        JsonConverter jsonConverter = new JsonConverter();
        String serialized = jsonConverter.serialize(requestObjects);
        System.out.println("contactIds: " + serialized);
        assertThat(serialized, containsString("\"contactIds\":"));
        assertThat(serialized, containsString("\"contactNumbersField\":\"field\""));
        assertThat(serialized, not(containsString("\"contactListId\":")));
    }

    @Test
    public void testFind() throws Exception {
        String expectedJson = getJsonPayload(BASE_PATH + RESPONSES_PATH + "findContacts.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse, expectedJson);

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
        mockHttpResponse(mockHttpClient, mockHttpResponse, expectedJson);

        Contact contact = new Contact();
        contact.setHomePhone("231554254");
        List<Contact> inputContacts = Arrays.asList(contact);
        List<ResourceId> resultResourceIds = client.contactsApi().create(inputContacts);
        assertThat(jsonConverter.serialize(new ListHolder<>(resultResourceIds)), equalToIgnoringWhiteSpace(expectedJson));
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
        mockHttpResponse(mockHttpClient, mockHttpResponse, expectedJson);

        Contact contact = client.contactsApi().get(TEST_ID);
        assertThat(jsonConverter.serialize(contact), equalToIgnoringWhiteSpace(expectedJson));
    }

    @Test
    public void testGetByIdAndFields() throws Exception {
        String expectedJson = getJsonPayload(BASE_PATH + RESPONSES_PATH + "getContactById.json");
        mockHttpResponse(mockHttpClient, mockHttpResponse, expectedJson);

        Contact contact = client.contactsApi().get(TEST_ID, TEST_CONTACTS_FIELDS);
        assertThat(jsonConverter.serialize(contact), equalToIgnoringWhiteSpace(expectedJson));
    }

    @Test
    public void testUpdateByNullId() throws Exception {
        ex.expectMessage(EMPTY_CONTACT_ID_MSG);
        ex.expect(NullPointerException.class);
        client.contactsApi().update(new Contact());
    }

    @Test
    public void testUpdate() throws Exception {
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse);

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
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse);

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
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse, expectedJson);

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
