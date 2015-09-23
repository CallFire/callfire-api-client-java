package com.callfire.api.client.api.integration.contacts;

import com.callfire.api.client.api.contacts.model.Contact;
import com.callfire.api.client.api.contacts.model.request.AddContactListItemsRequest;
import com.callfire.api.client.api.contacts.model.request.CreateContactListRequest;
import com.callfire.api.client.api.integration.AbstractIntegrationTest;
import org.junit.Test;

import java.util.Arrays;

/**
 * integration tests for /contacts/lists api endpoint
 */
public class DncListsApiTest extends AbstractIntegrationTest {

    @Test
    public void testAddContactList() throws Exception {

        AddContactListItemsRequest<Contact> request = AddContactListItemsRequest.<Contact>create()
            .contactNumbersField("")
            .contacts(Arrays.asList(new Contact(), new Contact()))
            .build();

        CreateContactListRequest.<Long>create()
            .name("name")
            .contacts(Arrays.asList(1L, 2L))
            .build();

    }
}
