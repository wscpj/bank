package com.bank.common.util;

import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import javax.naming.NamingException;
import javax.naming.directory.DirContext;

import org.apache.naming.resources.DirContextURLStreamHandler;
import org.apache.naming.resources.FileDirContext;

public class FilesUtils {

    /**
     * According the key, generate a file (if not exist, then create a new file).
     *
     * @param filename
     * @param fullPath
     *            the file relative path(something like `a../bxx/wenjian.txt`)
     * @return
     * @throws IOException
     * @throws NamingException
     */
    public static File getFile(String path, String filename) throws IOException, NamingException {
        if (filename == null || filename.isEmpty()) {
            return null;
        }
        String name = filename.replaceAll("/", Matcher.quoteReplacement(File.separator));
        File f = new File(getServerAbsolutePath() + File.separator + path + File.separator + name);
        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdirs();
        }
        if (!f.exists()) {
            f.createNewFile();
        }
        return f;
    }

    /**
     * close the IO stream.
     *
     * @param stream
     * @throws IOException
     */
    public static void close(Closeable stream) throws IOException {
        if (stream != null) {
            stream.close();
        }
    }

    public static String getServerAbsolutePath() throws NamingException {
        DirContext dirContext = DirContextURLStreamHandler.get();
        FileDirContext fdc = (FileDirContext) dirContext.lookup("");
        String webrootPath = fdc.getDocBase();
        return webrootPath;
    }

    public static Boolean copyFile(String oldPath, String newPath) {
        File oldfile = new File(oldPath);
        InputStream inStream = null;
        FileOutputStream fs = null;
        try {
            if (oldfile.exists()) {
                int byteread = 0;
                inStream = new FileInputStream(oldPath);
                fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                while ((byteread = inStream.read(buffer)) != -1) {
                    fs.write(buffer, 0, byteread);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                close(inStream);
                close(fs);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return Boolean.TRUE;
    }

    /**
     * createZip
     *
     * @param zipSaveDir
     * @param fileNames
     * @return finished ZIP file path
     * @throws NamingException
     * @throws IOException
     */
    public static File createZip(String path, String zipName, List<String> filePaths) {
        if (filePaths == null || filePaths.size() == 0) {
            return null;
        }
        File zipFile = null;
        ZipOutputStream zout = null;
        FileInputStream fin = null;
        try {
            zipFile = getFile(path, zipName);
            zout = new ZipOutputStream(new FileOutputStream(zipFile));
            for (String filePath : filePaths) {
                File file = getFile(path, FilesUtils.getFileNameByFilePath(filePath));
                fin = new FileInputStream(file);
                zout.putNextEntry(new ZipEntry(file.getName()));
                byte[] bytes = new byte[1024];
                while (fin.read(bytes) > 0) {
                    zout.write(bytes);
                }
                zout.closeEntry();
                close(fin);
                file.delete();
            }
            zout.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (fin != null) {
                    close(fin);
                }
                if (zout != null) {
                    close(zout);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return zipFile;
    }

    /**
     * unpackZip
     *
     * @param zipFilePath
     * @param outPath
     * @return unpack after file path
     * @throws IOException
     */

    public static Boolean unpackZip(String zipFilePath, String outPath) {
        Boolean isExistDirectory = Boolean.FALSE;
        ZipFile zipFile = null;
        FileOutputStream fout = null;
        DataOutputStream dout = null;
        File file;
        InputStream in = null;
        try {
            zipFile = new ZipFile(zipFilePath);
            for (Enumeration<?> entries = zipFile.entries(); entries.hasMoreElements();) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                if (entry.isDirectory()) {
                    isExistDirectory = Boolean.TRUE;
                } else {
                    file = new File(entry.getName());
                    in = zipFile.getInputStream(entry);
                    fout = new FileOutputStream(outPath + File.separator + file.getPath());
                    dout = new DataOutputStream(fout);
                    byte[] b = new byte[1024];
                    int len = 0;
                    while ((len = in.read(b)) != -1) {
                        dout.write(b, 0, len);
                    }
                    fout.close();
                    in.close();
                }
            }
        } catch (IOException e) {
            new RuntimeException(e);
        } finally {
            try {
                if (fout != null) {
                    fout.close();
                }
                if (in != null) {
                    in.close();
                }
                if (zipFile != null){
                    zipFile.close();
                }
            } catch (IOException e) {
                new RuntimeException(e);
            }
        }
        return isExistDirectory;
    }

    /**
     * createFolder
     *
     * @param filePath
     */
    public static void createFolder(String filePath) {
        File newPath = new File(filePath);
        if (!newPath.exists()) {
            newPath.mkdirs();
        }
    }

    public static File getFolder(String filePath) {
        File newPath = new File(filePath);
        if (!newPath.exists()) {
            newPath.mkdirs();
        }
        return newPath;
    }
    /**
     * deleteForder
     *
     * @param filepath
     */
    public static void deleteFolder(String filepath) {
        File f = new File(filepath);
        if (f.exists() && f.isDirectory()) {
            if (f.listFiles().length == 0) {
                f.delete();
            } else {
                File delFile[] = f.listFiles();
                int i = f.listFiles().length;
                for (int j = 0; j < i; j++) {
                    if (delFile[j].isDirectory()) {
                        deleteFolder(delFile[j].getAbsolutePath());
                    }
                    delFile[j].delete();
                }
                f.delete();
            }
        }
    }

    /**
     * getFileNameByFilePath
     *
     * @param path
     * @return
     */

    public static String getFileNameByFilePath(String path) {
        String fileName = new File(path).getName();
        return fileName;
    }

}
