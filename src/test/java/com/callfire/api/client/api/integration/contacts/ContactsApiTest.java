package com.callfire.api.client.api.integration.contacts;

import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.ResourceIds;
import com.callfire.api.client.api.contacts.model.Contact;
import com.callfire.api.client.api.contacts.model.ContactHistory;
import com.callfire.api.client.api.contacts.model.request.FindContactsRequest;
import com.callfire.api.client.api.contacts.model.request.GetContactHistoryRequest;
import com.callfire.api.client.api.integration.AbstractIntegrationTest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * integration tests for /contacts api endpoint
 */
public class ContactsApiTest extends AbstractIntegrationTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Test
    public void testFindContacts() throws Exception {
        FindContactsRequest request = FindContactsRequest.create()
            .number(asList("16506190257", "18778973473"))
            .id(asList(413858626003L, 425456525003L))
            .build();
        CallfireClient client = getCallfireClient();
        Page<Contact> contacts = client.getContactsApi().findContacts(request);
        System.out.println(contacts);

        assertEquals(2, contacts.getItems().size());
        assertEquals(getCallerId(), contacts.getItems().get(0).getHomePhone());
    }

    @Test
    public void testContactsCRUD() throws Exception {
        Contact contact1 = new Contact();
        contact1.setHomePhone("12345678901");
        contact1.setFirstName("firstName");
        contact1.setLastName("lastName");
        contact1.setLabels(asList("label1", "label2"));

        Contact contact2 = new Contact();
        contact2.setHomePhone("12345678902");
        CallfireClient client = getCallfireClient();
        ResourceIds contacts = client.getContactsApi().createContacts(asList(contact1, contact2));
        System.out.println(contacts);

        List<Long> contactIds = contacts.getIds();
        assertEquals(2, contactIds.size());

        Contact savedContact1 = client.getContactsApi().getContact(contactIds.get(0));
        System.out.println(savedContact1);
        assertEquals("12345678901", savedContact1.getHomePhone());
        assertEquals("firstName", savedContact1.getFirstName());
        assertEquals("lastName", savedContact1.getLastName());
        // TODO vmikhailov uncomment when will be implemented on server-side
        //    assertEquals(asList("label1", "label2"), savedContact1.getLabels());

        contact2.setId(contactIds.get(1));
        contact2.setFirstName("contact2");
        contact2.setZipcode("12345");
        Map<String, String> properties = new HashMap<>();
        properties.put("key1", "value1");
        properties.put("key2", "value2");
        contact2.setProperties(properties);
        client.getContactsApi().updateContact(contact2);

        Contact savedContact2 = client.getContactsApi().getContact(contact2.getId(), "homePhone,zipcode,properties");
        System.out.println(savedContact2);
        assertNull(savedContact2.getFirstName());
        assertEquals("12345678902", savedContact2.getHomePhone());
        assertEquals("12345", savedContact2.getZipcode());
        assertEquals(properties, savedContact2.getProperties());

        client.getContactsApi().deleteContact(contactIds.get(0));
        Contact contact = client.getContactsApi().getContact(contactIds.get(0), "id,deleted");
        assertTrue(contact.getDeleted());
    }

    @Test
    public void testGetContactHistory() throws Exception {
        CallfireClient client = getCallfireClient();
        GetContactHistoryRequest request = GetContactHistoryRequest.create()
            .id(413858626003L)
            .build();
        ContactHistory contactHistory = client.getContactsApi().getContactHistory(request);
        System.out.println(contactHistory);
    }
}
