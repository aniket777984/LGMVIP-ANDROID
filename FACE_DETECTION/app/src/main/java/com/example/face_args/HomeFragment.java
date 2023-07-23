package com.example.face_args;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.face.Face;
import com.google.mlkit.vision.face.FaceDetector;
import com.google.mlkit.vision.face.FaceDetectorOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class HomeFragment extends Fragment {

    Button moveToImageFragment;
    NavController navController;
//    List<FaceModel> faceModels;
    private Button cameraButton;

    private final static int REQUEST_IMAGE_CAPTURE = 124;
    private InputImage image;
    private FaceDetector detector;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        navController = Navigation.findNavController(view);
        FirebaseApp.initializeApp(getContext());
        cameraButton = view.findViewById(R.id.camera_button);

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // making a new intent for opening camera
                Intent intent = new Intent(
                        MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(
                        getActivity().getPackageManager())
                        != null) {
                    startActivityForResult(
                            intent, REQUEST_IMAGE_CAPTURE);
                }
                else {

                    Toast
                            .makeText(
                                    getContext(),
                                    "Something went wrong",
                                    Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE
                && resultCode == RESULT_OK) {
            assert data != null;
            Bundle extra = data.getExtras();
            Bitmap bitmap = (Bitmap) extra.get("data");
            AnalyzeFaces(bitmap);
        }
    }

    private void AnalyzeFaces(Bitmap bitmap)
    {
        FaceDetectorOptions options
                = new FaceDetectorOptions.Builder()
                .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_ACCURATE)
                .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_ALL)
                .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL)
                .build();
        try {

            image = InputImage.fromBitmap(bitmap,0);
            detector = com.google.mlkit.vision.face.FaceDetection.getClient(options);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        Task<List<Face>> result =
                detector.process(image)
                        .addOnSuccessListener(
                                faces -> {
                                    List<FaceModel> faceModelList=new ArrayList<>();
                                    int i = 1;
                                    for (Face face : faces) {
                                        FaceModel faceModel = new FaceModel();
                                        faceModel.setFaceId(i);
                                        faceModel.setAngleX(Math.round(face.getHeadEulerAngleX() * Math.pow(10, 3)) / Math.pow(10, 3));
                                        faceModel.setAngleY(Math.round(face.getHeadEulerAngleY() * Math.pow(10, 3)) / Math.pow(10, 3));
                                        faceModel.setAngleZ(Math.round(face.getHeadEulerAngleZ() * Math.pow(10, 3)) / Math.pow(10, 3));
                                        faceModel.setSmile(Math.round(face.getSmilingProbability()*100 * Math.pow(10, 3)) / Math.pow(10, 3));
                                        faceModel.setLeftEye(Math.round(face.getLeftEyeOpenProbability()*100 * Math.pow(10, 3)) / Math.pow(10, 3));
                                        faceModel.setRightEye(Math.round(face.getRightEyeOpenProbability()*100 * Math.pow(10, 3)) / Math.pow(10, 3));
                                        faceModelList.add(faceModel);
                                        i++;


                                    }if (faces.size() == 0) {
                                        Toast.makeText(getContext(),"NO FACES DETECTED",Toast.LENGTH_SHORT).show();
                                    }else {
                                        FaceModelList faceModels = new FaceModelList(bitmap, faceModelList);
                                        HomeFragmentDirections.ActionHomeFragmentToImageFragment action = HomeFragmentDirections.actionHomeFragmentToImageFragment();
                                        action.setFaceList(faceModels);
                                        navController.navigate(action);
                                    }

                                })
                        .addOnFailureListener(
                                e -> Toast.makeText(getContext(),"Exception Occurred : "+e,Toast.LENGTH_SHORT).show());
    }

}