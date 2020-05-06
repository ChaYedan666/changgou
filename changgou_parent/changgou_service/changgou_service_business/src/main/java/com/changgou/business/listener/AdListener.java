package com.changgou.business.listener;

import okhttp3.*;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author chayedan666
 * @version 1.0
 * @className: AdListener
 * @description: 监听rabbitmq发送的广告数据
 * @date: 2020/5/6
 */
@Component
@RabbitListener(queues = "ad_update_queue")
public class AdListener {

    /**
     * 接受广告队列中的数据进行处理
     * 业务是，模式Http请求到nginx，让nginx调用lua脚本进行大广告更新
     * @param position
     */
    @RabbitHandler
    public void messageHandler(String position){
        String url = "http://192.168.200.128/ad_update?position=" + position;
        // 创建okHttp对象
        OkHttpClient okHttpClient = new OkHttpClient();
        // 创建请求对象
        Request request = new Request.Builder().url(url).build();
        // 发送请求
        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                System.out.println("====发送失败=====");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("====发送成功====" + response.message());
            }
        });
    }
}
