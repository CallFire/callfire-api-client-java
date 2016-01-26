package com.callfire.api.client.api.contacts;

import com.callfire.api.client.JsonConverter;
import com.callfire.api.client.api.AbstractApiTest;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.ResourceId;
import com.callfire.api.client.api.common.model.request.GetByIdRequest;
import com.callfire.api.client.api.contacts.model.Contact;
import com.callfire.api.client.api.contacts.model.ContactList;
import com.callfire.api.client.api.contacts.model.request.AddContactListItemsRequest;
import com.callfire.api.client.api.contacts.model.request.CreateContactListRequest;
import com.callfire.api.client.api.contacts.model.request.FindContactListsRequest;
import com.callfire.api.client.api.contacts.model.request.UpdateContactListRequest;
import org.apache.http.client.methods.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class ContactListsApiTest extends AbstractApiTest {

    protected static final String RESPONSES_PATH = "/contacts/contactsApi/response/";
    protected static final String REQUESTS_PATH = "/contacts/contactsApi/request/";
    protected static final String EMPTY_LIST_ID_MSG = "listId cannot be null";
    protected static final String EMPTY_CONTACT_ID_MSG = "contactId cannot be null";
    protected static final String EMPTY_REQ_CONTACT_LIST_ID_MSG = "request.contactListId cannot be null";
    protected static final String EMPTY_CONTACT_LIST_ID_MSG = "contactListId cannot be null";

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
        String expectedJson = getJsonPayload(BASE_PATH + RESPONSES_PATH + "findContactLists.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(expectedJson);

        FindContactListsRequest request = FindContactListsRequest.create()
            .limit(1L)
            .offset(5L)
            .name("test")
            .fields(FIELDS)
            .build();
        Page<ContactList> contactLists = client.contactListsApi().find(request);
        assertThat(jsonConverter.serialize(contactLists), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString("limit=1"));
        assertThat(arg.getURI().toString(), containsString("offset=5"));
        assertThat(arg.getURI().toString(), containsString("name=test"));
        assertThat(arg.getURI().toString(), containsString(ENCODED_FIELDS));
    }

    @Test
    public void testCreate() throws Exception {
        String expectedJson = getJsonPayload(BASE_PATH + RESPONSES_PATH + "createContactList.json");
        mockHttpResponse(expectedJson);

        Contact c1 = new Contact();
        c1.setHomePhone("123456");
        Contact c2 = new Contact();
        c2.setHomePhone("123457");
        CreateContactListRequest request = CreateContactListRequest.<Contact>create()
            .name("listFromContacts")
            .contacts(asList(c1, c2))
            .build();
        ResourceId resourceId = client.contactListsApi().create(request);
        assertThat(jsonConverter.serialize(resourceId), equalToIgnoringWhiteSpace(expectedJson));
    }

    @Test
    public void testCreateFromCsv() throws Exception {
        String expectedJson = getJsonPayload(BASE_PATH + RESPONSES_PATH + "createContactList.json");
        mockHttpResponse(expectedJson);

        File file = new File(REQUESTS_PATH + "createContactsList.csv");

        ResourceId resourceId = client.contactListsApi().createFromCsv("testFile", file);
        assertThat(jsonConverter.serialize(resourceId), equalToIgnoringWhiteSpace(expectedJson));
    }

    @Test
    public void testGetByNullId() throws Exception {
        ex.expectMessage(EMPTY_ID_MSG);
        ex.expect(NullPointerException.class);
        client.contactListsApi().get(null);
    }

    @Test
    public void testGetById() throws Exception {
        String expectedJson = getJsonPayload(BASE_PATH + RESPONSES_PATH + "getContactList.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(expectedJson);

        ContactList contactList = client.contactListsApi().get(TEST_ID, FIELDS);
        assertThat(jsonConverter.serialize(contactList), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString(ENCODED_FIELDS));
    }

    @Test
    public void testUpdateByNullId() throws Exception {
        ex.expectMessage(EMPTY_REQUEST_ID_MSG);
        ex.expect(NullPointerException.class);
        client.contactListsApi().update(UpdateContactListRequest.create().id(null).build());
    }

    @Test
    public void testUpdateById() throws Exception {
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse();
        String requestJson = getJsonPayload(BASE_PATH + REQUESTS_PATH + "updateContactList.json");

        client.contactListsApi().update(UpdateContactListRequest.create().id(TEST_ID).name("test").build());

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPut.METHOD_NAME, arg.getMethod());
        assertThat(arg.getURI().toString(), containsString("/" + TEST_ID));
        assertThat(extractHttpEntity(arg), equalToIgnoringWhiteSpace(requestJson));
    }

    @Test
    public void testDeleteByNullId() throws Exception {
        ex.expectMessage(EMPTY_ID_MSG);
        ex.expect(NullPointerException.class);
        client.contactListsApi().delete(null);
    }

    @Test
    public void testDelete() throws Exception {
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse();

        client.contactListsApi().delete(TEST_ID);

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpDelete.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString("/" + TEST_ID));
    }

    @Test
    public void testGetContactsForContactListByNullId() throws Exception {
        ex.expectMessage(EMPTY_ID_MSG);
        ex.expect(NullPointerException.class);
        client.contactListsApi().getListItems(GetByIdRequest.create().build());
    }

    @Test
    public void testGetContactsForContactList() throws Exception {
        String expectedJson = getJsonPayload(BASE_PATH + RESPONSES_PATH + "findContacts.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(expectedJson);

        GetByIdRequest request = GetByIdRequest.create()
            .id(TEST_ID)
            .limit(1L)
            .offset(5L)
            .fields(FIELDS)
            .build();
        Page<Contact> contactsList = client.contactListsApi().getListItems(request);
        assertThat(jsonConverter.serialize(contactsList), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString("limit=1"));
        assertThat(arg.getURI().toString(), containsString("offset=5"));
        assertThat(arg.getURI().toString(), containsString(ENCODED_FIELDS));
    }

    @Test
    public void testAddContactsToContactListByNullId() throws Exception {
        ex.expectMessage(EMPTY_REQ_CONTACT_LIST_ID_MSG);
        ex.expect(NullPointerException.class);
        client.contactListsApi().addListItems(AddContactListItemsRequest.create().build());
    }

    @Test
    public void testAddContactsToContactListById() throws Exception {
        String expectedJson = getJsonPayload(BASE_PATH + RESPONSES_PATH + "addContactsToContactList.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse();

        Contact c1 = new Contact();
        c1.setHomePhone("123456");
        Contact c2 = new Contact();
        c2.setHomePhone("123457");
        AddContactListItemsRequest request = AddContactListItemsRequest.<Contact>create()
            .contactNumbersField("homePhone")
            .contactListId(TEST_ID)
            .contacts(asList(c1, c2))
            .build();
        client.contactListsApi().addListItems(request);
        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());

        assertThat(extractHttpEntity(arg), equalToIgnoringWhiteSpace(expectedJson));
        assertThat(arg.getURI().toString(), containsString("/" + TEST_ID));
    }

    @Test
    public void testDeleteContactFromContactListByNullContactListId() throws Exception {
        ex.expectMessage(EMPTY_LIST_ID_MSG);
        ex.expect(NullPointerException.class);
        client.contactListsApi().removeListItem(null, TEST_ID);
    }

    @Test
    public void testDeleteContactFromContactListByNullContactId() throws Exception {
        ex.expectMessage(EMPTY_CONTACT_ID_MSG);
        ex.expect(NullPointerException.class);
        client.contactListsApi().removeListItem(TEST_ID, null);
    }

    @Test
    public void testDeleteContactFromContactList() throws Exception {
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse();

        client.contactListsApi().removeListItem(TEST_ID, 123456L);

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpDelete.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString("/" + TEST_ID));
        assertThat(arg.getURI().toString(), containsString("/123456"));
    }

    @Test
    public void testDeleteContactsFromContactListByNullContactListId() throws Exception {
        ex.expectMessage(EMPTY_CONTACT_LIST_ID_MSG);
        ex.expect(NullPointerException.class);
        client.contactListsApi().removeListItems(null, new ArrayList<Long>());
    }

    @Test
    public void testDeleteContactsFromContactList() throws Exception {
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse();

        client.contactListsApi().removeListItems(TEST_ID, Arrays.asList(123456L));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpDelete.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString("/" + TEST_ID));
        assertThat(arg.getURI().toString(), containsString("contactId=123456"));
    }
}
