package com.winterframework.logistics.portal.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.winterframework.logistics.base.enums.StatusCode;
import com.winterframework.logistics.base.model.Response;
import com.winterframework.logistics.base.upload.FileUpload;
import com.winterframework.logistics.base.upload.UploadResult;
import com.winterframework.modules.spring.exetend.PropertyConfig;
 
@Controller("ptlFileUploadController")
@RequestMapping("/portal/file")
public class PtlFileUploadController {
	private static final Logger log = LoggerFactory.getLogger(PtlFileUploadController.class);
	@Resource(name = "fileUpload")
	private FileUpload fileUpload;
	@PropertyConfig(value = "file.upload.types")
	private String fileUploadTypes;
	@PropertyConfig(value = "file.upload.size")
	private String fileUploadSize;
	//文件服务器地址
	@PropertyConfig(value = "server.url.file.sftp")
	protected String serverUrlFileSftp;
	@PropertyConfig(value = "server.url.file.visit")
	protected String serverUrlFileVisit;
	
	@RequestMapping("/upload")
	@ResponseBody
	public Object upload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request,HttpServletResponse response) throws Exception {
		//新增代码，如果文件过大，返回0错误信息
		Response<String> res=new Response<String>();  
		UploadResult result =fileUpload.upload(file, serverUrlFileSftp,request.getSession().getServletContext().getRealPath("fileUpload"),Arrays.asList(fileUploadTypes.split(",")), Long.valueOf(fileUploadSize));
		if(result.getStatus()==UploadResult.ResultStatus.SUCCESS.getValue()){
			res.setStatus(StatusCode.SUCCESS.getCode());
			res.setData(serverUrlFileVisit+result.getFileUrl());
		}else{
			res.setStatus(com.winterframework.logistics.portal.common.StatusCode.FILE_UPLOAD_FAILED.getCode());
			res.setMessage(result.getMessage());
		}
		return res;
	}
}
