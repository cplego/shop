package org.cplego.foodie.resouce;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "file")
@PropertySource("classpath:uploadFile.properties")
public class Fileupload {

	private String imgUploadLoaction;

	private String proPath; //映射静态资源地址 域名

	public String getImgUploadLoaction() {
		return imgUploadLoaction;
	}

	public void setImgUploadLoaction(String imgUploadLoaction) {
		this.imgUploadLoaction = imgUploadLoaction;
	}

	public String getProPath() {
		return proPath;
	}

	public void setProPath(String proPath) {
		this.proPath = proPath;
	}
}
