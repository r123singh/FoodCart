package com.foodapp.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class OrderStatusService extends IntentService {

	public OrderStatusService() {
		super("OrderStatusService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.d("status service", "task starting");
		JSONObject jsonObject = null;
		String status = "NEW_ORDER";
		String responsemessage = null;
		try {
			long orderid = intent.getLongExtra("order_id", 0);
			while (status.equalsIgnoreCase("NEW_ORDER")) {
				Thread.sleep(2000 * 60);
				String link = "http://10.10.16.120:8080/Foodzill1.1/SyncOrderStatus?orderid=" + orderid;
				HttpClient client = new DefaultHttpClient();
				HttpGet request = new HttpGet();
				request.setURI(new URI(link));
				HttpResponse response = client.execute(request);
				BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
				StringBuffer sb = new StringBuffer("");
				String line = "";
				while ((line = in.readLine()) != null) {
					sb.append(line);
				}
				in.close();
				responsemessage = sb.toString();
				jsonObject = new JSONObject(responsemessage);
				Log.d("response:", responsemessage);
				status = jsonObject.getString("order_status");
			}
		} catch (Exception e) {
			Log.e("error", e.getLocalizedMessage());
		}

			
	}

}
