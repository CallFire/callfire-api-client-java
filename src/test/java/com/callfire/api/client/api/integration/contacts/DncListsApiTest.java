package com.callfire.api.client.api.integration.contacts;

import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.ResourceId;
import com.callfire.api.client.api.common.model.request.GetByIdRequest;
import com.callfire.api.client.api.contacts.DncListsApi;
import com.callfire.api.client.api.contacts.model.DncList;
import com.callfire.api.client.api.contacts.model.DoNotContact;
import com.callfire.api.client.api.contacts.model.UniversalDnc;
import com.callfire.api.client.api.contacts.model.request.AddDncListItemsRequest;
import com.callfire.api.client.api.contacts.model.request.FindDncListsRequest;
import com.callfire.api.client.api.integration.AbstractIntegrationTest;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * integration tests for /contacts/do-not-calls/lists api endpoint
 */
public class DncListsApiTest extends AbstractIntegrationTest {

    @Test
    public void testDncContactListWithItemsCRUD() throws Exception {
        CallfireClient client = getCallfireClient();
        DncListsApi api = client.getDncListsApi();

        DncList dncList = new DncList();
        dncList.setName("dncList1");
        ResourceId dncListId = api.createDncList(dncList);
        DncList created = api.getDncList(dncListId.getId());
        assertEquals("dncList1", created.getName());
        assertThat(created.getCreated(), greaterThan(DateUtils.addMinutes(new Date(), -3)));

        FindDncListsRequest findRequest = FindDncListsRequest.create()
            .name("dncList1")
            .build();
        Page<DncList> doNotCallLists = api.findDoNotCallLists(findRequest);
        assertThat(doNotCallLists.getTotalCount(), greaterThan(0L));
        System.out.println(doNotCallLists);

        DoNotContact dnc1 = new DoNotContact();
        dnc1.setNumber("12135543211");
        dnc1.setText(true);
        dnc1.setCall(false);
        DoNotContact dnc2 = new DoNotContact();
        dnc2.setNumber("12135543212");
        dnc2.setText(true);
        dnc2.setCall(false);
        DoNotContact dnc3 = new DoNotContact();
        dnc3.setNumber("12135543213");
        dnc3.setText(true);
        dnc3.setCall(false);
        AddDncListItemsRequest addItemsRequest = AddDncListItemsRequest.<DoNotContact>create()
            .contactListId(dncListId.getId())
            .contacts(asList(dnc1, dnc2, dnc3))
            .build();
        api.addDncListItems(addItemsRequest);

        // get items
        GetByIdRequest getItemsRequest = GetByIdRequest.create()
            .id(dncListId.getId())
            .build();
        Page<DoNotContact> dncListItems = api.getDncListItems(getItemsRequest);
        List<DoNotContact> items = dncListItems.getItems();
        assertEquals(3, items.size());

        api.removeDncListItem(dncListId.getId(), "12135543211");
        dncListItems = api.getDncListItems(getItemsRequest);
        items = dncListItems.getItems();
        assertEquals(2, items.size());

        api.removeDncListItems(dncListId.getId(), asList("12135543212", "12135543213"));
        dncListItems = api.getDncListItems(getItemsRequest);
        assertEquals(0, dncListItems.getItems().size());

        // delete
        api.deleteDncList(dncListId.getId());

        List<UniversalDnc> universalDncNumber = api.getUniversalDncNumber(getCallerId());
        System.out.println("universal: " + universalDncNumber);
    }
}
