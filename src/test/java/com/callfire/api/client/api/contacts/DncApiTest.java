package com.callfire.api.client.api.contacts;

import com.callfire.api.client.api.AbstractApiTest;

public class DncApiTest extends AbstractApiTest {

    // TODO vmalinovskiy: uncomment when dnc apis will be tested and available on docs site
    /* protected static final String RESPONSES_PATH = "/contacts/dncApi/response/";
    protected static final String REQUESTS_PATH = "/contacts/dncApi/request/";

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        client.getRestApiClient().setHttpClient(mockHttpClient);
    }

    @Test
    public void testFind() throws Exception {
        String expectedJson = getJsonPayload(BASE_PATH + RESPONSES_PATH + "findDncs.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(expectedJson);

        FindDncNumbersRequest request = FindDncNumbersRequest.create()
            .limit(1L)
            .offset(5L)
            .fields(FIELDS)
            .prefix("1")
            .call(true)
            .text(true)
            .build();
        Page<DoNotContact> doNotContactList = client.dncApi().find(request);
        assertThat(jsonConverter.serialize(doNotContactList), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString("prefix=1"));
        assertThat(arg.getURI().toString(), containsString("limit=1"));
        assertThat(arg.getURI().toString(), containsString("offset=5"));
        assertThat(arg.getURI().toString(), containsString(ENCODED_FIELDS));
        assertThat(arg.getURI().toString(), containsString("call=true"));
        assertThat(arg.getURI().toString(), containsString("text=true"));
    }

    @Test
    public void getDnc() throws Exception {
        String expectedJson = getJsonPayload(BASE_PATH + RESPONSES_PATH + "getDnc.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(expectedJson);


        DoNotContact doNotContact = client.dncApi().get("12135551188");
        assertThat(jsonConverter.serialize(doNotContact), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString("/12135551188"));
    }

    @Test
    public void testUpdate() throws Exception {
        String requestJson = getJsonPayload(BASE_PATH + REQUESTS_PATH + "updateDnc.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse();

        UpdateDncRequest request = UpdateDncRequest.create().call(true).text(true).number("100500").build();
        client.dncApi().update(request);

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPut.METHOD_NAME, arg.getMethod());
        assertThat(extractHttpEntity(arg), equalToIgnoringWhiteSpace(requestJson));
    }

    @Test
    public void testCreate() throws Exception {
        String requestJson = getJsonPayload(BASE_PATH + REQUESTS_PATH + "addDnc.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse();

        CreateDncsRequest request = CreateDncsRequest.create()
            .call(true)
            .text(true)
            .numbers(Arrays.asList("12135551188"))
            .source("testSource")
            .build();
        client.dncApi().create(request);

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
        assertThat(extractHttpEntity(arg), equalToIgnoringWhiteSpace(requestJson));
    }

    @Test
    public void testDeleteByNullId() throws Exception {
        ex.expectMessage(EMPTY_REQUEST_NUMBER_MSG);
        ex.expect(NullPointerException.class);
        client.dncApi().delete(null);
    }

    @Test
    public void testDelete() throws Exception {
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse();

        client.dncApi().delete("12135551188");

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpDelete.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString("/" + "12135551188"));
    }

    @Test
    public void testDeleteDncsFromSourceByNullId() throws Exception {
        ex.expectMessage(EMPTY_REQUEST_NUMBER_MSG);
        ex.expect(NullPointerException.class);
        client.dncApi().deleteDncsFromSource(null);
    }

    @Test
    public void testDeleteDncsFromSource() throws Exception {
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse();

        client.dncApi().delete("testSource");

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpDelete.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString("/" + "testSource"));
    }

    @Test
    public void testFindUniversalDncs() throws Exception {
        String expectedJson = getJsonPayload(BASE_PATH + RESPONSES_PATH + "findUniversalDncs.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(expectedJson);

        FindUniversalDncsRequest request = FindUniversalDncsRequest.create()
            .toNumber("12135551188")
            .fromNumber("18442800143")
            .fields(FIELDS)
            .build();
        List<UniversalDnc> udncsList = client.dncApi().findUniversalDncs(request);
        assertThat(jsonConverter.serialize(new ListHolder<>(udncsList)), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString("toNumber=12135551188"));
        assertThat(arg.getURI().toString(), containsString("fromNumber=18442800143"));
        assertThat(arg.getURI().toString(), containsString(ENCODED_FIELDS));
    }*/
}
