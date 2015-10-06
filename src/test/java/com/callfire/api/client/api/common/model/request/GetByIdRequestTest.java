package com.callfire.api.client.api.common.model.request;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class GetByIdRequestTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Test
    public void testBuildValidation() throws Exception {
        ex.expectMessage("request.id cannot be null");
        ex.expect(NullPointerException.class);
        GetByIdRequest.create()
            .offset(1L)
            .limit(5L)
            .build();
    }
}
