package com.winterframework.logistics.base.upload;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemOptions;
import org.apache.commons.vfs2.Selectors;
import org.apache.commons.vfs2.cache.DefaultFilesCache;
import org.apache.commons.vfs2.impl.DefaultFileSystemManager;
import org.apache.commons.vfs2.provider.local.DefaultLocalFileProvider;
import org.apache.commons.vfs2.provider.sftp.SftpFileProvider;
import org.apache.commons.vfs2.provider.sftp.SftpFileSystemConfigBuilder;
import org.apache.commons.vfs2.provider.zip.ZipFileProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.winterframework.modules.spring.exetend.PropertyConfig;

/**
 * 文件上传
 * @ClassName
 * @Description
 * @author ibm
 * 2018年1月12日
 */
@Component("fileUpload")
public class FileUpload {

	private Logger logger = LoggerFactory.getLogger(FileUpload.class);
	private final static DefaultFileSystemManager mgr = getDefaultFileSystemManager();

	/** 
	 * 校验文件的类型
	 * @param fileType 当为空时，则不校验该文件的类型
	*/
	private boolean checkFileType(MultipartFile file, List<String> fileType) {
		String fileName = file.getOriginalFilename();
		boolean isFileType = true;
		if (!CollectionUtils.isEmpty(fileType)) {
			//将文件名改为大写，防止因为大小写造成的文件类型不一致
			List<String> upperFileType = new ArrayList<String>();
			for (String string : fileType) {
				upperFileType.add(string.toUpperCase());
			}
			if (StringUtils.isEmpty(fileName)) {
				isFileType = false;
			} else {
				String postfix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toUpperCase();
				if (!upperFileType.contains(postfix)) {
					isFileType = false;
				}
			}
		}
		return isFileType;
	}
	
	/** 
	 * 校验文件大小
	* @Title: checkFileSize 
	* @param file
	* @param size 当为null时，则不校验文件大小
	* @param result
	* @return
	*/
	private boolean checkFileSize(MultipartFile file, Long size, UploadResult result) {
		boolean isFileSize = true;
		if (size != null) {
			long fileSize = file.getSize();
			if (fileSize > size.longValue()) {
				isFileSize = false;
			}
		}
		if (result != null) {
			result.setImgSize(file.getSize());
		}
		return isFileSize;
	}

	/** 
	 * 校验文件的宽和高
	* @Title: checkImgSize 
	* @param file
	* @param imgSizes 当为空时，则不校验文件的宽和高
	* @param result
	* @return
	*/
	private boolean checkImgSize(MultipartFile file, List<ImgSize> imgSizes, UploadResult result) {
		InputStream in = null;
		boolean isWithHeight = true;
		try {
			in = file.getInputStream();
			if (imgSizes != null) {
				BufferedImage buff = ImageIO.read(in);
				Long width = Long.valueOf(buff.getWidth());
				Long height = Long.valueOf(buff.getHeight());
				result.setWidth(width);
				result.setHeight(height);
				logger.error ("image size:width=" +  width.toString() + "  height=" + height.toString() );
				for (ImgSize imgSize : imgSizes) {
					if (imgSize.getHeight().longValue() != height.longValue()
							|| imgSize.getWidth().longValue() != width.longValue()) {
						isWithHeight = false;
						logger.error ("image size:width=" +  width.toString() + "  height=" + height.toString() );
						break;
					}
				}
			}
		} catch (IOException e) {
			logger.error("checkImgWidthAndHeight error", e);
			isWithHeight = false;
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				logger.error("checkImgWidthAndHeight error", e);
			}
		}
		return isWithHeight;
	}

	/** 
	 * 校验文件类型，大小以及宽高
	* @Title: checkFile 
	* @param file
	* @param fileType
	* @param size
	* @param imgSizes
	* @param result
	* @return
	* @throws Exception
	*/
	protected UploadResult checkFile(MultipartFile file, List<String> fileType, Long size, List<ImgSize> imgSizes) throws Exception {
		UploadResult result=new UploadResult();
		result.setStatus(UploadResult.ResultStatus.SUCCESS.getValue());
		result.setMessage(UploadResult.ResultStatus.SUCCESS.getMsg());
		if (!checkFileType(file, fileType)) {
			result.setStatus(UploadResult.ResultStatus.TYPE.getValue());
			result.setMessage(UploadResult.ResultStatus.TYPE.getMsg());
		}else if (!checkFileSize(file, size, result)) {
			result.setStatus(UploadResult.ResultStatus.SIZE.getValue());
			result.setMessage(UploadResult.ResultStatus.SIZE.getMsg());
		}else if (!checkImgSize(file, imgSizes, result)) {
			result.setStatus(UploadResult.ResultStatus.SPACESIZE.getValue());
			result.setMessage(UploadResult.ResultStatus.SPACESIZE.getMsg());
		}
		return result;
	}

	public UploadResult checkFile(MultipartFile file, List<String> fileType, Long size)
			throws Exception {
		return this.checkFile(file, fileType, size, null);
	}

	public UploadResult upload(MultipartFile file,String fileServerUrl,String filePath) throws Exception {
		return this.upload(file, fileServerUrl,filePath, null, null, null);
	}

	public UploadResult upload(MultipartFile file, String fileServerUrl,String filePath, List<String> fileType) throws Exception {
		return this.upload(file,fileServerUrl,filePath, fileType, null, null);
	}

	public UploadResult upload(MultipartFile file,String fileServerUrl,String filePath, List<String> fileType, Long size)
			throws Exception {
		return this.upload(file, fileServerUrl,filePath, fileType, size, null);
	}

	/** 
	 * 上传图片
	* @Title: upload 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param file
	* @param path
	* @param fileType
	* @param size
	* @param imgSizes
	* @return
	* @throws Exception
	*/
	public UploadResult upload(MultipartFile file,String fileServerUrl,String filePath, List<String> fileType, Long size,
			List<ImgSize> imgSizes) throws Exception {
		UploadResult result = new UploadResult();
		if ((result=checkFile(file, fileType, size, imgSizes)).getStatus()!=UploadResult.ResultStatus.SUCCESS.getValue()) {
			return result;
		}
		String oriName =getExtension(file.getOriginalFilename());
		String fileName = org.apache.commons.codec.digest.DigestUtils.md5Hex(file.getBytes()) + "." + oriName;
		result.setOriName(oriName);
		result.setFileUrl(fileName);
		File targetFile = new File(filePath, fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		try {
			//临时保存图片
			file.transferTo(targetFile);
			// 上传到文件服务器
			FileObject localFile = mgr.resolveFile(filePath);
			FileSystemOptions opts = new FileSystemOptions();
			SftpFileSystemConfigBuilder sscb = SftpFileSystemConfigBuilder.getInstance();
			sscb.setStrictHostKeyChecking(opts, "no");
			sscb.setUserDirIsRoot(opts, false);
			logger.info("开始文件上传:", fileServerUrl);
			FileObject ftpFile = mgr.resolveFile(fileServerUrl, opts);
			logger.info("上传成功", ftpFile);
			if (!ftpFile.exists()) {
				ftpFile.createFolder();
			}
			ftpFile.copyFrom(localFile, Selectors.SELECT_ALL);
		} catch (Exception e) {
			result.setStatus(UploadResult.ResultStatus.OTHER.getValue());
			result.setMessage(UploadResult.ResultStatus.OTHER.getMsg());
			logger.error("文件上传错误", e);
		}
		targetFile.delete();
		return result;
	}

	public UploadResult moveFile(MultipartFile file,String targetPath,String sourcePath, List<String> fileType, Long size,
			List<ImgSize> imgSizes) throws Exception {
		UploadResult result = new UploadResult();
		if ((result=checkFile(file, fileType, size, imgSizes)).getStatus()!=UploadResult.ResultStatus.SUCCESS.getValue()) {
			return result;
		}
		String oriName =getExtension(file.getOriginalFilename());
		String fileName = org.apache.commons.codec.digest.DigestUtils.md5Hex(file.getBytes()) + "." + oriName;
		result.setOriName(oriName);
		result.setFileUrl(fileName);
	    
	    File localFile = new File(sourcePath, fileName);
		if (!localFile.exists()) {
			localFile.mkdirs();
		}
		//临时保存图片
		file.transferTo(localFile);
		FileObject localFileObj = mgr.resolveFile(sourcePath);
		
	    FileSystemOptions opts = new FileSystemOptions();
	    SftpFileSystemConfigBuilder.getInstance().setStrictHostKeyChecking(opts, "no");
	    FileObject  targetfileObj = mgr.resolveFile(targetPath, opts);
	    if (!targetfileObj.exists()) {
	    	targetfileObj.createFolder();
	    }
	    try {
	    	targetfileObj.copyFrom(localFileObj, Selectors.SELECT_FILES);
	    } catch (FileSystemException e) {
	    	result.setStatus(UploadResult.ResultStatus.OTHER.getValue());
			result.setMessage(UploadResult.ResultStatus.OTHER.getMsg());
			logger.error("文件上传错误", e);
	    }
	    localFile.delete();
		return result;
	}
	public static String getExtension(String s) {
        String ext = null;
        int i = s.lastIndexOf('.');
        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1);
        }
        return ext;
    }
	private static DefaultFileSystemManager getDefaultFileSystemManager() {

		DefaultFileSystemManager mgr = new DefaultFileSystemManager();
		// SFTP 供应者
		SftpFileProvider fp = new SftpFileProvider();

		// ZIP 供应者
		ZipFileProvider zp = new ZipFileProvider();
		// 缺省本地文件供应者
		DefaultLocalFileProvider lf = new DefaultLocalFileProvider();

		try {
			// common-vfs 中 文件管理器的使用范例
			mgr.addProvider("sftp", fp);
			mgr.addProvider("zip", zp);
			mgr.addProvider("file", lf);
			mgr.setFilesCache(new DefaultFilesCache());
			mgr.init();

		} catch (FileSystemException e) {
			e.printStackTrace();
		}
		return mgr;
	}
}
