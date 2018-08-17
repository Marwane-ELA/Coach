package com.marwane.coach.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.ErrorReason;
import com.google.android.youtube.player.YouTubePlayer.PlaybackEventListener;
import com.google.android.youtube.player.YouTubePlayer.PlayerStateChangeListener;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

import com.marwane.coach.R;

/**
 * Bronnen:
 * https://developers.google.com/youtube/android/player/
 * https://www.youtube.com/watch?v=aJ7BoNG-r2c
 */
public class VideoActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    public static final String API_KEY = "KEY";
    private String VIDEO_ID = "";

    int image_id;
    String name;

    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        /** Initializing YouTube Player View **/
        YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtuber_player);
        youTubePlayerView.initialize(API_KEY, this);


        title = (TextView)findViewById(R.id.titleVideo);

        if(getIntent() != null)
        {
            image_id = getIntent().getIntExtra("image_id",-1);
            name = getIntent().getStringExtra("name");

            //detail_image.setImageResource(image_id);
            title.setText(name);
        }

        switch (name){
            case "Easy Pose":
                VIDEO_ID="zLvJD7iKVhw";
                break;
            case "Cobra Pose":
                VIDEO_ID="XU0wJ0OTopU";
                break;
            case "Downward Facing Dog":
                VIDEO_ID="j97SSGsnCAQ";
                break;
            case "Boat pose":
                VIDEO_ID="BiVJsjPCQeQ";
                break;
            case "half_pigeon":
                VIDEO_ID="3WJam-pECBo";
                break;
            case "Low Lunge":
                VIDEO_ID="aOfniMZY2hk";
                break;
            case "Upward pose":
                VIDEO_ID="birZvF7CcdY";
                break;
            case "Crescent Lunge":
                VIDEO_ID="eXupg3oNGJY";
                break;
            case "Warrior Pose":
                VIDEO_ID="RjHH2er8DkM";
                break;
            case "Bow pose":
                VIDEO_ID="FCuSE4oS9xc";
                break;
            case "Warrior pose 2":
                VIDEO_ID="4Ejz7IgODlU";
                break;
        }


    }

    @Override
    public void onInitializationSuccess(Provider provider, YouTubePlayer player, boolean wasRestored) {
        /** add listeners to YouTubePlayer instance **/
        player.setPlayerStateChangeListener(playerStateChangeListener);
        player.setPlaybackEventListener(playbackEventListener);
        /** Start buffering **/
        if (!wasRestored) {
            player.cueVideo(VIDEO_ID);
        }

    }

    private PlaybackEventListener playbackEventListener = new PlaybackEventListener() {
        @Override
        public void onBuffering(boolean arg0) {
        }
        @Override
        public void onPaused() {
        }
        @Override
        public void onPlaying() {
        }
        @Override
        public void onSeekTo(int arg0) {
        }
        @Override
        public void onStopped() {
        }
    };

    private PlayerStateChangeListener playerStateChangeListener = new PlayerStateChangeListener() {
        @Override
        public void onAdStarted() {
        }
        @Override
        public void onError(ErrorReason arg0) {
        }
        @Override
        public void onLoaded(String arg0) {
        }
        @Override
        public void onLoading() {
        }
        @Override
        public void onVideoEnded() {
        }
        @Override
        public void onVideoStarted() {
        }
    };

    @Override
    public void onInitializationFailure(Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, "Failured to Initialize!", Toast.LENGTH_LONG).show();
    }
}
