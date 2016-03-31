package com.example.thomas.rpgclicker.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.example.thomas.rpgclicker.activities.FragmentInventory;
import com.example.thomas.rpgclicker.R;
import com.example.thomas.rpgclicker.weapons.Item;


public class InventoryPanel extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener{

    private PanelThread _thread;
    private double offsetX, offsetYTop, offsetYBottom, offsetBetweenX, offsetBetweenY, sizeX, sizeY;
    private int width, height;

    private Bitmap inventory;

    private Item[][] inventorySlots = new Item[5][3];


    private FragmentInventory inv;

    public InventoryPanel(Context c, FragmentInventory inv) {
        super(c);
        this.setWillNotDraw(false);
        this.setOnTouchListener(this);

        getHolder().addCallback(this);

        this.inv = inv;
        this.setOnTouchListener(this);
    }

    public void init(){
        Bitmap temp = BitmapFactory.decodeResource(getResources(), R.drawable.inventory);
        inventory = Bitmap.createScaledBitmap(temp, getWidth(), getHeight(), false);
        setOffsets();
        loadItems();
    }

    @Override
    public void onDraw(Canvas canvas) {
        init();
        canvas.drawColor(Color.GRAY);
        if(inventory!= null)
            canvas.drawBitmap(inventory, 0, 0, new Paint());

        drawInventorySlots(canvas);
    }

    private void drawInventorySlots(Canvas canvas){
        for(int j = 0; j < inventorySlots.length; j++){
            for(int i = 0; i < inventorySlots[j].length; i++){
                if(inventorySlots[j][i] != null){
                    canvas.drawBitmap(inventorySlots[j][i].getBitmap(),
                            (int)(offsetX + i * (offsetBetweenY + sizeX)),
                            (int)(offsetYTop + j * (offsetBetweenY + sizeY)),
                            new Paint());
                }
            }
        }
    }

    private void setOffsets(){
        offsetX = 14.5 * getWidth() / 100;
        offsetYTop = 12.2 * getHeight() / 100;
        offsetYBottom = 14.8 * getHeight() / 100;
        offsetBetweenX = 4.8 * getWidth() / 100;
        offsetBetweenY = 2.9 * getHeight() / 100;
        sizeX = 20.3 * getWidth() / 100;
        sizeY = 12.2 * getHeight() / 100;
    }

    private void loadItems(){
        Item i1 = new Item("Axe", getResources(), R.drawable.axe2h);
        Item i2 = new Item("Book", getResources(), R.drawable.book);
        Item i3 = new Item("Shield", getResources(), R.drawable.shield);
        Item i4 = new Item("Staff", getResources(), R.drawable.staff);
        Item i5 = new Item("Sword", getResources(), R.drawable.sword);
        addItem(i1);
        addItem(i2);
        addItem(i3);
        addItem(i4);
        addItem(i5);
    }

    public boolean addItem(Item item){
        for(int j = 0; j < inventorySlots.length; j++){
            for(int i = 0; i < inventorySlots[j].length; i++){
                if(inventorySlots[j][i] == null){
                    inventorySlots[j][i] = item;
                    item.setPos(j, i);
                    return true;
                }
            }
        }
        return false;
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



    public boolean onTouch(View v, MotionEvent e){
        if(e.getAction() == MotionEvent.ACTION_UP){
            return true;
        }
        if(e.getAction() == MotionEvent.ACTION_DOWN){
            return true;
        }
        return false;

    }

    class PanelThread extends Thread {
        private SurfaceHolder _surfaceHolder;
        private InventoryPanel _panel;
        private boolean _run = false;

        public PanelThread(SurfaceHolder surfaceHolder, InventoryPanel panel) {
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
