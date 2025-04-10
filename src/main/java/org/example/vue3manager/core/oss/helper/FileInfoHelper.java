package org.example.vue3manager.core.oss.helper;

import org.apache.tika.Tika;
import org.apache.tika.mime.MimeTypeException;
import org.apache.tika.mime.MimeTypes;
import org.example.vue3manager.common.exception.OssOperateException;
import org.springframework.stereotype.Component;

@Component
public class FileInfoHelper {

  private final Tika tika;

  public FileInfoHelper(Tika tika) {
    this.tika = tika;
  }


  public String getFileType(byte[] fileData) throws OssOperateException {
    try {
      String mimeType = tika.detect(fileData);
      MimeTypes allTypes = MimeTypes.getDefaultMimeTypes();
      return allTypes.forName(mimeType).getExtension();
    } catch (MimeTypeException e) {
      throw new OssOperateException("");
    }
  }
}
