package com.example.nisha.doctorapp.GetAppointmentPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("AppointmentHistory")
    @Expose
    private List<AppointmentHistory> appointmentHistory = null;
    @SerializedName("LabHistory")
    @Expose
    private List<LabHistory> labHistory = null;

    public List<AppointmentHistory> getAppointmentHistory() {
        return appointmentHistory;
    }

    public void setAppointmentHistory(List<AppointmentHistory> appointmentHistory) {
        this.appointmentHistory = appointmentHistory;
    }

    public List<LabHistory> getLabHistory() {
        return labHistory;
    }

    public void setLabHistory(List<LabHistory> labHistory) {
        this.labHistory = labHistory;
    }

}
