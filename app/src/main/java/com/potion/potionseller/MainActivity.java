package com.potion.potionseller;

import android.app.ActionBar;
import android.content.ClipData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private ImageView potion1, cauldron;
    private boolean cauldronBox = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        potion1 = (ImageView)findViewById(R.id.potion1);
        cauldron = (ImageView)findViewById(R.id.cauldron);

        potion1.setOnTouchListener(new TouchListener());
        cauldron.setOnDragListener(new DragListener());
    }

    private final class TouchListener implements View.OnTouchListener{
        public boolean onTouch(View view, MotionEvent motionEvent){
            //LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)potion1.getLayoutParams();
            if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                //start dragging the item touched
                view.startDrag(data, shadowBuilder, view, 0);
                //potion1.setVisibility(View.INVISIBLE);
                return true;
            }
            else if(motionEvent.getAction() == MotionEvent.ACTION_MOVE){
                /*int moveX = (int)motionEvent.getX();
                int moveY = (int)motionEvent.getY();

                view.setTranslationX(moveX);
                view.setTranslationY(moveY);*/

                return true;
            }
            else{
                potion1.setVisibility(View.VISIBLE);
                return false;
            }

            /*
            switch (motionEvent.getAction()){
                case MotionEvent.ACTION_DOWN: {
                    final float movex = motionEvent.getX();
                }
            }
            return false;
             */
        }
    }

    private class DragListener implements View.OnDragListener{
        @Override
        public boolean onDrag(View v, DragEvent event){

            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    cauldron.setImageResource(R.drawable.cauldronactive);
                    cauldronBox = false;
                    potion1.setVisibility(View.INVISIBLE);
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    cauldronBox = true;
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    cauldronBox = false;
                    break;
                case DragEvent.ACTION_DROP:
                    //handle the dragged view being dropped over a drop view
                    cauldron.setImageResource(R.drawable.cauldronactivated);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    if(cauldronBox == false) {
                        cauldron.setImageResource(R.drawable.cauldron);
                    }
                    potion1.setVisibility(View.VISIBLE);
                    break;
                default:
                    break;
            }

            return true;
        }
    }
}
