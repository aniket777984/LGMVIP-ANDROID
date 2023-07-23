package com.example.face_args;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;

public class FaceModelList implements Parcelable {

    Bitmap bitmap;

    List<FaceModel> faceModelList;

    public FaceModelList(Bitmap bitmap, List<FaceModel> faceModelList) {
        this.bitmap = bitmap;
        this.faceModelList = faceModelList;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    protected FaceModelList(Parcel in) {
    }

    public static final Creator<FaceModelList> CREATOR = new Creator<FaceModelList>() {
        @Override
        public FaceModelList createFromParcel(Parcel in) {
            return new FaceModelList(in);
        }

        @Override
        public FaceModelList[] newArray(int size) {
            return new FaceModelList[size];
        }
    };

    public List<FaceModel> getFaceModelList() {
        return faceModelList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
    }
}
