package com.wsd.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wsd.annotation.ControllerLog;
import com.wsd.entity.ExceptionLog;
import com.wsd.entity.OperateLog;
import com.wsd.service.ExceptionLogService;
import com.wsd.service.OperateLogService;
import com.wsd.utils.ClientUtils;
import com.wsd.utils.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tm
 * @version 1.0.0
 * @description controller层日志切面
 * @updateRemark
 * @updateUser
 * @createDate 2020-3-26 15:57
 * @updateDate 2020-3-26 15:57
 */
@Aspect
@Component
public class ControllerLogAspect {

    @Autowired
    OperateLogService operateLogService;
    @Autowired
    ExceptionLogService exceptionLogService;

    /**
     * 记录操作日志切点
     */
    @Pointcut("@annotation(com.wsd.annotation.ControllerLog)")
    public void OperatePointcut(){}

    /**
     * 记录异常日志切点
     */
   @Pointcut("execution(* com.wsd.controller..*.*(..))")
   public void exceptionPointcut(){}

    @Around("OperatePointcut()")
    public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;

        result = joinPoint.proceed();

        return result;
    }

    /**
     * 方法执行成功后通知
     * @param joinPoint
     * @param keys
     */
    @AfterReturning(value = "OperatePointcut()", returning = "keys")
    public void addOperateLog(JoinPoint joinPoint, Object keys) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        OperateLog operateLog = new OperateLog();
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            ControllerLog controllerLog = method.getAnnotation(ControllerLog.class);
            if (controllerLog != null) {
                String model = controllerLog.model();
                String type = controllerLog.type();
                String describe = controllerLog.describe();
                operateLog.setModel(model);
                operateLog.setType(type);
                operateLog.setDetails(describe);
            }

            String className = joinPoint.getTarget().getClass().getName();
            String methodName = method.getName();
            methodName = className + "." + methodName;
            operateLog.setMethod(methodName);

            ObjectMapper objectMapper = new ObjectMapper();

            Map<String, String> rtnMap = converMap(request.getParameterMap());
            String params = objectMapper.writeValueAsString(rtnMap);
            operateLog.setRequestParam(params);

            String responseParam =  objectMapper.writeValueAsString(keys);
            operateLog.setResponseParam(responseParam);
            operateLog.setUserId(SecurityUtils.getCurrentUserInfo().getUserId());
            operateLog.setUserName(SecurityUtils.getCurrentUserInfo().getUsername());
            operateLog.setIp(ClientUtils.getIpAddr(request));
            operateLog.setUri(request.getRequestURI());
            operateLog.setCreateTime(new Date());

            //保存日志
            operateLogService.addOperateLog(operateLog);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 异常返回通知
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "exceptionPointcut()", throwing = "e")
    public void addExceptionLog(JoinPoint joinPoint, Throwable e) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        ExceptionLog exceptionLog = new ExceptionLog();
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = method.getName();
            methodName = className + "." + methodName;
            Map<String, String> rtnMap = converMap(request.getParameterMap());
            ObjectMapper objectMapper = new ObjectMapper();
            String params = objectMapper.writeValueAsString(rtnMap);

            exceptionLog.setRequestParam(params);
            exceptionLog.setMethod(methodName);
            exceptionLog.setName(e.getClass().getName());
            exceptionLog.setMessage(stackTraceToString(e.getClass().getName(), e.getMessage(), e.getStackTrace()));
            exceptionLog.setUserId(SecurityUtils.getCurrentUserInfo().getUserId());
            exceptionLog.setUserName(SecurityUtils.getCurrentUserInfo().getUsername());
            exceptionLog.setIp(ClientUtils.getIpAddr(request));
            exceptionLog.setUri(request.getRequestURI());
            exceptionLog.setCreateTime(new Date());

            //保存异常日志
            exceptionLogService.addExceptionLog(exceptionLog);

        } catch (Exception e2) {
            e2.printStackTrace();
        }

    }

    /**
     * 请求参数转换
     * @param paramMap
     * @return
     */
    public Map<String, String> converMap(Map<String, String[]> paramMap) {
        Map<String, String> rtnMap = new HashMap<String, String>();
        for (String key : paramMap.keySet()) {
            rtnMap.put(key, paramMap.get(key)[0]);
        }
        return rtnMap;
    }

    /**
     * 异常信息转换
     * @param exceptionName
     * @param exceptionMessage
     * @param elements
     * @return
     */
    public String stackTraceToString(String exceptionName, String exceptionMessage, StackTraceElement[] elements) {
        StringBuffer strbuff = new StringBuffer();
        for (StackTraceElement stet : elements) {
            strbuff.append(stet + "\n");
        }
        String message = exceptionName + ":" + exceptionMessage + "\n\t" + strbuff.toString();
        return message;
    }
}
