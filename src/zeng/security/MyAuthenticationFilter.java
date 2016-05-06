package zeng.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MyAuthenticationFilter  implements Filter{
	private MyAuthentication myAuthentication; 
	public void setMyAuthentication(MyAuthentication myAuthentication) {
		this.myAuthentication = myAuthentication;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
		    FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		String relativeUrl = request.getRequestURI();/* 以根开头的URL */
		String path = request.getContextPath();/* 获取客户端请求的上下文根 */
		
		if (relativeUrl.replaceAll(path, "").equals("/login")) {// 登录请求
			PrintWriter out = response.getWriter();
			int status = myAuthentication.getAuthenticationStatus(request);
			if (status == 0) {// 通过认证
				out.print("{success:true,msg:0}");
				chain.doFilter(request, response);
			}else {// 未通过认证
				out.print("{success:false,status:"+status+"}");
			}
		}else{
			//不是登陆请求，直接通过，后期可拓展
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
	}


}
