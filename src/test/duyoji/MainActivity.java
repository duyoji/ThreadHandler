package test.duyoji;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private boolean flag = false;
	private volatile Thread mLooper;
    private Handler mHandler;
    private TextView[] textBalls = new TextView[3];
    
    int countSecond = 0;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
//        mLooper = new Thread(this);
        
        Button mainButton = (Button) findViewById(R.id.main_button);
//        final TextView mainText = (TextView) findViewById(R.id.main_text);
        textBalls[0] = (TextView) findViewById(R.id.main_text1);
        textBalls[1] = (TextView) findViewById(R.id.main_text2);
        textBalls[2] = (TextView) findViewById(R.id.main_text3);
        
        final Context context = this;
        mainButton.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				mLooper = new Thread(this);
		        //スレッド処理を開始
				flag = !flag;
				if(flag){
					mLooper = new Thread(new  Runnable() {
						public void run() {
							long time = System.currentTimeMillis();
					        long count = 0;
					 
					        while (mLooper != null) {
					 
					            long now = System.currentTimeMillis();
					            if(now - time > 1000){
					 
					                //Message msg = new Message(); //非推奨
					                Message msg = Message.obtain(); //推奨
					                //Message msg = mHandler.obtainMessage(); //推奨
					                msg.obj = new String("ループが" + count + "回終了しました");
//					                msg.obj = (int)count;
					 
					                //ハンドラへのメッセージ送信
					                mHandler.sendMessage(msg);
					 
					                //スレッドの利用変数を初期化
					                time = now;
					                count++;
					                countSecond++;
					            }
					        }
						}
					});
			        if(mLooper != null ){
			            mLooper.start();
			        }
//			        mLooper.start();
				}else{
					mLooper = null;
				}
			}
		});
        
        
        mHandler =  new Handler(){
            //メッセージ受信
            public void handleMessage(Message message) {
                //メッセージの表示
//                mainText.setText((String) message.obj);
            	for(int i = 0; i < textBalls.length; i++){
            		if(i == countSecond % 3 ){
            			textBalls[i].setTextColor(Color.BLUE);
            		}else{
            			textBalls[i].setTextColor(Color.RED);
            		}
            	}
                //メッセージの種類に応じてswitch文で制御すれば
                //イベント制御に利用できます
            };
        };
                
        
        
    }
    
//    @Override
//    public void onStart(){
//        super.onStart();
//        Log.d("TAG", "startしました。");
//        mLooper = new Thread(this);
//        //スレッド処理を開始
//        if(mLooper != null ){
//            mLooper.start();
//        }
//    }
     
    @Override
    public void onStop(){
        super.onStop();
        Log.d("TAG", "stopしました。");
        //スレッドを削除
        mLooper = null;
    }

//	@Override
//	public void run() {
//        long time = System.currentTimeMillis();
//        long count = 0;
// 
//        while (mLooper != null) {
// 
//            long now = System.currentTimeMillis();
//            if(now - time > 1000){
// 
//                //Message msg = new Message(); //非推奨
//                Message msg = Message.obtain(); //推奨
//                //Message msg = mHandler.obtainMessage(); //推奨
//                msg.obj = new String("ループが" + count + "回終了しました");
////                msg.obj = (int)count;
// 
//                //ハンドラへのメッセージ送信
//                mHandler.sendMessage(msg);
// 
//                //スレッドの利用変数を初期化
//                time = now;
//                count++;
//                countSecond++;
//            }
//        }
//    }
}