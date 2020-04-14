package com.blm.webportal.pins.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.blm.webportal.pins.models.entity.Binnacle;
import com.blm.webportal.pins.models.entity.repository.BinnacleRepository;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Aspect
public class EventTrace {
	
	@Autowired
	private BinnacleRepository traceEventRepository;
	
	@Around("@annotation(com.blm.webportal.pins.aop.Trace)")
	public Object adaptee(ProceedingJoinPoint pjp) throws Throwable {

		try {
			String ip = "";
			String url = "";

			try {
				HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
						.currentRequestAttributes()).getRequest();
				ip = request.getRemoteAddr();
				url = request.getRequestURL().toString();
			} catch (Exception e) {
				System.out.println(e);
			}

			// before
			Object retVal = pjp.proceed();

			Binnacle event = new Binnacle();
			
			event.setIp(ip);
			event.setUrl(url);
			event.setMethod(pjp.getTarget().getClass().getSimpleName() + "." + pjp.getSignature().getName());
			event.setRequest_date(new Date());
			
			HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder
					.currentRequestAttributes()).getResponse();
			
			event.setStatus(response.getStatus());
			String errorInfo = "";
			switch (response.getStatus()){
	        case 404:
	            errorInfo = "Forbidden";
	            break;
	        case 500:
	            errorInfo = "Internal Server Error";
	            break;
	        case 200:
	            errorInfo = "Success";
	            break;
	        case 201:
	            errorInfo = "Created";
	            break;
	        default:
	            errorInfo = "ERROR_DEFAULT";
			}
			
			event.setRspDescription(errorInfo);

			if (pjp.getSignature().getName().equals("loadUserByUsername")) {
				event.setUser_name(pjp.getArgs()[0].toString());
			} else {
				try {
					String userName = SecurityContextHolder.getContext().getAuthentication().getName();
					event.setUser_name(userName);
				} catch (Exception e) {
					event.setUser_name("rest@api");
				}
			}

			traceEventRepository.save(event);
			return retVal;
		} catch (Throwable t) {
			throw t;
		}
	}
	
}
