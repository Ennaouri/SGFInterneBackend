package org.sgfourriere.modele;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FormInfraction {
	private Long policierId;
	private Long depannageId;
	private String numeroMatricule;
	private String marqueVehicule;
	private String typeVehicule;
	private String typeInfraction;
	private MultipartFile fb;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateEntre;
}
