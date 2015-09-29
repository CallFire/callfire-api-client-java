package com.callfire.api.client.api.contacts;

import com.callfire.api.client.JsonConverter;
import com.callfire.api.client.api.contacts.model.Contact;
import com.callfire.api.client.api.contacts.model.request.AddContactListItemsRequest;
import com.callfire.api.client.api.contacts.model.request.CreateContactListRequest;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

public class ContactsApiTest {

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
}
