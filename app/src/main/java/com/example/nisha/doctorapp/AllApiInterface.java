package com.example.nisha.doctorapp;

import com.example.nisha.doctorapp.ChangePasswordPOJO.ChangeBean;
import com.example.nisha.doctorapp.DoctorInsertPOJO.DoctorInsertBean;
import com.example.nisha.doctorapp.DoctorsPOJO.DoctorBean;
import com.example.nisha.doctorapp.EditProfilePOJO.EditProfileBean;
import com.example.nisha.doctorapp.ForgetPOJO.ForgetBean;
import com.example.nisha.doctorapp.FreeSlotLabsPOJO.FreeLAbsBean;
import com.example.nisha.doctorapp.FreeSlotPOJO.FreeslotBean;
import com.example.nisha.doctorapp.GetAppointmentPOJO.AppointmentBean;
import com.example.nisha.doctorapp.GetFeePOJO.GetBean;
import com.example.nisha.doctorapp.GetLabfeePOJO.GetLabBean;
import com.example.nisha.doctorapp.LabDetailsPOJO.LabDetailBean;
import com.example.nisha.doctorapp.LocationPOJO.LocationBean;
import com.example.nisha.doctorapp.LoginPOJO.LoginBean;
import com.example.nisha.doctorapp.ProfilePOJO.ProfileBean;
import com.example.nisha.doctorapp.ScheduleLabPOJO.SchedulelabBean;
import com.example.nisha.doctorapp.SignupPOJO.SignupBean;
import com.example.nisha.doctorapp.SpecialLabPOJO.SpeciallabBean;
import com.example.nisha.doctorapp.TestPOJO.TestBean;
import com.example.nisha.doctorapp.UpdateProfilePOJO.UpdateProfileBean;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface AllApiInterface {

    @Multipart
    @POST("doctor/api/sign_up.php")
    Call<SignupBean> signup(
            @Part("name") String mclass,
            @Part("email") String username,
            @Part("phone") String gender,
            @Part("gender") String age,
            @Part("dob") String email,
            @Part("password") String password
    );


    @Multipart
    @POST("doctor/api/login.php")
    Call<LoginBean> login(
            @Part("email") String mclass,
            @Part("password") String username
    );


    @Multipart
    @POST("doctor/api/forgotPassword.php")
    Call<ForgetBean> forgot
            (@Part("email") String email);


    @Multipart
    @POST("doctor/api/changePassword.php")
    Call<ChangeBean> chanage(
            @Part("userId") String mclass,
            @Part("password") String username
    );


    @Multipart
    @POST("doctor/api/getProfile.php")
    Call<ProfileBean> profile
            (@Part("userId") String userid);


    @Multipart
    @POST("doctor/api/updateProfile.php")
    Call<EditProfileBean> edit(
            @Part("userId") String dsa,
            @Part("name") String dsd ,
            @Part("gender") String dsad ,
            @Part("age") String usern
    );



    @Multipart
    @POST ("doctor/api/updateProfilePic.php")
    Call<UpdateProfileBean> updatebean(
            @Part("userId") String userid,
            @Part MultipartBody.Part file
    );




    @GET("doctor/api/getLocation.php")
    Call<LocationBean> getLocations();

    @GET("doctor/api/getDoctors.php")
    Call<DoctorBean> doctorprofile();

    @GET("doctor/api/getLabs.php")
    Call<LabDetailBean> labdetail();


    @Multipart
    @POST ("doctor/api/getDoctorSlot.php")
    Call<FreeslotBean> free(
            @Part("doctor_id") String userid,
            @Part("date") String sdlf
    );



    @Multipart
    @POST ("doctor/api/ScheduleAnAppointment.php")
    Call<DoctorInsertBean> insert(
            @Part("doctor_id") String userid,
            @Part("user_id") String sdlf ,
            @Part("date") String f ,
            @Part("slot_id") String sd,
            @Part("fee") String fds
    );



    @Multipart
    @POST ("doctor/api/GetTestSlot.php")
    Call<FreeLAbsBean> freelab(
            @Part("testId") String test,
            @Part("date") String date
    );

    @Multipart
    @POST ("doctor/api/ScheduleLabAppointment.php")
    Call<SchedulelabBean> schedule(
            @Part("testId") String userid,
            @Part("user_id") String sdlf ,
            @Part("date") String f ,
            @Part("slot_id") String sd ,
            @Part("fee") String dfs
    );



    @GET("doctor/api/master_test.php")
    Call<TestBean> test();


    @Multipart
    @POST ("doctor/api/GetAppointmentHistory.php")
    Call<AppointmentBean> appointment(
            @Part("user_id") String userid
    );


    @Multipart
    @POST ("doctor/api/GetDrFeeBySlotId.php")
    Call<GetBean> fees(
            @Part("doctor_id") String sa ,
            @Part("slotId") String sfdaf
    );


    @Multipart
    @POST ("doctor/api/GetTestFeeBySlotId.php")
    Call<GetLabBean> labfee(
            @Part("testId") String sa ,
            @Part("slotId") String sfdaf
    );


    @Multipart
    @POST ("doctor/api/GetTestData.php")
    Call<SpeciallabBean> spec(
            @Part("testId") String sa
    );









}
