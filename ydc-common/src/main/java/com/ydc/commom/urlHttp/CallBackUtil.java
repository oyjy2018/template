package com.ydc.commom.urlHttp;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 回调
 */

public abstract class CallBackUtil<T> {

    public  void onProgress(float progress, long total ){}


    void onError(final RealResponse response){
        final String errorMessage;
        if(response.inputStream != null){
            errorMessage = getRetString(response.inputStream);
        }else if(response.errorStream != null) {
            errorMessage = getRetString(response.errorStream);
        }else if(response.exception != null) {
            errorMessage = response.exception.getMessage();
        }else {
            errorMessage = "";
        }
        onFailure(response.code,errorMessage);
    }
    void onSuccess(RealResponse response){
        final T obj = onParseResponse(response);
        onResponse(obj);
    }


    /**
     * 解析response，执行在子线程
     */
    public abstract T onParseResponse(RealResponse response);

    /**
     * 访问网络失败后被调用
     */
    public abstract void onFailure(int code,String errorMessage);

    /**
     *
     * 访问网络成功后被调用
     */
    public abstract void onResponse(T response);



    public static abstract class CallBackDefault extends CallBackUtil<RealResponse> {
        @Override
        public RealResponse onParseResponse(RealResponse response) {
            return response;
        }
    }

    public static abstract class CallBackString extends CallBackUtil<String> {
        @Override
        public String onParseResponse(RealResponse response) {
            try {
                return getRetString(response.inputStream);
            } catch (Exception e) {
                throw new RuntimeException("failure");
            }
        }

    }



    public static final byte[] input2byte(InputStream inStream)
            throws IOException {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        while ((rc = inStream.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        byte[] in2b = swapStream.toByteArray();
        return in2b;
    }

    /**
     * 下载文件时的回调类
     */
    public static abstract class CallBackFile extends CallBackUtil<File> {

        private final String mDestFileDir;
        private final String mdestFileName;

        /**
         *
         * @param destFileDir:文件目录
         * @param destFileName：文件名
         */
        public CallBackFile(String destFileDir, String destFileName){
            mDestFileDir = destFileDir;
            mdestFileName = destFileName;
        }
        @Override
        public File onParseResponse(RealResponse response) {

            InputStream is = null;
            byte[] buf = new byte[1024*8];
            int len = 0;
            FileOutputStream fos = null;
            try{
                is = response.inputStream;
                final long total = response.contentLength;

                long sum = 0;

                File dir = new File(mDestFileDir);
                if (!dir.exists()){
                    dir.mkdirs();
                }
                File file = new File(dir, mdestFileName);
                fos = new FileOutputStream(file);
                while ((len = is.read(buf)) != -1){
                    sum += len;
                    fos.write(buf, 0, len);
                    final long finalSum = sum;
                    onProgress(finalSum * 100.0f / total,total);
                }
                fos.flush();

                return file;

            } catch (Exception e) {
                e.printStackTrace();
            } finally{
                try{
                    fos.close();
                    if (is != null) is.close();
                } catch (IOException e){
                }
                try{
                    if (fos != null) fos.close();
                } catch (IOException e){
                }

            }
            return null;
        }
    }


    static String getRetString(InputStream is) {
        String buf;
        try ( BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"))){
            StringBuilder sb = new StringBuilder();
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            buf = sb.toString();
            return buf;

        } catch (Exception e) {
            return null;
        }finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
