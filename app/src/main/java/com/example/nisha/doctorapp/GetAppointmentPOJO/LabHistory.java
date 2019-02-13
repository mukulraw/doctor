package com.example.nisha.doctorapp.GetAppointmentPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LabHistory {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("test_id")
    @Expose
    private String testId;
    @SerializedName("test_name")
    @Expose
    private String testName;
    @SerializedName("patient_id")
    @Expose
    private String patientId;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("test_slot_id")
    @Expose
    private String testSlotId;
    @SerializedName("time_slot")
    @Expose
    private String timeSlot;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("created")
    @Expose
    private String created;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTestSlotId() {
        return testSlotId;
    }

    public void setTestSlotId(String testSlotId) {
        this.testSlotId = testSlotId;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
