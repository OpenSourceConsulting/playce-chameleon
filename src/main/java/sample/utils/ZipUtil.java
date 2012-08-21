
package sample.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ibm.icu.text.CharsetDetector;

public class ZipUtil {

    private static final Log logger = LogFactory.getLog(ZipUtil.class);

    /*
     * FilePath : zip file full path. unzipedPath : unzip Directory full path
     */
    public static String extract(String zipFilePath, String unzipDirPath) throws IOException {

        ZipFile zipFile = null;

        try {
            if (logger.isDebugEnabled()) {
                logger.debug("[ZipUtil] zipFilePath :" + zipFilePath);
            }

            String unZipDir = (unzipDirPath == null) ? zipFilePath.substring(0, zipFilePath.lastIndexOf('.')) + File.separator
                    : unzipDirPath + File.separator;
            if (logger.isDebugEnabled()) {
                logger.debug("[ZipUtil] unzipDirPath :" + unZipDir);
            }

            zipFile = new ZipFile(zipFilePath);

            Enumeration e = zipFile.entries();
            for (; e.hasMoreElements();) {
                FileOutputStream output = null;
                InputStream input = null;
                String entryName;
                
                try {
                    byte[] data;
                    ZipEntry entry = (ZipEntry) e.nextElement();

                    if (logger.isDebugEnabled()) {
                        logger.debug("[ZipUtil] current entry:" + entry.getName());
                    }

                    entryName = entry.getName().replace('\\', '/');
                    if (entry.isDirectory()) {
                        File dir = new File(unZipDir + entry.getName());
                        if (!dir.exists() && !dir.mkdirs()) {
                            throw new IOException("[ZipUtil] make directory fail");
                        }
                    }
                    else {
                        File outputFile = null;

                        try {
                            outputFile = new File(unZipDir + entry.getName());

                            if (entry.getSize() == 0 && entry.getName().indexOf('.') == -1) {
                                throw new Exception("[ZipUtil] This file may be a directory");
                            }

                            File parentDir = outputFile.getParentFile();
                            if (!parentDir.exists() && !parentDir.mkdirs()) {
                                throw new IOException("[ZipUtil] make directory fail");
                            }

                            input = zipFile.getInputStream(entry);
                            output = new FileOutputStream(outputFile);
                            
                            String changeTarget = PropertyUtil.getProperty("unzip.change.target");
                            if(changeTarget.indexOf(entryName.substring(entryName.lastIndexOf(".")+1, entryName.length())) > -1) {
                                
                                OutputStreamWriter osr = new OutputStreamWriter(output, "UTF-8");
                                
                                CharsetDetector detector = new CharsetDetector();
                                data = IOUtils.toByteArray(input, entry.getSize());
                                IOUtils.closeQuietly(input);
                                detector.setDeclaredEncoding(PropertyUtil.getProperty("unzip.default.encoding"));
                                detector.setText(data);
                                Reader reader = detector.detect().getReader();
                                
                                int buffer = 0;
                                while(true) {
                                    buffer = reader.read();
                                    if(buffer == -1)
                                        break;
                                    
                                    osr.write(buffer);
                                }
                                osr.close();
                                
                            } else {
                                
                                int len = 0;
                                byte buffer[] = new byte[1024];
                                while ((len = input.read(buffer)) > 0)
                                    output.write(buffer, 0, len);
                            }
                            
                        }
                        catch (Exception ex2) {
                            ex2.printStackTrace();
                            if (!outputFile.exists() && !outputFile.mkdirs()) {
                                throw new IOException("[ZipUtil] make directory fail");
                            }
                        }
                    }
                }

                catch (IOException ex) {
                    throw ex;
                }

                catch (Exception ex) {
                    throw new IOException("[ZipUtil] extract fail", ex);
                }

                finally {
                    if (input != null) {
                        input.close();
                    }
                    if (output != null) {
                        output.close();
                    }
                }
            }
            return unZipDir;
        }

        catch (IOException e) {
            e.printStackTrace();
            throw e;
        }

        finally {
            if (zipFile != null) {
                zipFile.close();
            }
        }
    }
    
}
