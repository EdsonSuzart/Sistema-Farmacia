//ESSA CLASSE UTIL SERÁ PARA CRIAR JANELAS DE MENSAGENS NO SISTEMA, COMO:  SALVO COM SUCESSO, ERRO E ETC

package br.com.farmacia.util;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class JSFUtil {

	public static void adicionarMensagemSucesso(String mensagem) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,mensagem,mensagem);
		FacesContext contexto = FacesContext.getCurrentInstance(); // classe que coloca o erro de acordo com sua aplicação, de acordo com o contexto
		contexto.addMessage(null, msg);
	}
	
	public static void adicionarMensagemErro(String mensagem) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,mensagem,mensagem);
		FacesContext contexto = FacesContext.getCurrentInstance(); // classe que coloca o erro de acordo com sua aplicação, de acordo com o contexto
		contexto.addMessage(null, msg);
	}
}
