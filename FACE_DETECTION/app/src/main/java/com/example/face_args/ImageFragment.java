package com.example.face_args;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ImageFragment extends Fragment {

    Button openDialogBtn;
    List<FaceModel> faceList;

    public ImageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_image, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        openDialogBtn = view.findViewById(R.id.diaglogopenbtn);
        faceList = new ArrayList<>();

        if(getArguments() != null)
        {
            ImageFragmentArgs  args = ImageFragmentArgs.fromBundle(getArguments());
            FaceModelList faceModelList = args.getFaceList();

            assert faceModelList != null;
            faceList = faceModelList.getFaceModelList();
            Bitmap bitmap = faceModelList.getBitmap();

        }
        else
        {
            Toast.makeText(getContext(),"Not able to fetch arguments", Toast.LENGTH_SHORT).show();
        }

        openDialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment resultDialog
                        = new ResultDialog(getContext(),faceList);
                resultDialog.setCancelable(true);
                resultDialog.show(
                        getActivity().getSupportFragmentManager(),
                        FaceDetection.RESULT_DIALOG);
            }
        });

    }
}