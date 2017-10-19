package com.ssm.manager.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ssm.commons.FileUtil;

@Controller
public class TestController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/hello")
	public String hello() {
		return "hello";
	}

	@RequestMapping("/upload")
	public String upload() {
		return "upload";
	}

	@RequestMapping("/uploadFile")
	public String upload(@RequestParam MultipartFile[] myfiles,
			HttpServletRequest request) throws IOException {
		if (myfiles == null || myfiles.length == 0) {
			return "upload";
		}
		for (MultipartFile file : myfiles) {
			// 此处MultipartFile[]表明是多文件,如果是单文件MultipartFile就行了
			if (file.isEmpty()) {
				System.out.println("文件未上传!");
			} else {
				// 得到上传的文件名
				String fileName = file.getOriginalFilename();
				boolean isImage = FileUtil.isImage(fileName);
				// 得到服务器项目发布运行所在地址
				String sourcePath = "";
				if (isImage) {
					sourcePath = request.getSession().getServletContext()
							.getRealPath("image")
							+ File.separator;
				} else {
					sourcePath = request.getSession().getServletContext()
							.getRealPath("tempFile")
							+ File.separator;
				}
				// 此处未使用UUID来生成唯一标识,用日期做为标识
				String path = sourcePath
						+ new SimpleDateFormat("yyyyMMddHHmmss")
								.format(new Date()) + fileName;
				// 查看文件上传路径,方便查找
				System.out.println(path);
				// 把文件上传至path的路径
				File localFile = new File(path);
				file.transferTo(localFile);
			}
		}
		return "success";
	}

	@RequestMapping("/download")
	public void download(String fileName, HttpServletRequest request,
			HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition", "attachment;fileName="
				+ fileName);
		try {
			String path = request.getSession().getServletContext()
					.getRealPath("image")
					+ File.separator;
			InputStream inputStream = new FileInputStream(new File(path
					+ fileName));

			OutputStream os = response.getOutputStream();
			byte[] b = new byte[2048];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}

			// 这里主要关闭。
			os.flush();
			os.close();
			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 返回值要注意，要不然就出现下面这句错误！
		// java+getOutputStream() has already been called for this response
	}
}
