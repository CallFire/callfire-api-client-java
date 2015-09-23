package com.callfire.api.client.api.integration.contacts;

import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.contacts.model.DoNotContact;
import com.callfire.api.client.api.contacts.model.request.FindDncContactsRequest;
import com.callfire.api.client.api.integration.AbstractIntegrationTest;
import org.junit.Assert;
import org.junit.Test;

/**
 * integration tests for /contacts/do-not-calls api endpoint
 */
public class DncApiTest extends AbstractIntegrationTest {

    @Test
    public void testFindDncContacts() throws Exception {
        FindDncContactsRequest request = FindDncContactsRequest.create()
            .textDnc(true)
            .limit(1L)
            .build();
        CallfireClient client = getCallfireClient();
        Page<DoNotContact> dncContacts = client.getContactsApi().getDncApi().findDncContacts(request);
        Assert.assertEquals(1, dncContacts.getItems().size());

        System.out.println(dncContacts);
    }

    @Test
    public void testUpdateDncNumber() throws Exception {
        CallfireClient client = getCallfireClient();
        DoNotContact number = new DoNotContact();
        number.setListId(1700040003L);
        number.setNumber("16505541938");
        number.setCall(true);
        number.setText(true);
        client.getContactsApi().getDncApi().updateDncNumber(number);
    }
}
