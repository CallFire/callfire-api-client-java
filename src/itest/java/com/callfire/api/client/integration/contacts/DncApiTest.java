package com.callfire.api.client.integration.contacts;

import com.callfire.api.client.integration.AbstractIntegrationTest;

/**
 * integration tests for /contacts/dncs api endpoint
 */
public class DncApiTest extends AbstractIntegrationTest {

    // TODO vmalinovskiy: uncomment when dnc apis will be tested and available on docs site
    /*@Test
    public void testFind() throws Exception {
        CallfireClient client = getCallfireClient();
        FindDncNumbersRequest request = FindDncNumbersRequest.create()
            .text(true)
            .limit(1L)
            .build();

        Page<DoNotContact> dncContacts = client.dncApi().find(request);
        System.out.println(dncContacts);

        Assert.assertEquals(1, dncContacts.getItems().size());
    }

    @Test
    public void testCrudAndGetDnc() throws Exception {
        CallfireClient client = getCallfireClient();

        CreateDncsRequest crRequest = CreateDncsRequest.create()
            .call(true)
            .text(true)
            .numbers(Arrays.asList("12135551188"))
            .source("testSource")
            .build();
        client.dncApi().create(crRequest);

        DoNotContact dnc = client.dncApi().get("12135551188");
        Assert.assertEquals(dnc.getNumber(), "12135551188");
        Assert.assertEquals(dnc.getCall(), true);
        Assert.assertEquals(dnc.getText(), true);

        UpdateDncRequest updRequest = UpdateDncRequest.create()
            .call(true)
            .text(false)
            .number("12135551188")
            .build();
        client.dncApi().update(updRequest);

        dnc = client.dncApi().get("12135551188");
        Assert.assertEquals(dnc.getCall(), true);
        Assert.assertEquals(dnc.getText(), false);

        client.dncApi().delete("12135551188");

        dnc = client.dncApi().get("12135551188");
        Assert.assertEquals(dnc.getCall(), false);
        Assert.assertEquals(dnc.getText(), false);
    }

    @Test
    public void testDeleteDncsFromSource() throws Exception {
        CallfireClient client = getCallfireClient();

        CreateDncsRequest crRequest = CreateDncsRequest.create()
            .call(true)
            .text(true)
            .numbers(Arrays.asList("12135551189"))
            .source("testSourceForDeleteDncs")
            .build();
        client.dncApi().create(crRequest);

        FindDncNumbersRequest request = FindDncNumbersRequest.create()
            .source("testSourceForDeleteDncs")
            .build();

        Page<DoNotContact> dncContacts = client.dncApi().find(request);
        Assert.assertTrue(dncContacts.getItems().size() > 0);

        client.dncApi().deleteDncsFromSource("testSourceForDeleteDncs");

        dncContacts = client.dncApi().find(request);
        Assert.assertTrue(dncContacts.getItems().size() == 0);
    }

    @Test
    public void testFindUniversalDncs() throws Exception {
        CallfireClient client = getCallfireClient();
        FindUniversalDncsRequest request = FindUniversalDncsRequest.create()
            .toNumber("12135551188")
            .fromNumber("18442800143")
            .build();

        List<UniversalDnc> uDncs = client.dncApi().findUniversalDncs(request);
        Assert.assertEquals("18442800143", uDncs.get(0).getFromNumber());
        Assert.assertEquals("12135551188", uDncs.get(0).getToNumber());
        Assert.assertNotNull(uDncs.get(0).isInboundCall());
        Assert.assertNotNull(uDncs.get(0).isOutboundCall());
        Assert.assertNotNull(uDncs.get(0).isInboundText());
        Assert.assertNotNull(uDncs.get(0).isOutboundText());
    }*/

}
