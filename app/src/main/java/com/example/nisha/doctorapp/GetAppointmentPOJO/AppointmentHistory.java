package com.example.nisha.doctorapp.GetAppointmentPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AppointmentHistory {


    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("doctor_id")
    @Expose
    private String doctorId;
    @SerializedName("doctor_name")
    @Expose
    private String doctorName;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("experience")
    @Expose
    private String experience;
    @SerializedName("designation")
    @Expose
    private String designation;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("patient_id")
    @Expose
    private String patientId;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("slot_id")
    @Expose
    private String slotId;
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

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
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

    public String getSlotId() {
        return slotId;
    }

    public void setSlotId(String slotId) {
        this.slotId = slotId;
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
