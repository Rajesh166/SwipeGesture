package com.android.busroutes.sample.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.busroutes.sample.R;
import com.android.busroutes.sample.model.ImageInfo;
import com.bumptech.glide.Glide;

public class ImageDetailsActivity extends AppCompatActivity {

    public static final String ARG_ROUTE = "route";
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_details);
        ImageInfo imageInfo = getIntent().getExtras().getParcelable(ARG_ROUTE);
        ImageView routeImage = (ImageView) findViewById(R.id.route_image);
        final TextView description = (TextView) findViewById(R.id.description);

        if (imageInfo != null) {
            setTitle(imageInfo.getName());
            Glide.with(this)
                    .load(imageInfo.getImage())
                    .into(routeImage);
            description.setText(imageInfo.getDescription());
        } else {
            description.setText("N/A");
        }

        routeImage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (description.getVisibility() == View.VISIBLE) {
                    description.setVisibility(View.GONE);
                } else  {
                    description.setVisibility(View.VISIBLE);
                }
                return false;
            }
        });

        gestureDetector = new GestureDetector(this, new SwipeDetector());
    }


    private class SwipeDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH) {
                finish();
                return true;
            }

            if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                finish();
                return true;
            }

            return false;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // TouchEvent dispatcher.
        if (gestureDetector != null) {
            if (gestureDetector.onTouchEvent(ev)) {
                return true;
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

}
