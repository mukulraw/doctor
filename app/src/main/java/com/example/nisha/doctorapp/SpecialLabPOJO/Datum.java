package com.example.nisha.doctorapp.SpecialLabPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("testName")
    @Expose
    private String testName;
    @SerializedName("testId")
    @Expose
    private String testId;
    @SerializedName("time_slot")
    @Expose
    private String timeSlot;
    @SerializedName("estimated_fee")
    @Expose
    private String estimatedFee;
    @SerializedName("created")
    @Expose
    private String created;

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getEstimatedFee() {
        return estimatedFee;
    }

    public void setEstimatedFee(String estimatedFee) {
        this.estimatedFee = estimatedFee;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
