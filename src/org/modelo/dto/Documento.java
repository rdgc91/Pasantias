package org.modelo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.zkoss.zul.AbstractTreeModel;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Row;
import org.zkoss.zul.Tree;
import org.zkoss.zul.TreeModel;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.TreeitemRenderer;

@Entity
public class Documento
  implements Serializable
{
	@Id
	  @GeneratedValue(strategy=GenerationType.AUTO, generator="my_entity_seq_gen")
	  @SequenceGenerator(name="my_entity_seq_gen", sequenceName="idDocumento_seq")
	  private Integer idCodigo;
	private String codigo;
	private String titulo;
	@ManyToOne(fetch=FetchType.EAGER)
	  @JoinColumn(name="tipoDocumento")
	  private TipoDocumento tipoDocumento;
	@ManyToOne(cascade={javax.persistence.CascadeType.MERGE}, fetch=FetchType.EAGER)
	  @JoinColumn(name="padre")
	  private Documento padre;
	@ManyToOne(fetch=FetchType.EAGER)
	  @JoinColumn(name="area")
	  private Area area;
	@ManyToOne( fetch=FetchType.EAGER)
	  @JoinColumn(name="subArea")
	  private Area subArea;
	  @OneToMany(cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.EAGER)
	  @JoinColumn(name="documento")
	  private Set<Documento> historial = new HashSet();
  @OneToMany(cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.EAGER)
  @JoinColumn(name="documento")
  private Set<Valor> valores = new HashSet();
  
  @OneToMany(cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.EAGER)
  @JoinColumn(name="documento")
  private Set<HistorialDocumento> ocurrencias = new HashSet();
  
  private String estatus = "Nuevo";
  
  @ManyToOne(fetch=FetchType.EAGER)
  @JoinColumn(name="elaborador")
  private Usuario usuarioElaborador;
  @ManyToOne(fetch=FetchType.EAGER)
  @JoinColumn(name="usuarioActual")
  private Usuario usuarioActual;
  
  private String cargoElaborador;
  private Date fechaElaboracion = new Date();
  private Date fechaAprobacion;
  private Integer nroRevision=0;
  
  @OneToMany(mappedBy="documento", fetch=FetchType.EAGER, cascade={javax.persistence.CascadeType.ALL})
  @Fetch(FetchMode.SELECT)
  @NotFound(action=NotFoundAction.IGNORE)
  private Set<DocumentoAprobador> aprobadores = new HashSet();
  
  @OneToMany(mappedBy="documento", fetch=FetchType.EAGER, cascade={javax.persistence.CascadeType.ALL})
  @Fetch(FetchMode.SELECT)
  @NotFound(action=NotFoundAction.IGNORE)
  private Set<HistorialAprobador> historicoAprobadores = new HashSet();
  
  @ManyToMany(fetch=FetchType.EAGER)
  private Set<Documento> referenciasDocumentos = new HashSet();
  
  @ManyToMany(fetch=FetchType.EAGER)
  private Set<Usuario> referenciasUsuarios = new HashSet();
  
  @ManyToMany(fetch=FetchType.EAGER)
  private Set<Glosario> referenciasTerminos = new HashSet();
  
  
  
public Set<HistorialDocumento> getOcurrencias() {
	return ocurrencias;
}
public void setOcurrencias(Set<HistorialDocumento> ocurrencias) {
	this.ocurrencias = ocurrencias;
}
public Set<Glosario> getReferenciasTerminos() {
	return referenciasTerminos;
}
public void setReferenciasTerminos(Set<Glosario> referenciasTerminos) {
	this.referenciasTerminos = referenciasTerminos;
}
public Set<Usuario> getReferenciasUsuarios() {
	return referenciasUsuarios;
}
public void setReferenciasUsuarios(Set<Usuario> referenciasUsuarios) {
	this.referenciasUsuarios = referenciasUsuarios;
}
public Set<Documento> getReferenciasDocumentos() {
	return referenciasDocumentos;
}
public void setReferenciasDocumentos(Set<Documento> referenciasDocumentos) {
	this.referenciasDocumentos = referenciasDocumentos;
}
public Set<HistorialAprobador> getHistoricoAprobadores() {
	return historicoAprobadores;
}
public void setHistoricoAprobadores(Set<HistorialAprobador> historicoAprobadores) {
	this.historicoAprobadores = historicoAprobadores;
}
public Date getFechaAprobacion() {
	return fechaAprobacion;
}
public void setFechaAprobacion(Date fechaAprobacion) {
	this.fechaAprobacion = fechaAprobacion;
}
public Integer getIdCodigo() {
	return idCodigo;
}
public void setIdCodigo(Integer idCodigo) {
	this.idCodigo = idCodigo;
}
public Usuario getUsuarioActual() {
	return usuarioActual;
}
public void setUsuarioActual(Usuario usuarioActual) {
	this.usuarioActual = usuarioActual;
}
public Integer getNroRevision() {
	return nroRevision;
}
public void setNroRevision(Integer nroRevision) {
	this.nroRevision = nroRevision;
}
public Usuario getUsuarioElaborador() {
	return usuarioElaborador;
}
public void setUsuarioElaborador(Usuario usuarioElaborador) {
	this.usuarioElaborador = usuarioElaborador;
}
public String getCargoElaborador() {
	return cargoElaborador;
}
public void setCargoElaborador(String cargoElaborador) {
	this.cargoElaborador = cargoElaborador;
}
public Date getFechaElaboracion() {
	return fechaElaboracion;
}
public void setFechaElaboracion(Date fechaElaboracion) {
	this.fechaElaboracion = fechaElaboracion;
}
public String getTitulo() {
	return titulo;
}
public void setTitulo(String titulo) {
	this.titulo = titulo;
}
public Set<DocumentoAprobador> getAprobadores() {
	ordenarAprobadores();
	return aprobadores;
}
public void setAprobadores(Set<DocumentoAprobador> aprobadores) {
	this.aprobadores = aprobadores;
}
public String getCodigo() {
	return codigo;
}
public void setCodigo(String codigo) {
	this.codigo = codigo;
}
public TipoDocumento getTipoDocumento() {
	return tipoDocumento;
}
public void setTipoDocumento(TipoDocumento tipoDocumento) {
	this.tipoDocumento = tipoDocumento;
}
public Documento getPadre() {
	return padre;
}
public void setPadre(Documento padre) {
	this.padre = padre;
}
public Area getArea() {
	return area;
}
public void setArea(Area area) {
	this.area = area;
}
public Area getSubArea() {
	return subArea;
}
public void setSubArea(Area subArea) {
	this.subArea = subArea;
}
public Set<Documento> getHistorial() {
	return historial;
}
public void setHistorial(Set<Documento> historial) {
	this.historial = historial;
}
public Set<Valor> getValores() {
	return valores;
}
public void setValores(Set<Valor> valores) {
	this.valores = valores;
}
public String getEstatus() {
	return estatus;
}
public void setEstatus(String estatus) {
	this.estatus = estatus;
}

public DocumentoAprobador aprobadorActual(Usuario actual)
{
	ordenarAprobadores();
	for(DocumentoAprobador da : getAprobadores())
	{
		if (da.getAprobador().equals(actual))
		{
			return da;
		}
	}
	return null;
}

public DocumentoAprobador proximoAprobador()
{
	ordenarAprobadores();
	for(DocumentoAprobador da : getAprobadores())
	{
		if (da.getResultado() == null)
		{
			return da;
		}
	}
	return null;
}

public DocumentoAprobador ultimoAprobador()
{
	ordenarAprobadores();
	List lista = new ArrayList(getAprobadores());
	for(int i = 0; i < lista.size(); i++)
	{
		DocumentoAprobador da = (DocumentoAprobador) lista.get(i); 
		if (da.getResultado() == null)
		{
			if (i > 0)
			{
				return (DocumentoAprobador) lista.get(i - 1);
			}
			else
			{
				return null;
			}
		}
	}
	
	return (DocumentoAprobador) lista.get(lista.size() - 1);
}

public void ordenarAprobadores()
{
	  List listaAprobadores= new ArrayList(this.aprobadores);
		Collections.sort(listaAprobadores, new Comparator(){
			
			@Override
			public int compare(Object o1, Object o2) {
				org.modelo.dto.DocumentoAprobador dca1 = (org.modelo.dto.DocumentoAprobador) o1;
				org.modelo.dto.DocumentoAprobador dca2 = (org.modelo.dto.DocumentoAprobador) o2;
				return dca1.getOrden().compareTo(dca2.getOrden());
			}});
		
		this.setAprobadores(new LinkedHashSet(listaAprobadores));
}
}
