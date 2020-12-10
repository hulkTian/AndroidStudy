package com.hulk.androidstudy.java_base.rxjava;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * Created by tzh on 2020/12/4.
 */
public class UseRxJava {

    /**
     * 使用RXJava
     * RXJava有三要素：
     * 1.观察者，只有一个
     * 2.被观察者，可以有多个
     * 3.订阅，关联1和2
     * 核心思想：经过从上到下的链式处理，中间经过一次或多次的被观察者的处理（不同操作符），最终将需要的结果发送给观察者
     */
    public void testUse() {
        //被观察者
        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                emitter.onNext(1);
            }
        });
        //被装饰的被观察者
        Observable<Integer> observable2 = observable1.map(o -> {
            //中间转换
            o++;
            System.out.println(Thread.currentThread() + "中间转换：" + o);
            return o;
        });
        Observable<Integer> observable3 = observable2.flatMap(new Function<Integer, ObservableSource<Integer>>() {
            @Override
            public ObservableSource<Integer> apply(Integer integer) throws Throwable {
                return new ObservableSource<Integer>() {

                    @Override
                    public void subscribe(@NonNull Observer<? super Integer> observer) {
                        observer.onNext(3);
                    }
                };
            }
        });
        //被观察者在子线程
        Observable<Integer> observable4 =  observable3.subscribeOn(Schedulers.io());
        //观察者在主线程
        Observable<Integer> observable5 =  observable4.observeOn(AndroidSchedulers.mainThread());
        //订阅，将观察者和被观察者关联
        observable5.subscribe(
                //观察者
                new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        //订阅开始回调
                        System.out.println(Thread.currentThread() + "开始订阅：" + d.toString());
                    }

                    @Override
                    public void onNext(@NonNull Integer o) {
                        //观察者接受结果的回调
                        System.out.println(Thread.currentThread() + "获取结果：" + o);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        //报错的回调
                    }

                    @Override
                    public void onComplete() {
                        //完成的回调
                    }
                });
    }
}
