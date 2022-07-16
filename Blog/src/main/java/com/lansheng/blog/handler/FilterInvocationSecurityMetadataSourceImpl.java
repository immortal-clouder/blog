package com.lansheng.blog.handler;

import com.lansheng.blog.dao.RoleDao;
import com.lansheng.blog.dto.ResourceRoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;

/**
 * @description: 接口拦截规则
 * @author: 兰生
 * @date: 2022/07/15
 * @version: 1.0
 */
@Component
public class FilterInvocationSecurityMetadataSourceImpl implements FilterInvocationSecurityMetadataSource {

    /**
     * 资源角色列表
     * 包含资源id，路径，请求方式，角色名
     */
    private static List<ResourceRoleDTO> resourceRoleList;

    @Autowired
    private RoleDao roleDao;

    /**
     * 加载资源角色信息
     * 资源父权限parent_id不为空
     *是否匿名访问isAnonymous=0
     */
    @PostConstruct  //@PostConstruct该注解被用来修饰一个非静态的void（）方法。被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器执行一次。PostConstruct在构造函数之后执行，init（）方法之前执行。
    private void loadDataSource() {
        resourceRoleList = roleDao.listResourceRoles();
    }

    /**
     * 清空接口角色信息
     */
    public void clearDataSource() {
        resourceRoleList = null;
    }

    /**
     * @description: 通过请求路径去数据库中查询对应的角色权限，得到角色权限。通过SecurityConfig.createList(str)方法对得到的角色权限进行设置。其中SecurityConfig是上述ConfigAttribute的子类，其中createList方法对权限的信息进行了设置。然后又通过getAttribute进行获取。
     * @author: 兰生
     * @date: 2022/07/16
     * @param: Object
     * @return: ConfigAttribute
     **/
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // 修改接口角色关系后重新加载
        if (CollectionUtils.isEmpty(resourceRoleList)) {
            this.loadDataSource();
        }

        //Spring Security 通过FilterInvocation对object进行封装，可以安全的拿到其HttpServletRequest 和 HttpServletResponse对象
        FilterInvocation fi = (FilterInvocation) object;
        // 获取用户请求方式
        String method = fi.getRequest().getMethod();
        // 获取用户请求Url
        String url = fi.getRequest().getRequestURI();


        //new一个工具类AntPathMatcher的实例化对象，把路径匹配委托给AntPathMatcher实现
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        // 获取接口角色信息，若为匿名接口则放行，若无对应角色则禁止
        for (ResourceRoleDTO resourceRoleDTO : resourceRoleList) {
            //判断resourceRoleList中是否有和参数对象的URL和method完全相同的对象
            if (antPathMatcher.match(resourceRoleDTO.getUrl(), url) && resourceRoleDTO.getRequestMethod().equals(method)) {
                //如果有对象匹配成功，则获取该对象的角色列表RoleList
                List<String> roleList = resourceRoleDTO.getRoleList();
                if (CollectionUtils.isEmpty(roleList)) {
                    return SecurityConfig.createList("disable");
                }
                return SecurityConfig.createList(roleList.toArray(new String[]{})); //rolelist集合转换成String数组，通过SecurityConfig.createList(str)对结果进行封装，然后return
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }

}
