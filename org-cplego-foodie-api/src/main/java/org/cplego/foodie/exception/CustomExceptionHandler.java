package org.cplego.foodie.exception;

		import org.cplego.foodie.utils.JSONResult;
		import org.springframework.web.bind.annotation.ExceptionHandler;
		import org.springframework.web.bind.annotation.RestControllerAdvice;
		import org.springframework.web.multipart.MaxUploadSizeExceededException;

@RestControllerAdvice
public class CustomExceptionHandler {
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public JSONResult handlerMaxUploadFile(MaxUploadSizeExceededException ex){
		return JSONResult.errorMsg("文件大小超过限制500k");
	}

}
