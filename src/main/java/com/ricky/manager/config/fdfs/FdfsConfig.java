package com.ricky.manager.config.fdfs;

import lombok.extern.slf4j.Slf4j;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Description: FastDFS文件系统管理器
 * @Author: Ricky Charles
 * @Date: 2022-03-14 14:54
 **/
@Component
@Slf4j
public class FdfsConfig {

    private FdfsClientProperies fdfsClientProperies;

    public FdfsConfig(@Autowired FdfsClientProperies fdfsClientProperies) {
        this.fdfsClientProperies = fdfsClientProperies;
    }

    @PostConstruct
    public void initIt() throws Exception {
        try {
            Properties properties = new Properties();
            properties.setProperty("fastdfs.connect_timeout_in_seconds", fdfsClientProperies.getConnect_timeout_in_seconds());
            properties.setProperty("fastdfs.network_timeout_in_seconds", fdfsClientProperies.getNetwork_timeout_in_seconds());
            properties.setProperty("fastdfs.charset", fdfsClientProperies.getCharset());
            properties.setProperty("fastdfs.http_tracker_http_port", fdfsClientProperies.getHttp_tracker_http_port());
            properties.setProperty("fastdfs.http_anti_steal_token", fdfsClientProperies.getHttp_anti_steal_token());
            properties.setProperty("fastdfs.http_secret_key", fdfsClientProperies.getHttp_secret_key());
            properties.setProperty("fastdfs.tracker_servers", fdfsClientProperies.getTracker_servers());
            ClientGlobal.initByProperties(properties);
        } catch (IOException | MyException e) {
            log.error("init fdfs_client.properties failed.", e);
        }
    }

    /**
     * 上传文件的方法
     * @param inputStream 要上传的文件的输入流，用于读取文件内容
     * @param fileName 要上传的文件的原始文件名
     * @return 上传的结果
     */
    public String[] upload(InputStream inputStream, String fileName){
        TrackerClient trackerClient = new TrackerClient();
        try {
            TrackerServer trackerServer = trackerClient.getConnection();
            StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
            StorageClient storageClient = new StorageClient(trackerServer, storageServer);

            NameValuePair[] metaList = new NameValuePair[]{
                    new NameValuePair("fileName", fileName)
            };
            String extFileName = fileName.substring(fileName.lastIndexOf(".") + 1);
            byte[] datas = new byte[inputStream.available()];
            inputStream.read(datas);
            String[] result = storageClient.upload_file(datas, extFileName, metaList);
            return result;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 上传文件的方法
     * @param file MultipartFile
     * @return 上传的结果
     */
    public String[] upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        InputStream inputStream = file.getInputStream();
        return this.upload(inputStream, originalFilename);

    }

    /**
     * 下载文件
     * @param groupName
     * @param remoteFileName
     * @return
     */
    public byte[] download(String groupName, String remoteFileName){
        TrackerClient trackerClient = new TrackerClient();
        try{
            TrackerServer trackerServer = trackerClient.getConnection();
            StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
            StorageClient storageClient = new StorageClient(trackerServer, storageServer);
            byte[] datas = storageClient.download_file(groupName, remoteFileName);
            return datas;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 删除文件
     * @param groupName
     * @param remoteFileName
     * @return
     */
    public int delete(String groupName, String remoteFileName){
        TrackerClient trackerClient = new TrackerClient();
        try {
            TrackerServer trackerServer = trackerClient.getConnection();
            StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
            StorageClient storageClient = new StorageClient(trackerServer, storageServer);
            return storageClient.delete_file(groupName, remoteFileName);
        } catch (Exception e) {
            e.printStackTrace();
            return 10001;
        }
    }
}
