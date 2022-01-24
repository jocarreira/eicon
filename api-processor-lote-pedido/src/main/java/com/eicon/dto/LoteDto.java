package com.eicon.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//@XmlRootElement
public class LoteDto implements Serializable {

	private static final long serialVersionUID = -6707214914654851138L;
	
	//@XmlElement(name="idLote")
	private Long idLote;
	
	public Long getIdLote() {
		return idLote;
	}
	
	public void setIdLote(Long idLote) {
		this.idLote = idLote;
	}
}
