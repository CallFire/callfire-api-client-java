package com.callfire.api.client.integration.contacts;

import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.ResourceId;
import com.callfire.api.client.api.common.model.request.GetByIdRequest;
import com.callfire.api.client.api.contacts.ContactListsApi;
import com.callfire.api.client.api.contacts.model.Contact;
import com.callfire.api.client.api.contacts.model.ContactList;
import com.callfire.api.client.api.contacts.model.request.AddContactListItemsRequest;
import com.callfire.api.client.api.contacts.model.request.CreateContactListRequest;
import com.callfire.api.client.api.contacts.model.request.FindContactListsRequest;
import com.callfire.api.client.api.contacts.model.request.UpdateContactListRequest;
import com.callfire.api.client.integration.AbstractIntegrationTest;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * integration tests for /contacts/lists api endpoint
 */
public class ContactListsApiTest extends AbstractIntegrationTest {

    @Test
    public void testFindContactLists() throws Exception {
        CallfireClient client = getCallfireClient();

        FindContactListsRequest request = FindContactListsRequest.create().build();
        Page<ContactList> contactLists = client.contactListsApi().find(request);
        System.out.println(contactLists);
    }

    @Test
    public void testCreateContactListFromFile() throws Exception {
        CallfireClient client = getCallfireClient();
        File file = new File(getClass().getClassLoader().getResource("file-examples/contacts1.csv").toURI());
        ContactListsApi api = client.contactListsApi();
        ResourceId id = api.createFromCsv("fileList", file);

        ContactList contactList = api.get(id.getId());
        System.out.println(contactList);
        assertEquals(Integer.valueOf(2), contactList.getSize());
        assertEquals("fileList", contactList.getName());
    }

    @Test
    public void testContactListCRUD() throws Exception {
        CallfireClient client = getCallfireClient();
        ContactListsApi api = client.contactListsApi();

        // create from numbers
        CreateContactListRequest request = CreateContactListRequest.<String>create()
            .name("listFromNumbers")
            .contacts(asList(getDid2(), getDid3()))
            .build();
        ResourceId numbersListId = api.create(request);
        ContactList contactList = api.get(numbersListId.getId());
        assertEquals("listFromNumbers", contactList.getName());
        assertEquals(Integer.valueOf(2), contactList.getSize());

        // get items
        GetByIdRequest getItemsRequest = GetByIdRequest.create()
            .id(numbersListId.getId())
            .build();
        Page<Contact> contactListItems = api.getListItems(getItemsRequest);
        System.out.println(contactListItems);
        List<Contact> items = contactListItems.getItems();
        assertEquals(2, items.size());

        // create from ids
        request = CreateContactListRequest.<Long>create()
            .name("listFromExistingContacts")
            .contacts(asList(items.get(0).getId(), items.get(1).getId()))
            .build();
        ResourceId contactsListId = api.create(request);

        FindContactListsRequest findRequest = FindContactListsRequest.create()
            .name("listFrom")
            .build();
        Page<ContactList> contactLists = api.find(findRequest);
        assertThat(contactLists.getTotalCount(), greaterThan(1L));
        System.out.println(contactLists);

        // update
        UpdateContactListRequest updateListRequest = UpdateContactListRequest.create()
            .id(contactsListId.getId())
            .name("new_name")
            .build();
        api.update(updateListRequest);
        ContactList updatedList = api.get(contactsListId.getId());
        assertEquals("new_name", updatedList.getName());

        // delete
        api.delete(numbersListId.getId());
        api.delete(contactsListId.getId());
    }

    @Test
    public void testContactListItemsCRUD() throws Exception {
        CallfireClient client = getCallfireClient();
        ContactListsApi api = client.contactListsApi();
        // create from dto
        Contact c1 = new Contact();
        c1.setFirstName("name1");
        c1.setHomePhone("12345678901");
        Contact c2 = new Contact();
        c2.setFirstName("name2");
        c2.setHomePhone("12345678902");
        CreateContactListRequest request = CreateContactListRequest.<Contact>create()
            .name("listFromContacts")
            .contacts(asList(c1, c2))
            .build();
        ResourceId id = api.create(request);

        AddContactListItemsRequest addItemsRequest = AddContactListItemsRequest.<String>create()
            .contactListId(id.getId())
            .contacts(asList("12345543211"))
            .build();
        api.addListItems(addItemsRequest);

        GetByIdRequest getItemsRequest = GetByIdRequest.create().id(id.getId()).build();
        Page<Contact> contactListItems = api.getListItems(getItemsRequest);
        List<Contact> items = contactListItems.getItems();
        assertEquals(3, items.size());

        api.removeListItem(id.getId(), items.get(0).getId());
        contactListItems = api.getListItems(getItemsRequest);
        items = contactListItems.getItems();
        assertEquals(2, items.size());

        api.removeListItems(id.getId(), asList(items.get(0).getId(), items.get(1).getId()));
        contactListItems = api.getListItems(getItemsRequest);
        assertEquals(0, contactListItems.getItems().size());

        api.delete(id.getId());
    }
}
