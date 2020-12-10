package com.hulk.androidstudy.java_base.retrofit;

/**
 * 参数处理，将参数
 * Created by tzh on 2020/11/19.
 */
public abstract class ParameterHandler {

    abstract void apply(ServiceMethod serviceMethod, String value);

    /**
     * 处理Query参数
     */
    static class QueryParameterHandler extends ParameterHandler {
        String key;

        public QueryParameterHandler(String key) {
            this.key = key;
        }

        @Override
        void apply(ServiceMethod serviceMethod, String value) {
            serviceMethod.addQueryParameter(key, value);
        }
    }

    /**
     * 处理Field参数
     */
    static class FiledParameterHandler extends ParameterHandler {
        String key;

        public FiledParameterHandler(String key) {
            this.key = key;
        }

        @Override
        void apply(ServiceMethod serviceMethod, String value) {
            serviceMethod.addFiledParameter(key, value);
        }
    }
}
