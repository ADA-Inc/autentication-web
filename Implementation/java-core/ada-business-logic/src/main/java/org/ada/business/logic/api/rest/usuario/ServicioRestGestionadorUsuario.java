package org.ada.business.logic.api.rest.usuario;

import org.ada.data.access.percistencia.gestion.usuario.controlador.GestionadorUsuarioControllerDB;
import org.ada.security.model.persistencia.respuesta.ProcesoRespuestaApiDbModulos;
import org.ada.security.model.persistencia.usuario.UsuarioActualizarDBDto;
import org.ada.security.model.persistencia.usuario.UsuarioDBDto;
import org.ada.security.model.rest.respuesta.ProcesoRespuestaApiRest;
import org.ada.security.model.rest.respuesta.ProcesoRespuestaApiRestLogin;
import org.ada.security.model.rest.respuesta.ProcesoRespuestaApiRestModulos;
import org.ada.security.model.rest.usuario.FuncionalidadUsuarioRestDto;
import org.ada.security.model.rest.usuario.UsuarioActualizarRestDto;
import org.ada.security.model.rest.usuario.UsuarioRestDto;
import org.ada.util.constantes.ConstantesApiPathRest;
import org.ada.util.constantes.ConstantesCodigosAplicacion;
import org.ada.util.constantes.ConstantesMensajesAplicacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class ServicioRestGestionadorUsuario {

	@Autowired
	GestionadorUsuarioControllerDB gestionadorUsuarioControllerDB;
	
	@RequestMapping(value = ConstantesApiPathRest.PATH_LOGIN, method = RequestMethod.POST)
	public ResponseEntity<ProcesoRespuestaApiRestLogin> loginUsuario(@RequestBody UsuarioRestDto usuarioRestDto) {

		/*
		 * =================================================
		 * Se realiza instacia de objetos que se utilizaran 
		 * en el medoto.
		 * ************************************************* 
		 */
		UsuarioDBDto usuarioDBDto = null;
		ProcesoRespuestaApiRestLogin procesoRespuestaApiRestLogin = null;
		ProcesoRespuestaApiDbModulos procesoRespuestaApiDbModulos = null;

		/*
		 * ==============================================
		 * Se se realiza el mapeo de los parametros 
		 * de entrada que necesita el servicio ofrecido por 
		 * la API y se llama el controller
		 * ************************************************* 
		 */

		/*
		 * =====================================
		 * Este if se encarga de ...
		 * =====================================
		 */
		if (usuarioRestDto.getNombreUsuario()!=null) {


			try {
				usuarioDBDto = new UsuarioDBDto();
				usuarioDBDto.setP_NOMBRE_USUARIO(usuarioRestDto.getNombreUsuario());
				usuarioDBDto.setP_PASSWORD_USUARIO(usuarioRestDto.getClaveUsuario());

				procesoRespuestaApiDbModulos = gestionadorUsuarioControllerDB.loginUsuario(usuarioDBDto);

				/*
				 * =====================================
				 * Este if se encarga de ...
				 * =====================================
				 */
				if (procesoRespuestaApiDbModulos!= null && procesoRespuestaApiDbModulos.getCodigoRespuestaApi()!=null ) {
					procesoRespuestaApiRestLogin = new ProcesoRespuestaApiRestLogin();
					procesoRespuestaApiRestLogin.setIdUsuario(procesoRespuestaApiDbModulos.getIdUsuario());
					procesoRespuestaApiRestLogin.setCodigoRespuestaApi(procesoRespuestaApiDbModulos.getCodigoRespuestaApi());
					procesoRespuestaApiRestLogin.setMensajeRespuestaApi(procesoRespuestaApiDbModulos.getMensajeRespuestaApi());

				}else {
					procesoRespuestaApiRestLogin = new ProcesoRespuestaApiRestLogin();
					procesoRespuestaApiRestLogin.setCodigoRespuestaApi(ConstantesCodigosAplicacion.CODIGO_ERROR_SERVICIO);
					procesoRespuestaApiRestLogin.setMensajeRespuestaApi(ConstantesMensajesAplicacion.CODIGO_MENSAJE_ERROR_SERVICIO);
				}

			} catch (Exception e) {
				procesoRespuestaApiRestLogin = new ProcesoRespuestaApiRestLogin();
				procesoRespuestaApiRestLogin.setCodigoRespuestaApi(ConstantesCodigosAplicacion.CODIGO_ERROR_SERVICIO);
				procesoRespuestaApiRestLogin.setMensajeRespuestaApi(ConstantesMensajesAplicacion.CODIGO_MENSAJE_ERROR_SERVICIO);
			}

		}else {
			procesoRespuestaApiRestLogin = new ProcesoRespuestaApiRestLogin();
			procesoRespuestaApiRestLogin.setCodigoRespuestaApi(ConstantesCodigosAplicacion.CODIGO_ERROR_PARAMETROS_SERVICIO);
			procesoRespuestaApiRestLogin.setMensajeRespuestaApi(ConstantesMensajesAplicacion.CODIGO_MENSAJE_ERROR_PARAMETROS_SERVICIO);
		}

		if(procesoRespuestaApiRestLogin.getCodigoRespuestaApi()!=null){
			return new ResponseEntity<ProcesoRespuestaApiRestLogin>(procesoRespuestaApiRestLogin,HttpStatus.OK);
		}else {
			return new ResponseEntity<ProcesoRespuestaApiRestLogin>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		}


	}

	@RequestMapping(value = ConstantesApiPathRest.PATH_REGISTRO_USUARIO, method = RequestMethod.POST)
	public ResponseEntity<ProcesoRespuestaApiRest> registrarUsuarioRol(@RequestBody UsuarioRestDto usuarioRestDto) {

		/*
		 * ==============================================
		 * Se realiza instacia de objetos que se utilizaran 
		 * en el medoto.
		 * ************************************************* 
		 */
		UsuarioDBDto usuarioDBDto = null;
		ProcesoRespuestaApiRest procesoRespuestaApiRest = null;
		ProcesoRespuestaApiDbModulos procesoRespuestaApiDbModulos = null;

		/*
		 * ==============================================
		 * Se se realiza el mapeo de los parametros 
		 * de entrada que necesita el servicio ofrecido por 
		 * la API y se llama el controller
		 * ************************************************* 
		 */

		/*
		 * =====================================
		 * Este if se encarga de ...
		 * =====================================
		 */
		if (usuarioRestDto.getDocumentoPersona()!=null && usuarioRestDto.getNombreRol()!=null && usuarioRestDto.getNombreUsuario()!=null) {


			try {
				usuarioDBDto = new UsuarioDBDto();
				usuarioDBDto.setP_APELLIDO_PERSONA(usuarioRestDto.getApellidoPersona());
				usuarioDBDto.setP_DIRECCION_PERSONA(usuarioRestDto.getDireccionPersona());
				usuarioDBDto.setP_DOCUMENTO_PERSONA(usuarioRestDto.getDocumentoPersona());
				usuarioDBDto.setP_EMAIL_PERSONA(usuarioRestDto.getEmailPersona());
				usuarioDBDto.setP_NOMBRE_ROLL(usuarioRestDto.getNombreRol());
				usuarioDBDto.setP_NOMBRE_USUARIO(usuarioRestDto.getNombreUsuario());
				usuarioDBDto.setP_NOMBRES_PERSONA(usuarioRestDto.getNombresPersona());
				usuarioDBDto.setP_PAIS_PERSONA(usuarioRestDto.getPaisPersona());
				usuarioDBDto.setP_PASSWORD_USUARIO(usuarioRestDto.getClaveUsuario());
				usuarioDBDto.setP_TELEFONO_PERSONA(usuarioRestDto.getTelefonoPersona());

				procesoRespuestaApiDbModulos = gestionadorUsuarioControllerDB.registrarUsuarioRol(usuarioDBDto);

				/*
				 * =====================================
				 * Este if se encarga de ...
				 * =====================================
				 */
				if (procesoRespuestaApiDbModulos!= null && procesoRespuestaApiDbModulos.getCodigoRespuestaApi()!=null ) {
					procesoRespuestaApiRest = new ProcesoRespuestaApiRest();
					procesoRespuestaApiRest.setCodigoRespuestaApi(procesoRespuestaApiDbModulos.getCodigoRespuestaApi());
					procesoRespuestaApiRest.setMensajeRespuestaApi(procesoRespuestaApiDbModulos.getMensajeRespuestaApi());

				}else {
					procesoRespuestaApiRest = new ProcesoRespuestaApiRest();
					procesoRespuestaApiRest.setCodigoRespuestaApi(ConstantesCodigosAplicacion.CODIGO_ERROR_SERVICIO);
					procesoRespuestaApiRest.setMensajeRespuestaApi(ConstantesMensajesAplicacion.CODIGO_MENSAJE_ERROR_SERVICIO);
				}

			} catch (Exception e) {
				procesoRespuestaApiRest = new ProcesoRespuestaApiRest();
				procesoRespuestaApiRest.setCodigoRespuestaApi(ConstantesCodigosAplicacion.CODIGO_ERROR_SERVICIO);
				procesoRespuestaApiRest.setMensajeRespuestaApi(ConstantesMensajesAplicacion.CODIGO_MENSAJE_ERROR_SERVICIO);
			}

		}else {
			procesoRespuestaApiRest = new ProcesoRespuestaApiRest();
			procesoRespuestaApiRest.setCodigoRespuestaApi(ConstantesCodigosAplicacion.CODIGO_ERROR_PARAMETROS_SERVICIO);
			procesoRespuestaApiRest.setMensajeRespuestaApi(ConstantesMensajesAplicacion.CODIGO_MENSAJE_ERROR_PARAMETROS_SERVICIO);
		}

		if(procesoRespuestaApiRest.getCodigoRespuestaApi()!=null){
			return new ResponseEntity<ProcesoRespuestaApiRest>(procesoRespuestaApiRest,HttpStatus.OK);
		}else {
			return new ResponseEntity<ProcesoRespuestaApiRest>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		}


	}

	@RequestMapping(value = ConstantesApiPathRest.PATH_ACTUALIZAR_USUARIO, method = RequestMethod.POST)
	public ResponseEntity<ProcesoRespuestaApiRest> actualizarUsuarioPersona(@RequestBody FuncionalidadUsuarioRestDto  funcionalidadUsuarioRestDto) {

		/*
		 * ==============================================
		 * Se realiza instacia de objetos que se utilizaran 
		 * en el medoto.
		 * ************************************************* 
		 */
		UsuarioDBDto usuarioDBDto = null;
		UsuarioRestDto usuarioRestDto  = null;
		UsuarioActualizarRestDto usuarioActualizarRestDto  = null;
		UsuarioActualizarDBDto usuarioActualizarDBDto= null;
		ProcesoRespuestaApiRest procesoRespuestaApiRest = null;
		ProcesoRespuestaApiDbModulos procesoRespuestaApiDbModulos = null;

		/*
		  * =====================================
		  * Este if se encarga de ...
		  * =====================================
		*/
		if (funcionalidadUsuarioRestDto.getUsuarioRestDto()!= null && funcionalidadUsuarioRestDto.getActualizarRestDto()!= null) {
			usuarioRestDto = funcionalidadUsuarioRestDto.getUsuarioRestDto();
			usuarioActualizarRestDto = funcionalidadUsuarioRestDto.getActualizarRestDto();

		/*
		 * ==============================================
		 * Se se realiza el mapeo de los parametros 
		 * de entrada que necesita el servicio ofrecido por 
		 * la API y se llama el controller
		 * ************************************************* 
		 */

		/*
		 * =====================================
		 * Este if se encarga de ...
		 * =====================================
		 */
		if (usuarioRestDto.getDocumentoPersona()!=null && usuarioRestDto.getNombreUsuario()!=null) {
			try {
				
	
				
				usuarioDBDto = new UsuarioDBDto();
				usuarioActualizarDBDto = new UsuarioActualizarDBDto();
				
				usuarioDBDto.setP_NOMBRE_USUARIO(usuarioRestDto.getNombreUsuario());
				usuarioDBDto.setP_DOCUMENTO_PERSONA(usuarioRestDto.getDocumentoPersona());
				
				usuarioActualizarDBDto.setP_APELLIDO_PERSONA_ACT(usuarioActualizarRestDto.getApellidoPersonaActualizar());
				usuarioActualizarDBDto.setP_DOCUMENTO_PERSONA_ACT(usuarioActualizarRestDto.getDocumentoPersonaActualizar());
				usuarioActualizarDBDto.setP_DIRECCION_PERSONA_ACT(usuarioActualizarRestDto.getDireccionPersonaActualizar());
				usuarioActualizarDBDto.setP_EMAIL_PERSONA_ACT(usuarioActualizarRestDto.getEmailPersonaActualizar());
				usuarioActualizarDBDto.setP_NOMBRES_PERSONA_ACT(usuarioActualizarRestDto.getNombresPersonaActualizar());
				usuarioActualizarDBDto.setP_PAIS_PERSONA_ACT(usuarioActualizarRestDto.getPaisPersonaActualizar());
				usuarioActualizarDBDto.setP_PASSWORD_USUARIO_ACT(usuarioActualizarRestDto.getClaveUsuarioActualizar());
				usuarioActualizarDBDto.setP_TELEFONO_PERSONA_ACT(usuarioActualizarRestDto.getTelefonoPersonaActualizar());

				procesoRespuestaApiDbModulos = gestionadorUsuarioControllerDB.actualizarUsuarioPersona(usuarioDBDto,usuarioActualizarDBDto);

				/*
				 * =====================================
				 * Este if se encarga de ...
				 * =====================================
				 */
				if (procesoRespuestaApiDbModulos!= null && procesoRespuestaApiDbModulos.getCodigoRespuestaApi()!=null ) {
					procesoRespuestaApiRest = new ProcesoRespuestaApiRest();
					procesoRespuestaApiRest.setCodigoRespuestaApi(procesoRespuestaApiDbModulos.getCodigoRespuestaApi());
					procesoRespuestaApiRest.setMensajeRespuestaApi(procesoRespuestaApiDbModulos.getMensajeRespuestaApi());

				}else {
					procesoRespuestaApiRest = new ProcesoRespuestaApiRest();
					procesoRespuestaApiRest.setCodigoRespuestaApi(ConstantesCodigosAplicacion.CODIGO_ERROR_SERVICIO);
					procesoRespuestaApiRest.setMensajeRespuestaApi(ConstantesMensajesAplicacion.CODIGO_MENSAJE_ERROR_SERVICIO);
				}

			} catch (Exception e) {
				procesoRespuestaApiRest = new ProcesoRespuestaApiRest();
				procesoRespuestaApiRest.setCodigoRespuestaApi(ConstantesCodigosAplicacion.CODIGO_ERROR_SERVICIO);
				procesoRespuestaApiRest.setMensajeRespuestaApi(ConstantesMensajesAplicacion.CODIGO_MENSAJE_ERROR_SERVICIO);
			}

		}else {
			procesoRespuestaApiRest = new ProcesoRespuestaApiRest();
			procesoRespuestaApiRest.setCodigoRespuestaApi(ConstantesCodigosAplicacion.CODIGO_ERROR_PARAMETROS_SERVICIO);
			procesoRespuestaApiRest.setMensajeRespuestaApi(ConstantesMensajesAplicacion.CODIGO_MENSAJE_ERROR_PARAMETROS_SERVICIO);
		}
		}else {
			procesoRespuestaApiRest = new ProcesoRespuestaApiRest();
			procesoRespuestaApiRest.setCodigoRespuestaApi(ConstantesCodigosAplicacion.CODIGO_ERROR_PARAMETROS_SERVICIO);
			procesoRespuestaApiRest.setMensajeRespuestaApi(ConstantesMensajesAplicacion.CODIGO_MENSAJE_ERROR_PARAMETROS_SERVICIO);
		}
		if(procesoRespuestaApiRest.getCodigoRespuestaApi()!=null){
			return new ResponseEntity<ProcesoRespuestaApiRest>(procesoRespuestaApiRest,HttpStatus.OK);
		}else {
			return new ResponseEntity<ProcesoRespuestaApiRest>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		}


	}

	@RequestMapping(value = ConstantesApiPathRest.PATH_ASIGNAR_USUARIO, method = RequestMethod.POST)
	public ResponseEntity<ProcesoRespuestaApiRest> asignarRolUsuarioPersona(@RequestBody UsuarioRestDto usuarioRestDto) {

		/*
		 * ==============================================
		 * Se realiza instacia de objetos que se utilizaran 
		 * en el medoto.
		 * ************************************************* 
		 */
		UsuarioDBDto usuarioDBDto = null;
		ProcesoRespuestaApiRest procesoRespuestaApiRest = null;
		ProcesoRespuestaApiDbModulos procesoRespuestaApiDbModulos = null;

		/*
		 * ==============================================
		 * Se se realiza el mapeo de los parametros 
		 * de entrada que necesita el servicio ofrecido por 
		 * la API y se llama el controller
		 * ************************************************* 
		 */

		/*
		 * =====================================
		 * Este if se encarga de ...
		 * =====================================
		 */
		if (usuarioRestDto.getDocumentoPersona()!=null && usuarioRestDto.getNombreUsuario()!=null && usuarioRestDto.getNombreRol()!=null) {
			try {
				usuarioDBDto = new UsuarioDBDto();

				
				usuarioDBDto.setP_NOMBRE_USUARIO(usuarioRestDto.getNombreUsuario());
				usuarioDBDto.setP_DOCUMENTO_PERSONA(usuarioRestDto.getDocumentoPersona());
				usuarioDBDto.setP_NOMBRE_ROLL(usuarioRestDto.getNombreRol());


				procesoRespuestaApiDbModulos = gestionadorUsuarioControllerDB.asignarRolUsuarioPersona(usuarioDBDto);

				/*
				 * =====================================
				 * Este if se encarga de ...
				 * =====================================
				 */
				if (procesoRespuestaApiDbModulos!= null && procesoRespuestaApiDbModulos.getCodigoRespuestaApi()!=null ) {
					procesoRespuestaApiRest = new ProcesoRespuestaApiRest();
					procesoRespuestaApiRest.setCodigoRespuestaApi(procesoRespuestaApiDbModulos.getCodigoRespuestaApi());
					procesoRespuestaApiRest.setMensajeRespuestaApi(procesoRespuestaApiDbModulos.getMensajeRespuestaApi());

				}else {
					procesoRespuestaApiRest = new ProcesoRespuestaApiRest();
					procesoRespuestaApiRest.setCodigoRespuestaApi(ConstantesCodigosAplicacion.CODIGO_ERROR_SERVICIO);
					procesoRespuestaApiRest.setMensajeRespuestaApi(ConstantesMensajesAplicacion.CODIGO_MENSAJE_ERROR_SERVICIO);
				}

			} catch (Exception e) {
				procesoRespuestaApiRest = new ProcesoRespuestaApiRest();
				procesoRespuestaApiRest.setCodigoRespuestaApi(ConstantesCodigosAplicacion.CODIGO_ERROR_SERVICIO);
				procesoRespuestaApiRest.setMensajeRespuestaApi(ConstantesMensajesAplicacion.CODIGO_MENSAJE_ERROR_SERVICIO);
			}

		}else {
			procesoRespuestaApiRest = new ProcesoRespuestaApiRest();
			procesoRespuestaApiRest.setCodigoRespuestaApi(ConstantesCodigosAplicacion.CODIGO_ERROR_PARAMETROS_SERVICIO);
			procesoRespuestaApiRest.setMensajeRespuestaApi(ConstantesMensajesAplicacion.CODIGO_MENSAJE_ERROR_PARAMETROS_SERVICIO);
		}
		if(procesoRespuestaApiRest.getCodigoRespuestaApi()!=null){
			return new ResponseEntity<ProcesoRespuestaApiRest>(procesoRespuestaApiRest,HttpStatus.OK);
		}else {
			return new ResponseEntity<ProcesoRespuestaApiRest>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		}


	}
	
	@RequestMapping(value = ConstantesApiPathRest.PATH_OBTENER_MODULO, method = RequestMethod.POST)
	public ResponseEntity<ProcesoRespuestaApiRestModulos> obtenerUsuarioModulos(@RequestBody UsuarioRestDto usuarioRestDto) {

		/*
		 * ==============================================
		 * Se realiza instacia de objetos que se utilizaran 
		 * en el medoto.
		 * ************************************************* 
		 */
		UsuarioDBDto usuarioDBDto = null;
		ProcesoRespuestaApiRestModulos procesoRespuestaApiRestModulo = null;
		ProcesoRespuestaApiDbModulos procesoRespuestaApiDbModulos = null;

		/*
		 * ==============================================
		 * Se se realiza el mapeo de los parametros 
		 * de entrada que necesita el servicio ofrecido por 
		 * la API y se llama el controller
		 * ************************************************* 
		 */

		/*
		 * =====================================
		 * Este if se encarga de ...
		 * =====================================
		 */
		if (usuarioRestDto.getNombreUsuario()!=null) {
			try {
				usuarioDBDto = new UsuarioDBDto();
				
				usuarioDBDto.setP_NOMBRE_USUARIO(usuarioRestDto.getNombreUsuario());

				procesoRespuestaApiDbModulos = gestionadorUsuarioControllerDB.obtenerUsuarioModulos(usuarioDBDto);

				/*
				 * =====================================
				 * Este if se encarga de ...
				 * =====================================
				 */
				if (procesoRespuestaApiDbModulos!= null && procesoRespuestaApiDbModulos.getCodigoRespuestaApi()!=null ) {
					procesoRespuestaApiRestModulo = new ProcesoRespuestaApiRestModulos();
					procesoRespuestaApiRestModulo.setModulos(procesoRespuestaApiDbModulos.getModulos());
					procesoRespuestaApiRestModulo.setCodigoRespuestaApi(procesoRespuestaApiDbModulos.getCodigoRespuestaApi());
					procesoRespuestaApiRestModulo.setMensajeRespuestaApi(procesoRespuestaApiDbModulos.getMensajeRespuestaApi());

				}else {
					procesoRespuestaApiRestModulo = new ProcesoRespuestaApiRestModulos();
					procesoRespuestaApiRestModulo.setCodigoRespuestaApi(ConstantesCodigosAplicacion.CODIGO_ERROR_SERVICIO);
					procesoRespuestaApiRestModulo.setMensajeRespuestaApi(ConstantesMensajesAplicacion.CODIGO_MENSAJE_ERROR_SERVICIO);
				}

			} catch (Exception e) {
				procesoRespuestaApiRestModulo = new ProcesoRespuestaApiRestModulos();
				procesoRespuestaApiRestModulo.setCodigoRespuestaApi(ConstantesCodigosAplicacion.CODIGO_ERROR_SERVICIO);
				procesoRespuestaApiRestModulo.setMensajeRespuestaApi(ConstantesMensajesAplicacion.CODIGO_MENSAJE_ERROR_SERVICIO);
			}

		}else {
			procesoRespuestaApiRestModulo = new ProcesoRespuestaApiRestModulos();
			procesoRespuestaApiRestModulo.setCodigoRespuestaApi(ConstantesCodigosAplicacion.CODIGO_ERROR_PARAMETROS_SERVICIO);
			procesoRespuestaApiRestModulo.setMensajeRespuestaApi(ConstantesMensajesAplicacion.CODIGO_MENSAJE_ERROR_PARAMETROS_SERVICIO);
		}
		if(procesoRespuestaApiRestModulo.getCodigoRespuestaApi()!=null){
			return new ResponseEntity<ProcesoRespuestaApiRestModulos>(procesoRespuestaApiRestModulo,HttpStatus.OK);
		}else {
			return new ResponseEntity<ProcesoRespuestaApiRestModulos>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		}


	}
}
