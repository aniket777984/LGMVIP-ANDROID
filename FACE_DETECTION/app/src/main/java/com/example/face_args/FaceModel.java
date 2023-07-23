package com.example.face_args;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class FaceModel{

    int FaceId;

    double AngleX;
    double AngleY;
    double AngleZ;

    double Smile;
    double LeftEye;
    double RightEye;



    public int getFaceId() {
        return FaceId;
    }

    public void setFaceId(int faceId) {
        FaceId = faceId;
    }

    public double getAngleX() {
        return AngleX;
    }

    public void setAngleX(double angleX) {
        AngleX = angleX;
    }

    public double getAngleY() {
        return AngleY;
    }

    public void setAngleY(double angleY) {
        AngleY = angleY;
    }

    public double getAngleZ() {
        return AngleZ;
    }

    public void setAngleZ(double angleZ) {
        AngleZ = angleZ;
    }

    public double getSmile() {
        return Smile;
    }

    public void setSmile(double smile) {
        Smile = smile;
    }

    public double getLeftEye() {
        return LeftEye;
    }

    public void setLeftEye(double leftEye) {
        LeftEye = leftEye;
    }

    public double getRightEye() {
        return RightEye;
    }

    public void setRightEye(double rightEye) {
        RightEye = rightEye;
    }



}
