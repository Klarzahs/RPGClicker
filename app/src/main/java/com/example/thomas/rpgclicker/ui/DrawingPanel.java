package com.example.thomas.rpgclicker.ui;

/**
 * Created by Thomas on 28.03.2016.
 */

import android.app.Fragment;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import com.example.thomas.rpgclicker.activities.FragmentMain;
import com.example.thomas.rpgclicker.activities.MainActivity;
import com.example.thomas.rpgclicker.Monster;

public class DrawingPanel extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener{

    private PanelThread _thread;

    private Paint linePaint, textPaint, bgPaint;
    private static Paint popUpPaint;

    private long cdMeele = 0;
    private long cdSpell = 0;
    private long cdHeal = 0;
    private long offsetMeele = 500;
    private long offsetSpell = 3000;
    private long offsetHeal = 5000;

    private int oldX = -1, oldY = -1, newX = -1, newY = -1;
    private int skipDraw = 0;

    private static int riposteState = -1;
    private final String riposteString = "RIPOSTE";

    private FragmentMain main;

    public DrawingPanel(Context c, FragmentMain main) {
        super(c);
        this.setWillNotDraw(false);
        this.setOnTouchListener(this);

        getHolder().addCallback(this);

        linePaint = new Paint();
        linePaint.setARGB(255, 155, 0, 0);
        linePaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        textPaint = new Paint();
        textPaint.setARGB(255, 255, 255, 255);
        textPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(50f);
        bgPaint = new Paint();
        bgPaint.setARGB(255, 0, 0, 0);
        bgPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        popUpPaint = new Paint();
        popUpPaint.setARGB(255, 255, 0, 0);
        popUpPaint.setFlags(Paint.ANTI_ALIAS_FLAG);

        this.main = main;
        this.setOnTouchListener(this);
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), bgPaint);

        drawPlayerInfo(canvas);
        drawMonsterInfo(canvas);
        drawButtonTexts();

        if(oldX != -1){             // has received > 2 touch events
            if(skipDraw < 3) {      // hide line after 3 ticks
                canvas.drawLine(oldX, oldY, newX, newY, linePaint);
                skipDraw++;
            }
        }

        handleRiposte(canvas);
    }

    private void drawButtonTexts(){
        Button b = main.getMeeleButton();
        b.setText(getMeeleString());
        b = main.getSpellButton();
        b.setText(getSpellString());
        b = main.getHealButton();
        b.setText(getHealString());
    }

    private void drawPlayerInfo(Canvas canvas){
        canvas.drawText("HP: " + FragmentMain.player.getHp() + "/100", 100, 100, textPaint);
        canvas.drawText(FragmentMain.player.getGoldString(), 100, 150, textPaint);
    }

    private void drawMonsterInfo(Canvas canvas){
        Monster m = FragmentMain.monster;
        canvas.drawText("HP: "+m.getHp()+"/"+m.getMaxHP(), canvas.getWidth() - 300, 100, textPaint);
        canvas.drawText("LV: " + m.getLvl(), canvas.getWidth() - 300, 150, textPaint);
        canvas.drawText("DM: " + m.getDmg(), canvas.getWidth() - 300, 200, textPaint);
    }

    public static void riposte(){
        riposteState = 50;
        popUpPaint.setARGB(255, 255, 0, 0);
    }

    private void handleRiposte(Canvas canvas){
        if(riposteState > 0){
            riposteState += 5;

            popUpPaint.setTextSize(riposteState);
            Rect bounds = new Rect();
            popUpPaint.getTextBounds(riposteString, 0, riposteString.length(), bounds);
            canvas.drawText(riposteString, (canvas.getWidth()-bounds.width())/2, canvas.getHeight(), popUpPaint);

            if(riposteState == 100) {
                riposteState = -1;          // reset
                popUpPaint.setTextSize(riposteState);
            }
        }
    }

    private void handleRiposteEvent(){
        //handle riposte event
        if(riposteState > 0){
            if(newX - oldX >= 350 && newY - oldY <= -350){
                if(riposteState < 80)
                    riposteState = 80;
                popUpPaint.setARGB(255, 0, 125, 0);
                FragmentMain.monster.getAttacked(FragmentMain.player.attackMeele() * 5);
            }
        }
    }

    private String getSpellString(){
        String str = "0";
        if(System.currentTimeMillis() - cdSpell < offsetSpell){
            long time = offsetSpell - (System.currentTimeMillis() - cdSpell);
            if(time > 1000){
                str = "" + time;
                str = str.substring(0, 1);
            } else if(time > 100){
                str = "0," + time;
                str = str.substring(0, 3);
            }else if(time > 10) {
                str = "0,0" + time;
                str = str.substring(0, 4);
            }
        }
        return str;
    }

    private String getHealString(){
        String str = "0";
        if(System.currentTimeMillis() - cdHeal < offsetHeal){
            long time = offsetHeal - (System.currentTimeMillis() - cdHeal);
            if(time > 1000){
                str = "" + time;
                str = str.substring(0, 1);
            } else if(time > 100){
                str = "0," + time;
                str = str.substring(0, 3);
            }else if(time > 10) {
                str = "0,0" + time;
                str = str.substring(0, 4);
            }
        }
        return str;
    }

    private String getMeeleString(){
        String str = "0";
        if(System.currentTimeMillis() - cdMeele < offsetMeele){
            long time = offsetMeele - (System.currentTimeMillis() - cdMeele);
            if(time > 1000){
                str = "" + time;
                str = str.substring(0, 1);
            } else if(time > 100){
                str = "0," + time;
                str = str.substring(0, 3);
            }else if(time > 10) {
                str = "0,0" + time;
                str = str.substring(0, 4);
            }
        }
        return str;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        setWillNotDraw(false); //Allows us to use invalidate() to call onDraw()
        _thread = new PanelThread(getHolder(), this); //Start the thread that
        _thread.setRunning(true);                     //will make calls to
        _thread.start();                              //onDraw()
    }


    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        try {
            _thread.setRunning(false);                //Tells thread to stop
            _thread.join();                           //Removes thread from mem.
        } catch (InterruptedException e) {}
    }


    public void meeleAttack(){
        if(System.currentTimeMillis() - cdMeele >= offsetMeele){
            FragmentMain.monster.getAttacked(FragmentMain.player.attackMeele());
            cdMeele = System.currentTimeMillis();
        }
    }

    public void spellAttack(){
        if(System.currentTimeMillis() - cdSpell >= offsetSpell){
            FragmentMain.monster.getAttacked(FragmentMain.player.attackSpell());
            cdSpell = System.currentTimeMillis();
        }
    }

    public void spellHeal(){
        if(System.currentTimeMillis() - cdHeal >= offsetHeal){
            FragmentMain.player.heal();
            cdHeal = System.currentTimeMillis();
        }
    }

    public boolean onTouch(View v, MotionEvent e){
        if(e.getAction() == MotionEvent.ACTION_UP){
            oldX = newX;
            oldY = newY;
            newX = (int) e.getX();
            newY = (int) e.getY();
            skipDraw = 0;

            handleRiposteEvent();
            return true;
        }
        if(e.getAction() == MotionEvent.ACTION_DOWN){
            oldX = -1;      // reset
            newX = (int)e.getX();
            newY = (int) e.getY();
            return true;
        }
        return false;

    }

    class PanelThread extends Thread {
        private SurfaceHolder _surfaceHolder;
        private DrawingPanel _panel;
        private boolean _run = false;

        public PanelThread(SurfaceHolder surfaceHolder, DrawingPanel panel) {
            _surfaceHolder = surfaceHolder;
            _panel = panel;
        }

        public void setRunning(boolean run) { //Allow us to stop the thread
            _run = run;
        }

        @Override
        public void run() {
            Canvas c;
            while (_run) {     //When setRunning(false) occurs, _run is
                c = null;      //set to false and loop ends, stopping thread

                try {
                    c = _surfaceHolder.lockCanvas(null);
                    synchronized (_surfaceHolder) {
                        postInvalidate();
                    }
                    Thread.sleep(10);
                } catch(Exception e){
                    e.printStackTrace();
                } finally {
                    if (c != null) {
                        _surfaceHolder.unlockCanvasAndPost(c);
                    }
                }
            }
        }

    }

}
