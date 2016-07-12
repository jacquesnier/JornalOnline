package br.ufc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.ufc.model.Pessoa;

@Component
public class AutorizadorInteceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		String uri = request.getRequestURI();
		
		if(!permitirLeitor(uri) && !permitirEditor(uri) && !permitirJornalista(uri)){
			return true;
		}
		
		Pessoa logado = (Pessoa)request.getSession().getAttribute("PESSOA_LOGADA");
		
		
		if(logado!=null){
			if(logado.isEditor()){
				return permitirEditor(uri);
			}else if(logado.isJornalista()){
				return permitirJornalista(uri);
			}else if(logado.isLeitor()){
				return permitirLeitor(uri);
			}
		}
			// não tenha login
		
		response.sendRedirect("/");
		return false;
		
	}
	
	private boolean permitirEditor(String uri){
		if(uri.endsWith("/secao/add") || uri.endsWith("/pessoa/jornalista/add") || uri.endsWith("/noticia/excluir/") 
				|| uri.endsWith("/classificado/add") || uri.endsWith("/pessoa/editor/gerenciador")){
			return true;
		}
		return false;
	}
	
	private boolean permitirJornalista(String uri){
		if(uri.endsWith("/noticia/add") || uri.contains("/noticia/excluir/") 
				|| uri.endsWith("/pessoa/jornalista/gerenciador") ){
			return true;
		}
		return false;
	}

	private boolean permitirLeitor(String uri){
		if(uri.endsWith("/comentario/add") || uri.contains("oferta/add")){
			return true;
		}
		return false;
	}

	
}
