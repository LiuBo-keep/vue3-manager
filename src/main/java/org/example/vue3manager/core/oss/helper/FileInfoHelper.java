package org.example.vue3manager.core.oss.helper;

import org.apache.tika.Tika;
import org.apache.tika.mime.MimeTypeException;
import org.apache.tika.mime.MimeTypes;
import org.example.vue3manager.common.exception.OssOperateException;
import org.springframework.stereotype.Component;

/**
 * 文件信息助手类，用于获取文件相关信息，如文件类型等
 *
 * @author aidan.liu
 */
@Component
public class FileInfoHelper {

  /**
   * Apache Tika对象，用于检测文件类型
   */
  private final Tika tika;

  public FileInfoHelper(Tika tika) {
    this.tika = tika;
  }


  /**
   * 获取文件类型
   *
   * <p>该方法通过分析文件数据，使用Apache Tika库检测文件的MIME类型，
   * 然后根据MIME类型获取文件的扩展名
   *
   * @param fileData 文件数据，用于检测文件类型
   * @return 文件的扩展名，表示文件类型
   * @throws OssOperateException 如果检测文件类型时发生错误
   */
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
