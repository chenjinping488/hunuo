package com.example.http;

public interface HttpResponseImp {

	public void onHttpStart(int flag);

	public void onHttpSuccess(Object t, int flag);

	public void onHttpLoading(long count, long current, int flag);

	public void onHttpFailure(Throwable t, int errorNo, String strMsg, int flag);

}
