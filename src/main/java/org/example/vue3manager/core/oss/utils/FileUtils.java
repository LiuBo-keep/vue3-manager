package org.example.vue3manager.core.oss.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import org.example.vue3manager.common.exception.OssOperateException;

public class FileUtils {

  public static String getFileTypeFromFileName(byte[] fileData) throws OssOperateException {
    String fileName = getFileType(fileData);
    return getFileTypeFromFileName(fileName);
  }

  public static String getFileTypeFromFileName(String fileName) {
    int lastDotIndex = fileName.lastIndexOf('.');
    if (lastDotIndex == -1) {
      return "unknown";
    }
    return fileName.substring(lastDotIndex + 1).toLowerCase();
  }

  private static String getFileType(byte[] fileData) throws OssOperateException {
    try {
      Path tempFile = Files.createTempFile("temp", ".tmp");
      Files.write(tempFile, fileData);
      String fileType = Files.probeContentType(tempFile);
      Files.delete(tempFile);
      return fileType;
    } catch (Exception e) {
      throw new OssOperateException(e.getMessage());
    }
  }
}
