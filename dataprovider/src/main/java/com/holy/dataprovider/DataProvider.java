package com.holy.dataprovider;

import com.holy.dataprovider.constant.Constant;
import com.holy.dataprovider.database.DatabaseHelper;
import com.holy.dataprovider.interfaces.DatabaseProvider;
import com.holy.dataprovider.interfaces.NetworkProvider;
import com.holy.dataprovider.network.NetHelper;
import com.holy.dataprovider.network.bean.GankIOAndroidBean;
import com.holy.dataprovider.network.bean.GankIOFuliBean;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by DR on 2018/6/6.
 */

public class DataProvider implements NetworkProvider,DatabaseProvider{

    public static final int NEED_ALL = 0;
    public static final int NEED_NETWORK = 1;
    public static final int NEED_DATABASE = 2;

    private NetHelper netHelper;
    private DatabaseHelper databaseHelper;

    public static DataProviderProxy build(int flag){
        return new DataProviderProxy(flag);
    }

    private DataProvider initNetwork(){
        netHelper = NetHelper.getInstance();
        return this;
    }

    private DataProvider initDatabase(){
        databaseHelper = DatabaseHelper.getInstance();
        return this;
    }


    @Override
    public void getAndroidData(int number, int page, final DataCallback callback) {
        netHelper.setUrl(Constant.GANK_IO_URI)
                .setGankIOHelper()
                .getAndroidInfo(number,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GankIOAndroidBean>() {
                    @Override
                    public void onCompleted() {
                        callback.complete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.error(e);
                    }

                    @Override
                    public void onNext(GankIOAndroidBean gankIOAndroidBean) {
                        callback.getData(gankIOAndroidBean);
                    }
                });
    }

    @Override
    public void getFuliData(int number, int page, final DataCallback callback) {
        netHelper.setUrl(Constant.GANK_IO_URI)
                .setGankIOHelper()
                .getFuliInfo(number,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GankIOFuliBean>() {
                    @Override
                    public void onCompleted() {
                        callback.complete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.error(e);
                    }

                    @Override
                    public void onNext(GankIOFuliBean gankIOFuliBean) {
                        callback.getData(gankIOFuliBean);
                    }


                });
    }


    public static class DataProviderProxy implements NetworkProvider,DatabaseProvider{

        private DataProvider provider;

        public DataProviderProxy(int flag){
            provider = new DataProvider();
            switch (flag){
                case NEED_ALL:
                    provider.initDatabase()
                            .initNetwork();
                    break;
                case NEED_NETWORK:
                    provider.initNetwork();
                    break;
                case NEED_DATABASE:
                    provider.initDatabase();
                    break;
            }
        }

        @Override
        public void getAndroidData(int number,int page,DataCallback callback) {
            provider.getAndroidData(number,page,callback);
        }

        @Override
        public void getFuliData(int number, int page, DataCallback callback) {
            provider.getFuliData(number,page,callback);
        }
    }
}
