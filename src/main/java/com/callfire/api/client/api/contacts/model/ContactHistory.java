package com.callfire.api.client.api.contacts.model;

import com.callfire.api.client.api.common.model.CallfireModel;
import com.callfire.api.client.model.Call;
import com.callfire.api.client.model.Text;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class ContactHistory extends CallfireModel {
    private Long id;
    private List<Call> calls = new ArrayList<>();
    private List<Text> texts = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Call> getCalls() {
        return calls;
    }

    public void setCalls(List<Call> calls) {
        this.calls = calls;
    }

    public List<Text> getTexts() {
        return texts;
    }

    public void setTexts(List<Text> texts) {
        this.texts = texts;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("id", id)
            .append("calls", calls)
            .append("texts", texts)
            .toString();
    }
}
