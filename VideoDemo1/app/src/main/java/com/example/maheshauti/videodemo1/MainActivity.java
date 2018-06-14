package com.example.maheshauti.videodemo1;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private int SELECT_VIDEO=1;
    private String selectedVideoPath;
    VideoView videoView;
    Uri videoUri;
    MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView=(VideoView)findViewById(R.id.videoView);
        mediaController=new MediaController(this);
    }

    public void pickTheVideo(View view) {

        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, SELECT_VIDEO);
    }
    @ Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_VIDEO) {
                selectedVideoPath = getPath(data.getData());
                videoUri=data.getData();
                if(selectedVideoPath == null) {
                    Log.e("path:","selected video path = null!");

                } else {
                    /**
                     * try to do something there
                     * selectedVideoPath is path to the selected video
                     */

                    System.out.println("uri is : "+data.getData());
                    System.out.println("path is :"+selectedVideoPath);
                    Toast.makeText(this,"video picked is"+selectedVideoPath,Toast.LENGTH_LONG).show();

                }
            }
        }

    }
    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if(cursor!=null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        else return null;
    }

    public void playVideo(View view) {
        if(selectedVideoPath!=null){
//            File file = new File(selectedVideoPath);
//            Intent intent = new Intent(Intent.ACTION_VIEW);
//            intent.setDataAndType(Uri.fromFile(file), "video/*");
//            startActivity(intent);
            videoView.setVisibility(View.VISIBLE);
            videoView.setVideoURI(videoUri);
            videoView.setMediaController(mediaController);
            mediaController.setAnchorView(videoView);
            videoView.start();
        }
        else {
            Toast.makeText(this,"please select the video...",Toast.LENGTH_LONG).show();
        }

    }

    public void openExoPlayerActivity(View view) {
        EditText urlTXT=(EditText)findViewById(R.id.urlTXT);
        Intent intent=new Intent(this,Exoplayer.class);
        if(selectedVideoPath==null&& urlTXT.getText().length()!=0 ){

            intent.putExtra("urlTXT", urlTXT.getText().toString());
            startActivity(intent);

        }
        else if(selectedVideoPath!=null&& urlTXT.getText().length()==0){
            intent.putExtra("uri", videoUri.toString());
            startActivity(intent);
        }
        else if(selectedVideoPath!=null && urlTXT.getText().length()!=0){
            Toast.makeText(this," both links are present playing local video",Toast.LENGTH_LONG).show();
            intent.putExtra("uri", videoUri.toString());
            startActivity(intent);

        }
        else if(urlTXT.getText().length()==0&&selectedVideoPath==null)
            {
            Toast.makeText(this,"invalid url",Toast.LENGTH_LONG).show();
        }



    }
}
