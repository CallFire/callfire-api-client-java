package com.callfire.api.client.integration.contacts;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.ResourceId;
import com.callfire.api.client.api.common.model.request.GetByIdRequest;
import com.callfire.api.client.api.contacts.model.Contact;
import com.callfire.api.client.api.contacts.model.ContactHistory;
import com.callfire.api.client.api.contacts.model.request.FindContactsRequest;
import com.callfire.api.client.integration.AbstractIntegrationTest;

/**
 * integration tests for /contacts api endpoint
 */
public class ContactsApiTest extends AbstractIntegrationTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Test
    public void testFindContacts() throws Exception {
        FindContactsRequest request = FindContactsRequest.create()
            .build();
        CallfireClient client = getCallfireClient();
        Page<Contact> contacts = client.contactsApi().find(request);
        System.out.println(contacts);
        assertEquals(100, contacts.getItems().size());

        Contact cnt = contacts.getItems().get(0);
        String numberForSearching = null;

        if (cnt.getHomePhone() != null)
            numberForSearching = cnt.getHomePhone();
        else if (cnt.getMobilePhone() != null)
            numberForSearching = cnt.getMobilePhone();
        else if (cnt.getWorkPhone() != null)
            numberForSearching = cnt.getMobilePhone();

        if (numberForSearching != null) {
            request = FindContactsRequest.create()
                .number(asList(numberForSearching))
                .build();
            contacts = client.contactsApi().find(request);
            assertTrue(numberForSearching.equals(contacts.getItems().get(0).getWorkPhone()) ||
                       numberForSearching.equals(contacts.getItems().get(0).getHomePhone()) ||
                       numberForSearching.equals(contacts.getItems().get(0).getMobilePhone()));
            assertEquals(1, contacts.getItems().size());
        }
    }

    @Test
    public void testContactsCRUD() throws Exception {
        Contact contact1 = new Contact();
        contact1.setHomePhone("12345678901");
        contact1.setFirstName("firstName");
        contact1.setLastName("lastName");

        Contact contact2 = new Contact();
        contact2.setHomePhone("12345678902");
        CallfireClient client = getCallfireClient();
        List<ResourceId> contacts = client.contactsApi().create(asList(contact1, contact2));
        System.out.println(contacts);

        assertEquals(2, contacts.size());

        Contact savedContact1 = client.contactsApi().get(contacts.get(0).getId());
        System.out.println(savedContact1);
        assertEquals("12345678901", savedContact1.getHomePhone());
        assertEquals("firstName", savedContact1.getFirstName());
        assertEquals("lastName", savedContact1.getLastName());

        contact2.setId(contacts.get(1).getId());
        contact2.setFirstName("contact2");
        contact2.setZipcode("12345");
        Map<String, String> properties = new HashMap<>();
        properties.put("key1", "value1");
        properties.put("key2", "value2");
        contact2.setProperties(properties);
        client.contactsApi().update(contact2);

        Contact savedContact2 = client.contactsApi().get(contact2.getId(), "homePhone,zipcode,properties");
        System.out.println(savedContact2);
        assertNull(savedContact2.getFirstName());
        assertEquals("12345678902", savedContact2.getHomePhone());
        assertEquals("12345", savedContact2.getZipcode());
        assertEquals(properties, savedContact2.getProperties());

        client.contactsApi().delete(contacts.get(0).getId());
        Contact contact = client.contactsApi().get(contacts.get(0).getId(), "id,deleted");
        assertTrue(contact.getDeleted());
    }

    @Test
    public void testGetContactHistory() throws Exception {
        CallfireClient client = getCallfireClient();
        GetByIdRequest request = GetByIdRequest.create()
            .id(466225637003L)
            .build();
        ContactHistory contactHistory = client.contactsApi().getHistory(request);
        assertFalse(contactHistory.getCalls().isEmpty());

        System.out.println(contactHistory);
    }
}
