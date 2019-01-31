package com.example.nisha.doctorapp;

import com.example.nisha.doctorapp.ChangePasswordPOJO.ChangeBean;
import com.example.nisha.doctorapp.DoctorsPOJO.DoctorBean;
import com.example.nisha.doctorapp.EditProfilePOJO.EditProfileBean;
import com.example.nisha.doctorapp.ForgetPOJO.ForgetBean;
import com.example.nisha.doctorapp.LabDetailsPOJO.LabDetailBean;
import com.example.nisha.doctorapp.LocationPOJO.LocationBean;
import com.example.nisha.doctorapp.LoginPOJO.LoginBean;
import com.example.nisha.doctorapp.ProfilePOJO.ProfileBean;
import com.example.nisha.doctorapp.SignupPOJO.SignupBean;
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






}
