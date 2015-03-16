package com.example.http;

import com.example.utils.Constants;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

public class HttpRequest extends AjaxCallBack<Object> implements HttpResponseImp {

	private static HttpResponseImp response;

	private static HttpRequest httpRequest;

	private FinalHttp finalHttp;

	public static int flag;

	private HttpRequest() {
		finalHttp = new FinalHttp();
		finalHttp.configTimeout(10000);
	}

	public static HttpRequest getInstance(HttpResponseImp responseImp, int market) {
		response = responseImp;
		flag = market;
		if (httpRequest == null) {
			httpRequest = new HttpRequest();
		}
		return httpRequest;
	}

	/**************************************** 请求部分 ***********************************/

	public void login(String username, String password) {
		AjaxParams params = new AjaxParams();
		params.put("username", username);
		params.put("userpw", password);
		finalHttp.post(Constants.LOGIN_URL, params, this);
	}

	public void getIndex() {
		finalHttp.get(Constants.BANNER_URL, this);
	}

	/***************************************************************************/

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		response.onHttpStart(flag);
	}

	@Override
	public void onSuccess(Object t) {
		// TODO Auto-generated method stub
		response.onHttpSuccess(t, flag);
	}

	@Override
	public void onLoading(long count, long current) {
		// TODO Auto-generated method stub
		response.onHttpLoading(count, current, flag);
	}

	@Override
	public void onFailure(Throwable t, int errorNo, String strMsg) {
		// TODO Auto-generated method stub
		response.onHttpFailure(t, errorNo, strMsg, flag);
	}

	@Override
	public void onHttpStart(int flag) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onHttpSuccess(Object t, int flag) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onHttpLoading(long count, long current, int flag) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onHttpFailure(Throwable t, int errorNo, String strMsg, int flag) {
		// TODO Auto-generated method stub

	}
}
