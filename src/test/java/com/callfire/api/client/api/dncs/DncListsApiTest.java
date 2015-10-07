package com.callfire.api.client.api.dncs;

import com.callfire.api.client.api.AbstractApiTest;
import com.callfire.api.client.api.common.model.ListHolder;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.ResourceId;
import com.callfire.api.client.api.common.model.request.GetByIdRequest;
import com.callfire.api.client.api.contacts.model.Contact;
import com.callfire.api.client.api.contacts.model.DncList;
import com.callfire.api.client.api.contacts.model.DoNotContact;
import com.callfire.api.client.api.contacts.model.UniversalDnc;
import com.callfire.api.client.api.contacts.model.request.AddDncListItemsRequest;
import com.callfire.api.client.api.contacts.model.request.FindDncListsRequest;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalToIgnoringWhiteSpace;
import static org.junit.Assert.*;

public class DncListsApiTest extends AbstractApiTest {

    protected static final String RESPONSES_PATH = "/dncs/dncApi/response/";
    protected static final String REQUESTS_PATH = "/dncs/dncApi/request/";
    protected static final String EMPTY_TO_NUMBER_MSG = "toNumber cannot be blank";
    protected static final String EMPTY_NUMBER_MSG = "number cannot be blank";
    protected static final String EMPTY_REQ_CONTACT_LIST_ID_MSG = "request.contactListId cannot be null";
    protected static final String EMPTY_CONTACT_LIST_ID_MSG = "contactListId cannot be null";

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        client.getRestApiClient().setHttpClient(mockHttpClient);
    }

    @Test
    public void testFind() throws Exception {
        String expectedJson = getJsonPayload(BASE_PATH + RESPONSES_PATH + "findDncLists.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse, expectedJson);

        FindDncListsRequest request = FindDncListsRequest.create()
            .limit(1L)
            .offset(5L)
            .fields(FIELDS)
            .campaignId(TEST_ID)
            .name("test")
            .build();
        Page<DncList> dncList = client.dncListsApi().find(request);
        assertThat(jsonConverter.serialize(dncList), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString("limit=1"));
        assertThat(arg.getURI().toString(), containsString("offset=5"));
        assertThat(arg.getURI().toString(), containsString(ENCODED_FIELDS));
        assertThat(arg.getURI().toString(), containsString("campaignId=100500"));
        assertThat(arg.getURI().toString(), containsString("name=test"));
    }

    @Test
    public void testCreate() throws Exception {
        String requestJson = getJsonPayload(BASE_PATH + REQUESTS_PATH + "createDncList.json");
        String expectedJson = getJsonPayload(BASE_PATH + RESPONSES_PATH + "createDncList.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse, expectedJson);

        DncList dncList = new DncList();
        dncList.setId(TEST_ID);
        dncList.setCampaignId(TEST_ID);
        dncList.setSize(TEST_ID.intValue());
        dncList.setName("test");
        ResourceId res = client.dncListsApi().create(dncList);

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
        assertThat(extractHttpEntity(arg), equalToIgnoringWhiteSpace(requestJson));
        assertThat(jsonConverter.serialize(res), equalToIgnoringWhiteSpace(expectedJson));
    }


    @Test
    public void getUniversalDncNumbersByNullToNumber() throws Exception {
        ex.expectMessage(EMPTY_TO_NUMBER_MSG);
        ex.expect(NullPointerException.class);
        client.dncListsApi().getUniversalDncNumber(null);
        client.dncListsApi().getUniversalDncNumber("");
    }

    @Test
    public void getUniversalDncNumbers() throws Exception {
        String expectedJson = getJsonPayload(BASE_PATH + RESPONSES_PATH + "getUniversalDncNumbers.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse, expectedJson);

        List<UniversalDnc> universalDncNumbers = client.dncListsApi().getUniversalDncNumber(TEST_ID.toString(), TEST_ID.toString(), FIELDS);
        assertThat(jsonConverter.serialize(new ListHolder<>(universalDncNumbers)), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString("fromNumber=" + TEST_ID.toString()));
        assertThat(arg.getURI().toString(), containsString(ENCODED_FIELDS));
    }

    @Test
    public void testGetDncByNullId() throws Exception {
        ex.expectMessage(EMPTY_ID_MSG);
        ex.expect(NullPointerException.class);
        client.dncListsApi().get(null);
    }

    @Test
    public void testGetDnc() throws Exception {
        String expectedJson = getJsonPayload(BASE_PATH + RESPONSES_PATH + "getDncList.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse, expectedJson);

        DncList dncList = client.dncListsApi().get(TEST_ID, FIELDS);
        assertThat(jsonConverter.serialize(dncList), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString(ENCODED_FIELDS));
    }

    @Test
    public void testDeleteDncByNullId() throws Exception {
        ex.expectMessage(EMPTY_ID_MSG);
        ex.expect(NullPointerException.class);
        client.dncListsApi().delete(null);
    }

    @Test
    public void testDeleteDncById() throws Exception {
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse);

        client.dncListsApi().delete(TEST_ID);

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpDelete.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString("/" + TEST_ID));
    }

    @Test
    public void testGetDncItemsByNullId() throws Exception {
        ex.expectMessage(EMPTY_REQUEST_ID_MSG);
        ex.expect(NullPointerException.class);
        client.dncListsApi().getListItems(GetByIdRequest.create().build());
    }

    @Test
    public void testGetDncItems() throws Exception {
        String expectedJson = getJsonPayload(BASE_PATH + RESPONSES_PATH + "findDncList.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse, expectedJson);

        GetByIdRequest request = GetByIdRequest.create()
            .limit(1L)
            .offset(5L)
            .fields(FIELDS)
            .id(TEST_ID)
            .build();
        Page<DoNotContact> dncs = client.dncListsApi().getListItems(request);
        assertThat(jsonConverter.serialize(dncs), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString("limit=1"));
        assertThat(arg.getURI().toString(), containsString("offset=5"));
        assertThat(arg.getURI().toString(), containsString(ENCODED_FIELDS));
        assertThat(arg.getURI().toString(), containsString("/" + TEST_ID));
    }

    @Test
    public void testAddDncItemsByNullId() throws Exception {
        ex.expectMessage(EMPTY_REQ_CONTACT_LIST_ID_MSG);
        ex.expect(NullPointerException.class);
        client.dncListsApi().addListItems(AddDncListItemsRequest.create().build());
    }

    @Test
    public void testAddDncItems() throws Exception {
        String requestJson = getJsonPayload(BASE_PATH + REQUESTS_PATH + "addDncItems.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse);

        List<Contact> contacts = new ArrayList<Contact>();
        Contact cnt = new Contact();
        cnt.setId(TEST_ID);
        cnt.setHomePhone(TEST_ID.toString());
        contacts.add(cnt);
        AddDncListItemsRequest<Contact> request = AddDncListItemsRequest.<Contact>create()
            .contactListId(TEST_ID)
            .contacts(contacts)
            .build();

        client.dncListsApi().addListItems(request);

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
        assertThat(arg.getURI().toString(), containsString("/" + TEST_ID));
        assertThat(extractHttpEntity(arg), equalToIgnoringWhiteSpace(requestJson));
    }

    @Test
    public void testRemoveListItemByNullId() throws Exception {
        ex.expectMessage(EMPTY_ID_MSG);
        ex.expect(NullPointerException.class);
        client.dncListsApi().removeListItem(null, "test");
    }


    @Test
    public void testRemoveListItemByNullNumber() throws Exception {
        ex.expectMessage(EMPTY_NUMBER_MSG);
        ex.expect(NullPointerException.class);
        client.dncListsApi().removeListItem(TEST_ID, null);
        client.dncListsApi().removeListItem(TEST_ID, "");
    }

    @Test
    public void testRemoveListItem() throws Exception {
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse);

        client.dncListsApi().removeListItem(TEST_ID, "123456");

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpDelete.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString("/" + TEST_ID));
        assertThat(arg.getURI().toString(), containsString("/" + "123456"));
    }

    @Test
    public void testRemoveListItemsByNullId() throws Exception {
        ex.expectMessage(EMPTY_CONTACT_LIST_ID_MSG);
        ex.expect(NullPointerException.class);
        client.dncListsApi().removeListItems(null, Arrays.asList("123456"));
    }

    @Test
    public void testRemoveListItems() throws Exception {
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse);

        client.dncListsApi().removeListItems(TEST_ID, Arrays.asList("123456", "123457"));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpDelete.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString("/" + TEST_ID));
        assertThat(arg.getURI().toString(), containsString("number=123456&number=123457"));
    }


}
