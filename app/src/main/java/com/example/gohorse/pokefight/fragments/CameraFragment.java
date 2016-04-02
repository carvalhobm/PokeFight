package com.example.gohorse.pokefight.fragments;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.gohorse.pokefight.R;

public class CameraFragment extends Fragment implements View.OnClickListener  {

    View view;
    private Context context;

    ImageView iv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater
                .inflate(R.layout.camera_activity,
                        container,
                        false);
        context = getActivity().getApplicationContext();

        view.findViewById(R.id.button).setOnClickListener(this);
        iv=(ImageView)view.findViewById(R.id.imageView);


        return view;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Override
    public void onClick(View view) {

        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bitmap bp = (Bitmap) data.getExtras().get("data");
        iv.setImageBitmap(bp);

    }

}

