/*
 * MIT License 
 * 
 * Copyright (c) 2018 ADA INC
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 */

package org.ada.security.model.persistencia.respuesta;

/**
 *
 * <h1>ProcesoRespuestaApiDb
 *
 * Description
 *
 * @author THEOVERLORDKOTAN (ADA, Inc) 
 * @version 1.0
 * 
 */
public class ProcesoRespuestaApiDb {
	
	private String idUsuario;
	private String codigoRespuestaApi;
	private String mensajeRespuestaApi;
	
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getCodigoRespuestaApi() {
		return codigoRespuestaApi;
	}
	public void setCodigoRespuestaApi(String codigoRespuestaApi) {
		this.codigoRespuestaApi = codigoRespuestaApi;
	}
	public String getMensajeRespuestaApi() {
		return mensajeRespuestaApi;
	}
	public void setMensajeRespuestaApi(String mensajeRespuestaApi) {
		this.mensajeRespuestaApi = mensajeRespuestaApi;
	}
	
	

}
