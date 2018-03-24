package com.onezao.onezao.sendsms1208;

import com.onezao.onezao.zao.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SmsActivity extends Activity {
    String  objects[]  =  {"今天的风儿轻柔无比，今天的花儿香飘万里；今天的鸟儿十分欢喜，今天的云儿满载笑意；今天的事儿万分顺利，今天的人儿如此甜蜜。所有美...",
            "丫头，生活是你自己的，你哭它就对你哭，你笑它就对你笑。转眼，又是一年，你的生日即将来到。今年，还是少不了我对你的祝福，我忍不住...",
            "世界上最动听的声音，是妈妈声声的呼唤；世界上最温暖的笑容，是妈妈温暖的笑脸。妈妈，原谅生日时我不能陪在您身边，在这个日子里，我...",
            "今天是你的生日，祝你：发财势头如快马加鞭，一日千里；发展速度如滔滔江水，势不可挡；好事发生如雨后春笋，络绎不绝；祝福发送如比赛...",
            "茫茫人海相逢是缘分，芸芸众生相知是福气，年年月月相交是情谊。高山流水知音难求，你我手足兄弟情深。巴山夜雨，飘洒我的思念。剪烛西...",
            "太阳初生的时候，千万道光芒就是我心底丝丝缕缕的挂念；夕阳西下的时候，落日的余晖就是我心底分分秒秒的挂牵；生活一天一天，思念依然...",
            "春天的鲜花，夏天的浪花，秋天的繁华，冬天的雪花，不论何时何地都希望你乐开花，朋友，在这阳光明媚的日子，我为你放飞一群祝福，祝你...",
            "我把春风织成一块温暖的毯子送给你，将幸福包住。我把春雨编成一条梦幻的丝带送给你，把快乐缠住。我把春天挂满祝福送给你，让美好留住...",
            "喜鹊早已跳上了枝头，唱起幸福的旋律。鱼儿早已在水底徜徉，吐着祝福的气泡。我把幸福的花朵缠上平安的丝带，让健康的绿荫爬满你的生活...",
           
            "朋友是读不完的情，写不完的意，是一本看不倦的书，听也听不厌的歌。你我情谊，珍藏在梦里，存留在心里。送你一碗长寿面，不咸也不淡，...",
            "今天的风儿轻柔无比，今天的花儿香飘万里；今天的鸟儿十分欢喜，今天的云儿满载笑意；今天的事儿万分顺利，今天的人儿如此甜蜜。所有美...",
            "丫头，生活是你自己的，你哭它就对你哭，你笑它就对你笑。转眼，又是一年，你的生日即将来到。今年，还是少不了我对你的祝福，我忍不住...",
            "世界上最动听的声音，是妈妈声声的呼唤；世界上最温暖的笑容，是妈妈温暖的笑脸。妈妈，原谅生日时我不能陪在您身边，在这个日子里，我...",
            "今天是你的生日，祝你：发财势头如快马加鞭，一日千里；发展速度如滔滔江水，势不可挡；好事发生如雨后春笋，络绎不绝；祝福发送如比赛...",
            "茫茫人海相逢是缘分，芸芸众生相知是福气，年年月月相交是情谊。高山流水知音难求，你我手足兄弟情深。巴山夜雨，飘洒我的思念。剪烛西...",
            "太阳初生的时候，千万道光芒就是我心底丝丝缕缕的挂念；夕阳西下的时候，落日的余晖就是我心底分分秒秒的挂牵；生活一天一天，思念依然...",
            "春天的鲜花，夏天的浪花，秋天的繁华，冬天的雪花，不论何时何地都希望你乐开花，朋友，在这阳光明媚的日子，我为你放飞一群祝福，祝你...",
            "我把春风织成一块温暖的毯子送给你，将幸福包住。我把春雨编成一条梦幻的丝带送给你，把快乐缠住。我把春天挂满祝福送给你，让美好留住...",
            "喜鹊早已跳上了枝头，唱起幸福的旋律。鱼儿早已在水底徜徉，吐着祝福的气泡。我把幸福的花朵缠上平安的丝带，让健康的绿荫爬满你的生活...",
            
            "朋友是读不完的情，写不完的意，是一本看不倦的书，听也听不厌的歌。你我情谊，珍藏在梦里，存留在心里。送你一碗长寿面，不咸也不淡，...",
            "今天的风儿轻柔无比，今天的花儿香飘万里；今天的鸟儿十分欢喜，今天的云儿满载笑意；今天的事儿万分顺利，今天的人儿如此甜蜜。所有美...",
            "丫头，生活是你自己的，你哭它就对你哭，你笑它就对你笑。转眼，又是一年，你的生日即将来到。今年，还是少不了我对你的祝福，我忍不住...",
            "世界上最动听的声音，是妈妈声声的呼唤；世界上最温暖的笑容，是妈妈温暖的笑脸。妈妈，原谅生日时我不能陪在您身边，在这个日子里，我...",
            "今天是你的生日，祝你：发财势头如快马加鞭，一日千里；发展速度如滔滔江水，势不可挡；好事发生如雨后春笋，络绎不绝；祝福发送如比赛...",
            "茫茫人海相逢是缘分，芸芸众生相知是福气，年年月月相交是情谊。高山流水知音难求，你我手足兄弟情深。巴山夜雨，飘洒我的思念。剪烛西...",
            "太阳初生的时候，千万道光芒就是我心底丝丝缕缕的挂念；夕阳西下的时候，落日的余晖就是我心底分分秒秒的挂牵；生活一天一天，思念依然...",
            "春天的鲜花，夏天的浪花，秋天的繁华，冬天的雪花，不论何时何地都希望你乐开花，朋友，在这阳光明媚的日子，我为你放飞一群祝福，祝你...",
            "我把春风织成一块温暖的毯子送给你，将幸福包住。我把春雨编成一条梦幻的丝带送给你，把快乐缠住。我把春天挂满祝福送给你，让美好留住...",
            "喜鹊早已跳上了枝头，唱起幸福的旋律。鱼儿早已在水底徜徉，吐着祝福的气泡。我把幸福的花朵缠上平安的丝带，让健康的绿荫爬满你的生活...",
            "朋友是读不完的情，写不完的意，是一本看不倦的书，听也听不厌的歌。你我情谊，珍藏在梦里，存留在心里。送你一碗长寿面，不咸也不淡，..."

};
		
         @Override
        protected void onCreate(Bundle savedInstanceState) {
        	 super.onCreate(savedInstanceState);
        	 setContentView(R.layout.activity_sendsms_1208);
        	 
        	 ListView lv_sms = (ListView) findViewById(R.id.lv_sms);
        	 //配套ArrayAdapter使用的只能是一个TextView，不能有其他布局。
        	 final ArrayAdapter<String>   adapter  =  new  ArrayAdapter<String>(getApplicationContext(), R.layout.item_sendsms_1208, objects);
        	 lv_sms.setAdapter(adapter);
        	 //给ListView的条目设置点击事件
        	 lv_sms.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
                        //获取ListView单个item的值
                        String value =  adapter.getItem(position);
                        Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
                        //这里因为需要调用系统发送短信的Activity，所以需要查询源码，设置相对应的值。
                        Intent  intent  =  new  Intent();
                        intent.setAction("android.intent.action.SEND");
                        intent.setType("text/plain");
						intent.putExtra("sms_body", value );
                        startActivity(intent);
				}
			});
        }
}
