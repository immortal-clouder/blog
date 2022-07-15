package com.lansheng.blog.aspect;

import com.alibaba.fastjson.JSON;
import com.lansheng.blog.annotation.OptLog;
import com.lansheng.blog.dao.OperationLogDao;
import com.lansheng.blog.entity.OperationLog;
import com.lansheng.blog.utils.IpUtils;
import com.lansheng.blog.utils.UserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @description: 操作日志切面处理
 * @author: 兰生
 * @date: 2022/07/15 16:59
 * @version: 1.0
 */
@Aspect
@Component
public class OptLogAspect {

    @Autowired
    private OperationLogDao operationLogDao;

    /**
     * 设置操作日志切入点 记录操作日志 在注解的位置切入代码
     */
    @Pointcut("@annotation(com.lansheng.blog.annotation.OptLog)")
    public void optLogPointCut() {}


    /**
     * 正常返回通知，拦截用户操作日志，连接点正常执行完成后执行， 如果连接点抛出异常，则不会执行
     *
     * @param joinPoint 切入点
     * @param keys      返回结果
     */
    @AfterReturning(value = "optLogPointCut()", returning = "keys")
    @SuppressWarnings("unchecked")
    public void saveOptLog(JoinPoint joinPoint, Object keys) {
        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) Objects
                .requireNonNull(requestAttributes)
                .resolveReference(RequestAttributes.REFERENCE_REQUEST);

        // 从切面织入点处通过反射机制获取织入点处的方法签名MethodSignature
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 通过方法签名获取切入点所在的方法
        Method method = signature.getMethod();

        /**
         * 获取操作日志实体类
         */
        OperationLog operationLog = new OperationLog();

        /**
         * 通过注解获取操作模块、操作类型和操作描述
         */
        // 获取操作
        Api api = (Api) signature.getDeclaringType().getAnnotation(Api.class);
        ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
        OptLog optLog = method.getAnnotation(OptLog.class);
        // 操作模块
        operationLog.setOptModule(api.tags()[0]);
        // 操作类型
        operationLog.setOptType(optLog.optType());
        // 操作描述
        operationLog.setOptDesc(apiOperation.value());

        /**
         * 操作日志中的请求方法是由请求类名和请求方法名组成的
         */
        // 获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        // 获取请求的方法名
        String methodName = method.getName();
        methodName = className + "." + methodName;
        // 请求方法
        operationLog.setOptMethod(methodName);

        /**
         * 通过 HttpServletRequest获取请求URL、请求方式，然后通过IpUtils工具类获取请求IP地址，并根据IP地址解析出用户所在的地理位置IpSource
         */
        // 请求URL
        operationLog.setOptUrl(request.getRequestURI());
        // 请求方式
        operationLog.setRequestMethod(Objects.requireNonNull(request).getMethod());
        // 请求IP
        String ipAddress = IpUtils.getIpAddress(request);
        operationLog.setIpAddress(ipAddress);
        operationLog.setIpSource(IpUtils.getIpSource(ipAddress));

        /**
         * 通过 @AfterReturning(value = "optLogPointCut()", returning = "keys")
         * 绑定的参数joinPoint和keys，获取请求参数和返回结果
         */
        // 请求参数
        operationLog.setRequestParam(JSON.toJSONString(joinPoint.getArgs()));
        // 返回结果
        operationLog.setResponseData(JSON.toJSONString(keys));

        /**
         * 通过 UserUtils获取登录用户的信息中的id和昵称
         */
        // 请求用户ID
        operationLog.setUserId(UserUtils.getLoginUser().getId());
        // 请求用户
        operationLog.setNickname(UserUtils.getLoginUser().getNickname());


        operationLogDao.insert(operationLog);
    }

}
