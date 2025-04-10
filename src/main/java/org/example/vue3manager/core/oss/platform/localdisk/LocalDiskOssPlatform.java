package org.example.vue3manager.core.oss.platform.localdisk;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.example.vue3manager.common.exception.OssOperateException;
import org.example.vue3manager.core.oss.config.OssConfig;
import org.example.vue3manager.core.oss.config.OssPlatformType;
import org.example.vue3manager.core.oss.context.OssContext;
import org.example.vue3manager.core.oss.platform.AbstractOssPlatform;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LocalDiskOssPlatform extends AbstractOssPlatform {

  private final OssConfig ossConfig;

  public LocalDiskOssPlatform(OssConfig ossConfig) {
    this.ossConfig = ossConfig;
  }

  @Override
  protected String uploadFile(OssContext ossContext) throws OssOperateException {
    OssConfig.LocalDisk localDisk = ossConfig.getLocalDisk();
    if (StringUtils.isBlank(localDisk.getRootPath())) {
      throw new OssOperateException("LocalDisk basePath is null");
    }

    String rootPath = localDisk.getRootPath();
    String filePath = ossContext.getFilePath();
    String fileName = ossContext.getFileName();

    if (rootPath.endsWith(File.separator)) {
      filePath = rootPath + filePath;
    } else {
      filePath = rootPath + File.separator + filePath;
    }

    // 文件目录
    File file = new File(filePath);

    if (!file.exists()) {
      try {
        boolean mkdirs = file.mkdirs();
        if (!mkdirs) {
          throw new OssOperateException("Failed to create file directory,file directory: " + filePath);
        }
      } catch (Exception e) {
        log.error(
            "Create file directory error. File directory not created. File directory path: {}",
            filePath + " Message: " + e.getMessage());
        throw new OssOperateException("Create file directory error.File directory not created.File"
            + " directory path: " + ossContext.getFilePath() + " Message: " + e.getMessage());
      }
    }

    // 文件
    filePath = filePath + fileName;
    file = new File(filePath);
    if (!file.exists()) {
      try {
        // 1. create new file
        boolean createSuccess = file.createNewFile();
        if (!createSuccess) {
          throw new OssOperateException("Create file failed.File path: " + filePath);
        }
      } catch (IOException e) {
        log.error("Create file error. File not created. File path: {}",
            filePath + " Message: " + e.getMessage());
        throw new OssOperateException("Create file error.File not created.File path: " + filePath
            + " Message: " + e.getMessage());
      }
    } else {
      // pdf存在,判断其内容是否发生变化.没变化直接返回路径.
      if (pdfContentEquals(ossContext.getData(), file)) {
        return filePath + fileName;
      }
      log.info("Update pdf file,Original pdf file will be delete.file name:{}", filePath);
      deleteFile(file.getAbsolutePath());
    }

    try {
      //2. write data 2 created file.
      /*
       *  writeByteArrayToFile同步阻塞IO操作, 存储业务必须同步处理返回文件的存储路径
       */
      FileUtils.writeByteArrayToFile(file, ossContext.getData());
    } catch (IOException e) {
      log.error("File was created. But write data error.file path: {}",
          file.getAbsolutePath() + " Message: " + e.getMessage());
      throw new OssOperateException(
          "File was created. But write data error.file path: "
              + filePath + " Message: " + e.getMessage());
    }

    // 返回文件路径
    if (ossConfig.getResultPathType().equals(OssConfig.ResultPathType.RELATIVE)) {
      return ossContext.getFilePath() + ossContext.getFileName();
    } else {
      return filePath;
    }
  }

  @Override
  protected byte[] downloadedFile(OssContext ossContext) throws OssOperateException {
    String downloadFilePath = ossContext.getDownloadFilePath();

    File pdfFile = new File(downloadFilePath);
    if (pdfFile.exists()) {
      try {
        return FileUtils.readFileToByteArray(pdfFile);
      } catch (IOException e) {
        log.error("Get large file from file path: {} error. Caused by: {}", downloadFilePath,
            e.getMessage());
        throw new OssOperateException(
            "Get large file from file path: " + downloadFilePath + " error. Caused by: "
                + e.getMessage());
      }
    }

    return null;
  }

  @Override
  protected boolean deleteFile(OssContext ossContext) throws OssOperateException {
    String deleteFilePath = ossContext.getDeleteFilePath();
    File willBeDeleteFile = new File(deleteFilePath);
    if (!willBeDeleteFile.exists()) {
      log.warn("delete pdf file failed. file: {} does not exists.", deleteFilePath);
      throw new OssOperateException("delete pdf file failed. file: " + deleteFilePath
          + " does not exists.");
    } else {
      boolean isDeleted = FileUtils.deleteQuietly(willBeDeleteFile);
      if (isDeleted) {
        log.info("File :{} deleted successful.", deleteFilePath);
      } else {
        log.warn("File :{} deleted failed.un know cause.", deleteFilePath);
      }
      return isDeleted;
    }
  }

  @Override
  public Boolean isSupportOssType(OssPlatformType ossType) {
    return OssPlatformType.LOCAL_DISK.name().equalsIgnoreCase(ossType.name());
  }

  private boolean pdfContentEquals(byte[] signatureData, File originalPdfFile) {

    try (InputStream signatureDataInputStream = new ByteArrayInputStream(signatureData);
         InputStream originalPdfFileInputStream = new ByteArrayInputStream(
             FileUtils.readFileToByteArray(originalPdfFile))) {
      if (IOUtils.contentEquals(signatureDataInputStream, originalPdfFileInputStream)) {
        return true;
      }
    } catch (IOException e) {
      log.error("Equals file content error. file path: {}",
          originalPdfFile.getAbsolutePath() + " Message: " + e.getMessage());
    }
    return false;
  }
}
